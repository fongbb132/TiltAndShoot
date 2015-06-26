package com.fong.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.fong.game.gameworld.BeginningRenderer;
import com.fong.game.gameworld.GameOverRenderer;
import com.fong.game.gameworld.GameRenderer;
import com.fong.game.gameworld.GameWorld;
import com.fong.game.InputHelpers.InputHandler;
import com.fong.game.gameworld.PauseRenderer;
import com.fong.game.gameworld.RecordRender;
import com.fong.game.gameworld.SetAccRenderer;

/**
 * Created by wing on 6/3/15.
 */
public class GameScreen implements Screen {
    private GameWorld world;
    private GameRenderer renderer;
    private BeginningRenderer beginningRenderer;
    private SetAccRenderer setAccRenderer;
    private PauseRenderer pauseRenderer;
    private GameOverRenderer gameOverRenderer;
    private RecordRender recordRender;
    private float runTime = 0;
    private float timeRec = 0;

    public GameScreen(){

        float gameHeight = Gdx.graphics.getHeight();

        int midPointY = (int)gameHeight/2;

        world = new GameWorld(midPointY);
        renderer = new GameRenderer(world, (int)gameHeight, midPointY);
        beginningRenderer = new BeginningRenderer(world);
        setAccRenderer = new SetAccRenderer(world);
        pauseRenderer = new PauseRenderer(world);
        gameOverRenderer = new GameOverRenderer(world);
        recordRender = new RecordRender(world);

        Gdx.input.setInputProcessor(new InputHandler(world));

    }
    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        runTime += delta;
        world.update(delta);
        if(world.getCurrentState().equals(GameWorld.GameState.READY)){
            beginningRenderer.render();
            runTime = 0;
        }
        else if(world.getCurrentState().equals(GameWorld.GameState.RUNNING) ) {
            renderer.render(runTime);
            timeRec = runTime;
        }else if(world.getCurrentState().equals(GameWorld.GameState.SETACC)){
            setAccRenderer.render();
        }else if(world.getCurrentState().equals(GameWorld.GameState.PAUSE)){
            pauseRenderer.render();
        }else if(world.getCurrentState().equals(GameWorld.GameState.GAMEOVER)){
            if(runTime-timeRec<1) {
                renderer.render(delta);
            }else {
                gameOverRenderer.render(delta);
            }
        }else if(world.getCurrentState().equals(GameWorld.GameState.HIGHSCORE)){
            recordRender.render(runTime);
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        world.currentState = GameWorld.GameState.PAUSE;
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
