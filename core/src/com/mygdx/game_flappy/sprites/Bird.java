package com.mygdx.game_flappy.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Yuriy S on 07.08.2016.
 */
public class Bird {

    public static final int MOVEMENT = 100;
    private static final int GRAVITY = -15;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Texture bird;
    private Texture texture;
    private Sound flapSound;

    private Animation birdAnimation;

    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);

//        bird = new Texture("bird.png");
        texture = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
//        bounds = new Rectangle(x, y, bird.getWidth(), bird.getHeight());
        bounds = new Rectangle(x, y, texture.getWidth() / 3, texture.getHeight());
        flapSound = Gdx.audio.newSound(Gdx.files.internal("flap.ogg"));

    }

    //    public Texture getTexture() {
//        return bird;
//    }


    public Vector3 getPosition() {
        return position;
    }

    public void update(float dt) {
        birdAnimation.update(dt);
        if (position.y > 0) {
            velocity.add(0, GRAVITY, 0);
        }

        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);
        if (position.y < 0) {
            position.y = 0;
        }
        velocity.scl(1 / dt);
        bounds.setPosition(position.x, position.y);
    }

    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }

    public void jump() {
        velocity.y = 250;
        flapSound.play(0.3f);
    }


    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
//        bird.dispose();
        texture.dispose();
        flapSound.dispose();

    }
}