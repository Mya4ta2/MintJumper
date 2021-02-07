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
    public Vector2 bulletOutput = new Vector2();

    public Weapon(WeaponType weapon) {
        this.type = weapon;
        Vars.world.entitys.add(this);
    }

    public void shoot() {
        float x = (float) Math.cos(player.angle * Math.PI / 180);
        float y = (float) Math.sin(player.angle * Math.PI / 180);

        x = -x;
        y = -y;

        Bullet bullet = new Bullet(new Vector2(bulletOutput), new Vector2(x,y), Vars.world, type.bulletType, player);
        bullet.rotation = player.angle + 90;
    }

    @Override
    public void update(float delta) {
        if (sprite == null && type.texture != null) {
            sprite = new Sprite(type.texture);
        }

        if (sprite != null && player != null) {
            sprite.setCenter(player.weaponSlotPos.x * Vars.tileSize, player.weaponSlotPos.y * Vars.tileSize);
            sprite.setSize(type.width * Vars.tileSize, type.height * Vars.tileSize);
            sprite.setRotation(player.angle);

            sprite.setFlip(false, player.angle < 270 && player.angle > 90);

            bulletOutput.set(
                player.weaponSlotPos.x,
                player.weaponSlotPos.y
            );
        }
    }
}
