package karl.indie.units;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.Array;

import karl.indie.map.stage.MapStage;


/**
 * Created by Karl on 14.09.2016.
 */
public class Unit extends Actor {

    AssetManager am;
    String txtPath = "Guy.png";

    float speed = 1;
    int hp = 100;
    int spawnX, spawnY;

    Array<MapStage.direction> path;

    public Unit(int x, int y, Array<MapStage.direction> path, AssetManager am) {
        super();
        //get Game
        this.am = am;
        loadTexture();

        spawnX = x * 32 + 16;
        spawnY = y * 32 + 16;

        this.setPosition(spawnX, spawnY);
        //Add path an PathActions
        this.path = path;
        SequenceAction action = new SequenceAction();
        for(MapStage.direction d : path) {
            switch (d) {
                case UP:
                    action.addAction(Actions.moveBy(0f, 32f, speed));
                    break;
                case DOWN:
                    action.addAction(Actions.moveBy(0f, -32f, speed));
                    break;
                case LEFT:
                    action.addAction(Actions.moveBy(-32f, 0f, speed));
                    break;
                case RIGHT:
                    action.addAction(Actions.moveBy(32f, 0f, speed));
            }
        }
        addAction(action);
    }

    public void loadTexture() {
        am.load(txtPath, Texture.class);
        am.finishLoading();
    }
    public void getHit(int dmg) {
        hp -= dmg;
        if(hp <= 0) {
            remove();
        }
    }



    @Override
    public void draw(Batch batch, float parentAlpha) {
        Texture t = am.get(txtPath, Texture.class);
        batch.draw(t, getX() - t.getWidth()/2, getY() - t.getHeight()/2);
    }
}
