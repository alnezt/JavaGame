package PaooGame.Tiles.Entities;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

import java.awt.*;

public class Miki extends Tile {
    private final int mikiWidth = 16;
    private final int mikiHeight = 16;



    public Miki(int id, int x, int y) {
        super(Assets.miki[x][y], id);
    }

    @Override
    public void Draw(Graphics g, int x, int y) {
        g.drawImage(img, x, y, mikiWidth, mikiHeight, null);
    }

    public void Update(int x,int y)
    {
        super.img=Assets.miki[x][y];
    }

    public boolean isSolid()
    {
        return false;
    }

}




