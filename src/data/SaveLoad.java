/*
 * Decompiled with CFR 0.152.
 */
package data;

import data.DataStorage;
import data.Progress;
import entity.Entity;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import main.GamePanel;
import objects.OBJ_Door;
import tile_interactive.InteractiveTile;

public class SaveLoad {
	GamePanel gp;
	
	public SaveLoad(GamePanel gp) {
		this.gp = gp;
	}

    private String getSavePath() {
        String userHome = System.getProperty("user.home");
        return userHome + File.separator + "MonkeyBoy_save.dat";
    }

    public void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.getSavePath()))) {
            oos.writeObject(this.createSaveSnapshot());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private DataStorage createSaveSnapshot() {
        DataStorage ds = new DataStorage();
        ds.level = this.gp.player.level;
        ds.maxLife = this.gp.player.maxLife;
        ds.life = this.gp.player.life;
        ds.maxMana = this.gp.player.maxMana;
        ds.mana = this.gp.player.mana;
        ds.strength = this.gp.player.strength;
        ds.dexterity = this.gp.player.dexterity;
        ds.exp = this.gp.player.exp;
        ds.nextLevelExp = this.gp.player.nextLevelExp;
        ds.coin = this.gp.player.coin;
        ds.ammo = this.gp.player.ammo;
        ds.currentMap = this.gp.currentMap;
        ds.worldX = this.gp.player.worldX;
        ds.worldY = this.gp.player.worldY;
        for (int i = 0; i < this.gp.player.inventory.size(); i++) {
            Entity item = this.gp.player.inventory.get(i);
            ds.itemNames.add(item.name);
            ds.itemAmounts.add(item.amount);
        }
        ds.currentWeaponSlot = this.gp.player.getCurrentWeaponSlot();
        ds.currentShieldSlot = this.gp.player.getCurrentShieldSlot();
        ds.currentLightSlot = this.gp.player.getCurrentLightSlot();
        for (int i = 0; i < this.gp.player.hotbar.length; i++) {
            if (this.gp.player.hotbar[i] != null) {
                ds.hotbarItemNames[i] = this.gp.player.hotbar[i].name;
                ds.hotbarItemAmounts[i] = this.gp.player.hotbar[i].amount;
            } else {
                ds.hotbarItemNames[i] = null;
                ds.hotbarItemAmounts[i] = 0;
            }
        }
        this.captureMapObjects(ds);
        this.captureInteractiveTiles(ds);
        ds.dayState = this.gp.eManager.lighting.dayState;
        ds.dayCounter = this.gp.eManager.lighting.dayCounter;
        ds.skeletonLordDefeated = Progress.skeletonLordDefeated;
        ds.slimeBossDefeated = Progress.slimeBossDefeated;
        ds.talkedToMom = this.gp.talkedToMom;
        ds.orcRobbedPlayer = this.gp.orcRobbedPlayer;
        ds.sugarPurchased = this.gp.sugarPurchased;
        ds.talkedToOldMan = this.gp.talkedToOldMan;
        ds.hasReceivedMomCoins = this.gp.hasReceivedMomCoins;
        ds.questCompleted = this.gp.questCompleted;
        return ds;
    }

    private void captureMapObjects(DataStorage ds) {
        ds.mapObjectName = new String[this.gp.maxMap][this.gp.obj[1].length];
        ds.mapObjectWorldX = new int[this.gp.maxMap][this.gp.obj[1].length];
        ds.mapObjectWorldY = new int[this.gp.maxMap][this.gp.obj[1].length];
        ds.mapObjectLootNames = new String[this.gp.maxMap][this.gp.obj[1].length];
        ds.mapObjectOpened = new boolean[this.gp.maxMap][this.gp.obj[1].length];
        ds.mapObjectSize = new int[this.gp.maxMap][this.gp.obj[1].length];
        for (int mapNum = 0; mapNum < this.gp.maxMap; mapNum++) {
            for (int i = 0; i < this.gp.obj[mapNum].length; i++) {
                if (this.gp.obj[mapNum][i] == null) {
                    ds.mapObjectName[mapNum][i] = "NA";
                    continue;
                }
                Entity obj = this.gp.obj[mapNum][i];
                ds.mapObjectName[mapNum][i] = obj.name;
                ds.mapObjectWorldX[mapNum][i] = obj.worldX;
                ds.mapObjectWorldY[mapNum][i] = obj.worldY;
                if (obj.loot != null) {
                    ds.mapObjectLootNames[mapNum][i] = obj.loot.name;
                }
                ds.mapObjectOpened[mapNum][i] = obj.opened;
                if (obj instanceof OBJ_Door) {
                    ds.mapObjectSize[mapNum][i] = Math.max(1, obj.solidArea.width / this.gp.tileSize);
                }
            }
        }
    }

    private void captureInteractiveTiles(DataStorage ds) {
        ds.mapITileName = new String[this.gp.maxMap][this.gp.iTile[0].length];
        ds.mapITileWorldX = new int[this.gp.maxMap][this.gp.iTile[0].length];
        ds.mapITileWorldY = new int[this.gp.maxMap][this.gp.iTile[0].length];
        ds.mapITileDestructible = new boolean[this.gp.maxMap][this.gp.iTile[0].length];
        ds.mapITileDestroyed = new boolean[this.gp.maxMap][this.gp.iTile[0].length];
        ds.mapITileType = new int[this.gp.maxMap][this.gp.iTile[0].length];
        for (int mapNum = 0; mapNum < this.gp.maxMap; mapNum++) {
            for (int i = 0; i < this.gp.iTile[mapNum].length; i++) {
                if (this.gp.iTile[mapNum][i] == null) {
                    ds.mapITileName[mapNum][i] = "NA";
                    continue;
                }
                InteractiveTile tile = this.gp.iTile[mapNum][i];
                ds.mapITileName[mapNum][i] = tile.name;
                ds.mapITileWorldX[mapNum][i] = tile.worldX;
                ds.mapITileWorldY[mapNum][i] = tile.worldY;
                ds.mapITileDestructible[mapNum][i] = tile.destructible;
                ds.mapITileDestroyed[mapNum][i] = tile.life <= 0;
                ds.mapITileType[mapNum][i] = tile.tileType;
            }
        }
    }

	public void load() {
        File saveFile = new File(this.getSavePath());
        if (!saveFile.exists()) {
            this.gp.ui.addMessage("No save file found!");
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile))) {
            DataStorage ds = (DataStorage) ois.readObject();
            this.restorePlayerStats(ds);
            this.restoreInventory(ds);
            this.restoreHotbar(ds);
            this.syncHotbarWithInventory();
            this.restoreMapObjects(ds);
            this.restoreInteractiveTiles(ds);
            this.restoreWorldState(ds);
            this.removeDefeatedBosses();
            this.refreshPlayerCombatStats();
        } catch (Exception e) {
            this.gp.ui.addMessage("Error loading save file!");
            e.printStackTrace();
        }
    }

    private void restorePlayerStats(DataStorage ds) {
        this.gp.player.level = ds.level;
        this.gp.player.maxLife = ds.maxLife;
        this.gp.player.life = ds.life;
        this.gp.player.maxMana = ds.maxMana;
        this.gp.player.mana = ds.mana;
        this.gp.player.strength = ds.strength;
        this.gp.player.dexterity = ds.dexterity;
        this.gp.player.exp = ds.exp;
        this.gp.player.nextLevelExp = ds.nextLevelExp;
        this.gp.player.coin = ds.coin;
        this.gp.player.ammo = ds.ammo;
        this.gp.currentMap = ds.currentMap;
        this.gp.player.worldX = ds.worldX;
        this.gp.player.worldY = ds.worldY;
        this.gp.applyAreaForMap(this.gp.currentMap);
    }

    private void restoreInventory(DataStorage ds) {
        this.gp.player.inventory.clear();
        for (int i = 0; i < ds.itemNames.size(); i++) {
            Entity item = this.gp.eGenerator.getObject(ds.itemNames.get(i));
            item.amount = ds.itemAmounts.get(i);
            this.gp.player.inventory.add(item);
        }
        this.gp.player.currentWeapon = this.getInventoryItem(ds.currentWeaponSlot);
        this.gp.player.currentShield = this.getInventoryItem(ds.currentShieldSlot);
        this.gp.player.currentLight = this.getInventoryItem(ds.currentLightSlot);
    }

    private Entity getInventoryItem(int slot) {
        if (slot >= 0 && slot < this.gp.player.inventory.size()) {
            return this.gp.player.inventory.get(slot);
        }
        return null;
    }

    private void restoreHotbar(DataStorage ds) {
        for (int i = 0; i < this.gp.player.hotbar.length; i++) {
            if (ds.hotbarItemNames[i] != null) {
                this.gp.player.hotbar[i] = this.gp.eGenerator.getObject(ds.hotbarItemNames[i]);
                this.gp.player.hotbar[i].amount = ds.hotbarItemAmounts[i];
            } else {
                this.gp.player.hotbar[i] = null;
            }
        }
    }

    private void syncHotbarWithInventory() {
        for (int i = 0; i < this.gp.player.hotbar.length; i++) {
            if (this.gp.player.hotbar[i] == null) {
                continue;
            }
            String hotbarName = this.gp.player.hotbar[i].name;
            for (int invIdx = 0; invIdx < this.gp.player.inventory.size(); invIdx++) {
                Entity inventoryItem = this.gp.player.inventory.get(invIdx);
                if (inventoryItem != null && hotbarName.equals(inventoryItem.name)) {
                    this.gp.player.hotbar[i] = inventoryItem;
                    break;
                }
            }
        }
    }

    private void restoreMapObjects(DataStorage ds) {
        for (int mapNum = 0; mapNum < this.gp.maxMap; mapNum++) {
            for (int i = 0; i < this.gp.obj[mapNum].length; i++) {
                if (ds.mapObjectName[mapNum][i].equals("NA")) {
                    this.gp.obj[mapNum][i] = null;
                    continue;
                }
                Entity obj = this.gp.eGenerator.getObject(ds.mapObjectName[mapNum][i]);
                obj.worldX = ds.mapObjectWorldX[mapNum][i];
                obj.worldY = ds.mapObjectWorldY[mapNum][i];
                if (ds.mapObjectLootNames[mapNum][i] != null) {
                    obj.setLoot(this.gp.eGenerator.getObject(ds.mapObjectLootNames[mapNum][i]));
                }
                obj.opened = ds.mapObjectOpened[mapNum][i];
                if (obj.opened) {
                    obj.down1 = obj.image2;
                }
                if (ds.mapObjectSize != null && ds.mapObjectSize[mapNum][i] > 0
                        && obj.name != null && obj.name.equals("Door")) {
                    obj = new OBJ_Door(this.gp, ds.mapObjectSize[mapNum][i]);
                    obj.worldX = ds.mapObjectWorldX[mapNum][i];
                    obj.worldY = ds.mapObjectWorldY[mapNum][i];
                }
                this.gp.obj[mapNum][i] = obj;
            }
        }
    }

    private void restoreInteractiveTiles(DataStorage ds) {
        if (ds.mapITileName == null) {
            return;
        }
        for (int mapNum = 0; mapNum < this.gp.maxMap; mapNum++) {
            for (int i = 0; i < this.gp.iTile[mapNum].length; i++) {
                if (ds.mapITileName[mapNum][i] == null || ds.mapITileName[mapNum][i].equals("NA")) {
                    this.gp.iTile[mapNum][i] = null;
                    continue;
                }
                int tileType = ds.mapITileType != null ? ds.mapITileType[mapNum][i] : 0;
                InteractiveTile tile = this.gp.eGenerator.getInteractiveTile(ds.mapITileName[mapNum][i], tileType);
                if (tile == null) {
                    continue;
                }
                tile.worldX = ds.mapITileWorldX[mapNum][i];
                tile.worldY = ds.mapITileWorldY[mapNum][i];
                tile.destructible = ds.mapITileDestructible[mapNum][i];
                tile.tileType = tileType;
                if (ds.mapITileDestroyed != null && ds.mapITileDestroyed[mapNum][i]) {
                    tile.life = 0;
                    InteractiveTile destroyedForm = tile.getDestroyedForm();
                    if (destroyedForm != null) {
                        tile = destroyedForm;
                        tile.worldX = ds.mapITileWorldX[mapNum][i];
                        tile.worldY = ds.mapITileWorldY[mapNum][i];
                        tile.tileType = tileType;
                    }
                }
                this.gp.iTile[mapNum][i] = tile;
            }
        }
    }

    private void restoreWorldState(DataStorage ds) {
        this.gp.eManager.lighting.dayState = ds.dayState;
        this.gp.eManager.lighting.dayCounter = ds.dayCounter;
        Progress.skeletonLordDefeated = ds.skeletonLordDefeated;
        Progress.slimeBossDefeated = ds.slimeBossDefeated;
        this.gp.talkedToMom = ds.talkedToMom;
        this.gp.orcRobbedPlayer = ds.orcRobbedPlayer;
        this.gp.sugarPurchased = ds.sugarPurchased;
        this.gp.talkedToOldMan = ds.talkedToOldMan;
        this.gp.hasReceivedMomCoins = ds.hasReceivedMomCoins;
        this.gp.questCompleted = ds.questCompleted;
        if (this.gp.currentArea == this.gp.dungeon
                || this.gp.eManager.lighting.dayState == this.gp.eManager.lighting.night) {
            this.gp.eManager.lighting.filterAlpha = 1.0f;
        }
    }

    private void removeDefeatedBosses() {
        if (Progress.slimeBossDefeated) {
            this.removeBossFromMap(this.gp.slimeBossRoom, "Slime Boss");
        }
        if (Progress.skeletonLordDefeated) {
            this.removeBossFromMap(this.gp.skeletonLordRoom, "Skeleton Lord");
        }
    }

    private void removeBossFromMap(int mapIndex, String bossName) {
        for (int i = 0; i < this.gp.monster[mapIndex].length; i++) {
            if (this.gp.monster[mapIndex][i] != null && this.gp.monster[mapIndex][i].name.equals(bossName)) {
                this.gp.monster[mapIndex][i] = null;
            }
        }
    }

    private void refreshPlayerCombatStats() {
        this.gp.player.getAttack();
        this.gp.player.getDefense();
        this.gp.player.getAttackImage();
        this.gp.player.getGuardImage();
    }
}
