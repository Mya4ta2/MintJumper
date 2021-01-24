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
    public Vector2 playerVector = new Vector2();
    public float angle;

    public Weapon(WeaponType weapon) {
        this.type = weapon;
        Vars.world.entitys.add(this);
    }

    public void shoot() {
        float x = (float) Math.sin(angle * Math.PI / 180);
        float y = (float) Math.cos(angle * Math.PI / 180);

        new Bullet(new Vector2(player.position), new Vector2(x,y), Vars.world, type.bulletType, player);
    }

    @Override
    public void update(float delta) {
        angle = (float) getAngle(
                player.position.x * Vars.tileSize,
                player.position.y * Vars.tileSize,
                Cursor.worldX,
                Cursor.worldY,
                Gdx.graphics.getWidth()/2,
                Gdx.graphics.getHeight()/2
        ) * MathUtils.radiansToDegrees;

        if (sprite == null && type.texture != null) {
            sprite = new Sprite(type.texture);
        }

        if (sprite != null && player != null) {
            sprite.setCenter(player.weaponSlotPos.x * Vars.tileSize, player.weaponSlotPos.y * Vars.tileSize);
            sprite.setSize(type.width * Vars.tileSize, type.height * Vars.tileSize);
            sprite.setRotation(angle);
        }
    }

    public static double getAngle(double point1X, double point1Y,
                                  double point2X, double point2Y,
                                  double fixedX, double fixedY) {

        double angle1 = Math.atan2(point1Y - fixedY, point1X - fixedX);
        double angle2 = Math.atan2(point2Y - fixedY, point2X - fixedX);

        return angle1 - angle2;
    }
}
