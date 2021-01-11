package mint.runner.type;

import com.badlogic.gdx.graphics.Texture;

public class Block {
    public float width = 1, height = 1;

    public Texture texture;
    public final String name;

    public Block(String name) {
        this.name = name;
    }
}
