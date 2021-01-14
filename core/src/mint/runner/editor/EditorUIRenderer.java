package mint.runner.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import mint.runner.Cursor;
import mint.runner.Vars;
import mint.runner.content.Blocks;
import mint.runner.ctype.Renderer;
import mint.runner.type.Block;
import mint.runner.type.ContentType;
import mint.runner.ui.Button;
import mint.runner.ui.ContentSelectMenu;
import mint.runner.ui.ImageButton;
import mint.runner.ui.ToolSelectMenu;

public class EditorUIRenderer implements Renderer {
    public OrthographicCamera camera;
    public Viewport viewport;
    public Stage stage;
    public SpriteBatch batch;

    public ContentSelectMenu menu;
    public ToolSelectMenu toolSelectMenu;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        viewport = new ScreenViewport(camera);
        stage = new Stage();
        batch = new SpriteBatch();

        menu = new ContentSelectMenu(ContentType.block, new Texture("buttonUp.png"), new Texture("buttonDown.png"));
        toolSelectMenu = new ToolSelectMenu(
                new Texture("buttonUp.png"),
                new Texture("buttonDown.png"),
                new Texture("buttonUp.png"),
                new Texture("buttonDown.png")
        );

        stage.addActor(toolSelectMenu);
        stage.addActor(menu);
        stage.setViewport(viewport);
    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.end();

        camera.update();
        viewport.apply();

        EditorVars.currentContentSelected = menu.currentContent;
        EditorVars.tool = toolSelectMenu.currentTool;
    }

    @Override
    public void resize(int width, int height) {
        camera.position.set(width/2,height/2,0);
        viewport.update(width, height);

        menu.setSize(Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight());
        menu.setPosition(width-menu.getWidth(),0);
        menu.setPositions();

        toolSelectMenu.setPosition(0, 0);
        toolSelectMenu.setSize(Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight());
    }
}
