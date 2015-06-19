package com.fong.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.fong.game.gameworld.BeginningRenderer;
import com.fong.game.gameworld.GameRenderer;
import com.fong.game.gameworld.GameWorld;
import com.fong.game.InputHelpers.InputHandler;
import com.fong.game.gameworld.SetAccRenderer;

/**
 * Created by wing on 6/3/15.
 */
public class GameScreen implements Screen {
    private GameWorld world;
    private GameRenderer renderer;
    private BeginningRenderer beginningRenderer;
    private SetAccRenderer setAccRenderer;

    private float runTime = 0;
    private static final String TAG = "GameScreen";

    public GameScreen(){

        float gameHeight = Gdx.graphics.getHeight();

        int midPointY = (int)gameHeight/2;

        world = new GameWorld(midPointY);
        renderer = new GameRenderer(world, (int)gameHeight, midPointY);
        beginningRenderer = new BeginningRenderer(world);
        setAccRenderer = new SetAccRenderer(world);

        Gdx.input.setInputProcessor(new InputHandler(world));

    }
    @Override
    public void show() {
        Gdx.app.log(TAG, "show called");
    }

    @Override
    public void render(float delta) {
        delta/=2;
        runTime += delta;
        world.update(delta);
        if(world.getCurrentState().equals(GameWorld.GameState.READY)){
            beginningRenderer.render();
        }
        else if(world.getCurrentState().equals(GameWorld.GameState.RUNNING) ) {
            renderer.render(runTime);
        }else if(world.getCurrentState().equals(GameWorld.GameState.SETACC)){
            setAccRenderer.render();
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
        Gdx.app.log(TAG, "pause called");

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
