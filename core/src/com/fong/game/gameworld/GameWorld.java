package com.fong.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.fong.game.gameobjects.Bullet;
import com.fong.game.gameobjects.Enemy;
import com.fong.game.gameobjects.GeneralEnemy;
import com.fong.game.gameobjects.ShootWeaponBall;
import com.fong.game.gameobjects.Tilt;
import com.fong.game.gameobjects.Weapon;
import com.fong.game.gameobjects.WeaponBall;

import java.util.ArrayList;

/**
 * Created by wing on 6/4/15.
 */
public class GameWorld {

    public static int score=0;
    public static int gameWidth = Gdx.graphics.getWidth();
    public static int gameHeight = Gdx.graphics.getHeight();
    public enum GameState{
        READY, RUNNING, GAMEOVER, HIGHSCORE
    }

    private int tracking;
    private Tilt tilt;
    private GameState currentState;
    private int midPointY;

    public ArrayList<Bullet> bullets ;
    public ArrayList<Enemy> enemies;
    public ArrayList<WeaponBall> weaponBalls;
    public ArrayList<ArrayList<WeaponBall>> weaponList;
    public ArrayList<ShootWeaponBall> shootWeaponBallArrayList;
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
        shootWeaponBallArrayList = new ArrayList<ShootWeaponBall>();
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
        currentState = GameState.READY;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public void updateRunning(float delta) {
        tracking++;
        Gdx.app.log("GameWorld", gameWidth + " "+gameHeight);
        if(delta > .15f){
            delta = .15f;
        }

        for(int a = 0; a< 3; a++) {
            //rotation
            //Gdx.app.log("Game Word",Gdx.input.getX()+" "+Gdx.input.getY()+"down");
            if (isInBorder(Gdx.input.getX(a), Gdx.input.getY(a), 0, Gdx.graphics.getHeight() - 400*gameHeight/768, 200*gameWidth/768, 200*gameHeight/768)&&Gdx.input.isTouched(a)) {
                tilt.setRotation(5);
            }
            if (isInBorder(Gdx.input.getX(a), Gdx.input.getY(a), 0, Gdx.graphics.getHeight() - 200*gameHeight/768, 200*gameWidth/768, 200*gameHeight/768)&&Gdx.input.isTouched(a)) {
                tilt.setRotation(-5);
            }
            //the weapon balls
            if (isInBorder(Gdx.input.getX(a), Gdx.input.getY(a), Gdx.graphics.getWidth() - 120*gameWidth/768, Gdx.graphics.getHeight() - 240*gameHeight/768, 140*gameWidth/768, 140*gameHeight/768)&&Gdx.input.isTouched(a)) {
                if(!weaponList.get(0).isEmpty()&&tracking%7 == 0) {
                    WeaponBall weaponBall = weaponList.get(0).remove(0);
                    shootWeaponBallArrayList.add(new ShootWeaponBall(weaponBall.getType(), tilt.getX(), tilt.getY(), tilt));
                }
                if(weaponList.get(0).isEmpty()) {
                    weaponList.remove(0);
                    weaponList.add(new ArrayList<WeaponBall>());
                }
            }
            if (isInBorder(Gdx.input.getX(a), Gdx.input.getY(a), Gdx.graphics.getWidth() - 120*gameWidth/768, Gdx.graphics.getHeight() - 360*gameHeight/768, 140*gameWidth/768, 140*gameHeight/768)&&Gdx.input.isTouched(a)) {
                if(!weaponList.get(1).isEmpty()&&tracking%7 == 0) {
                    WeaponBall weaponBall = weaponList.get(1).remove(0);
                    shootWeaponBallArrayList.add(new ShootWeaponBall(weaponBall.getType(), tilt.getX(), tilt.getY(), tilt));
                }
                if(weaponList.get(1).isEmpty()) {
                    weaponList.remove(1);
                    weaponList.add(new ArrayList<WeaponBall>());
                }
            }
            if (isInBorder(Gdx.input.getX(a), Gdx.input.getY(a), Gdx.graphics.getWidth() - 120*gameWidth/768, Gdx.graphics.getHeight() - 480*gameHeight/768, 140*gameWidth/768, 140*gameHeight/768)&&Gdx.input.isTouched(a)) {
                if(!weaponList.get(2).isEmpty()&&tracking%7 == 0) {
                WeaponBall weaponBall = weaponList.get(2).remove(0);
                shootWeaponBallArrayList.add(new ShootWeaponBall(weaponBall.getType(), tilt.getX(), tilt.getY(), tilt));
                }
                if(weaponList.get(2).isEmpty()) {
                    weaponList.remove(2);
                    weaponList.add(new ArrayList<WeaponBall>());
                }
            }
            if (isInBorder(Gdx.input.getX(a), Gdx.input.getY(a), Gdx.graphics.getWidth() - 140*gameWidth/768, Gdx.graphics.getHeight() - 600*gameHeight/768, 140*gameWidth/768, 140*gameHeight/768)&&Gdx.input.isTouched(a)) {
                if (!weaponList.get(3).isEmpty() && tracking % 7 == 0) {
                    WeaponBall weaponBall = weaponList.get(3).remove(0);
                    shootWeaponBallArrayList.add(new ShootWeaponBall(weaponBall.getType(), tilt.getX(), tilt.getY(), tilt));
                }
                if(weaponList.get(3).isEmpty()) {
                    weaponList.remove(3);
                    weaponList.add(new ArrayList<WeaponBall>());
                }
            }
            if (isInBorder(Gdx.input.getX(a), Gdx.input.getY(a), Gdx.graphics.getWidth() - 140*gameWidth/768, Gdx.graphics.getHeight() - 720*gameHeight/768, 140*gameWidth/768, 140*gameHeight/768)&&Gdx.input.isTouched(a)) {
                if (!weaponList.get(4).isEmpty() && tracking % 7 == 0) {
                    WeaponBall weaponBall = weaponList.get(4).remove(0);
                    shootWeaponBallArrayList.add(new ShootWeaponBall(weaponBall.getType(), tilt.getX(), tilt.getY(), tilt));
                }
                if(weaponList.get(4).isEmpty()) {
                    weaponList.remove(4);
                    weaponList.add(new ArrayList<WeaponBall>());
                }
            }
            //End of weaponballs

            if (isInBorder(Gdx.input.getX(a), Gdx.input.getY(a), Gdx.graphics.getWidth() - 140*gameWidth/768, Gdx.graphics.getHeight() - 120*gameHeight/768, 140*gameWidth/768, 140*gameHeight/768)&&Gdx.input.isTouched(a)) {
                if (tracking % 10 == 0) {
                    bullets.add(new Bullet(tilt.getRotation(), tilt.getX(), tilt.getY()));
                }
            }

        }

        if(tracking%40==0){
            double prob = MathUtils.random();
            Enemy a;
            if(prob<0.25){
                a = new Enemy((float)MathUtils.random()*GameWorld.gameWidth-140, (float)MathUtils.random()*GameWorld.gameHeight);
            }else{
                a = new GeneralEnemy((float)MathUtils.random()*GameWorld.gameWidth-140, (float)MathUtils.random()*GameWorld.gameHeight);
            }
            enemies.add(a);
        }

        if(weaponBalls.size()<4 ) {
            if (MathUtils.random() < 0.4 && tracking%120==0) {
                weaponBalls.add(new WeaponBall((float) (MathUtils.random() * 300)));
            }
        }

        //update Bullets
        if (!bullets.isEmpty()) {
            for (int i = 0; i < bullets.size(); i++) {
                bullets.get(i).update(delta);
                if(!bullets.get(i).isExisted()){
                    bullets.remove(i);
                    i--;
                }
            }
        }

        //Update the weapons at that time
        for(int numShootWeapon = 0; numShootWeapon<shootWeaponBallArrayList.size();numShootWeapon++){
            shootWeaponBallArrayList.get(numShootWeapon).update(delta,tilt);
            if(!shootWeaponBallArrayList.get(numShootWeapon).isExisted()){
                shootWeaponBallArrayList.remove(numShootWeapon);
            }
        }

        weaponBallDetection(delta);

        EnemiesCollision(delta);

        tilt.update(delta);

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

    public void EnemiesCollision(float delta){

        if (!enemies.isEmpty()){
            for(int i = 0;i<enemies.size();i++){
                for(int numBul = 0; numBul<bullets.size();numBul++){
                    enemies.get(i).isOverlap(bullets.get(numBul).getCircle());
                    if(!enemies.get(i).isExisted()){
                        bullets.get(numBul).setIsExisted();
                    }
                }

                for(int numBall = 0; numBall < shootWeaponBallArrayList.size();numBall++){
                    enemies.get(i).isOverlap(shootWeaponBallArrayList.get(numBall).getCircle());
                }

                enemies.get(i).update(delta, tilt.getX(), tilt.getY());
                if(!enemies.get(i).isExisted()){
                    enemies.remove(i);
                    score++;
                    i--;
                }
            }
        }
    }

    public void weaponBallDetection(float delta){

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
                    if(ball.isHit){
                        shootWeaponBallArrayList.add(new ShootWeaponBall(ball.getType(), tilt.getX(), tilt.getY(),tilt));
                    }
                    i--;
                }
            }
        }
    }
}
