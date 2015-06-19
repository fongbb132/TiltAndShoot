package com.fong.game.gameobjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.fong.game.gameworld.GameWorld;

/**
 * Created by wing on 6/18/15.
 */
public class MiniAbsorbBall {

    private float oriX, oriY,time, rotation;
    private Vector2 position, velocity;
    private Circle circle;
    private boolean isExisted;

    public MiniAbsorbBall(float oriX, float oriY,float angle) {
        this.oriX = oriX;
        this.oriY = oriY;
        this.rotation = MathUtils.random()*360;
        position = new Vector2(oriX, oriY);
        velocity = new Vector2(MathUtils.cosDeg(angle)*80, MathUtils.sinDeg(angle)*80);
        circle = new Circle(position.x, position.y, 25* GameWorld.gameHeight/768);
        isExisted = true;
    }

    public void update(float delta){
        time+=delta;
        rotation = (rotation+3)%360;
        if(Math.sqrt((double)(position.x-oriX)*(position.x-oriX)+(position.y-oriY)*(position.y-oriY))<150*GameWorld.gameHeight/768){
            position.add(velocity.cpy().scl(delta));
            circle.set(position.x, position.y, 25*GameWorld.gameHeight/768);
        }

        if(time>8){
            isExisted = false;
        }

    }
    public float getRotation(){
        return rotation;
    }

    public boolean isExisted(){
        return isExisted;
    }

    public float getPosX(){
        return position.x;
    }

    public float getPosY(){
        return position.y;
    }

    public boolean isInRange(Enemy enemy){
        boolean isInRange = false;
        if(Math.sqrt((double)(position.x-enemy.getX())*(position.x-enemy.getX())-(position.y-enemy.getY())*(position.y-enemy.getY()))<50*GameWorld.gameHeight/768){
            isInRange = true;
        }
        return isInRange;
    }

    public Circle getCircle() {
        return circle;
    }
}
