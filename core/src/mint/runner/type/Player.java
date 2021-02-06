package mint.runner.type;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import mint.runner.Vars;
import mint.runner.audio.SoundPlayer;
import mint.runner.content.Sounds;
import mint.runner.content.Weapons;

public class Player implements Entity {
    public enum State {
        WalkLeft, WalkRight, LeftFail, RightFail
    }

    public float width = 1.5f;
    public float height = 1.5f;

    public Vector2 position = new Vector2();
    public Vector2 oldPosition = new Vector2();
    public Vector2 velocity = new Vector2();
    public Rectangle bounds = new Rectangle();
    public Rectangle groundHitBox = new Rectangle();
    public State state = State.WalkRight;
    public Weapon weapon;
    public Vector2 weaponSlotPos = new Vector2();

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
        Vars.world.entitys.add(this);
    }

    public Player(Vector2 position) {
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
        oldPosition.set(position);
        position.add(velocity.scl(delta));
        if (weapon == null) weapon = new Weapon(Weapons.test);
        if (weapon != null) {
            weapon.player = this;
            if (weapon.angle < 270 && weapon.angle > 90) {
                state = grounded ? State.WalkRight : State.RightFail;
            } else {
                state = grounded ? State.WalkLeft : State.LeftFail;
            }
        }

        if (state == State.WalkLeft || state == State.LeftFail) {
            weaponSlotPos.set(position);
            weaponSlotPos.add(0.5f,0.6f);
        } else if (state == State.WalkRight || state == State.RightFail){
            weaponSlotPos.set(position);
            weaponSlotPos.add(0.8f,0.6f);
        }

        bounds.x = position.x;
        bounds.y = position.y;
        groundHitBox.x = position.x;
        groundHitBox.y = position.y - 0.2f;

        if (sprint) currentSpeed = sprintSpeed;
        else currentSpeed = speed;
    }
}
