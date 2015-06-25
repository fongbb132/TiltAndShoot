package com.fong.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.fong.game.gameworld.GameWorld;

/**
 * Created by wing on 6/14/15.
 */
public class Bullet {
    private Vector2 acceleration, velocity, position;
    private Circle circle;
    private boolean isHitTarget;
    private boolean isExisted;

    public Bullet(float angle, float posX, float posY) {
        int v = 800;
        isExisted = true;
        int accX = (MathUtils.cos(angle)>0)?-4:4;
        int accY = (MathUtils.sin(angle)>0)?-4:4;
        this.acceleration = new Vector2(accX, accY);
        velocity = new Vector2((v*MathUtils.cos((float)(angle / 180 * Math.PI))), v*(MathUtils.sin(((float)(angle / 180 * Math.PI)))));
        position = new Vector2(posX+35*GameWorld.gameWidth/1196, posY+35*GameWorld.gameHeight/768);
        circle = new Circle(position.x, position.y, 10*GameWorld.gameHeight/768);
        isHitTarget = false;
    }

    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));
        if(position.x>Gdx.graphics.getWidth()-100||position.x < -1||position.y> Gdx.graphics.getHeight()+10||position.y<-1){
            isExisted = false;
        }
        circle.set(position.x, position.y, 10* GameWorld.gameHeight/768);
    }


    public Circle getCircle(){
        return circle;
    }

    public void setIsExisted(){
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
