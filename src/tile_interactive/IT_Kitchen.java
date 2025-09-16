/*
 * Decompiled with CFR 0.152.
 */
package tile_interactive;

import main.GamePanel;
import tile_interactive.InteractiveTile;

public class IT_Kitchen
extends InteractiveTile {
    GamePanel gp;
    public static final String itName = "Kitchen";

    public IT_Kitchen(GamePanel gamePanel, int n, int n2) {
        super(gamePanel, n, n2);
        this.gp = gamePanel;
        this.worldX = gamePanel.tileSize * n;
        this.worldY = gamePanel.tileSize * n2 - 27;
        this.name = itName;
        this.down1 = this.setup("/Tiles_Interactive/kitchen", gamePanel.tileSize * 8, gamePanel.tileSize * 3);
        this.solidArea.x = 0;
        this.solidArea.y = 0;
        this.solidArea.width = 333;
        this.solidArea.height = 105;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
    }
}
