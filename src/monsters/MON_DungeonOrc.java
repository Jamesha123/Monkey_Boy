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
import objects.OBJ_Axe;
import objects.OBJ_Coin_Bronze;
import objects.OBJ_Heart;
import objects.OBJ_ManaCrystal;
import objects.OBJ_Shield_Blue;
import objects.OBJ_Shield_Wood;
import objects.OBJ_Sword_Normal;

public class MON_DungeonOrc
extends Entity {
    GamePanel gp;

    public MON_DungeonOrc(GamePanel gamePanel) {
        super(gamePanel);
        this.gp = gamePanel;
        this.type = 2;
        this.name = "Dungeon Orc";
        this.life = this.maxLife = 20;
        this.attack = 15;
        this.defense = 4;
        this.exp = 25;
        this.speed = this.defaultSpeed = 1;
        this.walkAnimationSpeed = 20;
        this.motion1_duration = 40;
        this.motion2_duration = 75;
        this.knockBackPower = 8;
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
        int n = this.gp.currentMap;
        Objects.requireNonNull(this.gp);
        if (n == 13) {
            this.searchPath(this.getGoalCol((Entity)this.gp.player), this.getGoalRow((Entity)this.gp.player));
            if (!this.attacking) {
                Objects.requireNonNull(this.gp);
                Objects.requireNonNull(this.gp);
                this.checkMonsterAttack(1, 48 * 4, 48);
            }
        } else {
            if (this.onPath) {
                this.checkStopChasing((Entity)this.gp.player, 15, 120);
                this.searchPath(this.getGoalCol((Entity)this.gp.player), this.getGoalRow((Entity)this.gp.player));
            } else {
                this.checkStartChasing((Entity)this.gp.player, 7, 60);
                this.getRandomDirection(60);
            }
            if (!this.attacking) {
                Objects.requireNonNull(this.gp);
                Objects.requireNonNull(this.gp);
                this.checkMonsterAttack(8, 48 * 4, 48);
            }
        }
    }

    public void damageReaction() {
        this.actionLockCounter = 0;
        this.onPath = true;
    }

    public void checkDrop() {
        int n = new Random().nextInt(100) + 1;
        if (n < 30) {
            this.dropItem(new OBJ_Coin_Bronze(this.gp));
        } else if (n >= 30 && n < 35) {
            int n2 = new Random().nextInt(100) + 1;
            if (n2 < 33) {
                this.dropItem(new OBJ_Sword_Normal(this.gp));
            } else if (n2 >= 33 && n2 < 66) {
                this.dropItem(new OBJ_Axe(this.gp));
            } else if (n2 >= 66 && n2 < 99) {
                this.dropItem(new OBJ_Shield_Wood(this.gp));
            } else if (n2 == 100) {
                this.dropItem(new OBJ_Shield_Blue(this.gp));
            }
        } else if (n >= 50 && n < 75) {
            this.dropItem(new OBJ_Heart(this.gp));
        } else if (n >= 75 && n < 100) {
            this.dropItem(new OBJ_ManaCrystal(this.gp));
        }
    }
}
