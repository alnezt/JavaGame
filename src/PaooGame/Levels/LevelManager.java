package PaooGame.Levels;

import PaooGame.Enemies.Level2MovementStrategy;
import PaooGame.Enemies.Level3MovementStrategy;
import PaooGame.Enemies.MiniEnemy;
import PaooGame.Enemies.Ruddith;
import PaooGame.Tiles.*;
import PaooGame.Tiles.MapElements.*;


import java.awt.*;
import java.awt.image.BufferedImage;

//import static PaooGame.Levels.Level1.InitLvl2;
import static PaooGame.Enemies.MiniEnemy.*;
import static PaooGame.Enemies.Ruddith.*;
import static PaooGame.Levels.Level1.*;
import static PaooGame.Player.mikiPozX;
import static PaooGame.Player.mikiPozY;
import static PaooGame.Tiles.Tile.TILE_HEIGHT;
import static PaooGame.Tiles.Tile.TILE_WIDTH;
import static java.lang.System.exit;

public class LevelManager {
    protected static int[][] map;
    public static int actual_health=75;
    public static int actual_level=1;
//    public static int levelJDBC=1;
    public LevelManager(){
       // map=null;
    }

   public static int SetLevel()
    {

       if(mikiPozX==766 && (mikiPozY<=431 && mikiPozY>=416) && actual_level==1)//de la lvl1 la lvl 2
       {actual_level=2;InitLvl1(); mapPath="/textures/mapal2bun.png";
           mikiPozX=2;mikiPozY=470;actual_health=75;enemy1death=false;
           Ruddith.enPozX=480; enPozY=135;
           System.out.println("nivel 2");//levelJDBC=2;

           movementStrategy=new Level2MovementStrategy();
       }

       if(mikiPozX==768 && (mikiPozY>=368 && mikiPozY<=383 ) && actual_level==2)
       {
           actual_level=3;
           mapPath="/textures/mapal3bun.png";InitLvl1();
           mikiPozX=2;mikiPozY=470;actual_health=75;enemy1death=false;

           miniPozX=721;miniPozY=37;
           System.out.println("nivel 3");
           movementStrategy=new Level3MovementStrategy();
       }

        return actual_level;
    }
//determina id ul pt tipul de inimioara
    public static int InimioareManagement()
    {
        int health = 3;
        if(actual_health<25 && actual_health>0)
        {
            health=1;
        }

        if(actual_health>=25 && actual_health<50)
        {
            health=2;
        }
        if(actual_health>=50 && actual_health<=75)
        {
            health=3;
        }
        if(actual_health<=0)
        {
            health=0;
        }
        return health;
    }
    public static int[][] GetMap(BufferedImage image)
    {
        if(image==null)
    {
        System.out.println("IMAGINE GOALA");
        exit(0);
    }

        int h= image.getHeight();
        int w=image.getWidth();
        map=new int[h][w];
      //  visible=new boolean[h][w];

        for(int j=0;j<h;++j)
        {
            for(int i=0;i<w;++i)
            {
                Color color=new Color(image.getRGB(i,j));
                int value=color.getRed();
               // if(value>=48) value=4;
                map[j][i]=value;
               // visible[j][i] = true;
            }
        }
        return map;
    }





    public static int getSpriteIndex(int x,int y){return map[y][x];}


//deseneaza harta folosindu se de indexul bitului de red; fiecare index e pt y=un tip de tile
    public static void DrawMap(Graphics g,BufferedImage img)
    {
        map=GetMap(img);
        for(int j=0;j< img.getHeight();++j)
        {
            for(int i=0;i<img.getWidth();++i){
                int index = getSpriteIndex(i,j);
                if(index==1)
                {
                    Tile groundTile= new groundTile(index);
                    groundTile.Draw(g,i*48,j*48);

                }
                else {
                        if(index==2)
                        {
                            Tile roadTile=new roadTile(index);
                            roadTile.Draw(g,i*48,j*48);
                        }
                        else {
                            if(index==3)
                            {
                                Tile smallspikeTile=new smallwoodspikeTile(index);
                                smallspikeTile.Draw(g,i*48,j*48);
                            }
                            else {
                                if(index==4)
                                {
                                    Tile bigspikeTile=new bigwoodspikeTile(index);
                                    bigspikeTile.Draw(g,i*48,j*48);
                                }
                                else
                                {
                                    if(index==5)
                                    {
                                        Tile barrelTile=new BarrelTile(index);
                                        barrelTile.Draw(g,i*48,j*48);
                                    }

                                }

                        }}
                }

            }
        }
    }

    public  boolean CheckerSpike(int x, int y)
    {
        int pozX,pozY;
        pozX=x/TILE_WIDTH;
        pozY=y/TILE_HEIGHT;
        int i=getSpriteIndex(pozX,pozY);

        if(i==3 || i==4)
        {
            return true;
        }
        return false;

    }


    public static boolean Checker(int x, int y)
    {
        int pozX,pozY;
        pozX=x/TILE_WIDTH;
        pozY=y/TILE_HEIGHT;
        int i=getSpriteIndex(pozX,pozY);
        if(i==1 || i==5)
        {
            return true;
        }

        return false;
    }


}
