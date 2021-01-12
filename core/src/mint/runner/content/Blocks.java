package mint.runner.content;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import mint.runner.MainActivity;
import mint.runner.ctype.ContentList;
import mint.runner.type.Block;
import mint.runner.type.NeighborAir;

public class Blocks implements ContentList {
    public static Block air, dirt;

    @Override
    public void load() {
        air = new Block("air") {
            {
                neighborAir = MainActivity.getDirt();
            }
        };

        dirt = new Block("dirt") {
            {
                neighborAir = MainActivity.getDirt();
            }
        };
    }
}
