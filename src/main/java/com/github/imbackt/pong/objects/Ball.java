package com.github.imbackt.pong.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.github.imbackt.pong.core.Boot;
import com.github.imbackt.pong.core.GameScreen;
import com.github.imbackt.pong.helper.BodyHelper;
import com.github.imbackt.pong.helper.Const;
import com.github.imbackt.pong.helper.ContactType;

public class Ball {
    private final Body body;
    private float x, y, speed, velocityX, velocityY;
    private final int width;
    private final int height;
    private GameScreen gameScreen;
    private Texture  texture;

    public Ball(GameScreen gameScreen) {
        x = Boot.INSTANCE.getScreenWidth() / 2f;
        y = Boot.INSTANCE.getScreenHeight() / 2f;
        speed =5;
        velocityX = getRandomDirection();
        velocityY = getRandomDirection();

        texture = new Texture("white.png");
        this.gameScreen = gameScreen;
        width = 32;
        height = 32;

        this.body = BodyHelper.createBody(x, y, width, height, false, 0, gameScreen.getWorld(), ContactType.BALL);
    }

    private float getRandomDirection() {
        return (Math.random() < 0.5) ? 1 : -1;
    }

    public void update() {
        x = body.getPosition().x * Const.PPM - (width / 2f);
        y = body.getPosition().y * Const.PPM - (height / 2f);

        this.body.setLinearVelocity(velocityX * speed, velocityY * speed);
    }

    public void reset() {
        velocityX = getRandomDirection();
        velocityY = getRandomDirection();
        speed = 5;
        body.setTransform(Boot.INSTANCE.getScreenWidth() / 2f / Const.PPM, Boot.INSTANCE.getScreenHeight() / 2f / Const.PPM, 0);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }
}
