package ru.vaselevs.amentia.core.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import ru.vaselevs.amentia.core.enemy.EntityEnemyBoss;
import ru.vaselevs.amentia.core.collision.CollisionManager;
import ru.vaselevs.amentia.core.enemy.EntityEnemyCaterpillar;
import ru.vaselevs.amentia.core.enemy.EntityEnemyDerelict;
import ru.vaselevs.amentia.core.enemy.EntityEnemyHedgehog;
import ru.vaselevs.amentia.core.entity.EntityBase;
import ru.vaselevs.amentia.core.hud.HealthHUD;
import ru.vaselevs.amentia.core.image.WorldBackgroundImage;
import ru.vaselevs.amentia.core.input.InputManager;
import ru.vaselevs.amentia.core.player.EntityPlayer;
import ru.vaselevs.amentia.core.resource.ResourceDisposer;
import ru.vaselevs.amentia.core.stage.StageManager;
import ru.vaselevs.amentia.core.stage.StageWorld;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by CoreX on 23.03.2015.
 */
public class WorldForest extends WorldBase {
    private static final int WORLD_WIDTH = 8840;
    private static final int WORLD_HEIGHT = 800;

    private ResourceDisposer resourceDisposer;

    private CollisionManager collisionManager;

    private OrthographicCamera camera;

    private EntityPlayer player;

    private List<EntityBase> entities = new LinkedList<>();

    private Deque<EntityBase> spawnQueue = new ArrayDeque<>();
    private Deque<EntityBase> despawnQueue = new ArrayDeque<>();

    // renderable
    private WorldBackgroundImage backgroundImage;
    private WorldBackgroundImage groundImage;

    private HealthHUD healthHUD;

    public WorldForest(StageManager stageManager, SpriteBatch batch, SpriteBatch batchUI) {
        super(stageManager, batch, batchUI, WORLD_WIDTH, WORLD_HEIGHT);
        this.resourceDisposer = new ResourceDisposer();

        this.initializeCamera();
        this.initializeRenderer();
        this.initializeCollideManager();

        this.spawnEnemies();
        this.spawnPlayer(100, 50);

        this.healthHUD = new HealthHUD(this.getBatchUI());
        this.resourceDisposer.addResource(this.healthHUD);
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

        List<String> backgroundFiles = new LinkedList<>();
        backgroundFiles.add("background/forest_background/area1.png");
        backgroundFiles.add("background/forest_background/area2.png");
        backgroundFiles.add("background/forest_background/area3.png");
        backgroundFiles.add("background/forest_background/area4.png");

        this.backgroundImage = new WorldBackgroundImage(getBatch(), backgroundFiles);
        this.resourceDisposer.addResource(this.backgroundImage);

        List<String> groundFiles = new LinkedList<>();
        groundFiles.add("background/forest_background/gArea1.png");
        groundFiles.add("background/forest_background/gArea2.png");
        groundFiles.add("background/forest_background/gArea3.png");
        groundFiles.add("background/forest_background/gArea4.png");

        this.groundImage = new WorldBackgroundImage(getBatch(), groundFiles);
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

        for (int i = 1; i < 15; ++i) {
            this.spawnEntity(new EntityEnemyCaterpillar(this, i * 1250, 50));
            this.spawnEntity(new EntityEnemyBoss(this, i * 1000, 100));
        }

        this.spawnEntity(new EntityEnemyBoss(this, 1000, 100));
    }

    @Override
    public void render() {
        this.backgroundImage.draw(0, 200, this.backgroundImage.getWidth(), this.backgroundImage.getHeight());
        this.groundImage.draw(0, 0, this.groundImage.getWidth(), this.groundImage.getHeight());
        this.entities.forEach(e -> e.render());
        //this.collisionManager.renderDebug(this.getCamera());
    }

    @Override
    public void renderUI() {
        Matrix4 uiMatrix = this.getCamera().combined.cpy();
        uiMatrix.setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.getBatchUI().setProjectionMatrix(uiMatrix);
        this.healthHUD.healthPoints = this.player.getHealthPoints();
        this.healthHUD.render();
    }

    @Override
    public void update(float deltaTime) {
        if (InputManager.isPressedEscape()) {
            this.getStageManager().popStage();
            //return;
        }

        if (InputManager.isPressedRestart()) {
            this.getStageManager().replaceCurrentStage(new StageWorld(this.getStageManager()));
            //return;
        }

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
