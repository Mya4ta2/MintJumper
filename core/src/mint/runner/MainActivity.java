package mint.runner;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import mint.runner.content.*;
import mint.runner.screen.MenuScreen;
import mint.runner.screen.GameScreen;
import mint.runner.type.Events;
import mint.runner.type.Rounding;
import mint.runner.ui.UI;

public class MainActivity extends Game {
	public Screen gameScreen;

	@Override
	public void create() {
		new Sounds().load();
		new Walls().load();
		new Blocks().load();
		new Overlays().load();
		new Items().load();
		new Bullets().load();
		new Weapons().load();
		new Events().load();
		loadTextures();

		Vars.ui = new UI();
		Vars.ui.load();

		gameScreen = new GameScreen();
		setScreen(gameScreen);

		setScreen(new MenuScreen(this)); //temp
		Vars.menuScreen = (MenuScreen) getScreen();
	}

	public static Rounding getDirt() {
		final TextureRegion[][] tmp = new TextureRegion(new Texture("dirt.png")).split(Vars.tileSize, Vars.tileSize);

		return new Rounding() {
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

	public static Rounding getBrick() {
		final TextureRegion[][] tmp = new TextureRegion(new Texture("brick.png")).split(Vars.tileSize, Vars.tileSize);

		return new Rounding() {
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

	public static Rounding getGrass() {
		final TextureRegion[][] tmp = new TextureRegion(new Texture("grass.png")).split(Vars.tileSize, Vars.tileSize);

		return new Rounding() {
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
				upAndLeft = tmp[0][3];
				upAndRight = tmp[0][4];
				downAndLeft = tmp[1][3];
				downAndRight = tmp[1][4];
			}
		};
	}

	public static Rounding getAir() {
		//empty textures
		return new Rounding() {
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
		Walls.dirt.texture = new Texture("dirt-wall.png");
		Overlays.bush.texture = new Texture("bush.png");
		Overlays.grass1.texture = new Texture("overlay/grass.png");
		Weapons.test.texture = new Texture("weapon.png");
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		Events.resize.run();
		super.resize(width, height);
	}

	@Override
	public void setScreen(Screen screen) {
		super.setScreen(screen);
	}

	@Override
	public Screen getScreen() {
		return super.getScreen();
	}
}
