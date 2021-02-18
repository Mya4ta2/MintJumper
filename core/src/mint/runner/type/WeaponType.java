package mint.runner.type;

import com.badlogic.gdx.graphics.Texture;

public class WeaponType extends Item {
    public Texture texture;
    public BulletType bulletType;
    public float width, height;

    public WeaponType(String name) {
        super(name);
    }
}
