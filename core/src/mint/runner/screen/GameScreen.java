package mint.runner.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import mint.runner.Cursor;
import mint.runner.Vars;
import mint.runner.controller.WorldController;
import mint.runner.maps.WorldReader;
import mint.runner.maps.WorldWriter;
import mint.runner.type.World;
import mint.runner.view.WorldRenderer;

public class GameScreen implements Screen {
    public World world;
    public WorldRenderer worldRenderer;
    public WorldController worldController;

    @Override
    public void show() {
        worldRenderer = new WorldRenderer(world);
        worldRenderer.create();
        worldController = new WorldController(world);

        Cursor.worldPosition = new Vector2();
        Vars.camera = worldRenderer.camera;
    }

    @Override
    public void render(float delta) {
        Color bgColor = world.backgroundColor;
        Gdx.gl20.glClearColor(bgColor.r, bgColor.g, bgColor.b,1);
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
        Cursor.x = (int)(Gdx.input.getX() + (Gdx.graphics.getWidth() / 2));
        Cursor.y = (int)(worldRenderer.camera.viewportHeight - Gdx.input.getY() + (Gdx.graphics.getHeight() / 2));

        Cursor.worldX = (int) (worldRenderer.camera.position.x + Cursor.x - Gdx.graphics.getWidth());
        Cursor.worldY = (int) (worldRenderer.camera.position.y + Cursor.y - Gdx.graphics.getHeight());

        Cursor.worldPosition.set(Cursor.worldX, Cursor.worldY);
    }
}
