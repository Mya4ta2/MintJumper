package mint.runner.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

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

        parent.addActor(table);
    }
}
