package mint.runner.type;

import com.badlogic.gdx.graphics.Texture;
import mint.runner.ctype.MappableContent;

public class Wall implements MappableContent {
    public final String name;
    public Texture texture;

    public float width = 1;
    public float height = 1;

    public Wall(String name) {
        this.name = name;
    }
}
