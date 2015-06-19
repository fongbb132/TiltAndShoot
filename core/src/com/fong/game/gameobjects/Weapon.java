package com.fong.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.fong.game.gameworld.GameWorld;

/**
 * Created by wing on 6/14/15.
 */
public class Weapon {

    private Vector2 velocity, position, acceleration;
    private boolean isExisted;

    public float v;
    public Weapon(float angle,float posX, float posY){
        this.v = 120;
        int accX = (Math.cos(angle)>0)?-4:4;
        int accY = (Math.sin(angle)>0)?-4:4;
        this.acceleration = new Vector2(accX, accY);
        velocity = new Vector2((float)(v*Math.cos(angle/180*Math.PI)), (float)(v*Math.sin(angle/180*Math.PI)));
        position = new Vector2(posX+2* GameWorld.gameWidth/1196, posY+2*GameWorld.gameHeight/768);
        isExisted = true;
    }

    public void update(float delta){

        /*
        velocity.x = (velocity.x>0)?(float)(velocity.x - 0.04) : (float)(velocity.x+0.04);
        velocity.y = (velocity.y>0)?(float)(velocity.y - 0.04) : (float)(velocity.y+0.04);
        position.add(velocity.cpy().scl(delta));

        if (position.x < 4 || position.x > Gdx.graphics.getWidth()/Gdx.graphics.getWidth()*136 - 4) {
            velocity.x = (velocity.x>0)?(float)(velocity.x - 2) : (float)(velocity.x+2);
            velocity.x = -velocity.x;
        }
        if (position.y < 4 || position.y > Gdx.graphics.getHeight()/Gdx.graphics.getHeight()*88 - 4) {
            velocity.y = (velocity.y>0)?(float)(velocity.y - 2) : (float)(velocity.y+2);
            velocity.y = -velocity.y;
        }

        if(Math.abs(velocity.x)<4){
            velocity.x = 0;
        }
        if(Math.abs(velocity.y)<4){
            velocity.y = 0;
        }
        */
        position.add(velocity.cpy().scl(delta));
        if(position.x>116||position.x < -1||position.y>Gdx.graphics.getHeight()||position.y<-1){
            isExisted = false;
        }
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
