package mint.runner.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import mint.runner.Vars;
import mint.runner.content.Blocks;
import mint.runner.ctype.ContentList;
import mint.runner.ctype.MappableContent;
import mint.runner.type.Block;
import mint.runner.type.ContentType;

import java.lang.reflect.Field;

public class ContentSelectMenu extends Actor {
    public static final float separator = 3;
    public ContentType contentType;
    public Texture unPressedButton;
    public Texture pressedButton;

    public ImageButton[] buttons;

    public ContentSelectMenu(ContentType contentType, Texture unPressedButton, Texture pressedButton) {
        this.contentType = contentType;
        this.unPressedButton = unPressedButton;
        this.pressedButton = pressedButton;

        setDefaultPos();
        setDefaultSize();
        fill();
        setPositions();
    }

    public void fill() {
        if (contentType == ContentType.block) {
            buttons = new ImageButton[Blocks.blocks.size];
            for (int i = 0; i < buttons.length; i++) {
                buttons[i] = new ImageButton(
                        unPressedButton,
                        pressedButton,
                        Blocks.blocks.get(i).neighborAir.up.getTexture());
                buttons[i].setSize(Vars.tileSize * 2,Vars.tileSize * 2);
            }
        }
    }

    public void setPositions() {
        int x = 0;
        int y = 0;

        for (int i = 0; i < buttons.length; i++) {
            x++;

            if ((Vars.tileSize * x * separator) > getWidth()) {
                y++;
                x = 0;
            }

            buttons[i].setPosition(Vars.tileSize * x * separator ,Vars.tileSize * y * separator);
        }
    }

    public void setDefaultPos() {
        setPosition(100,100);
    }

    public void setDefaultSize() {
        setSize(100,100);
    }
}
