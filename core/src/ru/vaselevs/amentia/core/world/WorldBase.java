package ru.vaselevs.amentia.core.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.physics.box2d.World;
import ru.vaselevs.amentia.core.entity.EntityBase;
import ru.vaselevs.amentia.core.resource.IDisposable;
import ru.vaselevs.amentia.core.stage.StageManager;

import javax.swing.text.html.parser.Entity;

/**
 * Created by CoreX on 23.03.2015.
 */
public abstract class WorldBase implements IDisposable {
    private float width;
    private float height;
    private SpriteBatch batch;
    private SpriteBatch batchUI;
    private BoundingBox boundingBox;
    private float groundLevel;
    private StageManager stageManager;

    protected WorldBase(StageManager stageManager, SpriteBatch batch, SpriteBatch batchUI, float width, float height) {
        this.stageManager = stageManager;
        this.batch = batch;
        this.batchUI = batchUI;
        this.width = width;
        this.height = height;
        this.boundingBox = this.makeBoundingBox();
        this.groundLevel = 50;
    }

    public StageManager getStageManager() {
        return this.stageManager;
    }

    public SpriteBatch getBatch() {
        return this.batch;
    }

    public SpriteBatch getBatchUI() {
        return this.batchUI;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    private BoundingBox makeBoundingBox() {
        float screenCenterX = Gdx.graphics.getWidth() / 2;
        float screenCenterY = Gdx.graphics.getHeight() / 2;
        Vector3 min = new Vector3(screenCenterX, screenCenterY, 0f);
        Vector3 max = new Vector3(getWidth() - screenCenterX, screenCenterY, 0f);
        return new BoundingBox(min, max);
    }

    public abstract void addEntityToSpawnQueue(EntityBase entity);

    protected abstract void spawnEntity(EntityBase entity);

    public BoundingBox getBoundingBox() {
        return this.boundingBox;
    }


    public float getGroundLevel() {
        return this.groundLevel;
    }

    protected abstract void render();

    protected abstract void renderUI();

    protected abstract void update(float deltaTime);

    public abstract Camera getCamera();

    @Override
    public abstract void dispose();

    public abstract void addEntityToDespawnQueue(EntityBase entity);
}
