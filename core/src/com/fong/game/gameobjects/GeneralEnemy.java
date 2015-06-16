package com.fong.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.fong.game.gameworld.GameWorld;

/**
 * Created by wing on 6/15/15.
 */
public class GeneralEnemy extends Enemy {
    public GeneralEnemy(float posX, float posY) {
        super(posX, posY);
        this.velocity.set(5,5);
    }

    @Override
    public void update(float delta, float posX, float posY) {
        position.add(velocity.cpy().scl(delta));
        if (position.x < 1 || position.x > GameWorld.gameWidth) {
            velocity.x = -velocity.x;
        }
        if (position.y < 1 || position.y > GameWorld.gameHeight) {
            velocity.y = -velocity.y;
        }
        myCircle.set(position.x+0.5f, position.y+0.5f, 1);
        position.add(velocity.cpy().scl(delta));
    }
}
