package mint.runner.content;

import com.badlogic.gdx.utils.Array;
import mint.runner.ctype.ContentList;
import mint.runner.type.Overlay;

import java.util.ArrayList;

public class Overlays implements ContentList {
    public static Overlay bush, grass;
    public static Array<Overlay> overlays = new Array<>();

    @Override
    public void load() {
        bush = new Overlay("bush") {

        };
        overlays.add(bush);

        grass = new Overlay("grass");
        overlays.add(grass);
    }
}
