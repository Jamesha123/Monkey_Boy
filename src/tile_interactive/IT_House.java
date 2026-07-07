/*
 * Decompiled with CFR 0.152.
 */
package tile_interactive;

import main.GamePanel;
import tile_interactive.InteractiveTile;

public class IT_House
extends InteractiveTile {
    GamePanel gp;
    public static final String itName = "House";

    public IT_House(GamePanel gamePanel, int col, int row) {
        super(gamePanel, col, row);
        this.gp = gamePanel;
        this.worldX = gamePanel.tileSize * col;
        this.worldY = gamePanel.tileSize * row - 27;
        this.name = itName;
        this.down1 = this.setup("/Tiles_Interactive/house", gamePanel.tileSize * 9, gamePanel.tileSize * 8);
        this.solidArea.x = 20;
        this.solidArea.y = 0;
        this.solidArea.width = 380;
        this.solidArea.height = 350;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
    }
}
