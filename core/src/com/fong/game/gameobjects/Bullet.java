package com.fong.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by wing on 6/14/15.
 */
public class Bullet {
    private Vector2 acceleration, velocity, position;
    private Circle circle;
    private boolean isHitTarget;
    private boolean isExisted;

    public Bullet(float angle, float posX, float posY) {
        int v = 120;
        isExisted = true;
        int accX = (Math.cos(angle)>0)?-4:4;
        int accY = (Math.sin(angle)>0)?-4:4;
        this.acceleration = new Vector2(accX, accY);
        velocity = new Vector2((float)(v*Math.cos(angle/180*Math.PI)), (float)(v*Math.sin(angle/180*Math.PI)));
        position = new Vector2(posX+2, posY+2);
        circle = new Circle(position.x, position.y, 1.5f);
        isHitTarget = false;
    }

    public void update(float delta) {

        position.add(velocity.cpy().scl(delta));
        if(position.x>116||position.x < -1||position.y> Gdx.graphics.getHeight()||position.y<-1){
            isExisted = false;
        }
        circle.set(position.x, position.y, 1.5f);
    }


    public Circle getCircle(){
        return circle;
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
