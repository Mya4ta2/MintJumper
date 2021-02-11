package mint.runner.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
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
import mint.runner.maps.WorldReader;
import mint.runner.maps.WorldWriter;
import mint.runner.screen.GameScreen;
import mint.runner.type.ContentType;
import mint.runner.type.World;
import mint.runner.ui.*;

import java.util.ArrayList;

import static mint.runner.Vars.worldDir;

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
    public Stage worldSelectStage;

    Table worldSelectTable;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        viewport = new ScreenViewport(camera);
        stage = new Stage();
        resumeStage = new Stage();
        batch = new SpriteBatch();
        propertiesStage = new Stage();

        menu = new ContentSelectMenu(ContentType.overlay, new Texture("buttonUp.png"), new Texture("buttonDown.png"));
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

        //TODO i sorry =(
        worldSelectStage = new Stage();
        worldSelectTable = new Table();

        final ArrayList<World> worlds = new ArrayList<>();
        FileHandle worldDirHandle = Gdx.files.internal("world");

        for (int i = 0; i < Gdx.files.internal(worldDir).list().length; i++) {
            try {
                worlds.add(WorldReader.readFile(Gdx.files.internal(worldDir).list()[i]));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Texture buttonUp = new Texture("buttonUp.png");
        Texture buttonDown = new Texture("buttonDown.png");

        TextButton back = new TextButton(buttonUp, buttonDown, new BitmapFont());
        back.setText("back");
        back.setSize(170,70);
        back.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                currentStage = stage;
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        worldSelectTable.center().add(back).row();

        for (int i = 0; i < worlds.size(); i++) {
            TextButton worldTempButton = new TextButton(buttonUp, buttonDown,new BitmapFont());
            worldTempButton.setText(worlds.get(i).name);
            worldTempButton.setSize(170,70);
            worldSelectTable.center().add(worldTempButton).row();
            final int finalI = i;
            worldTempButton.addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    EditorVars.world = worlds.get(finalI);
                    currentStage = stage;
                    return super.touchDown(event, x, y, pointer, button);
                }
            });
        }

        worldSelectStage.addActor(worldSelectTable);
        currentStage = worldSelectStage;
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

        worldSelectTable.setSize(width,height);

        toolSelectMenu.setPosition(0, 0);
        toolSelectMenu.setSize(Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight());
    }
}
