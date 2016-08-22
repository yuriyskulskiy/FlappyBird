package com.mygdx.game_flappy.states;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Yuriy S on 07.08.2016.
 */
public class GameStateManager {

    private Stack<State> states;

    public GameStateManager() {
        states = new Stack<State>();
    }

    public void push(State state) {
        states.push(state);
    }

//no dispose()
    public void pop(State state) {
        states.pop().dispose();
    }

    public void set(State state) {
        states.pop().dispose();

        states.push(state);
        if(state instanceof PlayState){

        }
    }

    public void update(float dt) {
        states.peek().update(dt);

    }

    public void render(SpriteBatch sb) {
        states.peek().render(sb);
    }
}
