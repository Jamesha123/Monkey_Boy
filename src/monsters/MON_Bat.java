/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  entity.Entity
 *  main.GamePanel
 */
package monsters;

import entity.Entity;
import java.util.Objects;
import java.util.Random;
import main.GamePanel;
import objects.OBJ_Coin_Bronze;
import objects.OBJ_Heart;
import objects.OBJ_ManaCrystal;

public class MON_Bat
extends Entity {
    GamePanel gp;

    public MON_Bat(GamePanel gamePanel) {
        super(gamePanel);
        this.gp = gamePanel;
        this.type = 2;
        this.name = "Bat";
        this.speed = this.defaultSpeed = 4;
        this.life = this.maxLife = 7;
        this.attack = 7;
        this.defense = 0;
        this.exp = 7;
        this.solidArea.x = 3;
        this.solidArea.y = 15;
        this.solidArea.width = 42;
        this.solidArea.height = 21;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
        this.canContactDamage = true;
        this.getImage();
    }

    public void getImage() {
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.up1 = this.setup("/Monster/bat_down_1.png.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.up2 = this.setup("/Monster/bat_down_2.png.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.down1 = this.setup("/Monster/bat_down_1.png.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.down2 = this.setup("/Monster/bat_down_2.png.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.left1 = this.setup("/Monster/bat_down_1.png.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.left2 = this.setup("/Monster/bat_down_2.png.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.right1 = this.setup("/Monster/bat_down_1.png.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.right2 = this.setup("/Monster/bat_down_2.png.png", 48, 48);
    }

    public void setAction() {
        if (!this.onPath) {
            this.getRandomDirection(10);
        }
    }

    public void damageReaction() {
        this.actionLockCounter = 0;
    }

    public void checkDrop() {
        int n = new Random().nextInt(100) + 1;
        if (n < 60) {
            this.dropItem(new OBJ_Coin_Bronze(this.gp));
        } else if (n >= 60 && n < 80) {
            this.dropItem(new OBJ_Heart(this.gp));
        } else if (n >= 80 && n < 90 && this.gp.player.mana > 0) {
            this.dropItem(new OBJ_ManaCrystal(this.gp));
        }
    }
}
