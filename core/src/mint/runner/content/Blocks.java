package mint.runner.content;

import com.badlogic.gdx.utils.Array;
import mint.runner.MainActivity;
import mint.runner.ctype.ContentList;
import mint.runner.type.Block;

public class Blocks implements ContentList {
    public static Block air, dirt, grass, brick;
    public static Array<Block> blocks = new Array<>();

    @Override
    public void load() {
        air = new Block("air") {
            {
                rounding = MainActivity.getDirt();
            }
        };
        blocks.add(air);

        dirt = new Block("dirt") {
            {
                rounding = MainActivity.getDirt();
            }
        };
        blocks.add(dirt);

        grass = new Block("grass") {
            {
                rounding = MainActivity.getGrass();
            }
        };
        blocks.add(grass);

        brick = new Block("brick") {
            {
                rounding = MainActivity.getBrick();
            }
        };
        blocks.add(brick);
    }
}
