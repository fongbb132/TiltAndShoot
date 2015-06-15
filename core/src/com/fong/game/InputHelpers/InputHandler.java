package com.fong.game.InputHelpers;

import com.badlogic.gdx.InputProcessor;
import com.fong.game.gameworld.GameWorld;

/**
 * Created by wing on 6/4/15.
 */
public class InputHandler implements InputProcessor{

    private GameWorld gameWorld;
    public InputHandler(GameWorld world) {
        this.gameWorld = world;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(gameWorld.isReady()){
            gameWorld.start();
        }

        if(gameWorld.isGameOver()||gameWorld.isHighScore()){
            gameWorld.restart();
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
