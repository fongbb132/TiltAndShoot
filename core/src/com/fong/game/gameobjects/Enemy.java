package com.fong.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.fong.game.gameworld.GameWorld;

/**
 * Created by wing on 6/15/15.
 */
public class Enemy {

    public Vector2 velocity, position, acceleration;
    private boolean isExisted;
    public Circle myCircle;

    public Enemy(float posX, float posY){
        velocity = new Vector2(10,10);
        position = new Vector2(posX+2, posY+2);
        isExisted = true;
        myCircle = new Circle(position.x+0.5f, position.y+0.5f, 1);
    }

    public void update(float delta, float posX, float posY){
        velocity.x = (position.x < posX)? Math.abs(velocity.x):-Math.abs(velocity.x);
        velocity.y = (position.y < posY)? Math.abs(velocity.y):-Math.abs(velocity.y);

        if (position.x < 1 || position.x > GameWorld.gameWidth ) {
            velocity.x = -velocity.x;
        }
        if (position.y < 1 || position.y > GameWorld.gameHeight) {
            velocity.y = -velocity.y;
        }
        myCircle.set(position.x+0.5f, position.y+0.5f, 1);
        position.add(velocity.cpy().scl(delta));

    }

    public void isOverlap(Circle c){
        if(myCircle.overlaps(c))
            isExisted = false;
    }

    public boolean isExisted(){
        return isExisted;
    }

    public float getX(){
        return position.x;
    }

    public float getY(){
        return position.y;
    }
}
