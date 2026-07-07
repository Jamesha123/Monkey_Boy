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

public class OBJ_Fireball
extends Projectile {
    GamePanel gp;
    public static final String objName = "Fireball";
    private int i;

    public OBJ_Fireball(GamePanel gamePanel, String variant) {
        super(gamePanel);
        this.gp = gamePanel;
        this.name = objName;
        this.useCost = 1;
        this.alive = false;
        if (variant == "boss") {
            this.speed = 3;
            this.life = this.maxLife = 180;
            this.attack = 15;
            this.knockBackPower = 10;
            this.i = 3;
            Objects.requireNonNull(gamePanel);
            this.solidArea.height = 48 * 3;
            Objects.requireNonNull(gamePanel);
            this.solidArea.width = 48 * 3;
        } else {
            this.speed = 5;
            this.life = this.maxLife = 80;
            this.attack = 1;
            this.i = 1;
        }
        this.getImage(this.i);
    }

    public void getImage(int scale) {
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.up1 = this.setup("/Projectile/fireball_up_1.png", 48 * scale, 48 * scale);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.up2 = this.setup("/Projectile/fireball_up_2.png", 48 * scale, 48 * scale);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.down1 = this.setup("/Projectile/fireball_down_1.png", 48 * scale, 48 * scale);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.down2 = this.setup("/Projectile/fireball_down_2.png", 48 * scale, 48 * scale);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.left1 = this.setup("/Projectile/fireball_left_1.png", 48 * scale, 48 * scale);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.left2 = this.setup("/Projectile/fireball_left_2.png", 48 * scale, 48 * scale);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.right1 = this.setup("/Projectile/fireball_right_1.png", 48 * scale, 48 * scale);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.right2 = this.setup("/Projectile/fireball_right_2.png", 48 * scale, 48 * scale);
    }

    public boolean haveResource(Entity entity) {
        boolean hasEnoughMana = false;
        if (entity.mana >= this.useCost) {
            hasEnoughMana = true;
        }
        return hasEnoughMana;
    }

    public void subtractResource(Entity entity) {
        entity.mana -= this.useCost;
    }

    public Color getParticleColor() {
        Color particleColor = new Color(240, 50, 0);
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
