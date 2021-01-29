package mint.runner.ui;

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
