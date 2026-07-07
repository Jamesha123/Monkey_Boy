/*
 * Decompiled with CFR 0.152.
 */
package tile_interactive;

import main.GamePanel;
import tile_interactive.InteractiveTile;

public class IT_Drawer
extends InteractiveTile {
    GamePanel gp;
    public static final String itName = "Drawer";

    public IT_Drawer(GamePanel gamePanel, int col, int row) {
        super(gamePanel, col, row);
        this.gp = gamePanel;
        this.worldX = gamePanel.tileSize * col;
        this.worldY = gamePanel.tileSize * row;
        this.name = itName;
        this.down1 = this.setup("/Tiles_Interactive/drawer", gamePanel.tileSize, gamePanel.tileSize * 2);
        this.solidArea.x = 1;
        this.solidArea.y = 1;
        this.solidArea.width = 40;
        this.solidArea.height = 70;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
        this.collision = true;
    }
}
