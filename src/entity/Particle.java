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

    public Particle(GamePanel gamePanel, Entity entity, Color color, int n, int n2, int n3, int n4, int n5) {
        super(gamePanel);
        this.generator = entity;
        this.color = color;
        this.size = n;
        this.speed = n2;
        this.maxLife = n3;
        this.xd = n4;
        this.yd = n5;
        this.life = n3;
        int n6 = gamePanel.tileSize / 2 - n / 2;
        this.worldX = entity.worldX + n6;
        this.worldY = entity.worldY + n6;
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
        int n = this.worldX - this.gp.player.worldX + this.gp.player.screenX;
        int n2 = this.worldY - this.gp.player.worldY + this.gp.player.screenY;
        graphics2D.setColor(this.color);
        graphics2D.fillRect(n, n2, this.size, this.size);
    }
}
