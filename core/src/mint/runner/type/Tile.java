package mint.runner.type;

import mint.runner.content.Blocks;

public class Tile {
    public int x, y;
    public Block block = Blocks.air;

    public Tile(int x, int y, Block block) {
        this.x = x;
        this.y = y;
        this.block = block;
    }

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
