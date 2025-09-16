/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  data.Progress
 *  entity.Entity
 *  main.GamePanel
 */
package monsters;

import data.Progress;
import entity.Entity;
import java.awt.Rectangle;
import java.util.Objects;
import main.GamePanel;
import monsters.MON_DungeonOrc;
import objects.OBJ_Fireball;
import objects.OBJ_Sword_Epic;

public class MON_SkeletonLord
extends Entity {
    GamePanel gp;
    public static final String monName = "Skeleton Lord";
    private int currentPhase = 0;
    private int orcsSpawned = 0;
    private int size = 0;
    private boolean orcsAlive = false;
    private boolean inInvinciblePhase = false;
    private boolean spawnSaved = false;
    private int spawnWorldX;
    private int spawnWorldY;
    private int originalSolidX;
    private int originalSolidY;
    private int originalSolidW;
    private int originalSolidH;
    private int fireballCounter = 0;
    private int FIREBALL_INTERVAL = 180;

    public MON_SkeletonLord(GamePanel gamePanel) {
        super(gamePanel);
        this.gp = gamePanel;
        this.type = 2;
        this.boss = true;
        this.name = monName;
        this.speed = this.defaultSpeed = 1;
        this.life = this.maxLife = 180;
        this.attack = 16;
        this.defense = 2;
        this.exp = 50;
        this.motion1_duration = 25;
        this.motion2_duration = 50;
        this.knockBackPower = 5;
        this.sleep = true;
        Objects.requireNonNull(gamePanel);
        this.size = 48 * 5;
        this.solidArea.x = 48;
        this.solidArea.y = 48;
        Objects.requireNonNull(gamePanel);
        this.solidArea.width = this.size - 48 * 2;
        Objects.requireNonNull(gamePanel);
        this.solidArea.height = this.size - 48;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
        this.attackArea.width = 140;
        this.attackArea.height = 140;
        this.projectile = new OBJ_Fireball(gamePanel, "boss");
        this.rate = 1;
        this.getImage();
        this.getAttackImage();
        this.setDialogue();
    }

    public void getImage() {
        int n = 5;
        if (!this.inRage) {
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.up1 = this.setup("/Monster/skeletonlord_up_1.png", 48 * n, 48 * n);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.up2 = this.setup("/Monster/skeletonlord_up_2.png", 48 * n, 48 * n);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.down1 = this.setup("/Monster/skeletonlord_down_1.png", 48 * n, 48 * n);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.down2 = this.setup("/Monster/skeletonlord_down_2.png", 48 * n, 48 * n);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.left1 = this.setup("/Monster/skeletonlord_left_1.png", 48 * n, 48 * n);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.left2 = this.setup("/Monster/skeletonlord_left_2.png", 48 * n, 48 * n);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.right1 = this.setup("/Monster/skeletonlord_right_1.png", 48 * n, 48 * n);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.right2 = this.setup("/Monster/skeletonlord_right_2.png", 48 * n, 48 * n);
        } else {
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.up1 = this.setup("/Monster/skeletonlord_phase2_up_1.png", 48 * n, 48 * n);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.up2 = this.setup("/Monster/skeletonlord_phase2_up_2.png", 48 * n, 48 * n);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.down1 = this.setup("/Monster/skeletonlord_phase2_down_1.png", 48 * n, 48 * n);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.down2 = this.setup("/Monster/skeletonlord_phase2_down_2.png", 48 * n, 48 * n);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.left1 = this.setup("/Monster/skeletonlord_phase2_left_1.png", 48 * n, 48 * n);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.left2 = this.setup("/Monster/skeletonlord_phase2_left_2.png", 48 * n, 48 * n);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.right1 = this.setup("/Monster/skeletonlord_phase2_right_1.png", 48 * n, 48 * n);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.right2 = this.setup("/Monster/skeletonlord_phase2_right_2.png", 48 * n, 48 * n);
        }
    }

    public void getAttackImage() {
        int n = 5;
        if (!this.inRage) {
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.attackUp1 = this.setup("/Monster/skeletonlord_attack_up_1.png", 48 * n, 48 * n * 2);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.attackUp2 = this.setup("/Monster/skeletonlord_attack_up_2.png", 48 * n, 48 * n * 2);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.attackDown1 = this.setup("/Monster/skeletonlord_attack_down_1.png", 48 * n, 48 * n * 2);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.attackDown2 = this.setup("/Monster/skeletonlord_attack_down_2.png", 48 * n, 48 * n * 2);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.attackLeft1 = this.setup("/Monster/skeletonlord_attack_left_1.png", 48 * n * 2, 48 * n);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.attackLeft2 = this.setup("/Monster/skeletonlord_attack_left_2.png", 48 * n * 2, 48 * n);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.attackRight1 = this.setup("/Monster/skeletonlord_attack_right_1.png", 48 * n * 2, 48 * n);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.attackRight2 = this.setup("/Monster/skeletonlord_attack_right_2.png", 48 * n * 2, 48 * n);
        } else {
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.attackUp1 = this.setup("/Monster/skeletonlord_phase2_attack_up_1.png", 48 * n, 48 * n * 2);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.attackUp2 = this.setup("/Monster/skeletonlord_phase2_attack_up_2.png", 48 * n, 48 * n * 2);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.attackDown1 = this.setup("/Monster/skeletonlord_phase2_attack_down_1.png", 48 * n, 48 * n * 2);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.attackDown2 = this.setup("/Monster/skeletonlord_phase2_attack_down_2.png", 48 * n, 48 * n * 2);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.attackLeft1 = this.setup("/Monster/skeletonlord_phase2_attack_left_1.png", 48 * n * 2, 48 * n);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.attackLeft2 = this.setup("/Monster/skeletonlord_phase2_attack_left_2.png", 48 * n * 2, 48 * n);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.attackRight1 = this.setup("/Monster/skeletonlord_phase2_attack_right_1.png", 48 * n * 2, 48 * n);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.attackRight2 = this.setup("/Monster/skeletonlord_phase2_attack_right_2.png", 48 * n * 2, 48 * n);
        }
    }

    private void getSleepImage() {
        int n = 5;
        if (!this.inRage) {
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.up1 = this.setup("/Monster/skeletonlord_sleep.png", 48 * n, 48 * n);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.up2 = this.setup("/Monster/skeletonlord_sleep.png", 48 * n, 48 * n);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.down1 = this.setup("/Monster/skeletonlord_sleep.png", 48 * n, 48 * n);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.down2 = this.setup("/Monster/skeletonlord_sleep.png", 48 * n, 48 * n);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.left1 = this.setup("/Monster/skeletonlord_sleep.png", 48 * n, 48 * n);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.left2 = this.setup("/Monster/skeletonlord_sleep.png", 48 * n, 48 * n);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.right1 = this.setup("/Monster/skeletonlord_sleep.png", 48 * n, 48 * n);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.right2 = this.setup("/Monster/skeletonlord_sleep.png", 48 * n, 48 * n);
        } else {
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.up1 = this.setup("/Monster/skeletonlord_phase2_sleep.png", 48 * n, 48 * n);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.up2 = this.setup("/Monster/skeletonlord_phase2_sleep.png", 48 * n, 48 * n);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.down1 = this.setup("/Monster/skeletonlord_phase2_sleep.png", 48 * n, 48 * n);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.down2 = this.setup("/Monster/skeletonlord_phase2_sleep.png", 48 * n, 48 * n);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.left1 = this.setup("/Monster/skeletonlord_phase2_sleep.png", 48 * n, 48 * n);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.left2 = this.setup("/Monster/skeletonlord_phase2_sleep.png", 48 * n, 48 * n);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.right1 = this.setup("/Monster/skeletonlord_phase2_sleep.png", 48 * n, 48 * n);
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            this.right2 = this.setup("/Monster/skeletonlord_phase2_sleep.png", 48 * n, 48 * n);
        }
    }

    public void setDialogue() {
        this.dialogues[0][0] = "PLAYER: You got to be kidding me!";
        this.dialogues[0][1] = "SKELETON LORD: The sugar is mine!";
        this.dialogues[0][2] = "SKELETON LORD: You will die here!";
        this.dialogues[0][3] = "SKELETON LORD: WELCOME TO YOUR DOOM!";
        this.dialogues[1][0] = "PLAYER: *whew* I can't believe I did that.";
        this.dialogues[1][1] = "PLAYER: Now to find the sugar...";
    }

    public void attacking() {
        ++this.spriteCounter;
        if (this.spriteCounter <= this.motion1_duration) {
            this.spriteNum = 1;
        } else if (this.spriteCounter > this.motion1_duration && this.spriteCounter <= this.motion2_duration) {
            this.spriteNum = 2;
            int n = this.worldX;
            int n2 = this.worldY;
            int n3 = this.solidArea.width;
            int n4 = this.solidArea.height;
            switch (this.direction) {
                case "up": {
                    this.worldY -= this.attackArea.height;
                    break;
                }
                case "down": {
                    this.worldY += this.solidArea.height;
                    break;
                }
                case "left": {
                    this.worldX -= this.attackArea.width;
                    break;
                }
                case "right": {
                    this.worldX += this.solidArea.width;
                }
            }
            this.solidArea.width = this.attackArea.width;
            this.solidArea.height = this.attackArea.height;
            if (this.type == 2) {
                if (this.gp.cChecker.checkPlayer((Entity)this)) {
                    this.damagePlayer(this.attack);
                }
            } else {
                int n5 = this.gp.cChecker.checkEntity((Entity)this, this.gp.monster);
                int n6 = this.currentWeapon != null ? this.currentWeapon.knockBackPower : 1;
                this.gp.player.damageMonster(n5, (Entity)this, this.attack, n6, this.direction);
                int n7 = this.gp.cChecker.checkEntity((Entity)this, (Entity[][])this.gp.iTile);
                this.gp.player.damageInteractiveTile(n7);
                int n8 = this.gp.cChecker.checkEntity((Entity)this, this.gp.projectile);
                this.gp.player.damageProjectile(n8);
            }
            this.worldX = n;
            this.worldY = n2;
            this.solidArea.width = n3;
            this.solidArea.height = n4;
        } else if (this.spriteCounter > this.motion2_duration) {
            this.spriteNum = 1;
            this.spriteCounter = 0;
            this.attacking = false;
        }
    }

    public void setAction() {
        this.checkPhaseTransitions();
        if (this.inInvinciblePhase) {
            this.handleInvinciblePhase();
        } else {
            if (!this.inRage && this.life < this.maxLife / 2) {
                this.inRage = true;
                this.getImage();
                this.getAttackImage();
                ++this.defaultSpeed;
                this.speed = this.defaultSpeed;
                this.attack = 18;
                this.FIREBALL_INTERVAL = 120;
            }
            ++this.fireballCounter;
            if (this.fireballCounter >= this.FIREBALL_INTERVAL) {
                this.checkBossShoot(1, 0);
                this.fireballCounter = 0;
            }
            this.moveTowardPlayer(60);
            if (!this.attacking) {
                Objects.requireNonNull(this.gp);
                Objects.requireNonNull(this.gp);
                this.checkMonsterAttack(60, 48 * 7, 48 * 5);
            }
        }
    }

    public void update() {
        if (this.gp.entitiesFrozen && this.type != 0) {
            return;
        }
        if (this.inInvinciblePhase) {
            this.setAction();
            ++this.spriteCounter;
            if (this.spriteCounter > 12) {
                if (this.spriteNum == 1) {
                    this.spriteNum = 2;
                } else if (this.spriteNum == 2) {
                    this.spriteNum = 1;
                }
                this.spriteCounter = 0;
            }
            return;
        }
        super.update();
    }

    private void checkPhaseTransitions() {
        if (this.inInvinciblePhase) {
            return;
        }
        int n = this.maxLife / 4;
        if (this.currentPhase < 1 && this.life <= n * 3) {
            this.currentPhase = 1;
            this.inInvinciblePhase = true;
            this.onPath = true;
            this.invincible = true;
        } else if (this.currentPhase < 2 && this.life <= n * 2) {
            this.currentPhase = 2;
            this.inInvinciblePhase = true;
            this.onPath = true;
            this.invincible = true;
        } else if (this.currentPhase < 3 && this.life <= n) {
            this.currentPhase = 3;
            this.inInvinciblePhase = true;
            this.onPath = true;
            this.invincible = true;
        }
    }

    private void handleInvinciblePhase() {
        if (this.onPath) {
            Objects.requireNonNull(this.gp);
            int n = 24 * 48;
            Objects.requireNonNull(this.gp);
            int n2 = 16 * 48;
            int n3 = n - this.worldX;
            int n4 = n2 - this.worldY;
            int n5 = Math.abs(n3);
            Objects.requireNonNull(this.gp);
            if (n5 > 48 / 2) {
                if (n3 > 0) {
                    this.direction = "right";
                    this.worldX += this.speed;
                } else {
                    this.direction = "left";
                    this.worldX -= this.speed;
                }
            }
            int n6 = Math.abs(n4);
            Objects.requireNonNull(this.gp);
            if (n6 > 48 / 2) {
                if (n4 > 0) {
                    this.direction = "down";
                    this.worldY += this.speed;
                } else {
                    this.direction = "up";
                    this.worldY -= this.speed;
                }
            }
            int n7 = Math.abs(n3);
            Objects.requireNonNull(this.gp);
            if (n7 <= 48) {
                int n8 = Math.abs(n4);
                Objects.requireNonNull(this.gp);
                if (n8 <= 48) {
                    if (!this.spawnSaved) {
                        this.spawnWorldX = this.worldX;
                        this.spawnWorldY = this.worldY;
                        this.originalSolidX = this.solidArea.x;
                        this.originalSolidY = this.solidArea.y;
                        this.originalSolidW = this.solidArea.width;
                        this.originalSolidH = this.solidArea.height;
                        this.spawnSaved = true;
                    }
                    Objects.requireNonNull(this.gp);
                    this.solidArea.x = 48 * 2;
                    Objects.requireNonNull(this.gp);
                    this.solidArea.y = 48 / 2;
                    Objects.requireNonNull(this.gp);
                    this.solidArea.width = 48;
                    Objects.requireNonNull(this.gp);
                    this.solidArea.height = 48;
                    this.solidAreaDefaultX = this.solidArea.x;
                    this.solidAreaDefaultY = this.solidArea.y;
                    this.getSleepImage();
                    this.onPath = false;
                    this.sleep = true;
                    this.worldX = n;
                    this.worldY = n2;
                    this.spawnDungeonOrcs();
                }
            }
        } else {
            this.checkOrcsStatus();
            if (!this.orcsAlive && this.orcsSpawned > 0) {
                this.endInvinciblePhase();
            }
        }
    }

    private void checkOrcsStatus() {
        int n = 0;
        for (int i = 0; i < this.gp.monster[this.gp.currentMap].length; ++i) {
            if (this.gp.monster[this.gp.currentMap][i] == null || !this.gp.monster[this.gp.currentMap][i].name.equals("Dungeon Orc") || !this.gp.monster[this.gp.currentMap][i].alive || this.gp.monster[this.gp.currentMap][i].dying) continue;
            ++n;
        }
        this.orcsAlive = n > 0;
    }

    private void endInvinciblePhase() {
        this.inInvinciblePhase = false;
        this.orcsSpawned = 0;
        this.orcsAlive = false;
        if (this.spawnSaved) {
            this.worldX = this.spawnWorldX;
            this.worldY = this.spawnWorldY;
            this.solidArea.x = this.originalSolidX;
            this.solidArea.y = this.originalSolidY;
            this.solidArea.width = this.originalSolidW;
            this.solidArea.height = this.originalSolidH;
            this.solidAreaDefaultX = this.solidArea.x;
            this.solidAreaDefaultY = this.solidArea.y;
        }
        this.wakeUpKnockBackPlayer();
    }

    private void wakeUpKnockBackPlayer() {
        int n = this.worldX + this.solidArea.x + this.solidArea.width / 2;
        int n2 = this.worldY + this.solidArea.y + this.solidArea.height / 2;
        int n3 = this.gp.player.worldX + this.gp.player.solidArea.x + this.gp.player.solidArea.width / 2;
        int n4 = this.gp.player.worldY + this.gp.player.solidArea.y + this.gp.player.solidArea.height / 2;
        int n5 = n3 - n;
        int n6 = n4 - n2;
        String string = Math.abs(n5) > Math.abs(n6) ? (n5 >= 0 ? "right" : "left") : (n6 >= 0 ? "down" : "up");
        int n7 = Math.max(this.knockBackPower, 60);
        this.setKnockBack((Entity)this.gp.player, this, n7, string);
        this.getImage();
        this.invincible = false;
        this.sleep = false;
        Rectangle rectangle = new Rectangle(this.worldX + this.solidArea.x, this.worldY + this.solidArea.y, this.solidArea.width, this.solidArea.height);
        Rectangle rectangle2 = new Rectangle(this.gp.player.worldX + this.gp.player.solidArea.x, this.gp.player.worldY + this.gp.player.solidArea.y, this.gp.player.solidArea.width, this.gp.player.solidArea.height);
        Objects.requireNonNull(this.gp);
        int n8 = 48 * 10;
        int n9 = Math.max(4, this.gp.player.speed);
        while (rectangle.intersects(rectangle2) && n8-- > 0) {
            switch (string) {
                case "up": {
                    this.gp.player.worldY -= n9;
                    break;
                }
                case "down": {
                    this.gp.player.worldY += n9;
                    break;
                }
                case "left": {
                    this.gp.player.worldX -= n9;
                    break;
                }
                case "right": {
                    this.gp.player.worldX += n9;
                }
            }
            rectangle2.setLocation(this.gp.player.worldX + this.gp.player.solidArea.x, this.gp.player.worldY + this.gp.player.solidArea.y);
        }
    }

    private void spawnDungeonOrcs() {
        this.orcsSpawned = 0;
        this.spawnOrcAtOffset(-2, -2);
        this.spawnOrcAtOffset(5, -2);
        this.spawnOrcAtOffset(-2, 2);
        this.spawnOrcAtOffset(5, 2);
        this.spawnOrcAtOffset(2, 6);
        this.orcsAlive = true;
    }

    private void spawnOrcAtOffset(int n, int n2) {
        for (int i = 0; i < this.gp.monster[this.gp.currentMap].length; ++i) {
            if (this.gp.monster[this.gp.currentMap][i] != null) continue;
            MON_DungeonOrc mON_DungeonOrc = new MON_DungeonOrc(this.gp);
            Objects.requireNonNull(this.gp);
            mON_DungeonOrc.worldX = this.worldX + n * 48;
            Objects.requireNonNull(this.gp);
            mON_DungeonOrc.worldY = this.worldY + n2 * 48;
            this.gp.monster[this.gp.currentMap][i] = mON_DungeonOrc;
            ++this.orcsSpawned;
            break;
        }
    }

    public void checkDrop() {
        this.gp.bossBattleOn = false;
        Progress.skeletonLordDefeated = true;
        this.gp.stopMusic();
        this.gp.playMusic(19);
        for (int i = 0; i < this.gp.obj[1].length; ++i) {
            if (this.gp.obj[this.gp.currentMap][i] == null || !this.gp.obj[this.gp.currentMap][i].name.equals("Iron Door")) continue;
            this.gp.playSE(21);
            this.gp.obj[this.gp.currentMap][i] = null;
        }
        this.startDialogue(this, 1);
        this.dropItem(new OBJ_Sword_Epic(this.gp));
    }
}
