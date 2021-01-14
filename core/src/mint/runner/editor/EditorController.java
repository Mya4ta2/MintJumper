package mint.runner.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import mint.runner.Cursor;
import mint.runner.Vars;
import mint.runner.content.Blocks;
import mint.runner.type.Block;
import mint.runner.type.Player;
import mint.runner.type.Tile;
import mint.runner.type.World;

import static mint.runner.Vars.tileSize;

public class EditorController {
    public World world;

    public EditorController(World world) {
        this.world = world;
    }

    public void update(float delta) {
        world.entitys.updateAll(delta);
        world.setTiles();

        processInput();
    }

    public void processInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            world.player.velocity.x += world.player.currentSpeed;
            world.player.state = Player.State.WalkRight;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            world.player.velocity.x -= world.player.currentSpeed;
            world.player.state = Player.State.WalkLeft;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            world.player.velocity.y += world.player.currentSpeed;
            world.player.state = Player.State.WalkRight;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            world.player.velocity.y -= world.player.currentSpeed;
            world.player.state = Player.State.WalkLeft;
        }

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            int x = (int) (Cursor.worldX / tileSize);
            int y = (int) (Cursor.worldY / tileSize);
            System.out.println(x + " " + y);
            if (EditorVars.currentContentSelected != null)
                if (
                        x > 0 && x < world.width &&
                        y > 0 && y < world.height
                ) {
                    world.tiles.get(x, y).block = (Block) EditorVars.currentContentSelected;
                }
        }

        world.player.sprint = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT);
    }
}

