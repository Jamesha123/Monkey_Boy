/*
 * Decompiled with CFR 0.152.
 */
package entity;

import entity.Entity;
import java.awt.Rectangle;
import main.GamePanel;

public class NPC_Garther
extends Entity {
    public NPC_Garther(GamePanel gamePanel) {
        super(gamePanel);
        this.name = "Garther";
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
        int n = 1;
        this.up1 = this.setup("/NPC/garther_right_1", this.gp.tileSize * n, this.gp.tileSize * n);
        this.up2 = this.setup("/NPC/garther_right_2", this.gp.tileSize * n, this.gp.tileSize * n);
        this.down1 = this.setup("/NPC/garther_right_1", this.gp.tileSize * n, this.gp.tileSize * n);
        this.down2 = this.setup("/NPC/garther_right_2", this.gp.tileSize * n, this.gp.tileSize * n);
        this.left1 = this.setup("/NPC/garther_left_1", this.gp.tileSize * n, this.gp.tileSize * n);
        this.left2 = this.setup("/NPC/garther_left_2", this.gp.tileSize * n, this.gp.tileSize * n);
        this.right1 = this.setup("/NPC/garther_right_1", this.gp.tileSize * n, this.gp.tileSize * n);
        this.right2 = this.setup("/NPC/garther_right_2", this.gp.tileSize * n, this.gp.tileSize * n);
    }

    public void setDialogue() {
        this.dialogues[0][0] = "Garther: Best, avoid the forest at all cost.";
        this.dialogues[0][1] = "Garther: Many monsters lurks there.";
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
