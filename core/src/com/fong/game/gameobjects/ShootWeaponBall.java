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
    private Vector2 position, velocity, tiltPosition;
    private Circle circle;
    private boolean isExploded;

    float angle = 0;

    private float time = 0;
    public ShootWeaponBall(int type, float posX, float posY,Tilt tilt){
        this.type = type;
        this.position = new Vector2(posX, posY);
        this.circle = new Circle(position.x, position.y, 0);
        this.isExploded = false;
        float angle = tilt.getRotation();
        this.tiltPosition = new Vector2(tilt.getX(), tilt.getY());
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
                circle.radius = (150*time-40*time*time)*GameWorld.gameHeight/768;
                break;
            case 1:
                if(time<2) {
                    position.set(tilt.getX()+35*GameWorld.gameWidth/1196, tilt.getY()+35*GameWorld.gameHeight/768);
                    circle.set(tilt.getX()+35*GameWorld.gameWidth/1196, tilt.getY()+35*GameWorld.gameHeight/768,50*GameWorld.gameHeight/768 + 30*time*time*GameWorld.gameHeight/768);
                }else if((time < 4.5 )){
                    position.set(tilt.getX()+35*GameWorld.gameWidth/1196, tilt.getY()+35*GameWorld.gameHeight/768);
                    circle.set(tilt.getX()+35*GameWorld.gameWidth/1196, tilt.getY()+35*GameWorld.gameHeight/768, circle.radius);
                }else {
                    position.set(tilt.getX()+35*GameWorld.gameWidth/1196, tilt.getY()+35*GameWorld.gameHeight/768);
                    circle.set(tilt.getX()+35*GameWorld.gameWidth/1196, tilt.getY()+35*GameWorld.gameHeight/768, circle.radius-(0.8f*time*time)*GameWorld.gameHeight/768);
                }
                break;
            case 2:
                if(time < 1.5){
                    tilt.setIsPressed(true);
                    position.set(tilt.getX()+35*GameWorld.gameWidth/1196, tilt.getY()+35*GameWorld.gameHeight/768);
                    circle.set(position.x, position.y, 35*GameWorld.gameHeight/768);
                    if(time > 1.3){
                        float angle = tilt.getRotation();
                        this.velocity = new Vector2((450* MathUtils.cos((float) (angle / 180 * Math.PI))), 300*(MathUtils.sin(((float)(angle / 180 * Math.PI)))));
                    }
                }else {
                    position.add(velocity.cpy().scl(delta));
                    if (position.x > Gdx.graphics.getWidth() - 100*GameWorld.gameWidth/1196 || position.x < -1 || position.y > Gdx.graphics.getHeight() || position.y < -1) {
                        circle.radius = -1;
                        break;
                    }
                    circle.set(position.x, position.y, 100 * (time-1.5f)*GameWorld.gameHeight/768);
                }
                break;
            case 3:
                float angleTime = time;
                if(time<0.01){
                    angle = tilt.getRotation();
                    tiltPosition.set(tilt.getX(), tilt.getY());
                }
                if(time>2){
                    angleTime = 2;
                }
                angle = (angle+angleTime)%360;
                float radius = (10 + 20 * time)*GameWorld.gameHeight/768;
                if (position.x > Gdx.graphics.getWidth() - 100 || position.x < -1 || position.y > Gdx.graphics.getHeight() || position.y < -1) {
                    circle.radius = -1;
                    break;
                }
                position.set(tiltPosition.x+radius*MathUtils.cos(angle/180*MathUtils.PI),tiltPosition.y+radius*MathUtils.sin(angle/180*MathUtils.PI));
                circle.set(position.x, position.y, 20*GameWorld.gameHeight/768);
                break;
            case 4:
                int i;
                if(time < 1){
                    i = (int)(time*25);
                    if(i%2==0){
                        velocity.set(-velocity.x, -velocity.y);
                    }
                    circle.set(position.x, position.y,25*GameWorld.gameHeight/768);
                }else {
                    isExploded = true;
                    circle.radius = -1;
                }
                position.add(velocity.cpy().scl(delta));
                break;
            default:

        }
    }

    public boolean isExploded(){
        return isExploded;
    }

    public int getType() {
        return type;
    }
}
