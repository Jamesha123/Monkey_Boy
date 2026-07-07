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

    public void update() {
        if (this.gp.gameState == this.gp.dialogueState
                || this.gp.gameState == this.gp.cutsceneState
                || this.gp.gameState == this.gp.cutsceneTransitionState) {
            this.inRestrictedState = true;
        } else {
            this.inRestrictedState = false;
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
            int inventorySize = Math.max(1, this.gp.player.inventory.size());
            if (this.keyH.keyPressed[87] || this.keyH.keyPressed[38]) {
                this.selectedInventoryItem = (this.selectedInventoryItem - 1 + inventorySize) % inventorySize;
                this.keyH.keyPressed[87] = false;
                this.keyH.keyPressed[38] = false;
            }
            if (this.keyH.keyPressed[83] || this.keyH.keyPressed[40]) {
                this.selectedInventoryItem = (this.selectedInventoryItem + 1) % inventorySize;
                this.keyH.keyPressed[83] = false;
                this.keyH.keyPressed[40] = false;
            }
            if (this.keyH.keyPressed[10]) {
                this.removeItemFromInventory();
                this.keyH.keyPressed[10] = false;
            }
        }
    }

    private void addItemToInventory(String itemName) {
        if (this.gp.player == null) {
            return;
        }
        Entity object = this.gp.eGenerator.getObject(itemName);
        if (object == null) {
            return;
        }
        if (this.gp.player.canObtainItem(object)) {
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
        Entity item = (Entity)this.gp.player.inventory.get(this.selectedInventoryItem);
        if (this.keyH.shiftPressed && item.stackable && item.amount > 1) {
            this.gp.player.inventory.remove(this.selectedInventoryItem);
            this.gp.playSE(2);
        } else if (item.stackable && item.amount > 1) {
            --item.amount;
            if (item.amount <= 0) {
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

    private void modifyStat(int statIndex, int delta) {
        if (this.gp.player == null) {
            return;
        }
        int index = statIndex;
        this.statValues[index] = this.statValues[index] + delta;
        switch (statIndex) {
            case 0: {
                this.statValues[statIndex] = Math.max(1, Math.min(99, this.statValues[statIndex]));
                break;
            }
            case 1: {
                this.statValues[statIndex] = Math.max(1, Math.min(999, this.statValues[statIndex]));
                if (this.statValues[statIndex] <= this.gp.player.maxLife) break;
                this.gp.player.maxLife = this.statValues[statIndex];
                break;
            }
            case 2: {
                this.statValues[statIndex] = Math.max(1, Math.min(999, this.statValues[statIndex]));
                if (this.statValues[statIndex] <= this.gp.player.maxMana) break;
                this.gp.player.maxMana = this.statValues[statIndex];
                break;
            }
            case 3: {
                this.statValues[statIndex] = Math.max(1, Math.min(20, this.statValues[statIndex]));
                break;
            }
            case 4: {
                this.statValues[statIndex] = Math.max(1, Math.min(99, this.statValues[statIndex]));
                break;
            }
            case 5: {
                this.statValues[statIndex] = Math.max(1, Math.min(99, this.statValues[statIndex]));
                break;
            }
            case 6: {
                this.statValues[statIndex] = Math.max(0, this.statValues[statIndex]);
                break;
            }
            case 7: {
                this.statValues[statIndex] = Math.max(1, this.statValues[statIndex]);
                break;
            }
            case 8: {
                this.statValues[statIndex] = Math.max(0, this.statValues[statIndex]);
                break;
            }
            case 9: {
                this.statValues[statIndex] = Math.max(0, Math.min(3, this.statValues[statIndex]));
                break;
            }
            case 10: {
                this.statValues[statIndex] = Math.max(0, Math.min(13, this.statValues[statIndex]));
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
        int textX = 10;
        int textY = 290;
        int lineHeight = 20;
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawString("=== DEBUG MODE ===", textX, textY);
        graphics2D.setColor(this.godMode ? Color.GREEN : Color.RED);
        graphics2D.drawString("God Mode: " + (this.godMode ? "ON" : "OFF") + " (G)", textX, textY += lineHeight);
        graphics2D.setColor(this.freezeEnemies ? Color.GREEN : Color.RED);
        graphics2D.drawString("Freeze Enemies: " + (this.freezeEnemies ? "ON" : "OFF") + " (T)", textX, textY += lineHeight);
        graphics2D.setColor(this.collisionOff ? Color.GREEN : Color.RED);
        graphics2D.drawString("Collision: " + (this.collisionOff ? "ON" : "OFF") + " (U)", textX, textY += lineHeight);
        graphics2D.setColor(Color.CYAN);
        graphics2D.drawString("Reload Map (R)", textX, textY += lineHeight);
        graphics2D.setColor(this.showCollision ? Color.GREEN : Color.RED);
        graphics2D.drawString("Show Collision: " + (this.showCollision ? "ON" : "OFF") + " (I)", textX, textY += lineHeight);
        graphics2D.setColor(this.darknessFilterOff ? Color.GREEN : Color.RED);
        graphics2D.drawString("Darkness Filter: " + (this.darknessFilterOff ? "OFF" : "ON") + " (O)", textX, textY += lineHeight);
        graphics2D.setColor(this.characterEditor ? Color.GREEN : Color.RED);
        graphics2D.drawString("Game Master Panel: " + (this.characterEditor ? "ON" : "OFF") + " (P)", textX, textY += lineHeight);
        graphics2D.setColor(this.debugStore ? Color.GREEN : Color.RED);
        graphics2D.drawString("Debug Store: " + (this.debugStore ? "ON" : "OFF") + " (Y)", textX, textY += lineHeight);
        graphics2D.setColor(Color.YELLOW);
        graphics2D.drawString("Press F1 to toggle debug mode", textX, textY += lineHeight);
        graphics2D.setFont(this.gp.ui.fontBold20);
        graphics2D.setColor(Color.white);
        graphics2D.drawString("WorldX " + this.gp.player.worldX, textX, textY += lineHeight);
        graphics2D.drawString("WorldY " + this.gp.player.worldY, textX, textY += lineHeight);
        graphics2D.drawString("Col " + (this.gp.player.worldX + this.gp.player.solidArea.x) / this.gp.tileSize, textX, textY += lineHeight);
        graphics2D.drawString("Row " + (this.gp.player.worldY + this.gp.player.solidArea.y) / this.gp.tileSize, textX, textY += lineHeight);
        graphics2D.drawString("Draw Time: " + this.gp.drawTime, textX, textY += lineHeight);
        textY += lineHeight;
    }

    private void drawCollisionOverlays(Graphics2D graphics2D) {
        int rectY;
        int rectX;
        int attackY;
        int attackX;
        Stroke stroke = graphics2D.getStroke();
        graphics2D.setStroke(new BasicStroke(2.0f));
        graphics2D.setColor(new Color(255, 0, 0, 180));
        if (this.gp.player != null) {
            attackX = this.gp.player.getScreenX() + this.gp.player.solidArea.x;
            attackY = this.gp.player.getScreenY() + this.gp.player.solidArea.y;
            graphics2D.drawRect(attackX, attackY, this.gp.player.solidArea.width, this.gp.player.solidArea.height);
        }
        for (attackX = 0; attackX < this.gp.npc[1].length; ++attackX) {
            Entity npc = this.gp.npc[this.gp.currentMap][attackX];
            if (npc == null || !npc.inCamera()) continue;
            rectX = npc.getScreenX() + npc.solidArea.x;
            rectY = npc.getScreenY() + npc.solidArea.y;
            graphics2D.drawRect(rectX, rectY, npc.solidArea.width, npc.solidArea.height);
        }
        for (attackX = 0; attackX < this.gp.monster[1].length; ++attackX) {
            Entity monster = this.gp.monster[this.gp.currentMap][attackX];
            if (monster == null || !monster.inCamera()) continue;
            rectX = monster.getScreenX() + monster.solidArea.x;
            rectY = monster.getScreenY() + monster.solidArea.y;
            graphics2D.drawRect(rectX, rectY, monster.solidArea.width, monster.solidArea.height);
        }
        for (attackX = 0; attackX < this.gp.obj[1].length; ++attackX) {
            Entity object = this.gp.obj[this.gp.currentMap][attackX];
            if (object == null || !object.inCamera()) continue;
            rectX = object.getScreenX() + object.solidArea.x;
            rectY = object.getScreenY() + object.solidArea.y;
            graphics2D.drawRect(rectX, rectY, object.solidArea.width, object.solidArea.height);
        }
        for (attackX = 0; attackX < this.gp.iTile[1].length; ++attackX) {
            InteractiveTile interactiveTile = this.gp.iTile[this.gp.currentMap][attackX];
            if (interactiveTile == null || !interactiveTile.inCamera()) continue;
            rectX = interactiveTile.getScreenX() + interactiveTile.solidArea.x;
            rectY = interactiveTile.getScreenY() + interactiveTile.solidArea.y;
            graphics2D.drawRect(rectX, rectY, interactiveTile.solidArea.width, interactiveTile.solidArea.height);
        }
        if (this.gp.player != null && this.gp.player.attackArea != null) {
            graphics2D.setColor(new Color(255, 165, 0, 180));
            attackX = 0;
            attackY = 0;
            switch (this.gp.player.direction) {
                case "up": {
                    attackX = this.gp.player.getScreenX() + this.gp.player.solidArea.x;
                    attackY = this.gp.player.getScreenY() + this.gp.player.solidArea.y - this.gp.player.attackArea.height;
                    break;
                }
                case "down": {
                    attackX = this.gp.player.getScreenX() + this.gp.player.solidArea.x;
                    attackY = this.gp.player.getScreenY() + this.gp.player.solidArea.y + this.gp.player.solidArea.height;
                    break;
                }
                case "left": {
                    attackX = this.gp.player.getScreenX() + this.gp.player.solidArea.x - this.gp.player.attackArea.width;
                    attackY = this.gp.player.getScreenY() + this.gp.player.solidArea.y;
                    break;
                }
                case "right": {
                    attackX = this.gp.player.getScreenX() + this.gp.player.solidArea.x + this.gp.player.solidArea.width;
                    attackY = this.gp.player.getScreenY() + this.gp.player.solidArea.y;
                }
            }
            graphics2D.drawRect(attackX, attackY, this.gp.player.attackArea.width, this.gp.player.attackArea.height);
        }
        graphics2D.setColor(new Color(255, 165, 0, 180));
        for (attackX = 0; attackX < this.gp.npc[1].length; ++attackX) {
            Entity npc = this.gp.npc[this.gp.currentMap][attackX];
            if (npc == null || !npc.inCamera() || npc.attackArea == null || npc.attackArea.width <= 0 || npc.attackArea.height <= 0) continue;
            int npcAttackX = 0;
            rectY = 0;
            switch (npc.direction) {
                case "up": {
                    npcAttackX = npc.getScreenX() + npc.solidArea.x;
                    rectY = npc.getScreenY() + npc.solidArea.y - npc.attackArea.height;
                    break;
                }
                case "down": {
                    npcAttackX = npc.getScreenX() + npc.solidArea.x;
                    rectY = npc.getScreenY() + npc.solidArea.y + npc.solidArea.height;
                    break;
                }
                case "left": {
                    npcAttackX = npc.getScreenX() + npc.solidArea.x - npc.attackArea.width;
                    rectY = npc.getScreenY() + npc.solidArea.y;
                    break;
                }
                case "right": {
                    npcAttackX = npc.getScreenX() + npc.solidArea.x + npc.solidArea.width;
                    rectY = npc.getScreenY() + npc.solidArea.y;
                }
            }
            graphics2D.drawRect(npcAttackX, rectY, npc.attackArea.width, npc.attackArea.height);
        }
        for (attackX = 0; attackX < this.gp.monster[1].length; ++attackX) {
            Entity monster = this.gp.monster[this.gp.currentMap][attackX];
            if (monster == null || !monster.inCamera() || monster.attackArea == null || monster.attackArea.width <= 0 || monster.attackArea.height <= 0) continue;
            int monsterAttackX = 0;
            rectY = 0;
            switch (monster.direction) {
                case "up": {
                    monsterAttackX = monster.getScreenX() + monster.solidArea.x;
                    rectY = monster.getScreenY() + monster.solidArea.y - monster.attackArea.height;
                    break;
                }
                case "down": {
                    monsterAttackX = monster.getScreenX() + monster.solidArea.x;
                    rectY = monster.getScreenY() + monster.solidArea.y + monster.solidArea.height;
                    break;
                }
                case "left": {
                    monsterAttackX = monster.getScreenX() + monster.solidArea.x - monster.attackArea.width;
                    rectY = monster.getScreenY() + monster.solidArea.y;
                    break;
                }
                case "right": {
                    monsterAttackX = monster.getScreenX() + monster.solidArea.x + monster.solidArea.width;
                    rectY = monster.getScreenY() + monster.solidArea.y;
                }
            }
            graphics2D.drawRect(monsterAttackX, rectY, monster.attackArea.width, monster.attackArea.height);
        }
        graphics2D.setStroke(stroke);
    }

    private void changeMap(int mapIndex) {
        int previousArea = this.gp.currentArea;
        this.gp.previousMap = this.gp.currentMap;
        this.gp.currentMap = mapIndex;
        this.gp.player.setDefaultPositions(mapIndex);
        if (previousArea != this.gp.currentArea) {
            this.gp.stopMusic();
            this.gp.playMusicForCurrentArea();
        }
    }

    private void drawGameMasterPanel(Graphics2D graphics2D) {
        String displayValue;
        int i;
        int panelX = this.gp.screenWidth - 300;
        int panelY = 30;
        int lineHeight = 20;
        int panelHeight = this.editableStats.length * lineHeight + 40 + 20 + this.flagNames.length * lineHeight + 60;
        graphics2D.setColor(new Color(0, 0, 0, 150));
        graphics2D.fillRect(panelX - 10, panelY - 20, 290, panelHeight);
        graphics2D.setColor(Color.WHITE);
        graphics2D.setFont(this.gp.ui.fontBold24);
        graphics2D.drawString("GAME MASTER PANEL", panelX, panelY);
        panelY += lineHeight + 10;
        graphics2D.setFont(this.gp.ui.fontBold18);
        for (i = 0; i < this.editableStats.length; ++i) {
            if (i == this.selectedStat && !this.editingFlags) {
                graphics2D.setColor(new Color(255, 255, 0, 160));
                graphics2D.fillRoundRect(panelX - 7, panelY - 18, 284, 22, 6, 6);
                graphics2D.setColor(Color.BLACK);
            } else {
                graphics2D.setColor(Color.WHITE);
            }
            if (i == 9) {
                displayValue = this.getDayStateName(this.statValues[i]);
                graphics2D.drawString(this.editableStats[i] + ": " + displayValue, panelX, panelY);
            } else if (i == 10) {
                displayValue = this.mapNames[this.statValues[i]];
                graphics2D.drawString(this.editableStats[i] + ": " + displayValue, panelX, panelY);
            } else {
                graphics2D.drawString(this.editableStats[i] + ": " + this.statValues[i], panelX, panelY);
            }
            panelY += lineHeight;
        }
        graphics2D.setColor(Color.WHITE);
        graphics2D.setFont(this.gp.ui.fontBold20);
        graphics2D.drawString("FLAGS", panelX, panelY += 15);
        panelY += lineHeight;
        graphics2D.setFont(this.gp.ui.fontBold18);
        this.updateFlagValues();
        for (i = 0; i < this.flagNames.length; ++i) {
            if (i == this.selectedFlag && this.editingFlags) {
                graphics2D.setColor(new Color(255, 255, 0, 160));
                graphics2D.fillRoundRect(panelX - 7, panelY - 18, 284, 22, 6, 6);
                graphics2D.setColor(Color.BLACK);
            } else {
                graphics2D.setColor(Color.WHITE);
            }
            displayValue = this.flagValues[i] ? "TRUE" : "FALSE";
            graphics2D.drawString(this.flagNames[i] + ": " + displayValue, panelX, panelY);
            panelY += lineHeight;
        }
        graphics2D.setColor(new Color(0, 0, 0, 120));
        graphics2D.fillRoundRect(panelX - 10, (panelY += 15) - 20, 280, 60, 10, 10);
        graphics2D.setColor(Color.YELLOW);
        graphics2D.setFont(this.gp.ui.fontBold16);
        graphics2D.drawString("W/S or \u2191/\u2193: Navigate", panelX, panelY);
        graphics2D.drawString("A/D or \u2190/\u2192: Change value / Toggle", panelX, panelY += 15);
        graphics2D.drawString("Shift + A/D: Change by 10 (Stats)", panelX, panelY += 15);
    }

    private void drawDebugStore(Graphics2D graphics2D) {
        this.drawInventoryDisplay(graphics2D);
        int panelX = this.gp.screenWidth - 400;
        int panelY = 30;
        int lineHeight = 20;
        graphics2D.setColor(new Color(0, 0, 0, 150));
        graphics2D.fillRect(panelX - 10, panelY - 20, 390, this.storeItems.length * lineHeight + 40);
        graphics2D.setColor(Color.WHITE);
        graphics2D.setFont(this.gp.ui.fontBold24);
        graphics2D.drawString("DEBUG STORE", panelX, panelY);
        panelY += lineHeight + 10;
        graphics2D.setFont(this.gp.ui.fontBold18);
        for (int i = 0; i < this.storeItems.length; ++i) {
            if (i == this.selectedStoreItem) {
                graphics2D.setColor(Color.YELLOW);
                graphics2D.fillRect(panelX - 5, panelY - 15, 380, 18);
                graphics2D.setColor(Color.BLACK);
            } else {
                graphics2D.setColor(Color.WHITE);
            }
            graphics2D.drawString(this.storeItems[i], panelX, panelY);
            panelY += lineHeight;
        }
        graphics2D.setColor(new Color(0, 0, 0, 120));
        graphics2D.fillRoundRect(panelX - 10, (panelY += 20) - 25, 160, 120, 10, 10);
        graphics2D.setColor(Color.YELLOW);
        graphics2D.setFont(this.gp.ui.fontBold16);
        graphics2D.drawString("A/D: Switch panels", panelX, panelY);
        graphics2D.drawString("W/S or \u2191/\u2193: Navigate", panelX, panelY += 15);
        graphics2D.drawString("ENTER: Add to inventory", panelX, panelY += 15);
        graphics2D.drawString("ESC: Close store", panelX, panelY += 15);
        graphics2D.drawString("Inventory: " + this.gp.player.inventory.size() + "/20", panelX, panelY += 15);
        graphics2D.setColor(this.storePanelSelected ? Color.GREEN : Color.GRAY);
        graphics2D.drawString("STORE SELECTED", panelX, panelY + 20);
    }

    private void drawInventoryDisplay(Graphics2D graphics2D) {
        int slotIndex;
        int frameX = 10;
        int frameY = 30;
        int frameWidth = this.gp.tileSize * 6;
        int frameHeight = this.gp.tileSize * 5;
        this.drawSubWindow(graphics2D, frameX, frameY, frameWidth, frameHeight);
        graphics2D.setColor(Color.WHITE);
        graphics2D.setFont(graphics2D.getFont().deriveFont(1, 24.0f));
        graphics2D.drawString("INVENTORY", 15, 25);
        int slotStartX = frameX + 20;
        int slotStartY = frameY + 20;
        int slotX = slotStartX;
        int slotY = slotStartY;
        int slotSize = this.gp.tileSize + 3;
        for (slotIndex = 0; slotIndex < 20; ++slotIndex) {
            if (slotIndex == this.selectedInventoryItem && !this.storePanelSelected) {
                graphics2D.setColor(Color.YELLOW);
                graphics2D.setStroke(new BasicStroke(3.0f));
                graphics2D.drawRoundRect(slotX - 2, slotY - 2, this.gp.tileSize + 4, this.gp.tileSize + 4, 10, 10);
            }
            if (slotIndex < this.gp.player.inventory.size()) {
                Entity item = (Entity)this.gp.player.inventory.get(slotIndex);
                graphics2D.drawImage((Image)item.down1, slotX, slotY, null);
                if (item.stackable && item.amount > 1) {
                    graphics2D.setFont(this.gp.ui.fontPlain32);
                    String amountText = "" + item.amount;
                    int amountX = slotX + this.gp.tileSize - 20;
                    int amountY = slotY + this.gp.tileSize - 5;
                    graphics2D.setColor(new Color(60, 60, 60));
                    graphics2D.drawString(amountText, amountX, amountY);
                    graphics2D.setColor(Color.white);
                    graphics2D.drawString(amountText, amountX - 3, amountY - 3);
                }
            }
            slotX += slotSize;
            if (slotIndex != 4 && slotIndex != 9 && slotIndex != 14) continue;
            slotX = slotStartX;
            slotY += slotSize;
        }
        int helpX = frameX + frameWidth + 20;
        int helpY = frameY + 30;
        graphics2D.setColor(new Color(0, 0, 0, 120));
        graphics2D.fillRoundRect(helpX - 10, helpY - 25, 160, 80, 10, 10);
        graphics2D.setColor(Color.YELLOW);
        graphics2D.setFont(graphics2D.getFont().deriveFont(1, 16.0f));
        graphics2D.drawString("ENTER: Remove item", helpX, helpY);
        graphics2D.drawString("SHIFT+ENTER: Remove all", helpX, helpY += 15);
        graphics2D.setColor(!this.storePanelSelected ? Color.GREEN : Color.GRAY);
        graphics2D.drawString("INVENTORY SELECTED", helpX, helpY += 15);
    }

    private String getDayStateName(int dayState) {
        switch (dayState) {
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

    private void drawSubWindow(Graphics2D graphics2D, int x, int y, int width, int height) {
        Color color = new Color(0, 0, 0, 210);
        graphics2D.setColor(color);
        graphics2D.fillRoundRect(x, y, width, height, 35, 35);
        color = new Color(255, 255, 255);
        graphics2D.setColor(color);
        graphics2D.setStroke(new BasicStroke(5.0f));
        graphics2D.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    private void reloadMap() {
        int i;
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
        for (i = 0; i < this.gp.obj[this.gp.currentMap].length; ++i) {
            this.gp.obj[this.gp.currentMap][i] = null;
        }
        for (i = 0; i < this.gp.npc[this.gp.currentMap].length; ++i) {
            this.gp.npc[this.gp.currentMap][i] = null;
        }
        for (i = 0; i < this.gp.monster[this.gp.currentMap].length; ++i) {
            this.gp.monster[this.gp.currentMap][i] = null;
        }
        for (i = 0; i < this.gp.iTile[this.gp.currentMap].length; ++i) {
            this.gp.iTile[this.gp.currentMap][i] = null;
        }
        this.gp.aSetter.setObject();
        this.gp.aSetter.setNPC();
        this.gp.aSetter.setMonster();
        this.gp.aSetter.setInteractiveTile();
        this.gp.ui.addMessage("Map reloaded!");
    }
}
