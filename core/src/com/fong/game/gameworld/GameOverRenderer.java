package com.fong.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.fong.game.InputHelpers.AssetLoader;
import com.fong.game.gameobjects.Bullet;
import com.fong.game.gameobjects.MiniAbsorbBall;
import com.fong.game.gameobjects.ShootWeaponBall;
import com.fong.game.gameobjects.Tilt;
import com.fong.game.gameobjects.WeaponBall;

import java.util.ArrayList;

/**
 * Created by wing on 6/25/15.
 */
public class GameOverRenderer {

    private GameWorld myWorld;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera cam;
    private SpriteBatch batcher;

    private ArrayList<Bullet> bullets;
    private ArrayList<WeaponBall> weaponBalls;
    private int gameHeight, gameWidth;
    private ArrayList<ArrayList<WeaponBall>> weaponList;
    private ArrayList<MiniAbsorbBall> absorbBallArrayList;
    private BitmapFont font;

    public GameOverRenderer(GameWorld myWorld) {
        this.myWorld = myWorld;

        bullets = myWorld.bullets;
        cam = new OrthographicCamera();
        //first argument: whether the orthographic projection is used or not
        //what the width should be
        //what the height should be
        cam.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        gameHeight = GameWorld.gameHeight;
        gameWidth = GameWorld.gameWidth;

        batcher = new SpriteBatch();
        //Attach batcher to camera
        batcher.setProjectionMatrix(cam.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

    }

    public void render(float delta){


        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //Background Color
        shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 60 / 255.0f, 1);
        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(GameWorld.gameWidth / 2 - 250 * gameWidth / 1196, GameWorld.gameHeight / 2, 200 * gameWidth / 1196, 200 * gameHeight / 768);
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(GameWorld.gameWidth / 2 + 50 * gameWidth / 1196, GameWorld.gameHeight / 2, 200 * gameWidth / 1196, 200 * gameHeight / 768);
        shapeRenderer.end();

        batcher.begin();
        batcher.enableBlending();
        AssetLoader.ConsolasFont.getData().setScale(3,3);
        AssetLoader.ConsolasFont.draw(batcher, myWorld.getScore() + "", GameWorld.gameWidth / 2, GameWorld.gameHeight / 2 - 300 * gameHeight / 768);
        AssetLoader.ConsolasFont.draw(batcher,"High Score: "+AssetLoader.getHighScore()+"", GameWorld.gameWidth/2-200*gameWidth/1196, GameWorld.gameHeight/2-150*gameHeight/768);
        batcher.end();

    }

}
