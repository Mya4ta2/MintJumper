package mint.runner.ui.fragment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import mint.runner.Vars;
import mint.runner.type.Events;
import mint.runner.ui.Image;
import mint.runner.ui.Separator;
import mint.runner.ui.TextButton;

public class MenuFragment extends Fragment {
    public Table table;

    @Override
    public void build(Group parent) {
        table = new Table();

        Texture buttonUp = new Texture("buttonUp.png");
        Texture buttonDown = new Texture("buttonDown.png");
        Texture logo = new Texture("logo.png");

        Image image = new Image(logo);

        TextButton startButton = new TextButton(buttonUp, buttonDown, new BitmapFont());
        startButton.setText("custom game");
        startButton.setSize(170,70);
        startButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Vars.menuScreen.setCustomGameStage();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        TextButton editor = new TextButton(buttonUp, buttonDown, new BitmapFont());
        editor.setText("editor");
        editor.setSize(170,70);
        editor.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Vars.menuScreen.openEditor();
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
        table.center().add(editor).row();
        table.center().add(new Separator(25)).row();
        table.center().add(exitButton).row();

        parent.addActor(table);
    }
}
