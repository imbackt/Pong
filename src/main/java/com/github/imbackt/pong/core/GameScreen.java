package com.github.imbackt.pong.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.github.imbackt.pong.helper.Const;
import com.github.imbackt.pong.objects.Ball;
import com.github.imbackt.pong.objects.Player;
import com.github.imbackt.pong.objects.Wall;
import org.lwjgl.opengl.GL20;

public class GameScreen extends ScreenAdapter {
    private final OrthographicCamera camera;
    private final SpriteBatch batch;
    private final World world;
    private final Box2DDebugRenderer box2DDebugRenderer;

    private final Player player;
    private final Ball ball;
    private Wall topWall, bottomWall;

    public GameScreen(OrthographicCamera camera) {
        this.camera = camera;
        camera.position.set(new Vector3(Boot.INSTANCE.getScreenWidth() / 2f, Boot.INSTANCE.getScreenHeight() / 2f, 0));
        batch = new SpriteBatch();
        world = new World(new Vector2(0, 0), false);
        box2DDebugRenderer = new Box2DDebugRenderer();

        player = new Player(16, Boot.INSTANCE.getScreenHeight() / 2f, this);
        ball = new Ball(this);
        topWall = new Wall(32, this);
        bottomWall = new Wall(Boot.INSTANCE.getScreenHeight() - 32, this);
    }

    public void update() {
        world.step(1 / 60f, 6, 2);

        camera.update();
        player.update();
        ball.update();

        batch.setProjectionMatrix(camera.combined);

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            ball.reset();
        }
    }

    @Override
    public void render(float delta) {
        update();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        player.render(batch);
        ball.render(batch);
        topWall.render(batch);
        bottomWall.render(batch);
        batch.end();

        box2DDebugRenderer.render(world, camera.combined.scl(Const.PPM));
    }

    public World getWorld() {
        return world;
    }
}
