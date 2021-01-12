package mint.runner.type;

import com.badlogic.gdx.graphics.Texture;

public class BulletType {
    public final String name;
    public Texture texture;

    public float damage;

    public BulletType(String name) {
        this.name = name;
    }
}
