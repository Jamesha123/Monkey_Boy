/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  entity.Entity
 *  main.GamePanel
 */
package objects;

import entity.Entity;
import java.util.Objects;
import java.util.Random;
import main.GamePanel;

public class OBJ_Chest
extends Entity {
    GamePanel gp;
    public static final String objName = "Chest";
    private int torchAmount = 0;
    private int potionAmount = 0;

    public OBJ_Chest(GamePanel gamePanel) {
        super(gamePanel);
        this.gp = gamePanel;
        this.type = 8;
        this.name = objName;
        Objects.requireNonNull(gamePanel);
        Objects.requireNonNull(gamePanel);
        this.image = this.setup("/Object/chest.png", 48, 48);
        Objects.requireNonNull(gamePanel);
        Objects.requireNonNull(gamePanel);
        this.image2 = this.setup("/Object/chest_opened.png", 48, 48);
        this.down1 = this.image;
        this.collision = true;
        this.solidArea.x = 4;
        this.solidArea.y = 16;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
        this.solidArea.width = 40;
        this.solidArea.height = 32;
    }

    public void setLoot(Entity entity) {
        this.loot = entity;
        if (entity.name == "Bronze Coin") {
            this.gp.player.coin += 20;
        }
        this.setDialogue();
    }

    public void setDialogue() {
        if (this.loot.name.equals("Torch")) {
            this.dialogues[0][0] = "You open the chest and find " + this.torchAmount + " Torches!\n... But you cannot carry any more!";
            this.dialogues[1][0] = "You open the chest and find " + this.torchAmount + " Torches!\nYou obtain " + this.torchAmount + " Torches!";
        } else if (this.loot.name.equals("Red Potion")) {
            this.dialogues[0][0] = "You open the chest and find " + this.potionAmount + " Red Potions!\n... But you cannot carry any more!";
            this.dialogues[1][0] = "You open the chest and find " + this.potionAmount + " Red Potions!\nYou obtain " + this.potionAmount + " Red Potions!";
        } else if (this.loot.name.equals("Wood Shield")) {
            this.dialogues[0][0] = "You open the chest and find a " + this.loot.name + "!\n... But you cannot carry any more!";
            this.dialogues[1][0] = "You open the chest and find a " + this.loot.name + "!\nYou obtain the " + this.loot.name + "!\nEquip to use. Hold 'SPACE' to use it.";
        } else if (this.loot.name.equals("Sugar")) {
            this.dialogues[0][0] = "You open the chest and find a " + this.loot.name + "!\n... But you cannot carry any more!";
            this.dialogues[1][0] = "You open the chest and find a " + this.loot.name + "!\nYou obtain the " + this.loot.name + "!";
            this.dialogues[1][1] = "PLAYER: YES! I found the bag of sugar! I need to take \nthis back home as soon as possible.";
        } else {
            this.dialogues[0][0] = "You open the chest and find a " + this.loot.name + "!\n... But you cannot carry any more!";
            this.dialogues[1][0] = "You open the chest and find a " + this.loot.name + "!\nYou obtain the " + this.loot.name + "!";
        }
        this.dialogues[2][0] = "It's empty";
    }

    public void interact() {
        if (!this.opened) {
            this.gp.playSE(3);
            if (this.loot.name.equals("Torch")) {
                Entity entity = this.gp.eGenerator.getObject("Torch");
                Random random = new Random();
                entity.amount = this.torchAmount = random.nextInt(7) + 3;
                this.setDialogue();
                if (!this.gp.player.canObtainItem(entity)) {
                    this.startDialogue(this, 0);
                } else {
                    this.startDialogue(this, 1);
                    this.down1 = this.image2;
                    this.opened = true;
                }
            } else if (this.loot.name.equals("Red Potion")) {
                Entity entity = this.gp.eGenerator.getObject("Red Potion");
                Random random = new Random();
                entity.amount = this.potionAmount = random.nextInt(5) + 1;
                this.setDialogue();
                if (!this.gp.player.canObtainItem(entity)) {
                    this.startDialogue(this, 0);
                } else {
                    this.startDialogue(this, 1);
                    this.down1 = this.image2;
                    this.opened = true;
                }
            } else if (this.loot.name.equals("Key")) {
                boolean bl = false;
                for (int i = 0; i < this.gp.obj[this.gp.currentMap].length; ++i) {
                    if (this.gp.obj[this.gp.currentMap][i] == null || !this.gp.obj[this.gp.currentMap][i].name.equals("Door")) continue;
                    bl = true;
                    break;
                }
                if (bl) {
                    if (!this.loot.name.equals("Key")) {
                        this.loot = this.gp.eGenerator.getObject("Key");
                    }
                } else if (!this.loot.name.equals("Bag")) {
                    this.loot = this.gp.eGenerator.getObject("Bag");
                }
                this.setDialogue();
                if (!this.gp.player.canObtainItem(this.loot)) {
                    this.startDialogue(this, 0);
                } else {
                    this.startDialogue(this, 1);
                    this.down1 = this.image2;
                    this.opened = true;
                }
            } else if (this.loot.name.equals("Sugar")) {
                this.setDialogue();
                if (!this.gp.player.canObtainItem(this.loot)) {
                    this.startDialogue(this, 0);
                } else {
                    this.startDialogue(this, 1);
                    this.down1 = this.image2;
                    this.opened = true;
                }
            } else if (!this.gp.player.canObtainItem(this.loot)) {
                this.startDialogue(this, 0);
            } else {
                this.startDialogue(this, 1);
                this.down1 = this.image2;
                this.opened = true;
            }
        } else {
            this.startDialogue(this, 2);
        }
    }
}
