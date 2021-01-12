package mint.runner.type;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import mint.runner.Vars;
import mint.runner.audio.SoundPlayer;
import mint.runner.content.Sounds;

public class Player {
    public float width = 2.5f;
    public float height = 2.5f;

    public Vector2 position = new Vector2();
    public Vector2 oldPosition = new Vector2();
    public Vector2 velocity = new Vector2();
    public Rectangle bounds = new Rectangle();
    public Rectangle groundHitBox = new Rectangle();

    public int MaxHealth = 100;
    public int jumpHeight = 5;
    public int health;
    public boolean walkLeft = false;
    public boolean grounded;

    public float speed = 8;
    public float sprintSpeed = 12;
    public float currentSpeed = speed;
    public boolean sprint;

    public void jump() {
        velocity.add(0, jumpHeight * Vars.tileSize * 10);
        SoundPlayer.play(Sounds.jump);
    }

    public Player() {
        this.position = Vector2.Zero;

        bounds.x = 0;
        bounds.y = 0;
        bounds.width = width;
        bounds.height = height;
        groundHitBox.width = width;
        groundHitBox.height = height;
    }

    public Player(Vector2 position) {
        this.position = position;

        bounds.x = position.x;
        bounds.y = position.y;
        bounds.width = width;
        bounds.height = height;
        groundHitBox.width = width;
        groundHitBox.height = height;
    }

    public void update(float delta) {
        oldPosition.set(position);
        position.add(velocity.scl(delta));

        bounds.x = position.x;
        bounds.y = position.y;
        groundHitBox.x = position.x;
        groundHitBox.y = position.y - groundHitBox.height;

        if (sprint) currentSpeed = sprintSpeed;
        else currentSpeed = speed;
    }
}
