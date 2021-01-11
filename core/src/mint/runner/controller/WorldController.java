package mint.runner.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import mint.runner.type.World;

public class WorldController {
    public World world;

    public WorldController(World world) {
        this.world = world;
    }

    public void update(float delta) {
        world.player.update(delta);

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

}
