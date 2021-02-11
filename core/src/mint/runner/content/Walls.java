package mint.runner.content;

import com.badlogic.gdx.utils.Array;
import mint.runner.ctype.ContentList;
import mint.runner.type.Wall;

public class Walls implements ContentList {
    public static Wall air, dirt;

    public static Array<Wall> walls = new Array<>();

    @Override
    public void load() {
        air = new Wall("air");
        walls.add(air);
        dirt = new Wall("dirt");
        walls.add(dirt);
    }
}
