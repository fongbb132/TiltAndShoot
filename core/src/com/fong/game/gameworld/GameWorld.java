package com.fong.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.fong.game.gameobjects.Bullet;
import com.fong.game.gameobjects.Enemy;
import com.fong.game.gameobjects.GeneralEnemy;
import com.fong.game.gameobjects.ScrollHandler;
import com.fong.game.gameobjects.Tilt;
import com.fong.game.gameobjects.Weapon;
import com.fong.game.gameobjects.WeaponBall;

import java.util.ArrayList;

/**
 * Created by wing on 6/4/15.
 */
public class GameWorld {

    public static int gameWidth = 124;
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
    public ArrayList<ArrayList<WeaponBall>> weaponList;

    private ScrollHandler scroller;
    private Rectangle ground;
    private boolean isPlay = true;


    public GameWorld(int midPointY) {
        this.tilt = new Tilt();
        this.enemies = new ArrayList<Enemy>();
        currentState = GameState.READY;
        bullets = new ArrayList<Bullet>();
        this.weaponBalls = new ArrayList<WeaponBall>();
        this.weaponList = new ArrayList<ArrayList<WeaponBall>>(5);
        for(int i = 0; i< 5;i++){
            ArrayList arrayList = new ArrayList();
            weaponList.add(arrayList);
        }
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

        if(tracking%40==0){
            double prob = Math.random();
            Enemy a;
            if(prob<0.25){
                a = new Enemy((float)Math.random()*GameWorld.gameWidth-5, (float)Math.random()*GameWorld.gameHeight);
            }else{
                a = new GeneralEnemy((float)Math.random()*GameWorld.gameWidth-5, (float)Math.random()*GameWorld.gameHeight);
            }
            enemies.add(a);
        }

        if (!enemies.isEmpty()){
            for(int i = 0;i<enemies.size();i++){
                for(int numBul = 0; numBul<bullets.size();numBul++){
                    enemies.get(i).isOverlap(bullets.get(numBul).getCircle());
                    if(!enemies.get(i).isExisted()){
                        bullets.get(numBul).setIsExisted();
                    }
                }
                enemies.get(i).update(delta, tilt.getX(), tilt.getY());
                if(!enemies.get(i).isExisted()){
                    enemies.remove(i);
                    i--;
                }
            }
        }

        if(weaponBalls.size()<4 ) {
            if (Math.random() < 0.4 && tracking%120==0) {
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

        //weaponball detection
        if(!weaponBalls.isEmpty()){
            for(int i = 0; i<weaponBalls.size();i++){
                weaponBalls.get(i).setHit(tilt);
                for(int numBullet = 0; numBullet<bullets.size();numBullet++){
                    weaponBalls.get(i).setShot(bullets.get(numBullet));
                    if(weaponBalls.get(i).isShot){
                        bullets.get(numBullet).setIsExisted();
                    }
                }
                weaponBalls.get(i).update(delta);
                if(weaponBalls.get(i).isPassed||weaponBalls.get(i).isShot||weaponBalls.get(i).isHit){
                    WeaponBall ball = weaponBalls.remove(i);
                    if(ball.isShot) {
                        ArrayList<WeaponBall> list = null;
                        for (int num = 0; num < weaponList.size(); num++) {
                            if (weaponList.get(num).isEmpty()) {
                                list = weaponList.get(num);
                                break;
                            } else {
                                if ((weaponList.get(num).get(0).getType() == ball.getType())) {
                                    list = weaponList.get(num);
                                    break;
                                }
                            }
                        }
                        list.add(ball);
                    }
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

    public ArrayList getWeaponList(){
        return weaponList;
    }
}
