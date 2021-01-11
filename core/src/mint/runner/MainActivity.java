package mint.runner;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import mint.runner.content.Blocks;
import mint.runner.screen.GameScreen;

public class MainActivity extends Game {
	public Screen gameScreen;

	@Override
	public void create() {
		new Blocks().load();
		loadTextures();

		gameScreen = new GameScreen();
		setScreen(gameScreen);
	}

	public void loadTextures() {
		Blocks.dirt.texture = new Texture("badlogic.jpg"); //temp
	}
}
