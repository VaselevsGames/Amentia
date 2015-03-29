package ru.vaselevs.amentia.core.renderer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.vaselevs.amentia.core.image.BackgroundImage;
import ru.vaselevs.amentia.core.resource.ResourceDisposer;
import ru.vaselevs.amentia.core.world.WorldBase;

/**
 * Created by CoreX on 27.03.2015.
 */
public class WorldRendererFirst extends WorldRendererBase {

    private ResourceDisposer resourceDisposer;

    private BackgroundImage backgroundImage;
    private BackgroundImage groundImage;

    public WorldRendererFirst(WorldBase world, SpriteBatch batch) {
        super(world, batch);

        this.resourceDisposer = new ResourceDisposer();

        this.backgroundImage = new BackgroundImage(getBatch(), "forest_background.jpg");
        this.resourceDisposer.addResource(this.backgroundImage);

        this.groundImage = new BackgroundImage(getBatch(), "ground.png");
        this.resourceDisposer.addResource(this.groundImage);
    }

    @Override
    public void render() {
        this.backgroundImage.draw(0, 230, this.backgroundImage.getWidth(), this.backgroundImage.getHeight());
        this.groundImage.draw(0, 0, this.groundImage.getWidth(), this.groundImage.getHeight());
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void dispose() {
        resourceDisposer.disposeAll();
    }
}
