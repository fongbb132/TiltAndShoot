package com.fong.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.fong.game.gameworld.GameWorld;

/**
 * Created by wing on 6/15/15.
 */
public class GeneralEnemy extends Enemy {
    public GeneralEnemy(float posX, float posY) {
        super(posX, posY);
        this.velocity.set(5,5);
    }

    @Override
    public void update(float delta, float posX, float posY) {

        if(!getSpecial()) {
            position.add(velocity.cpy().scl(delta));
        }else{
            if(destination.x-0.5<getX()&&getX()<destination.x+0.5){
                velocity.x = 0;
            }else {
                velocity.x = (position.x<destination.x) ? Math.abs(velocity.x) : -Math.abs(velocity.x);
            }
            if(destination.y-0.5<getY()&&getY()<destination.y+0.5) {
                velocity.y = 0;
            }
            else {
                velocity.y = (position.y<destination.y) ? Math.abs(velocity.y) : -Math.abs(velocity.y);
            }
        }
        myCircle.setRadius(5);
        if(destination!=null&&myCircle.contains(destination.x, destination.y)){
            velocity.set(0,0);
            setIsArrive();
        }
        if (position.x < 1 || position.x > GameWorld.gameWidth-140) {
            velocity.x = -velocity.x;
        }
        if (position.y < 1 || position.y > GameWorld.gameHeight) {
            velocity.y = -velocity.y;
        }
        myCircle.set(position.x+10, position.y+10, 10*GameWorld.gameHeight/768);
        position.add(velocity.cpy().scl(delta));
    }
}
