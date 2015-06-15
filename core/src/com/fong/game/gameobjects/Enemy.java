package com.fong.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by wing on 6/15/15.
 */
public class Enemy {

    private Vector2 velocity, position, acceleration;
    private boolean isExisted;

    public Enemy(float posX, float posY){
        velocity = new Vector2(40,40);
        position = new Vector2(posX+2, posY+2);
        isExisted = true;
    }

    public void update(float delta, float posX, float posY){
        velocity.x = (position.x < posX)? Math.abs(velocity.x):-Math.abs(velocity.x);
        velocity.y = (position.y < posY)? Math.abs(velocity.y):-Math.abs(velocity.y);

        if (position.x < 1 || position.x > Gdx.graphics.getWidth()/Gdx.graphics.getWidth()*136 - 4) {
            velocity.x = -velocity.x;
        }
        if (position.y < 1 || position.y > Gdx.graphics.getHeight()/Gdx.graphics.getHeight()*88 - 4) {
            velocity.y = -velocity.y;
        }

        position.add(velocity.cpy().scl(delta));

    }

    public void setIsExisted(){
        this.isExisted = false;
    }

    public float getX(){
        return position.x;
    }

    public float getY(){
        return position.y;
    }
}
