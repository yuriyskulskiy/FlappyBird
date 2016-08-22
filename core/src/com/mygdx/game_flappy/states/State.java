package com.mygdx.game_flappy.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Yuriy S on 07.08.2016.
 */
public abstract class State {
    //window to the game world
    protected OrthographicCamera camera;

    //3d proection
    private Vector3 mouse;

    protected  GameStateManager gsm;

    public State(GameStateManager gsm){
        this.gsm = gsm;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void handleInput();
    public abstract void update(float delta);
    public abstract void render(SpriteBatch batch);
    public abstract void dispose();


}
