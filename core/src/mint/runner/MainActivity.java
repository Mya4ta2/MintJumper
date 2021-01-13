package mint.runner;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import mint.runner.content.*;
import mint.runner.editor.WorldEditorScreen;
import mint.runner.screen.GameScreen;
import mint.runner.type.NeighborAir;

import java.util.Arrays;

public class MainActivity extends Game {
	public Screen gameScreen;

	@Override
	public void create() {
		new Sounds().load();
		new Blocks().load();
		new Items().load();
		new Bullets().load();
		new Weapons().load();
		loadTextures();

		gameScreen = new GameScreen();
		setScreen(gameScreen);

		setScreen(new WorldEditorScreen()); //temp

	}

	public static NeighborAir getDirt() {
		final TextureRegion[][] tmp = new TextureRegion(new Texture("dirt.png")).split(Vars.tileSize, Vars.tileSize);

		return new NeighborAir() {
			{
				upLeft = tmp[0][0];
				up = tmp[0][1];
				upRight = tmp[0][2];
				left = tmp[1][0];
				middle = tmp[1][1];
				right = tmp[1][2];
				downLeft = tmp[2][0];
				down = tmp[2][1];
				downRight = tmp[2][2];
			}
		};
	}

	public static NeighborAir getGrass() {
		final TextureRegion[][] tmp = new TextureRegion(new Texture("grass.png")).split(Vars.tileSize, Vars.tileSize);

		return new NeighborAir() {
			{
				upLeft = tmp[0][0];
				up = tmp[0][1];
				upRight = tmp[0][2];
				left = tmp[1][0];
				middle = tmp[1][1];
				right = tmp[1][2];
				downLeft = tmp[2][0];
				down = tmp[2][1];
				downRight = tmp[2][2];
			}
		};
	}

	public static NeighborAir getAir() {
		//empty textures
		return new NeighborAir() {
			{
				upLeft = new TextureRegion();
				up = new TextureRegion();
				upRight = new TextureRegion();
				left = new TextureRegion();
				middle = new TextureRegion();
				right = new TextureRegion();
				downLeft = new TextureRegion();
				down = new TextureRegion();
				downRight = new TextureRegion();
			}
		};
	}

	public void loadTextures() {
		Bullets.bullet.texture = new Texture("dirt.png");
		Weapons.test.texture = new Texture("weapon.png");
	}
}
