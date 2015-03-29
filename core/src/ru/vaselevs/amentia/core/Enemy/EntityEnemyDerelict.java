package ru.vaselevs.amentia.core.enemy;

import ru.vaselevs.amentia.core.animation.Animation;
import ru.vaselevs.amentia.core.animation.AnimationManager;
import ru.vaselevs.amentia.core.entity.EntityBase;
import ru.vaselevs.amentia.core.resource.ResourceDisposer;
import ru.vaselevs.amentia.core.world.WorldBase;

/**
 * Created by Brook on 28.03.2015.
 */
public class EntityEnemyDerelict extends EntityBase {
    private ResourceDisposer resourceDisposer;

    private AnimationManager animationManager;

    private float healthPoint;

    public EntityEnemyDerelict(WorldBase world, float x, float y) {
        super(world, x, y);
        this.width = 101;
        this.height = 150;
        this.healthPoint = 100f;

        this.resourceDisposer = new ResourceDisposer();
        this.animationManager = new AnimationManager();

        this.animationManager.add("move", new Animation("enemy/Derelict_2x1.png", world.getBatch(), 2, 0.2f, true));
        this.animationManager.add("death", new Animation("enemy/EnemyDeath.png", world.getBatch(), 2, 0f, false));

        this.animationManager.play("move");
    }

    @Override
    public void render() {
        this.animationManager.render(this.x, this.y, this.width, this.height, false, false);
    }

    @Override
    public void update(float deltaTime) {
        this.animationManager.update(deltaTime);
        this.x += -1 * 250f * deltaTime;
    }

    @Override
    public void damage(int hit) {
        this.healthPoint -= hit;
        if (this.healthPoint <= 0 ) {
            this.animationManager.play("death");
        }
    }

    @Override
    public void dispose() {
        this.animationManager.dispose();
    }
}
