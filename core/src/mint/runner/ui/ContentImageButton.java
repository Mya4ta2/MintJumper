package mint.runner.ui;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import mint.runner.ctype.MappableContent;

public class ContentImageButton extends ImageButton{
    public MappableContent content;

    public ContentImageButton(Texture unPressedButton, Texture pressedButton, Texture image, Sound pressSound) {
        super(unPressedButton, pressedButton, image, pressSound);
    }

    public ContentImageButton(Texture unPressedButton, Texture pressedButton, Texture image) {
        super(unPressedButton, pressedButton, image);
    }
}
