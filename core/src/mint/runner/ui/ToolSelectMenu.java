package mint.runner.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import mint.runner.Vars;
import mint.runner.editor.EditorToolsType;

public class ToolSelectMenu extends Actor {
    public Sprite black;

    public EditorToolsType currentTool;

    public Table buttonsTable;
    public ImageButton paintButton;
    public ImageButton erasingButton;

    public ToolSelectMenu(Texture unPressedButton, Texture pressedButton, Texture paintIcon, Texture erasingIcon) {
        black = new Sprite(new Texture("Black.png")); //:<

        this.paintButton = new ImageButton(unPressedButton, pressedButton, paintIcon);
        this.erasingButton = new ImageButton(unPressedButton, pressedButton, erasingIcon);

        paintButton.setSize(Vars.tileSize * 2,Vars.tileSize * 2);
        erasingButton.setSize(Vars.tileSize * 2, Vars.tileSize * 2);

        paintButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                currentTool = EditorToolsType.Brush;
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        erasingButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                currentTool = EditorToolsType.Erasing;
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        buttonsTable = new Table();
        buttonsTable.center().add(paintButton);
        buttonsTable.center().add(erasingButton);

        setDefaultSize();
    }

    public void setDefaultSize() {
        setSize(100,100);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        buttonsTable.setSize(getWidth(),getHeight());
        buttonsTable.setPosition(getX(),getY());

        if (paintButton.getStage() == null && getStage() != null) {
            getStage().addActor(buttonsTable);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        black.setAlpha(0.5f);
        black.setSize(getWidth(), getHeight());
        black.setPosition(getX(), getY());
        black.draw(batch);
    }
}
