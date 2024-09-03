package PaooGame.Enemies;

import PaooGame.Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

import static PaooGame.Levels.LevelManager.actual_level;
import static PaooGame.Player.*;


/*package PaooGame.Enemies;*/



public class MiniEnemy {
    public final int miniW = 48;
    public final int miniH = 48;
    public static int miniPozX;
    public static int miniPozY;
    protected int idd;
    public BufferedImage img;
    public static String enemyDirection="";
    public static boolean enemy2death=false;
    public static MovementStrategy movementStrategy;
    public MiniEnemy(int x, int y,MovementStrategy strategy)
    {

           this.miniPozX = x;
           this.miniPozY = y;
               img = Assets.mini;
        this.movementStrategy = strategy;

    }
    public static  MovementStrategy getMovementStrategyForLevel(int level) {
        switch (level) {
            case 2:
                return new Level2MovementStrategy();
            case 3:
                return new Level3MovementStrategy();
            default:
                return new Level2MovementStrategy();
        }
    }

    public static boolean CheckPlayerEnemyCollision()
{

int sideLength=48;
    int mikiRight = mikiPozX + sideLength;
    int mikiBottom = mikiPozY + sideLength;


    int miniRight = miniPozX + sideLength;
    int miniBottom = miniPozY + sideLength;

    if (mikiPozX >= miniRight || miniPozX >= mikiRight) {
        return false;
    }

    if (mikiPozY >= miniBottom || miniPozY >= mikiBottom) {
        return false;
    }



    return true;
}

    public void move() {
        movementStrategy.move(this);
    }
public static void MiniMovement()
{

    if(actual_level==2){
        if(miniPozX!=195) {
            if(CheckPlayerEnemyCollision())
                actual_health -= 50;
                miniPozX--;}
      if (miniPozX == 195 && miniPozY >= 320 && miniPozY <= 526) {
          miniPozY++;

          if(CheckPlayerEnemyCollision())
              actual_health -= 50;

          if(miniPozX==195 && miniPozY>=526)
          {
              miniPozX=673;miniPozY=327;
          }
      }
    }
       if (actual_level == 3) {
          // System.out.println("mx,my"+miniPozX+",my"+miniPozY);
           if (miniPozY < 527 && miniPozX ==721) {
               miniPozY++;
           }

           if (miniPozY == 527 && miniPozX >= 241) {
               miniPozX--;
           }
           if(CheckPlayerEnemyCollision())
               actual_health -= 50;
           if(miniPozY>=526 && miniPozX==721)
           {
               miniPozY=37;miniPozX=721;
           }
       }

       //System.out.println("mx,my"+miniPozX+",my"+miniPozY);
   }


    public void DrawEnemy2(Graphics g)
    {
        g.drawImage(img,miniPozX-screenX,miniPozY-screenY,miniW,miniH,null);
    }

  /*  public void RenderMini(Graphics g)
    {
        DrawEnemy2(g);
    }*/
    public void setMovementStrategy(MovementStrategy strategy) {
        this.movementStrategy = strategy;
    }
}
