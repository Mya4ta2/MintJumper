package mint.runner.ui;

import mint.runner.ui.fragment.EditorResumeFragment;
import mint.runner.ui.fragment.MenuFragment;
import mint.runner.ui.fragment.WorldPropertiesFragment;

public class UI {
    public EditorResumeFragment resumeFragment;
    public WorldPropertiesFragment propertiesFragment;
    public MenuFragment menuFragment;

    public void load() {
        resumeFragment = new EditorResumeFragment();
        propertiesFragment = new WorldPropertiesFragment();
        menuFragment = new MenuFragment();
    }
}
