package PaooGame.Tiles.MapElements;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

public class BarrelTile extends Tile {
    public BarrelTile(int id){super(Assets.barrel,id);}
    @Override
    public boolean IsSolid()
    {return true;}

}
