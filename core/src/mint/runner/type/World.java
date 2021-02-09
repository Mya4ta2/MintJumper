package mint.runner.type;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import mint.runner.Vars;
import mint.runner.content.Blocks;
import mint.runner.content.Overlays;
import mint.runner.content.Walls;

public class World {
    public final int width, height;
    public final Tiles tiles;
    public final String name;
    public final Entitys entitys;
    public Color backgroundColor;
    public Player player;

    public World(int width, int height, String name) {
        this.width = width;
        this.height = height;
        this.name = name;
        Vars.world = this;
        tiles = new Tiles(width, height);
        entitys = new Entitys();
        player = new Player(new Vector2(width/2 + 5,height/2 + 5));
        backgroundColor = Color.valueOf("fafafa");

        tiles.get(10,8).overlay = Overlays.bush;
        tiles.get(15,8).wall = Walls.dirt;
        tiles.get(16,8).block = Blocks.dirt;

        //createTestWorld();
        setTiles();
    }

    public void createTestWorld() {
        for (int i = 1; i < width-1; i++) {
            tiles.get(width/2 + i,height/2).block = Blocks.grass;
        }

        for (int i = 1; i < width-1; i++) {
            tiles.get(width/2 + i,height/2-1).block = Blocks.dirt;
        }

        for (int i = 1; i < width; i++) {
            tiles.get(i,2).block = Blocks.grass;
            tiles.get(i,1).block = Blocks.grass;
        }
    }
    
    public void setTiles() {
        for (int i = 0; i < tiles.array.length; i++) {
            tiles.array[i].setRounding(this);
        }
    }
}
