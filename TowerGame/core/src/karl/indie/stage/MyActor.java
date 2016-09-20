package karl.indie.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

/**
 * Created by Karl on 16.09.2016.
 */
public class MyActor extends Actor {
    Texture guy = new Texture(Gdx.files.internal("Guy.png"));

    public MyActor() {
        setTouchable(Touchable.enabled);
        addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.RIGHT) {
                    MoveByAction mba = new MoveByAction();
                    mba.setDuration(2f);
                    mba.setAmount(100f, 0f);

                    MoveByAction mba2 = new MoveByAction();
                    mba2.setDuration(2f);
                    mba2.setAmount(0f, 100f);

                    MyActor.this.addAction(new SequenceAction(mba, mba2));
                }
                return true;
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(guy, getX(), getY());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }


}
