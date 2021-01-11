package mint.runner.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import mint.runner.content.Blocks;
import mint.runner.type.Tile;
import mint.runner.type.World;

public class WorldController {
    public World world;

    public WorldController(World world) {
        this.world = world;
    }

    public void update(float delta) {
        world.player.update(delta);

        checkPlayerGrounded();
        processInput();
    }

    public void processInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            world.player.velocity.y += world.player.currentSpeed;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            world.player.velocity.y -= world.player.currentSpeed;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            world.player.velocity.x += world.player.currentSpeed;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            world.player.velocity.x -= world.player.currentSpeed;
        }

        world.player.sprint = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT);
    }

    public void checkPlayerGrounded() {
        int count = 0;

        Vector2 pos = world.player.position;

        Tile[] nearTiles = {
                world.tiles.get((int) pos.y, (int) pos.x),
                pos.y > world.height ? world.tiles.get((int) pos.y, (int) pos.x - 1) : world.tiles.get((int) pos.y, (int) pos.x),
                pos.y > world.height ? world.tiles.get((int) pos.y, (int) pos.x + 1) : world.tiles.get((int) pos.y, (int) pos.x)
        };

        for (int i = 0; i < nearTiles.length; i++) {
            if (world.player.groundHitBox.overlaps(nearTiles[i].bounds) && nearTiles[i].block != Blocks.air) {
                count += 1;
            }
        }

        world.player.grounded = count > 0;
        System.out.println(count > 0);
    }
}
