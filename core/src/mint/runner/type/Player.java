package mint.runner.type;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player {
    private float WIDTH = 1.5f;
    private float HEIGHT = 1.5f;

    private Vector2 position = new Vector2();
    private Vector2 oldPosition = new Vector2();
    private Vector2 velocity = new Vector2();
    private Rectangle bounds = new Rectangle();

    private int MaxHealth = 100;
    private int health;
    private boolean walkLeft = false;

    private float speed = 8;
    private float sprintSpeed = 12;
    private float currentSpeed = speed;
    private boolean sprint;

    public Player() {
        this.position = Vector2.Zero;

        bounds.x = 0;
        bounds.y = 0;
        bounds.width = WIDTH;
        bounds.height = HEIGHT;
    }

    public Player(Vector2 position) {
        this.position = position;

        bounds.x = position.x;
        bounds.y = position.y;
        bounds.width = WIDTH;
        bounds.height = HEIGHT;
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
