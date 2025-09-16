/*
 * Decompiled with CFR 0.152.
 */
package entity;

import entity.Entity;
import java.awt.Rectangle;
import main.GamePanel;

public class NPC_Dad
extends Entity {
    public NPC_Dad(GamePanel gamePanel) {
        super(gamePanel);
        this.name = "Dad";
        this.type = 1;
        this.solidArea = new Rectangle();
        this.solidArea.x = 8;
        this.solidArea.y = 16;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
        this.solidArea.width = 60;
        this.solidArea.height = 80;
        this.walkAnimationSpeed = 80;
        this.getNPCImage();
        this.setDialogue();
    }

    public void getNPCImage() {
        this.down1 = this.setup("/NPC/dad", this.gp.tileSize * 2, this.gp.tileSize * 2);
        this.down2 = this.setup("/NPC/dad2", this.gp.tileSize * 2, this.gp.tileSize * 2);
    }

    public void setDialogue() {
        this.dialogues[0][0] = "...";
        this.dialogues[1][0] = "Don't do anything stupid.";
        this.dialogues[2][0] = "I can't believe you got the sugar back!";
        this.dialogues[2][1] = "You did well, kid.";
    }

    @Override
    public void speak() {
        if (this.gp.questCompleted) {
            this.startDialogue(this, 2);
        } else if (this.gp.talkedToMom) {
            this.startDialogue(this, 1);
        } else {
            this.startDialogue(this, 0);
        }
    }
}
