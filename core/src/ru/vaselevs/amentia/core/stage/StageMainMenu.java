package ru.vaselevs.amentia.core.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import ru.vaselevs.amentia.core.font.Font;
import ru.vaselevs.amentia.core.game.GameConstants;
import ru.vaselevs.amentia.core.helper.MathHelper;
import ru.vaselevs.amentia.core.image.BackgroundImage;
import ru.vaselevs.amentia.core.resource.ResourceDisposer;

/**
 * Created by CoreX on 22.03.2015.
 */

public class StageMainMenu extends StageBase {
    private ResourceDisposer resourceDisposer;
    private Skin uiSkin;

    private static final float PANEL_BOUND_FROM = 560;
    private static final float PANEL_BOUND_TO = 1000;

    // font
    private Font font_captureIt;
    private BitmapFont buttonFont;
    private Font font_malahit;
    private BitmapFont labelFont;

    // textures
    private BackgroundImage backgroundImage;

    public StageMainMenu(StageManager stageManager) {
        super(stageManager);
        this.resourceDisposer = new ResourceDisposer();
        this.loadResources();
        this.initializeMenu();
    }

    private void loadResources() {
        this.loadBackground();
        this.loadUISkin();
    }

    private void initializeMenu() {
        this.initializeButtonFont();
        this.initializeLabelFont();
        this.createGameTittle();
        this.createButtons();
    }

    private void loadBackground() {
        this.backgroundImage = new BackgroundImage(getBatch(), "menu_background.png");
        // add resources to disposer
        this.resourceDisposer.addResource(backgroundImage);
    }

    private void loadUISkin() {
        this.uiSkin = new Skin(Gdx.files.internal("skins/uiskin.json"));
        // add resources to disposer
        this.resourceDisposer.addResource(this.uiSkin);
    }

    private void initializeButtonFont() {
        this.font_captureIt = new Font("Capture_it");
        this.buttonFont = font_captureIt.generateFont(18);
        TextButton.TextButtonStyle style = this.uiSkin.get("default", TextButton.TextButtonStyle.class);
        style.font = this.buttonFont;
        // add resources to disposer
        this.resourceDisposer.addResource(this.font_captureIt);
        this.resourceDisposer.addResource(this.buttonFont);
    }

    private void initializeLabelFont() {
        this.font_malahit = new Font("Malahit");
        this.labelFont = font_malahit.generateFont(24);
        Label.LabelStyle style = this.uiSkin.get("text", Label.LabelStyle.class);
        style.font = this.labelFont;
        // add resources to disposer
        this.resourceDisposer.addResource(this.font_malahit);
        this.resourceDisposer.addResource(this.labelFont);
    }

    private void createGameTittle() {
        Label label = new Label(GameConstants.GAME_TITLE, uiSkin, "text");
        float x = MathHelper.calculateCenter(PANEL_BOUND_FROM, PANEL_BOUND_TO, label.getWidth());
        float y = Gdx.graphics.getHeight() - 50;
        label.setPosition(x, y);
        this.getStage().addActor(label);
    }

    private void createButtons() {
        this.createStartGameButton();
        this.createAboutButton();
        this.createExitGameButton();
    }

    private void createStartGameButton() {
        TextButton button = new TextButton("Новая игра", uiSkin, "default");
        float width = 300f;
        float height = 50f;
        float x = MathHelper.calculateCenter(PANEL_BOUND_FROM, PANEL_BOUND_TO, width);
        float y = Gdx.graphics.getHeight() - height - (40 + 60 * 3);
        button.setBounds(x, y, width, height);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                startGameClickHandler();
            }
        });
        this.getStage().addActor(button);
    }

    private void createAboutButton() {
        TextButton button = new TextButton("Об игре ...", uiSkin, "default");
        float width = 300f;
        float height = 50f;
        float x = MathHelper.calculateCenter(PANEL_BOUND_FROM, PANEL_BOUND_TO, width);
        float y = Gdx.graphics.getHeight() - height - (40 + 60 * 4);
        button.setBounds(x, y, width, height);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                aboutClickHandler();
            }
        });
        this.getStage().addActor(button);
    }

    private void createExitGameButton() {
        TextButton button = new TextButton("Выйти из игры", uiSkin, "default");
        float width = 300f;
        float height = 50f;
        float x = MathHelper.calculateCenter(PANEL_BOUND_FROM, PANEL_BOUND_TO, width);
        float y = Gdx.graphics.getHeight() - height - (40 + 60 * 5);
        button.setBounds(x, y, width, height);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                exitGameClickHandler();
            }
        });
        this.getStage().addActor(button);
    }

    @Override
    public void handleRender() {
        super.handleRender();
        this.getBatch().begin();
        this.backgroundImage.draw(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.getBatch().end();
        this.getStage().draw();
    }

    @Override
    public void handleUpdate(float deltaTime) {
        this.getStage().act(deltaTime);
    }

    @Override
    public void handlePause(boolean isPaused) {
        super.handlePause(isPaused);
    }

    @Override
    public void unloadStage() {
        super.unloadStage();
        this.resourceDisposer.disposeAll();
    }

    private void startGameClickHandler() {
        this.getStageManager().pushStage(new StageWorld(getStageManager()));
    }

    private void aboutClickHandler() {
        this.getStageManager().pushStage(new StageAbout(getStageManager()));
    }

    private void exitGameClickHandler() {
        Gdx.app.exit();
    }
}
