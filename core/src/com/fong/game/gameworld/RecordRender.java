package com.fong.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.fong.game.InputHelpers.AssetLoader;

/**
 * Created by wing on 6/25/15.
 */
public class RecordRender {
    private GameWorld gameWorld;
    private ShapeRenderer shapeRenderer;
    private float gameWidth, gameHeight;
    private OrthographicCamera camera;
    private SpriteBatch batcher;

    public RecordRender(GameWorld world){
        this.gameWorld = world;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, GameWorld.gameWidth, GameWorld.gameHeight);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(camera.combined);

        gameWidth = GameWorld.gameWidth;
        gameHeight = GameWorld.gameHeight;

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    public void render(float runTime){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //begin shapeRender
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //Background Color
        shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 60 / 255.0f, 1);
        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer.end();

        batcher.begin();
        batcher.enableBlending();
        batcher.draw(AssetLoader.homeButton, gameWidth / 2 - 300 * gameWidth / 1196, gameHeight / 2 - 100 * gameHeight / 768, 200 * gameWidth / 1196, 200 * gameHeight / 768, 0, 0, 380, 311, false, true);
        batcher.draw(AssetLoader.startButton, gameWidth / 2 + 100 * gameWidth / 1196, gameHeight / 2 - 100 * gameHeight / 768, 200 * gameWidth / 1196, 200 * gameHeight / 768);
        AssetLoader.ConsolasFont.getData().setScale(1.5f);
        AssetLoader.ConsolasFont.draw(batcher, "High Score: " + AssetLoader.getHighScore() + "", GameWorld.gameWidth / 2 - 200 * GameWorld.gameWidth / 1196, GameWorld.gameHeight / 2-150*GameWorld.gameHeight/768);
        batcher.end();

    }

}
