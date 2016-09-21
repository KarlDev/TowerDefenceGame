package karl.indie.map.Tower;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

import karl.indie.map.stage.MapStage;
import karl.indie.map.stage.TiledMapTileActor;

/**
 * Created by mun on 21.09.2016.
 */

public class TowerChooser extends Group {
    private MapStage stage;
    private TiledMapTileActor tileActor;
    private AssetManager am;
    private Array<String> possibleTower;

    public TowerChooser(TiledMapTileActor tileActor) {
        this.tileActor = tileActor;
        stage = (MapStage)tileActor.getStage();
        am = stage.game.assManager;

        setName("TowerChooserGroup");

        this.setBounds(tileActor.getX(), tileActor.getY(), tileActor.getWidth(), tileActor.getHeight());

        am.load("Tower1.png", Texture.class);
        am.load("Tower2.png", Texture.class);
        am.load("Tower3.png", Texture.class);
        am.finishLoading();


        TowerButtonActor act = new TowerButtonActor("Tower1.png");
        act.setBounds( - 36, - 48, getWidth(), getHeight());
        addActor(act);
        TowerButtonActor act1 = new TowerButtonActor("Tower2.png");
        act1.setBounds(0,  - 48, getWidth(), getHeight());
        addActor(act1);
        TowerButtonActor act2 = new TowerButtonActor("Tower3.png");
        act2.setBounds(+ 36,  - 48, getWidth(), getHeight());
        addActor(act2);

        TowerChooser lastChooser = (TowerChooser)stage.getActorByName("TowerChooserGroup");
        if(lastChooser != null) lastChooser.tileActor.towerBuilt = false;
        stage.removeActorByName("TowerChooserGroup");
    }

    public class TowerButtonActor extends Actor {
        private String towerType;

        public TowerButtonActor(String towerType) {
            this.towerType = towerType;
            addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    getStage().addActor(new TowerActor(tileActor, TowerButtonActor.this.towerType));
                    TowerChooser.this.remove();
                }
            });
        }
        @Override
        public void draw(Batch batch, float parentAlpha) {
            batch.draw(am.get(towerType, Texture.class), getX(), getY());
        }
    }
}
