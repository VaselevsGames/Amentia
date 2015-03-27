package ru.vaselevs.amentia.core.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.vaselevs.amentia.core.resource.IDisposable;

/**
 * Created by CoreX on 27.03.2015.
 */
public class Animation implements IDisposable {

    private SpriteBatch batch;
    private Texture texture;

    private int framesNum;
    private int frameWidth;
    private int frameHeight;

    private float frameTime;
    private float frameTimeAccumulator;

    private int currentFrame;
    private boolean isRunning;

    private boolean loop;

    public Animation(String internalFile, SpriteBatch batch, int framesNum, int frameWidth, int frameHeight, float frameTime, boolean loop) {
        this.texture = new Texture(Gdx.files.internal("images/sprite/animation/" + internalFile));
        this.batch = batch;
        this.framesNum = framesNum;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.frameTime = frameTime;
        this.frameTimeAccumulator = 0;
        this.currentFrame = 0;
        this.isRunning = false;
        this.loop = loop;
    }

    public void render(float x, float y, float width, float height, boolean flipX, boolean flipY) {
        int offsetX = currentFrame * frameWidth;
        this.batch.draw(this.texture, x, y, width, height, offsetX, 0, this.frameWidth, this.frameHeight, flipX, flipY);
    }

    public void update(float deltaTime) {
        if (this.isRunning) {
            if (this.frameTimeAccumulator >= this.frameTime) {
                this.frameTimeAccumulator = 0f;
                if (this.currentFrame < this.framesNum - 1) {
                    this.currentFrame++;
                } else {
                    if (this.loop) {
                        this.currentFrame = 0;
                    } else {
                        this.currentFrame = this.framesNum - 1;
                    }
                }
            }
            this.frameTimeAccumulator += deltaTime;
        }
    }

    public void play() {
        this.isRunning = true;
    }

    public void stop() {
        this.isRunning = false;
    }

    public void reset() {
        this.currentFrame = 0;
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
