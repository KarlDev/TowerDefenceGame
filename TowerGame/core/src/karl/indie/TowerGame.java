package karl.indie;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import karl.indie.map.MapScreen;

public class TowerGame extends Game {
	public SpriteBatch batch;
	public AssetManager assManager;
	
	@Override
	public void create () {
		assManager = new AssetManager();
		batch = new SpriteBatch();


		this.setScreen(new MapScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
