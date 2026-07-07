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
        int entityLeftX = entity.worldX + entity.solidArea.x;
        int entityRightX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.worldY + entity.solidArea.y;
        int entityBottomY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
        int leftCol = entityLeftX / this.gp.tileSize;
        int rightCol = entityRightX / this.gp.tileSize;
        int topRow = entityTopY / this.gp.tileSize;
        int bottomRow = entityBottomY / this.gp.tileSize;
        String direction = entity.direction;
        if (entity.knockBack) {
            direction = entity.knockBackDirection;
        }
        switch (direction) {
            case "up": {
                topRow = (entityTopY - entity.speed) / this.gp.tileSize;
                int tileNum1 = this.gp.tileM.mapTileNum[this.gp.currentMap][leftCol][topRow];
                int tileNum2 = this.gp.tileM.mapTileNum[this.gp.currentMap][rightCol][topRow];
                if (!this.gp.tileM.tile[tileNum1].collision && !this.gp.tileM.tile[tileNum2].collision) break;
                entity.collisionOn = true;
                break;
            }
            case "down": {
                bottomRow = (entityBottomY + entity.speed) / this.gp.tileSize;
                int tileNum1 = this.gp.tileM.mapTileNum[this.gp.currentMap][leftCol][bottomRow];
                int tileNum2 = this.gp.tileM.mapTileNum[this.gp.currentMap][rightCol][bottomRow];
                if (!this.gp.tileM.tile[tileNum1].collision && !this.gp.tileM.tile[tileNum2].collision) break;
                entity.collisionOn = true;
                break;
            }
            case "left": {
                leftCol = (entityLeftX - entity.speed) / this.gp.tileSize;
                int tileNum1 = this.gp.tileM.mapTileNum[this.gp.currentMap][leftCol][topRow];
                int tileNum2 = this.gp.tileM.mapTileNum[this.gp.currentMap][leftCol][bottomRow];
                if (!this.gp.tileM.tile[tileNum1].collision && !this.gp.tileM.tile[tileNum2].collision) break;
                entity.collisionOn = true;
                break;
            }
            case "right": {
                rightCol = (entityRightX + entity.speed) / this.gp.tileSize;
                int tileNum1 = this.gp.tileM.mapTileNum[this.gp.currentMap][rightCol][topRow];
                int tileNum2 = this.gp.tileM.mapTileNum[this.gp.currentMap][rightCol][bottomRow];
                if (!this.gp.tileM.tile[tileNum1].collision && !this.gp.tileM.tile[tileNum2].collision) break;
                entity.collisionOn = true;
            }
        }
    }

    public int checkObject(Entity entity, boolean player) {
        int index = 999;
        String direction = entity.direction;
        if (entity.knockBack) {
            direction = entity.knockBackDirection;
        }
        for (int i = 0; i < this.gp.obj[1].length; ++i) {
            if (this.gp.obj[this.gp.currentMap][i] == null) continue;
            entity.solidArea.x = entity.worldX + entity.solidArea.x;
            entity.solidArea.y = entity.worldY + entity.solidArea.y;
            this.gp.obj[this.gp.currentMap][i].solidArea.x = this.gp.obj[this.gp.currentMap][i].worldX + this.gp.obj[this.gp.currentMap][i].solidArea.x;
            this.gp.obj[this.gp.currentMap][i].solidArea.y = this.gp.obj[this.gp.currentMap][i].worldY + this.gp.obj[this.gp.currentMap][i].solidArea.y;
            switch (direction) {
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
                if (player) {
                    index = i;
                }
            }
            entity.solidArea.x = entity.solidAreaDefaultX;
            entity.solidArea.y = entity.solidAreaDefaultY;
            this.gp.obj[this.gp.currentMap][i].solidArea.x = this.gp.obj[this.gp.currentMap][i].solidAreaDefaultX;
            this.gp.obj[this.gp.currentMap][i].solidArea.y = this.gp.obj[this.gp.currentMap][i].solidAreaDefaultY;
        }
        return index;
    }

    public int checkEntity(Entity entity, Entity[][] entityArray) {
        int index = 999;
        String direction = entity.direction;
        if (entity.knockBack) {
            direction = entity.knockBackDirection;
        }
        for (int i = 0; i < entityArray[1].length; ++i) {
            if (entityArray[this.gp.currentMap][i] == null) continue;
            entity.solidArea.x = entity.worldX + entity.solidArea.x;
            entity.solidArea.y = entity.worldY + entity.solidArea.y;
            entityArray[this.gp.currentMap][i].solidArea.x = entityArray[this.gp.currentMap][i].worldX + entityArray[this.gp.currentMap][i].solidArea.x;
            entityArray[this.gp.currentMap][i].solidArea.y = entityArray[this.gp.currentMap][i].worldY + entityArray[this.gp.currentMap][i].solidArea.y;
            switch (direction) {
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
                index = i;
            }
            entity.solidArea.x = entity.solidAreaDefaultX;
            entity.solidArea.y = entity.solidAreaDefaultY;
            entityArray[this.gp.currentMap][i].solidArea.x = entityArray[this.gp.currentMap][i].solidAreaDefaultX;
            entityArray[this.gp.currentMap][i].solidArea.y = entityArray[this.gp.currentMap][i].solidAreaDefaultY;
        }
        return index;
    }

    public boolean checkPlayer(Entity entity) {
        boolean contactPlayer = false;
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
            contactPlayer = true;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        this.gp.player.solidArea.x = this.gp.player.solidAreaDefaultX;
        this.gp.player.solidArea.y = this.gp.player.solidAreaDefaultY;
        return contactPlayer;
    }

    public int checkInteractiveTile(Entity entity) {
        int index = 999;
        String direction = entity.direction;
        if (entity.knockBack) {
            direction = entity.knockBackDirection;
        }
        for (int i = 0; i < this.gp.iTile[this.gp.currentMap].length; ++i) {
            if (this.gp.iTile[this.gp.currentMap][i] == null) continue;
            entity.solidArea.x = entity.worldX + entity.solidArea.x;
            entity.solidArea.y = entity.worldY + entity.solidArea.y;
            this.gp.iTile[this.gp.currentMap][i].solidArea.x = this.gp.iTile[this.gp.currentMap][i].worldX + this.gp.iTile[this.gp.currentMap][i].solidArea.x;
            this.gp.iTile[this.gp.currentMap][i].solidArea.y = this.gp.iTile[this.gp.currentMap][i].worldY + this.gp.iTile[this.gp.currentMap][i].solidArea.y;
            switch (direction) {
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
                    index = i;
                } else {
                    entity.collisionOn = true;
                    index = i;
                }
            }
            entity.solidArea.x = entity.solidAreaDefaultX;
            entity.solidArea.y = entity.solidAreaDefaultY;
            this.gp.iTile[this.gp.currentMap][i].solidArea.x = this.gp.iTile[this.gp.currentMap][i].solidAreaDefaultX;
            this.gp.iTile[this.gp.currentMap][i].solidArea.y = this.gp.iTile[this.gp.currentMap][i].solidAreaDefaultY;
        }
        return index;
    }
}
