package mint.runner.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
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
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(1,1,1,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        worldRenderer.render(delta);
        worldController.update(delta);
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
}
