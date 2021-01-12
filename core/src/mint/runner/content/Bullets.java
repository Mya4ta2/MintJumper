package mint.runner.content;

import mint.runner.ctype.ContentList;
import mint.runner.type.BulletType;

public class Bullets implements ContentList {
    public static BulletType bullet;

    @Override
    public void load() {
        bullet = new BulletType("bullet") {
            {

            }
        };
    }
}
