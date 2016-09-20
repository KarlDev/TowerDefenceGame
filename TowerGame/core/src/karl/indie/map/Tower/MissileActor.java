package karl.indie.map.Tower;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Karl on 17.09.2016.
 */
public class MissileActor extends Actor{
    AssetManager am;
    String texture;
    public MissileActor(AssetManager am, String path) {
        this.am = am;
        texture = path;
        if(!am.isLoaded(path, Texture.class)) {
            am.load(path, Texture.class);
            am.finishLoading();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(am.get(texture, Texture.class), getX(), getY());
    }
}
