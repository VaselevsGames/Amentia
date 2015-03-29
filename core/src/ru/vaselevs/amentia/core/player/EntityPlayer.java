package ru.vaselevs.amentia.core.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.collision.BoundingBox;
import ru.vaselevs.amentia.core.animation.Animation;
import ru.vaselevs.amentia.core.animation.AnimationManager;
import ru.vaselevs.amentia.core.entity.EntityBase;
import ru.vaselevs.amentia.core.resource.ResourceDisposer;
import ru.vaselevs.amentia.core.world.WorldBase;

/**
 * Created by CoreX on 23.03.2015.
 */
public class EntityPlayer extends EntityBase {
    private float x;
    private float y;
    private float width;
    private float height;

    /*
    values:
    -1: LEFT
    +1: RIGHT
     */
    private int hDirection;

    /*
    values:
    -1: DOWN
    +1: UP
     */
    private int vDirection;

    private ResourceDisposer resourceDisposer;

    private AnimationManager animationManager;

    private float maxJumpHeight;
    private float currentJumpHeight;
    private boolean isJumping;

    private OrthographicCamera camera;

    public EntityPlayer(WorldBase world) {
        super(world);
        this.camera = null;
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.camera.position.set(this.camera.viewportWidth / 2f, this.camera.viewportHeight / 2f, 0);

        this.x = 100;
        this.y = 50;
        this.width = 112;
        this.height = 150;
        this.vDirection = 0;

        this.resourceDisposer = new ResourceDisposer();
        this.animationManager = new AnimationManager();

        this.animationManager.add("move", new Animation("hero/Move_4x1.png", world.getBatch(), 4, 112, 150, 0.15f, true));
        this.animationManager.add("jump", new Animation("hero/Jump.png", world.getBatch(), 1, 118, 149, 0f, true));

        this.maxJumpHeight = 50f;
        this.currentJumpHeight = 0f;
        this.isJumping = false;

        this.hDirection = 0;
    }

    @Override
    public void render() {
        this.getWorld().getBatch().setProjectionMatrix(this.camera.combined);
        boolean flipX = this.hDirection == -1 ? true : false;
        this.animationManager.render(this.x, this.y, this.width, this.height, flipX, false);
    }

    @Override
    public void update(float deltaTime) {
        this.animationManager.update(deltaTime);

        int hDirection = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            hDirection += 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            hDirection -= 1;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            doJumping();
        }

        doMoving();

        this.updateCamera(hDirection, deltaTime);

        this.animationManager.stop();

        if (hDirection != 0) {
            this.animationManager.play("move");
            this.hDirection = hDirection;
        } else {
            this.animationManager.reset();
        }
    }

    @Override
    public void damage(int hit) {

    }

    private void doMoving() {

    }

    private void doJumping() {
        this.hDirection = 1;
        this.isJumping = true;
    }

    private void updateCamera(int direction, float deltaTime) {
        this.x += direction * 250f * deltaTime;
        this.camera.position.x = this.x + this.width / 2;

        BoundingBox boundingBox = this.getWorld().getBoundingBox();

        if (this.camera.position.x < boundingBox.min.x) {
            this.camera.position.x = boundingBox.min.x;
        }
        if (this.camera.position.x > boundingBox.max.x) {
            this.camera.position.x = boundingBox.max.x;
        }
        this.camera.update();
    }

    @Override
    public void dispose() {
        this.animationManager.dispose();
    }
}
