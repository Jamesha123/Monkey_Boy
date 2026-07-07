/*
 * Decompiled with CFR 0.152.
 */
package tile_interactive;

import java.util.Objects;
import main.GamePanel;
import tile_interactive.InteractiveTile;

public class IT_Village
extends InteractiveTile {
    GamePanel gp;
    public static final String itName = "Village";

    public IT_Village(GamePanel gamePanel, int col, int row, int villageType) {
        super(gamePanel, col, row);
        this.gp = gamePanel;
        this.tileType = villageType;
        this.worldX = gamePanel.tileSize * col;
        this.worldY = gamePanel.tileSize * row;
        this.name = itName;
        Objects.requireNonNull(gamePanel);
        if (villageType == 1) {
            this.down1 = this.setup("/Tiles_Interactive/village1", gamePanel.tileSize * 7, gamePanel.tileSize * 8);
            this.worldY = gamePanel.tileSize * row - 27;
            this.solidArea.x = 30;
            this.solidArea.y = 10;
            this.solidArea.width = 282;
            this.solidArea.height = 340;
            this.solidAreaDefaultX = this.solidArea.x;
            this.solidAreaDefaultY = this.solidArea.y;
        } else {
            Objects.requireNonNull(gamePanel);
            if (villageType == 2) {
                this.down1 = this.setup("/Tiles_Interactive/village2", gamePanel.tileSize * 11, gamePanel.tileSize * 9);
                this.worldY = gamePanel.tileSize * row - 28;
                this.solidArea.x = 30;
                this.solidArea.y = 10;
                this.solidArea.width = 290;
                this.solidArea.height = 370;
                this.solidAreaDefaultX = this.solidArea.x;
                this.solidAreaDefaultY = this.solidArea.y;
            } else {
                Objects.requireNonNull(gamePanel);
                if (villageType == 3) {
                    this.down1 = this.setup("/Tiles_Interactive/village3", gamePanel.tileSize * 11, gamePanel.tileSize * 8);
                    this.worldY = gamePanel.tileSize * row - 30;
                    this.solidArea.x = 20;
                    this.solidArea.y = 0;
                    this.solidArea.width = 475;
                    this.solidArea.height = 350;
                    this.solidAreaDefaultX = this.solidArea.x;
                    this.solidAreaDefaultY = this.solidArea.y;
                } else {
                    Objects.requireNonNull(gamePanel);
                    if (villageType == 4) {
                        this.down1 = this.setup("/Tiles_Interactive/village1Back", gamePanel.tileSize * 7, gamePanel.tileSize * 8);
                        this.worldY = gamePanel.tileSize * row - 27;
                        this.solidArea.x = 30;
                        this.solidArea.y = 10;
                        this.solidArea.width = 282;
                        this.solidArea.height = 340;
                        this.solidAreaDefaultX = this.solidArea.x;
                        this.solidAreaDefaultY = this.solidArea.y;
                    } else {
                        Objects.requireNonNull(gamePanel);
                        if (villageType == 5) {
                            this.down1 = this.setup("/Tiles_Interactive/village3Back", gamePanel.tileSize * 11, gamePanel.tileSize * 8);
                            this.worldY = gamePanel.tileSize * row - 30;
                            this.solidArea.x = 20;
                            this.solidArea.y = 0;
                            this.solidArea.width = 475;
                            this.solidArea.height = 350;
                            this.solidAreaDefaultX = this.solidArea.x;
                            this.solidAreaDefaultY = this.solidArea.y;
                        }
                    }
                }
            }
        }
        Objects.requireNonNull(gamePanel);
        if (villageType == 6) {
            this.down1 = this.setup("/Tiles_Interactive/store", gamePanel.tileSize * 7, gamePanel.tileSize * 8);
            this.worldY = gamePanel.tileSize * row - 40;
            this.solidArea.x = 30;
            this.solidArea.y = 10;
            this.solidArea.width = 282;
            this.solidArea.height = 340;
            this.solidAreaDefaultX = this.solidArea.x;
            this.solidAreaDefaultY = this.solidArea.y;
        }
    }
}
