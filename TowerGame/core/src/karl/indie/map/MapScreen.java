package karl.indie.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

import karl.indie.TowerGame;
import karl.indie.map.Tower.TowerActor;

/**
 * Created by Karl on 14.09.2016.
 */
public class MapScreen implements Screen, InputProcessor {
    private TowerGame game;
    private MapStage stage;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    public MapScreen(TowerGame ga) {
        game = ga;

        map = new TmxMapLoader().load("mapWithPath.tmx");
        stage = new MapStage(game, map);
        Gdx.input.setInputProcessor(this);

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

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        stage.addActor(new TowerActor(stage.screenToStageCoordinates(new Vector2(screenX, screenY)), game.assManager));
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
