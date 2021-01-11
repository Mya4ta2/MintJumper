package mint.runner.content;

import mint.runner.ctype.ContentList;
import mint.runner.type.Block;

public class Blocks implements ContentList {
    public static Block air, dirt;

    @Override
    public void load() {
        air = new Block("air") {
            {

            }
        };

        dirt = new Block("dirt") {
            {

            }
        };
    }
}
