package com.fong.game.gameobjects;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.fong.game.gameworld.GameWorld;

/**
 * Created by wing on 6/15/15.
 */
public class WeaponBall {


    private Vector2 velocity, position;
    public boolean isPassed, isShot, isHit;
    public int type;
    private Circle myCirle;

    public WeaponBall(float velocity){
        this.type = (int)(MathUtils.random()*5);
        double prob = MathUtils.random();
        if(prob>0.25){
            position = new Vector2(0, (float)(MathUtils.random()* GameWorld.gameHeight));
            this.velocity = new Vector2(velocity, 0);
        }else if(prob>0.5){
            position = new Vector2((float)(MathUtils.random()*GameWorld.gameWidth-120), 0);
            this.velocity = new Vector2(0, velocity);
        }else if(prob>0.75){
            position = new Vector2(114, (float)(MathUtils.random()* GameWorld.gameHeight));
            this.velocity = new Vector2(-velocity, 0);
        }else{
            position = new Vector2((float)(MathUtils.random()*GameWorld.gameWidth-120), GameWorld.gameHeight);
            this.velocity = new Vector2(0, -velocity);
        }
        isPassed = false;
        isShot = false;
        isHit = false;
        myCirle = new Circle(position.x, position.y, 25*GameWorld.gameHeight/768);
    }

    public void update(float delta){
        position.add(velocity.cpy().scl(delta));
        if(position.x> GameWorld.gameWidth-120||position.x < -1||position.y>Gdx.graphics.getHeight()||position.y<-1){
            isPassed = true;
        }
        myCirle.set(position.x, position.y, 25*GameWorld.gameHeight/768);

    }

    public float getX(){
        return position.x;
    }

    public float getY(){
        return position.y;
    }

    public boolean isPassed() {
        return isPassed;
    }

    public boolean isShot() {
        return isShot;
    }

    public void setShot(Bullet bullet){
        if(myCirle.overlaps(bullet.getCircle())) {
            isShot = true;
        }
    }

    public void setHit(Tilt tile){
        if(myCirle.overlaps(tile.getCircle()))
            isHit = true;
    }

    public int getType(){
        return this.type;
    }
}
