package ru.vaselevs.amentia.core.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.vaselevs.amentia.core.resource.IDisposable;

import java.util.function.Consumer;

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
    private boolean markReplay;

    public Animation(String internalFile, SpriteBatch batch, int framesNum, float frameTime, boolean loop) {
        this.texture = new Texture(Gdx.files.internal("images/sprite/animation/" + internalFile));
        this.batch = batch;
        this.framesNum = framesNum;
        this.frameWidth = this.texture.getWidth() / framesNum;
        this.frameHeight = this.texture.getHeight();
        this.frameTime = frameTime;
        this.frameTimeAccumulator = 0;
        this.currentFrame = 0;
        this.isRunning = false;
        this.loop = loop;
        this.markReplay = false;
    }

    /*
    	public void draw (Texture texture, float x, float y, float originX, float originY, float width, float height, float scaleX,
		float scaleY, float rotation, int srcX, int srcY, int srcWidth, int srcHeight, boolean flipX, boolean flipY) {

     */

    public void render(float x, float y, float width, float height, boolean flipX, boolean flipY, float rotation, float origX, float origY) {
        int offsetX = currentFrame * frameWidth;
        this.batch.draw(this.texture, x, y, origX, origY, width, height, 1f, 1f, rotation, offsetX, 0, this.frameWidth, this.frameHeight, flipX, flipY);
    }

    public void update(float deltaTime) {
        if (this.isRunning) {
            if (this.frameTimeAccumulator >= this.frameTime) {
                this.frameTimeAccumulator = 0f;

                if (this.currentFrame == 0) {
                    this.isRunning = true;
                    this.onStart((a) -> {
                    });
                }

                if (this.currentFrame < this.framesNum - 1) {
                    this.currentFrame++;
                } else {
                    // finish
                    this.markReplay = false;
                    this.onFinish((a) -> {
                    });
                    if(!this.loop) {
                        this.isRunning = false;
                    }
                    if (this.markReplay || this.loop) {
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

    public void onStart(Consumer<Animation> consumer) {
        consumer.accept(this);
    }

    public void onFinish(Consumer<Animation> consumer) {
        consumer.accept(this);
    }

    public void onStop(Consumer<Animation> consumer) {
        consumer.accept(this);
    }

    public void onReset(Consumer<Animation> consumer) {
        consumer.accept(this);
    }

    public void markForReplay() {
        this.markReplay = true;
    }

    public void stop() {
        this.isRunning = false;
        this.onStop((a) -> {
        });
    }

    public void reset() {
        this.currentFrame = 0;
        this.onReset((a) -> {
        });
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public int getWidth() {
        return this.frameWidth;
    }

    public int getHeight() {
        return this.frameHeight;
    }
}
