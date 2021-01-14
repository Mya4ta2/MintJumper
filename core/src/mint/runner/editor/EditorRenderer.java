package mint.runner.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import mint.runner.Cursor;
import mint.runner.Vars;
import mint.runner.content.Blocks;
import mint.runner.ctype.Renderer;
import mint.runner.type.*;

import static mint.runner.Vars.tileSize;

public class EditorRenderer implements Renderer {
    public World world;
    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Viewport viewport;
    public ShapeRenderer shapeRenderer;

    public Texture playerRightWalkTexture; //oh no
    public Texture playerLeftWalkTexture;
    public Texture playerRightFailTexture;
    public Texture playerLeftFailTexture;

    public Texture background;

    public EditorRenderer(World world) {
        this.world = world;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.zoom = 0.4f;
        viewport = new ScreenViewport(camera);
        playerRightWalkTexture = new Texture("player.png");
        playerLeftWalkTexture = new Texture("player-left.png");
        playerRightFailTexture = new Texture("player-right-fail.png");
        playerLeftFailTexture = new Texture("player-left-fail.png");

        background = new Texture("background.png");

        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        drawBackground(batch);
        drawWorld(batch);
        drawPlayer(batch);
        drawBullets(batch);

        if (EditorVars.currentContentSelected != null) {
            batch.draw(((Block) EditorVars.currentContentSelected).neighborAir.up, Cursor.worldX, Cursor.worldY);
        }

        batch.end();

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        //drawDebugRectangles();
        drawCursorHitbox();
        drawWorldBounds();
        shapeRenderer.end();

        camera.position.set(
                (world.player.position.x * tileSize) + (world.player.width * tileSize) / 2,
                (world.player.position.y * tileSize) + (world.player.height * tileSize) / 2,
                0
        );
        camera.update();
        viewport.apply();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public void drawCursorHitbox() {
        shapeRenderer.rect(
                (int) (Cursor.worldX / tileSize) * tileSize,
                (int) (Cursor.worldY / tileSize) * tileSize,
                tileSize,
                tileSize);
    }

    public void drawWorldBounds() {
        shapeRenderer.rect(0,0,world.width * tileSize,world.height * tileSize);
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
                        array[i].neighborAirState.currentBlockTexture,
                        array[i].x * tileSize,
                        array[i].y * tileSize,
                        array[i].block.width * tileSize,
                        array[i].block.height * tileSize
                );
            }
        }
    }

    public void drawBackground(SpriteBatch batch) {
        batch.draw(
                background,
                world.player.position.x * 2,
                world.player.position.y * 2,
                world.width * tileSize,
                world.height * tileSize
        );
    }

    public void drawBullets(SpriteBatch batch) {
        for (int i = 0; i < world.entitys.array.size; i++) {
            if (world.entitys.array.get(i) instanceof Bullet) {
                Bullet bullet = (Bullet) world.entitys.array.get(i);
                batch.draw(
                        bullet.type.texture,
                        bullet.position.x * tileSize,
                        bullet.position.y * tileSize
                );
            }
        }
    }

    public void drawPlayer(SpriteBatch batch) {
        Texture texture = null;

        switch (world.player.state) {
            case WalkLeft: texture = playerLeftWalkTexture; break;
            case WalkRight: texture = playerRightWalkTexture; break;
            case LeftFail: texture = playerLeftFailTexture; break;
            case RightFail: texture = playerRightFailTexture; break;
        }

        batch.draw(
                texture,
                world.player.position.x * tileSize,
                world.player.position.y * tileSize,
                world.player.width * tileSize,
                world.player.height * tileSize
        );

        if (world.player.weapon != null && world.player.weapon.sprite != null) world.player.weapon.sprite.draw(batch);
    }
}

