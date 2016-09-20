package karl.indie.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import karl.indie.TowerGame;
/**
 * Created by Karl on 14.09.2016.
 */
public class MapScreen implements Screen{
    private TowerGame game;
    private karl.indie.map.stage.MapStage stage;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    public MapScreen(TowerGame ga) {
        game = ga;

        map = new TmxMapLoader().load("mapWithPath.tmx");
        stage = new karl.indie.map.stage.MapStage(game, map);
        Gdx.input.setInputProcessor(stage);

        mapRenderer = new OrthogonalTiledMapRenderer(map);
        mapRenderer.setView((OrthographicCamera)stage.getCamera());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.assManager.update();
        stage.act();

        mapRenderer.render();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        stage.dispose();
        map.dispose();
        game.dispose();
        mapRenderer.dispose();
    }
}
