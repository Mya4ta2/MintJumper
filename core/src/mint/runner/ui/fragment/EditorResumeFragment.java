package mint.runner.ui.fragment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import mint.runner.type.Events;
import mint.runner.ui.Separator;
import mint.runner.ui.TextButton;

public class EditorResumeFragment extends Fragment {
    public Table table;

    @Override
    public void build(Group parent) {
        table = new Table();

        TextButton properties = new TextButton(new Texture("buttonUp.png"), new Texture("buttonDown.png"), new BitmapFont());
        properties.setText("properties");
        properties.setSize(170,70);

        TextButton save = new TextButton(new Texture("buttonUp.png"), new Texture("buttonDown.png"), new BitmapFont());
        save.setText("save");
        save.setSize(170,70);

        TextButton exit = new TextButton(new Texture("buttonUp.png"), new Texture("buttonDown.png"), new BitmapFont());
        exit.setText("exit");
        exit.setSize(170,70);

        table.center().add(properties).row();
        table.center().add(new Separator(25)).row();
        table.center().add(save).row();
        table.center().add(new Separator(25)).row();
        table.center().add(exit).row();
        table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Events.resize.add(() -> {
            table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        });

        parent.addActor(table);
    }
}
