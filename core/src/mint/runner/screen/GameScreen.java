package mint.runner.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import mint.runner.Cursor;
import mint.runner.Vars;
import mint.runner.controller.WorldController;
import mint.runner.type.World;
import mint.runner.view.WorldRenderer;

public class GameScreen implements Screen {
    public World world;
    public WorldRenderer worldRenderer;
    public WorldController worldController;

    @Override
    public void show() {
        world = new World(50,100);
        worldRenderer = new WorldRenderer(world);
        worldRenderer.create();
        worldController = new WorldController(world);

        Cursor.worldPosition = new Vector2();
        Vars.camera = worldRenderer.camera;
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(1,1,1,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        worldRenderer.render(delta);
        worldController.update(delta);

        setCursor();
    }

    @Override
    public void resize(int width, int height) {
        worldRenderer.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void setCursor() {
        int x = Gdx.input.getX();
        int y = Gdx.graphics.getHeight() - Gdx.input.getY();

        int worldX = (int) (worldRenderer.camera.position.x + x);
        int worldY = (int) (worldRenderer.camera.position.y + y);

        Cursor.x = x;
        Cursor.y = y;
        Cursor.worldX = worldX;
        Cursor.worldY = worldY;
        Cursor.worldPosition.set(worldX, worldY);
    }
}
