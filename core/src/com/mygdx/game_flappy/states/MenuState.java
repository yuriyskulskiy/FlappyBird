package com.mygdx.game_flappy.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game_flappy.CurrentMusic;
import com.mygdx.game_flappy.FlappyDemo;

/**
 * Created by Yuriy S on 07.08.2016.
 */
public class MenuState extends State {

    private Texture background;
    private Texture playBtn;


    public MenuState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        background = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.isTouched()){
            gsm.set(new PlayState(gsm));

            CurrentMusic.getCurrentMusic().turnProdigy();
        }
    }

    @Override
    public void update(float delta) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
//        batch.draw(background, 0, 0, FlappyDemo.WIDTH, FlappyDemo.HEIGHT);
        batch.draw(background, 0, 0);

//        batch.draw(playBtn, (FlappyDemo.WIDTH / 2) - (playBtn.getWidth() / 2), FlappyDemo.HEIGHT / 2);
        batch.draw(playBtn,camera.position.x-playBtn.getWidth()/2,camera.position.y);
        batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        System.out.println("Menu state have been disposed");
    }
}
