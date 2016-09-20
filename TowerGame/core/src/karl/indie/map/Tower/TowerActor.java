package karl.indie.map.Tower;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import karl.indie.map.stage.MapStage;
import karl.indie.map.stage.TiledMapTileActor;
import karl.indie.units.Unit;

/**
 * Created by Karl on 17.09.2016.
 */
public class TowerActor extends Actor {
    private TiledMapTileActor tileActor;
    private AssetManager am;

    private String txtPath = "Tower.png";
    float drawX, drawY;
    private String missleTexturePath = "DefaultMisle.png";

    float atkSpeed;
    float sinceAtk;
    float range;
    int dmg;

    public TowerActor(TiledMapTileActor tileActor, String txtPath) {
        this.tileActor = tileActor;
        this.txtPath = txtPath;

        //Get ASsetmanager
        am = ((MapStage)tileActor.getStage()).game.assManager;
        //check if txt is rly loaded..
        if(!am.isLoaded(txtPath)){
            am.load(txtPath, Texture.class);
            am.finishLoading();
        }

        //BOUNDS
        drawX=tileActor.getX();
        drawY=tileActor.getY();
        setBounds(tileActor.getX() + 16, tileActor.getY() + 16, tileActor.getWidth(), tileActor.getHeight());


        //STATS
        atkSpeed = 0.8f;
        sinceAtk = 0;
        range = 50;
        dmg = 2;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(am.get(txtPath, Texture.class), drawX, drawY);
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
