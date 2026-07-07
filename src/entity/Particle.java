/*
 * Decompiled with CFR 0.152.
 */
package entity;

import entity.Entity;
import java.awt.Color;
import java.awt.Graphics2D;
import main.GamePanel;

public class Particle
extends Entity {
    Entity generator;
    Color color;
    int size;
    int xd;
    int yd;

    public Particle(GamePanel gp, Entity generator, Color color, int size, int speed, int maxLife, int xDelta, int yDelta) {
        super(gp);
        this.generator = generator;
        this.color = color;
        this.size = size;
        this.speed = speed;
        this.maxLife = maxLife;
        this.xd = xDelta;
        this.yd = yDelta;
        this.life = maxLife;
        int offset = gp.tileSize / 2 - size / 2;
        this.worldX = generator.worldX + offset;
        this.worldY = generator.worldY + offset;
    }

    @Override
    public void update() {
        --this.life;
        if (this.life < this.maxLife / 3) {
            ++this.yd;
        }
        this.worldX += this.xd * this.speed;
        this.worldY += this.yd * this.speed;
        if (this.life == 0) {
            this.alive = false;
        }
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        int screenX = this.worldX - this.gp.player.worldX + this.gp.player.screenX;
        int screenY = this.worldY - this.gp.player.worldY + this.gp.player.screenY;
        graphics2D.setColor(this.color);
        graphics2D.fillRect(screenX, screenY, this.size, this.size);
    }
}
