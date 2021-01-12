package mint.runner.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import mint.runner.Vars;
import mint.runner.content.Blocks;
import mint.runner.ctype.Renderer;
import mint.runner.type.Player;
import mint.runner.type.Tile;
import mint.runner.type.World;

import static mint.runner.Vars.tileSize;

public class WorldRenderer implements Renderer {
    public World world;
    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Viewport viewport;
    public ShapeRenderer shapeRenderer;

    public Texture playerTexture; //oh no

    public WorldRenderer(World world) {
        this.world = world;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.zoom = 0.2f;
        viewport = new ScreenViewport(camera);
        playerTexture = new Texture("player.png");

        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        drawWorld(batch);
        batch.end();

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        drawDebugRectangles();
        shapeRenderer.end();

        camera.position.set(world.player.position.x * tileSize, world.player.position.y * tileSize,0);
        camera.update();
        viewport.apply();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public void drawDebugRectangles() {
        shapeRenderer.setColor(Color.RED);
        for (int i = 0; i < world.tiles.array.length; i++) {
            Tile tile = world.tiles.array[i];
            shapeRenderer.rect(
                    tile.bounds.x * tileSize,
                    tile.bounds.y * tileSize,
                    tile.bounds.width * tileSize,
                    tile.bounds.height * tileSize
            );
        }

        shapeRenderer.setColor(Color.CYAN);
        Player player = world.player;
        shapeRenderer.rect(
                player.bounds.x * tileSize,
                player.bounds.y * tileSize,
                player.bounds.width * tileSize,
                player.bounds.height * tileSize
        );

        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(
                player.groundHitBox.x * tileSize,
                player.groundHitBox.y * tileSize,
                player.groundHitBox.width * tileSize,
                player.groundHitBox.height * tileSize
        );
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
                playerTexture,
                world.player.position.x * tileSize,
                world.player.position.y * tileSize,
                world.player.width * tileSize,
                world.player.height * tileSize
        );
    }
}
