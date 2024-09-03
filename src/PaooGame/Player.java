
package PaooGame;

import PaooGame.Graphics.Assets;
import PaooGame.Levels.LevelManager;
import PaooGame.Keyhandlers.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends LevelManager{
    public static class GameException extends Exception {
        public GameException(String message) {
            super(message);
        }
    }
    public static class PlayerAlreadyExistsException extends GameException {
        public PlayerAlreadyExistsException(String message) {
            super(message);
        }
    }


    public final int mikiWidth = 48;
    public final int mikiHeight = 48;
    public static int mikiPozX;
    public static int mikiPozY;
    public int imgRow = 0;
    public int imgCol = 0;
    public int health = 3;
    private static int speed=3;
    public BufferedImage img;
    public static int screenX;
    public static int screenY;

    public static String direction = "";
    public static Player miki=null;
    private static boolean isRunning=false;
    public boolean isMoving=false;
    public double time=120;

   private Player(int x, int y) {
        this.mikiPozX = x;
        this.mikiPozY = y;
        img = Assets.miki[0][0];

    }

    public static Player createPlayer(int x, int y) throws PlayerAlreadyExistsException {
        if (miki == null) {
            miki = new Player(x, y);
            return miki;
        } else {
            throw new PlayerAlreadyExistsException("Player already exists");
        }
    }
    public void DrawPlayer(Graphics g) {
        g.drawImage(img, mikiPozX-screenX, mikiPozY-screenY, mikiWidth, mikiHeight, null);
    }

    public void setImgRow(int row) {
        imgRow = row;
    }

    public void setImgCol(int col) {
        imgCol = col;
    }

    public int getImgRow() {
        return imgRow;
    }

    public int getImgCol() {
        return imgCol;
    }

    public void Update(int row, int col) {
        img = Assets.miki[row][col];

    }

    public void MovementMiki(KeyHandler keyH)  {
       screenX=mikiPozX-816/2+mikiWidth;
       screenY=mikiPozY-624/2+mikiWidth;
//coordonate pentru camera


       if(isRunning==false) speed=2;//schimb viteza daca nu e sprint

        if (keyH.isSus()) {
            direction = "up";

                moveup(keyH);


            isMoving=true;

        }
        if (keyH.isJos() ) {
            direction = "down";
            movedown(keyH);
            isMoving=true;
        }
        if (keyH.isStanga() ) {

            direction = "left";
            moveleft(keyH);
            isMoving=true;
        }
        if (keyH.isDreapta()) {

            direction = "right";
            try {
                moveright(keyH);
            } catch (InvalidPlayerPositionException e) {
               System.out.println(e.getMessage());
            }
            isMoving=true;
        }
        if(keyH.isRunning())
        {
            isRunning=true;
            isMoving=true;
        }
      SuperRun(keyH);

    }

    public void moveup(KeyHandler keyH) {
        try {
            if (direction.equals("up")) {
                // Check the positions for any obstacles or boundaries
                if (!Checker(mikiPozX - 1, mikiPozY + 15) && !Checker(mikiPozX + mikiWidth - 15, mikiPozY + mikiHeight - 15)) {
                    mikiPozY -= speed; // Move up
                    imgRow = 1;
                    System.out.println("( " + mikiPozX + " " + mikiPozY + " )");

                    imgCol = (imgCol + 1) % 4;
                    Update(imgRow, imgCol);
                    SpikeDamageUp();
                    isRunning = false; // Assuming you want to stop running after moving up
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Properly handle exceptions
        }
    }


    public  void SpikeDamageDown()
    {   InimioareManagement();//apelez modificarea aparitiei obiectelor ce imi indica hp ul

        if(CheckerSpike(mikiPozX, mikiPozY + mikiHeight + 1)==true && CheckerSpike(mikiPozX + mikiWidth - 12, mikiPozY + mikiHeight + 1)==true && isRunning==false)
        {
            System.out.println("SpikeDamage");
            actual_health--;
            System.out.println("health is " +actual_health);
        }

    }
    //Metodele SpikeDamage_directie imi indica, coliziunea cu tile-uri de tip capcana si ma folosesc de distanta dintre personaj si capcana pt a crea coliziunea

    public  void SpikeDamageUp()
    {
        InimioareManagement();
        if(CheckerSpike(mikiPozX - 1, mikiPozY + 15) && CheckerSpike(mikiPozX - 15 + mikiWidth, mikiPozY + mikiHeight - 15) && isRunning==false /*&& isMoving==false*/)
        {
            System.out.println("SpikeDamage--aoleeu");
            actual_health--;
            System.out.println("health is " +actual_health);

        }


    }
   public  void SpikeDamageLeft()
    {
InimioareManagement();
        if(CheckerSpike(mikiPozX+mikiWidth-32,mikiPozY-15+mikiHeight) && CheckerSpike(mikiPozX+mikiWidth-32, mikiPozY + 15) && isRunning==false /*&& isMoving==false*/)
        {
            System.out.println("SpikeDamage");
            actual_health--;
            System.out.println("health is " +actual_health);
        }

    }

    public  void SpikeDamageRight()
    {
InimioareManagement();
        if(CheckerSpike(mikiPozX + mikiWidth - 1, mikiPozY + mikiHeight - 15) && CheckerSpike(mikiPozX + mikiWidth - 15, mikiPozY + 15) && isRunning==false /*&& isMoving==false*/)
        {
            System.out.println("SpikeDamage");
            actual_health--;
            System.out.println("health is " +actual_health);
        }

    }

    //Metodele de tipul move_directie imi verifica, coliziunea cu harta si imi deplaseaza personajul folosindu ma de animatii
    public void movedown(KeyHandler keyH)  {
        if (direction.equals("down") && !Checker(mikiPozX, mikiPozY + mikiHeight + 1) && !Checker(mikiPozX + mikiWidth - 12, mikiPozY + mikiHeight + 1)) {
            mikiPozY += speed; // Move down
            imgRow = 0;
            System.out.println("( " + mikiPozX + " " + mikiPozY + " )");
            imgCol = (imgCol + 1) % 4;
            Update(imgRow, imgCol);//animatii personaj
            direction = "down";
            SpikeDamageDown();
            isRunning = false;
        }
        }


    public void moveleft(KeyHandler keyH)  {
        if (direction.equals("left") && !Checker(mikiPozX, mikiPozY - 15 + mikiHeight) && !Checker(mikiPozX + mikiWidth - 32, mikiPozY + 15) && mikiPozX > 0) {
            mikiPozX -= speed;
            imgRow = 2;
            System.out.println("( " + mikiPozX + " " + mikiPozY + " )");
            direction = "left";
            imgCol = (imgCol + 1) % 4;
            Update(imgRow, imgCol);
            SpikeDamageLeft();
            isRunning = false;

        }
    }

    public void moveright(KeyHandler keyH) throws  InvalidPlayerPositionException {
        if (direction.equals("right") && !Checker(mikiPozX + mikiWidth - 1, mikiPozY + mikiHeight - 15) && !Checker(mikiPozX + mikiWidth - 1, mikiPozY + 15) && mikiPozX < 770) {
            {
                if ((actual_level == 2 && mikiPozX >= 768 && mikiPozY >= 382)||(actual_level==1 && mikiPozX>=765 && mikiPozY>=426)) {
                    throw new InvalidPlayerPositionException();

                }
                mikiPozX += speed;
                imgRow = 3;
                System.out.println("( " + mikiPozX + " " + mikiPozY + " )");
                imgCol = (imgCol + 1) % 4;
                Update(imgRow, imgCol);
                direction = "right";
                SpikeDamageRight();
                isRunning = false;
            }
        }
    }



//schimba viteza pt sprint
    public void SuperRun(KeyHandler keyH)
    {
        if(keyH.isRunning())
        {
            speed=4;
        }

    }
//clasa pentru exceptia ce imi aparea cand schimbam hartile pentru niveluri

    public class InvalidPlayerPositionException extends Exception
    {
        public InvalidPlayerPositionException() {

            super("Invalid Position");
            if(actual_level ==2)
            {
                mikiPozY=382;
            }
            if(actual_level==1)
            {
                mikiPozY=426;
            }

        }

    }


}


