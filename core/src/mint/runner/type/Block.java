package mint.runner.type;

import com.badlogic.gdx.graphics.Texture;

public class Block {
    public float width, height;

    public Texture texture;
    public final String name;

    public Block(String name) {
        this.name = name;
    }
}
