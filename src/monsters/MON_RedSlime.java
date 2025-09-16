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
import objects.OBJ_Potion_Red;
import objects.OBJ_Shield_Blue;
import objects.OBJ_Shield_Wood;
import objects.OBJ_Sword_Normal;

public class MON_RedSlime
extends Entity {
    GamePanel gp;
    private String dayName = "Red Slime";
    private int dayDefaultSpeed = 2;
    private int dayMaxLife = 8;
    private int dayAttack = 7;
    private int dayDefense = 0;
    private int dayExp = 5;
    private int dayWalkAnimationSpeed = 18;
    private boolean isNightTime = false;

    public MON_RedSlime(GamePanel gamePanel) {
        super(gamePanel);
        this.gp = gamePanel;
        this.type = 2;
        this.walkAnimationSpeed = this.dayWalkAnimationSpeed;
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
        this.up1 = this.setup("/Monster/redslime_down_1.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.up2 = this.setup("/Monster/redslime_down_2.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.down1 = this.setup("/Monster/redslime_down_1.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.down2 = this.setup("/Monster/redslime_down_2.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.left1 = this.setup("/Monster/redslime_down_1.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.left2 = this.setup("/Monster/redslime_down_2.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.right1 = this.setup("/Monster/redslime_down_1.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.right2 = this.setup("/Monster/redslime_down_2.png", 48, 48);
    }

    public void setAction() {
        int n = this.gp.currentMap;
        Objects.requireNonNull(this.gp);
        if (n == 9) {
            this.moveTowardPlayer(60);
        } else if (this.onPath) {
            this.checkStopChasing((Entity)this.gp.player, 15, 100);
            this.searchPath(this.getGoalCol((Entity)this.gp.player), this.getGoalRow((Entity)this.gp.player));
        } else {
            this.checkStartChasing((Entity)this.gp.player, 6, 50);
            this.getRandomDirection(60);
        }
    }

    public void damageReaction() {
        this.actionLockCounter = 0;
        this.onPath = true;
    }

    public void checkDrop() {
        int n = new Random().nextInt(100) + 1;
        if (n < 40) {
            this.dropItem(new OBJ_Coin_Bronze(this.gp));
        } else if (n >= 40 && n < 41) {
            int n2 = new Random().nextInt(100) + 1;
            if (n2 < 33) {
                this.dropItem(new OBJ_Sword_Normal(this.gp));
            } else if (n2 >= 33 && n2 < 66) {
                this.dropItem(new OBJ_Potion_Red(this.gp));
            } else if (n2 >= 66 && n2 < 99) {
                this.dropItem(new OBJ_Shield_Wood(this.gp));
            } else if (n2 == 100) {
                this.dropItem(new OBJ_Shield_Blue(this.gp));
            }
        } else if (n >= 50 && n < 75) {
            this.dropItem(new OBJ_Heart(this.gp));
        } else if (n >= 75 && n < 100 && this.gp.player.mana > 0) {
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
        this.name = "Night Red Slime";
        this.speed = this.defaultSpeed = 3;
        this.life = this.maxLife = 12;
        this.attack = 12;
        this.defense = 2;
        this.exp = 14;
        this.walkAnimationSpeed = 12;
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
