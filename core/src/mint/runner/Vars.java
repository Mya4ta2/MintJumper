package mint.runner;

import com.badlogic.gdx.graphics.Camera;
import mint.runner.screen.MenuScreen;
import mint.runner.type.ContentLoader;
import mint.runner.type.Events;
import mint.runner.type.World;
import mint.runner.ui.UI;

public class Vars {
    public static int tileSize = 16;
    public static World world;
    public static Camera camera;

    public static MenuScreen menuScreen; //i think how to fix this =)
    public static UI ui;
    public static ContentLoader content;

    public static String worldDir = "world";
}
