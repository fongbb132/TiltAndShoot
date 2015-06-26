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
    private OrthographicCamera camera;
    private SpriteBatch batcher;

    public RecordRender(GameWorld world){
        this.gameWorld = world;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, GameWorld.gameWidth, GameWorld.gameHeight);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(camera.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    public void render(float runTime){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //Background Color
        shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        shapeRenderer.rect(0, 0, GameWorld.gameWidth, GameWorld.gameHeight);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(GameWorld.gameWidth / 2 - 250 * GameWorld.gameWidth / 1196, GameWorld.gameHeight / 2, 200 * GameWorld.gameWidth / 1196, 200 * GameWorld.gameHeight / 768);
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(GameWorld.gameWidth / 2 + 50 * GameWorld.gameWidth / 1196, GameWorld.gameHeight / 2, 200 * GameWorld.gameWidth / 1196, 200 * GameWorld.gameHeight / 768);
        shapeRenderer.end();


        batcher.begin();
        batcher.enableBlending();
        AssetLoader.ConsolasFont.getData().setScale(3, 3);
        AssetLoader.ConsolasFont.draw(batcher, "High Score: " + AssetLoader.getHighScore() + "", GameWorld.gameWidth / 2 - 200 * GameWorld.gameWidth / 1196, GameWorld.gameHeight / 2-150*GameWorld.gameHeight/768);
        batcher.end();

    }

}
