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
import com.fong.game.gameobjects.Bullet;
import com.fong.game.InputHelpers.AssetLoader;
import com.fong.game.gameobjects.MiniAbsorbBall;
import com.fong.game.gameobjects.ShootWeaponBall;
import com.fong.game.gameobjects.Tilt;
import com.fong.game.gameobjects.WeaponBall;

import java.util.ArrayList;


/**
 * Created by wing on 6/4/15.
 */
public class GameRenderer {

    private GameWorld myWorld;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera cam;
    private SpriteBatch batcher;

    private Tilt tilt;

    private TextureRegion cursor1,cursor2,bullet;

    private ArrayList<Bullet> bullets;
    private ArrayList<WeaponBall> weaponBalls;
    private int gameHeight, gameWidth;
    private ArrayList<ArrayList<WeaponBall>> weaponList;
    private ArrayList<MiniAbsorbBall> absorbBallArrayList;

    public GameRenderer(GameWorld world, int gameHeight, int midPointY) {

        this.myWorld = world;

        bullets = myWorld.bullets;
        this.gameHeight = gameHeight;
        gameWidth = GameWorld.gameWidth;
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

        initGameObjects();
        initAssets();

    }

    public void render(float runTime) {
        bullets = myWorld.bullets;

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        weaponBalls = myWorld.weaponBalls;
        weaponList = myWorld.getWeaponList();
        //begin shapeRender
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //Background Color
        shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 60 / 255.0f, 1);
        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer.end();

        //rotation circles
        batcher.begin();
        batcher.enableBlending();
        //Moving button

        if(!GameWorld.isButton){
            if(myWorld.antiClock){
                batcher.draw(AssetLoader.clock2, 0, GameWorld.gameHeight - (200*gameHeight/768),200*gameWidth/1196,200*gameHeight/768);
            }else{
                batcher.draw(AssetLoader.clock1, 0, GameWorld.gameHeight - (200*gameHeight/768),200*gameWidth/1196,200*gameHeight/768);
            }

            if(myWorld.clock){
                batcher.draw(AssetLoader.antiClock2, 0, GameWorld.gameHeight - (400*gameHeight/768),200*gameWidth/1196,200*gameHeight/768);
            }else {
                batcher.draw(AssetLoader.antiClock1, 0, GameWorld.gameHeight - (400*gameHeight/768),200*gameWidth/1196,200*gameHeight/768);
            }

            if(myWorld.time<0.2){
                batcher.draw(AssetLoader.shoot2, Gdx.graphics.getWidth() - 140, Gdx.graphics.getHeight() - 140, 140, 140);
            }else {
                batcher.draw(AssetLoader.shoot1, Gdx.graphics.getWidth() - 140, Gdx.graphics.getHeight() - 140, 140, 140);
            }
        }else {
            if(myWorld.fixButtonX>0&&myWorld.fixButtonY>0){
                batcher.draw(AssetLoader.buttonBackground, myWorld.fixButtonX-140, myWorld.fixButtonY-140, 280, 280);
                batcher.draw(AssetLoader.button,myWorld.buttonX-70, myWorld.buttonY-70, 140,140 );
            }

            if(myWorld.fixShootButtonX>0) {
                batcher.draw(AssetLoader.buttonBackground, myWorld.fixShootButtonX-140, myWorld.fixShootButtonY-140, 280, 280);
                batcher.draw(AssetLoader.button,myWorld.shootButtonX-70, myWorld.shootButtonY-70, 140, 140);
            }
        }
        batcher.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

            if (!myWorld.enemies.isEmpty()) {
                for (int i = 0; i < myWorld.enemies.size(); i++) {
                    if(myWorld.enemies.get(i).canKill) {
                        shapeRenderer.setColor(130 / 255.0f, 30 / 255.0f, 160 / 255.0f, 1);
                    }else {
                        shapeRenderer.setColor(Color.WHITE);
                    }
                    shapeRenderer.rect(myWorld.enemies.get(i).getX(), myWorld.enemies.get(i).getY(), 20*gameWidth/1196, 20*gameHeight/768);
                }
            }
            if (!weaponBalls.isEmpty()) {
                for (int i = 0; i < weaponBalls.size(); i++) {

                    switch (weaponBalls.get(i).getType()) {
                        case 0:
                            shapeRenderer.setColor(215 / 255.0f, 77 / 255.0f, 17 / 255.0f, 1);
                            break;
                        case 1:
                            shapeRenderer.setColor(0 / 255.0f, 204 / 255.0f, 0 / 255.0f, 1);
                            break;
                        case 2:
                            shapeRenderer.setColor(51 / 255.0f, 153 / 255.0f, 255 / 255.0f, 1);
                            break;
                        case 3:
                            shapeRenderer.setColor(204 / 255.0f, 0 / 255.0f, 102 / 255.0f, 1);
                            break;
                        case 4:
                            shapeRenderer.setColor(102 / 255.0f, 0 / 255.0f, 102 / 255.0f, 1);
                            break;
                        default:
                            break;

                    }
                    shapeRenderer.circle(weaponBalls.get(i).getX(), weaponBalls.get(i).getY(), 25*gameHeight/768);
                }
            }
        releaseWeaponBall();

        drawAbsorbingBalls();

        drawWeaponCircles();
        //Pause Circle
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(GameWorld.gameWidth - 150 * gameWidth / 1196, 60 * gameHeight / 768, 50);

        shapeRenderer.end();

        batcher.begin();
        batcher.enableBlending();
        if (!bullets.isEmpty()) {
            for (int i = 0; i < bullets.size(); i++) {
                batcher.draw(bullet, bullets.get(i).getX(), bullets.get(i).getY(), 10*gameWidth/1196,10*gameHeight/768, 20*gameWidth/1196,20*gameHeight/768, 1, 1, 0f);
            }
        }
        batcher.end();

        batcher.begin();
        batcher.enableBlending();

        AssetLoader.ConsolasFont.getData().setScale(2, 2);
        for (int num = 0; num < weaponList.size(); num++) {
            AssetLoader.ConsolasFont.draw(batcher, "x" + weaponList.get(num).size(), GameWorld.gameWidth - 120*GameWorld.gameWidth/1196, GameWorld.gameHeight - (240 + num * 120)*GameWorld.gameHeight/768, 100, 100, false);
        }

        AssetLoader.ConsolasFont.draw(batcher, GameWorld.score + "", 30 * GameWorld.gameWidth / 1196, 30 * GameWorld.gameHeight / 768);

        if(!tilt.getIsPressed()) {
            batcher.draw(cursor1, tilt.getX(),
                    tilt.getY(), 35*gameWidth/1196,
                    35 * gameHeight / 768, 70*gameWidth/1196, 70 * gameHeight / 768,
                    1, 1, tilt.getRotation());
        }else{
            batcher.draw(cursor2, tilt.getX(),
                    tilt.getY(), 35*gameWidth/1196,
                    35 * gameHeight / 768, 70*gameWidth/1196, 70 * gameHeight / 768,
                    1, 1, tilt.getRotation());
        }
        batcher.end();

    }

    private void initGameObjects(){
        tilt = myWorld.getTilt();
    }

    private void initAssets(){
        cursor1 = AssetLoader.cursorA;
        cursor2 = AssetLoader.cursorB;
        bullet = AssetLoader.bullet;
    }

    private void drawAbsorbingBalls(){
        absorbBallArrayList = myWorld.absorbBallsList;
        for(int i = 0; i<absorbBallArrayList.size();i++){
            MiniAbsorbBall ball = absorbBallArrayList.get(i);
            shapeRenderer.setColor(102 / 255.0f, 0 / 255.0f, 102 / 255.0f, 1);
            shapeRenderer.circle(ball.getPosX(), ball.getPosY(), 25*GameWorld.gameHeight/768);
        }

    }

    private void releaseWeaponBall(){
        for(int numBall = 0; numBall<myWorld.shootWeaponBallArrayList.size();numBall++){
            ShootWeaponBall ball = myWorld.shootWeaponBallArrayList.get(numBall);
            switch (ball.getType()){
                case 0:
                    shapeRenderer.setColor(215 / 255.0f, 77 / 255.0f, 17 / 255.0f, 1);
                    break;
                case 1:
                    shapeRenderer.setColor(0 / 255.0f, 204 / 255.0f, 0 / 255.0f, 1);
                    break;
                case 2:
                    shapeRenderer.setColor(51 / 255.0f, 153 / 255.0f, 255 / 255.0f, 1);
                    break;
                case 3:
                    shapeRenderer.setColor(204 / 255.0f, 0 / 255.0f, 102 / 255.0f, 1);
                    break;
                case 4:
                    shapeRenderer.setColor(102 / 255.0f, 0 / 255.0f, 102 / 255.0f, 1);
                    break;
                default:
                    break;
            }
            shapeRenderer.circle(ball.getX() , ball.getY() , ball.getCircle().radius*gameHeight/768,40 );
        }
    }

    private void drawWeaponCircles(){

        for (int num = 0; num < weaponList.size(); num++) {
            if (!weaponList.get(num).isEmpty()) {

                switch (weaponList.get(num).get(0).getType()) {
                    case 0:
                        shapeRenderer.setColor(215 / 255.0f, 77 / 255.0f, 17 / 255.0f, 1);
                        break;
                    case 1:
                        shapeRenderer.setColor(0 / 255.0f, 204 / 255.0f, 0 / 255.0f, 1);
                        break;
                    case 2:
                        shapeRenderer.setColor(51 / 255.0f, 153 / 255.0f, 255 / 255.0f, 1);
                        break;
                    case 3:
                        shapeRenderer.setColor(204 / 255.0f, 0 / 255.0f, 102 / 255.0f, 1);
                        break;
                    case 4:
                        shapeRenderer.setColor(102 / 255.0f, 0 / 255.0f, 102 / 255.0f, 1);
                        break;
                    default:

                }
                switch (num) {
                    case 0:
                        shapeRenderer.circle(GameWorld.gameWidth-60*gameWidth/1196, GameWorld.gameHeight - 180*gameHeight/768,60*gameHeight/768, 20);
                        break;
                    case 1:
                        shapeRenderer.circle(GameWorld.gameWidth-60*gameWidth/1196, GameWorld.gameHeight - 300*gameHeight/768, 60*gameHeight/768, 20);
                        break;
                    case 2:
                        shapeRenderer.circle(GameWorld.gameWidth-60*gameWidth/1196, GameWorld.gameHeight - 420*gameHeight/768, 60*gameHeight/768, 20);
                        break;
                    case 3:
                        shapeRenderer.circle(GameWorld.gameWidth-60*gameWidth/1196, GameWorld.gameHeight - 540*gameHeight/768, 60*gameHeight/768, 20);
                        break;
                    case 4:
                        shapeRenderer.circle(GameWorld.gameWidth-60*gameWidth/1196, GameWorld.gameHeight - 660*gameHeight/768, 60*gameHeight/768, 20);
                        break;
                    default:

                }

            }

        }
    }
}
