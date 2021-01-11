package mint.runner.ctype;

public interface Renderer {
    void create();

    void render(float delta);

    void resize(int width, int height);
}
