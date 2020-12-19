package Entity.Enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import Entity.Enemy;
import Entity.Player;
import Handlers.Content;
import Main.GamePanel;
import TileMap.TileMap;

public class Jija extends Enemy {

    private BufferedImage[] sprites;
    private Player player;
    private boolean active;

    //GHOST STATI
    public Jija(TileMap tm, Player p) {

        super(tm);
        player = p;
        health = maxHealth = 1;
        width = 75;
        height = 75;
        cwidth = 60;
        cheight = 54;

        damage = 1;
        moveSpeed = 0.8;
        fallSpeed = 0.15;
        maxFallSpeed = 4.0;
        jumpStart = -5;

        sprites = Content.Jija[0];

        animation.setFrames(sprites);
        animation.setDelay(4);

        left = true;
        facingRight = false;

    }

    //PEREMESHENIE
    private void getNextPosition() {

        if(left) dx = -moveSpeed;
        else if(right) dx = moveSpeed;
        else dx = 0;
        if(falling) {

            dy += fallSpeed;
            if(dy > maxFallSpeed) dy = maxFallSpeed;
        }

        if(jumping && !falling) {

            dy = jumpStart;
        }
    }

    public void update() {

        if(!active) {

            if(Math.abs(player.getx() - x) < GamePanel.WIDTH) active = true;
            return;
        }

        //PROVERKA NA UDAR
        if(flinching) {

            flinchCount++;
            if(flinchCount == 6) flinching = false;
        }

        //PEREMESHENIE
        getNextPosition();
        checkTileMapCollision();
        calculateCorners(x, ydest + 1);

        if(!bottomLeft) {

            left = false;
            right = facingRight = true;
        }

        if(!bottomRight) {

            left = true;
            right = facingRight = false;
        }

        setPosition(xtemp, ytemp);

        if(dx == 0) {

            left = !left;
            right = !right;
            facingRight = !facingRight;
        }

        //ANIMACIA
        animation.update();

    }

    //DRAW
    public void draw(Graphics2D g) {

        if(flinching) {

            if(flinchCount == 0 || flinchCount == 2) return;
        }

        super.draw(g);

    }

}
