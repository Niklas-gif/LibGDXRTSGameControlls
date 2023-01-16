package com.mygdx.libgdxrtsgamecontrols;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    private final RTSGame game ;
    private OrthographicCamera camera;
    private Texture map;
    private Stage borders;

    public GameScreen(RTSGame game){
        this.game = game;
        map = new Texture("Test_Map.png");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GlobalVariables.SCREEN_WIDTH,GlobalVariables.SCREEN_HEIGHT);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.DARK_GRAY);
        game.batch.setProjectionMatrix(camera.combined);
        camera.update();
        game.batch.begin();
        game.batch.draw(map,0,0);
        game.batch.end();
        scroll();
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
        map.dispose();
        game.dispose();
    }

    private void scroll(){ //Test Scroll Function
        if(Gdx.input.isKeyPressed(Input.Keys.I) && camera.zoom < 3){
            camera.zoom += 2f * Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.O) && camera.zoom > 0.5){
            camera.zoom -= 2f * Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.getX()>=GlobalVariables.SCREEN_WIDTH-500){
            camera.position.set(camera.position.x+=5,camera.position.y,0);
        }
        if(Gdx.input.getX()<=100){
            camera.position.set(camera.position.x -= 5, camera.position.y, 0);
        }
        if(Gdx.input.getY()<=100){
            camera.position.set(camera.position.x,camera.position.y+=5,0);
        }
        if(Gdx.input.getY()>=GlobalVariables.SCREEN_HEIGHT-400){
            camera.position.set(camera.position.x,camera.position.y-=5,0);
        }
    }
}
