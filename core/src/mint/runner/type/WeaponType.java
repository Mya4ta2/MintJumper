package mint.runner.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import mint.runner.Vars;

public class WeaponType extends Item {
    public Texture texture;
    public BulletType bulletType;
    public float width, height;

    public WeaponType(String name) {
        super(name);
    }
}
