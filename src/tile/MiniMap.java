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
        int n = this.gp.tileSize * this.gp.maxWorldCol;
        int n2 = this.gp.tileSize * this.gp.maxWorldRow;
        for (int i = 0; i < this.gp.maxMap; ++i) {
            this.worldMap[i] = new BufferedImage(n, n2, 2);
            Graphics2D graphics2D = this.worldMap[i].createGraphics();
            int n3 = 0;
            int n4 = 0;
            while (n3 < this.gp.maxWorldCol && n4 < this.gp.maxWorldRow) {
                int n5 = this.mapTileNum[i][n3][n4];
                int n6 = this.gp.tileSize * n3;
                int n7 = this.gp.tileSize * n4;
                if (n5 >= 0 && n5 < this.tile.length && this.tile[n5] != null) {
                    graphics2D.drawImage((Image)this.tile[n5].image, n6, n7, null);
                }
                if (++n3 != this.gp.maxWorldCol) continue;
                n3 = 0;
                ++n4;
            }
            graphics2D.dispose();
        }
    }

    public void drawFullMapScreen(Graphics2D graphics2D) {
        graphics2D.setColor(Color.black);
        graphics2D.fillRect(0, 0, this.gp.screenWidth, this.gp.screenHeight);
        int n = 500;
        int n2 = 500;
        int n3 = this.gp.screenWidth / 2 - n / 2;
        int n4 = this.gp.screenHeight / 2 - n2 / 2;
        graphics2D.drawImage(this.worldMap[this.gp.currentMap], n3, n4, n, n2, null);
        double d = (double)(this.gp.tileSize * this.gp.maxWorldCol) / (double)n;
        int n5 = (int)((double)n3 + (double)this.gp.player.worldX / d);
        int n6 = (int)((double)n4 + (double)this.gp.player.worldY / d);
        int n7 = (int)((double)this.gp.tileSize / d);
        graphics2D.drawImage(this.gp.player.down1, n5, n6, n7, n7, null);
        graphics2D.setFont(this.gp.ui.maruMonica.deriveFont(32.0f));
        graphics2D.setColor(Color.white);
        graphics2D.drawString("Press M to close", 750, 550);
    }

    public void drawMiniMap(Graphics2D graphics2D) {
        if (this.miniMapOn) {
            int n = 150;
            int n2 = 150;
            int n3 = this.gp.screenWidth - n - 50;
            int n4 = 50;
            graphics2D.drawImage(this.worldMap[this.gp.currentMap], n3, n4, n, n2, null);
            double d = (double)(this.gp.tileSize * this.gp.maxWorldCol) / (double)n;
            int n5 = (int)((double)n3 + (double)this.gp.player.worldX / d);
            int n6 = (int)((double)n4 + (double)this.gp.player.worldY / d);
            int n7 = this.gp.tileSize / 3;
            graphics2D.drawImage(this.gp.player.down1, n5 - 6, n6 - 6, n7, n7, null);
        }
    }
}
