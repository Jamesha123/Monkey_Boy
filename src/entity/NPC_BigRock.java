/*
 * Decompiled with CFR 0.152.
 */
package entity;

import entity.Entity;
import java.awt.Rectangle;
import java.util.ArrayList;
import main.GamePanel;
import tile_interactive.InteractiveTile;

public class NPC_BigRock
extends Entity {
    public static final String npcName = "BigRock";

    public NPC_BigRock(GamePanel gp) {
        super(gp);
        this.name = npcName;
        this.direction = "down";
        this.speed = 4;
        this.solidArea = new Rectangle();
        this.solidArea.x = 2;
        this.solidArea.y = 6;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
        this.solidArea.width = 44;
        this.solidArea.height = 40;
        this.getNPCImage();
        this.setDialogue();
    }

    public void getNPCImage() {
        this.up1 = this.setup("/NPC/bigrock", this.gp.tileSize, this.gp.tileSize);
        this.up2 = this.setup("/NPC/bigrock", this.gp.tileSize, this.gp.tileSize);
        this.down1 = this.setup("/NPC/bigrock", this.gp.tileSize, this.gp.tileSize);
        this.down2 = this.setup("/NPC/bigrock", this.gp.tileSize, this.gp.tileSize);
        this.left1 = this.setup("/NPC/bigrock", this.gp.tileSize, this.gp.tileSize);
        this.left2 = this.setup("/NPC/bigrock", this.gp.tileSize, this.gp.tileSize);
        this.right1 = this.setup("/NPC/bigrock", this.gp.tileSize, this.gp.tileSize);
        this.right2 = this.setup("/NPC/bigrock", this.gp.tileSize, this.gp.tileSize);
    }

    public void setDialogue() {
        this.dialogues[0][0] = "PLAYER: It's a giant rock! Perhaps it is used to weigh \ndown the metal plate.";
    }

    @Override
    public void speak() {
        this.startDialogue(this, this.dialogueSet);
        ++this.dialogueSet;
        if (this.dialogues[this.dialogueSet][0] == null) {
            --this.dialogueSet;
        }
    }

    @Override
    public void update() {
        this.detectPlate();
    }

    @Override
    public void move(String direction) {
        this.direction = direction;
        this.checkCollision();
        if (!this.collisionOn) {
            switch (this.direction) {
                case "up": {
                    this.worldY -= this.speed;
                    break;
                }
                case "down": {
                    this.worldY += this.speed;
                    break;
                }
                case "left": {
                    this.worldX -= this.speed;
                    break;
                }
                case "right": {
                    this.worldX += this.speed;
                }
            }
        }
        this.detectPlate();
    }

    public void detectPlate() {
        ArrayList<InteractiveTile> metalPlates = new ArrayList<InteractiveTile>();
        ArrayList<Entity> bigRocks = new ArrayList<Entity>();
        for (int i = 0; i < this.gp.iTile[1].length; ++i) {
            if (this.gp.iTile[this.gp.currentMap][i] == null || this.gp.iTile[this.gp.currentMap][i].name == null || !this.gp.iTile[this.gp.currentMap][i].name.equals("Metal Plate")) continue;
            metalPlates.add(this.gp.iTile[this.gp.currentMap][i]);
        }
        for (int i = 0; i < this.gp.npc[1].length; ++i) {
            if (this.gp.npc[this.gp.currentMap][i] == null || !this.gp.npc[this.gp.currentMap][i].name.equals(npcName)) continue;
            bigRocks.add(this.gp.npc[this.gp.currentMap][i]);
        }
        int linkedRockCount = 0;
        for (int i = 0; i < metalPlates.size(); ++i) {
            int deltaY = Math.abs(this.worldY - metalPlates.get(i).worldY);
            int deltaX = Math.abs(this.worldX - metalPlates.get(i).worldX);
            int maxDelta = Math.max(deltaX, deltaY);
            if (maxDelta < 8) {
                if (this.linkedEntity != null) continue;
                this.linkedEntity = metalPlates.get(i);
                this.gp.playSE(3);
                continue;
            }
            if (this.linkedEntity != metalPlates.get(i)) continue;
            this.linkedEntity = null;
        }
        for (int i = 0; i < bigRocks.size(); ++i) {
            if (bigRocks.get(i).linkedEntity == null) continue;
            ++linkedRockCount;
        }
        if (linkedRockCount == bigRocks.size()) {
            for (int i = 0; i < this.gp.obj[1].length; ++i) {
                if (this.gp.obj[this.gp.currentMap][i] == null || !this.gp.obj[this.gp.currentMap][i].name.equals("Iron Door")) continue;
                this.gp.obj[this.gp.currentMap][i] = null;
                this.gp.playSE(21);
            }
        }
    }
}
