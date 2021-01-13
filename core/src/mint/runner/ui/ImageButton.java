package mint.runner.ui;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class ImageButton extends Button {
    public Texture image;

    public ImageButton(Texture unPressedButton, Texture pressedButton, Texture image, Sound pressSound) {
        super(unPressedButton, pressedButton, pressSound);
        this.image = image;
    }

    public ImageButton(Texture unPressedButton, Texture pressedButton, Texture image) {
        super(unPressedButton, pressedButton);
        this.image = image;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.draw(
                image,
                getX() + 4,
                getY() + 4,
                getWidth() - 8,
                getHeight() - 8
        );
    }
}
