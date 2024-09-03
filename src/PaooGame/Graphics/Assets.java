package PaooGame.Graphics;

//import PaooGame.Exceptions.AssetLoadException;

import java.awt.image.BufferedImage;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets {
    /// Referinte catre elementele grafice (dale) utilizate in joc.
    /*public static BufferedImage playerLeft;
    public static BufferedImage playerRight;*/
    public static BufferedImage smallwoodspike;
    public static BufferedImage bigwoodspike;
    public static BufferedImage road;
    public static BufferedImage ground;
    public static BufferedImage[][] miki;
    public static BufferedImage[][] rudith;
    public static BufferedImage barrel;
    public static BufferedImage[][] hearts;
    public static BufferedImage mini;
    public static  class   AssetLoadException extends Exception {
        public AssetLoadException(String message) {
            super(message);
        }

        public AssetLoadException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static void Init() throws AssetLoadException {

        try {
            /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
            SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/elemente1.png"));
            SpriteSheet mikisheet = new SpriteSheet(ImageLoader.LoadImage("/textures/redsamurai.png"));
            SpriteSheet rudithsheet = new SpriteSheet(ImageLoader.LoadImage("/textures/rudithh.png"));
            barrel = ImageLoader.LoadImage("/textures/butoi.png");
            SpriteSheet heartsheet = new SpriteSheet(ImageLoader.LoadImage("/textures/inimi.png"));
            miki = new BufferedImage[4][7];

            for (int i = 0; i < 4; ++i) {
                for (int j = 0; j < 7; ++j) {
                    miki[i][j] = mikisheet.cropMiki(i, j);
                }
            }

            smallwoodspike = sheet.crop(2, 0);
            bigwoodspike = sheet.crop(3, 0);
            road = sheet.crop(0, 0);
            ground = sheet.crop(1, 0);

            rudith = new BufferedImage[5][1];

            for (int i = 0; i < 5; ++i) {
                for (int j = 0; j < 1; ++j) {
                    rudith[i][j] = rudithsheet.cropRudith(i, j);
                }
            }

            hearts = new BufferedImage[3][1];
            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 1; ++j) {
                    hearts[i][j] = heartsheet.cropHeart(i, j);
                }
            }

            mini = ImageLoader.LoadImage("/textures/mini.png");
        } catch (Exception e) {
            throw new AssetLoadException("Error loading assets", e);
        }
    }
}
