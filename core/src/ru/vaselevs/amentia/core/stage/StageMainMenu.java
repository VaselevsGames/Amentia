package ru.vaselevs.amentia.core.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import ru.vaselevs.amentia.core.font.Font;
import ru.vaselevs.amentia.core.game.GameConstants;

/**
 * Created by CoreX on 22.03.2015.
 */

public class StageMainMenu extends BaseStage {
    SpriteBatch batch;
    Stage stage;
    Skin uiSkin;

    // font
    private Font font_captureIt;
    private Font font_malahit;

    private BitmapFont buttonFont;
    private BitmapFont labelFont;

    // textures
    Texture backgroundTexture;

    // buttons
    TextButton buttonStartGame;
    TextButton buttonAbout;
    TextButton buttonExitGame;
    Label gameTitle;

    public StageMainMenu(StageManager stageManager) {
        super(stageManager);
        this.stage = getStage();
        this.batch = getBatch();

        this.loadBackground();
        this.loadUISkin();
        this.initializeButtonFont();
        this.initializeLabelFont();

        Gdx.input.setInputProcessor(stage);
        this.createGameTittle();
        this.createButtons();
    }

    private void createGameTittle() {
        this.gameTitle = new Label(GameConstants.GAME_TITLE, uiSkin, "title");
        float x = Gdx.graphics.getWidth() - gameTitle.getWidth() - 45;
        float y = Gdx.graphics.getHeight() - 50;
        this.gameTitle.setPosition(x, y);
        this.stage.addActor(this.gameTitle);
    }

    private void createButtons() {
        this.buttonStartGame = this.makeStartGameButton();
        this.buttonAbout = this.makeAboutButton();
        this.buttonExitGame = this.makeExitGameButton();
        this.stage.addActor(this.buttonStartGame);
        this.stage.addActor(this.buttonAbout);
        this.stage.addActor(this.buttonExitGame);
    }

    private void initializeButtonFont() {
        this.font_captureIt = new Font("Capture_it");
        this.buttonFont = font_captureIt.generateFont(18);
        TextButton.TextButtonStyle style = this.uiSkin.get("default", TextButton.TextButtonStyle.class);
        style.font = this.buttonFont;
    }

    private void initializeLabelFont() {
        this.font_malahit = new Font("Malahit");
        this.labelFont = font_malahit.generateFont(24);
        Label.LabelStyle style = this.uiSkin.get("title", Label.LabelStyle.class);
        style.font = this.labelFont;
    }

    private void loadBackground() {
        this.backgroundTexture = new Texture(Gdx.files.internal("images/menu_background.jpg"));
    }

    private void loadUISkin() {
        this.uiSkin = new Skin(Gdx.files.internal("skins/uiskin.json"));
    }

    private TextButton makeStartGameButton() {
        TextButton button = new TextButton("Новая игра", uiSkin, "default");
        float width = 230f;
        float height = 50f;
        float x = Gdx.graphics.getWidth() - width - 65;
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
        float width = 230f;
        float height = 50f;
        float x = Gdx.graphics.getWidth() - width - 65;
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
        float width = 230f;
        float height = 50f;
        float x = Gdx.graphics.getWidth() - width - 65;
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
        this.batch.begin();
        this.batch.draw(this.backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.batch.end();

        this.stage.draw();
    }

    @Override
    public void handleUpdate(float deltaTime) {
        this.stage.act(deltaTime);
    }

    @Override
    public void handlePause(boolean isPaused) {
        System.out.println("changed game state: " + (isPaused ? "pause" : "resume"));
    }

    @Override
    protected void unloadStage() {
        buttonFont.dispose();
        labelFont.dispose();
        uiSkin.dispose();
        stage.dispose();
        batch.dispose();
        font_captureIt.dispose();
        font_malahit.dispose();
    }

    private void startGameClickHandler() {
        getStageManager().pushStage(new StageWorld( getStageManager() ));
    }

    private void aboutClickHandler() {
        System.out.println("about button clicked");
    }

    private void exitGameClickHandler() {
        System.out.println("exit game button clicked");
        Gdx.app.exit();
    }
}
