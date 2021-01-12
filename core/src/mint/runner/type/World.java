package mint.runner.type;

import com.badlogic.gdx.math.Vector2;
import mint.runner.content.Blocks;

public class World {
    public final int width, height;
    public final Tiles tiles;
    public Player player;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new Tiles(width,height);
        player = new Player(new Vector2(5,5));

        createTestWorld();
    }

    public void createTestWorld() {
        for (int i = 0; i < width; i++) {
            tiles.get(i,1).block = Blocks.dirt;
        }

        for (int i = 0; i < width; i++) {
            tiles.get(i,0).block = Blocks.dirt;
        }
    }
}
