package com.fong.game.InputHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by wing on 6/5/15.
 */
public class AssetLoader {

    public static Texture cursor1,cursor2;
    public static TextureRegion cursorA,cursorB, bullet;
    public static Texture bulletTexture;
    public static Animation cursorAnimation;

    public static void load(){
        cursor1 = new Texture(Gdx.files.internal("data/newCursor.png"));
        cursor2 = new Texture(Gdx.files.internal("data/newCursor2.png"));
        bulletTexture = new Texture(Gdx.files.internal("data/bullet.png"));
        cursorA = new TextureRegion(cursor1, 0, 0, 350, 350);
        cursorB = new TextureRegion(cursor2, 0,0, 350,350);
        bullet = new TextureRegion(bulletTexture, 0,0, 350, 350);

        cursorA.flip(false, true);
        cursorB.flip(false, true);

    }

    public static void dispose(){
        cursor1.dispose();
        cursor2.dispose();
        bulletTexture.dispose();
    }

    public static void setHighScore(int val){

    }

    public static int getHighScore(){
        return 0;
    }
}
