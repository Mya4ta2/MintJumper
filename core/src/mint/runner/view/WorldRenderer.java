package mint.runner.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import mint.runner.Vars;
import mint.runner.content.Blocks;
import mint.runner.ctype.Renderer;
import mint.runner.type.Tile;
import mint.runner.type.World;

import static mint.runner.Vars.tileSize;

public class WorldRenderer implements Renderer {
    public World world;
    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Viewport viewport;

    public WorldRenderer(World world) {
        this.world = world;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        viewport = new ScreenViewport(camera);
    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        drawWorld(batch);
        batch.end();

        camera.position.set(world.player.position.x,world.player.position.y,0);
        System.out.println(world.player.position.x);
        camera.update();
        viewport.apply();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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

        batch.draw(
                Blocks.dirt.texture,
                world.player.position.x * tileSize,
                world.player.position.y * tileSize,
                world.player.width * tileSize,
                world.player.height * tileSize
        );
    }
}
