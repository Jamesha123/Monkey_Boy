/*
 * Decompiled with CFR 0.152.
 */
package data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage
implements Serializable {
    int level;
    int maxLife;
    int life;
    int maxMana;
    int mana;
    int strength;
    int dexterity;
    int exp;
    int nextLevelExp;
    int coin;
    int ammo;
    int currentMap;
    int worldX;
    int worldY;
    ArrayList<String> itemNames = new ArrayList();
    ArrayList<Integer> itemAmounts = new ArrayList();
    int currentWeaponSlot;
    int currentShieldSlot;
    int currentLightSlot;
    String[] hotbarItemNames = new String[5];
    int[] hotbarItemAmounts = new int[5];
    String[][] mapObjectName;
    int[][] mapObjectWorldX;
    int[][] mapObjectWorldY;
    String[][] mapObjectLootNames;
    boolean[][] mapObjectOpened;
    int[][] mapObjectSize;
    String[][] mapITileName;
    int[][] mapITileWorldX;
    int[][] mapITileWorldY;
    boolean[][] mapITileDestructible;
    boolean[][] mapITileDestroyed;
    int[][] mapITileType;
    int dayState;
    int dayCounter;
    boolean skeletonLordDefeated;
    boolean slimeBossDefeated;
    boolean talkedToMom;
    boolean orcRobbedPlayer;
    boolean sugarPurchased;
    boolean talkedToOldMan;
    boolean hasReceivedMomCoins;
    boolean questCompleted;
}
