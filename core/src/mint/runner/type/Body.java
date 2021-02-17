package mint.runner.type;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Body implements Entity {
    public Vector2 position = new Vector2();
    public Vector2 oldPosition = new Vector2();
    public Vector2 velocity = new Vector2();
    public Rectangle bounds = new Rectangle();

    @Override
    public void update(float delta) {
        oldPosition.set(position);
        position.add(velocity);
        velocity.crs(delta,delta);
    }
}
