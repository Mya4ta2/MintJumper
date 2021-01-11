package mint.runner.type;

import com.badlogic.gdx.math.Rectangle;
import mint.runner.Vars;
import mint.runner.content.Blocks;

public class Tile {
    public int x, y;
    public Block block = Blocks.air;
    public Rectangle bounds = new Rectangle();

    public Tile(int x, int y, Block block) {
        this.x = x;
        this.y = y;
        this.block = block;

        bounds.width = Vars.tileSize;
        bounds.height = Vars.tileSize;
        bounds.x = x;
        bounds.y = y;
    }

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;

        bounds.width = Vars.tileSize;
        bounds.height = Vars.tileSize;
        bounds.x = x;
        bounds.y = y;
    }
}
