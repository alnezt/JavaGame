
package PaooGame;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

import java.awt.*;

public class hearts extends Tile {
    private final int heartw=16;
    private final int hearth=16;

    public hearts(int id,int x,int y) {
        super(Assets.hearts[x][y],id);
    }

    @Override
    public void Draw(Graphics g, int x, int y) {
        g.drawImage(img, x, y, heartw, hearth, null);
    }
    public void Update(int x,int y)
    {
        super.img=Assets.hearts[x][y];
    }
}


