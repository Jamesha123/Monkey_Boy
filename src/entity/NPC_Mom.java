/*
 * Decompiled with CFR 0.152.
 */
package entity;

import entity.Entity;
import java.awt.Rectangle;
import main.GamePanel;

public class NPC_Mom
extends Entity {
    public NPC_Mom(GamePanel gamePanel) {
        super(gamePanel);
        this.name = "Mom";
        this.type = 1;
        this.solidArea = new Rectangle();
        this.solidArea.x = 8;
        this.solidArea.y = 16;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
        this.solidArea.width = 30;
        this.solidArea.height = 60;
        this.walkAnimationSpeed = 40;
        this.getNPCImage();
        this.setDialogue();
    }

    public void getNPCImage() {
        this.down1 = this.setup("/NPC/mom", this.gp.tileSize, this.gp.tileSize * 2);
        this.down2 = this.setup("/NPC/mom2", this.gp.tileSize, this.gp.tileSize * 2);
    }

    public void setDialogue() {
        this.dialogues[0][0] = "Good morning, kiddo.\nI'm glad to see that you are awake.";
        this.dialogues[0][1] = "Could you do me a favor?";
        this.dialogues[0][2] = "Can you go to the village and buy me some \nsugar?";
        this.dialogues[0][3] = "Here's some money.";
        this.dialogues[0][4] = "Remember to stay on the path and don't go \ninto the forest. There are monsters always \nlurking.";
        this.dialogues[0][5] = "I'll be waiting for you.";
        this.dialogues[1][0] = "Hurry back, kiddo.";
        this.dialogues[1][1] = "Don't forget the sugar!";
        this.dialogues[2][0] = "You're back! Did you get the sugar?";
        this.dialogues[2][1] = "Thank you so much, kiddo!";
        this.dialogues[2][2] = "You're such a good helper.";
        this.dialogues[2][3] = "PLAYER: Thank you, Mom.";
        this.dialogues[2][4] = "PLAYER: Now I can go rest.";
        this.dialogues[3][0] = "Thanks for getting the sugar, kiddo.";
    }

    @Override
    public void speak() {
        this.dialogueSet = this.determineDialogueSet();
        this.startDialogue(this, this.dialogueSet);
        if (this.dialogueSet == 0) {
            this.gp.talkedToMom = true;
        }
    }

    public void advanceDialogue() {
        ++this.dialogueIndex;
        if (this.dialogueSet == 0 && this.dialogueIndex == 4 && !this.gp.hasReceivedMomCoins) {
            this.gp.player.coin += 50;
            this.gp.ui.addMessage("You received 50 coins.");
            this.gp.hasReceivedMomCoins = true;
        }
    }

    private int determineDialogueSet() {
        boolean bl;
        boolean bl2 = bl = this.gp.player.searchItemInInventory("Sugar") != 999;
        if (this.gp.questCompleted) {
            return 3;
        }
        if (bl) {
            this.gp.questCompleted = true;
            return 2;
        }
        if (this.gp.talkedToMom) {
            return 1;
        }
        return 0;
    }
}
