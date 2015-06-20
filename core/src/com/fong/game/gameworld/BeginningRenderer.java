package com.fong.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.fong.game.InputHelpers.AssetLoader;

/**
 * Created by wing on 6/19/15.
 */
public class BeginningRenderer {

    private float gameHeight = Gdx.graphics.getHeight();
    private float gameWidth = Gdx.graphics.getWidth();
    private GameWorld gameWorld;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera cam;
    private SpriteBatch batcher;

    public BeginningRenderer(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        cam = new OrthographicCamera();
        //first argument: whether the orthographic projection is used or not
        //what the width should be
        //what the height should be
        cam.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batcher = new SpriteBatch();
        //Attach batcher to camera
        batcher.setProjectionMatrix(cam.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
    }

    public void render() {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //Background Color
        shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 60 / 255.0f, 1);
        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        //Start game box
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(gameWidth / 2 - 150 * gameWidth / 1196, gameHeight / 2 - 50 * gameHeight / 768, 300 * gameWidth / 1196, 100 * gameHeight / 768);
        shapeRenderer.end();
        batcher.begin();
        batcher.enableBlending();
        AssetLoader.ConsolasFont.draw(batcher, "Start", gameWidth / 2 - 150 * gameWidth / 1196, gameHeight / 2 - 50 * gameHeight / 768);
        batcher.end();
        //Setting box
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(gameWidth / 2 - 500 * gameWidth / 1196, gameHeight / 2 - 50 * gameHeight / 768, 300 * gameWidth / 1196, 100 * gameHeight / 768);

        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(gameWidth / 2 + 200 * gameWidth / 1196, gameHeight / 2 - 50 * gameHeight / 768, 300 * gameWidth / 1196, 100 * gameHeight / 768);
        shapeRenderer.end();


        batcher.begin();
        batcher.enableBlending();
        AssetLoader.ConsolasFont.draw(batcher, "Setting", gameWidth / 2 - 500 * gameWidth / 1196, gameHeight / 2 - 50 * gameHeight / 768);
        AssetLoader.ConsolasFont.draw(batcher, "Records", gameWidth / 2 + 200 * gameWidth / 1196, gameHeight / 2 - 50 * gameHeight / 768);
        batcher.end();

    }
}
