/*
 * Decompiled with CFR 0.152.
 */
package tile_interactive;

import main.GamePanel;
import tile_interactive.InteractiveTile;

public class IT_Trunk
extends InteractiveTile {
    GamePanel gp;
    public static final String itName = "Trunk";

    public IT_Trunk(GamePanel gamePanel, int n, int n2) {
        super(gamePanel, n, n2);
        this.gp = gamePanel;
        this.worldX = gamePanel.tileSize * n;
        this.worldY = gamePanel.tileSize * n2;
        this.name = itName;
        this.down1 = this.setup("/Tiles_Interactive/trunk", gamePanel.tileSize, gamePanel.tileSize);
        this.solidArea.x = 0;
        this.solidArea.y = 0;
        this.solidArea.width = 0;
        this.solidArea.height = 0;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
    }
}
