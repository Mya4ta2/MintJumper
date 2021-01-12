package mint.runner.content;

import mint.runner.ctype.ContentList;
import mint.runner.type.Item;

public class Items implements ContentList {
    public static Item air;

    @Override
    public void load() {
        air = new Item("air");
    }
}
