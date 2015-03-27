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
import ru.vaselevs.amentia.core.image.BackgroundImage;
import ru.vaselevs.amentia.core.resource.ResourceDisposer;

/**
 * Created by CoreX on 22.03.2015.
 */

public class StageMainMenu extends StageBase {
    Skin uiSkin;

    // font
    private Font font_captureIt;
    private Font font_malahit;

    private BitmapFont buttonFont;
    private BitmapFont labelFont;

    // textures
    private BackgroundImage backgroundImage;

    private ResourceDisposer resourceDisposer;

    // buttons
    TextButton buttonStartGame;
    TextButton buttonAbout;
    TextButton buttonExitGame;
    Label gameTitle;

    public StageMainMenu(StageManager stageManager) {
        super(stageManager);
        resourceDisposer = new ResourceDisposer();

        // add resources to disposer
        resourceDisposer.addResource(getStage());
        resourceDisposer.addResource(getBatch());

        this.loadBackground();
        this.loadUISkin();
        this.initializeButtonFont();
        this.initializeLabelFont();

        Gdx.input.setInputProcessor(getStage());
        this.createGameTittle();
        this.createButtons();
    }

    private void createGameTittle() {
        this.gameTitle = new Label(GameConstants.GAME_TITLE, uiSkin, "title");
        float x = Gdx.graphics.getWidth() - gameTitle.getWidth() - 130;
        float y = Gdx.graphics.getHeight() - 50;
        this.gameTitle.setPosition(x, y);
        this.getStage().addActor(this.gameTitle);
    }

    private void createButtons() {
        this.getStage().addActor(this.buttonStartGame = this.makeStartGameButton());
        this.getStage().addActor(this.buttonAbout = this.makeAboutButton());
        this.getStage().addActor(this.buttonExitGame = this.makeExitGameButton());
    }

    private void initializeButtonFont() {
        this.font_captureIt = new Font("Capture_it");
        this.buttonFont = font_captureIt.generateFont(18);
        TextButton.TextButtonStyle style = this.uiSkin.get("default", TextButton.TextButtonStyle.class);
        style.font = this.buttonFont;
        // add resources to disposer
        resourceDisposer.addResource(this.font_captureIt);
        resourceDisposer.addResource(this.buttonFont);
    }

    private void initializeLabelFont() {
        this.font_malahit = new Font("Malahit");
        this.labelFont = font_malahit.generateFont(24);
        Label.LabelStyle style = this.uiSkin.get("title", Label.LabelStyle.class);
        style.font = this.labelFont;
        // add resources to disposer
        resourceDisposer.addResource(this.font_malahit);
        resourceDisposer.addResource(this.labelFont);
    }

    private void loadBackground() {
        backgroundImage = new BackgroundImage(getBatch(), "menu_background.jpg");
        // add resources to disposer
        resourceDisposer.addResource(backgroundImage);
    }

    private void loadUISkin() {
        this.uiSkin = new Skin(Gdx.files.internal("skins/uiskin.json"));
        // add resources to disposer
        resourceDisposer.addResource(this.uiSkin);
    }

    private TextButton makeStartGameButton() {
        TextButton button = new TextButton("Новая игра", uiSkin, "default");
        float width = 430f;
        float height = 50f;
        float x = Gdx.graphics.getWidth() - width - 50;
        float y = Gdx.graphics.getHeight() - height - (40 + 60 * 3);
        button.setBounds(x, y, width, height);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                startGameClickHandler();
            }
        });
        return button;
    }

    private TextButton makeAboutButton() {
        TextButton button = new TextButton("Об игре ...", uiSkin, "default");
        float width = 430f;
        float height = 50f;
        float x = Gdx.graphics.getWidth() - width - 50;
        float y = Gdx.graphics.getHeight() - height - (40 + 60 * 4);
        button.setBounds(x, y, width, height);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                aboutClickHandler();
            }
        });
        return button;
    }

    private TextButton makeExitGameButton() {
        TextButton button = new TextButton("Выйти из игры", uiSkin, "default");
        float width = 430f;
        float height = 50f;
        float x = Gdx.graphics.getWidth() - width - 50;
        float y = Gdx.graphics.getHeight() - height - (40 + 60 * 5);
        button.setBounds(x, y, width, height);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                exitGameClickHandler();
            }
        });
        return button;
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
        System.out.println("changed game state: " + (isPaused ? "pause" : "resume"));
    }

    @Override
    protected void unloadStage() {
        this.resourceDisposer.disposeAll();
    }

    private void startGameClickHandler() {
        getStageManager().pushStage(new StageWorld(getStageManager()));
    }

    private void aboutClickHandler() {
        System.out.println("about button clicked");
    }

    private void exitGameClickHandler() {
        System.out.println("exit game button clicked");
        Gdx.app.exit();
    }
}
