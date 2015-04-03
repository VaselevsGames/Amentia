package ru.vaselevs.amentia.core.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.vaselevs.amentia.core.collision.CollisionManager;
import ru.vaselevs.amentia.core.enemy.EntityEnemyCaterpillar;
import ru.vaselevs.amentia.core.enemy.EntityEnemyDerelict;
import ru.vaselevs.amentia.core.enemy.EntityEnemyHedgehog;
import ru.vaselevs.amentia.core.entity.EntityBase;
import ru.vaselevs.amentia.core.image.BackgroundImage;
import ru.vaselevs.amentia.core.player.EntityPlayer;
import ru.vaselevs.amentia.core.resource.ResourceDisposer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by CoreX on 23.03.2015.
 */
public class WorldFirst extends WorldBase {
    private static final int WORLD_WIDTH = 8842;
    private static final int WORLD_HEIGHT = 800;

    private ResourceDisposer resourceDisposer;

    private CollisionManager collisionManager;

    private OrthographicCamera camera;

    private EntityPlayer player;

    private List<EntityBase> entities = new LinkedList<>();

    private Deque<EntityBase> spawnQueue = new ArrayDeque<>();
    private Deque<EntityBase> despawnQueue = new ArrayDeque<>();

    // renderable
    private BackgroundImage backgroundImage;
    private BackgroundImage groundImage;

    public WorldFirst(SpriteBatch batch) {
        super(batch, WORLD_WIDTH, WORLD_HEIGHT);
        this.resourceDisposer = new ResourceDisposer();

        this.initializeCamera();
        this.initializeRenderer();
        this.initializeCollideManager();

        this.spawnEnemies();
        this.spawnPlayer(100, 50);
    }

    private void initializeCamera() {
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
    }

    private void initializeCollideManager() {
        this.collisionManager = new CollisionManager(this);
    }

    public CollisionManager getCollisionManager() {
        return this.collisionManager;
    }

    private void initializeRenderer() {
        this.backgroundImage = new BackgroundImage(getBatch(), "forest_background.png");
        this.resourceDisposer.addResource(this.backgroundImage);
        this.groundImage = new BackgroundImage(getBatch(), "ground.png");
        this.resourceDisposer.addResource(this.groundImage);
    }

    private void spawnPlayer(float x, float y) {
        this.player = new EntityPlayer(this, x, y);
        this.spawnEntity(this.player);
    }


    @Override
    public void addEntityToSpawnQueue(EntityBase entity) {
        this.spawnQueue.addLast(entity);
    }

    @Override
    public void addEntityToDespawnQueue(EntityBase entity) {
        this.despawnQueue.addLast(entity);
    }

    @Override
    protected void spawnEntity(EntityBase entity) {
        this.entities.add(entity);
        this.getCollisionManager().addEntity(entity);
    }

    public boolean despawnEntity(EntityBase entity) {
        return this.collisionManager.removeEntity(entity) && this.entities.remove(entity);
    }

    private void spawnEnemies() {
        this.spawnEntity(new EntityEnemyDerelict(this, 6500, 50));
        this.spawnEntity(new EntityEnemyDerelict(this, 1500, 50));
        this.spawnEntity(new EntityEnemyHedgehog(this, 8000, 50));

        for (int i = 1; i < 5; ++i) {
            this.spawnEntity(new EntityEnemyCaterpillar(this, i * 1000, 50));
        }
    }

    @Override
    public void render() {
        this.backgroundImage.draw(0, 200, this.backgroundImage.getWidth(), this.backgroundImage.getHeight());
        this.groundImage.draw(0, 0, this.groundImage.getWidth(), this.groundImage.getHeight());
        this.entities.forEach(e -> e.render());
        //this.collisionManager.renderDebug(this.getCamera());
    }

    @Override
    public void update(float deltaTime) {
        this.despawnEntitiesFromQueue();
        this.spawnEntitiesFromQueue();
        this.collisionManager.update(deltaTime);
    }

    private void spawnEntitiesFromQueue() {
        while (this.spawnQueue.peekFirst() != null) {
            this.spawnEntity(this.spawnQueue.removeFirst());
        }
    }

    private void despawnEntitiesFromQueue() {
        while (this.despawnQueue.peekFirst() != null) {
            this.despawnEntity(this.despawnQueue.removeFirst());
        }
    }

    @Override
    public Camera getCamera() {
        return this.camera;
    }

    @Override
    public void dispose() {
        this.entities.forEach(e -> e.dispose());
        this.resourceDisposer.disposeAll();
    }
}
