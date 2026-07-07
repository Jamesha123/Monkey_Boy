/*
 * Decompiled with CFR 0.152.
 */
package entity;

import entity.Entity;
import java.awt.Rectangle;
import main.GamePanel;

public class NPC_BlackSmith
extends Entity {
    public NPC_BlackSmith(GamePanel gp) {
        super(gp);
        this.name = "BlackSmith";
        this.type = 1;
        this.speed = this.defaultSpeed = 1;
        this.solidArea = new Rectangle();
        this.solidArea.x = 8;
        this.solidArea.y = 16;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
        this.solidArea.width = 30;
        this.solidArea.height = 30;
        this.getNPCImage();
        this.setDialogue();
    }

    public void getNPCImage() {
        int spriteScale = 1;
        this.up1 = this.setup("/NPC/blacksmith_right_1", this.gp.tileSize * spriteScale, this.gp.tileSize * spriteScale);
        this.up2 = this.setup("/NPC/blacksmith_right_2", this.gp.tileSize * spriteScale, this.gp.tileSize * spriteScale);
        this.down1 = this.setup("/NPC/blacksmith_right_1", this.gp.tileSize * spriteScale, this.gp.tileSize * spriteScale);
        this.down2 = this.setup("/NPC/blacksmith_right_2", this.gp.tileSize * spriteScale, this.gp.tileSize * spriteScale);
        this.left1 = this.setup("/NPC/blacksmith_left_1", this.gp.tileSize * spriteScale, this.gp.tileSize * spriteScale);
        this.left2 = this.setup("/NPC/blacksmith_left_2", this.gp.tileSize * spriteScale, this.gp.tileSize * spriteScale);
        this.right1 = this.setup("/NPC/blacksmith_right_1", this.gp.tileSize * spriteScale, this.gp.tileSize * spriteScale);
        this.right2 = this.setup("/NPC/blacksmith_right_2", this.gp.tileSize * spriteScale, this.gp.tileSize * spriteScale);
    }

    public void setDialogue() {
        this.dialogues[0][0] = "BLACKSMITH: Beautiful day, isn't it?";
        this.dialogues[0][1] = "PLAYER: Yeah it is.";
    }

    @Override
    public void setAction() {
        this.getRandomDirection(120);
    }

    @Override
    public void speak() {
        this.facePlayer();
        this.startDialogue(this, 0);
    }
}
