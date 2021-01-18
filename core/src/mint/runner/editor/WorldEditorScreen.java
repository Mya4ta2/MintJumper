package mint.runner.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import mint.runner.Cursor;
import mint.runner.Vars;
import mint.runner.type.World;

public class WorldEditorScreen implements Screen {
    public World world;
    public EditorRenderer worldRenderer;
    public EditorUIRenderer uiRenderer;
    public EditorController controller;

    @Override
    public void show() {
        world = new World(50,50,"test");
        worldRenderer = new EditorRenderer(world);
        uiRenderer = new EditorUIRenderer();
        uiRenderer.create();
        worldRenderer.create();
        controller = new EditorController(world);
    }

    @Override
    public void render(float delta) {
        Color bgColor = world.backgroundColor;
        Gdx.gl20.glClearColor(bgColor.r, bgColor.g, bgColor.b,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        worldRenderer.render(delta);
        uiRenderer.render(delta);
        controller.update(delta);

        Gdx.input.setInputProcessor(uiRenderer.currentStage);
        setCursor();
    }

    @Override
    public void resize(int width, int height) {
        worldRenderer.resize(width, height);
        uiRenderer.resize(width, height);
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
    }
}
