package mint.runner.type;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import mint.runner.content.Blocks;
import mint.runner.content.Walls;

public class Tile {
    public int x, y;
    public Block block = Blocks.air;
    public Wall wall = Walls.air;
    public Overlay overlay;
    public RoundingState roundingState = new RoundingState(block.rounding);

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
    public void setRounding(World world) {
        Tile
        upLeft = null, up = null, upRight = null,
        left = null, right = null,
        downLeft = null, down = null, downRight = null;

        Tile emptyTile = new Tile(0,0);

        boolean leftWorldEnd = false, rightWorldEnd = false, upWorldEnd = false, downWorldEnd = false;

        TextureRegion tmp = roundingState.currentBlockTexture;
        roundingState = new RoundingState(block.rounding);
        roundingState.currentBlockTexture = tmp;

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

            if (left.block == Blocks.air) roundingState.currentBlockTexture = roundingState.rounding.left;
            if (up.block == Blocks.air) roundingState.currentBlockTexture = roundingState.rounding.up;
            if (right.block == Blocks.air) roundingState.currentBlockTexture = roundingState.rounding.right;
            if (down.block == Blocks.air) roundingState.currentBlockTexture = roundingState.rounding.down;

            if (right.block == Blocks.air && up.block == Blocks.air) roundingState.currentBlockTexture = roundingState.rounding.upRight;
            if (left.block == Blocks.air && up.block == Blocks.air) roundingState.currentBlockTexture = roundingState.rounding.upLeft;

            if (right.block == Blocks.air && down.block == Blocks.air) roundingState.currentBlockTexture = roundingState.rounding.downRight;
            if (left.block == Blocks.air && down.block == Blocks.air) roundingState.currentBlockTexture = roundingState.rounding.downLeft;

            if (
                   up.block != Blocks.air &&
                   down.block != Blocks.air &&
                   right.block != Blocks.air &&
                   left.block != Blocks.air
            ) {
                roundingState.currentBlockTexture = roundingState.rounding.middle;
            }

            if (    roundingState.rounding.upAndLeft != null &&
                    roundingState.rounding.upAndRight != null &&
                    (up.roundingState.currentBlockTexture == up.roundingState.rounding.left) &&
                    (left.roundingState.currentBlockTexture == left.roundingState.rounding.up)) {
                roundingState.currentBlockTexture = roundingState.rounding.upAndLeft;
            }

            if (    roundingState.rounding.upAndLeft != null &&
                    roundingState.rounding.upAndRight != null &&
                    (up.roundingState.currentBlockTexture == up.roundingState.rounding.right) &&
                    (right.roundingState.currentBlockTexture == left.roundingState.rounding.up)) {
                roundingState.currentBlockTexture = roundingState.rounding.upAndRight;
            }

            if (    roundingState.rounding.downAndLeft != null &&
                    roundingState.rounding.downAndRight != null &&
                    (down.roundingState.currentBlockTexture == up.roundingState.rounding.left) &&
                    (left.roundingState.currentBlockTexture == left.roundingState.rounding.down)) {
                roundingState.currentBlockTexture = roundingState.rounding.downAndLeft;
            }

            if (    roundingState.rounding.downAndLeft != null &&
                    roundingState.rounding.downAndRight != null &&
                    (down.roundingState.currentBlockTexture == up.roundingState.rounding.right) &&
                    (right.roundingState.currentBlockTexture == left.roundingState.rounding.down)) {
                roundingState.currentBlockTexture = roundingState.rounding.downAndRight;
            }

            //

            if (    roundingState.rounding.upAndLeft != null &&
                    roundingState.rounding.upAndRight != null &&
                    (up.roundingState.currentBlockTexture == up.roundingState.rounding.upLeft) &&
                    (left.roundingState.currentBlockTexture == left.roundingState.rounding.up)) {
                roundingState.currentBlockTexture = roundingState.rounding.upAndLeft;
            }

            if (    roundingState.rounding.upAndLeft != null &&
                    roundingState.rounding.upAndRight != null &&
                    (up.roundingState.currentBlockTexture == up.roundingState.rounding.upRight) &&
                    (right.roundingState.currentBlockTexture == left.roundingState.rounding.up)) {
                roundingState.currentBlockTexture = roundingState.rounding.upAndRight;
            }

            if (    roundingState.rounding.downAndLeft != null &&
                    roundingState.rounding.downAndRight != null &&
                    (down.roundingState.currentBlockTexture == up.roundingState.rounding.downLeft) &&
                    (left.roundingState.currentBlockTexture == left.roundingState.rounding.down)) {
                roundingState.currentBlockTexture = roundingState.rounding.downAndLeft;
            }

            if (    roundingState.rounding.downAndLeft != null &&
                    roundingState.rounding.downAndRight != null &&
                    (down.roundingState.currentBlockTexture == up.roundingState.rounding.downRight) &&
                    (right.roundingState.currentBlockTexture == left.roundingState.rounding.down)) {
                roundingState.currentBlockTexture = roundingState.rounding.downAndRight;
            }
        }
    }
}
