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

public class SaveLoad {
	GamePanel gp;
	
	public SaveLoad(GamePanel gp) {
		this.gp = gp;
	}

    private String getSavePath() {
        String userHome = System.getProperty("user.home");
        return userHome + File.separator + "MonkeyBoy_save.dat";
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void save() {
        try {
            Throwable throwable = null;
            Object var2_4 = null;
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(this.getSavePath())));
                try {
                    int i;
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
                    int i2 = 0;
                    while (i2 < this.gp.player.inventory.size()) {
                        ds.itemNames.add(((Entity)this.gp.player.inventory.get((int)i2)).name);
                        ds.itemAmounts.add(((Entity)this.gp.player.inventory.get((int)i2)).amount);
                        ++i2;
                    }
                    ds.currentWeaponSlot = this.gp.player.getCurrentWeaponSlot();
                    ds.currentShieldSlot = this.gp.player.getCurrentShieldSlot();
                    ds.currentLightSlot = this.gp.player.getCurrentLightSlot();
                    i2 = 0;
                    while (i2 < this.gp.player.hotbar.length) {
                        if (this.gp.player.hotbar[i2] != null) {
                            ds.hotbarItemNames[i2] = this.gp.player.hotbar[i2].name;
                            ds.hotbarItemAmounts[i2] = this.gp.player.hotbar[i2].amount;
				} else {
                            ds.hotbarItemNames[i2] = null;
                            ds.hotbarItemAmounts[i2] = 0;
                        }
                        ++i2;
                    }
                    ds.mapObjectName = new String[this.gp.maxMap][this.gp.obj[1].length];
                    ds.mapObjectWorldX = new int[this.gp.maxMap][this.gp.obj[1].length];
                    ds.mapObjectWorldY = new int[this.gp.maxMap][this.gp.obj[1].length];
                    ds.mapObjectLootNames = new String[this.gp.maxMap][this.gp.obj[1].length];
                    ds.mapObjectOpened = new boolean[this.gp.maxMap][this.gp.obj[1].length];
                    ds.mapObjectSize = new int[this.gp.maxMap][this.gp.obj[1].length];
                    ds.mapITileName = new String[this.gp.maxMap][this.gp.iTile[0].length];
                    ds.mapITileWorldX = new int[this.gp.maxMap][this.gp.iTile[0].length];
                    ds.mapITileWorldY = new int[this.gp.maxMap][this.gp.iTile[0].length];
                    ds.mapITileDestructible = new boolean[this.gp.maxMap][this.gp.iTile[0].length];
                    ds.mapITileDestroyed = new boolean[this.gp.maxMap][this.gp.iTile[0].length];
                    ds.mapITileType = new int[this.gp.maxMap][this.gp.iTile[0].length];
                    int mapNum = 0;
                    while (mapNum < this.gp.maxMap) {
                        i = 0;
                        while (i < this.gp.obj[mapNum].length) {
                            if (this.gp.obj[mapNum][i] == null) {
						ds.mapObjectName[mapNum][i] = "NA";
                            } else {
                                ds.mapObjectName[mapNum][i] = this.gp.obj[mapNum][i].name;
                                ds.mapObjectWorldX[mapNum][i] = this.gp.obj[mapNum][i].worldX;
                                ds.mapObjectWorldY[mapNum][i] = this.gp.obj[mapNum][i].worldY;
                                if (this.gp.obj[mapNum][i].loot != null) {
                                    ds.mapObjectLootNames[mapNum][i] = this.gp.obj[mapNum][i].loot.name;
                                }
                                ds.mapObjectOpened[mapNum][i] = this.gp.obj[mapNum][i].opened;
                                if (this.gp.obj[mapNum][i] instanceof OBJ_Door) {
                                    ds.mapObjectSize[mapNum][i] = Math.max(1, this.gp.obj[mapNum][i].solidArea.width / this.gp.tileSize);
                                }
                            }
                            ++i;
                        }
                        ++mapNum;
                    }
                    mapNum = 0;
                    while (mapNum < this.gp.maxMap) {
                        i = 0;
                        while (i < this.gp.iTile[mapNum].length) {
                            if (this.gp.iTile[mapNum][i] == null) {
						ds.mapITileName[mapNum][i] = "NA";
                            } else {
                                ds.mapITileName[mapNum][i] = this.gp.iTile[mapNum][i].name;
                                ds.mapITileWorldX[mapNum][i] = this.gp.iTile[mapNum][i].worldX;
                                ds.mapITileWorldY[mapNum][i] = this.gp.iTile[mapNum][i].worldY;
                                ds.mapITileDestructible[mapNum][i] = this.gp.iTile[mapNum][i].destructible;
                                ds.mapITileDestroyed[mapNum][i] = this.gp.iTile[mapNum][i].life <= 0;
                                ds.mapITileType[mapNum][i] = this.gp.iTile[mapNum][i].tileType;
                            }
                            ++i;
                        }
                        ++mapNum;
                    }
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
			oos.writeObject(ds);	
                    if (oos == null) return;
                }
                catch (Throwable throwable2) {
                    if (oos == null) throw throwable2;
                    oos.close();
                    throw throwable2;
                }
                oos.close();
                return;
            }
            catch (Throwable throwable3) {
                if (throwable == null) {
                    throwable = throwable3;
                    throw throwable;
                } else {
                    if (throwable == throwable3) throw throwable;
                    throwable.addSuppressed(throwable3);
                }
                throw throwable;
            }
        }
        catch (Exception e) {
			e.printStackTrace();
		}
	}

    /*
     * Unable to fully structure code
     */
	public void load() {
        saveFile = new File(this.getSavePath());
        if (!saveFile.exists()) {
            this.gp.ui.addMessage("No save file found!");
            return;
        }
        try {
            var2_2 = null;
            var3_5 = null;
            try {
                ois = new ObjectInputStream(new FileInputStream(saveFile));
                try {
                    block49: {
                        block48: {
                            block47: {
                                block46: {
                                    block45: {
                                        ds = (DataStorage)ois.readObject();
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
                                        v0 = this.gp.currentMap;
                                        this.gp.getClass();
                                        if (v0 == 11) break block45;
                                        v1 = this.gp.currentMap;
                                        this.gp.getClass();
                                        if (v1 == 12) break block45;
                                        v2 = this.gp.currentMap;
                                        this.gp.getClass();
                                        if (v2 != 13) break block46;
                                    }
                                    this.gp.getClass();
                                    this.gp.currentArea = 3;
                                    break block47;
                                }
                                v3 = this.gp.currentMap;
                                this.gp.getClass();
                                if (v3 == 0 || v4 == 1 || v5 == 6 || v6 == 7) {
                                // 4 sources

                                {
                                    this.gp.getClass();
                                    this.gp.currentArea = 2;
			} else {
                                    this.gp.getClass();
                                    this.gp.currentArea = 1;
                                }
                            }
                            this.gp.player.inventory.clear();
                            i = 0;
                            while (i < ds.itemNames.size()) {
                                this.gp.player.inventory.add(this.gp.eGenerator.getObject(ds.itemNames.get(i)));
                                ((Entity)this.gp.player.inventory.get((int)i)).amount = ds.itemAmounts.get(i);
                                ++i;
                            }
                            this.gp.player.currentWeapon = ds.currentWeaponSlot >= 0 && ds.currentWeaponSlot < this.gp.player.inventory.size() ? (Entity)this.gp.player.inventory.get(ds.currentWeaponSlot) : null;
                            this.gp.player.currentShield = ds.currentShieldSlot >= 0 && ds.currentShieldSlot < this.gp.player.inventory.size() ? (Entity)this.gp.player.inventory.get(ds.currentShieldSlot) : null;
                            this.gp.player.currentLight = ds.currentLightSlot >= 0 && ds.currentLightSlot < this.gp.player.inventory.size() ? (Entity)this.gp.player.inventory.get(ds.currentLightSlot) : null;
                            this.gp.player.getAttack();
                            this.gp.player.getDefense();
                            this.gp.player.getAttackImage();
                            this.gp.player.getGuardImage();
                            i = 0;
                            while (i < this.gp.player.hotbar.length) {
                                if (ds.hotbarItemNames[i] != null) {
                                    this.gp.player.hotbar[i] = this.gp.eGenerator.getObject(ds.hotbarItemNames[i]);
                                    this.gp.player.hotbar[i].amount = ds.hotbarItemAmounts[i];
				} else {
                                    this.gp.player.hotbar[i] = null;
                                }
                                ++i;
                            }
                            i = 0;
                            while (i < this.gp.player.hotbar.length) {
                                if (this.gp.player.hotbar[i] != null) {
                                    hotbarName = this.gp.player.hotbar[i].name;
                                    invIdx = 0;
                                    while (invIdx < this.gp.player.inventory.size()) {
                                        if (this.gp.player.inventory.get(invIdx) != null && hotbarName.equals(((Entity)this.gp.player.inventory.get((int)invIdx)).name)) {
                                            this.gp.player.hotbar[i] = (Entity)this.gp.player.inventory.get(invIdx);
						break;
					}
                                        ++invIdx;
                                    }
                                }
                                ++i;
                            }
                            mapNum = 0;
                            while (mapNum < this.gp.maxMap) {
                                i = 0;
                                while (i < this.gp.obj[mapNum].length) {
                                    if (ds.mapObjectName[mapNum][i].equals("NA")) {
                                        this.gp.obj[mapNum][i] = null;
                                    } else {
                                        this.gp.obj[mapNum][i] = this.gp.eGenerator.getObject(ds.mapObjectName[mapNum][i]);
                                        this.gp.obj[mapNum][i].worldX = ds.mapObjectWorldX[mapNum][i];
                                        this.gp.obj[mapNum][i].worldY = ds.mapObjectWorldY[mapNum][i];
                                        if (ds.mapObjectLootNames[mapNum][i] != null) {
                                            this.gp.obj[mapNum][i].setLoot(this.gp.eGenerator.getObject(ds.mapObjectLootNames[mapNum][i]));
                                        }
                                        this.gp.obj[mapNum][i].opened = ds.mapObjectOpened[mapNum][i];
                                        if (this.gp.obj[mapNum][i].opened) {
                                            this.gp.obj[mapNum][i].down1 = this.gp.obj[mapNum][i].image2;
                                        }
                                        if (this.gp.obj[mapNum][i] != null && ds.mapObjectSize != null && ds.mapObjectSize[mapNum][i] > 0 && this.gp.obj[mapNum][i].name != null && this.gp.obj[mapNum][i].name.equals("Door")) {
                                            size = ds.mapObjectSize[mapNum][i];
                                            this.gp.obj[mapNum][i] = new OBJ_Door(this.gp, size);
                                            this.gp.obj[mapNum][i].worldX = ds.mapObjectWorldX[mapNum][i];
                                            this.gp.obj[mapNum][i].worldY = ds.mapObjectWorldY[mapNum][i];
                                        }
                                    }
                                    ++i;
                                }
                                ++mapNum;
                            }
                            if (ds.mapITileName != null) {
                                mapNum = 0;
                                while (mapNum < this.gp.maxMap) {
                                    i = 0;
                                    while (i < this.gp.iTile[mapNum].length) {
                                        if (ds.mapITileName[mapNum][i] == null || ds.mapITileName[mapNum][i].equals("NA")) {
                                            this.gp.iTile[mapNum][i] = null;
                                        } else {
                                            tileType = ds.mapITileType != null ? ds.mapITileType[mapNum][i] : 0;
                                            this.gp.iTile[mapNum][i] = this.gp.eGenerator.getInteractiveTile(ds.mapITileName[mapNum][i], tileType);
                                            if (this.gp.iTile[mapNum][i] != null) {
                                                this.gp.iTile[mapNum][i].worldX = ds.mapITileWorldX[mapNum][i];
                                                this.gp.iTile[mapNum][i].worldY = ds.mapITileWorldY[mapNum][i];
                                                this.gp.iTile[mapNum][i].destructible = ds.mapITileDestructible[mapNum][i];
                                                this.gp.iTile[mapNum][i].tileType = tileType;
                                                if (ds.mapITileDestroyed != null && ds.mapITileDestroyed[mapNum][i]) {
                                                    this.gp.iTile[mapNum][i].life = 0;
                                                    destroyedForm = this.gp.iTile[mapNum][i].getDestroyedForm();
                                                    if (destroyedForm != null) {
                                                        this.gp.iTile[mapNum][i] = destroyedForm;
                                                        this.gp.iTile[mapNum][i].worldX = ds.mapITileWorldX[mapNum][i];
                                                        this.gp.iTile[mapNum][i].worldY = ds.mapITileWorldY[mapNum][i];
                                                        this.gp.iTile[mapNum][i].tileType = tileType;
                                                    }
                                                }
                                            }
                                        }
                                        ++i;
                                    }
                                    ++mapNum;
                                }
                            }
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
                            v7 = this.gp.currentArea;
                            this.gp.getClass();
                            if (v7 == 3 || this.gp.eManager.lighting.dayState == this.gp.eManager.lighting.night) {
                                this.gp.eManager.lighting.filterAlpha = 1.0f;
                            }
                            if (!Progress.slimeBossDefeated) break block48;
                            i = 0;
                            do {
                                v8 = this.gp.monster;
                                this.gp.getClass();
                                if (v8[9][i] != null) {
                                    v9 = this.gp.monster;
                                    this.gp.getClass();
                                    if (v9[9][i].name.equals("Slime Boss")) {
                                        v10 = this.gp.monster;
                                        this.gp.getClass();
                                        v10[9][i] = null;
                                    }
                                }
                                ++i;
lbl190:
                                // 2 sources

                                v11 = this.gp.monster;
                                this.gp.getClass();
                            } while (i < v11[9].length);
                        }
                        if (!Progress.skeletonLordDefeated) break block49;
                        i = 0;
                        do {
                            v12 = this.gp.monster;
                            this.gp.getClass();
                            if (v12[13][i] != null) {
                                v13 = this.gp.monster;
                                this.gp.getClass();
                                if (v13[13][i].name.equals("Skeleton Lord")) {
                                    v14 = this.gp.monster;
                                    this.gp.getClass();
                                    v14[13][i] = null;
                                }
                            }
                            ++i;
lbl212:
                            // 2 sources

                            v15 = this.gp.monster;
                            this.gp.getClass();
                        } while (i < v15[13].length);
                    }
                    this.gp.player.getAttack();
                    this.gp.player.getDefense();
                    this.gp.player.getAttackImage();
                    this.gp.player.getGuardImage();
                    ** if (ois == null) goto lbl-1000
                }
                catch (Throwable var2_3) {
                    if (ois != null) {
                        ois.close();
                    }
                    throw var2_3;
                }
lbl-1000:
                // 1 sources

                {
                    ois.close();
                }
lbl-1000:
                // 2 sources

                {
                }
            }
            catch (Throwable var3_6) {
                if (var2_2 == null) {
                    var2_2 = var3_6;
                } else if (var2_2 != var3_6) {
                    var2_2.addSuppressed(var3_6);
                }
                throw var2_2;
            }
        }
        catch (Exception e) {
            this.gp.ui.addMessage("Error loading save file!");
			e.printStackTrace();
		}
	}
}
