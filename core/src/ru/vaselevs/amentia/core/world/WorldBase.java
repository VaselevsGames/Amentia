package ru.vaselevs.amentia.core.world;

import com.badlogic.gdx.graphics.Camera;
import ru.vaselevs.amentia.core.entity.EntityBase;
import ru.vaselevs.amentia.core.player.EntityPlayer;

/**
 * Created by CoreX on 23.03.2015.
 */
public class WorldBase {
    private float width;
    private float height;

    private EntityPlayer player;
    private Camera camera;

    public void updateCamera() {
        player.updateCamera();
    }

}
