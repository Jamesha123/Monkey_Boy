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

public class MON_Orc
extends Entity {
    GamePanel gp;
    private String dayName = "Orc";
    private int dayDefaultSpeed = 1;
    private int dayMaxLife = 10;
    private int dayAttack = 8;
    private int dayDefense = 2;
    private int dayExp = 10;
    private int dayWalkAnimationSpeed = 25;
    private boolean isNightTime = false;

    public MON_Orc(GamePanel gamePanel) {
        super(gamePanel);
        this.gp = gamePanel;
        this.type = 2;
        this.motion1_duration = 40;
        this.motion2_duration = 85;
        this.walkAnimationSpeed = this.dayWalkAnimationSpeed;
        this.knockBackPower = 5;
        this.canContactDamage = false;
        this.solidArea.x = 4;
        this.solidArea.y = 4;
        this.solidArea.width = 40;
        this.solidArea.height = 44;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
        this.attackArea.width = 32;
        this.attackArea.height = 32;
        this.getImage();
        this.getAttackImage();
        this.applyDayStats();
        this.applyNightTimeBuffs();
    }

    public void getImage() {
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.up1 = this.setup("/Monster/orc_up_1.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.up2 = this.setup("/Monster/orc_up_2.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.down1 = this.setup("/Monster/orc_down_1.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.down2 = this.setup("/Monster/orc_down_2.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.left1 = this.setup("/Monster/orc_left_1.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.left2 = this.setup("/Monster/orc_left_2.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.right1 = this.setup("/Monster/orc_right_1.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.right2 = this.setup("/Monster/orc_right_2.png", 48, 48);
    }

    public void getAttackImage() {
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.attackUp1 = this.setup("/Monster/orc_attack_up_1.png", 48, 48 * 2);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.attackUp2 = this.setup("/Monster/orc_attack_up_2.png", 48, 48 * 2);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.attackDown1 = this.setup("/Monster/orc_attack_down_1.png", 48, 48 * 2);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.attackDown2 = this.setup("/Monster/orc_attack_down_2.png", 48, 48 * 2);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.attackLeft1 = this.setup("/Monster/orc_attack_left_1.png", 48 * 2, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.attackLeft2 = this.setup("/Monster/orc_attack_left_2.png", 48 * 2, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.attackRight1 = this.setup("/Monster/orc_attack_right_1.png", 48 * 2, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.attackRight2 = this.setup("/Monster/orc_attack_right_2.png", 48 * 2, 48);
    }

    public void setAction() {
        if (this.onPath) {
            this.checkStopChasing((Entity)this.gp.player, 15, 100);
            this.searchPath(this.getGoalCol((Entity)this.gp.player), this.getGoalRow((Entity)this.gp.player));
        } else {
            this.checkStartChasing((Entity)this.gp.player, 5, 50);
            this.getRandomDirection(60);
        }
        if (!this.attacking) {
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.checkMonsterAttack(10, 48 * 4, 48);
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
        this.name = "Night Orc";
        this.speed = this.defaultSpeed = 1;
        this.life = this.maxLife = 18;
        this.attack = 20;
        this.defense = 4;
        this.exp = 20;
        this.walkAnimationSpeed = 20;
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
