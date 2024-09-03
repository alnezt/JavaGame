package PaooGame.Tiles.MapElements;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

public class roadTile extends Tile
{

        public roadTile(int id)
        {
            super(Assets.road,id);
        }

        @Override
        public boolean IsSolid()
        {return false;}


}
