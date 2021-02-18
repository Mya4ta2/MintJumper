package mint.runner.content;

import mint.runner.ctype.ContentList;
import mint.runner.type.WeaponType;

public class Weapons implements ContentList {
    public static WeaponType test;

    @Override
    public void load() {
        test = new WeaponType("test") {
            {
                bulletType = Bullets.bullet;
                width = 1.5f;
                height = 0.5f;
            }
        };
    }
}
