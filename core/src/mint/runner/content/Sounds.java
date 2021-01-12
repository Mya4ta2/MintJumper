package mint.runner.content;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import mint.runner.ctype.ContentList;

public class Sounds implements ContentList {
    public static Sound jump;

    @Override
    public void load() {
        jump = Gdx.audio.newSound(Gdx.files.internal("jump.wav"));
    }
}
