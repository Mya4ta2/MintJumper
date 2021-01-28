package mint.runner.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class EditorResumeFragment extends Fragment {
    public Table table;

    @Override
    public void build(Group parent) {
        table = new Table();

        TextButton properties = new TextButton(new Texture("buttonUp.png"), new Texture("buttonDown.png"), new BitmapFont());
        properties.setText("properties");
        properties.setSize(170,70);

        TextButton exit = new TextButton(new Texture("buttonUp.png"), new Texture("buttonDown.png"), new BitmapFont());
        exit.setText("exit");
        exit.setSize(170,70);

        table.center().add(properties).row();
        table.add(new Separator(25)).row();
        table.center().add(exit);

        parent.addActor(table);
    }

    //oh no
    public void resize(int width, int height) {
        table.setSize(width,height);
    }
}
