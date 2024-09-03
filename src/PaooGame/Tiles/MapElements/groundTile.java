package PaooGame.Tiles.MapElements;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

public class groundTile extends Tile
{

        public groundTile(int id)
        {
            super(Assets.ground,id);
        }

        @Override
        public boolean IsSolid()
        {return true;}



}
