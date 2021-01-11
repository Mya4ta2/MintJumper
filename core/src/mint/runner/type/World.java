package mint.runner.type;

import mint.runner.content.Blocks;

public class World {
    public final int width, height;
    public final Tiles tiles;
    public Player player;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new Tiles(width,height);
        player = new Player();

        createTestWorld();
    }

    public void createTestWorld() {
        tiles.get(0,0).block = Blocks.dirt;
    }
}
