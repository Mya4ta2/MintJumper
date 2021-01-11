package mint.runner.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import mint.runner.Vars;
import mint.runner.content.Blocks;
import mint.runner.ctype.Renderer;
import mint.runner.type.Tile;
import mint.runner.type.World;

import static mint.runner.Vars.tileSize;

public class WorldRenderer implements Renderer {
    public World world;
    public SpriteBatch batch;

    public WorldRenderer(World world) {
        this.world = world;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        batch.begin();
        drawWorld(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    public void drawWorld(SpriteBatch batch) {
        Tile[] array = world.tiles.array;
        for (int i = 0; i < array.length; i++) {
            if (array[i].block != Blocks.air) {
                batch.draw(
                        array[i].block.texture,
                        array[i].x * tileSize,
                        array[i].y * tileSize,
                        array[i].block.width * tileSize,
                        array[i].block.height * tileSize
                );
            }
        }
    }
}
