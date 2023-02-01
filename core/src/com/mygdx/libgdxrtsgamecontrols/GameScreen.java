package com.mygdx.libgdxrtsgamecontrols;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
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
    private final OrthographicCamera camera;
    private final Texture map;
    private final Viewport viewport;
    private int width;
    private int height;
    private int triggerArea = 100;
    private float scrollSpeed = 5f;
    private final float MAX_SCROLL_SPEED = 15f;
    private final float ZOOM_SPEED = 2f;
    private Stage borders;

    public GameScreen(RTSGame game){
        this.game = game;
        map = new Texture("Isometric_Map.png");
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
        ScreenUtils.clear(new Color(0x3a7830));
        game.batch.setProjectionMatrix(camera.combined);
        camera.update();
        game.batch.begin();
        game.batch.draw(map,0,0);
        game.batch.end();
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            game.dispose();
            System.exit(0);
        }
        scroll();
        zoom();
    }


    private void zoom(){
        if(Gdx.input.isKeyPressed(Input.Keys.I) && camera.zoom < 3){
            camera.zoom += ZOOM_SPEED * Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.O) && camera.zoom > 0.5){
            camera.zoom -= ZOOM_SPEED * Gdx.graphics.getDeltaTime();
        }
    }

    private void scroll(){
        if(Gdx.input.getX()>=width-triggerArea){
            camera.position.set(
                    camera.position.x+=accelerate(),camera.position.y,0);
        }
        else if(Gdx.input.getX()<=triggerArea){
            accelerate();
            camera.position.set(
                    camera.position.x-=accelerate(), camera.position.y, 0);
        }
        else if(Gdx.input.getY()<=triggerArea){
            accelerate();
            camera.position.set(
                    camera.position.x,camera.position.y+=accelerate(),0);
        }
        else if(Gdx.input.getY()>=height-triggerArea){
            accelerate();
            camera.position.set(
                    camera.position.x,camera.position.y-=accelerate(),0);
        }
        else{
            scrollSpeed=5;
        }

    }

    private float accelerate(){
        if(!(scrollSpeed >= MAX_SCROLL_SPEED)) {
            return scrollSpeed += 5 * Gdx.graphics.getDeltaTime()/2;
        }
        else {
            return scrollSpeed;
        }
    }

    @Override
    public void resize(int width, int height) {//TODO Maybe shrink triggerarea according to the screen size ?
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
}
