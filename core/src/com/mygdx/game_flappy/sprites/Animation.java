package com.mygdx.game_flappy.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Yuriy S on 22.08.2016.
 */
public class Animation {
    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int currentFrame;

    public Animation(TextureRegion region, int frameCount, float cycleTime) {
        frames = new Array<TextureRegion>();
        int farameWidth = region.getRegionWidth() / frameCount;
        for (int i = 0; i < frameCount; i++) {
            frames.add(new TextureRegion(region, i * farameWidth, 0, farameWidth, region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        currentFrame = 0;

    }

    public void update(float dt) {
        currentFrameTime += dt;
        if (currentFrameTime > maxFrameTime) {
            currentFrame++;
            currentFrameTime = 0;

        }
        if (currentFrame >= frameCount) {
            currentFrame = 0;
        }
    }

    public TextureRegion getFrame() {
        return frames.get(currentFrame);
    }


}
