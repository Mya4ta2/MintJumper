package mint.runner.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Block {
    public float width = 1, height = 1;

    public TextureRegion texture;
    public NeighborAir neighborAir;
    public final String name;

    public Block(String name) {
        this.name = name;
    }
}
