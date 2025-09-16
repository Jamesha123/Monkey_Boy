/*
 * Decompiled with CFR 0.152.
 */
package main;

import data.Progress;
import entity.Entity;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.util.Objects;
import main.GamePanel;
import main.KeyHandler;
import tile.TileManager;
import tile_interactive.InteractiveTile;

public class DebugManager {
    private GamePanel gp;
    private KeyHandler keyH;
    public boolean debugMode = false;
    public boolean godMode = false;
    public boolean freezeEnemies = false;
    public boolean collisionOff = false;
    public boolean characterEditor = false;
    public boolean debugStore = false;
    public boolean showCollision = false;
    public boolean darknessFilterOff = false;
    public boolean debugSuspended = false;
    boolean inRestrictedState;
    private int selectedStat = 0;
    private String[] editableStats = new String[]{"Level", "Life", "Mana", "Speed", "Strength", "Dexterity", "Exp", "Next Level Exp", "Coin", "Day", "Map"};
    private int[] statValues = new int[11];
    private boolean editingFlags = false;
    private int selectedFlag = 0;
    private String[] flagNames = new String[]{"Talked To Mom", "Orc Robbed Player", "Talked To Old Man", "Received Mom Coins", "Quest Completed", "Slime Boss Defeated", "Skeleton Lord Defeated"};
    private boolean[] flagValues = new boolean[7];
    private String[] mapNames = new String[]{"Bedroom", "Downstairs", "World Map", "World Map 2", "World Map 3", "Village", "Store", "Wizard Home", "World Map 4", "Slime Boss Room", "World Map 5", "Dungeon 01", "Dungeon 02", "Skeleton Lord Room"};
    private int selectedStoreItem = 0;
    private int selectedInventoryItem = 0;
    private boolean storePanelSelected = true;
    private String[] storeItems = new String[]{"Red Potion", "Key", "Sugar", "Normal Sword", "Epic Sword", "Wood Shield", "Blue Shield", "Woodcutter's Axe", "Pickaxe", "Lantern", "Tent", "Torch", "Book of Fireball Magic", "Boots"};

    public DebugManager(GamePanel gamePanel) {
        this.gp = gamePanel;
        this.keyH = gamePanel.keyH;
    }

    /*
     * Unable to fully structure code
     */
    public void update() {
        v0 = this.gp.gameState;
        Objects.requireNonNull(this.gp);
        if (v0 == 2 || v1 == 10 || v2 == 11) {
        // 3 sources

        {
            v3 = true;
        } else {
            v3 = this.inRestrictedState = false;
        }
        if (this.inRestrictedState) {
            if (this.debugMode && !this.debugSuspended) {
                this.debugSuspended = true;
                this.debugMode = false;
                this.godMode = false;
                this.freezeEnemies = false;
                this.gp.entitiesFrozen = false;
                this.collisionOff = false;
                this.characterEditor = false;
                this.debugStore = false;
                this.showCollision = false;
                this.darknessFilterOff = false;
                this.gp.tileM.drawPath = false;
            }
        } else if (this.debugSuspended) {
            this.debugMode = true;
            this.debugSuspended = false;
        }
        if (this.keyH.keyPressed[112]) {
            if (!this.inRestrictedState) {
                if (!this.debugMode) {
                    this.debugMode = true;
                } else {
                    this.debugMode = false;
                    this.godMode = false;
                    this.freezeEnemies = false;
                    this.collisionOff = false;
                    this.characterEditor = false;
                    this.debugStore = false;
                    this.showCollision = false;
                    this.darknessFilterOff = false;
                    this.gp.tileM.drawPath = false;
                    Objects.requireNonNull(this.gp);
                    this.gp.gameState = 1;
                }
            }
            this.keyH.keyPressed[112] = false;
        }
        if (this.debugMode) {
            if (this.keyH.keyPressed[71]) {
                this.godMode = this.godMode == false;
                this.keyH.keyPressed[71] = false;
            }
            if (this.keyH.keyPressed[84]) {
                this.gp.entitiesFrozen = this.freezeEnemies = this.freezeEnemies == false;
                this.keyH.keyPressed[84] = false;
            }
            if (this.keyH.keyPressed[85]) {
                this.collisionOff = this.collisionOff == false;
                this.keyH.keyPressed[85] = false;
            }
            if (this.keyH.keyPressed[82]) {
                this.reloadMap();
                this.keyH.keyPressed[82] = false;
            }
            if (this.keyH.keyPressed[73]) {
                this.gp.tileM.drawPath = this.showCollision = this.showCollision == false;
                this.keyH.keyPressed[73] = false;
            }
            if (this.keyH.keyPressed[79]) {
                this.darknessFilterOff = this.darknessFilterOff == false;
                this.keyH.keyPressed[79] = false;
            }
            if (this.keyH.keyPressed[80]) {
                if (!this.characterEditor) {
                    if (this.debugStore) {
                        this.debugStore = false;
                    }
                    this.characterEditor = true;
                    this.updateStatValues();
                    this.updateFlagValues();
                    Objects.requireNonNull(this.gp);
                    this.gp.gameState = 13;
                } else {
                    this.characterEditor = false;
                    Objects.requireNonNull(this.gp);
                    this.gp.gameState = 1;
                }
                this.keyH.keyPressed[80] = false;
            }
            if (this.characterEditor && this.keyH.keyPressed[27]) {
                this.characterEditor = false;
                Objects.requireNonNull(this.gp);
                this.gp.gameState = 1;
                this.keyH.keyPressed[27] = false;
            }
            if (this.keyH.keyPressed[89]) {
                if (!this.debugStore) {
                    if (this.characterEditor) {
                        this.characterEditor = false;
                    }
                    this.debugStore = true;
                    Objects.requireNonNull(this.gp);
                    this.gp.gameState = 13;
                } else {
                    this.debugStore = false;
                    Objects.requireNonNull(this.gp);
                    this.gp.gameState = 1;
                }
                this.keyH.keyPressed[89] = false;
            }
            if (this.debugStore && this.keyH.keyPressed[27]) {
                this.debugStore = false;
                Objects.requireNonNull(this.gp);
                this.gp.gameState = 1;
                this.keyH.keyPressed[27] = false;
            }
            if (this.characterEditor) {
                this.handleGameMasterPanelInput();
            }
            if (this.debugStore) {
                this.handleDebugStoreInput();
            }
        }
        if (this.godMode && this.gp.player != null) {
            this.gp.player.life = this.gp.player.maxLife;
            this.gp.player.mana = this.gp.player.maxMana;
        }
    }

    private void updateStatValues() {
        if (this.gp.player != null) {
            this.statValues[0] = this.gp.player.level;
            this.statValues[1] = this.gp.player.life;
            this.statValues[2] = this.gp.player.mana;
            this.statValues[3] = this.gp.player.speed;
            this.statValues[4] = this.gp.player.strength;
            this.statValues[5] = this.gp.player.dexterity;
            this.statValues[6] = this.gp.player.exp;
            this.statValues[7] = this.gp.player.nextLevelExp;
            this.statValues[8] = this.gp.player.coin;
            this.statValues[9] = this.gp.eManager.lighting.dayState;
            this.statValues[10] = this.gp.currentMap;
        }
    }

    private void updateFlagValues() {
        this.flagValues[0] = this.gp.talkedToMom;
        this.flagValues[1] = this.gp.orcRobbedPlayer;
        this.flagValues[2] = this.gp.talkedToOldMan;
        this.flagValues[3] = this.gp.hasReceivedMomCoins;
        this.flagValues[4] = this.gp.questCompleted;
        this.flagValues[5] = Progress.slimeBossDefeated;
        this.flagValues[6] = Progress.skeletonLordDefeated;
    }

    private void handleGameMasterPanelInput() {
        if (!this.editingFlags) {
            if (this.keyH.keyPressed[87] || this.keyH.keyPressed[38]) {
                if (this.selectedStat == 0) {
                    this.editingFlags = true;
                    this.selectedFlag = this.flagNames.length - 1;
                } else {
                    this.selectedStat = (this.selectedStat - 1 + this.editableStats.length) % this.editableStats.length;
                }
                this.keyH.keyPressed[87] = false;
                this.keyH.keyPressed[38] = false;
            }
            if (this.keyH.keyPressed[83] || this.keyH.keyPressed[40]) {
                if (this.selectedStat == this.editableStats.length - 1) {
                    this.editingFlags = true;
                    this.selectedFlag = 0;
                } else {
                    this.selectedStat = (this.selectedStat + 1) % this.editableStats.length;
                }
                this.keyH.keyPressed[83] = false;
                this.keyH.keyPressed[40] = false;
            }
            if (this.keyH.keyPressed[65] || this.keyH.keyPressed[37]) {
                if (this.keyH.shiftPressed) {
                    this.modifyStat(this.selectedStat, -10);
                } else {
                    this.modifyStat(this.selectedStat, -1);
                }
                this.keyH.keyPressed[65] = false;
                this.keyH.keyPressed[37] = false;
            }
            if (this.keyH.keyPressed[68] || this.keyH.keyPressed[39]) {
                if (this.keyH.shiftPressed) {
                    this.modifyStat(this.selectedStat, 10);
                } else {
                    this.modifyStat(this.selectedStat, 1);
                }
                this.keyH.keyPressed[68] = false;
                this.keyH.keyPressed[39] = false;
            }
        } else {
            if (this.keyH.keyPressed[87] || this.keyH.keyPressed[38]) {
                if (this.selectedFlag == 0) {
                    this.editingFlags = false;
                    this.selectedStat = this.editableStats.length - 1;
                } else {
                    this.selectedFlag = (this.selectedFlag - 1 + this.flagNames.length) % this.flagNames.length;
                }
                this.keyH.keyPressed[87] = false;
                this.keyH.keyPressed[38] = false;
            }
            if (this.keyH.keyPressed[83] || this.keyH.keyPressed[40]) {
                if (this.selectedFlag == this.flagNames.length - 1) {
                    this.editingFlags = false;
                    this.selectedStat = 0;
                } else {
                    this.selectedFlag = (this.selectedFlag + 1) % this.flagNames.length;
                }
                this.keyH.keyPressed[83] = false;
                this.keyH.keyPressed[40] = false;
            }
            if (this.keyH.keyPressed[65] || this.keyH.keyPressed[37] || this.keyH.keyPressed[68] || this.keyH.keyPressed[39] || this.keyH.keyPressed[10]) {
                this.flagValues[this.selectedFlag] = !this.flagValues[this.selectedFlag];
                this.applyFlagChanges();
                this.keyH.keyPressed[65] = false;
                this.keyH.keyPressed[37] = false;
                this.keyH.keyPressed[68] = false;
                this.keyH.keyPressed[39] = false;
                this.keyH.keyPressed[10] = false;
            }
        }
    }

    private void handleDebugStoreInput() {
        if (this.keyH.keyPressed[65]) {
            this.storePanelSelected = false;
            this.keyH.keyPressed[65] = false;
        }
        if (this.keyH.keyPressed[68]) {
            this.storePanelSelected = true;
            this.keyH.keyPressed[68] = false;
        }
        if (this.storePanelSelected) {
            if (this.keyH.keyPressed[87] || this.keyH.keyPressed[38]) {
                this.selectedStoreItem = (this.selectedStoreItem - 1 + this.storeItems.length) % this.storeItems.length;
                this.keyH.keyPressed[87] = false;
                this.keyH.keyPressed[38] = false;
            }
            if (this.keyH.keyPressed[83] || this.keyH.keyPressed[40]) {
                this.selectedStoreItem = (this.selectedStoreItem + 1) % this.storeItems.length;
                this.keyH.keyPressed[83] = false;
                this.keyH.keyPressed[40] = false;
            }
            if (this.keyH.keyPressed[10]) {
                this.addItemToInventory(this.storeItems[this.selectedStoreItem]);
                this.keyH.keyPressed[10] = false;
            }
        } else {
            int n = Math.max(1, this.gp.player.inventory.size());
            if (this.keyH.keyPressed[87] || this.keyH.keyPressed[38]) {
                this.selectedInventoryItem = (this.selectedInventoryItem - 1 + n) % n;
                this.keyH.keyPressed[87] = false;
                this.keyH.keyPressed[38] = false;
            }
            if (this.keyH.keyPressed[83] || this.keyH.keyPressed[40]) {
                this.selectedInventoryItem = (this.selectedInventoryItem + 1) % n;
                this.keyH.keyPressed[83] = false;
                this.keyH.keyPressed[40] = false;
            }
            if (this.keyH.keyPressed[10]) {
                this.removeItemFromInventory();
                this.keyH.keyPressed[10] = false;
            }
        }
    }

    private void addItemToInventory(String string) {
        if (this.gp.player == null) {
            return;
        }
        Entity entity = this.gp.eGenerator.getObject(string);
        if (entity == null) {
            return;
        }
        if (this.gp.player.canObtainItem(entity)) {
            this.gp.playSE(1);
        } else {
            this.gp.playSE(2);
            this.gp.ui.addMessage("Inventory Full");
        }
    }

    private void removeItemFromInventory() {
        if (this.gp.player == null || this.gp.player.inventory.isEmpty()) {
            return;
        }
        if (this.selectedInventoryItem >= this.gp.player.inventory.size()) {
            this.selectedInventoryItem = 0;
        }
        Entity entity = (Entity)this.gp.player.inventory.get(this.selectedInventoryItem);
        if (this.keyH.shiftPressed && entity.stackable && entity.amount > 1) {
            this.gp.player.inventory.remove(this.selectedInventoryItem);
            this.gp.playSE(2);
        } else if (entity.stackable && entity.amount > 1) {
            --entity.amount;
            if (entity.amount <= 0) {
                this.gp.player.inventory.remove(this.selectedInventoryItem);
            }
            this.gp.playSE(2);
        } else {
            this.gp.player.inventory.remove(this.selectedInventoryItem);
            this.gp.playSE(2);
        }
        if (this.selectedInventoryItem >= this.gp.player.inventory.size() && !this.gp.player.inventory.isEmpty()) {
            this.selectedInventoryItem = this.gp.player.inventory.size() - 1;
        }
    }

    private void modifyStat(int n, int n2) {
        if (this.gp.player == null) {
            return;
        }
        int n3 = n;
        this.statValues[n3] = this.statValues[n3] + n2;
        switch (n) {
            case 0: {
                this.statValues[n] = Math.max(1, Math.min(99, this.statValues[n]));
                break;
            }
            case 1: {
                this.statValues[n] = Math.max(1, Math.min(999, this.statValues[n]));
                if (this.statValues[n] <= this.gp.player.maxLife) break;
                this.gp.player.maxLife = this.statValues[n];
                break;
            }
            case 2: {
                this.statValues[n] = Math.max(1, Math.min(999, this.statValues[n]));
                if (this.statValues[n] <= this.gp.player.maxMana) break;
                this.gp.player.maxMana = this.statValues[n];
                break;
            }
            case 3: {
                this.statValues[n] = Math.max(1, Math.min(20, this.statValues[n]));
                break;
            }
            case 4: {
                this.statValues[n] = Math.max(1, Math.min(99, this.statValues[n]));
                break;
            }
            case 5: {
                this.statValues[n] = Math.max(1, Math.min(99, this.statValues[n]));
                break;
            }
            case 6: {
                this.statValues[n] = Math.max(0, this.statValues[n]);
                break;
            }
            case 7: {
                this.statValues[n] = Math.max(1, this.statValues[n]);
                break;
            }
            case 8: {
                this.statValues[n] = Math.max(0, this.statValues[n]);
                break;
            }
            case 9: {
                this.statValues[n] = Math.max(0, Math.min(3, this.statValues[n]));
                break;
            }
            case 10: {
                this.statValues[n] = Math.max(0, Math.min(13, this.statValues[n]));
            }
        }
        this.applyStatChanges();
    }

    private void applyStatChanges() {
        if (this.gp.player == null) {
            return;
        }
        this.gp.player.level = this.statValues[0];
        this.gp.player.life = this.statValues[1];
        this.gp.player.maxLife = this.statValues[1];
        this.gp.player.mana = this.statValues[2];
        this.gp.player.maxMana = this.statValues[2];
        this.gp.player.speed = this.statValues[3];
        this.gp.player.defaultSpeed = this.statValues[3];
        this.gp.player.strength = this.statValues[4];
        this.gp.player.dexterity = this.statValues[5];
        this.gp.player.exp = this.statValues[6];
        this.gp.player.nextLevelExp = this.statValues[7];
        this.gp.player.coin = this.statValues[8];
        this.gp.eManager.lighting.dayState = this.statValues[9];
        this.gp.eManager.lighting.dayCounter = 0;
        if (this.statValues[10] != this.gp.currentMap) {
            this.changeMap(this.statValues[10]);
        }
        this.gp.player.getAttack();
        this.gp.player.getDefense();
        this.gp.player.getAttackImage();
    }

    private void applyFlagChanges() {
        this.gp.talkedToMom = this.flagValues[0];
        this.gp.orcRobbedPlayer = this.flagValues[1];
        this.gp.talkedToOldMan = this.flagValues[2];
        this.gp.hasReceivedMomCoins = this.flagValues[3];
        this.gp.questCompleted = this.flagValues[4];
        Progress.slimeBossDefeated = this.flagValues[5];
        Progress.skeletonLordDefeated = this.flagValues[6];
    }

    public void draw(Graphics2D graphics2D) {
        if (!this.debugMode) {
            return;
        }
        graphics2D.setFont(this.gp.ui.fontBold22);
        this.drawDebugStatus(graphics2D);
        if (this.characterEditor) {
            this.drawGameMasterPanel(graphics2D);
        }
        if (this.debugStore) {
            this.drawDebugStore(graphics2D);
        }
        if (this.showCollision) {
            this.drawCollisionOverlays(graphics2D);
        }
    }

    private void drawDebugStatus(Graphics2D graphics2D) {
        int n = 10;
        int n2 = 290;
        int n3 = 20;
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawString("=== DEBUG MODE ===", n, n2);
        graphics2D.setColor(this.godMode ? Color.GREEN : Color.RED);
        graphics2D.drawString("God Mode: " + (this.godMode ? "ON" : "OFF") + " (G)", n, n2 += n3);
        graphics2D.setColor(this.freezeEnemies ? Color.GREEN : Color.RED);
        graphics2D.drawString("Freeze Enemies: " + (this.freezeEnemies ? "ON" : "OFF") + " (T)", n, n2 += n3);
        graphics2D.setColor(this.collisionOff ? Color.GREEN : Color.RED);
        graphics2D.drawString("Collision: " + (this.collisionOff ? "ON" : "OFF") + " (U)", n, n2 += n3);
        graphics2D.setColor(Color.CYAN);
        graphics2D.drawString("Reload Map (R)", n, n2 += n3);
        graphics2D.setColor(this.showCollision ? Color.GREEN : Color.RED);
        graphics2D.drawString("Show Collision: " + (this.showCollision ? "ON" : "OFF") + " (I)", n, n2 += n3);
        graphics2D.setColor(this.darknessFilterOff ? Color.GREEN : Color.RED);
        graphics2D.drawString("Darkness Filter: " + (this.darknessFilterOff ? "OFF" : "ON") + " (O)", n, n2 += n3);
        graphics2D.setColor(this.characterEditor ? Color.GREEN : Color.RED);
        graphics2D.drawString("Game Master Panel: " + (this.characterEditor ? "ON" : "OFF") + " (P)", n, n2 += n3);
        graphics2D.setColor(this.debugStore ? Color.GREEN : Color.RED);
        graphics2D.drawString("Debug Store: " + (this.debugStore ? "ON" : "OFF") + " (Y)", n, n2 += n3);
        graphics2D.setColor(Color.YELLOW);
        graphics2D.drawString("Press F1 to toggle debug mode", n, n2 += n3);
        graphics2D.setFont(this.gp.ui.fontBold20);
        graphics2D.setColor(Color.white);
        graphics2D.drawString("WorldX " + this.gp.player.worldX, n, n2 += n3);
        graphics2D.drawString("WorldY " + this.gp.player.worldY, n, n2 += n3);
        graphics2D.drawString("Col " + (this.gp.player.worldX + this.gp.player.solidArea.x) / this.gp.tileSize, n, n2 += n3);
        graphics2D.drawString("Row " + (this.gp.player.worldY + this.gp.player.solidArea.y) / this.gp.tileSize, n, n2 += n3);
        graphics2D.drawString("Draw Time: " + this.gp.drawTime, n, n2 += n3);
        n2 += n3;
    }

    private void drawCollisionOverlays(Graphics2D graphics2D) {
        int n;
        int n2;
        int n3;
        int n4;
        Stroke stroke = graphics2D.getStroke();
        graphics2D.setStroke(new BasicStroke(2.0f));
        graphics2D.setColor(new Color(255, 0, 0, 180));
        if (this.gp.player != null) {
            n4 = this.gp.player.getScreenX() + this.gp.player.solidArea.x;
            n3 = this.gp.player.getScreenY() + this.gp.player.solidArea.y;
            graphics2D.drawRect(n4, n3, this.gp.player.solidArea.width, this.gp.player.solidArea.height);
        }
        for (n4 = 0; n4 < this.gp.npc[1].length; ++n4) {
            Entity entity = this.gp.npc[this.gp.currentMap][n4];
            if (entity == null || !entity.inCamera()) continue;
            n2 = entity.getScreenX() + entity.solidArea.x;
            n = entity.getScreenY() + entity.solidArea.y;
            graphics2D.drawRect(n2, n, entity.solidArea.width, entity.solidArea.height);
        }
        for (n4 = 0; n4 < this.gp.monster[1].length; ++n4) {
            Entity entity = this.gp.monster[this.gp.currentMap][n4];
            if (entity == null || !entity.inCamera()) continue;
            n2 = entity.getScreenX() + entity.solidArea.x;
            n = entity.getScreenY() + entity.solidArea.y;
            graphics2D.drawRect(n2, n, entity.solidArea.width, entity.solidArea.height);
        }
        for (n4 = 0; n4 < this.gp.obj[1].length; ++n4) {
            Entity entity = this.gp.obj[this.gp.currentMap][n4];
            if (entity == null || !entity.inCamera()) continue;
            n2 = entity.getScreenX() + entity.solidArea.x;
            n = entity.getScreenY() + entity.solidArea.y;
            graphics2D.drawRect(n2, n, entity.solidArea.width, entity.solidArea.height);
        }
        for (n4 = 0; n4 < this.gp.iTile[1].length; ++n4) {
            InteractiveTile interactiveTile = this.gp.iTile[this.gp.currentMap][n4];
            if (interactiveTile == null || !interactiveTile.inCamera()) continue;
            n2 = interactiveTile.getScreenX() + interactiveTile.solidArea.x;
            n = interactiveTile.getScreenY() + interactiveTile.solidArea.y;
            graphics2D.drawRect(n2, n, interactiveTile.solidArea.width, interactiveTile.solidArea.height);
        }
        if (this.gp.player != null && this.gp.player.attackArea != null) {
            graphics2D.setColor(new Color(255, 165, 0, 180));
            n4 = 0;
            n3 = 0;
            switch (this.gp.player.direction) {
                case "up": {
                    n4 = this.gp.player.getScreenX() + this.gp.player.solidArea.x;
                    n3 = this.gp.player.getScreenY() + this.gp.player.solidArea.y - this.gp.player.attackArea.height;
                    break;
                }
                case "down": {
                    n4 = this.gp.player.getScreenX() + this.gp.player.solidArea.x;
                    n3 = this.gp.player.getScreenY() + this.gp.player.solidArea.y + this.gp.player.solidArea.height;
                    break;
                }
                case "left": {
                    n4 = this.gp.player.getScreenX() + this.gp.player.solidArea.x - this.gp.player.attackArea.width;
                    n3 = this.gp.player.getScreenY() + this.gp.player.solidArea.y;
                    break;
                }
                case "right": {
                    n4 = this.gp.player.getScreenX() + this.gp.player.solidArea.x + this.gp.player.solidArea.width;
                    n3 = this.gp.player.getScreenY() + this.gp.player.solidArea.y;
                }
            }
            graphics2D.drawRect(n4, n3, this.gp.player.attackArea.width, this.gp.player.attackArea.height);
        }
        graphics2D.setColor(new Color(255, 165, 0, 180));
        for (n4 = 0; n4 < this.gp.npc[1].length; ++n4) {
            Entity entity = this.gp.npc[this.gp.currentMap][n4];
            if (entity == null || !entity.inCamera() || entity.attackArea == null || entity.attackArea.width <= 0 || entity.attackArea.height <= 0) continue;
            int n5 = 0;
            n = 0;
            switch (entity.direction) {
                case "up": {
                    n5 = entity.getScreenX() + entity.solidArea.x;
                    n = entity.getScreenY() + entity.solidArea.y - entity.attackArea.height;
                    break;
                }
                case "down": {
                    n5 = entity.getScreenX() + entity.solidArea.x;
                    n = entity.getScreenY() + entity.solidArea.y + entity.solidArea.height;
                    break;
                }
                case "left": {
                    n5 = entity.getScreenX() + entity.solidArea.x - entity.attackArea.width;
                    n = entity.getScreenY() + entity.solidArea.y;
                    break;
                }
                case "right": {
                    n5 = entity.getScreenX() + entity.solidArea.x + entity.solidArea.width;
                    n = entity.getScreenY() + entity.solidArea.y;
                }
            }
            graphics2D.drawRect(n5, n, entity.attackArea.width, entity.attackArea.height);
        }
        for (n4 = 0; n4 < this.gp.monster[1].length; ++n4) {
            Entity entity = this.gp.monster[this.gp.currentMap][n4];
            if (entity == null || !entity.inCamera() || entity.attackArea == null || entity.attackArea.width <= 0 || entity.attackArea.height <= 0) continue;
            int n6 = 0;
            n = 0;
            switch (entity.direction) {
                case "up": {
                    n6 = entity.getScreenX() + entity.solidArea.x;
                    n = entity.getScreenY() + entity.solidArea.y - entity.attackArea.height;
                    break;
                }
                case "down": {
                    n6 = entity.getScreenX() + entity.solidArea.x;
                    n = entity.getScreenY() + entity.solidArea.y + entity.solidArea.height;
                    break;
                }
                case "left": {
                    n6 = entity.getScreenX() + entity.solidArea.x - entity.attackArea.width;
                    n = entity.getScreenY() + entity.solidArea.y;
                    break;
                }
                case "right": {
                    n6 = entity.getScreenX() + entity.solidArea.x + entity.solidArea.width;
                    n = entity.getScreenY() + entity.solidArea.y;
                }
            }
            graphics2D.drawRect(n6, n, entity.attackArea.width, entity.attackArea.height);
        }
        graphics2D.setStroke(stroke);
    }

    private void changeMap(int n) {
        int n2 = this.gp.currentArea;
        this.gp.previousMap = this.gp.currentMap;
        this.gp.currentMap = n;
        this.gp.player.setDefaultPositions(n);
        if (n2 != this.gp.currentArea) {
            this.gp.stopMusic();
            this.gp.playMusicForCurrentArea();
        }
    }

    private void drawGameMasterPanel(Graphics2D graphics2D) {
        String string;
        int n;
        int n2 = this.gp.screenWidth - 300;
        int n3 = 30;
        int n4 = 20;
        int n5 = this.editableStats.length * n4 + 40 + 20 + this.flagNames.length * n4 + 60;
        graphics2D.setColor(new Color(0, 0, 0, 150));
        graphics2D.fillRect(n2 - 10, n3 - 20, 290, n5);
        graphics2D.setColor(Color.WHITE);
        graphics2D.setFont(this.gp.ui.fontBold24);
        graphics2D.drawString("GAME MASTER PANEL", n2, n3);
        n3 += n4 + 10;
        graphics2D.setFont(this.gp.ui.fontBold18);
        for (n = 0; n < this.editableStats.length; ++n) {
            if (n == this.selectedStat && !this.editingFlags) {
                graphics2D.setColor(new Color(255, 255, 0, 160));
                graphics2D.fillRoundRect(n2 - 7, n3 - 18, 284, 22, 6, 6);
                graphics2D.setColor(Color.BLACK);
            } else {
                graphics2D.setColor(Color.WHITE);
            }
            if (n == 9) {
                string = this.getDayStateName(this.statValues[n]);
                graphics2D.drawString(this.editableStats[n] + ": " + string, n2, n3);
            } else if (n == 10) {
                string = this.mapNames[this.statValues[n]];
                graphics2D.drawString(this.editableStats[n] + ": " + string, n2, n3);
            } else {
                graphics2D.drawString(this.editableStats[n] + ": " + this.statValues[n], n2, n3);
            }
            n3 += n4;
        }
        graphics2D.setColor(Color.WHITE);
        graphics2D.setFont(this.gp.ui.fontBold20);
        graphics2D.drawString("FLAGS", n2, n3 += 15);
        n3 += n4;
        graphics2D.setFont(this.gp.ui.fontBold18);
        this.updateFlagValues();
        for (n = 0; n < this.flagNames.length; ++n) {
            if (n == this.selectedFlag && this.editingFlags) {
                graphics2D.setColor(new Color(255, 255, 0, 160));
                graphics2D.fillRoundRect(n2 - 7, n3 - 18, 284, 22, 6, 6);
                graphics2D.setColor(Color.BLACK);
            } else {
                graphics2D.setColor(Color.WHITE);
            }
            string = this.flagValues[n] ? "TRUE" : "FALSE";
            graphics2D.drawString(this.flagNames[n] + ": " + string, n2, n3);
            n3 += n4;
        }
        graphics2D.setColor(new Color(0, 0, 0, 120));
        graphics2D.fillRoundRect(n2 - 10, (n3 += 15) - 20, 280, 60, 10, 10);
        graphics2D.setColor(Color.YELLOW);
        graphics2D.setFont(this.gp.ui.fontBold16);
        graphics2D.drawString("W/S or \u2191/\u2193: Navigate", n2, n3);
        graphics2D.drawString("A/D or \u2190/\u2192: Change value / Toggle", n2, n3 += 15);
        graphics2D.drawString("Shift + A/D: Change by 10 (Stats)", n2, n3 += 15);
    }

    private void drawDebugStore(Graphics2D graphics2D) {
        this.drawInventoryDisplay(graphics2D);
        int n = this.gp.screenWidth - 400;
        int n2 = 30;
        int n3 = 20;
        graphics2D.setColor(new Color(0, 0, 0, 150));
        graphics2D.fillRect(n - 10, n2 - 20, 390, this.storeItems.length * n3 + 40);
        graphics2D.setColor(Color.WHITE);
        graphics2D.setFont(this.gp.ui.fontBold24);
        graphics2D.drawString("DEBUG STORE", n, n2);
        n2 += n3 + 10;
        graphics2D.setFont(this.gp.ui.fontBold18);
        for (int i = 0; i < this.storeItems.length; ++i) {
            if (i == this.selectedStoreItem) {
                graphics2D.setColor(Color.YELLOW);
                graphics2D.fillRect(n - 5, n2 - 15, 380, 18);
                graphics2D.setColor(Color.BLACK);
            } else {
                graphics2D.setColor(Color.WHITE);
            }
            graphics2D.drawString(this.storeItems[i], n, n2);
            n2 += n3;
        }
        graphics2D.setColor(new Color(0, 0, 0, 120));
        graphics2D.fillRoundRect(n - 10, (n2 += 20) - 25, 160, 120, 10, 10);
        graphics2D.setColor(Color.YELLOW);
        graphics2D.setFont(this.gp.ui.fontBold16);
        graphics2D.drawString("A/D: Switch panels", n, n2);
        graphics2D.drawString("W/S or \u2191/\u2193: Navigate", n, n2 += 15);
        graphics2D.drawString("ENTER: Add to inventory", n, n2 += 15);
        graphics2D.drawString("ESC: Close store", n, n2 += 15);
        graphics2D.drawString("Inventory: " + this.gp.player.inventory.size() + "/20", n, n2 += 15);
        graphics2D.setColor(this.storePanelSelected ? Color.GREEN : Color.GRAY);
        graphics2D.drawString("STORE SELECTED", n, n2 + 20);
    }

    private void drawInventoryDisplay(Graphics2D graphics2D) {
        int n;
        int n2 = 10;
        int n3 = 30;
        int n4 = this.gp.tileSize * 6;
        int n5 = this.gp.tileSize * 5;
        this.drawSubWindow(graphics2D, n2, n3, n4, n5);
        graphics2D.setColor(Color.WHITE);
        graphics2D.setFont(graphics2D.getFont().deriveFont(1, 24.0f));
        graphics2D.drawString("INVENTORY", 15, 25);
        int n6 = n2 + 20;
        int n7 = n3 + 20;
        int n8 = n6;
        int n9 = n7;
        int n10 = this.gp.tileSize + 3;
        for (n = 0; n < 20; ++n) {
            if (n == this.selectedInventoryItem && !this.storePanelSelected) {
                graphics2D.setColor(Color.YELLOW);
                graphics2D.setStroke(new BasicStroke(3.0f));
                graphics2D.drawRoundRect(n8 - 2, n9 - 2, this.gp.tileSize + 4, this.gp.tileSize + 4, 10, 10);
            }
            if (n < this.gp.player.inventory.size()) {
                Entity entity = (Entity)this.gp.player.inventory.get(n);
                graphics2D.drawImage((Image)entity.down1, n8, n9, null);
                if (entity.stackable && entity.amount > 1) {
                    graphics2D.setFont(this.gp.ui.fontPlain32);
                    String string = "" + entity.amount;
                    int n11 = n8 + this.gp.tileSize - 20;
                    int n12 = n9 + this.gp.tileSize - 5;
                    graphics2D.setColor(new Color(60, 60, 60));
                    graphics2D.drawString(string, n11, n12);
                    graphics2D.setColor(Color.white);
                    graphics2D.drawString(string, n11 - 3, n12 - 3);
                }
            }
            n8 += n10;
            if (n != 4 && n != 9 && n != 14) continue;
            n8 = n6;
            n9 += n10;
        }
        n = n2 + n4 + 20;
        int n13 = n3 + 30;
        graphics2D.setColor(new Color(0, 0, 0, 120));
        graphics2D.fillRoundRect(n - 10, n13 - 25, 160, 80, 10, 10);
        graphics2D.setColor(Color.YELLOW);
        graphics2D.setFont(graphics2D.getFont().deriveFont(1, 16.0f));
        graphics2D.drawString("ENTER: Remove item", n, n13);
        graphics2D.drawString("SHIFT+ENTER: Remove all", n, n13 += 15);
        graphics2D.setColor(!this.storePanelSelected ? Color.GREEN : Color.GRAY);
        graphics2D.drawString("INVENTORY SELECTED", n, n13 += 15);
    }

    private String getDayStateName(int n) {
        switch (n) {
            case 0: {
                return "Day";
            }
            case 1: {
                return "Dusk";
            }
            case 2: {
                return "Night";
            }
            case 3: {
                return "Dawn";
            }
        }
        return "Unknown";
    }

    private void drawSubWindow(Graphics2D graphics2D, int n, int n2, int n3, int n4) {
        Color color = new Color(0, 0, 0, 210);
        graphics2D.setColor(color);
        graphics2D.fillRoundRect(n, n2, n3, n4, 35, 35);
        color = new Color(255, 255, 255);
        graphics2D.setColor(color);
        graphics2D.setStroke(new BasicStroke(5.0f));
        graphics2D.drawRoundRect(n + 5, n2 + 5, n3 - 10, n4 - 10, 25, 25);
    }

    private void reloadMap() {
        int n;
        switch (this.gp.currentMap) {
            case 0: {
                TileManager tileManager = this.gp.tileM;
                Objects.requireNonNull(this.gp);
                tileManager.loadMap("/Map/bedroom.txt", 0);
                break;
            }
            case 1: {
                TileManager tileManager = this.gp.tileM;
                Objects.requireNonNull(this.gp);
                tileManager.loadMap("/Map/downstairs.txt", 1);
                break;
            }
            case 2: {
                TileManager tileManager = this.gp.tileM;
                Objects.requireNonNull(this.gp);
                tileManager.loadMap("/Map/worldmap.txt", 2);
                break;
            }
            case 3: {
                TileManager tileManager = this.gp.tileM;
                Objects.requireNonNull(this.gp);
                tileManager.loadMap("/Map/worldmap2.txt", 3);
                break;
            }
            case 4: {
                TileManager tileManager = this.gp.tileM;
                Objects.requireNonNull(this.gp);
                tileManager.loadMap("/Map/worldmap3.txt", 4);
                break;
            }
            case 5: {
                TileManager tileManager = this.gp.tileM;
                Objects.requireNonNull(this.gp);
                tileManager.loadMap("/Map/village.txt", 5);
                break;
            }
            case 6: {
                TileManager tileManager = this.gp.tileM;
                Objects.requireNonNull(this.gp);
                tileManager.loadMap("/Map/store.txt", 6);
                break;
            }
            case 7: {
                TileManager tileManager = this.gp.tileM;
                Objects.requireNonNull(this.gp);
                tileManager.loadMap("/Map/wizardHome.txt", 7);
                break;
            }
            case 8: {
                TileManager tileManager = this.gp.tileM;
                Objects.requireNonNull(this.gp);
                tileManager.loadMap("/Map/worldmap4.txt", 8);
                break;
            }
            case 9: {
                TileManager tileManager = this.gp.tileM;
                Objects.requireNonNull(this.gp);
                tileManager.loadMap("/Map/slimeBossRoom.txt", 9);
                break;
            }
            case 10: {
                TileManager tileManager = this.gp.tileM;
                Objects.requireNonNull(this.gp);
                tileManager.loadMap("/Map/worldmap5.txt", 10);
                break;
            }
            case 11: {
                TileManager tileManager = this.gp.tileM;
                Objects.requireNonNull(this.gp);
                tileManager.loadMap("/Map/dungeon01.txt", 11);
                break;
            }
            case 12: {
                TileManager tileManager = this.gp.tileM;
                Objects.requireNonNull(this.gp);
                tileManager.loadMap("/Map/dungeon02.txt", 12);
                break;
            }
            case 13: {
                TileManager tileManager = this.gp.tileM;
                Objects.requireNonNull(this.gp);
                tileManager.loadMap("/Map/skeletonLordRoom.txt", 13);
            }
        }
        this.gp.tileM.refreshWorldMapImageForCurrentMap();
        for (n = 0; n < this.gp.obj[this.gp.currentMap].length; ++n) {
            this.gp.obj[this.gp.currentMap][n] = null;
        }
        for (n = 0; n < this.gp.npc[this.gp.currentMap].length; ++n) {
            this.gp.npc[this.gp.currentMap][n] = null;
        }
        for (n = 0; n < this.gp.monster[this.gp.currentMap].length; ++n) {
            this.gp.monster[this.gp.currentMap][n] = null;
        }
        for (n = 0; n < this.gp.iTile[this.gp.currentMap].length; ++n) {
            this.gp.iTile[this.gp.currentMap][n] = null;
        }
        this.gp.aSetter.setObject();
        this.gp.aSetter.setNPC();
        this.gp.aSetter.setMonster();
        this.gp.aSetter.setInteractiveTile();
        this.gp.ui.addMessage("Map reloaded!");
    }
}
