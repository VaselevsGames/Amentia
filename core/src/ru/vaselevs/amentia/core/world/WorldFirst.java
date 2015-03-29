package ru.vaselevs.amentia.core.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.vaselevs.amentia.core.entity.EntityBase;
import ru.vaselevs.amentia.core.player.EntityPlayer;
import ru.vaselevs.amentia.core.enemy.EntityEnemyCaterpillar;
import ru.vaselevs.amentia.core.enemy.EntityEnemyDerelict;
import ru.vaselevs.amentia.core.enemy.EntityEnemyHedgehog;
import ru.vaselevs.amentia.core.renderer.WorldRendererFirst;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by CoreX on 23.03.2015.
 */
public class WorldFirst extends WorldBase {

    private static final int WORLD_WIDTH = 8842;
    private static final int WORLD_HEIGHT = 850;

    private WorldRendererFirst worldRenderer;

    private EntityPlayer player;

    private List<EntityBase> enemies = new LinkedList<>();

    public WorldFirst(SpriteBatch batch) {
        super(batch, WORLD_WIDTH, WORLD_HEIGHT);
        this.worldRenderer = new WorldRendererFirst(this, getBatch());
        this.player = new EntityPlayer(this, 100,50);

        this.enemies.add(new EntityEnemyDerelict(this,6500,50));
        this.enemies.add(new EntityEnemyDerelict(this,1500,50));
        this.enemies.add(new EntityEnemyHedgehog(this, 8000,50));
        for (int i = 1; i < 5; ++i) {
            this.enemies.add(new EntityEnemyCaterpillar(this, i*1000,50));
        }
    }

    @Override
    public void render() {
        this.worldRenderer.render();
        this.player.render();
        this.enemies.forEach( (e) -> e.render() );
    }

    @Override
    public void update(float deltaTime) {
        this.worldRenderer.update(deltaTime);
        this.player.update(deltaTime);
        this.enemies.forEach( (e) -> e.update(deltaTime) );
    }

    @Override
    public void dispose() {
        worldRenderer.dispose();
        super.dispose();
    }
}
