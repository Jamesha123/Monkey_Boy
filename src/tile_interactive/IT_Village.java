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

    public IT_Village(GamePanel gamePanel, int n, int n2, int n3) {
        super(gamePanel, n, n2);
        this.gp = gamePanel;
        this.tileType = n3;
        this.worldX = gamePanel.tileSize * n;
        this.worldY = gamePanel.tileSize * n2;
        this.name = itName;
        Objects.requireNonNull(gamePanel);
        if (n3 == 1) {
            this.down1 = this.setup("/Tiles_Interactive/village1", gamePanel.tileSize * 7, gamePanel.tileSize * 8);
            this.worldY = gamePanel.tileSize * n2 - 27;
            this.solidArea.x = 30;
            this.solidArea.y = 10;
            this.solidArea.width = 282;
            this.solidArea.height = 340;
            this.solidAreaDefaultX = this.solidArea.x;
            this.solidAreaDefaultY = this.solidArea.y;
        } else {
            Objects.requireNonNull(gamePanel);
            if (n3 == 2) {
                this.down1 = this.setup("/Tiles_Interactive/village2", gamePanel.tileSize * 11, gamePanel.tileSize * 9);
                this.worldY = gamePanel.tileSize * n2 - 28;
                this.solidArea.x = 30;
                this.solidArea.y = 10;
                this.solidArea.width = 290;
                this.solidArea.height = 370;
                this.solidAreaDefaultX = this.solidArea.x;
                this.solidAreaDefaultY = this.solidArea.y;
            } else {
                Objects.requireNonNull(gamePanel);
                if (n3 == 3) {
                    this.down1 = this.setup("/Tiles_Interactive/village3", gamePanel.tileSize * 11, gamePanel.tileSize * 8);
                    this.worldY = gamePanel.tileSize * n2 - 30;
                    this.solidArea.x = 20;
                    this.solidArea.y = 0;
                    this.solidArea.width = 475;
                    this.solidArea.height = 350;
                    this.solidAreaDefaultX = this.solidArea.x;
                    this.solidAreaDefaultY = this.solidArea.y;
                } else {
                    Objects.requireNonNull(gamePanel);
                    if (n3 == 4) {
                        this.down1 = this.setup("/Tiles_Interactive/village1Back", gamePanel.tileSize * 7, gamePanel.tileSize * 8);
                        this.worldY = gamePanel.tileSize * n2 - 27;
                        this.solidArea.x = 30;
                        this.solidArea.y = 10;
                        this.solidArea.width = 282;
                        this.solidArea.height = 340;
                        this.solidAreaDefaultX = this.solidArea.x;
                        this.solidAreaDefaultY = this.solidArea.y;
                    } else {
                        Objects.requireNonNull(gamePanel);
                        if (n3 == 5) {
                            this.down1 = this.setup("/Tiles_Interactive/village3Back", gamePanel.tileSize * 11, gamePanel.tileSize * 8);
                            this.worldY = gamePanel.tileSize * n2 - 30;
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
        if (n3 == 6) {
            this.down1 = this.setup("/Tiles_Interactive/store", gamePanel.tileSize * 7, gamePanel.tileSize * 8);
            this.worldY = gamePanel.tileSize * n2 - 40;
            this.solidArea.x = 30;
            this.solidArea.y = 10;
            this.solidArea.width = 282;
            this.solidArea.height = 340;
            this.solidAreaDefaultX = this.solidArea.x;
            this.solidAreaDefaultY = this.solidArea.y;
        }
    }
}
