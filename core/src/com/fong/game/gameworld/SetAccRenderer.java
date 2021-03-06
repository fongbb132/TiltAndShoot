package com.fong.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.fong.game.InputHelpers.AssetLoader;
import com.fong.game.gameobjects.Tilt;


/**
 * Created by wing on 6/19/15.
 */
public class SetAccRenderer {

    private float gameHeight = Gdx.graphics.getHeight();
    private float gameWidth = GameWorld.gameWidth;
    private GameWorld gameWorld;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera cam;
    private SpriteBatch batcher;
    private Tilt tilt;
    private TextureRegion cursor1;

    public SetAccRenderer(GameWorld world) {

        this.gameWorld = world;
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
        tilt = gameWorld.tilt2;
        cursor1 = AssetLoader.cursorA;

    }

    public void render(){

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //Background Color
        shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 60 / 255.0f, 1);
        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer.end();

        batcher.begin();
        batcher.draw(AssetLoader.backButton, 10 * GameWorld.gameWidth / 1196, 10 * GameWorld.gameHeight / 768, 200 * GameWorld.gameWidth / 1196, 100 * GameWorld.gameHeight / 768, 0, 0, 616, 316, false, true);
        batcher.draw(AssetLoader.adjustButton, 10 * GameWorld.gameWidth / 1196, GameWorld.gameHeight - 110 * GameWorld.gameHeight / 768, 200 * GameWorld.gameWidth / 1196, 100 * GameWorld.gameHeight / 768, 0, 0, 616, 316, false, true);
        if(GameWorld.isButton){
            batcher.draw(AssetLoader.tiltButton, 10 * gameWidth / 1196, gameHeight / 2, 200 * gameWidth / 1196, 100 * gameHeight / 768, 0, 0, 616, 316, false, true);
        }else {
            batcher.draw(AssetLoader.buttonButton, 10 * gameWidth / 1196, gameHeight / 2, 200 * gameWidth / 1196, 100 * gameHeight / 768, 0, 0, 616, 316, false, true);
        }
        batcher.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(310 * GameWorld.gameWidth / 1196, 50 * GameWorld.gameHeight / 768, GameWorld.seekBarX - 310 * GameWorld.gameWidth / 1196, 10);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(GameWorld.seekBarX, 50 * GameWorld.gameHeight / 768, 1110 * GameWorld.gameWidth / 1196 - GameWorld.seekBarX, 10);
        shapeRenderer.setColor(Color.YELLOW);
        shapeRenderer.circle(GameWorld.seekBarX, 55 * GameWorld.gameHeight / 768, 5);
        shapeRenderer.end();

        batcher.begin();
        batcher.enableBlending();
        if(GameWorld.isButton){
            if(gameWorld.fixButtonX>0&&gameWorld.fixButtonY>0){
                batcher.draw(AssetLoader.buttonBackground, gameWorld.fixButtonX-180, gameWorld.fixButtonY-180, 360, 360);
                batcher.draw(AssetLoader.button,gameWorld.buttonX-70, gameWorld.buttonY-70, 140,140 );
            }
        }else {
        }
        batcher.draw(cursor1, tilt.getX(),
                tilt.getY(), 35*GameWorld.gameWidth/1196,
                35 * gameHeight / 768, 70* GameWorld.gameWidth/1196, 70 * gameHeight / 768,
                1, 1, tilt.getRotation());
        batcher.end();
    }
}
