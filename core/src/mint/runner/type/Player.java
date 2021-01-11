package mint.runner.type;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player {
    public float width = 1.5f;
    public float height = 1.5f;

    public Vector2 position = new Vector2();
    public Vector2 oldPosition = new Vector2();
    public Vector2 velocity = new Vector2();
    public Rectangle bounds = new Rectangle();

    public int MaxHealth = 100;
    public int health;
    public boolean walkLeft = false;

    public float speed = 8;
    public float sprintSpeed = 12;
    public float currentSpeed = speed;
    public boolean sprint;

    public Player() {
        this.position = Vector2.Zero;

        bounds.x = 0;
        bounds.y = 0;
        bounds.width = width;
        bounds.height = height;
    }

    public Player(Vector2 position) {
        this.position = position;

        bounds.x = position.x;
        bounds.y = position.y;
        bounds.width = width;
        bounds.height = height;
    }

    public void update(float delta) {
        oldPosition.set(position);
        position.add(velocity.scl(delta));

        bounds.x = position.x;
        bounds.y = position.y;

        if (sprint) currentSpeed = sprintSpeed;
        else currentSpeed = speed;
    }
}
