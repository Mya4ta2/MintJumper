package mint.runner.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import mint.runner.type.World;

public class WorldEditorScreen implements Screen {
    public World world;
    public EditorRenderer worldRenderer;
    public EditorUIRenderer uiRenderer;
    public EditorController controller;

    public OrthographicCamera camera;
    public Viewport viewport;

    @Override
    public void show() {
        world = new World(50,50,"test");
        world.backgroundColor = Color.FIREBRICK;
        worldRenderer = new EditorRenderer(world);
        uiRenderer = new EditorUIRenderer();
        uiRenderer.create();
        worldRenderer.create();
        controller = new EditorController(world);

        camera = new OrthographicCamera();
        viewport = new ScreenViewport(camera);
    }

    @Override
    public void render(float delta) {
        Color bgColor = world.backgroundColor;
        Gdx.gl20.glClearColor(bgColor.r, bgColor.g, bgColor.b,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.position.set(world.player.position, 0);
        camera.update();

        worldRenderer.render(delta);
        uiRenderer.render(delta);
        controller.update(delta);
        viewport.apply();

        Gdx.input.setInputProcessor(uiRenderer.stage);
    }

    @Override
    public void resize(int width, int height) {
        worldRenderer.resize(width, height);
        uiRenderer.resize(width, height);
        viewport.update(width, height);
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
