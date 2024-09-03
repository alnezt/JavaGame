package PaooGame.Tiles.MapElements;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

public class bigwoodspikeTile extends Tile {

        public bigwoodspikeTile(int id)
        {
            super(Assets.bigwoodspike,id);
        }

        @Override
        public boolean IsSolid()
        {return true;}

}
