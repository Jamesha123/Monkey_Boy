/*
 * Decompiled with CFR 0.152.
 */
package tile_interactive;

import data.Progress;
import main.GamePanel;
import tile_interactive.InteractiveTile;

public class IT_Barrier
extends InteractiveTile {
    GamePanel gp;
    public static final String itName = "Barrier";
    private boolean isActive = true;
    private String requiredTask = "";
    private int barrierID = 0;

    public IT_Barrier(GamePanel gamePanel, int n, int n2) {
        super(gamePanel, n, n2);
        this.gp = gamePanel;
        this.worldX = gamePanel.tileSize * n;
        this.worldY = gamePanel.tileSize * n2;
        this.name = itName;
        this.down1 = this.setup("/Tiles_Interactive/barrier", gamePanel.tileSize, gamePanel.tileSize);
        this.solidArea.x = 0;
        this.solidArea.y = 0;
        this.solidArea.width = gamePanel.tileSize;
        this.solidArea.height = gamePanel.tileSize;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
        this.collision = true;
    }

    public IT_Barrier(GamePanel gamePanel, int n, int n2, String string, int n3) {
        this(gamePanel, n, n2);
        this.requiredTask = string;
        this.barrierID = n3;
    }

    public void checkTaskCompletion() {
        if (!this.isActive) {
            return;
        }
        boolean bl = false;
        switch (this.requiredTask) {
            case "talk_to_mom": {
                if (!this.gp.talkedToMom) break;
                bl = true;
                break;
            }
            case "skeleton_lord": {
                if (!this.gp.bossBattleOn && !Progress.skeletonLordDefeated) break;
                bl = true;
                break;
            }
        }
        if (bl) {
            this.removeBarrier();
        }
    }

    public void removeBarrier() {
        if (this.isActive) {
            this.isActive = false;
            this.collision = false;
            this.removeFromMap();
        }
    }

    private void removeFromMap() {
        for (int i = 0; i < this.gp.iTile[1].length; ++i) {
            if (this.gp.iTile[this.gp.currentMap][i] != this) continue;
            this.gp.iTile[this.gp.currentMap][i] = null;
            break;
        }
    }

    public void forceRemove() {
        this.removeBarrier();
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setRequiredTask(String string) {
        this.requiredTask = string;
    }

    public int getBarrierID() {
        return this.barrierID;
    }

    @Override
    public void update() {
        super.update();
        if (this.isActive) {
            this.checkTaskCompletion();
        }
    }
}
