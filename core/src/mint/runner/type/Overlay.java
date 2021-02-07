package mint.runner.type;

import com.badlogic.gdx.graphics.Texture;
import mint.runner.ctype.MappableContent;

public class Overlay implements MappableContent {
    public final String name;
    public Texture texture;

    public Overlay(String name) {
        this.name = name;
    }
}
