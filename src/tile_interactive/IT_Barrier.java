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

    public IT_Barrier(GamePanel gamePanel, int col, int row) {
        super(gamePanel, col, row);
        this.gp = gamePanel;
        this.worldX = gamePanel.tileSize * col;
        this.worldY = gamePanel.tileSize * row;
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

    public IT_Barrier(GamePanel gamePanel, int col, int row, String requiredTask, int barrierId) {
        this(gamePanel, col, row);
        this.requiredTask = requiredTask;
        this.barrierID = barrierId;
    }

    public void checkTaskCompletion() {
        if (!this.isActive) {
            return;
        }
        boolean taskCompleted = false;
        switch (this.requiredTask) {
            case "talk_to_mom": {
                if (!this.gp.talkedToMom) break;
                taskCompleted = true;
                break;
            }
            case "skeleton_lord": {
                if (!this.gp.bossBattleOn && !Progress.skeletonLordDefeated) break;
                taskCompleted = true;
                break;
            }
        }
        if (taskCompleted) {
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

    public void setRequiredTask(String requiredTask) {
        this.requiredTask = requiredTask;
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
