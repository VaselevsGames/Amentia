package ru.vaselevs.amentia.core.player;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.collision.BoundingBox;
import ru.vaselevs.amentia.core.animation.Animation;
import ru.vaselevs.amentia.core.animation.AnimationManager;
import ru.vaselevs.amentia.core.entity.EntityBase;
import ru.vaselevs.amentia.core.input.InputManager;
import ru.vaselevs.amentia.core.resource.ResourceDisposer;
import ru.vaselevs.amentia.core.world.WorldBase;

/**
 * Created by CoreX on 23.03.2015.
 */

public class EntityPlayer extends EntityBase {
    private ResourceDisposer resourceDisposer;

    private AnimationManager animationManager;
    private PlayerState playerState;

    private float startJumpHeight;

    private int horizontalDirection;

    private float currentJumpHeight;
    private boolean isJumping;
    private boolean isFalling;
    private boolean isDead;

    private float healthPoints;

    public EntityPlayer(WorldBase world, float x, float y) {
        super(world, x, y);
        this.resourceDisposer = new ResourceDisposer();
        this.initializeAnimation();
        this.initializePlayer();
        this.width = 112;
        this.height = 150;
        this.name = "EntityPlayer";
        this.healthPoints = PlayerConstants.HEALTH_POINTS;
    }

    private void initializeAnimation() {
        this.animationManager = new AnimationManager();
        this.resourceDisposer.addResource(this.animationManager);

        SpriteBatch batch = this.getWorld().getBatch();

        this.animationManager.add("idle", new Animation("hero/Point.png", batch, 1, 0f, true));
        this.animationManager.add("walk", new Animation("hero/Move_4x1.png", batch, 4, 0.15f, true));
        this.animationManager.add("jump", new Animation("hero/Jump.png", batch, 1, 0f, true));
        this.animationManager.add("fall", new Animation("hero/Fall.png", batch, 1, 0f, true));
        this.animationManager.add("death", new Animation("hero/Death_5x1.png", batch, 5, 0.1f, false));
    }

    private void initializePlayer() {
        this.currentJumpHeight = 0f;
        this.isJumping = false;
        this.isFalling = false;
        this.isDead = false;
        this.horizontalDirection = 0;
        this.playerState = PlayerState.IDLE;
        this.switchState(PlayerState.IDLE);
    }

    @Override
    public void render() {
        this.getWorld().getBatch().setProjectionMatrix(this.getWorld().getCamera().combined);
        boolean flipX = this.horizontalDirection == -1 ? true : false;
        this.animationManager.render(this.x, this.y, this.width, this.height, flipX, false);
    }

    @Override
    public void update(float deltaTime) {
        this.animationManager.update(deltaTime);
        switch (this.playerState) {
            case IDLE:
                this.handleIdle(deltaTime);
                break;
            case WALK:
                this.handleWalk(deltaTime);
                break;
            case JUMP:
                this.handleJump(deltaTime);
                break;
            case FALL:
                this.handleFall(deltaTime);
                break;
            case DEATH:
                this.handleDeath(deltaTime);
                break;
        }
        this.updateCamera();
    }

    private void damage(float hit) {
        if(!this.isDead()) {
            this.healthPoints -= hit;
            if (this.healthPoints <= 0) {
                this.switchState(PlayerState.DEATH);
            }
        }
    }

    @Override
    public void collidedWith(EntityBase entity) {
        //System.out.println(this.name + " collided with " + entity.getName());
        damage(34f);
    }

    @Override
    public Rectangle getBodyRectangle() {
        float boundsDecrease = 10f;
        return new Rectangle(
                this.x + 5f + boundsDecrease,
                this.y + boundsDecrease,
                this.width - 2 * (5f + boundsDecrease),
                this.height - 2 * boundsDecrease
        );
    }

    private void handleIdle(float deltaTime) {
        if (InputManager.isPressedLeft() || InputManager.isPressedRight()) {
            this.switchState(PlayerState.WALK);
        } else if (InputManager.isPressedJump()) {
            this.switchState(PlayerState.JUMP);

        } else {
            // if no pressed keys -> play idle animation
            this.animationManager.play("idle");
        }
    }

    private void handleWalk(float deltaTime) {
        if (InputManager.isPressedJump()) {
            this.switchState(PlayerState.JUMP);
        } else if (InputManager.isPressedLeft() || InputManager.isPressedRight()) {
            // handle walk
            this.handlePlayerMoving(deltaTime, PlayerConstants.WALK_SPEED);
            this.animationManager.play("walk");
        } else {
            // if no pressed keys -> switch state to idle
            this.switchState(PlayerState.IDLE);
        }
    }

    private void handleJump(float deltaTime) {
        // handle jump
        this.animationManager.play("jump");

        if (!this.isJumping && !this.isFalling) {
            this.isJumping = true;
            this.startJumpHeight = this.y;
        }

        if (this.isJumping) {
            if (this.currentJumpHeight < PlayerConstants.MAX_JUMP_HEIGHT) {
                this.currentJumpHeight += PlayerConstants.JUMP_HEIGHT_STEP;
                this.y = this.startJumpHeight + this.currentJumpHeight;
            } else {
                this.isJumping = false;
                this.currentJumpHeight = PlayerConstants.MAX_JUMP_HEIGHT;
                this.isFalling = true;
                this.switchState(PlayerState.FALL);
            }
        }
        this.handlePlayerMoving(deltaTime, PlayerConstants.JUMP_HORIZONTAL_SPEED);
    }

    private void handleFall(float deltaTime) {
        // handle fall

        this.animationManager.play("fall");

        if (this.isFalling) {
            if (this.currentJumpHeight > 0f) {
                this.currentJumpHeight -= PlayerConstants.JUMP_HEIGHT_STEP;
                this.y = this.startJumpHeight + this.currentJumpHeight;
            } else {
                this.isFalling = false;
                this.currentJumpHeight = 0f;
                this.y = this.startJumpHeight;
                this.switchState(PlayerState.IDLE);
            }
        }
        this.handlePlayerMoving(deltaTime, PlayerConstants.JUMP_HORIZONTAL_SPEED);
    }

    private void handleDeath(float deltaTime) {
        // handle death
        this.isDead = true;
        this.animationManager.play("death");
        this.horizontalDirection = 1;
    }

    private void switchState(PlayerState newState) {
        this.playerState = newState;
    }

    private void handlePlayerMoving(float deltaTime, float moveSpeed) {
        int hDirection = 0;

        if (InputManager.isPressedLeft()) {
            hDirection += -1;
        }
        if (InputManager.isPressedRight()) {
            hDirection += 1;
        }

        this.x += hDirection * moveSpeed * deltaTime;

        float worldLeftBound = 0;
        float worldRightBound = this.getWorld().getWidth() - this.width;

        if (this.x <= worldLeftBound) {
            this.x = worldLeftBound;
        }

        if(this.x >= worldRightBound) {
            this.x = worldRightBound;
        }

        if (hDirection != 0) {
            this.horizontalDirection = hDirection;
        }
    }

    private void updateCamera() {
        Camera camera = this.getWorld().getCamera();
        camera.position.x = this.x + this.width / 2;
        BoundingBox boundingBox = this.getWorld().getBoundingBox();
        if (camera.position.x < boundingBox.min.x) {
            camera.position.x = boundingBox.min.x;
        }
        if (camera.position.x > boundingBox.max.x) {
            camera.position.x = boundingBox.max.x;
        }
        camera.update();
    }

    public boolean isDead() {
        return this.isDead;
    }

    @Override
    public void dispose() {
        this.resourceDisposer.disposeAll();
    }
}
