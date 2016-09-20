package karl.indie.map;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;


/**
 * Created by Karl on 17.09.2016.
 */
public class TowerPlacer extends InputListener {
    MapStage stage;

    public TowerPlacer(MapStage stage) {
        super();
        this.stage = stage;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        super.touchDown(event, x, y, pointer, button);
        System.out.println("HI");
        return placeTower(x, y);
    }

    public boolean placeTower(float x, float y) {

        //TowerActor ta = new TowerActor((int) x, (int) y);
        //stage.addActor(ta);

        return true;
    }
}
