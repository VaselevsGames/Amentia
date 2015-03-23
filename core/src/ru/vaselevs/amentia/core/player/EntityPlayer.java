package ru.vaselevs.amentia.core.player;

import com.badlogic.gdx.graphics.Camera;
import ru.vaselevs.amentia.core.entity.EntityBase;
import ru.vaselevs.amentia.core.world.WorldBase;

/**
 * Created by CoreX on 23.03.2015.
 */
public class EntityPlayer extends EntityBase {

    private Camera camera;

    public EntityPlayer(WorldBase world) {
        super(world);
        this.camera = null;
    }

    public void attachCamera(Camera camera) {
        this.camera = camera;
    }

    public void detachCamera() {
        this.camera = null;
    }

    public void updateCamera() {
        if( this.camera != null ) {

        }
    }


}
