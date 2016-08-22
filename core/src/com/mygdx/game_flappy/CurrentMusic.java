package com.mygdx.game_flappy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * Created by Yuriy S on 22.08.2016.
 */
public class CurrentMusic {
    private static CurrentMusic currenMusic = new CurrentMusic();
    private  Music music;
    private CurrentMusic(){
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        music.setLooping(true);
        music.setVolume(0.3f);  //10% volume
        music.play();
        currenMusic = this;
    }
    public static CurrentMusic  getCurrentMusic(){
        return  currenMusic;
    }
    public void turnProdigy(){
        music.dispose();
        music = Gdx.audio.newMusic(Gdx.files.internal("p.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);  //10% volume
        music.play();
    }
    public Music getMusic(){
        return music;
    }
}
