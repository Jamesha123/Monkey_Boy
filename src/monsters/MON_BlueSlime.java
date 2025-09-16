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
import main.GamePanel;
import objects.OBJ_Boots;

public class MON_BlueSlime
extends Entity {
    GamePanel gp;
    private String dayName = "Blue Slime";
    private int dayDefaultSpeed = 4;
    private int dayMaxLife = 4;
    private int dayAttack = 11;
    private int dayDefense = 0;
    private int dayExp = 10;
    private int dayWalkAnimationSpeed = 20;
    private boolean isNightTime = false;

    public MON_BlueSlime(GamePanel gamePanel) {
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
        this.up1 = this.setup("/Monster/blueslime_down_1.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.up2 = this.setup("/Monster/blueslime_down_2.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.down1 = this.setup("/Monster/blueslime_down_1.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.down2 = this.setup("/Monster/blueslime_down_2.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.left1 = this.setup("/Monster/blueslime_down_1.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.left2 = this.setup("/Monster/blueslime_down_2.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.right1 = this.setup("/Monster/blueslime_down_1.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.right2 = this.setup("/Monster/blueslime_down_2.png", 48, 48);
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
        this.dropItem(new OBJ_Boots(this.gp));
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
        this.name = "Night Blue Slime";
        this.speed = this.defaultSpeed = 6;
        this.life = this.maxLife = 10;
        this.attack = 15;
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
