package PaooGame.Tiles.MapElements;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

public class smallwoodspikeTile extends Tile
{
    public smallwoodspikeTile(int id)
    {
        super(Assets.smallwoodspike,id);
    }

    @Override
    public boolean IsSolid()
    {return true;}
}
