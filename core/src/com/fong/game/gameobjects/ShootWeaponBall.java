package com.fong.game.gameobjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by wing on 6/16/15.
 */
public class ShootWeaponBall {

    private int type;
    private Vector2 position;
    private Circle circle;
    private boolean isExisted;

    public ShootWeaponBall(int type, float posX, float posY){
        this.type = type;
        this.position = new Vector2(posX, posY);
        this.circle = new Circle(position.x, position.y, 0);
        this.isExisted = true;
    }

    public float getX(){
        return position.x;
    }

    public float getY(){
        return position.y;
    }

    public void setCircle(float rad){
        circle.set(position.x, position.y, rad);
    }

    public Circle getCircle(){
        return circle ;
    }

    public boolean isExisted(){
        return circle.radius>4;
    }
}
