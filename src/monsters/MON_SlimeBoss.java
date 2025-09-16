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
import java.util.Objects;
import main.GamePanel;
import monsters.MON_BlueSlime;
import monsters.MON_GreenSlime;
import monsters.MON_RedSlime;
import objects.OBJ_Key;
import objects.OBJ_Rock;

public class MON_SlimeBoss
extends Entity {
    GamePanel gp;
    public static final String monName = "Slime Boss";
    private int rockAttackCounter = 0;
    private int slimeSpawnCounter = 1200;
    private final int ROCK_ATTACK_INTERVAL = 300;
    private final int SLIME_SPAWN_INTERVAL = 1500;
    private int size = 0;

    public MON_SlimeBoss(GamePanel gamePanel) {
        super(gamePanel);
        this.gp = gamePanel;
        this.type = 2;
        this.boss = true;
        this.name = monName;
        this.speed = this.defaultSpeed = 1;
        this.life = this.maxLife = 150;
        this.attack = 13;
        this.defense = 3;
        this.exp = 100;
        this.canContactDamage = true;
        this.sleep = true;
        Objects.requireNonNull(gamePanel);
        this.size = 48 * 5;
        this.solidArea.x = 12;
        this.solidArea.y = 80;
        this.solidArea.width = this.size - 25;
        this.solidArea.height = this.size - 80;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
        this.projectile = new OBJ_Rock(gamePanel, "boss");
        this.rate = 1;
        this.getImage();
        this.setDialogue();
    }

    public void getImage() {
        int n = 5;
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.up1 = this.setup("/Monster/greenslime_down_1.png", 48 * n, 48 * n);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.up2 = this.setup("/Monster/greenslime_down_2.png", 48 * n, 48 * n);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.down1 = this.setup("/Monster/greenslime_down_1.png", 48 * n, 48 * n);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.down2 = this.setup("/Monster/greenslime_down_2.png", 48 * n, 48 * n);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.left1 = this.setup("/Monster/greenslime_down_1.png", 48 * n, 48 * n);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.left2 = this.setup("/Monster/greenslime_down_2.png", 48 * n, 48 * n);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.right1 = this.setup("/Monster/greenslime_down_1.png", 48 * n, 48 * n);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.right2 = this.setup("/Monster/greenslime_down_2.png", 48 * n, 48 * n);
    }

    public void setDialogue() {
        this.dialogues[0][0] = "BLORP! I am the Slime King!";
        this.dialogues[0][1] = "Another adventurer has dared to challenge \nme?!";
        this.dialogues[0][2] = "PREPARE TO BE ABSORBED!";
    }

    public void setAction() {
        this.moveTowardPlayer(60);
        ++this.rockAttackCounter;
        ++this.slimeSpawnCounter;
        if (this.rockAttackCounter >= 300) {
            this.checkBossShoot(1, 0);
            this.rockAttackCounter = 0;
        }
        if (this.slimeSpawnCounter >= 1500) {
            this.spawnSlimeArmy();
            this.slimeSpawnCounter = 0;
        }
    }

    private void spawnSlimeArmy() {
        this.spawnSlimeAtOffset(new MON_RedSlime(this.gp), -1, 1);
        this.spawnSlimeAtOffset(new MON_RedSlime(this.gp), 5, 1);
        this.spawnSlimeAtOffset(new MON_GreenSlime(this.gp), -1, 5);
        this.spawnSlimeAtOffset(new MON_BlueSlime(this.gp), 5, 5);
    }

    private void spawnSlimeAtOffset(Entity entity, int n, int n2) {
        for (int i = 0; i < this.gp.monster[1].length; ++i) {
            if (this.gp.monster[this.gp.currentMap][i] != null) continue;
            Objects.requireNonNull(this.gp);
            entity.worldX = this.worldX + n * 48;
            Objects.requireNonNull(this.gp);
            entity.worldY = this.worldY + n2 * 48;
            entity.sleep = false;
            this.gp.monster[this.gp.currentMap][i] = entity;
            break;
        }
    }

    public void damageReaction() {
        this.actionLockCounter = 0;
    }

    public void checkDrop() {
        this.gp.bossBattleOn = false;
        Progress.slimeBossDefeated = true;
        this.gp.stopMusic();
        this.gp.playMusic(0);
        this.dropItem(new OBJ_Key(this.gp));
    }
}
