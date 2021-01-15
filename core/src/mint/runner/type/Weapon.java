package mint.runner.type;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import jdk.nashorn.internal.objects.NativeArguments;
import mint.runner.Cursor;
import mint.runner.Vars;

public class Weapon implements Entity {
    public WeaponType type;
    public Player player;
    public Sprite sprite;
    public Vector2 pixelPosition = new Vector2();

    public Weapon(WeaponType weapon) {
        this.type = weapon;
        Vars.world.entitys.add(this);
    }

    public void shoot() {
        new Bullet(new Vector2(player.position), new Vector2(1,0), Vars.world, type.bulletType, player);
    }

    @Override
    public void update(float delta) {
        if (sprite == null && type.texture != null) {
            sprite = new Sprite(type.texture);
        }

        if (sprite != null && player != null) {
            sprite.setCenter(player.weaponSlotPos.x * Vars.tileSize, player.weaponSlotPos.y * Vars.tileSize);
            sprite.setSize(type.width * Vars.tileSize, type.height * Vars.tileSize);
        }
    }
}
