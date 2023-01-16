package com.mygdx.libgdxrtsgamecontrols;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    private final RTSGame game ;
    private OrthographicCamera camera;

    public GameScreen(RTSGame game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GlobalVariables.SCREEN_WIDTH,GlobalVariables.SCREEN_HEIGHT);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera.update();
        ScreenUtils.clear(Color.DARK_GRAY);
        game.batch.begin();
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
