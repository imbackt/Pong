package com.github.imbackt.pong.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.github.imbackt.pong.core.GameScreen;
import com.github.imbackt.pong.helper.BodyHelper;
import com.github.imbackt.pong.helper.Const;
import com.github.imbackt.pong.helper.ContactType;

abstract public class PlayerPaddle {

    protected Body body;
    protected float x, y, speed, velocity;
    protected int width, height, score;
    protected Texture texture;
    protected GameScreen gameScreen;

    public PlayerPaddle(float x, float y, GameScreen gameScreen) {
        this.x = x;
        this.y = y;
        this.gameScreen = gameScreen;
        this.speed = 6;
        this.width = 16;
        this.height = 64;
        this.texture = new Texture("white.png");
        this.body = BodyHelper.createBody(x, y, width, height, false, 10000, gameScreen.getWorld(), ContactType.PLAYER);
    }

    public void update() {
        x = body.getPosition().x * Const.PPM - (width / 2f);
        y = body.getPosition().y * Const.PPM - (height / 2f);
        velocity = 0;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }
}
