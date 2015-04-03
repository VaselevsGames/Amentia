package ru.vaselevs.amentia.core.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.vaselevs.amentia.core.enemy.EntityEnemyCaterpillar;
import ru.vaselevs.amentia.core.enemy.EntityEnemyDerelict;
import ru.vaselevs.amentia.core.enemy.EntityEnemyHedgehog;
import ru.vaselevs.amentia.core.entity.EntityBase;
import ru.vaselevs.amentia.core.image.BackgroundImage;
import ru.vaselevs.amentia.core.physics.CollideManager;
import ru.vaselevs.amentia.core.player.EntityPlayer;
import ru.vaselevs.amentia.core.resource.ResourceDisposer;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by CoreX on 23.03.2015.
 */
public class WorldFirst extends WorldBase {
    private ResourceDisposer resourceDisposer;

    private static final int WORLD_WIDTH = 8842;
    private static final int WORLD_HEIGHT = 800;

    private CollideManager collideManager;

    private OrthographicCamera camera;

    private EntityPlayer player;

    private List<EntityBase> entities = new LinkedList<>();

    // renderable
    private BackgroundImage backgroundImage;
    private BackgroundImage groundImage;

    public WorldFirst(SpriteBatch batch) {
        super(batch, WORLD_WIDTH, WORLD_HEIGHT);
        this.resourceDisposer = new ResourceDisposer();

        this.initializeCamera();
        this.initializeRenderer();
        this.initializePhysics();

        this.spawnPlayer(100, 50);
        this.spawnEnemies();
    }

    private void initializeCamera() {
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
    }

    private void initializePhysics() {
        this.collideManager = new CollideManager(this);
    }

    public CollideManager getCollideManager() {
        return this.collideManager;
    }

    private void initializeRenderer() {
        this.backgroundImage = new BackgroundImage(getBatch(), "forest_background.png");
        this.resourceDisposer.addResource(this.backgroundImage);
        this.groundImage = new BackgroundImage(getBatch(), "ground.png");
        this.resourceDisposer.addResource(this.groundImage);
    }

    private void spawnPlayer(float x, float y) {
        this.player = new EntityPlayer(this, x, y);
        this.resourceDisposer.addResource(this.player);

        this.getCollideManager().addEntity(this.player);
    }

    private void spawnEnemies() {
        this.entities.add(new EntityEnemyDerelict(this, 6500, 50));
        this.entities.add(new EntityEnemyDerelict(this, 1500, 50));
        this.entities.add(new EntityEnemyHedgehog(this, 8000, 50));

        for (int i = 1; i < 5; ++i) {
            this.entities.add(new EntityEnemyCaterpillar(this, i * 1000, 50));
        }

        // copy entities to physicsmanager
        this.entities.forEach(enemy -> this.getCollideManager().addEntity(enemy));
    }

    @Override
    public void render() {
        this.backgroundImage.draw(0, 200, this.backgroundImage.getWidth(), this.backgroundImage.getHeight());
        this.groundImage.draw(0, 0, this.groundImage.getWidth(), this.groundImage.getHeight());
        this.entities.forEach(e -> e.render());
        this.player.render();
        //this.collideManager.renderDebug(this.getCamera());
    }

    @Override
    public void update(float deltaTime) {
        this.collideManager.update(deltaTime);
        //this.player.update(deltaTime);
        //this.entities.forEach(e -> e.update(deltaTime));
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
