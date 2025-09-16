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

    public NPC_BigRock(GamePanel gamePanel) {
        super(gamePanel);
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
    public void move(String string) {
        this.direction = string;
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
        int n;
        int n2;
        ArrayList<InteractiveTile> arrayList = new ArrayList<InteractiveTile>();
        ArrayList<Entity> arrayList2 = new ArrayList<Entity>();
        for (n2 = 0; n2 < this.gp.iTile[1].length; ++n2) {
            if (this.gp.iTile[this.gp.currentMap][n2] == null || this.gp.iTile[this.gp.currentMap][n2].name == null || !this.gp.iTile[this.gp.currentMap][n2].name.equals("Metal Plate")) continue;
            arrayList.add(this.gp.iTile[this.gp.currentMap][n2]);
        }
        for (n2 = 0; n2 < this.gp.npc[1].length; ++n2) {
            if (this.gp.npc[this.gp.currentMap][n2] == null || !this.gp.npc[this.gp.currentMap][n2].name.equals(npcName)) continue;
            arrayList2.add(this.gp.npc[this.gp.currentMap][n2]);
        }
        n2 = 0;
        for (n = 0; n < arrayList.size(); ++n) {
            int n3;
            int n4 = Math.abs(this.worldX - ((InteractiveTile)arrayList.get((int)n)).worldX);
            int n5 = Math.max(n4, n3 = Math.abs(this.worldY - ((InteractiveTile)arrayList.get((int)n)).worldY));
            if (n5 < 8) {
                if (this.linkedEntity != null) continue;
                this.linkedEntity = (Entity)arrayList.get(n);
                this.gp.playSE(3);
                continue;
            }
            if (this.linkedEntity != arrayList.get(n)) continue;
            this.linkedEntity = null;
        }
        for (n = 0; n < arrayList2.size(); ++n) {
            if (((Entity)arrayList2.get((int)n)).linkedEntity == null) continue;
            ++n2;
        }
        if (n2 == arrayList2.size()) {
            for (n = 0; n < this.gp.obj[1].length; ++n) {
                if (this.gp.obj[this.gp.currentMap][n] == null || !this.gp.obj[this.gp.currentMap][n].name.equals("Iron Door")) continue;
                this.gp.obj[this.gp.currentMap][n] = null;
                this.gp.playSE(21);
            }
        }
    }
}
