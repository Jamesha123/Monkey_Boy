/*
 * Decompiled with CFR 0.152.
 */
package main;

import entity.Entity;
import main.GamePanel;
import objects.OBJ_Axe;
import objects.OBJ_Bag;
import objects.OBJ_Book_Fireball;
import objects.OBJ_Boots;
import objects.OBJ_Chest;
import objects.OBJ_Coin_Bronze;
import objects.OBJ_Door;
import objects.OBJ_Door_Iron;
import objects.OBJ_Fireball;
import objects.OBJ_Heart;
import objects.OBJ_Key;
import objects.OBJ_Lantern;
import objects.OBJ_ManaCrystal;
import objects.OBJ_Pickaxe;
import objects.OBJ_Potion_Red;
import objects.OBJ_Rock;
import objects.OBJ_Shield_Blue;
import objects.OBJ_Shield_Wood;
import objects.OBJ_Sugar;
import objects.OBJ_Sword_Epic;
import objects.OBJ_Sword_Normal;
import objects.OBJ_Tent;
import objects.OBJ_Torch;
import tile_interactive.IT_Barrier;
import tile_interactive.IT_Bed;
import tile_interactive.IT_Bookshelf;
import tile_interactive.IT_Chair;
import tile_interactive.IT_Couch;
import tile_interactive.IT_DestructibleWall;
import tile_interactive.IT_DinningTable;
import tile_interactive.IT_Drawer;
import tile_interactive.IT_DryTree;
import tile_interactive.IT_House;
import tile_interactive.IT_Kitchen;
import tile_interactive.IT_Log;
import tile_interactive.IT_MetalPlate;
import tile_interactive.IT_Rug;
import tile_interactive.IT_Torch;
import tile_interactive.IT_Trunk;
import tile_interactive.IT_Village;
import tile_interactive.InteractiveTile;

public class EntityGenerator {
    GamePanel gp;

    public EntityGenerator(GamePanel gamePanel) {
        this.gp = gamePanel;
    }

    public Entity getObject(String string) {
        Entity entity = null;
        switch (string) {
            case "Woodcutter's Axe": {
                entity = new OBJ_Axe(this.gp);
                break;
            }
            case "Boots": {
                entity = new OBJ_Boots(this.gp);
                break;
            }
            case "Key": {
                entity = new OBJ_Key(this.gp);
                break;
            }
            case "Lantern": {
                entity = new OBJ_Lantern(this.gp);
                break;
            }
            case "Red Potion": {
                entity = new OBJ_Potion_Red(this.gp);
                break;
            }
            case "Blue Shield": {
                entity = new OBJ_Shield_Blue(this.gp);
                break;
            }
            case "Wood Shield": {
                entity = new OBJ_Shield_Wood(this.gp);
                break;
            }
            case "Normal Sword": {
                entity = new OBJ_Sword_Normal(this.gp);
                break;
            }
            case "Tent": {
                entity = new OBJ_Tent(this.gp);
                break;
            }
            case "Door": {
                entity = new OBJ_Door(this.gp, 2);
                break;
            }
            case "Chest": {
                entity = new OBJ_Chest(this.gp);
                break;
            }
            case "Bronze Coin": {
                entity = new OBJ_Coin_Bronze(this.gp);
                break;
            }
            case "Heart": {
                entity = new OBJ_Heart(this.gp);
                break;
            }
            case "Mana Crystal": {
                entity = new OBJ_ManaCrystal(this.gp);
                break;
            }
            case "Fireball": {
                entity = new OBJ_Fireball(this.gp, "normal");
                break;
            }
            case "Rock": {
                entity = new OBJ_Rock(this.gp, "normal");
                break;
            }
            case "Pickaxe": {
                entity = new OBJ_Pickaxe(this.gp);
                break;
            }
            case "Iron Door": {
                entity = new OBJ_Door_Iron(this.gp);
                break;
            }
            case "Torch": {
                entity = new OBJ_Torch(this.gp);
                break;
            }
            case "Bag": {
                entity = new OBJ_Bag(this.gp);
                break;
            }
            case "Sugar": {
                entity = new OBJ_Sugar(this.gp);
                break;
            }
            case "Book of Fireball Magic": {
                entity = new OBJ_Book_Fireball(this.gp);
                break;
            }
            case "Epic Sword": {
                entity = new OBJ_Sword_Epic(this.gp);
            }
        }
        return entity;
    }

    public InteractiveTile getInteractiveTile(String string) {
        return this.getInteractiveTile(string, 0);
    }

    public InteractiveTile getInteractiveTile(String string, int n) {
        InteractiveTile interactiveTile = null;
        switch (string) {
            case "Bed": {
                interactiveTile = new IT_Bed(this.gp, 0, 0);
                break;
            }
            case "Barrier": {
                interactiveTile = new IT_Barrier(this.gp, 0, 0);
                break;
            }
            case "Bookself": {
                interactiveTile = new IT_Bookshelf(this.gp, 0, 0);
                break;
            }
            case "Chair": {
                interactiveTile = new IT_Chair(this.gp, 0, 0, "Up");
                break;
            }
            case "Couch": {
                interactiveTile = new IT_Couch(this.gp, 0, 0);
                break;
            }
            case "Destructible Wall": {
                interactiveTile = new IT_DestructibleWall(this.gp, 0, 0);
                break;
            }
            case "Dinning Table": {
                interactiveTile = new IT_DinningTable(this.gp, 0, 0);
                break;
            }
            case "Drawer": {
                interactiveTile = new IT_Drawer(this.gp, 0, 0);
                break;
            }
            case "DryTree": {
                interactiveTile = new IT_DryTree(this.gp, 0, 0);
                break;
            }
            case "House": {
                interactiveTile = new IT_House(this.gp, 0, 0);
                break;
            }
            case "Kitchen": {
                interactiveTile = new IT_Kitchen(this.gp, 0, 0);
                break;
            }
            case "Log": {
                interactiveTile = new IT_Log(this.gp, 0, 0);
                break;
            }
            case "Metal Plate": {
                interactiveTile = new IT_MetalPlate(this.gp, 0, 0);
                break;
            }
            case "Rug": {
                interactiveTile = new IT_Rug(this.gp, 0, 0);
                break;
            }
            case "Torch": {
                interactiveTile = new IT_Torch(this.gp, 0, 0, n);
                break;
            }
            case "Trunk": {
                interactiveTile = new IT_Trunk(this.gp, 0, 0);
                break;
            }
            case "Village": {
                interactiveTile = new IT_Village(this.gp, 0, 0, n);
            }
        }
        return interactiveTile;
    }
}
