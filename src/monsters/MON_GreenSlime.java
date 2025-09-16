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
import objects.OBJ_Rock;

public class MON_GreenSlime
extends Entity {
    GamePanel gp;
    private String dayName = "Green Slime";
    private int dayDefaultSpeed = 1;
    private int dayMaxLife = 4;
    private int dayAttack = 3;
    private int dayDefense = 0;
    private int dayExp = 2;
    private int dayWalkAnimationSpeed = 12;
    private boolean isNightTime = false;

    public MON_GreenSlime(GamePanel gamePanel) {
        super(gamePanel);
        this.gp = gamePanel;
        this.type = 2;
        this.projectile = new OBJ_Rock(gamePanel, "normal");
        this.rate = 30;
        this.walkAnimationSpeed = 12;
        this.solidArea.x = 3;
        this.solidArea.y = 18;
        this.solidArea.width = 42;
        this.solidArea.height = 30;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
        this.getImage();
        this.applyDayStats();
        this.applyNightTimeBuffs();
    }

    public void getImage() {
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.up1 = this.setup("/Monster/greenslime_down_1.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.up2 = this.setup("/Monster/greenslime_down_2.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.down1 = this.setup("/Monster/greenslime_down_1.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.down2 = this.setup("/Monster/greenslime_down_2.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.left1 = this.setup("/Monster/greenslime_down_1.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.left2 = this.setup("/Monster/greenslime_down_2.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.right1 = this.setup("/Monster/greenslime_down_1.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.right2 = this.setup("/Monster/greenslime_down_2.png", 48, 48);
    }

    public void setAction() {
        int n = this.gp.currentMap;
        Objects.requireNonNull(this.gp);
        if (n == 9) {
            this.moveTowardPlayer(60);
        } else if (this.onPath) {
            this.checkStopChasing((Entity)this.gp.player, 15, 100);
            this.searchPath(this.getGoalCol((Entity)this.gp.player), this.getGoalRow((Entity)this.gp.player));
            this.checkMonsterShoot(75, this.rate);
        } else {
            this.checkStartChasing((Entity)this.gp.player, 5, 50);
            this.getRandomDirection(120);
        }
    }

    public void damageReaction() {
        this.actionLockCounter = 0;
        this.onPath = true;
    }

    public void checkDrop() {
        int n = new Random().nextInt(100) + 1;
        if (n < 50) {
            this.dropItem(new OBJ_Coin_Bronze(this.gp));
        } else if (n >= 50 && n < 75) {
            this.dropItem(new OBJ_Heart(this.gp));
        } else if (n >= 75 && n < 90 && this.gp.player.mana > 0) {
            this.dropItem(new OBJ_ManaCrystal(this.gp));
        }
    }

    private void applyDayStats() {
        this.name = this.dayName;
        this.speed = this.defaultSpeed = this.dayDefaultSpeed;
        this.life = this.maxLife = this.dayMaxLife;
        this.attack = this.dayAttack;
        this.defense = this.dayDefense;
        this.exp = this.dayExp;
        this.walkAnimationSpeed = this.dayWalkAnimationSpeed;
    }

    private void applyNightStats() {
        this.name = "Night Green Slime";
        this.speed = this.defaultSpeed = 1;
        this.life = this.maxLife = 6;
        this.attack = 8;
        this.defense = 2;
        this.exp = 8;
        this.rate = 15;
        this.walkAnimationSpeed = 8;
        if (this.projectile != null) {
            this.projectile.attack = 8;
        }
    }

    public void applyNightTimeBuffs() {
        boolean bl;
        boolean bl2 = bl = this.gp.eManager != null && this.gp.eManager.lighting != null && this.gp.eManager.lighting.isNightTime();
        if (bl != this.isNightTime) {
            this.isNightTime = bl;
            if (this.isNightTime) {
                this.applyNightStats();
            } else {
                this.applyDayStats();
            }
        }
    }

    public void update() {
        super.update();
        this.applyNightTimeBuffs();
    }
}
