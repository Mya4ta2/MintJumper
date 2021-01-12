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
        player = new Player(new Vector2(width/2 + 5,height/2 + 5));

        createTestWorld();
        setTiles();
    }

    public void createTestWorld() {
        for (int i = 1; i < width-1; i++) {
            tiles.get(width/2 + i,height/2).block = Blocks.grass;
        }

        for (int i = 1; i < width-1; i++) {
            tiles.get(width/2 + i,height/2-1).block = Blocks.dirt;
        }
    }
    
    public void setTiles() {
        for (int i = 0; i < tiles.array.length; i++) {
            tiles.array[i].setNeighborAir(this);
        }
    }
}
