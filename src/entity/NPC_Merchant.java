/*
 * Decompiled with CFR 0.152.
 */
package entity;

import entity.Entity;
import java.util.Objects;
import main.GamePanel;
import objects.OBJ_Lantern;
import objects.OBJ_Potion_Red;
import objects.OBJ_Shield_Blue;
import objects.OBJ_Shield_Wood;
import objects.OBJ_Sugar;
import objects.OBJ_Sword_Normal;
import objects.OBJ_Torch;

public class NPC_Merchant
extends Entity {
    public NPC_Merchant(GamePanel gamePanel) {
        super(gamePanel);
        this.name = "Merchant";
        this.type = 1;
        this.getNPCImage();
        this.setDialogue();
        this.setItems();
    }

    public void getNPCImage() {
        this.up1 = this.setup("/NPC/merchant_down_1", this.gp.tileSize, this.gp.tileSize);
        this.up2 = this.setup("/NPC/merchant_down_2", this.gp.tileSize, this.gp.tileSize);
        this.down1 = this.setup("/NPC/merchant_down_1", this.gp.tileSize, this.gp.tileSize);
        this.down2 = this.setup("/NPC/merchant_down_2", this.gp.tileSize, this.gp.tileSize);
        this.left1 = this.setup("/NPC/merchant_down_1", this.gp.tileSize, this.gp.tileSize);
        this.left2 = this.setup("/NPC/merchant_down_2", this.gp.tileSize, this.gp.tileSize);
        this.right1 = this.setup("/NPC/merchant_down_1", this.gp.tileSize, this.gp.tileSize);
        this.right2 = this.setup("/NPC/merchant_down_2", this.gp.tileSize, this.gp.tileSize);
    }

    public void setDialogue() {
        this.dialogues[0][0] = "Welcome! Welcome, to my shop.";
        this.dialogues[1][0] = "Come again, Goodbye hehe!";
        this.dialogues[2][0] = "You need more coins to buy that!";
        this.dialogues[3][0] = "You cannot carry any more items!";
        this.dialogues[4][0] = "You cannot sell an equipped item!";
        this.dialogues[5][0] = "You are lucky, that's the last bag of sugar \nfor the season!";
        this.dialogues[6][0] = "You just bouught that, you cannot get a \nrefund!";
    }

    public void setItems() {
        this.inventory.add(new OBJ_Potion_Red(this.gp));
        this.inventory.add(new OBJ_Sword_Normal(this.gp));
        this.inventory.add(new OBJ_Shield_Wood(this.gp));
        this.inventory.add(new OBJ_Shield_Blue(this.gp));
        this.inventory.add(new OBJ_Lantern(this.gp));
        this.inventory.add(new OBJ_Torch(this.gp));
        if (!this.gp.sugarPurchased) {
            this.inventory.add(new OBJ_Sugar(this.gp));
        }
    }

    @Override
    public void speak() {
        this.facePlayer();
        Objects.requireNonNull(this.gp);
        this.gp.gameState = 7;
        this.gp.ui.npc = this;
    }
}
