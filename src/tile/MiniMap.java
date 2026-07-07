/*
 * Decompiled with CFR 0.152.
 */
package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import main.GamePanel;
import tile.TileManager;

public class MiniMap
extends TileManager {
    GamePanel gp;
    BufferedImage[] worldMap;
    public boolean miniMapOn = false;

    public MiniMap(GamePanel gamePanel) {
        super(gamePanel);
        this.gp = gamePanel;
        this.createWorldMap();
    }

    public void createWorldMap() {
        this.worldMap = new BufferedImage[this.gp.maxMap];
        int mapWidth = this.gp.tileSize * this.gp.maxWorldCol;
        int mapHeight = this.gp.tileSize * this.gp.maxWorldRow;
        for (int i = 0; i < this.gp.maxMap; ++i) {
            this.worldMap[i] = new BufferedImage(mapWidth, mapHeight, 2);
            Graphics2D graphics2D = this.worldMap[i].createGraphics();
            int col = 0;
            int row = 0;
            while (col < this.gp.maxWorldCol && row < this.gp.maxWorldRow) {
                int tileNum = this.mapTileNum[i][col][row];
                int worldX = this.gp.tileSize * col;
                int worldY = this.gp.tileSize * row;
                if (tileNum >= 0 && tileNum < this.tile.length && this.tile[tileNum] != null) {
                    graphics2D.drawImage((Image)this.tile[tileNum].image, worldX, worldY, null);
                }
                if (++col != this.gp.maxWorldCol) continue;
                col = 0;
                ++row;
            }
            graphics2D.dispose();
        }
    }

    public void drawFullMapScreen(Graphics2D graphics2D) {
        graphics2D.setColor(Color.black);
        graphics2D.fillRect(0, 0, this.gp.screenWidth, this.gp.screenHeight);
        int mapDisplayWidth = 500;
        int mapDisplayHeight = 500;
        int mapX = this.gp.screenWidth / 2 - mapDisplayWidth / 2;
        int mapY = this.gp.screenHeight / 2 - mapDisplayHeight / 2;
        graphics2D.drawImage(this.worldMap[this.gp.currentMap], mapX, mapY, mapDisplayWidth, mapDisplayHeight, null);
        double scale = (double)(this.gp.tileSize * this.gp.maxWorldCol) / (double)mapDisplayWidth;
        int playerMapX = (int)((double)mapX + (double)this.gp.player.worldX / scale);
        int playerMapY = (int)((double)mapY + (double)this.gp.player.worldY / scale);
        int playerIconSize = (int)((double)this.gp.tileSize / scale);
        graphics2D.drawImage(this.gp.player.down1, playerMapX, playerMapY, playerIconSize, playerIconSize, null);
        graphics2D.setFont(this.gp.ui.maruMonica.deriveFont(32.0f));
        graphics2D.setColor(Color.white);
        graphics2D.drawString("Press M to close", 750, 550);
    }

    public void drawMiniMap(Graphics2D graphics2D) {
        if (this.miniMapOn) {
            int miniMapWidth = 150;
            int miniMapHeight = 150;
            int miniMapX = this.gp.screenWidth - miniMapWidth - 50;
            int miniMapY = 50;
            graphics2D.drawImage(this.worldMap[this.gp.currentMap], miniMapX, miniMapY, miniMapWidth, miniMapHeight, null);
            double scale = (double)(this.gp.tileSize * this.gp.maxWorldCol) / (double)miniMapWidth;
            int playerMapX = (int)((double)miniMapX + (double)this.gp.player.worldX / scale);
            int playerMapY = (int)((double)miniMapY + (double)this.gp.player.worldY / scale);
            int playerIconSize = this.gp.tileSize / 3;
            graphics2D.drawImage(this.gp.player.down1, playerMapX - 6, playerMapY - 6, playerIconSize, playerIconSize, null);
        }
    }
}
