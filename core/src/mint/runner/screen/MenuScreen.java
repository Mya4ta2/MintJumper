package mint.runner.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import mint.runner.MainActivity;
import mint.runner.Vars;
import mint.runner.editor.WorldEditorScreen;
import mint.runner.maps.WorldReader;
import mint.runner.type.World;
import mint.runner.ui.Image;
import mint.runner.ui.MenuFragment;
import mint.runner.ui.Separator;
import mint.runner.ui.TextButton;

import java.util.ArrayList;
import java.util.Arrays;

import static mint.runner.Vars.tileSize;
import static mint.runner.Vars.worldDir;

public class MenuScreen implements Screen {
    public MainActivity mainActivity;

    public OrthographicCamera camera;
    public Viewport viewport;
    public Stage currentStage;
    public Stage stage;
    public Stage customGameStage;
    public Table customGameTable;
    public Table table;

    public MenuScreen(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void show() {
        stage = new Stage();
        table = new Table();
        customGameStage = new Stage();
        customGameTable = new Table();
        currentStage = stage; //oh no

        camera = new OrthographicCamera();
        viewport = new ScreenViewport(camera);
        stage.setViewport(viewport);
        customGameStage.setViewport(viewport);

        Texture buttonUp = new Texture("buttonUp.png");
        Texture buttonDown = new Texture("buttonDown.png");

        Group menuGroup = new Group();
        Vars.ui.menuFragment.build(menuGroup);

        table.add(menuGroup);
        stage.addActor(table);

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

        TextButton newMap = new TextButton(buttonUp, buttonDown, new BitmapFont());
        newMap.setText("new map");
        newMap.setSize(170,70);
        newMap.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                mainActivity.setScreen(new WorldEditorScreen());
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        final ArrayList<World> worlds = new ArrayList<>();
        FileHandle worldDirHandle = Gdx.files.internal("world");

        for (int i = 0; i < Gdx.files.internal(worldDir).list().length; i++) {
            try {
                worlds.add(WorldReader.readFile(Gdx.files.internal(worldDir).list()[i]));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        customGameTable.center().add(back);

        for (int i = 0; i < worlds.size(); i++) {
            TextButton worldTempButton = new TextButton(buttonUp,buttonDown,new BitmapFont());
            worldTempButton.setText(worlds.get(i).name);
            worldTempButton.setSize(170,70);
            customGameTable.center().add(worldTempButton).row();
            final int finalI = i;
            worldTempButton.addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    GameScreen gameScreen = new GameScreen();
                    System.out.println(worlds.get(finalI).name);
                    gameScreen.world = worlds.get(finalI);
                    mainActivity.setScreen(gameScreen);
                    return super.touchDown(event, x, y, pointer, button);
                }
            });
        }

        customGameStage.addActor(customGameTable);
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl20.glClearColor(1,1,1,1);

        currentStage.act();
        currentStage.draw();

        viewport.apply();

        Gdx.input.setInputProcessor(currentStage);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(width/2,height/2,0);
        table.setSize(width,height);
        customGameTable.setSize(width,height);
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

    public void setCustomGameStage() {
        currentStage = customGameStage;
    }

    public void openEditor() {
        mainActivity.setScreen(new WorldEditorScreen());
    }
}
