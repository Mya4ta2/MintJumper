package mint.runner.type;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import mint.runner.Vars;
import mint.runner.content.Blocks;

public class Tile {
    public int x, y;
    public Block block = Blocks.air;
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

    public void setNeighborAir(World world) {
        Tile
        upLeft, up, upRight,
        left, right,
        downLeft, down, downRight;

        TextureRegion tmp = neighborAirState.currentBlockTexture;
        neighborAirState = new NeighborAirState(block.neighborAir);
        neighborAirState.currentBlockTexture = tmp;

        if (block != Blocks.air) {
            upLeft = world.tiles.get(x - 1,y + 1);
            up = world.tiles.get(x,y + 1);
            upRight = world.tiles.get(x + 1,y + 1);
            left = world.tiles.get(x - 1, y);
            right = world.tiles.get(x + 1, y);
            downLeft = world.tiles.get(x - 1, y - 1);
            down = world.tiles.get(x, y - 1);
            downRight = world.tiles.get(x + 1, y - 1);

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
        }

//        if (x - 1 < 0 || y + 1 > world.height-1) upLeft = null;
//        else upLeft = world.tiles.get(x - 1,y + 1);
//
//        if (y + 1 > world.height-1) up = null;
//        else up = world.tiles.get(x,y + 1);
//
//        if (x + 1 > world.width-1 || y + 1 > world.height-1) upRight = null;
//        else upRight = world.tiles.get(x + 1,y + 1);
//
//        if (x - 1 < 0) left = null;
//        else left = world.tiles.get(x - 1, y);
//
//        if (x + 1 > world.width-1) right = null;
//        else right = world.tiles.get(x + 1,y);
//
//        downLeft = world.tiles.get(x -1, y - 1);

//        down = world.tiles.get(x, y - 1);
//        downRight = world.tiles.get(x + 1, y - 1);

    }
}
