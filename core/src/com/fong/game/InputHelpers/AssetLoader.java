package com.fong.game.InputHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fong.game.gameobjects.Tilt;
import com.fong.game.gameworld.GameWorld;

/**
 * Created by wing on 6/5/15.
 */
public class AssetLoader {
    public static BitmapFont ConsolasFont;
    public static Texture cursor1,cursor2,clock1, clock2, antiClock1, antiClock2, shoot1, shoot2, buttonBackground, button;
    public static Texture homeButton, pauseButton, startButton,enemy, enemyRunning;
    public static TextureRegion cursorA,cursorB, bullet, clockA, clockB, antiClockA, antiClockB, shootA, shootB,homeButtonTexture;
    public static Texture settingButton,playButton, recordButton;
    public static Texture bulletTexture;
    public static Texture backButton,tiltButton, buttonButton, adjustButton;
    public static Preferences preferences;

    public static void load(){

        cursor1 = new Texture(Gdx.files.internal("data/newCursor.png"));
        cursor2 = new Texture(Gdx.files.internal("data/newCursor2.png"));
        bulletTexture = new Texture(Gdx.files.internal("data/bullet.png"));
        clock1 = new Texture(Gdx.files.internal("data/clockwise1.png"));
        clock2 = new Texture(Gdx.files.internal("data/clockwise2.png"));
        antiClock1 = new Texture(Gdx.files.internal("data/anticlockwise1.png"));
        antiClock2 = new Texture(Gdx.files.internal("data/anticlockwise2.png"));
        ConsolasFont = new BitmapFont(Gdx.files.internal("data/Consolas.fnt"),true);
        shoot1 = new Texture(Gdx.files.internal("data/shootButton1.png"));
        shoot2 = new Texture(Gdx.files.internal("data/shootButton2.png"));
        buttonBackground = new Texture(Gdx.files.internal("data/ButtonBackground.png"));
        button = new Texture(Gdx.files.internal("data/Button.png"));
        homeButton = new Texture(Gdx.files.internal("data/homeButton.png"));
        pauseButton = new Texture(Gdx.files.internal("data/pauseButton.png"));
        startButton = new Texture(Gdx.files.internal("data/startButton.png"));
        enemy = new Texture(Gdx.files.internal("data/enemy.png"));
        enemyRunning = new Texture(Gdx.files.internal("data/enemyRunning.png"));
        settingButton = new Texture(Gdx.files.internal("data/SettingButton.png"));
        playButton = new Texture(Gdx.files.internal("data/PlayButton.png"));
        recordButton = new Texture(Gdx.files.internal("data/RecordButton.png"));
        backButton = new Texture(Gdx.files.internal("data/BackButton.png"));
        adjustButton = new Texture(Gdx.files.internal("data/AdjustButton.png"));
        tiltButton = new Texture(Gdx.files.internal("data/tiltButton.png"));
        buttonButton = new Texture(Gdx.files.internal("data/ButtonButton.png"));

 /*
        cursor1 = new Texture(Gdx.files.internal("android/assets/data/newCursor.png"));
        cursor2 = new Texture(Gdx.files.internal("android/assets/data/newCursor2.png"));
        bulletTexture = new Texture(Gdx.files.internal("android/assets/data/bullet.png"));
        clock1 = new Texture(Gdx.files.internal("android/assets/data/clockwise1.png"));
        clock2 = new Texture(Gdx.files.internal("android/assets/data/clockwise2.png"));
        antiClock1 = new Texture(Gdx.files.internal("android/assets/data/anticlockwise1.png"));
        antiClock2 = new Texture(Gdx.files.internal("android/assets/data/anticlockwise2.png"));
        ConsolasFont = new BitmapFont(Gdx.files.internal("android/assets/data/Consolas.fnt"),true);
        */

        cursorA = new TextureRegion(cursor1, 0, 0, 350, 350);
        cursorB = new TextureRegion(cursor2, 0,0, 350,350);
        bullet = new TextureRegion(bulletTexture, 0, 0, 350, 350);
        clockA = new TextureRegion(clock1, 0,0,350,350);
        clockB = new TextureRegion(clock2, 0, 0, 350, 350);
        antiClockA = new TextureRegion(antiClock1,0 ,0, 350, 350);
        antiClockB = new TextureRegion(antiClock2, 0, 0, 350, 350);
        shootA = new TextureRegion(shoot1, 0,0,350,350);
        shootB = new TextureRegion(shoot2, 0,0, 350, 350);
        homeButtonTexture = new TextureRegion(homeButton, 0, 0, 380,370);


        cursorA.flip(false, true);
        cursorB.flip(false, true);
        homeButtonTexture.flip(false, true);

        ConsolasFont.getData().setScale(0.5f);

        preferences = Gdx.app.getPreferences("TiltAndShoot");
        if(!preferences.contains("highScore")){
            preferences.putInteger("highScore", 0);
        }

        if(!preferences.contains("Sensitivity")){
            Tilt.sensitivity = 1;
            preferences.putFloat("Sensitivity", Tilt.sensitivity);
        }else {
            Tilt.sensitivity = preferences.getFloat("Sensitivity");
        }

        if(!preferences.contains("AccX")){
            Tilt.AcCorrectionX = 0;
            preferences.putFloat("AccX", Tilt.AcCorrectionX);
        }else {
            Tilt.AcCorrectionX = preferences.getFloat("AccX");
        }

        if(!preferences.contains("AccY")){
            Tilt.AcCorrectionY = 0;
            preferences.putFloat("AccY", Tilt.AcCorrectionY);
        }else {
            Tilt.AcCorrectionY = preferences.getFloat("AccY");
        }

        if(!preferences.contains("isButton")){
            GameWorld.isButton = false;
            preferences.putBoolean("isButton", GameWorld.isButton);
        }else {
            GameWorld.isButton = preferences.contains("isButton");
        }
    }

    public static void dispose(){
        cursor1.dispose();
        cursor2.dispose();
        bulletTexture.dispose();
        ConsolasFont.dispose();
        clock1.dispose();
        clock2.dispose();
        antiClock1.dispose();
        antiClock2.dispose();
        shoot1.dispose();
        shoot2.dispose();
        button.dispose();
        buttonBackground.dispose();
        startButton.dispose();
        pauseButton.dispose();
        homeButton.dispose();
        settingButton.dispose();
        playButton.dispose();
        recordButton.dispose();
        backButton.dispose();
        tiltButton.dispose();
        adjustButton.dispose();
        buttonButton.dispose();
    }

    public static void setHighScore(int val){
        preferences.putInteger("highScore", val);
        preferences.flush();
    }

    public static void setControl(float sens, float accX, float accY){
        preferences.putFloat("Sensitivity", sens);
        preferences.putFloat("AccX", accX);
        preferences.putFloat("AccY", accY);
        preferences.flush();
    }

    public static int getHighScore(){
        return preferences.getInteger("highScore");
    }

    public static void setIsButton(boolean b){
        preferences.putBoolean("isButton", b);
        preferences.flush();
    }
}
