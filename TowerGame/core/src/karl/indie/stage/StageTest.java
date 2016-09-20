package karl.indie.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

import karl.indie.TowerGame;



/**
 * Created by Karl on 15.09.2016.
 */
public class StageTest implements Screen {
    MyStage stage;

    private TiledMap map;
    private TiledMapRenderer mapRenderer;

    public StageTest(TowerGame g) {
        stage = new MyStage();
        Gdx.input.setInputProcessor(stage);

        stage.addActor(new MyActor());

        stage.getActors().get(0).setPosition(32, 32);
        stage.setKeyboardFocus(stage.getActors().get(0));
        stage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);
                MoveToAction mta = new MoveToAction();
                mta.setPosition(x,y);
                mta.setDuration(5f);
                stage.getActors().get(0).addAction(mta);
                return true;
            }
        });

        map = new TmxMapLoader().load("map.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);
        mapRenderer.setView((OrthographicCamera)stage.getCamera());
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        //mapRenderer.render();
        /**
        timeSinceAct += delta;
        if(timeSinceAct >= 0.5) {
            timeSinceAct = 0;
            stage.act();
        }**/
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
