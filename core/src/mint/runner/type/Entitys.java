package mint.runner.type;

import com.badlogic.gdx.utils.Array;

public class Entitys {
    public Array<Entity> array = new Array<>();

    public void add(Entity entity) {
        array.add(entity);
    }

    public void updateAll(float delta) {
        for (Entity entity : array) {
            entity.update(delta);
        }
    }
}
