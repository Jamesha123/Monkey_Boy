/*
 * Decompiled with CFR 0.152.
 */
package tile_interactive;

import main.GamePanel;
import tile_interactive.InteractiveTile;

public class IT_Log
extends InteractiveTile {
    GamePanel gp;
    public static final String itName = "Log";

    public IT_Log(GamePanel gamePanel, int n, int n2) {
        super(gamePanel, n, n2);
        this.gp = gamePanel;
        this.worldX = gamePanel.tileSize * n;
        this.worldY = gamePanel.tileSize * n2 - 10;
        this.name = itName;
        this.down1 = this.setup("/Tiles_Interactive/log", gamePanel.tileSize * 2, gamePanel.tileSize);
        this.solidArea.x = 0;
        this.solidArea.y = 10;
        this.solidArea.width = gamePanel.tileSize * 2;
        this.solidArea.height = gamePanel.tileSize;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
    }
}
