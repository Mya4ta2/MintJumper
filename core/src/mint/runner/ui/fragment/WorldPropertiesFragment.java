package mint.runner.ui.fragment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import mint.runner.type.Events;
import mint.runner.ui.NumericField;
import mint.runner.ui.TextButton;
import mint.runner.ui.TextField;

public class WorldPropertiesFragment extends Fragment {
    public Table table;
    @Override
    public void build(Group parent) {
        table = new Table();
        TextButton exit = new TextButton(new Texture("buttonUp.png"), new Texture("buttonDown.png"), new BitmapFont());
        exit.setText("exit");
        exit.setSize(170,70);

        BitmapFont bitmapFont = new BitmapFont();
        bitmapFont.setColor(Color.BLACK);
        TextField textField = new NumericField(bitmapFont);

        table.center().add(exit);
        table.center().add(textField);
        table.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        Events.resize.add(() -> {
            table.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        });

        parent.addActor(table);
    }
}
