package PaooGame.Tiles.Entities;

import PaooGame.Graphics.Assets;
import PaooGame.Player;
import PaooGame.Tiles.Tile;

import java.awt.*;

public class Mini extends Tile {

    private final int miniw=48;
    private final int minih=48;

    public Mini(int id,int x,int y)
    {
        super(Assets.mini,id);
    }

    @Override
    public void Draw(Graphics g, int x, int y) {
        g.drawImage(img, x- Player.screenX, y-Player.screenY, miniw, minih, null);
    }

    public void Update(int x,int y)
    {
        super.img=Assets.mini;
    }

    public boolean isSolid()
    {
        return false;
    }
}
