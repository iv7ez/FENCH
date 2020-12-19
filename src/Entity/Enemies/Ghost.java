package Entity.Enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import Entity.Enemy;
import Handlers.Content;
import TileMap.TileMap;


public class Ghost extends Enemy {

    private BufferedImage[] idleSprites;
    private int tick;
    private double a;
    private double b;

    //STATI MEDUZI
    public Ghost(TileMap tm) {

        super(tm);

        health = maxHealth = 2;

        width = 117;
        height = 60;
        cwidth = 75;
        cheight = 45;

        damage = 1;
        moveSpeed = 5;

        idleSprites = Content.Ghost[0];

        animation.setFrames(idleSprites);
        animation.setDelay(4);

        tick = 0;
        a = Math.random() * 0.06 + 0.07;
        b = Math.random() * 0.06 + 0.07;

    }

    public void update() {

        // PROWERKA NA UDAR
        if(flinching) {

            flinchCount++;
            if(flinchCount == 6) flinching = false;
        }

        tick++;
        x = Math.sin(a * tick) + x;
        y = Math.sin(b * tick) + y;

        // ANIMATIA
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
