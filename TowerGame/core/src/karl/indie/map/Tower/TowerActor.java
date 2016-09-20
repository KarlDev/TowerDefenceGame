package karl.indie.map.Tower;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

import karl.indie.map.MapStage;
import karl.indie.units.Unit;

/**
 * Created by Karl on 17.09.2016.
 */
public class TowerActor extends Actor {
    private String txtPath = "Tower.png";
    private String missleTexturePath = "DefaultMisle.png";
    private AssetManager am;
    float atkSpeed;
    float sinceAtk;
    float range;
    int dmg;

    public TowerActor(Vector2 pos, AssetManager am) {
        this.am = am;
        if(!am.isLoaded(txtPath)){
            am.load(txtPath, Texture.class);
            am.finishLoading();
        }
        setPosition(pos.x, pos.y);

        atkSpeed = 0.8f;
        sinceAtk = 0;
        range = 50;
        dmg = 2;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(am.get(txtPath, Texture.class), getX(), getY());
    }

    @Override
    public void act(float delta) {
        sinceAtk += delta;
        if( !(sinceAtk >= atkSpeed) ) return;
        sinceAtk = 0;
        Group grp = ((MapStage) getStage()).unitGroup;
        for(Actor unit : grp.getChildren()) {
            if (Vector2.dst(getX(), getY(), unit.getX(), unit.getY()) < range) {
                ((Unit) unit).getHit(dmg);
                MissileActor ma = new MissileActor(am, "missile.png");
                ma.setPosition(getX(), getY());
                ma.addAction(Actions.sequence(Actions.moveTo(unit.getX(), unit.getY(), 0.3f), Actions.removeActor()));
                getStage().addActor(ma);
            }
        }
    }
}
