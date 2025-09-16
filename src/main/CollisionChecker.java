/*
 * Decompiled with CFR 0.152.
 */
package main;

import entity.Entity;
import main.GamePanel;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gamePanel) {
        this.gp = gamePanel;
    }

    public void checkTile(Entity entity) {
        int n = entity.worldX + entity.solidArea.x;
        int n2 = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int n3 = entity.worldY + entity.solidArea.y;
        int n4 = entity.worldY + entity.solidArea.y + entity.solidArea.height;
        int n5 = n / this.gp.tileSize;
        int n6 = n2 / this.gp.tileSize;
        int n7 = n3 / this.gp.tileSize;
        int n8 = n4 / this.gp.tileSize;
        String string = entity.direction;
        if (entity.knockBack) {
            string = entity.knockBackDirection;
        }
        switch (string) {
            case "up": {
                n7 = (n3 - entity.speed) / this.gp.tileSize;
                int n9 = this.gp.tileM.mapTileNum[this.gp.currentMap][n5][n7];
                int n10 = this.gp.tileM.mapTileNum[this.gp.currentMap][n6][n7];
                if (!this.gp.tileM.tile[n9].collision && !this.gp.tileM.tile[n10].collision) break;
                entity.collisionOn = true;
                break;
            }
            case "down": {
                n8 = (n4 + entity.speed) / this.gp.tileSize;
                int n11 = this.gp.tileM.mapTileNum[this.gp.currentMap][n5][n8];
                int n12 = this.gp.tileM.mapTileNum[this.gp.currentMap][n6][n8];
                if (!this.gp.tileM.tile[n11].collision && !this.gp.tileM.tile[n12].collision) break;
                entity.collisionOn = true;
                break;
            }
            case "left": {
                n5 = (n - entity.speed) / this.gp.tileSize;
                int n13 = this.gp.tileM.mapTileNum[this.gp.currentMap][n5][n7];
                int n14 = this.gp.tileM.mapTileNum[this.gp.currentMap][n5][n8];
                if (!this.gp.tileM.tile[n13].collision && !this.gp.tileM.tile[n14].collision) break;
                entity.collisionOn = true;
                break;
            }
            case "right": {
                n6 = (n2 + entity.speed) / this.gp.tileSize;
                int n15 = this.gp.tileM.mapTileNum[this.gp.currentMap][n6][n7];
                int n16 = this.gp.tileM.mapTileNum[this.gp.currentMap][n6][n8];
                if (!this.gp.tileM.tile[n15].collision && !this.gp.tileM.tile[n16].collision) break;
                entity.collisionOn = true;
            }
        }
    }

    public int checkObject(Entity entity, boolean bl) {
        int n = 999;
        String string = entity.direction;
        if (entity.knockBack) {
            string = entity.knockBackDirection;
        }
        for (int i = 0; i < this.gp.obj[1].length; ++i) {
            if (this.gp.obj[this.gp.currentMap][i] == null) continue;
            entity.solidArea.x = entity.worldX + entity.solidArea.x;
            entity.solidArea.y = entity.worldY + entity.solidArea.y;
            this.gp.obj[this.gp.currentMap][i].solidArea.x = this.gp.obj[this.gp.currentMap][i].worldX + this.gp.obj[this.gp.currentMap][i].solidArea.x;
            this.gp.obj[this.gp.currentMap][i].solidArea.y = this.gp.obj[this.gp.currentMap][i].worldY + this.gp.obj[this.gp.currentMap][i].solidArea.y;
            switch (string) {
                case "up": {
                    entity.solidArea.y -= entity.speed;
                    break;
                }
                case "down": {
                    entity.solidArea.y += entity.speed;
                    break;
                }
                case "left": {
                    entity.solidArea.x -= entity.speed;
                    break;
                }
                case "right": {
                    entity.solidArea.x += entity.speed;
                }
            }
            if (entity.solidArea.intersects(this.gp.obj[this.gp.currentMap][i].solidArea)) {
                if (this.gp.obj[this.gp.currentMap][i].collision) {
                    entity.collisionOn = true;
                }
                if (bl) {
                    n = i;
                }
            }
            entity.solidArea.x = entity.solidAreaDefaultX;
            entity.solidArea.y = entity.solidAreaDefaultY;
            this.gp.obj[this.gp.currentMap][i].solidArea.x = this.gp.obj[this.gp.currentMap][i].solidAreaDefaultX;
            this.gp.obj[this.gp.currentMap][i].solidArea.y = this.gp.obj[this.gp.currentMap][i].solidAreaDefaultY;
        }
        return n;
    }

    public int checkEntity(Entity entity, Entity[][] entityArray) {
        int n = 999;
        String string = entity.direction;
        if (entity.knockBack) {
            string = entity.knockBackDirection;
        }
        for (int i = 0; i < entityArray[1].length; ++i) {
            if (entityArray[this.gp.currentMap][i] == null) continue;
            entity.solidArea.x = entity.worldX + entity.solidArea.x;
            entity.solidArea.y = entity.worldY + entity.solidArea.y;
            entityArray[this.gp.currentMap][i].solidArea.x = entityArray[this.gp.currentMap][i].worldX + entityArray[this.gp.currentMap][i].solidArea.x;
            entityArray[this.gp.currentMap][i].solidArea.y = entityArray[this.gp.currentMap][i].worldY + entityArray[this.gp.currentMap][i].solidArea.y;
            switch (string) {
                case "up": {
                    entity.solidArea.y -= entity.speed;
                    break;
                }
                case "down": {
                    entity.solidArea.y += entity.speed;
                    break;
                }
                case "left": {
                    entity.solidArea.x -= entity.speed;
                    break;
                }
                case "right": {
                    entity.solidArea.x += entity.speed;
                }
            }
            if (entity.solidArea.intersects(entityArray[this.gp.currentMap][i].solidArea) && entityArray[this.gp.currentMap][i] != entity) {
                entity.collisionOn = true;
                n = i;
            }
            entity.solidArea.x = entity.solidAreaDefaultX;
            entity.solidArea.y = entity.solidAreaDefaultY;
            entityArray[this.gp.currentMap][i].solidArea.x = entityArray[this.gp.currentMap][i].solidAreaDefaultX;
            entityArray[this.gp.currentMap][i].solidArea.y = entityArray[this.gp.currentMap][i].solidAreaDefaultY;
        }
        return n;
    }

    public boolean checkPlayer(Entity entity) {
        boolean bl = false;
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
        this.gp.player.solidArea.x = this.gp.player.worldX + this.gp.player.solidArea.x;
        this.gp.player.solidArea.y = this.gp.player.worldY + this.gp.player.solidArea.y;
        switch (entity.direction) {
            case "up": {
                entity.solidArea.y -= entity.speed;
                break;
            }
            case "down": {
                entity.solidArea.y += entity.speed;
                break;
            }
            case "left": {
                entity.solidArea.x -= entity.speed;
                break;
            }
            case "right": {
                entity.solidArea.x += entity.speed;
            }
        }
        if (entity.solidArea.intersects(this.gp.player.solidArea)) {
            entity.collisionOn = true;
            bl = true;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        this.gp.player.solidArea.x = this.gp.player.solidAreaDefaultX;
        this.gp.player.solidArea.y = this.gp.player.solidAreaDefaultY;
        return bl;
    }

    public int checkInteractiveTile(Entity entity) {
        int n = 999;
        String string = entity.direction;
        if (entity.knockBack) {
            string = entity.knockBackDirection;
        }
        for (int i = 0; i < this.gp.iTile[this.gp.currentMap].length; ++i) {
            if (this.gp.iTile[this.gp.currentMap][i] == null) continue;
            entity.solidArea.x = entity.worldX + entity.solidArea.x;
            entity.solidArea.y = entity.worldY + entity.solidArea.y;
            this.gp.iTile[this.gp.currentMap][i].solidArea.x = this.gp.iTile[this.gp.currentMap][i].worldX + this.gp.iTile[this.gp.currentMap][i].solidArea.x;
            this.gp.iTile[this.gp.currentMap][i].solidArea.y = this.gp.iTile[this.gp.currentMap][i].worldY + this.gp.iTile[this.gp.currentMap][i].solidArea.y;
            switch (string) {
                case "up": {
                    entity.solidArea.y -= entity.speed;
                    break;
                }
                case "down": {
                    entity.solidArea.y += entity.speed;
                    break;
                }
                case "left": {
                    entity.solidArea.x -= entity.speed;
                    break;
                }
                case "right": {
                    entity.solidArea.x += entity.speed;
                }
            }
            if (entity.solidArea.intersects(this.gp.iTile[this.gp.currentMap][i].solidArea)) {
                if (this.gp.iTile[this.gp.currentMap][i].name.equals("Torch")) {
                    n = i;
                } else {
                    entity.collisionOn = true;
                    n = i;
                }
            }
            entity.solidArea.x = entity.solidAreaDefaultX;
            entity.solidArea.y = entity.solidAreaDefaultY;
            this.gp.iTile[this.gp.currentMap][i].solidArea.x = this.gp.iTile[this.gp.currentMap][i].solidAreaDefaultX;
            this.gp.iTile[this.gp.currentMap][i].solidArea.y = this.gp.iTile[this.gp.currentMap][i].solidAreaDefaultY;
        }
        return n;
    }
}
