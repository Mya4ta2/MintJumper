package mint.runner.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import mint.runner.Vars;
import mint.runner.content.Blocks;
import mint.runner.ctype.MappableContent;
import mint.runner.type.ContentType;

public class ContentSelectMenu extends Actor {
    public static final float separator = 2.5f;
    public ContentType contentType;
    public Texture unPressedButton;
    public Texture pressedButton;
    public Sprite black;
    public MappableContent currentContent;

    public ContentImageButton[] buttons;

    public ContentSelectMenu(ContentType contentType, Texture unPressedButton, Texture pressedButton) {
        this.contentType = contentType;
        this.unPressedButton = unPressedButton;
        this.pressedButton = pressedButton;

        black = new Sprite(new Texture("Black.png")); //:<

        setDefaultPos();
        setDefaultSize();
        fill();
        setPositions();
    }

    public void fill() {
        if (contentType == ContentType.block) {
            buttons = new ContentImageButton[Blocks.blocks.size];
            for (int i = 0; i < buttons.length; i++) {
                buttons[i] = new ContentImageButton(
                        unPressedButton,
                        pressedButton,
                        Blocks.blocks.get(i).rounding.up.getTexture());
                buttons[i].content = Blocks.blocks.get(i);
                buttons[i].setSize(Vars.tileSize * 2,Vars.tileSize * 2);
            }

            //oh no code
            for (int i = 0; i < buttons.length; i++) {
                final int finalI = i;
                buttons[i].addListener(new InputListener(){
                    ContentImageButton button1 = buttons[finalI];

                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        currentContent = button1.content;
                        return super.touchDown(event, x, y, pointer, button);
                    }
                });
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

            buttons[i].setPosition((Vars.tileSize * x * separator) + getX(),(Vars.tileSize * y * separator) + getY());
        }
    }

    public void setDefaultPos() {
        setPosition(100,100);
    }

    public void setDefaultSize() {
        setSize(100,100);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        black.setAlpha(0.5f);
        black.setSize(getWidth(),getHeight());
        black.setPosition(getX(),getY());
        black.draw(batch);
    }

    @Override
    public void act(float delta) {
        setPositions();

        if (buttons[0].getStage() == null && getStage() != null) {
            for (int i = 0; i < buttons.length; i++) {
                getStage().addActor(buttons[i]);
            }
        }

        super.act(delta);
    }
}
