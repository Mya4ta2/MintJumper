package mint.runner.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import mint.runner.ctype.Renderer;
import mint.runner.type.Tile;
import mint.runner.type.World;

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
            batch.draw(
                    array[i].block.texture,
                    array[i].x,
                    array[i].y
            );
        }
    }
}
