package com.mygdx.game_flappy.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Yuriy S on 08.08.2016.
 */
public class Tube {
    public static final int TUBE_WIDTH = 52;
    //диапазон отклонений на которых будут создаваться трубы по высоте
    public static final int FLUCTUATION = 130;
    public static final int TUBE_GAP = 100;
    public static final int LOWEST_OPENING = 120;

    private Texture topTube;
    private Texture bottomTube;
    private Vector2 posTopTube;
    private Vector2 posBottomTube;
    private Rectangle boundsTop;
    private Rectangle boundsBottom;
    private Random random;

    public Tube(float x) {

        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        random = new Random();

        posTopTube = new Vector2(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBottomTube = new Vector2(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());

        boundsTop = new Rectangle(posTopTube.x,posTopTube.y,topTube.getWidth(),topTube.getHeight());
        boundsBottom = new Rectangle(posBottomTube.x,posBottomTube.y,bottomTube.getWidth(),bottomTube.getHeight());

    }

    public void reposition(float x) {
        posTopTube.set(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBottomTube.set(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());

        boundsTop.setPosition(posTopTube.x,posTopTube.y);
        boundsBottom.setPosition(posBottomTube.x,posBottomTube.y);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsBottom)||player.overlaps(boundsTop);
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBottomTube() {
        return posBottomTube;
    }

    public void dispose(){
        topTube.dispose();
        bottomTube.dispose();
    }
}
