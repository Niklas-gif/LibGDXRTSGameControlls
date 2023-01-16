package com.mygdx.libgdxrtsgamecontrols;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {
    private final RTSGame game ;
    private OrthographicCamera camera;
    private Texture map;
    private Viewport viewport;
    private int width;
    private int height;
    private Stage borders;

    public GameScreen(RTSGame game){
        this.game = game;
        map = new Texture("Test_Map.png");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GlobalVariables.SCREEN_WIDTH,GlobalVariables.SCREEN_HEIGHT);
        viewport = new FitViewport(GlobalVariables.SCREEN_WIDTH,GlobalVariables.SCREEN_HEIGHT,camera);
        viewport.apply();
    }
    @Override
    public void show() {
        width = GlobalVariables.SCREEN_WIDTH;
        height = GlobalVariables.SCREEN_HEIGHT;

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
        System.out.println("width: " + width + " height: " + height );
        this.width = width;
        this.height = height;
        viewport.update(width,height);
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

    private void scroll(){ //Test Scroll Function TODO: Should not depend on the Screen size
        if(Gdx.input.isKeyPressed(Input.Keys.I) && camera.zoom < 3){
            camera.zoom += 2f * Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.O) && camera.zoom > 0.5){
            camera.zoom -= 2f * Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.getX()>=width-500){
            //System.out.println("scroll width:" + width);
            camera.position.set(camera.position.x+=5,camera.position.y,0);
        }
        if(Gdx.input.getX()<=100){
            camera.position.set(camera.position.x -= 5, camera.position.y, 0);
        }
        if(Gdx.input.getY()<=100){
            camera.position.set(camera.position.x,camera.position.y+=5,0);
        }
        if(Gdx.input.getY()>=height-400){
            camera.position.set(camera.position.x,camera.position.y-=5,0);
        }
    }
}
