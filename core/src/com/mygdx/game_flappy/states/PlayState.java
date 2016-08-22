package com.mygdx.game_flappy.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game_flappy.FlappyDemo;
import com.mygdx.game_flappy.sprites.Bird;
import com.mygdx.game_flappy.sprites.Tube;

/**
 * Created by Yuriy S on 07.08.2016.
 */
public class PlayState extends State {

    public static final int TUBE_SPACING = 125;
    public static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = - 50;

    private Bird bird;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPosition1;
    private Vector2 groundPosition2;

    private Array<Tube> tubes;




    public PlayState(GameStateManager gameStateManager) {

        super(gameStateManager);
        bird = new Bird(50, 300);
        camera.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        bg = new Texture("bg.png");
        ground = new Texture("ground.png");
        groundPosition1 = new Vector2(camera.position.x-camera.viewportWidth/2,GROUND_Y_OFFSET);
        groundPosition2 = new Vector2((camera.position.x-camera.viewportWidth/2)+ground.getWidth(),GROUND_Y_OFFSET);
        tubes = new Array<Tube>();
        for (int i = 1; i <= TUBE_COUNT; i++) {
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }


    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump();
        }
    }

    @Override
    public void update(float delta) {
        handleInput();
        updateGround();
        bird.update(delta);
        camera.position.x=bird.getPosition().x+80;
        for (int i = 0; i <tubes.size ; i++) {
                Tube tube = tubes.get(i);
            if ((camera.position.x - (camera.viewportWidth / 2)) > (tube.getPosTopTube().x + tube.getTopTube().getWidth())) {
                tube.reposition(tube.getPosBottomTube().x + (Tube.TUBE_WIDTH + TUBE_SPACING)*TUBE_COUNT);
            }
            if(tube.collides(bird.getBounds())){
                gsm.set(new PlayState(gsm));
            }
        }
if(bird.getPosition().y<=ground.getHeight()+GROUND_Y_OFFSET){
    gsm.set(new PlayState(gsm));
};
        camera.update();
    }



    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(bg, camera.position.x - (camera.viewportWidth / 2), 0);
        batch.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
       for (Tube tube:tubes) {
           batch.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
           batch.draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);
       }
        batch.draw(ground,groundPosition1.x,groundPosition1.y);
        batch.draw(ground,groundPosition2.x,groundPosition2.y);
        batch.end();
    }

    @Override
    public void dispose() {
         bg.dispose();
        bird.dispose();
        for (Tube tube:tubes) {
            tube.dispose();
            System.out.println("play state have benn disposed");
        }
        ground.dispose();
    }

    private  void updateGround(){
        if( (camera.position.x-camera.viewportWidth/2)>(groundPosition1.x+ground.getWidth()) ){
              groundPosition1.add( ground.getWidth()*2,0 );
        }
        if( (camera.position.x-camera.viewportWidth/2)>(groundPosition2.x+ground.getWidth()) ){
            groundPosition2.add( ground.getWidth()*2,0 );
        }
    }
}
