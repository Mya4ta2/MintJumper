package mint.runner.type;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import static mint.runner.Vars.tileSize;

public class Bullet implements Entity {
    public Vector2 position = new Vector2();
    public Vector2 velocity = new Vector2();
    public Entity owner;
    public World world;
    public BulletType type;
    public Sprite sprite;
    public float rotation;

    public Bullet(Vector2 position, Vector2 velocity, World world, BulletType type, Entity owner) {
        this.position = position;
        this.velocity = velocity;
        this.world = world;
        this.type = type;
        this.owner = owner;
        sprite = new Sprite(type.texture);

        world.entitys.add(this);
    }

    @Override
    public void update(float delta) {
        position.add(velocity);

        sprite.setRotation(rotation);
        sprite.setPosition(position.x * tileSize, position.y * tileSize);
        velocity.crs(delta, delta);
    }
}
