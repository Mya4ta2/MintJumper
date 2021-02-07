package mint.runner.content;

import mint.runner.ctype.ContentList;
import mint.runner.type.Overlay;

public class Overlays implements ContentList {
    public static Overlay bush;

    @Override
    public void load() {
        bush = new Overlay("bush") {

        };
    }
}
