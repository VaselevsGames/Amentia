package ru.vaselevs.amentia.core.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.vaselevs.amentia.core.image.UserInterfaceImage;
import ru.vaselevs.amentia.core.resource.IDisposable;
import ru.vaselevs.amentia.core.resource.ResourceDisposer;

/**
 * Created by CoreX on 04.04.2015.
 */
public class HealthHUD implements IDisposable {
    private ResourceDisposer resourceDisposer;
    private UserInterfaceImage healthbarOwner;
    private UserInterfaceImage healthbarBackground;
    private UserInterfaceImage healthbar;

    public float healthPoints;
    private float healthbarStep;

    public HealthHUD(SpriteBatch batch) {
        this.resourceDisposer = new ResourceDisposer();
        this.healthPoints = 100f;
        this.healthbarOwner = new UserInterfaceImage(batch, "hud/hero/healthbar_owner.png");
        this.healthbarBackground = new UserInterfaceImage(batch, "hud/hero/healthbar_background.png");
        this.healthbar = new UserInterfaceImage(batch, "hud/hero/healthbar.png");
        this.healthbarStep = healthbar.getWidth() / 100f;
    }

    public void render() {
        this.healthbarBackground.draw(
                9,
                Gdx.graphics.getHeight() - 10 - 150,
                this.healthbarBackground.getWidth(),
                this.healthbarBackground.getHeight()
        );

        this.healthbar.draw(
                9,
                Gdx.graphics.getHeight() - 10 - 150,
                (int)(this.healthbarStep * this.healthPoints),
                this.healthbar.getHeight()
        );

        this.healthbarOwner.draw(
                10,
                Gdx.graphics.getHeight() - this.healthbarOwner.getHeight() - 10,
                this.healthbarOwner.getWidth(),
                this.healthbarOwner.getHeight()
        );
    }

    @Override
    public void dispose() {
        this.resourceDisposer.disposeAll();
    }
}
