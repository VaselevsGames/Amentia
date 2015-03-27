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
        this.animations = new HashMap<String, Animation>();
    }

    public void add(String name, Animation animation) {
        this.resourceDisposer.addResource(animation);
        this.animations.put(name, animation);
    }

    public void render(float x, float y, float width, float height, boolean flipX, boolean flipY) {
        Animation animation = this.currentAnimation;
        if (animation != null) {
            animation.render(x, y, width, height, flipX, flipY);
        }
    }

    public void update(float deltaTime) {
        Animation animation = this.currentAnimation;
        if (animation != null) {
            animation.update(deltaTime);
        }
    }

    public boolean play(String animationName) {
        if (this.animations.containsKey(animationName)) {
            this.currentAnimation = this.animations.get(animationName);
            this.currentAnimation.play();
            return true;
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

    @Override
    public void dispose() {
        this.resourceDisposer.disposeAll();
        this.animations.clear();
    }
}
