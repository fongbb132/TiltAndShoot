package com.fong.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.fong.game.gameobjects.Bullet;
import com.fong.game.gameobjects.Enemy;
import com.fong.game.gameobjects.ScrollHandler;
import com.fong.game.gameobjects.Tilt;
import com.fong.game.gameobjects.WeaponBall;

import java.util.ArrayList;

/**
 * Created by wing on 6/4/15.
 */
public class GameWorld {

    public static int gameWidth = 136;
    public static int gameHeight = 88;
    public enum GameState{
        READY, RUNNING, GAMEOVER, HIGHSCORE
    }

    private int tracking;
    private Tilt tilt;
    private GameState currentState;
    private int score = 0;
    private int midPointY;

    public ArrayList<Bullet> bullets ;
    public ArrayList<Enemy> enemies;
    public ArrayList<WeaponBall> weaponBalls;

    private ScrollHandler scroller;
    private Rectangle ground;
    private boolean isPlay = true;


    public GameWorld(int midPointY) {
        this.tilt = new Tilt();
        this.enemies = new ArrayList<Enemy>();
        currentState = GameState.READY;
        bullets = new ArrayList<Bullet>();
        this.weaponBalls = new ArrayList<WeaponBall>();
    }

    public void update(float delta){
        switch (currentState){
            case READY:

                updateRunning(delta);
                //updateReady(delta);
                break;

            case RUNNING:
                updateRunning(delta);
            default:
                updateRunning(delta);
                break;
        }
    }

    private void updateReady(float delta) {

    }


    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public void start() {
        currentState = GameState.RUNNING;
    }

    public void restart() {
        currentState = GameState.READY;
        score = 0;
        scroller.onRestart();
        currentState = GameState.READY;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public void updateRunning(float delta) {
        tracking++;
        if(delta > .15f){
            delta = .15f;
        }

        for(int a = 0; a< 3; a++) {
            //rotation
            //Gdx.app.log("Game Word",Gdx.input.getX()+" "+Gdx.input.getY()+"down");
            if (isInBorder(Gdx.input.getX(a), Gdx.input.getY(a), 0, Gdx.graphics.getHeight() - 400, 200, 200)&&Gdx.input.isTouched(a)) {
                tilt.setRotation(5);
            }
            if (isInBorder(Gdx.input.getX(a), Gdx.input.getY(a), 0, Gdx.graphics.getHeight() - 200, 200, 200)&&Gdx.input.isTouched(a)) {
                tilt.setRotation(-5);
            }
            if (isInBorder(Gdx.input.getX(a), Gdx.input.getY(a), Gdx.graphics.getWidth() - 140, Gdx.graphics.getHeight() - 140, 140, 140)&&Gdx.input.isTouched(a)) {
                if (tracking % 10 == 0) {
                    bullets.add(new Bullet(tilt.getRotation(), tilt.getX(), tilt.getY()));
                }
            }

        }

        if(tracking%30==0){
            enemies.add(new Enemy((float)(Math.random()*136),(float)(Math.random()*88)));
        }
        if (!enemies.isEmpty()){
            for(int i = 0;i<enemies.size();i++){
                enemies.get(i).update(delta, tilt.getX(), tilt.getY());
            }
        }

        if(weaponBalls.size()<6) {
            if (Math.random() < 0.3) {
                weaponBalls.add(new WeaponBall((float) (Math.random() * 50)));
            }
        }

        if (!bullets.isEmpty()) {
            for (int i = 0; i < bullets.size(); i++) {
                bullets.get(i).update(delta);
                if(!bullets.get(i).isExisted()){
                    bullets.remove(i);
                    i--;
                }
            }
        }

        if(!weaponBalls.isEmpty()){
            for(int i = 0; i<weaponBalls.size();i++){

                weaponBalls.get(i).setHit(tilt);
                for(int numBullet = 0; numBullet<bullets.size();numBullet++){
                    weaponBalls.get(i).setShot(bullets.get(numBullet));
                }
                weaponBalls.get(i).update(delta);
                if(weaponBalls.get(i).isPassed||weaponBalls.get(i).isShot||weaponBalls.get(i).isHit){
                    weaponBalls.remove(i);
                    i--;
                }
            }
        }


        tilt.update(delta);

    }

    public ScrollHandler getScroller() {
        return scroller;
    }

    public Tilt getTilt(){
        return this.tilt;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int increment){
        score += increment;
    }

    public boolean isHighScore(){
        return currentState == GameState.HIGHSCORE;
    }

    public boolean isInBorder(int x, int y, int fromX, int fromY, int width, int height){
        if(x>=fromX && x<=fromX + width && y>=fromY && y<= fromY+height &&Gdx.input.isTouched())
            return true;
        else
            return false;
    }
}
