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

    public OBJ_Fireball(GamePanel gamePanel, String string) {
        super(gamePanel);
        this.gp = gamePanel;
        this.name = objName;
        this.useCost = 1;
        this.alive = false;
        if (string == "boss") {
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

    public void getImage(int n) {
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.up1 = this.setup("/Projectile/fireball_up_1.png", 48 * n, 48 * n);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.up2 = this.setup("/Projectile/fireball_up_2.png", 48 * n, 48 * n);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.down1 = this.setup("/Projectile/fireball_down_1.png", 48 * n, 48 * n);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.down2 = this.setup("/Projectile/fireball_down_2.png", 48 * n, 48 * n);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.left1 = this.setup("/Projectile/fireball_left_1.png", 48 * n, 48 * n);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.left2 = this.setup("/Projectile/fireball_left_2.png", 48 * n, 48 * n);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.right1 = this.setup("/Projectile/fireball_right_1.png", 48 * n, 48 * n);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.right2 = this.setup("/Projectile/fireball_right_2.png", 48 * n, 48 * n);
    }

    public boolean haveResource(Entity entity) {
        boolean bl = false;
        if (entity.mana >= this.useCost) {
            bl = true;
        }
        return bl;
    }

    public void subtractResource(Entity entity) {
        entity.mana -= this.useCost;
    }

    public Color getParticleColor() {
        Color color = new Color(240, 50, 0);
        return color;
    }

    public int getParticleSize() {
        int n = 10;
        return n;
    }

    public int getParticleSpeed() {
        int n = 1;
        return n;
    }

    public int getParticleMaxLife() {
        int n = 20;
        return n;
    }
}
