package mint.runner.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import mint.runner.MainActivity;
import mint.runner.ui.Image;
import mint.runner.ui.Separator;
import mint.runner.ui.TextButton;

import static mint.runner.Vars.tileSize;

public class MenuScreen implements Screen {
    public MainActivity mainActivity;
    public Stage stage;
    public Table table;

    public OrthographicCamera camera;
    public Viewport viewport;

    public MenuScreen(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void show() {
        stage = new Stage();
        table = new Table();

        camera = new OrthographicCamera();
        viewport = new ScreenViewport(camera);
        stage.setViewport(viewport);

        Texture buttonUp = new Texture("buttonUp.png");
        Texture buttonDown = new Texture("buttonDown.png");
        Texture logo = new Texture("logo.png");

        Image image = new Image(logo);

        TextButton startButton = new TextButton(buttonUp, buttonDown, new BitmapFont());
        startButton.setText("start");
        startButton.setSize(170,70);
        startButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                mainActivity.setScreen(mainActivity.gameScreen);
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        TextButton exitButton = new TextButton(buttonUp, buttonDown, new BitmapFont());
        exitButton.setText("exit");
        exitButton.setSize(170,70);
        exitButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        table.center().add(image).row();
        table.center().add(new Separator(25)).row();
        table.center().add(startButton).row();
        table.center().add(new Separator(25)).row();
        table.center().add(exitButton).row();

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl20.glClearColor(1,1,1,1);

        stage.act();
        stage.draw();

        viewport.apply();

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(width/2,height/2,0);
        table.setSize(width,height);
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
