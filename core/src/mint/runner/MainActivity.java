package mint.runner;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import mint.runner.content.*;
import mint.runner.screen.MenuScreen;
import mint.runner.screen.GameScreen;
import mint.runner.type.NeighborAir;
import mint.runner.ui.UI;

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

		Vars.ui = new UI();
		Vars.ui.load();

		gameScreen = new GameScreen();
		setScreen(gameScreen);

		setScreen(new MenuScreen(this)); //temp
		Vars.menuScreen = (MenuScreen) getScreen();
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
		Bullets.bullet.texture = new Texture("bullet.png");
		Weapons.test.texture = new Texture("weapon.png");
	}
}
