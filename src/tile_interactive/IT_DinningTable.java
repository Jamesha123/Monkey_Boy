/*
 * Decompiled with CFR 0.152.
 */
package tile_interactive;

import main.GamePanel;
import tile_interactive.InteractiveTile;

public class IT_DinningTable
extends InteractiveTile {
    GamePanel gp;
    public static final String itName = "Dinning Table";

    public IT_DinningTable(GamePanel gamePanel, int col, int row) {
        super(gamePanel, col, row);
        this.gp = gamePanel;
        this.worldX = gamePanel.tileSize * col;
        this.worldY = gamePanel.tileSize * row;
        this.name = itName;
        this.down1 = this.setup("/Tiles_Interactive/dinningTable", gamePanel.tileSize * 3, gamePanel.tileSize * 3);
        this.solidArea.x = 10;
        this.solidArea.y = 5;
        this.solidArea.width = 130;
        this.solidArea.height = 48;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
    }
}
