package mint.runner.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import mint.runner.content.Blocks;
import mint.runner.type.Bullet;
import mint.runner.type.Player;
import mint.runner.type.Tile;
import mint.runner.type.World;

public class WorldController {
    public World world;

    public WorldController(World world) {
        this.world = world;
    }

    public void update(float delta) {
        world.entitys.updateAll(delta);
        world.setTiles();

        processInput();
        checkPlayerGrounded();
        processGravity();
        processCollisions();
    }

    public void processInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            world.player.velocity.x = world.player.currentSpeed;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            world.player.velocity.x = -world.player.currentSpeed;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && world.player.grounded) {
            world.player.jump();
        }

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            if (world.player.weapon != null) world.player.weapon.shoot();
        }

        world.player.sprint = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT);
    }

    public void checkPlayerGrounded() {
        int count = 0;

        Vector2 pos = world.player.position;

        if (pos.x - 1 <= -1 || pos.y - 1 <= -1 ||
            pos.x + 1 > world.width || pos.y + 1 > world.width) {
            return;
        }

        Tile[] nearTiles = {
                world.tiles.get((int) pos.x, (int) pos.y - 1),
                world.tiles.get((int) pos.x - 1, (int) pos.y - 1),
                world.tiles.get((int) pos.x + 1, (int) pos.y - 1)
        };

        for (int i = 0; i < nearTiles.length; i++) {
            if (world.player.groundHitBox.overlaps(nearTiles[i].bounds) && nearTiles[i].block != Blocks.air) {
                count += 1;
            }
        }

        world.player.grounded = count > 0;

        System.out.println(world.player.grounded);
    }

    public void processCollisions() {
        boolean collision = false;

        Vector2 pos = world.player.position;

        if (pos.x - 1 < 0 || pos.y - 1 < 0 ||
                pos.x + 1 > world.width || pos.y + 1 > world.width) {
            return;
        }

        Tile left1Tile = world.tiles.get((int) pos.x, (int) pos.y);
        Tile left2Tile = world.tiles.get((int) pos.x, (int) pos.y + 1);
        Tile left3Tile = world.tiles.get((int) pos.x, (int) pos.y + 2);
        Tile right1Tile = world.tiles.get((int) pos.x + 1, (int) pos.y);
        Tile right2Tile = world.tiles.get((int) pos.x + 1, (int) pos.y + 1);
        Tile right3Tile = world.tiles.get((int) pos.x + 1, (int) pos.y + 2);

        collision =
                (
                        left1Tile.block != Blocks.air &&
                                world.player.bounds.overlaps(left1Tile.bounds) ||
                                left2Tile.block != Blocks.air &&
                                        world.player.bounds.overlaps(left2Tile.bounds) ||
                                left3Tile.block != Blocks.air &&
                                        world.player.bounds.overlaps(left3Tile.bounds) ||
                                right1Tile.block != Blocks.air &&
                                        world.player.bounds.overlaps(right1Tile.bounds) ||
                                right2Tile.block != Blocks.air &&
                                        world.player.bounds.overlaps(right2Tile.bounds) ||
                                right3Tile.block != Blocks.air &&
                                        world.player.bounds.overlaps(right3Tile.bounds)
                );

        if (collision) {
            world.player.position.set(world.player.oldPosition);
            world.player.velocity.setZero();
        }
    }

    public void processGravity() {
        if (!world.player.grounded) {
            world.player.velocity.y -= 0.01f;
        }

        if (world.player.velocity.y < 0 && world.player.velocity.x > 0) world.player.state = Player.State.RightFail;
        if (world.player.velocity.y < 0 && world.player.velocity.x < 0) world.player.state = Player.State.LeftFail;
    }
}
