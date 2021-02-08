package mint.runner.content;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import mint.runner.MainActivity;
import mint.runner.ctype.ContentList;
import mint.runner.type.Block;
import mint.runner.type.NeighborAir;

public class Blocks implements ContentList {
    public static Block air, dirt, grass, brick;
    public static Array<Block> blocks = new Array<>();

    @Override
    public void load() {
        air = new Block("air") {
            {
                neighborAir = MainActivity.getDirt();
            }
        };
        blocks.add(air);

        dirt = new Block("dirt") {
            {
                neighborAir = MainActivity.getDirt();
            }
        };
        blocks.add(dirt);

        grass = new Block("grass") {
            {
                neighborAir = MainActivity.getGrass();
            }
        };
        blocks.add(grass);

        brick = new Block("brick") {
            {
                neighborAir = MainActivity.getBrick();
            }
        };
        blocks.add(brick);
    }
}
