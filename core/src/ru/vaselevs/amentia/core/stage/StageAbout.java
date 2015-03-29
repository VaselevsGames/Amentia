package ru.vaselevs.amentia.core.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import ru.vaselevs.amentia.core.font.Font;
import ru.vaselevs.amentia.core.game.GameConstants;
import ru.vaselevs.amentia.core.input.InputManager;
import ru.vaselevs.amentia.core.resource.ResourceDisposer;


/**
 * Created by CoreX on 29.03.2015.
 */
public class StageAbout extends StageBase {
    private ResourceDisposer resourceDisposer;
    private Skin uiSkin;

    private Font font;
    private BitmapFont labelBitmapFont;

    public StageAbout(StageManager stageManager) {
        super(stageManager);
        this.resourceDisposer = new ResourceDisposer();
        this.loadResources();
        this.createLabel();
    }

    private void loadResources() {
        this.loadBackground();
        this.initializeFont();
        this.loadUISkin();
    }

    private void loadBackground() {

    }

    private void loadUISkin() {
        this.uiSkin = new Skin(Gdx.files.internal("skins/uiskin.json"));
        this.resourceDisposer.addResource(this.uiSkin);
        Label.LabelStyle style = this.uiSkin.get("text", Label.LabelStyle.class);
        style.font = this.labelBitmapFont;
    }

    private void initializeFont() {
        this.font = new Font("DroidSans");
        this.labelBitmapFont = font.generateFont(18);
        this.resourceDisposer.addResource(this.font);
        this.resourceDisposer.addResource(this.labelBitmapFont);
    }

    private void createLabel() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ Об игре ]\n");
        stringBuilder.append("> ...\n");
        stringBuilder.append("\n");
        stringBuilder.append("[ Разработчики ]\n");
        stringBuilder.append("> NstTaInt\n");
        stringBuilder.append("    animation\n");
        stringBuilder.append("    game-design\n");
        stringBuilder.append("> DarkoreXOR\n");
        stringBuilder.append("    game-development\n");
        stringBuilder.append("    testing\n");
        stringBuilder.append("> MorozovArtem\n");
        stringBuilder.append("    programming\n");
        stringBuilder.append("    gameplay testing\n");
        stringBuilder.append("\n");
        stringBuilder.append("[ Информация о разработке ]\n");
        stringBuilder.append("> Версия: " + GameConstants.GAME_VERSION + "\n");
        stringBuilder.append("> Использовано в разработке: libGDX framework\n");

        Label label = new Label(stringBuilder, uiSkin, "text");
        label.setAlignment(Align.topLeft);

        label.setPosition(100, 100);
        label.setSize(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 200);
        this.getStage().addActor(label);
    }

    @Override
    public void handleRender() {
        super.handleRender();
        this.getStage().draw();
    }

    @Override
    protected void handleUpdate(float deltaTime) {
        super.handleUpdate(deltaTime);
        if(InputManager.isPressedEscape()) {
            this.getStageManager().popStage();
        }
    }

    @Override
    public void unloadStage() {
        this.resourceDisposer.disposeAll();
        super.unloadStage();
    }
}
