/*
 * Decompiled with CFR 0.152.
 */
package tile_interactive;

import main.GamePanel;
import tile_interactive.InteractiveTile;

public class IT_Couch
extends InteractiveTile {
    GamePanel gp;
    public static final String itName = "Couch";

    public IT_Couch(GamePanel gamePanel, int col, int row) {
        super(gamePanel, col, row);
        this.gp = gamePanel;
        this.worldX = gamePanel.tileSize * col;
        this.worldY = gamePanel.tileSize * row;
        this.name = itName;
        this.down1 = this.setup("/Tiles_Interactive/couch", gamePanel.tileSize * 2, gamePanel.tileSize * 3);
        this.solidArea.x = 30;
        this.solidArea.y = 25;
        this.solidArea.width = 50;
        this.solidArea.height = 95;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
    }
}
