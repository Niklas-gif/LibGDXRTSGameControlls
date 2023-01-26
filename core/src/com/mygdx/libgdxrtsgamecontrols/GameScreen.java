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
    private final OrthographicCamera camera;
    private final Texture map;
    private final Viewport viewport;
    private int width;
    private int height;
    private int triggerArea = 100;
    private int scrollSpeed = 5;
    private float zoomSpeed = 2f;
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
            camera.zoom += zoomSpeed * Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.O) && camera.zoom > 0.5){
            camera.zoom -= zoomSpeed * Gdx.graphics.getDeltaTime();
        }
    }

    private void scroll(){
        if(Gdx.input.getX()>=width-triggerArea){
            camera.position.set(
                    camera.position.x+=scrollSpeed,camera.position.y,0);
        }
        if(Gdx.input.getX()<=triggerArea){
            camera.position.set(
                    camera.position.x-=scrollSpeed, camera.position.y, 0);
        }
        if(Gdx.input.getY()<=triggerArea){
            camera.position.set(
                    camera.position.x,camera.position.y+=scrollSpeed,0);
        }
        if(Gdx.input.getY()>=height-triggerArea){
            camera.position.set(
                    camera.position.x,camera.position.y-=scrollSpeed,0);
        }
    }

    private void accelerate(){
        //TODO: Implement acceleration function which increases the scrolling speed
    }

    @Override
    public void resize(int width, int height) {//TODO Maybe shrink triggerarea according to the screen size ?
        //System.out.println("width: " + width + " height: " + height );
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
