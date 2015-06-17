package com.fong.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.fong.game.gameworld.GameWorld;

/**
 * Created by wing on 6/16/15.
 */
public class ShootWeaponBall {

    private int type;
    private Vector2 position, velocity;
    private Circle circle;
    private boolean isExisted;

    private float time = 0;
    public ShootWeaponBall(int type, float posX, float posY,Tilt tilt){
        this.type = type;
        this.position = new Vector2(posX, posY);
        this.circle = new Circle(position.x, position.y, 0);
        this.isExisted = true;
        float angle = tilt.getRotation();
        this.velocity = new Vector2((450* MathUtils.cos((float) (angle / 180 * Math.PI))), 450*(MathUtils.sin(((float)(angle / 180 * Math.PI)))));
    }

    public float getX(){
        return position.x;
    }

    public float getY(){
        return position.y;
    }


    public Circle getCircle(){
        return circle ;
    }

    public boolean isExisted(){
        return !(circle.radius<0);
    }

    public void update(float delta,Tilt tilt) {
        time += delta;

        switch (type){
            case 0:
                circle.radius = 150*time-40*time*time;
                break;
            case 1:
                if(time<3) {
                    position.set(tilt.getX()+35, tilt.getY()+35);
                    circle.set(tilt.getX()+35, tilt.getY()+35, 40*time*time);
                }else {
                    position.set(tilt.getX()+35, tilt.getY()+35);
                    circle.set(tilt.getX()+35, tilt.getY()+35, (circle.radius-1*time*time));
                }
                break;
            case 2:
                if(time < 1.5){
                    position.set(tilt.getX()+35, tilt.getY()+35);
                    circle.set(position.x, position.y, 35);
                    if(time > 1.3){
                        float angle = tilt.getRotation();
                        this.velocity = new Vector2((450* MathUtils.cos((float) (angle / 180 * Math.PI))), 450*(MathUtils.sin(((float)(angle / 180 * Math.PI)))));
                    }
                }else {
                    position.add(velocity.cpy().scl(delta));
                    if (position.x > Gdx.graphics.getWidth() - 100 || position.x < -1 || position.y > Gdx.graphics.getHeight() || position.y < -1) {
                        circle.radius = -1;
                        break;
                    }
                    circle.set(position.x, position.y, 150 * (time-1.5f));
                }
                break;
            case 3:
                circle.radius = 150*time-40*time*time;
                break;
            case 4:
                circle.radius = 150*time-40*time*time;
                break;
            default:

        }
    }

    public int getType() {
        return type;
    }
}
