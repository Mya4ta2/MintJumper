package mint.runner.screen;

import com.badlogic.gdx.Screen;
import mint.runner.type.World;
import mint.runner.view.WorldRenderer;

public class GameScreen implements Screen {
    public World world;
    public WorldRenderer worldRenderer;

    @Override
    public void show() {
        world = new World(10,10);
        worldRenderer = new WorldRenderer(world);
        worldRenderer.create();
    }

    @Override
    public void render(float delta) {
        worldRenderer.render(delta);
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
