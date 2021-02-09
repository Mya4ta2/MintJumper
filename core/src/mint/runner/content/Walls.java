package mint.runner.content;

import mint.runner.ctype.ContentList;
import mint.runner.type.Wall;

public class Walls implements ContentList {
    public static Wall air;

    @Override
    public void load() {
        air = new Wall("air");
    }
}
