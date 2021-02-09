package mint.runner.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import mint.runner.Cursor;
import mint.runner.Vars;
import mint.runner.content.Blocks;
import mint.runner.type.*;

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

            if (EditorVars.currentContentSelected != null && EditorVars.tool == EditorToolsType.Brush)
                if (
                        x >= 0 && x <= world.width &&
                        y >= 0 && y <= world.height
                ) {
                    if (EditorVars.currentContentSelected instanceof Block)
                        world.tiles.get(x, y).block = (Block) EditorVars.currentContentSelected;
                    else if (EditorVars.currentContentSelected instanceof Overlay)
                        world.tiles.get(x, y).overlay = (Overlay) EditorVars.currentContentSelected;
                }

            if (EditorVars.tool == EditorToolsType.Erasing) {
                if (
                        x >= 0 && x <= world.width &&
                        y >= 0 && y <= world.height
                ) {
                    world.tiles.get(x, y).block = Blocks.air;
                }
            }
        }

        world.player.sprint = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT);
    }
}

