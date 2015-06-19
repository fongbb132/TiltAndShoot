package com.fong.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.fong.game.gameworld.GameWorld;

/**
 * Created by wing on 6/15/15.
 */
public class Enemy {

    public Vector2 velocity, position;
    private boolean isExisted;
    public Circle myCircle;
    private float velX = MathUtils.random()*200;
    private float velY = MathUtils.random()*200;

    public Enemy(float posX, float posY){
        velocity = new Vector2(velX, velY);
        position = new Vector2(posX+10, posY+10);
        isExisted = true;
        myCircle = new Circle(position.x+10*GameWorld.gameWidth/1196, position.y+10*GameWorld.gameHeight/768, 1);
    }

    public void update(float delta, float posX, float posY){
        velocity.x = (position.x < posX+15)? Math.abs(velocity.x):-Math.abs(velocity.x);
        velocity.y = (position.y < posY+15)? Math.abs(velocity.y):-Math.abs(velocity.y);

        if (position.x < 1 || position.x > GameWorld.gameWidth-140) {
            velocity.x = -velocity.x;
        }
        if (position.y < 1 || position.y > GameWorld.gameHeight) {
            velocity.y = -velocity.y;
        }
        myCircle.set(position.x+10*GameWorld.gameWidth/1196, position.y+10*GameWorld.gameHeight/768, 10*GameWorld.gameHeight/768);
        position.add(velocity.cpy().scl(delta));

    }

    public boolean isOverlap(Circle c){
        if(myCircle.overlaps(c))
            isExisted = false;
        return isExisted;
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

    public void setVelocity(float velX, float velY){
        velocity.set(velX, velY);
    }

}
