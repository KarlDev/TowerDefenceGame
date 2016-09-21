package karl.indie.map.stage;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import karl.indie.map.Tower.TowerActor;

/**
 * Created by Karl on 21.09.2016.
 */
public class TiledMapTileActor extends Actor {
    private TiledMap map;
    private TiledMapTileLayer layer;
    private TiledMapTileLayer.Cell cell;
    private boolean towerBuilt = false;

    public TiledMapTileActor(TiledMap map, TiledMapTileLayer layer, TiledMapTileLayer.Cell cell) {
        this.map = map;
        this.layer = layer;
        this.cell = cell;
        addListener(new TileActorListener());
    }

    public void buildTower() {
        getStage().addActor(new TowerActor(this, "Tower2.png"));
    }

    public class TileActorListener extends ClickListener {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            if(towerBuilt) return;
            buildTower();
            towerBuilt = true;
        }
    }
}
