package com.fong.game.gameobjects;


import com.fong.game.gameworld.GameWorld;

/**
 * Created by wing on 6/5/15.
 */
public class ScrollHandler {
    private GameWorld gameWorld;

    public static final int SCROLL_SPEED = -59;
    public static final int PIPE_GAP = 49;

    public ScrollHandler(GameWorld gameWorld, float yPos){
        this.gameWorld = gameWorld;

    }

    public void update(float delta){
    }
/*
    public boolean collides(Bird bird) {
    }
*/

    public void stop() {


    }

    private void addScore(int increment){
        gameWorld.addScore(increment);
    }

    public void onRestart() {

    }
}
