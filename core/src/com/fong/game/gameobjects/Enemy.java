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

    public Vector2 velocity, position, destination;
    private boolean isExisted;
    public Circle myCircle;
    private boolean isSpecial;
    private boolean isArrive;
    public float time;
    public boolean canKill;
    public Enemy(float posX, float posY){
        float velX = MathUtils.random()*200;
        float velY = MathUtils.random()*200;
        velocity = new Vector2(velX, velY);
        position = new Vector2(posX, posY);
        isExisted = true;
        myCircle = new Circle(position.x+10*GameWorld.gameWidth/1196, position.y+10*GameWorld.gameHeight/768, 1);
        isSpecial = false;
        isArrive = false;
        time = 0;
        canKill = false;
    }

    public void update(float delta, float posX, float posY){
        time+=delta;
        canKill = time>2;

        if(!canKill){
            velocity.set(0,0);
        }else if(time<2.1){
            velocity.set(40,40);
        }else {
            if (!isSpecial) {
                if (!isArrive) {
                    velocity.x = (position.x < posX + 15) ? Math.abs(velocity.x) : -Math.abs(velocity.x);
                    velocity.y = (position.y < posY + 15) ? Math.abs(velocity.y) : -Math.abs(velocity.y);
                }
                position.add(velocity.cpy().scl(delta));
            } else {
                if (destination.x - 1.5 < getX() && getX() < destination.x + 1.5) {
                    velocity.x = 0;
                } else {
                    velocity.x = (position.x < destination.x) ? Math.abs(velocity.x) : -Math.abs(velocity.x);
                }
                if (destination.y - 1.5 < getY() && getY() < destination.y + 1.5) {
                    velocity.y = 0;
                } else {
                    velocity.y = (position.y < destination.y) ? Math.abs(velocity.y) : -Math.abs(velocity.y);
                }
            }
        }
        position.add(velocity.cpy().scl(delta));

        if(destination!=null && velocity.x==0 && velocity.y==0){
            isArrive = true;
        }

        if (position.x < 1 || position.x > GameWorld.gameWidth-140*GameWorld.gameWidth/1196) {
            velocity.x = -velocity.x;
        }
        if (position.y < 1 || position.y > GameWorld.gameHeight) {
            velocity.y = -velocity.y;
        }

        myCircle.set(position.x+10*GameWorld.gameWidth/1196, position.y+10*GameWorld.gameHeight/768, 10*GameWorld.gameHeight/768);

    }

    public boolean isOverlap(Circle c){
        if(myCircle.overlaps(c))
            isExisted = false;
        return isExisted;
    }

    public void setDestination(float x, float y){
        destination = new Vector2(x, y);
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

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setSpecial(boolean b){
        this.isSpecial = b;
    }

    public void setIsArrive(){
        this.isArrive = true;
    }

    public boolean getArrive(){
        return isArrive;
    }

    public boolean getSpecial(){
        return isSpecial;
    }

    public Vector2 getDestination(){
        return destination;
    }

    public void addTime(float delta){
        time+=delta;
    }
    public boolean isCanKill(){
        return canKill;
    }
}
