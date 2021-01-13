package mint.runner.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import mint.runner.Vars;
import mint.runner.content.Blocks;
import mint.runner.ctype.ContentList;
import mint.runner.ctype.MappableContent;
import mint.runner.type.Block;
import mint.runner.type.ContentType;

import java.lang.reflect.Field;

public class ContentSelectMenu extends Actor {
    public static final float separator = 3;
    public ContentType contentType;

    public ContentSelectMenu(ContentType contentType) {
        this.contentType = contentType;

        setDefaultPos();
        setDefaultSize();
    }

    public void setDefaultPos() {
        setPosition(100,100);
    }

    public void setDefaultSize() {
        setSize(100,100);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (contentType == ContentType.block) {
            int x = 0;
            int y = 0;
            for (int i = 0; i < (Blocks.blocks.size); i++) {
                x++;

                if ((Vars.tileSize * x * separator) > getWidth()) {
                    y++;
                    x = 0;
                }

                Block block = Blocks.blocks.get(i);

                batch.draw(
                        block.neighborAir.up,
                        getX() + (Vars.tileSize * x * separator),
                        getY() + (Vars.tileSize * y * separator),
                        Vars.tileSize * 2,
                        Vars.tileSize * 2
                );
            }
        }
    }
}
