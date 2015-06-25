package com.fong.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fong.game.InputHelpers.AssetLoader;
import com.fong.game.screens.GameScreen;

public class MyGdxGame extends Game {
	
	@Override
	public void create () {
		AssetLoader.load();
		setScreen(new GameScreen());
	}

	@Override
	public void dispose(){
		AssetLoader.dispose();
		super.dispose();
	}
}
