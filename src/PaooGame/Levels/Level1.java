package PaooGame.Levels;

import PaooGame.Graphics.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

//import static PaooGame.Levels.LevelManager.SetLevel;
import static PaooGame.Levels.LevelManager.actual_level;

public class Level1 {
    protected static BufferedImage image;
    protected static int[][] map;
    //in aceasta clasa initializez harta pt fiecare nivel
public static String mapPath="/textures/mapaversiunenoua.png";

    public static void InitLvl1()
    {

             ChangeMapPath();
            image= ImageLoader.LoadImage(mapPath);

    }

    public static void ChangeMapPath()
    {
        if(actual_level==1)
        {
            mapPath="/textures/mapaversiunenoua.png";

        }
        else
        {
            if(actual_level==2)
            {
                mapPath="/textures/mapal2bun.png";
            }
            else {
                mapPath="/textures/mapal3bun.png";
            }
        }
    }

    public static void InitLvl2()
    {

        image= ImageLoader.LoadImage("/textures/mapal2bun.png");
    }


    public static void InitLvl3()
    {

        image= ImageLoader.LoadImage("/textures/mapal3bun.png");
    }

    public static void DrawLevel(Graphics g)
    {
        LevelManager.DrawMap(g,image);
    }

    public static int [][] GetMap()
    {
        return map;
    }
}
