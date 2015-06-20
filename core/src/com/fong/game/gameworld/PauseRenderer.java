package com.fong.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


/**
 * Created by wing on 6/20/15.
 */
public class PauseRenderer {

    private GameWorld gameWorld;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera cam;
    private SpriteBatch batcher;
    private float gameHeight = Gdx.graphics.getHeight();
    private float gameWidth = Gdx.graphics.getWidth();

    public PauseRenderer(GameWorld gameWorld){
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

    public void render(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(gameWidth / 2 - 300 * gameWidth / 1196, gameHeight / 2 - 100 * gameHeight / 768, 200 * gameWidth / 1196, 200 * gameHeight / 768);
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(gameWidth/2+ 100 *gameWidth/1196, gameHeight/2-100*gameHeight/768, 200*gameWidth/1196, 200*gameHeight/768);
        shapeRenderer.end();

    }
}
