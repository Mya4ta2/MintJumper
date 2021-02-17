package mint.runner.type;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import mint.runner.Cursor;
import mint.runner.Vars;
import mint.runner.audio.SoundPlayer;
import mint.runner.content.Sounds;
import mint.runner.content.Weapons;

public class Player extends Body {
    public enum State {
        WalkLeft, WalkRight, LeftFail, RightFail
    }

    public float width = 0.9375f;
    public float height = 1.5f;

    public Rectangle groundHitBox = new Rectangle();
    public State state = State.WalkRight;
    public Weapon weapon;
    public Vector2 weaponSlotPos = new Vector2();

    public int MaxHealth = 100;
    public int jumpHeight = 3;
    public int health;
    public boolean walkLeft = false;
    public boolean grounded;
    public boolean lookRight;
    public boolean lookLeft;

    public float speed = 0.1f;
    public float sprintSpeed = 12;
    public float currentSpeed = speed;
    public boolean sprint;
    public float angle;

    public void jump() {
        velocity.add(0, jumpHeight / 16f);
        SoundPlayer.play(Sounds.jump);
    }

    public static double getAngle(double point1X, double point1Y,
                                  double point2X, double point2Y,
                                  double fixedX, double fixedY) {

        double angle1 = Math.atan2(point1Y - fixedY, point1X - fixedX);
        double angle2 = Math.atan2(point2Y - fixedY, point2X - fixedX);

        return angle1 - angle2;
    }

    public void setAngle() {
        angle = (float) Math.toDegrees(getAngle(
                0,
                0,
                Cursor.x - (Gdx.graphics.getWidth() / 2),
                Cursor.y - (Gdx.graphics.getHeight() / 2),
                Gdx.graphics.getWidth()/2,
                Gdx.graphics.getHeight()/2
        ));

        angle = -angle;

        if (angle < 0) angle += 360;
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
        super.update(delta);
        setAngle();
        if (weapon == null) weapon = new Weapon(Weapons.test);
        if (weapon != null) {
            weapon.player = this;
        }

        if (angle < 270 && angle > 90) {
            state = grounded ? State.WalkRight : State.RightFail;
            lookLeft = false;
            lookRight = true;
        } else {
            state = grounded ? State.WalkLeft : State.LeftFail;
            lookLeft = true;
            lookRight = false;
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
