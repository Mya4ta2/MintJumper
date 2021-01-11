package mint.runner.type;

public class Tile {
    public int x, y;
    public Block block;

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
