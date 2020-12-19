package GameState;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import Entity.Enemy;
import Entity.EnergyParticle;
import Entity.Explosion;
import Entity.HUD;
import Entity.Player;
import Entity.PlayerSave;
import Entity.Teleport;
import Entity.Title;
import Entity.Enemies.Jija;
import Handlers.Keys;
import Main.GamePanel;
import TileMap.Background;
import TileMap.TileMap;

public class Level2 extends GameState {

    private Background sky;
    private Background mountains;

    private Player player;
    private TileMap tileMap;
    private ArrayList<Enemy> enemies;
    private ArrayList<EnergyParticle> energyParticles;
    private ArrayList<Explosion> explosions;

    private HUD hud;
    private BufferedImage SLOVA;
    private Title title;
    private Title subtitle;
    private Teleport teleport;

    // SLUCHAI
    private boolean blockInput = false;
    private int eventCount = 0;
    private boolean eventStart;
    private ArrayList<Rectangle> tb;
    private boolean eventFinish;
    private boolean eventDead;

    public Level2(GameStateManager gsm) {

        super(gsm);
        init();
    }

    public void init() {

        // BG
        sky = new Background("/Backgrounds/sky.gif", 0);
        mountains = new Background("/Backgrounds/2lvl.gif", 0.2);

        // TILE
        tileMap = new TileMap(90);
        tileMap.loadTiles("/Tilesets/2lvl.gif");
        tileMap.loadMap("/Maps/lvl2.map");
        tileMap.setPosition(140, 0);

        tileMap.setBounds(tileMap.getWidth() - 2 * tileMap.getTileSize(), tileMap.getHeight() - 2 * tileMap.getTileSize(), 0, 0);

        tileMap.setTween(1);

        // IGROK
        player = new Player(tileMap);
        player.setPosition(150, 150);
        player.setHealth(PlayerSave.getHealth());
        player.setLives(PlayerSave.getLives());
        player.setTime(PlayerSave.getTime());

        // VRAGI
        enemies = new ArrayList<Enemy>();
        populateEnemies();

        // CHASTICI VRAGOV
        energyParticles = new ArrayList<EnergyParticle>();

        // IGROK+
        player.init(enemies, energyParticles);

        // VZRIVE
        explosions = new ArrayList<Explosion>();

        // HP and TRY"i
        hud = new HUD(player);

        // GO AND GROUP
        try {

            SLOVA = ImageIO.read(getClass().getResourceAsStream("/HUD/SLOVA.gif"));
            title = new Title(SLOVA.getSubimage(0, 0, 534, 60));
            title.sety(180);
            subtitle = new Title(SLOVA.getSubimage(0, 60, 246, 78));
            subtitle.sety(255);
        }

        catch(Exception e) {

            e.printStackTrace();
        }

        // teleport FOR GAME

        teleport = new Teleport(tileMap);
        teleport.setPosition(3200,840 );
		/*
		// teleport FOR TEST
		teleport = new Teleport(tileMap);
		teleport.setPosition(180, 530);
*/
        // START SLUCHAYA
        eventStart = true;
        tb = new ArrayList<Rectangle>();
        eventStart();


    }

    //VRAGI SPAWN
    private void populateEnemies() {
        enemies.clear();
        Jija gp;


        gp = new Jija(tileMap, player);
        gp.setPosition(1300, 100);
        enemies.add(gp);
        gp = new Jija(tileMap, player);
        gp.setPosition(1320, 100);
        enemies.add(gp);
        gp = new Jija(tileMap, player);
        gp.setPosition(1340, 100);
        enemies.add(gp);
        gp = new Jija(tileMap, player);
        gp.setPosition(1800, 100);
        enemies.add(gp);
        gp = new Jija(tileMap, player);
        gp.setPosition(1820, 100);
        enemies.add(gp);
        gp = new Jija(tileMap, player);
        gp.setPosition(2177, 100);
        enemies.add(gp);
        gp = new Jija(tileMap, player);
        gp.setPosition(2900, 100);
        enemies.add(gp);
        gp = new Jija(tileMap, player);
        gp.setPosition(2950, 100);
        enemies.add(gp);


    }

    public void update() {

        // PROVERKA KLAWISH
        handleInput();

        // PROVERKA NA SOBITIE
        if(teleport.contains(player)) {

            eventFinish = blockInput = true;
        }

        // PROVERKA NA SOBITIE SMERTI
        if(player.getHealth() == 0 || player.gety() > tileMap.getHeight()) {

            eventDead = blockInput = true;
        }

        // START EVENT"ov
        if(eventStart) eventStart();
        if(eventDead) eventDead();
        if(eventFinish) eventFinish();

        // PEREDVIDGENIE TITLE
        if(title != null) {

            title.update();
            if(title.shouldRemove()) title = null;
        }

        if(subtitle != null) {

            subtitle.update();
            if(subtitle.shouldRemove()) subtitle = null;
        }


        // ZAGRUZKA PLEYERA
        player.update();

        // ZAGRUZKA KARTI
        tileMap.setPosition(GamePanel.WIDTH / 2 - player.getx(), GamePanel.HEIGHT / 2 - player.gety());
        tileMap.update();
        tileMap.fixBounds();

        // ZAGRUZKA VRAGOV
        for(int i = 0; i < enemies.size(); i++) {

            Enemy e = enemies.get(i);
            e.update();
            if(e.isDead()) {

                enemies.remove(i);
                i--;
                explosions.add(new Explosion(tileMap, e.getx(), e.gety()));
            }
        }



        // ZAGRUZKA VZRIVOV
        for(int i = 0; i < explosions.size(); i++) {

            explosions.get(i).update();
            if(explosions.get(i).shouldRemove()) {

                explosions.remove(i);
                i--;
            }
        }

        // ZAGRUZKA TELEPORTA
        teleport.update();

    }


    public void draw(Graphics2D g) {

        // PRORISOVKA BG
        sky.draw(g);

        mountains.draw(g);

        // RISOVANIE TILE"a
        tileMap.draw(g);

        // ZAGRUZKA VRAGOV
        for(int i = 0; i < enemies.size(); i++) {

            enemies.get(i).draw(g);
        }



        // ZAGRUZKA VZRIVOV
        for(int i = 0; i < explosions.size(); i++) {

            explosions.get(i).draw(g);
        }

        // OTRISOVKA IGROKA
        player.draw(g);

        // OTRISOVKA TELEPORTA
        teleport.draw(g);

        // OTRISOVKA HP and TRY"i
        hud.draw(g);

        // OTRISOVKA TITLE
        if(title != null) title.draw(g);
        if(subtitle != null) subtitle.draw(g);

        // OTRISOVKA PEREHODA
        g.setColor(java.awt.Color.BLACK);
        for(int i = 0; i < tb.size(); i++) {

            g.fill(tb.get(i));
        }

    }

    public void handleInput() {

        if(Keys.isPressed(Keys.ESCAPE)) gsm.setPaused(true);
        if(blockInput || player.getHealth() == 0) return;
        player.setUp(Keys.keyState[Keys.UP]);
        player.setLeft(Keys.keyState[Keys.LEFT]);
        player.setDown(Keys.keyState[Keys.DOWN]);
        player.setRight(Keys.keyState[Keys.RIGHT]);
        player.setJumping(Keys.keyState[Keys.BUTTON1]);
        player.setDashing(Keys.keyState[Keys.BUTTON2]);
        if(Keys.isPressed(Keys.BUTTON3)) player.setAttacking();
        if(Keys.isPressed(Keys.BUTTON4)) player.setCharging();
    }

////////////////////////////// SLUCHAI

    // PEREZAGRUZKA LEVELA
    private void reset() {

        player.reset();
        player.setPosition(300, 161);
        populateEnemies();
        blockInput = true;
        eventCount = 0;
        eventStart = true;
        eventStart();
        title = new Title(SLOVA.getSubimage(0, 0, 534, 60));
        title.sety(180);
        subtitle = new Title(SLOVA.getSubimage(0, 60, 246, 78));
        subtitle.sety(255);
    }

    // START LEVELA
    private void eventStart() {

        eventCount++;

        if(eventCount == 1) {

            tb.clear();
            tb.add(new Rectangle(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT / 2));
            tb.add(new Rectangle(0, 0, GamePanel.WIDTH / 2, GamePanel.HEIGHT));
            tb.add(new Rectangle(0, GamePanel.HEIGHT / 2, GamePanel.WIDTH, GamePanel.HEIGHT / 2));
            tb.add(new Rectangle(GamePanel.WIDTH / 2, 0, GamePanel.WIDTH / 2, GamePanel.HEIGHT));
        }

        if(eventCount > 1 && eventCount < 60) {

            tb.get(0).height -= 4;
            tb.get(1).width -= 6;
            tb.get(2).y += 4;
            tb.get(3).x += 6;
        }

        if(eventCount == 30) title.begin();
        if(eventCount == 60) {

            eventStart = blockInput = false;
            eventCount = 0;
            subtitle.begin();
            tb.clear();
        }
    }

    // IGROK UMER
    private void eventDead() {

        eventCount++;

        if(eventCount == 1) {

            player.setDead();
            player.stop();
        }

        if(eventCount == 60) {

            tb.clear();
            tb.add(new Rectangle(GamePanel.WIDTH / 2, GamePanel.HEIGHT / 2, 0, 0));
        }

        else if(eventCount > 60) {

            tb.get(0).x -= 6;
            tb.get(0).y -= 4;
            tb.get(0).width += 12;
            tb.get(0).height += 8;
        }

        if(eventCount >= 120) {

            if(player.getLives() == 0) {

                gsm.setState(GameStateManager.MENUSTATE);
            }

            else {

                eventDead = blockInput = false;
                eventCount = 0;
                player.loseLife();
                reset();
            }
        }
    }

    // KONEC UROVNYA
    private void eventFinish() {

        eventCount++;
        if(eventCount == 1) {

            player.setTeleporting(true);
            player.stop();
        }

        else if(eventCount == 120) {

            tb.clear();
            tb.add(new Rectangle(GamePanel.WIDTH / 2, GamePanel.HEIGHT / 2, 0, 0));
        }

        else if(eventCount > 120) {

            tb.get(0).x -= 6;
            tb.get(0).y -= 4;
            tb.get(0).width += 12;
            tb.get(0).height += 8;
        }

        if(eventCount == 180) {

            PlayerSave.setHealth(player.getHealth());
            PlayerSave.setLives(player.getLives());
            PlayerSave.setTime(player.getTime());
            gsm.setState(GameStateManager.LEVELEND);
        }

    }

}