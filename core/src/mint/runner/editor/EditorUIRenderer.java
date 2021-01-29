package mint.runner.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import mint.runner.Vars;
import mint.runner.ctype.Renderer;
import mint.runner.maps.WorldWriter;
import mint.runner.type.ContentType;
import mint.runner.ui.*;

public class EditorUIRenderer implements Renderer {
    public OrthographicCamera camera;
    public Viewport viewport;
    public Stage stage;
    public SpriteBatch batch;

    public ContentSelectMenu menu;
    public ToolSelectMenu toolSelectMenu;

    public Stage currentStage;
    public Stage resumeStage;
    public Stage propertiesStage;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        viewport = new ScreenViewport(camera);
        stage = new Stage();
        resumeStage = new Stage();
        batch = new SpriteBatch();
        currentStage = stage;
        propertiesStage = new Stage();

        menu = new ContentSelectMenu(ContentType.block, new Texture("buttonUp.png"), new Texture("buttonDown.png"));
        toolSelectMenu = new ToolSelectMenu(
                new Texture("buttonUp.png"),
                new Texture("buttonDown.png"),
                new Texture("brush.png"),
                new Texture("erase.png")
        );

        stage.addActor(toolSelectMenu);
        stage.addActor(menu);
        stage.setViewport(viewport);
        resumeStage.setViewport(viewport);
        propertiesStage.setViewport(viewport);

        Group resumeGroup = new Group();
        Vars.ui.resumeFragment.build(resumeGroup);
        resumeStage.addActor(resumeGroup);

        (((Table)((Group) resumeStage.getActors().get(0)).getChild(0)).getChild(0)).addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                currentStage = propertiesStage;
                return super.touchDown(event, x, y, pointer, button);
            }
        }); //:<

        (((Table)((Group) resumeStage.getActors().get(0)).getChild(0)).getChild(2)).addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                WorldWriter.writeNewMap(EditorVars.world.name, EditorVars.world);
                return super.touchDown(event, x, y, pointer, button);
            }
        }); //:<

        Group propertiesGroup = new Group();
        Vars.ui.propertiesFragment.build(propertiesGroup);
        propertiesStage.addActor(propertiesGroup);

        TextField textField = (TextField) ((Table)((Group) propertiesStage.getActors().get(0)).getChild(0)).getChild(1);
        textField.onTextChangedEvents.add(() -> {
            EditorVars.world = WorldWriter.cutWorldToSize(EditorVars.world, Integer.parseInt(textField.getText().toString()), EditorVars.world.height);
        });
    }

    @Override
    public void render(float delta) {
        currentStage.act();
        currentStage.draw();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.end();

        camera.update();
        viewport.apply();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            if (currentStage == resumeStage) currentStage = stage;
            else currentStage = resumeStage;
        }

        propertiesStage.setKeyboardFocus(((Table)((Group) propertiesStage.getActors().get(0)).getChild(0)).getChild(1));

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

        //i experiments
        Vars.ui.resumeFragment.resize(width, height);
        ((Group) propertiesStage.getActors().get(0)).getChild(0).setSize(width,height);

        toolSelectMenu.setPosition(0, 0);
        toolSelectMenu.setSize(Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight());
    }
}
