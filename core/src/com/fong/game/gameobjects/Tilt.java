package com.fong.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by wing on 6/13/15.
 */
public class Tilt {

    private Vector2 acceleration;
    private Vector2 velocity;
    private Vector2 position;
    private float rotation;
    private Circle myCircle;
    public Tilt() {
        this.rotation = 0;
        this.acceleration = new Vector2(0,0);
        this.velocity = new Vector2(0,0);
        this.position = new Vector2(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        myCircle = new Circle(position.x, position.y, 3);
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
        float accelY = Gdx.input.getAccelerometerX();
        float accelX = Gdx.input.getAccelerometerY();

        acceleration.set(accelX*80, accelY*80);

        velocity.add(acceleration.cpy().scl(delta));

        if(velocity.y>500)
            velocity.y = 500;
        if(velocity.x>500)
            velocity.x = 500;
        if(velocity.y<-500)
            velocity.y = -500;
        if(velocity.x<-500)
            velocity.x = -500;

        position.add(velocity.cpy().scl(delta));

        if(position.x< 2) {
            position.x = 2;
            velocity.x = 0;
        }
        if(position.x>114) {
            position.x = 114;
            velocity.x = 0;
        }
        if(position.y < 2 ) {
            position.y = 2;
            velocity.y = 0;
        }
        if(position.y>80) {
            position.y = 80;
            velocity.y = 0;
        }

        myCircle.set(position.x, position.y, 3);
    }

    public Circle getCircle() {
        return myCircle;
    }
}
