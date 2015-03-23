package ru.vaselevs.amentia.core.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import ru.vaselevs.amentia.core.image.BackgroundImage;
import ru.vaselevs.amentia.core.resource.ResourceDisposer;

/**
 * Created by CoreX on 22.03.2015.
 */
public class StageWorld extends StageBase {

    private BackgroundImage backgroundImage;
    private OrthographicCamera camera;

    private BoundingBox boundingBox;

    private ResourceDisposer resourceDisposer;

    public StageWorld(StageManager stageManager) {
        super(stageManager);
        this.resourceDisposer = new ResourceDisposer();

        this.backgroundImage = new BackgroundImage(getBatch(), "forest_background.jpg");
        this.resourceDisposer.addResource(this.backgroundImage);

        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.camera.position.set(this.camera.viewportWidth / 2f, this.camera.viewportHeight / 2f, 0);

        this.boundingBox = getBoundingBox();
    }

    public BoundingBox getBoundingBox() {
        float screenCenterX = Gdx.graphics.getWidth() / 2;
        float screenCenterY = Gdx.graphics.getHeight() / 2;
        Vector3 min = new Vector3(screenCenterX, screenCenterY, 0f);
        Vector3 max = new Vector3(this.backgroundImage.getWidth() - screenCenterX, screenCenterY, 0f);
        return new BoundingBox(min, max);
    }

    @Override
    protected void handleUpdate(float deltaTime) {
        int direction = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            direction += 1;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            direction -= 1;
        }

        this.camera.translate(direction * 250f * deltaTime, 0f);

        if (this.camera.position.x < this.boundingBox.min.x) {
            this.camera.position.x = this.boundingBox.min.x;
        }
        if (this.camera.position.x > this.boundingBox.max.x) {
            this.camera.position.x = this.boundingBox.max.x;
        }
        this.camera.update();
    }

    @Override
    protected void handleRender() {
        super.handleRender();
        this.getBatch().setProjectionMatrix(this.camera.combined);
        this.getBatch().begin();
        this.backgroundImage.draw(0, 0, this.backgroundImage.getWidth(), this.backgroundImage.getHeight());
        this.getBatch().end();

    }

    @Override
    protected void unloadStage() {
        super.unloadStage();
        this.resourceDisposer.disposeAll();
    }
}
