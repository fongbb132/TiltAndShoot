package com.fong.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.fong.game.gameworld.GameWorld;

/**
 * Created by wing on 6/13/15.
 */
public class Tilt {

    private Vector2 acceleration;
    private Vector2 velocity;
    private Vector2 position;
    private float rotation;
    private Circle myCircle;

    public static float AcCorrectionX=0, AcCorrectionY=0;
    public Tilt() {
        this.rotation = 0;
        this.acceleration = new Vector2(0,0);
        this.velocity = new Vector2(0,0);
        this.position = new Vector2(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        myCircle = new Circle(position.x+35*GameWorld.gameWidth/1196, position.y+35*GameWorld.gameHeight/768, 35*GameWorld.gameWidth/1196);
    }

    public float getX(){
        return position.x;
    }

    public float getY(){
        return position.y;
    }

    public float getRotation(){
        return rotation;
    }

    public void setRotation(int deltaR){
        this.rotation+=deltaR;
        rotation%=360;
    }

    public void update(float delta){
        float accelY = Gdx.input.getAccelerometerX()-AcCorrectionX;
        float accelX = Gdx.input.getAccelerometerY()-AcCorrectionY;

        acceleration.set(accelX*500, accelY*500);

        velocity.add(acceleration.cpy().scl(delta));

        if(velocity.y>2500)
            velocity.y = 2500;
        if(velocity.x>2500)
            velocity.x = 2500;
        if(velocity.y<-2500)
            velocity.y = -2500;
        if(velocity.x<-2500)
            velocity.x = -500;

        position.add(velocity.cpy().scl(delta));

        if(position.x< 2) {
            position.x = 2;
            velocity.x = 0;
        }
        if(position.x> GameWorld.gameWidth-140) {
            position.x = GameWorld.gameWidth-140;
            velocity.x = 0;
        }
        if(position.y < 2 ) {
            position.y = 2;
            velocity.y = 0;
        }
        if(position.y > GameWorld.gameHeight-40) {
            position.y = GameWorld.gameHeight-40;
            velocity.y = 0;
        }

        myCircle.set(position.x, position.y, 35*GameWorld.gameHeight/768);
    }

    public Circle getCircle() {
        return myCircle;
    }
}
