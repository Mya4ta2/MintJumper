package mint.runner.type;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import mint.runner.Vars;
import mint.runner.ai.EnemyController;
import mint.runner.audio.SoundPlayer;
import mint.runner.content.Sounds;
import mint.runner.content.Weapons;

//TODO yes, it copy of Player, need remove code re-use
public class Enemy implements Entity {
    public float width = 1.5f;
    public float height = 1.5f;

    public EnemyController controller;
    public Vector2 position = new Vector2();
    public Vector2 oldPosition = new Vector2();
    public Vector2 velocity = new Vector2();
    public Rectangle bounds = new Rectangle();
    public Rectangle groundHitBox = new Rectangle();
    public Player.State state = Player.State.WalkRight;
    public Weapon weapon;
    public Vector2 weaponSlotPos = new Vector2();

    public int MaxHealth = 100;
    public int jumpHeight = 5;
    public int health;
    public boolean walkLeft = false;
    public boolean grounded;

    public float speed = 8;
    public float currentSpeed = speed;

    public void jump() {
        velocity.add(0, jumpHeight * Vars.tileSize * 10);
        SoundPlayer.play(Sounds.jump);
    }

    public Enemy() {
        this.position = Vector2.Zero;

        bounds.x = 0;
        bounds.y = 0;
        bounds.width = width;
        bounds.height = height;
        groundHitBox.width = width;
        groundHitBox.height = height;
        Vars.world.entitys.add(this);
    }

    public Enemy(Vector2 position) {
        this.position = position;

        bounds.x = position.x;
        bounds.y = position.y;
        bounds.width = width;
        bounds.height = height;
        groundHitBox.width = width;
        groundHitBox.height = height;
        Vars.world.entitys.add(this);
    }

    @Override
    public void update(float delta) {
        controller.update(delta);
        oldPosition.set(position);
        position.add(velocity.scl(delta));

        weaponSlotPos.set(position);
        weaponSlotPos.add(0.5f,0.6f); //temp

        bounds.x = position.x;
        bounds.y = position.y;
        groundHitBox.x = position.x;
        groundHitBox.y = position.y - 0.2f;
    }
}
