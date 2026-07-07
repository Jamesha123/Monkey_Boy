/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  entity.Entity
 *  entity.Projectile
 *  main.GamePanel
 */
package objects;

import entity.Entity;
import entity.Projectile;
import java.awt.Color;
import java.util.Objects;
import main.GamePanel;

public class OBJ_Rock
extends Projectile {
    GamePanel gp;
    public static final String objName = "Rock";

    public OBJ_Rock(GamePanel gamePanel, String variant) {
        super(gamePanel);
        this.gp = gamePanel;
        this.name = objName;
        this.life = this.maxLife = 80;
        this.useCost = 1;
        this.alive = false;
        if (variant == "boss") {
            this.getImage(2);
            this.attack = 15;
            this.speed = 5;
            this.knockBackPower = 15;
            Objects.requireNonNull(gamePanel);
            this.solidArea.x = 48 / 2;
            Objects.requireNonNull(gamePanel);
            this.solidArea.y = 48 / 2;
            this.solidAreaDefaultX = this.solidArea.x;
            this.solidAreaDefaultY = this.solidArea.y;
        } else {
            this.getImage(1);
            this.attack = 5;
            this.speed = 8;
            Objects.requireNonNull(gamePanel);
            this.solidArea.x = 48 / 4;
            Objects.requireNonNull(gamePanel);
            this.solidArea.y = 48 / 4;
            Objects.requireNonNull(gamePanel);
            this.solidArea.width = 48 / 2;
            Objects.requireNonNull(gamePanel);
            this.solidArea.height = 48 / 2;
            this.solidAreaDefaultX = this.solidArea.x;
            this.solidAreaDefaultY = this.solidArea.y;
        }
    }

    public void getImage(int scale) {
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.up1 = this.setup("/Projectile/rock_down_1.png", 48 * scale, 48 * scale);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.up2 = this.setup("/Projectile/rock_down_1.png", 48 * scale, 48 * scale);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.down1 = this.setup("/Projectile/rock_down_1.png", 48 * scale, 48 * scale);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.down2 = this.setup("/Projectile/rock_down_1.png", 48 * scale, 48 * scale);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.left1 = this.setup("/Projectile/rock_down_1.png", 48 * scale, 48 * scale);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.left2 = this.setup("/Projectile/rock_down_1.png", 48 * scale, 48 * scale);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.right1 = this.setup("/Projectile/rock_down_1.png", 48 * scale, 48 * scale);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.right2 = this.setup("/Projectile/rock_down_1.png", 48 * scale, 48 * scale);
    }

    public boolean haveResource(Entity entity) {
        boolean hasEnoughAmmo = false;
        if (entity.ammo >= this.useCost) {
            hasEnoughAmmo = true;
        }
        return hasEnoughAmmo;
    }

    public void subtractResource(Entity entity) {
        entity.ammo -= this.useCost;
    }

    public Color getParticleColor() {
        Color particleColor = new Color(40, 50, 0);
        return particleColor;
    }

    public int getParticleSize() {
        int particleSize = 10;
        return particleSize;
    }

    public int getParticleSpeed() {
        int particleSpeed = 1;
        return particleSpeed;
    }

    public int getParticleMaxLife() {
        int particleMaxLife = 20;
        return particleMaxLife;
    }
}
