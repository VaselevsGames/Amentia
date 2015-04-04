package ru.vaselevs.amentia.core.animation;

import ru.vaselevs.amentia.core.resource.IDisposable;
import ru.vaselevs.amentia.core.resource.ResourceDisposer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by CoreX on 27.03.2015.
 */
public class AnimationManager implements IDisposable {
    private ResourceDisposer resourceDisposer;
    private Map<String, Animation> animations;
    private Animation currentAnimation;

    public AnimationManager() {
        this.resourceDisposer = new ResourceDisposer();
        this.animations = new HashMap<>();
    }

    public void add(String name, Animation animation) {
        this.resourceDisposer.addResource(animation);
        this.animations.put(name, animation);
    }

    public Animation get(String name) {
        return this.animations.getOrDefault(name, null);
    }

    public void render(float x, float y, float width, float height, boolean flipX, boolean flipY) {
        Animation animation = this.currentAnimation;
        if (animation != null) {
            animation.render(x, y, width, height, flipX, flipY, 0f, 0f, 0f);
        }
    }

    public void render(float x, float y, float width, float height, boolean flipX, boolean flipY, float rotation, float origX, float origY) {
        Animation animation = this.currentAnimation;
        if (animation != null) {
            animation.render(x, y, width, height, flipX, flipY, rotation, origX, origY);
        }
    }

    public void update(float deltaTime) {
        Animation animation = this.currentAnimation;
        if (animation != null) {
            animation.update(deltaTime);
        }
    }

    public boolean play(String animationName) {
        Animation newAnimation = this.get(animationName);

        if (newAnimation != null) {
            if (this.currentAnimation != null) {
                this.stop();
            }
            this.currentAnimation = this.get(animationName);
            if (newAnimation != null) {
                newAnimation.play();
                return true;
            }
            return false;
        }
        return false;
    }

    public void stop() {
        Animation animation = this.currentAnimation;
        if (animation != null) {
            animation.stop();
        }
    }

    public void reset() {
        Animation animation = this.currentAnimation;
        if (animation != null) {
            animation.reset();
        }
    }

    public int getWidth() {
        Animation animation = this.currentAnimation;
        if (animation != null) {
            return animation.getWidth();
        }
        return 0;
    }

    public int getHeight() {
        Animation animation = this.currentAnimation;
        if (animation != null) {
            return animation.getHeight();
        }
        return 0;
    }

    @Override
    public void dispose() {
        this.resourceDisposer.disposeAll();
        this.animations.clear();
    }

    public boolean isRunning() {
        Animation animation = this.currentAnimation;
        if (animation != null) {
            return animation.isRunning();
        }
        return false;
    }
}
