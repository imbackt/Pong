package com.github.imbackt.pong.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.github.imbackt.pong.core.GameScreen;

public class Player extends PlayerPaddle{
    public Player(float x, float y, GameScreen gameScreen) {
        super(x, y, gameScreen);
    }

    public void update() {
        super.update();

        if (Gdx.input.isKeyPressed(Input.Keys.W))
            velocity = 1;
        if (Gdx.input.isKeyPressed(Input.Keys.S))
            velocity = -1;

        body.setLinearVelocity(0, velocity * speed);
    }
}
