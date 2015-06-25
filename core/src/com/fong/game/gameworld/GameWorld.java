package com.fong.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.fong.game.gameobjects.Bullet;
import com.fong.game.gameobjects.Enemy;
import com.fong.game.gameobjects.GeneralEnemy;
import com.fong.game.gameobjects.MiniAbsorbBall;
import com.fong.game.gameobjects.ShootWeaponBall;
import com.fong.game.gameobjects.Tilt;
import com.fong.game.gameobjects.WeaponBall;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by wing on 6/4/15.
 */
public class GameWorld {

    public static int score=0;
    private float timeRotA = 0;
    private float timeRotC = 0;
    public Tilt tilt2 = new Tilt();
    public static int gameWidth = Gdx.graphics.getWidth();
    public static int gameHeight = Gdx.graphics.getHeight();
    public enum GameState{
        READY, RUNNING, GAMEOVER, HIGHSCORE,SETACC,SETSENSATIVITY, PAUSE
    }

    public static float seekBarX = 200*gameWidth/1196 + gameWidth/2;
    public boolean clock = false;
    public boolean antiClock = false;
    private int tracking;
    private Tilt tilt;
    public GameState currentState;
    public float gameOverTime=0;
    public ArrayList<Bullet> bullets ;
    public ArrayList<Enemy> enemies;
    public ArrayList<WeaponBall> weaponBalls;
    public ArrayList<ArrayList<WeaponBall>> weaponList;
    public ArrayList<ShootWeaponBall> shootWeaponBallArrayList;
    public ArrayList<MiniAbsorbBall> absorbBallsList;
    private float time;
    private int horrizontal = 0;

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
        absorbBallsList = new ArrayList<MiniAbsorbBall>();
    }

    public void update(float delta){
        switch (currentState){
            case READY:
                updateReady(delta);
                break;
            case RUNNING:
                gameOverTime = 0;
                updateRunning(delta);
                break;
            case SETACC:
                updateSetting(delta);
                break;
            case PAUSE:
                updatePause(delta);
                break;
            case GAMEOVER:
                updateGameOver(delta);
                break;
            default:
                break;
        }
    }

    private void updateGameOver(float delta) {
        gameOverTime= gameOverTime + delta;
        if(gameOverTime>1){
            float inX = Gdx.input.getX();
            float inY = Gdx.input.getY();
            if(isInBorder(inX, inY, GameWorld.gameWidth / 2 - 250 * gameWidth / 1196, GameWorld.gameHeight / 2, 200 * gameWidth / 1196, 200 * gameHeight / 768)){
                currentState = GameState.READY;
            }

            if(isInBorder(inX, inY, GameWorld.gameWidth / 2 + 50 * gameWidth / 1196, GameWorld.gameHeight/2, 200*gameWidth/1196, 200*gameHeight/768)){
                updateReady(delta);
                currentState = GameState.RUNNING;
            }
        }

    }

    private void updatePause(float delta) {
        float inputX = Gdx.input.getX();
        float inputY = Gdx.input.getY();

        if(isInBorder(inputX, inputY, gameWidth/2-300*gameWidth/1196, gameHeight/2-100*gameHeight/768, 200*gameWidth/1196,200*gameHeight/768)){
            if(Gdx.input.justTouched()) {
                currentState = GameState.READY;
            }
        }else if(isInBorder(inputX, inputY, gameWidth/2+100 *gameWidth/1196, gameHeight/2-100*gameHeight/768, 200*gameWidth/1196, 200*gameHeight/768)){
            currentState = GameState.RUNNING;
        }

    }

    private void updateReady(float delta) {
        this.tilt.reset();
        this.enemies = new ArrayList<Enemy>();
        bullets = new ArrayList<Bullet>();
        this.weaponBalls = new ArrayList<WeaponBall>();
        this.weaponList = new ArrayList<ArrayList<WeaponBall>>(5);
        for(int i = 0; i< 5;i++){
            ArrayList arrayList = new ArrayList();
            weaponList.add(arrayList);
        }
        shootWeaponBallArrayList = new ArrayList<ShootWeaponBall>();
        absorbBallsList = new ArrayList<MiniAbsorbBall>();
        time = 0;
        score = 0;

        float inputX = Gdx.input.getX();
        float inputY = Gdx.input.getY();
        if(isInBorder(inputX, inputY, gameWidth / 2 - 150 * gameWidth / 1196,
                gameHeight/2-50*gameHeight/768, 300 * gameWidth / 1196, 100 * gameHeight / 768)&&Gdx.input.isTouched()){
            currentState = GameState.RUNNING;
        }else if(isInBorder(inputX, inputY, gameWidth/2 - 500 * gameWidth / 1196,
                gameHeight/2-50*gameHeight/768, 300 * gameWidth / 1196, 100 * gameHeight / 768)&&Gdx.input.isTouched()){
            if(Gdx.input.justTouched()) {
                currentState = GameState.SETACC;
            }
        }else if(isInBorder(inputX, inputY, gameWidth/2 - 500 * gameWidth / 1196,
                gameHeight/2-50*gameHeight/768, 300 * gameWidth / 1196, 100 * gameHeight / 768)&&Gdx.input.isTouched()){
            currentState = GameState.SETSENSATIVITY;
        }

    }

    private void updateSetting(float delta){
        float accX = Gdx.input.getAccelerometerY();
        float accY = Gdx.input.getAccelerometerX();
        if(isInBorder(Gdx.input.getX(), Gdx.input.getY(),
                10*GameWorld.gameWidth/1196, GameWorld.gameHeight-110 *GameWorld.gameHeight/768, 200*GameWorld.gameWidth/1196,100*GameWorld.gameHeight/768)){
            Tilt.AcCorrectionX = accX;
            Tilt.AcCorrectionY = accY;
            tilt2.setPosition();
        }else if(isInBorder(Gdx.input.getX(), Gdx.input.getY(),
                10*GameWorld.gameWidth/1196, 10 *GameWorld.gameHeight/768, 200*GameWorld.gameWidth/1196,100*GameWorld.gameHeight/768)){
            currentState = GameState.READY;
        }else if(isInBorder(Gdx.input.getX(), Gdx.input.getY(), 310*gameWidth/1196, 10 *GameWorld.gameHeight/768,
                800*GameWorld.gameWidth/1196,100*GameWorld.gameHeight/768)){
            seekBarX = Gdx.input.getX();
            Tilt.sensitivity = (seekBarX-310)/400;
        }
        tilt2.update(delta);
    }

    public GameState getCurrentState(){
        return this.currentState;
    }

    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public void start() {
        currentState = GameState.READY;
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
        time += delta;
        timeRotC +=delta;
        timeRotA +=delta;
        tracking++;
        if(delta > .15f){
            delta = .15f;
        }
        tilt.update(delta);
        for(int a = 0; a< 3; a++) {
            //rotation
            if (isInBorder(Gdx.input.getX(a), Gdx.input.getY(a), 0, Gdx.graphics.getHeight() - 400*gameHeight/768, 200*gameWidth/768, 200*gameHeight/768)&&Gdx.input.isTouched(a)) {
                tilt.setRotation(5);
                clock = true;
                timeRotC = 0;
            }
            if (isInBorder(Gdx.input.getX(a), Gdx.input.getY(a), 0, Gdx.graphics.getHeight() - 200*gameHeight/768, 200*gameWidth/768, 200*gameHeight/768)&&Gdx.input.isTouched(a)) {
                tilt.setRotation(-5);
                antiClock = true;
                timeRotA = 0;
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

            //Pause bottom
            if(isInBorder(Gdx.input.getX(a), Gdx.input.getY(a),GameWorld.gameWidth-150*gameWidth/1196, 60*gameHeight/768,100*gameWidth/ 1196, 100*gameHeight/768)){
                currentState = GameState.PAUSE;
            }

            if (isInBorder(Gdx.input.getX(a), Gdx.input.getY(a), Gdx.graphics.getWidth() - 140 * gameWidth / 768, Gdx.graphics.getHeight() - 120 * gameHeight / 768, 140 * gameWidth / 768, 140 * gameHeight / 768)&&Gdx.input.isTouched(a)) {
                if (tracking % 8 == 0) {
                    bullets.add(new Bullet(tilt.getRotation(), tilt.getX(), tilt.getY()));
                    tilt.setIsPressed(true);
                    time = 0;
                }
            }
            if(time>0.3){
                tilt.setIsPressed(false);
            }
            if(timeRotA>0.05){
                antiClock = false;
            }
            if(timeRotC>0.05){
                clock = false;
            }

        }

        if(weaponBalls.size()<4 ) {
            if (MathUtils.random() < 0.01) {
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
            ShootWeaponBall ball = shootWeaponBallArrayList.get(numShootWeapon);
            ball.update(delta, tilt);
            if(!ball.isExisted()){
                if(ball.getType()==4){
                    if(ball.isExploded()){
                        for(int numExBall = 0; numExBall <6; numExBall++){
                            absorbBallsList.add(new MiniAbsorbBall(ball.getX(), ball.getY(), 360 / 6 * numExBall));
                        }
                    }
                }
                shootWeaponBallArrayList.remove(numShootWeapon);
            }
        }

        BulletCollision(delta);

        weaponBallDetection(delta);

        updateAbsorbingBalls(delta);

        int c = (int)(time/10);
        double prob = MathUtils.random();
        switch (c){
            case 0:
                Enemy a;
                if(prob<0.02){
                    a = new GeneralEnemy((float)MathUtils.random()*GameWorld.gameWidth-140*gameWidth/1196, (float)MathUtils.random()*GameWorld.gameHeight);
                    a.setVelocity(30,30);
                    enemies.add(a);
                }
                EnemiesCollision(delta);
                break;
            case 1:
                if(prob<0.08) {
                    Enemy b = new GeneralEnemy((float) MathUtils.random() * GameWorld.gameWidth - 140 * gameWidth / 1196, (float) MathUtils.random() * GameWorld.gameHeight);
                    b.setVelocity(40,40);
                    enemies.add(b);
                }
                EnemiesCollision(delta);
                break;
            case 2:
                Enemy b=null;
                if(prob<0.05) {
                    b = new GeneralEnemy((float) MathUtils.random() * GameWorld.gameWidth - 140 * gameWidth / 1196, (float) MathUtils.random() * GameWorld.gameHeight);
                    b.setVelocity(120,120);
                }else if(prob<0.4){
                    b = new Enemy((float) MathUtils.random() * GameWorld.gameWidth - 140 * gameWidth / 1196, (float) MathUtils.random() * GameWorld.gameHeight);
                    b.setVelocity(200,200);
                }
                if(b!=null) {
                    enemies.add(b);
                }
                EnemiesCollision(delta);
                break;
            case 3:
                if(horrizontal==0) {
                    for (int num = 0; num < 15; num++) {
                        Enemy enemy = new Enemy(20 * gameWidth / 1196 + num * gameWidth / 15, 20 * gameHeight / 768);
                        enemy.setVelocity(0, 50);
                        enemies.add(enemy);
                        Enemy enemy1 = new Enemy(20 * gameWidth / 1196 + num * gameWidth / 15, gameHeight - 20 * gameHeight / 768);
                        enemy1.setVelocity(0, -50);
                        enemies.add(enemy1);
                    }
                    horrizontal++;
                }
                EnemiesCollision(delta);
                break;
            case 4:
                Enemy d = null;
                if(prob<0.15) {
                    d = new GeneralEnemy((float) MathUtils.random() * GameWorld.gameWidth - 140 * gameWidth / 1196, (float) MathUtils.random() * GameWorld.gameHeight);

                }else if(prob<0.3){
                    d = new Enemy((float) MathUtils.random() * GameWorld.gameWidth - 140 * gameWidth / 1196, (float) MathUtils.random() * GameWorld.gameHeight);
                }
                if(d!=null) {
                    enemies.add(d);
                }
                if(enemies.size()<63||enemies.size()>80) {
                    EnemiesCollision(delta);
                }
                else {
                    EnemiesCollision(delta,gameWidth/2-80*gameWidth/1196,gameHeight/2-80*gameHeight/768, 8,8);
                }
                if(isAllArrived()){
                    for(int i = 0;i < enemies.size(); i++){
                        if(enemies.get(i).getSpecial()) {
                            enemies.get(i).setSpecial(false);
                            enemies.get(i).setVelocity(300, 300);
                        }
                    }
                }
                horrizontal = 0;
                break;
            case 5:
                if(horrizontal==0) {
                    for (int num = 0; num < 20; num++) {
                        Enemy enemy = new Enemy(20 * gameWidth / 1196 + num * gameWidth / 20, 20 * gameHeight / 768);
                        enemy.setVelocity(0, 80);
                        Enemy enemy2 = new Enemy(20*gameWidth/1196, 20*gameHeight/768+num*gameHeight/20);
                        enemy2.setVelocity(80, 0);
                        enemies.add(enemy2);
                        enemies.add(enemy);
                        Enemy enemy1 = new Enemy(20 * gameWidth / 1196 + num * gameWidth / 20, gameHeight - 20 * gameHeight / 768);
                        enemy1.setVelocity(0, -80);
                        Enemy enemy3 = new Enemy(gameWidth - 20*gameWidth/1196, 20*gameHeight/768+num*gameHeight/20);
                        enemy3.setVelocity(-80, 0);
                        enemies.add(enemy3);
                        enemies.add(enemy1);
                    }
                    horrizontal++;
                }
                EnemiesCollision(delta);
                break;
            case 6:
                Enemy ab=null;
                if(prob<0.05) {
                    ab = new GeneralEnemy((float) MathUtils.random() * GameWorld.gameWidth - 140 * gameWidth / 1196, (float) MathUtils.random() * GameWorld.gameHeight);
                    ab.setVelocity(120,120);
                }else if(prob<0.4){
                    ab = new Enemy((float) MathUtils.random() * GameWorld.gameWidth - 140 * gameWidth / 1196, (float) MathUtils.random() * GameWorld.gameHeight);
                    ab.setVelocity(200,200);
                }
                if(ab!=null) {
                    enemies.add(ab);
                }
                EnemiesCollision(delta);
                break;
            case 7:
                Enemy fd = null;
                if(prob<0.15) {
                    fd = new GeneralEnemy((float) MathUtils.random() * GameWorld.gameWidth - 140 * gameWidth / 1196, (float) MathUtils.random() * GameWorld.gameHeight);

                }else if(prob<0.3){
                    fd = new Enemy((float) MathUtils.random() * GameWorld.gameWidth - 140 * gameWidth / 1196, (float) MathUtils.random() * GameWorld.gameHeight);
                }
                if(fd!=null) {
                    enemies.add(fd);
                }
                if(enemies.size()<37||enemies.size()>80) {
                    EnemiesCollision(delta);
                }
                else {
                    EnemiesCollision(delta,gameWidth/2-80*gameWidth/1196,gameHeight/2-80*gameHeight/768, 8,8);
                }
                if(isAllArrived()){
                    for(int i = 0;i < enemies.size(); i++){
                        if(enemies.get(i).getSpecial()) {
                            Enemy g = enemies.get(i);
                            float angle = MathUtils.atan2(g.getDestination().y-gameHeight/2, g.getDestination().x-gameWidth/2);
                            enemies.get(i).setSpecial(false);
                            enemies.get(i).setVelocity(300*MathUtils.cos(angle), 300*MathUtils.sin(angle));
                        }
                    }
                }
                EnemiesCollision(delta);
                break;
            default:
                c = 0;
                EnemiesCollision(delta);
                break;
        }

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

    public boolean isInBorder(float x, float y, float fromX, float fromY, float width, float height){
        if(x>=fromX && x<=fromX + width && y>=fromY && y<= fromY+height && Gdx.input.isTouched())
            return true;
        else
            return false;
    }

    public ArrayList getWeaponList(){
        return weaponList;
    }

    public void BulletCollision(float delta){
        for(int numBul = 0; numBul<bullets.size();numBul++){
            for(int i = 0; i<enemies.size();i++){
                enemies.get(i).isOverlap(bullets.get(numBul).getCircle());
                if(!enemies.get(i).isExisted()){
                    if((numBul-1)>=0&&bullets.get(numBul-1).isExisted()) {
                        bullets.get(numBul).setIsExisted();
                    }
                }
            }
            for(int numBalls = 0; numBalls<weaponBalls.size();numBalls++){
                weaponBalls.get(numBalls).setShot(bullets.get(numBul));
                if(weaponBalls.get(numBalls).isShot){
                    if((numBul-1)>=0&&bullets.get(numBul-1).isExisted()) {
                        bullets.get(numBul).setIsExisted();
                    }
                }
            }
        }
    }

    public void EnemiesCollision(float delta){
        if (!enemies.isEmpty()){
            for(int i = 0;i<enemies.size();i++) {
                for (int numBall = 0; numBall < shootWeaponBallArrayList.size(); numBall++) {
                    enemies.get(i).isOverlap(shootWeaponBallArrayList.get(numBall).getCircle());
                }
                enemies.get(i).update(delta, tilt.getX(), tilt.getY());
                for (int numball = 0; numball < absorbBallsList.size(); numball++) {
                    MiniAbsorbBall ball = absorbBallsList.get(numball);
                    Enemy enemy = enemies.get(i);
                    if (ball.isInRange(enemies.get(i))) {
                        float velX = (enemy.getX() < ball.getPosX() + 15) ? Math.abs(150) : -Math.abs(150);
                        float velY = (enemy.getY() < ball.getPosY()) ? Math.abs(150) : -Math.abs(150);
                        enemies.get(i).setVelocity(velX, velY);
                    }
                    enemies.get(i).isOverlap(ball.getCircle());
                }

                if (enemies.get(i).isExisted() && enemies.get(i).isCanKill() &&tilt.getCircle().overlaps(enemies.get(i).myCircle)) {
                    tilt.setVelocity(0,0);
                    currentState = GameState.GAMEOVER;
                }
                if (!enemies.get(i).isExisted()) {
                    Enemy temp = enemies.remove(i);
                    if (temp.getSpecial()) {
                        if (temp.getSpecial()) {
                            Enemy newEnemy = new Enemy(MathUtils.random() * gameWidth, MathUtils.random() * gameHeight);
                            newEnemy.setVelocity(300, 300);
                            newEnemy.setDestination(temp.getX(), temp.getY());
                        }
                    }
                    score++;
                    i--;
                }
            }
        }
    }

    public void EnemiesCollision(float delta, float posX, float posY, int x, int y){
        if (!enemies.isEmpty()){
            for(int i = 0;i<enemies.size();i++){

                for(int numBall = 0; numBall < shootWeaponBallArrayList.size();numBall++){
                    enemies.get(i).isOverlap(shootWeaponBallArrayList.get(numBall).getCircle());
                }

                if(!enemies.get(i).getSpecial()) {
                    enemies.get(i).setSpecial(true);
                    enemies.get(i).setVelocity(300,300);
                    enemies.get(i).setDestination(posX + ((i % x) * 15 * gameWidth / 1196), ((posY + ((int) (i / y)) * 15 * gameHeight / 768)) % 768);

                }

                for(int numball = 0; numball<absorbBallsList.size();numball++){
                    MiniAbsorbBall ball = absorbBallsList.get(numball);
                    Enemy enemy = enemies.get(i);
                    if(ball.isInRange(enemies.get(i))){
                        float velX = (enemy.getX() < ball.getPosX()+15)? Math.abs(150):-Math.abs(150);
                        float velY = (enemy.getY() < ball.getPosY()+15)? Math.abs(150):-Math.abs(150);
                        enemies.get(i).setSpecial(false);
                        enemies.get(i).setVelocity(velX, velY);
                    }
                    enemies.get(i).isOverlap(ball.getCircle());
                }

                enemies.get(i).update(delta, tilt.getX(), tilt.getY());

                if (enemies.get(i).isExisted() && enemies.get(i).isCanKill() && tilt.getCircle().overlaps(enemies.get(i).myCircle)) {
                    tilt.setVelocity(0,0);
                    currentState = GameState.GAMEOVER;
                }

                if(!enemies.get(i).isExisted()){
                    Enemy temp = enemies.remove(i);
                    if(temp.getSpecial()) {
                        Enemy newEnemy = new Enemy(MathUtils.random()*gameWidth,MathUtils.random()*gameHeight);
                        newEnemy.setVelocity(300, 300);
                        newEnemy.setDestination(temp.getX(), temp.getY());
                    }
                    score++;
                    i--;
                }
            }

        }
    }

    public void updateAbsorbingBalls(float delta){
        for(int i = 0; i < absorbBallsList.size(); i++){
            absorbBallsList.get(i).update(delta);
            if(!absorbBallsList.get(i).isExisted()){
                absorbBallsList.remove(i);
                i--;
            }
        }
    }

    public void weaponBallDetection(float delta){
        if(!weaponBalls.isEmpty()){
            for(int i = 0; i<weaponBalls.size();i++){
                weaponBalls.get(i).setHit(tilt);
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
                        shootWeaponBallArrayList.add(new ShootWeaponBall(ball.getType(), tilt.getX()+35*gameWidth/1196, tilt.getY()+35*gameHeight/768,tilt));
                    }
                    i--;
                }
            }
        }
    }

    public boolean isAllArrived(){
        for(int i = 0; i< enemies.size(); i++){
            if(enemies.get(i).getSpecial()){
                if(!enemies.get(i).getArrive()){
                    return false;
                }
            }
        }
        return true;
    }
}
