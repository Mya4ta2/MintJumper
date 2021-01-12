package mint.runner.type;

import com.badlogic.gdx.math.Vector2;

public class Bullet implements Entity{
    public Vector2 position = new Vector2();
    public Vector2 velocity = new Vector2();
    public Entity owner;
    public World world;
    public BulletType type;

    public Bullet(Vector2 position, Vector2 velocity, World world) {
        this.position = position;
        this.velocity = velocity;
        this.world = world;

        world.entitys.add(this);
    }

    @Override
    public void update(float delta) {
        position.add(velocity.scl(delta));
    }
}