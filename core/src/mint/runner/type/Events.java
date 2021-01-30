package mint.runner.type;

import com.badlogic.gdx.Gdx;

public class Events {
    public static Event resize;

    public void load() {
        resize = new Event();
    }
}
