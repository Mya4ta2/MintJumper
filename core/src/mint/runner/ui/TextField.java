package mint.runner.ui;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class TextField extends Actor {
    private final StringBuilder text = new StringBuilder();
    public BitmapFont font;

    public TextField(BitmapFont font) {
        this.font = font;

        addListener(new InputListener(){
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.BACKSPACE) removeLast();
                return super.keyDown(event, keycode);
            }

            @Override
            public boolean keyTyped(InputEvent event, char character) {
                addSymbol(character);
                return super.keyTyped(event, character);
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        font.draw(batch, text.toString(), getX(), getY());
    }

    public void removeLast() {
        if (text.length() > 0) {
            text.delete(text.length()-2, text.length());
        }
    }

    public void addSymbol(char symbol) {
        text.append(symbol);
    }
}
