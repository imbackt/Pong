package com.github.imbackt.pong.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.github.imbackt.pong.core.Boot;
import com.github.imbackt.pong.core.GameScreen;
import com.github.imbackt.pong.helper.BodyHelper;
import com.github.imbackt.pong.helper.ContactType;

public class Wall {

    private Body body;
    private float x, y;
    private int width, height;
    private Texture texture;

    public Wall(float y, GameScreen gameScreen) {
        this.x = Boot.INSTANCE.getScreenWidth() / 2f;
        this.y = y;
        this.width = Boot.INSTANCE.getScreenWidth();
        this.height = 64;

        this.texture = new Texture("white.png");
        this.body = BodyHelper.createBody(x, y, width, height, true, 0, gameScreen.getWorld(), ContactType.WALL);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x - (width / 2f), y - (height / 2f), width, height);
    }
}
