/*
 * Decompiled with CFR 0.152.
 */
package entity;

import entity.Entity;
import main.GamePanel;

public class Projectile
extends Entity {
    Entity user;

    public Projectile(GamePanel gp) {
        super(gp);
    }

    public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.user = user;
        this.alive = alive;
        this.life = this.maxLife;
    }

    @Override
    public void update() {
        int monsterIndex;
        if (this.user == this.gp.player) {
            monsterIndex = this.gp.cChecker.checkEntity(this, this.gp.monster);
            if (monsterIndex != 999) {
                this.gp.player.damageMonster(monsterIndex, this, this.attack * (this.gp.player.level / 2), this.knockBackPower, this.direction);
                this.generateParticle(this.user.projectile, this.gp.monster[this.gp.currentMap][monsterIndex]);
                this.alive = false;
            }
            this.user.projectile.collisionOn = false;
            this.gp.cChecker.checkTile(this.user.projectile);
            if (this.user.projectile.collisionOn) {
                this.generateParticle(this.user.projectile, this.user.projectile);
                this.alive = false;
            }
        }
        if (this.user != this.gp.player) {
            int hitPlayer = this.gp.cChecker.checkPlayer(this) ? 1 : 0;
            if (!this.gp.player.invincible && hitPlayer == 1) {
                this.damagePlayer(this.attack);
                this.generateParticle(this.user.projectile, this.user.projectile);
                this.alive = false;
            }
            this.user.projectile.collisionOn = false;
            this.gp.cChecker.checkTile(this.user.projectile);
            if (this.user.projectile.collisionOn) {
                this.generateParticle(this.user.projectile, this.user.projectile);
                this.alive = false;
            }
        }
        switch (this.direction) {
            case "up": {
                this.worldY -= this.speed;
                break;
            }
            case "down": {
                this.worldY += this.speed;
                break;
            }
            case "left": {
                this.worldX -= this.speed;
                break;
            }
            case "right": {
                this.worldX += this.speed;
            }
        }
        --this.life;
        if (this.life <= 0) {
            this.alive = false;
        }
        ++this.spriteCounter;
        if (this.spriteCounter > 12) {
            if (this.spriteNum == 1) {
                this.spriteNum = 2;
            } else if (this.spriteNum == 2) {
                this.spriteNum = 1;
            }
            this.spriteCounter = 0;
        }
    }

    public boolean haveResource(Entity user) {
        return false;
    }

    public void subtractResource(Entity user) {
    }
}
