/*
 * Decompiled with CFR 0.152.
 */
package tile_interactive;

import java.util.Objects;
import main.GamePanel;
import tile_interactive.InteractiveTile;

public class IT_Bed
extends InteractiveTile {
    GamePanel gp;
    public static final String itName = "Bed";

    public IT_Bed(GamePanel gamePanel, int n, int n2) {
        super(gamePanel, n, n2);
        this.gp = gamePanel;
        this.worldX = gamePanel.tileSize * n;
        this.worldY = gamePanel.tileSize * n2;
        this.name = itName;
        this.down1 = this.setup("/Tiles_Interactive/bed", gamePanel.tileSize * 2, gamePanel.tileSize * 3);
        this.solidArea.x = 8;
        this.solidArea.y = 0;
        this.solidArea.width = 70;
        this.solidArea.height = 120;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
        this.collision = false;
        this.setDialogue();
    }

    public void setDialogue() {
        this.dialogues[0][0] = "It's morning, time to get going!";
        this.dialogues[1][0] = "PLAYER: What a day. I need to sleep.";
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void interact() {
        if (this.gp.questCompleted) {
            int n = this.gp.currentMap;
            Objects.requireNonNull(this.gp);
            if (n == 0) {
                this.gp.csManager.sceneNum = this.gp.csManager.endScene;
                Objects.requireNonNull(this.gp);
                this.gp.gameState = 10;
                return;
            }
        }
        this.startDialogue(this, 0);
    }
}
