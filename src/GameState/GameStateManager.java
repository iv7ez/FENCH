package GameState;

import Main.GamePanel;

//MANAGER SOSTOYANIYA GAME
public class GameStateManager {


    private GameState[] gameStates;
    private int currentState;

    private PauseState pauseState;
    private boolean paused;

    public static final int NUMGAMESTATES = 16;
    public static final int MENUSTATE = 0;
    public static final int LEVEL1 = 2;
    public static final int LEVEL2 = 3;
    public static final int LEVELEND = 4;
    public static final int GAMEOVERSCREEN = 15;

    public GameStateManager() {



        gameStates = new GameState[NUMGAMESTATES];

        pauseState = new PauseState(this);
        paused = false;

        currentState = MENUSTATE;
        loadState(currentState);

    }

    //ZAGRUZKA SOSTOYANIY LVELOW
    private void loadState(int state) {

        if(state == MENUSTATE)
            gameStates[state] = new MenuState(this);
        else if(state == LEVEL1)
            gameStates[state] = new Level1(this);
        else if(state == LEVEL2)
            gameStates[state] = new Level2(this);
        else if(state == LEVELEND)
            gameStates[state] = new LevelEND(this);
        else if(state == GAMEOVERSCREEN)
            gameStates[state] = new GameOverScreen(this);
    }

    //OTGRUZKA SOSTOYANIY
    private void unloadState(int state) {

        gameStates[state] = null;
    }
    //PRIMINENIE SOSTOYANIY
    public void setState(int state) {

        unloadState(currentState);
        currentState = state;
        loadState(currentState);
    }

    //PAUSE
    public void setPaused(boolean b) {

        paused = b;
    }

    public void update() {

        if(paused) {

            pauseState.update();
            return;
        }

        if(gameStates[currentState] != null) gameStates[currentState].update();
    }

    public void draw(java.awt.Graphics2D g) {

        if(paused) {

            pauseState.draw(g);
            return;
        }

        if(gameStates[currentState] != null) gameStates[currentState].draw(g);

        else {

            g.setColor(java.awt.Color.BLACK);
            g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        }
    }

}