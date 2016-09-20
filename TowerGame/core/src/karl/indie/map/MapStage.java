package karl.indie.map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import karl.indie.TowerGame;
import karl.indie.units.Unit;

/**
 * Created by Karl on 17.09.2016.
 */
public class MapStage extends Stage {
    private TowerGame game;
    private TiledMap map;
    private Array<direction> path;

    public Group unitGroup;

    private int spawnX, spawnY;

    public MapStage(TowerGame game, TiledMap map) {
        this.game = game;
        this.map = map;

        unitGroup = new Group();
        unitGroup.setName("unitGroup");
        addActor(unitGroup);
        //addListener(new TowerPlacer(this));

        setPath();
        //spawnUnit();
    }

    float timeSinceUnit = 0;

    @Override
    public void act(float delta) {
        spawnUnit(delta);

        super.act(delta);
    }

    public void spawnUnit(float delta) {
        timeSinceUnit += delta;
        if(timeSinceUnit < 2.5f) return;

        timeSinceUnit = 0;
        unitGroup.addActor(new Unit(spawnX, spawnY, path, game.assManager));
    }


    public void setPath() {
        path = new Array<direction>();
        TiledMapTileLayer pl = (TiledMapTileLayer)map.getLayers().get("pathLayer");

        /**for(int x = 0; x < pl.getWidth(); x++) {
            for(int y = 0; y < pl.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = pl.getCell(x, y);
                if(!(cell == null)) System.out.println(cell.getTile().getId() + " at: " + x + ", " + y);
            }
        }**/
        //Set Spawnpoint and first direction in Path
        setSpawnpoint(pl);
        //Spawnpoint x and y
        int x = spawnX, y = spawnY;

        boolean hasNextPathTile = true;

        while (hasNextPathTile) {
            switch (pl.getCell(x,y).getTile().getId()) {
                case 3:
                    //Exit point
                    hasNextPathTile = false;
                    break;
                case 4:
                    //down
                    path.add(direction.DOWN);
                    y--;
                    break;
                case 5:
                    //left
                    path.add(direction.LEFT);
                    x--;
                    break;
                case 6:
                    //right
                    path.add(direction.RIGHT);
                    x++;
                    break;
                case 7:
                    //up
                    path.add(direction.UP);
                    y++;
                    break;
                default:
                    y += path.get(0).getY();
                    x += path.get(0).getX();
            }
        }
    }
    public void setSpawnpoint(TiledMapTileLayer pl) {
        for(int x = 0; x < pl.getWidth(); x++) {
            for(int y = 0; y < pl.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = pl.getCell(x, y);
                if(!(cell == null)) {
                    //Check for SpawnpointTile
                    switch (cell.getTile().getId()) {
                        case 8:
                            //down
                            path.add(direction.DOWN);
                            spawnX = x;
                            spawnY = y;
                            return;
                        case 9:
                            //left
                            path.add(direction.LEFT);
                            spawnX = x;
                            spawnY = y;
                            return;
                        case 10:
                            //right
                            path.add(direction.RIGHT);
                            spawnX = x;
                            spawnY = y;
                            return;
                        case 11:
                            //up
                            path.add(direction.UP);
                            spawnX = x;
                            spawnY = y;
                            return;
                    }
                }
            }
        }
    }

    public enum direction {
        UP,DOWN,LEFT,RIGHT;

        public int getX() {
            switch (this) {
                case UP:
                    return 0;
                case DOWN:
                    return 0;
                case LEFT:
                    return -1;
                case RIGHT:
                    return 1;
                default:
                    return 0;
            }
        }
        public int getY() {
            switch (this) {
                case UP:
                    return 1;
                case DOWN:
                    return -1;
                case LEFT:
                    return 0;
                case RIGHT:
                    return 0;
                default:
                    return 0;
            }
        }
    }
}
