
package PaooGame.Tiles.Entities;

import PaooGame.Graphics.Assets;
import PaooGame.Player;
import PaooGame.Tiles.Tile;

import java.awt.*;

public class Rudith extends Tile {
    private final int rudithH=56;
    private final int rudithW=69;

    public Rudith(int id,int x,int y)
    {
        super(Assets.rudith[x][y],id);
    }

    @Override
    public void Draw(Graphics g, int x, int y) {
        g.drawImage(img, x- Player.screenX, y-Player.screenY, rudithW, rudithH, null);
    }

    public void Update(int x,int y)
    {
        super.img=Assets.rudith[x][y];
    }

    public boolean isSolid()
    {
        return false;
    }
}

