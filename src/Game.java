
import PaooGame.Enemies.*;


import PaooGame.GameWindow.GameWindow;
import PaooGame.Graphics.Assets;
import PaooGame.Graphics.ImageLoader;
import PaooGame.Keyhandlers.KeyHandler;

import PaooGame.Levels.Level1;
import PaooGame.Player;
import PaooGame.Tiles.Tile;
import PaooGame.life.inimioare;
import org.sqlite.JDBC;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.sql.*;
import java.text.DecimalFormat;

import static PaooGame.Enemies.MiniEnemy.*;
import static PaooGame.Enemies.Ruddith.*;
import static PaooGame.Graphics.Assets.rudith;
import static PaooGame.Levels.Level1.*;
import static PaooGame.Player.*;
import static java.lang.System.exit;

public class Game implements Runnable {

    public GameWindow wnd;        /*!< Fereastra in care se va desena tabla jocului*/
    private boolean runState;   /*!< Flag ce starea firului de executie.*/
    private Thread gameThread; /*!< Referinta catre thread-ul de update si draw al ferestrei*/
    private BufferStrategy bs;         /*!< Referinta catre un mecanism cu care se organizeaza memoria complexa pentru un canvas.*/
    private Graphics g;          /*!< Referinta catre un context grafic.*/
    private Tile tile; /*!< variabila membra temporara. Este folosita in aceasta etapa doar pentru a desena ceva pe ecran.*/
    private final KeyHandler keyH = new KeyHandler();   //keyhandlerul meu
    public static Player miki;
    public static Ruddith rudith;
    public static MiniEnemy mini;

    public static inimioare mikilife1;
    public static inimioare mikilife2;
    public static inimioare mikilife3;
    public Connection c;
    public Statement stmt;
    // private JDBC jdbc;
    MovementStrategy movementStrategy;
    BufferedImage backgr = ImageLoader.LoadImage("/textures/backgr22.png");
    public static boolean win = false;
    private Menu menu;

    public Game(String title, int width, int height) {
        wnd = new GameWindow(title, width, height);
        runState = false;


    }


    //metoda pt initializarea bazei de date
    public void InitJDBC() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:gamebun2.db");
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS GameStateBunFinal ("
                    + "ID INT PRIMARY KEY NOT NULL,"
                    + "ACTUAL_HEALTH INT NOT NULL,"
                    + "LEVEL INT NOT NULL,"
                    + "MIKI_POZX INT NOT NULL,"
                    + "MIKI_POZY INT NOT NULL,"
                    + "EN_POZX INT NOT NULL,"
                    + "EN_POZY INT NOT NULL,"
                    + "TIME INT NOT NULL,"
                    + "MINI_POZX INT NOT NULL,"
                    + "MINI_POZY INT NOT NULL)";
            stmt.execute(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            exit(0);
        }
        System.out.println("Table created successfully");
    }

    //am 3 obiecte "inimioara" pe care le modific in functie de hp ulplayer ului
    public void InitHearts() {
        if (actual_health > 50 && actual_health <= 75) {
            mikilife1 = new inimioare(30, 25, 0);
            mikilife2 = new inimioare(70, 25, 0);
            mikilife3 = new inimioare(110, 25, 0);
        } else {
            if (actual_health > 25 && actual_health <= 50) {
                mikilife1 = new inimioare(30, 25, 0);
                mikilife2 = new inimioare(70, 25, 0);
                mikilife3 = new inimioare(110, 25, 2);
            } else {
                if (actual_health > 0 && actual_health <= 25) {
                    mikilife1 = new inimioare(30, 25, 0);
                    mikilife2 = new inimioare(70, 25, 2);
                    mikilife3 = new inimioare(110, 25, 2);
                } else {
                    mikilife1 = new inimioare(30, 25, 2);
                    mikilife2 = new inimioare(70, 25, 2);
                    mikilife3 = new inimioare(110, 25, 2);
                }
            }
        }
    }

    //initializez personaje, jdbc,
    private void InitGame() {
        wnd = new GameWindow("Soarecu", 816, 624);
        /// Este construita fereastra grafica.
        wnd.BuildGameWindow();
        wnd.GetCanvas().addKeyListener(keyH);
        /// Se incarca toate elementele grafice (dale)
        try {
            Assets.Init();
        } catch (Assets.AssetLoadException e) {
            System.out.println(e.getMessage());
        }
        try {
            miki = createPlayer(2, 470);
        } catch (PlayerAlreadyExistsException e) {
        }
        rudith = new Ruddith(480, 135, 1);
        PlayerObserver playerObserver = new PlayerObserver();

        rudith.addObserver(playerObserver);//am folosit design pattern ul pt a afisa pozitiile inamicului rudith
        //m a ajutat mult la debugging

        MovementStrategy movementStrategy = getMovementStrategyForLevel(actual_level);
        mini = new MiniEnemy(673, 327, movementStrategy);
        //ma folosesc de strategiile pt cele 2 nivele
        InitHearts();
        InitJDBC();
        loadGameState();//incarc ce s a salvat in baza de date
        Level1.InitLvl1();//initializez harta

    }

    public void run() {
        /// Initializeaza obiectul game
        InitGame();

        long oldTime = System.nanoTime();
        long curentTime;
        final int framesPerSecond = 60;
        final double timeFrame = 1000000000 / framesPerSecond;

        while (runState == true) {
            /// Se obtine timpul curent
            curentTime = System.nanoTime();
            /// Daca diferenta de timp dintre curentTime si oldTime mai mare decat 16.6 ms
            if ((curentTime - oldTime) > timeFrame) {
                /// Actualizeaza pozitiile elementelor
                MovementMiki();
                EnemyMovement();

            }
            if (actual_level == 2 || actual_level == 3)
                MovementMini();
            /// Deseneaza elementele grafica in fereastra.
            Draw();
            oldTime = curentTime;
        }
    }


    public synchronized void StartGame() {
        if (runState == false) {
            runState = true;
            gameThread = new Thread(this);
            gameThread.start();
        }
    }



    public void MovementMiki() {
        miki.MovementMiki(keyH);
        saveGameState();
    }

    public void MovementMini() {

            if (actual_level == 2 || actual_level == 3) {
                if (mini != null) {
                    mini.move();
                } else {
                    System.err.println("Error: mini is not initialized.");
                }

        }
        }


    public void EnemyMovement() {

        if (actual_level == 1)
            rudith.EnemyFollowingLvl1(480, 135);
        else {
            if (actual_level == 2) {
                rudith.EnemyFollowingLvl2(480, 135);
            } /*else {
                if (actual_level == 3) {
                    {
                        throw new RudithNullException();

                    }
                }*/
            }

    }
    
    private void DrawAllHearts() {
        mikilife1.DrawHearts(g);
        mikilife2.DrawHearts(g);
        mikilife3.DrawHearts(g);
    }
//desenez tot
    private void Draw() {
        bs = wnd.GetCanvas().getBufferStrategy();
        if (bs == null) {
            try {
                wnd.GetCanvas().createBufferStrategy(3);
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        g = bs.getDrawGraphics();
        g.drawImage(backgr, 0, 0, 816, 624, null);

        Level1.InitLvl1();
        Level1.DrawLevel(g);

        miki.DrawPlayer(g);
        if (!enemy1death && actual_level==1 || actual_level==2) {
            rudith.DrawEnemy(g);
        }

        DrawAllHearts();
        InitHearts();
        Game_Over();
        if (win) {
            DrawWinMessage(g);

        } else if (lose) {
            DrawLoseMessage(g);

        }
        SetLevel();

        if (actual_level == 2 || actual_level == 3) mini.DrawEnemy2(g);
        timer();
        DrawTimer();

        bs.show();
        g.dispose();
    }

    public boolean lose=false;

   public void Game_Over() {
       if (time <= 0) {
           resetGame();//se reia de la inceput
           lose = true;
       }

       if (actual_level == 1 && actual_health <= 0) {
           resetGame();
           lose = true;
       }

       if (actual_level == 2 && actual_health <= 0) {
          resetGame();
           lose = true;
       }

       if (actual_level == 3 && actual_health <= 0) {
           resetGame();
           lose = true;
       }

       if (mikiPozX <= 463 && mikiPozX >= 431 && mikiPozY == 320 && actual_level == 3 && !win) {
           resetGame();
           win = true;
       }
       if(lose==true)
       {
         //  Menu.render(g);
     // lose=false;
       }
   }

    private void resetGame() {
        actual_level = 1;
        InitLvl1();
        mikiPozX = 2;
        mikiPozY = 470;
        actual_health = 75;
        enemy1death = false;
        mapPath = "/textures/mapaversiunenoua.png";
        Ruddith.enPozX = 480;
        enPozY = 135;
        time = 120;
        miniPozX=673;miniPozY=327;
        movementStrategy=new Level2MovementStrategy();
    }


    public double time = 120;
    public double timedraw=2;
    public void TimeDraw() {
        timedraw -= 1. / 60;
    }
    public void timer() {
       if(!keyH.isRunning())
       {   time -= 1. / 60;}
       else {
           time -= 4. / 60;
       }
        Font fnt = new Font("Monospaced", Font.BOLD, 20);
        g.setFont(fnt);
        g.setColor(Color.white);
    }

    public void DrawTimer() {
        DecimalFormat dformat = new DecimalFormat("#0.0");
        g.drawString("timp:" + dformat.format(time) + "s", 20, 20);
    }
    private void DrawWinMessage(Graphics g) {
       TimeDraw();
        if(timedraw>=0){
        Font fnt = new Font("Monospaced", Font.BOLD, 40);
        g.setFont(fnt);
        g.setColor(Color.GREEN);
        g.drawString("You Win!", 300, 300);}
    }

    private void DrawLoseMessage(Graphics g) {
      TimeDraw();
        if(timedraw>=0){
        Font fnt = new Font("Monospaced", Font.BOLD, 40);
        g.setFont(fnt);
        g.setColor(Color.BLUE);
        g.drawString("Game Over", 300, 300);}

    }


    public Graphics getG() {
        return g;
    }
//metoda pt a salva baza de date
    public void saveGameState() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:gamebun2.db");
            stmt = c.createStatement();
            String sql = "INSERT OR REPLACE INTO GameStateBunFinal (ID, ACTUAL_HEALTH,LEVEL, MIKI_POZX, MIKI_POZY, EN_POZX, EN_POZY, TIME, MINI_POZX, MINI_POZY) "
                    + "VALUES (1, "  + actual_health + ", "+actual_level+"," + mikiPozX + ", " + mikiPozY + "," + enPozX + ", " + enPozY + ", " +time + ", " + miniPozX + ", " + miniPozY + ");";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
//metoda pt a incarca baza de date
    public void loadGameState() {
        try {

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:gamebun2.db");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM GameStateBunFinal WHERE ID = 1;");
            if (rs.next()) {

                actual_health = rs.getInt("ACTUAL_HEALTH");
                mikiPozX = rs.getInt("MIKI_POZX");
                mikiPozY = rs.getInt("MIKI_POZY");
                enPozX = rs.getInt("EN_POZX");
                enPozY = rs.getInt("EN_POZY");
                time=rs.getInt("TIME");
               // System.out.println( "mapa = " +mapPath);
                miniPozX = rs.getInt("MINI_POZX");
                miniPozY = rs.getInt("MINI_POZY");
                actual_level = rs.getInt("LEVEL");
            }
          //  stmt.executeUpdate(sql);
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }


}
