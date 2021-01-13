package mint.runner.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import mint.runner.content.Blocks;
import mint.runner.ctype.Renderer;
import mint.runner.type.ContentType;
import mint.runner.ui.ContentSelectMenu;

public class EditorUIRenderer implements Renderer {
    public OrthographicCamera camera;
    public Viewport viewport;
    public Stage stage;

    public ContentSelectMenu menu;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        viewport = new ScreenViewport(camera);
        stage = new Stage();

        menu = new ContentSelectMenu(ContentType.block);
        menu.setPosition(0, 0);
        stage.addActor(menu);
        stage.setViewport(viewport);
    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();

        camera.update();
        viewport.apply();
    }

    @Override
    public void resize(int width, int height) {
        camera.position.set(width/2,height/2,0);
        viewport.update(width, height);

        menu.setSize(Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight());
    }
}
