package mint.runner.type;

import com.badlogic.gdx.utils.Array;
import mint.runner.content.*;
import mint.runner.ctype.MappableContent;

public class ContentLoader {
    public Array<MappableContent> mappableContents = new Array();

    public Sounds sounds = new Sounds();
    public Walls walls = new Walls();
    public Blocks blocks = new Blocks();
    public Overlays overlays = new Overlays();
    public Items items = new Items();
    public Bullets bullets = new Bullets();
    public Weapons weapons = new Weapons();
    public Events events = new Events();

    public void load() {
        sounds.load();
        walls.load();
        blocks.load();
        overlays.load();
        items.load();
        bullets.load();
        weapons.load();
        events.load();

        mappableContents.addAll(Walls.walls);
        mappableContents.addAll(Blocks.blocks);
        mappableContents.addAll(Overlays.overlays);
    }
}
