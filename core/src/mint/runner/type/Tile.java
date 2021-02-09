package mint.runner.type;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import mint.runner.Vars;
import mint.runner.content.Blocks;
import mint.runner.content.Overlays;
import mint.runner.content.Walls;

public class Tile {
    public int x, y;
    public Block block = Blocks.air;
    public Wall wall = Walls.air;
    public Overlay overlay;
    public NeighborAirState neighborAirState = new NeighborAirState(block.neighborAir);

    public Rectangle bounds = new Rectangle();

    public Tile(int x, int y, Block block) {
        this.x = x;
        this.y = y;
        this.block = block;

        bounds.width = 1;
        bounds.height = 1;
        bounds.x = x;
        bounds.y = y;
    }

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;

        bounds.width = 1;
        bounds.height = 1;
        bounds.x = x;
        bounds.y = y;
    }


    //TODO move to neighbourAir, and think how to make better this hell
    public void setNeighborAir(World world) {
        Tile
        upLeft = null, up = null, upRight = null,
        left = null, right = null,
        downLeft = null, down = null, downRight = null;

        Tile emptyTile = new Tile(0,0);

        boolean leftWorldEnd = false, rightWorldEnd = false, upWorldEnd = false, downWorldEnd = false;

        TextureRegion tmp = neighborAirState.currentBlockTexture;
        neighborAirState = new NeighborAirState(block.neighborAir);
        neighborAirState.currentBlockTexture = tmp;

        //this the best code in my life...
        if (block != Blocks.air) {
            if (x - 1 < 0) leftWorldEnd = true;
            if (x + 1 > world.width-1) rightWorldEnd = true;
            if (y - 1 < 0) downWorldEnd = true;
            if (y + 1 > world.height-1) upWorldEnd = true;

            if (!leftWorldEnd && !upWorldEnd) upLeft = world.tiles.get(x - 1,y + 1);
            else upLeft = emptyTile;
            if (!upWorldEnd) up = world.tiles.get(x,y + 1);
            else up = emptyTile;
            if (!rightWorldEnd && !upWorldEnd) upRight = world.tiles.get(x + 1,y + 1);
            else upRight = emptyTile;
            if (!leftWorldEnd) left = world.tiles.get(x - 1, y);
            else left = emptyTile;
            if (!rightWorldEnd) right = world.tiles.get(x + 1, y);
            else right = emptyTile;
            if (!downWorldEnd && !leftWorldEnd) downLeft = world.tiles.get(x - 1, y - 1);
            else downLeft = emptyTile;
            if (!downWorldEnd) down = world.tiles.get(x, y - 1);
            else down = emptyTile;
            if (!rightWorldEnd && !downWorldEnd) downRight = world.tiles.get(x + 1, y - 1);
            else downRight = emptyTile;

            if (left.block == Blocks.air) neighborAirState.currentBlockTexture = neighborAirState.neighborAir.left;
            if (up.block == Blocks.air) neighborAirState.currentBlockTexture = neighborAirState.neighborAir.up;
            if (right.block == Blocks.air) neighborAirState.currentBlockTexture = neighborAirState.neighborAir.right;
            if (down.block == Blocks.air) neighborAirState.currentBlockTexture = neighborAirState.neighborAir.down;

            if (right.block == Blocks.air && up.block == Blocks.air) neighborAirState.currentBlockTexture = neighborAirState.neighborAir.upRight;
            if (left.block == Blocks.air && up.block == Blocks.air) neighborAirState.currentBlockTexture = neighborAirState.neighborAir.upLeft;

            if (right.block == Blocks.air && down.block == Blocks.air) neighborAirState.currentBlockTexture = neighborAirState.neighborAir.downRight;
            if (left.block == Blocks.air && down.block == Blocks.air) neighborAirState.currentBlockTexture = neighborAirState.neighborAir.downLeft;

            if (
                   up.block != Blocks.air &&
                   down.block != Blocks.air &&
                   right.block != Blocks.air &&
                   left.block != Blocks.air
            ) {
                neighborAirState.currentBlockTexture = neighborAirState.neighborAir.middle;
            }

            if (    neighborAirState.neighborAir.upAndLeft != null &&
                    neighborAirState.neighborAir.upAndRight != null &&
                    (up.neighborAirState.currentBlockTexture == up.neighborAirState.neighborAir.left) &&
                    (left.neighborAirState.currentBlockTexture == left.neighborAirState.neighborAir.up)) {
                neighborAirState.currentBlockTexture = neighborAirState.neighborAir.upAndLeft;
            }

            if (    neighborAirState.neighborAir.upAndLeft != null &&
                    neighborAirState.neighborAir.upAndRight != null &&
                    (up.neighborAirState.currentBlockTexture == up.neighborAirState.neighborAir.right) &&
                    (right.neighborAirState.currentBlockTexture == left.neighborAirState.neighborAir.up)) {
                neighborAirState.currentBlockTexture = neighborAirState.neighborAir.upAndRight;
            }

            if (    neighborAirState.neighborAir.downAndLeft != null &&
                    neighborAirState.neighborAir.downAndRight != null &&
                    (down.neighborAirState.currentBlockTexture == up.neighborAirState.neighborAir.left) &&
                    (left.neighborAirState.currentBlockTexture == left.neighborAirState.neighborAir.down)) {
                neighborAirState.currentBlockTexture = neighborAirState.neighborAir.downAndLeft;
            }

            if (    neighborAirState.neighborAir.downAndLeft != null &&
                    neighborAirState.neighborAir.downAndRight != null &&
                    (down.neighborAirState.currentBlockTexture == up.neighborAirState.neighborAir.right) &&
                    (right.neighborAirState.currentBlockTexture == left.neighborAirState.neighborAir.down)) {
                neighborAirState.currentBlockTexture = neighborAirState.neighborAir.downAndRight;
            }

            //

            if (    neighborAirState.neighborAir.upAndLeft != null &&
                    neighborAirState.neighborAir.upAndRight != null &&
                    (up.neighborAirState.currentBlockTexture == up.neighborAirState.neighborAir.upLeft) &&
                    (left.neighborAirState.currentBlockTexture == left.neighborAirState.neighborAir.up)) {
                neighborAirState.currentBlockTexture = neighborAirState.neighborAir.upAndLeft;
            }

            if (    neighborAirState.neighborAir.upAndLeft != null &&
                    neighborAirState.neighborAir.upAndRight != null &&
                    (up.neighborAirState.currentBlockTexture == up.neighborAirState.neighborAir.upRight) &&
                    (right.neighborAirState.currentBlockTexture == left.neighborAirState.neighborAir.up)) {
                neighborAirState.currentBlockTexture = neighborAirState.neighborAir.upAndRight;
            }

            if (    neighborAirState.neighborAir.downAndLeft != null &&
                    neighborAirState.neighborAir.downAndRight != null &&
                    (down.neighborAirState.currentBlockTexture == up.neighborAirState.neighborAir.downLeft) &&
                    (left.neighborAirState.currentBlockTexture == left.neighborAirState.neighborAir.down)) {
                neighborAirState.currentBlockTexture = neighborAirState.neighborAir.downAndLeft;
            }

            if (    neighborAirState.neighborAir.downAndLeft != null &&
                    neighborAirState.neighborAir.downAndRight != null &&
                    (down.neighborAirState.currentBlockTexture == up.neighborAirState.neighborAir.downRight) &&
                    (right.neighborAirState.currentBlockTexture == left.neighborAirState.neighborAir.down)) {
                neighborAirState.currentBlockTexture = neighborAirState.neighborAir.downAndRight;
            }
        }
    }
}
