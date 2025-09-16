/*
 * Decompiled with CFR 0.152.
 */
package tile_interactive;

import main.GamePanel;
import tile_interactive.InteractiveTile;

public class IT_Chair
extends InteractiveTile {
    GamePanel gp;
    public static final String itName = "Chair";

    public IT_Chair(GamePanel gamePanel, int n, int n2, String string) {
        super(gamePanel, n, n2);
        this.gp = gamePanel;
        this.worldX = gamePanel.tileSize * n + 28;
        this.worldY = gamePanel.tileSize * n2 - 10;
        this.name = itName;
        this.down1 = this.setup("/Tiles_Interactive/chair", gamePanel.tileSize, gamePanel.tileSize * 2);
        this.solidArea.x = 0;
        this.solidArea.y = 0;
        this.solidArea.width = 10;
        this.solidArea.height = 60;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
    }
}
