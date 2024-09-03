package PaooGame.Tiles;

import PaooGame.Player;
import PaooGame.Tiles.MapElements.bigwoodspikeTile;
import PaooGame.Tiles.MapElements.groundTile;
import PaooGame.Tiles.MapElements.roadTile;
import PaooGame.Tiles.MapElements.smallwoodspikeTile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

/*! \class public class Tile
    \brief Retine toate dalele intr-un vector si ofera posibilitatea regasirii dupa un id.
 */
public class Tile extends Image {
    private static final int NO_TILES   = 48;
    public static Tile[] tiles          = new Tile[NO_TILES];       /*!< Vector de referinte de tipuri de dale.*/

        /// De remarcat ca urmatoarele dale sunt statice si publice. Acest lucru imi permite sa le am incarcate
        /// o singura data in memorie

    public static Tile smallwoodspikeTile=new smallwoodspikeTile(3);
    public static Tile bigwoodspikeTile=new bigwoodspikeTile(4);
    public static Tile roadTile=new roadTile(2);
    public static Tile groundTile=new groundTile(1);


    public static final int TILE_WIDTH  = 48;                       /*!< Latimea unei dale.*/
    public static final int TILE_HEIGHT = 48;                       /*!< Inaltimea unei dale.*/

    protected BufferedImage img;                                    /*!< Imaginea aferenta tipului de dala.*/
    public final int id;                                         /*!< Id-ul unic aferent tipului de dala.*/

    protected boolean collisionn;


    public Tile(BufferedImage img, int id)
    {
        this.img = img;
        this.id = id;

        tiles[this.id] = this;

    }
public void getCollisionsTile()
{
    tiles[1].collisionn=false;
    for(int i=2;i<=4;++i)
    {
        tiles[i].collisionn=true;
    }
}

    /*! \fn public void Update()
        \brief Actualizeaza proprietatile dalei.
     */
    public void Update()
    {

    }

    public void Draw(Graphics g, int x, int y)
    {
            /// Desenare dala
        g.drawImage(img, x- Player.screenX, y-Player.screenY, TILE_WIDTH, TILE_HEIGHT, null);
    }

    /*! \fn public boolean IsSolid()
        \brief Returneaza proprietatea de dala solida (supusa coliziunilor) sau nu.
     */
    public boolean IsSolid()
    {
        return false;
    }

    /*! \fn public int GetId()
        \brief Returneaza id-ul dalei.
     */
    public int GetId()
    {
        return id;
    }


    @Override
    public int getWidth(ImageObserver observer) {
        return 0;
    }

    @Override
    public int getHeight(ImageObserver observer) {
        return 0;
    }

    @Override
    public ImageProducer getSource() {
        return null;
    }

    @Override
    public Graphics getGraphics() {
        return null;
    }

    @Override
    public Object getProperty(String name, ImageObserver observer) {
        return null;
    }


}

