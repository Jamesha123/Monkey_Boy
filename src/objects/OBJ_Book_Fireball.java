/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  entity.Entity
 *  main.GamePanel
 */
package objects;

import entity.Entity;
import java.util.Objects;
import main.GamePanel;

public class OBJ_Book_Fireball
extends Entity {
    GamePanel gp;
    public static final String objName = "Book of Fireball Magic";

    public OBJ_Book_Fireball(GamePanel gamePanel) {
        super(gamePanel);
        this.gp = gamePanel;
        this.type = 11;
        this.name = objName;
        Objects.requireNonNull(gamePanel);
        Objects.requireNonNull(gamePanel);
        this.down1 = this.setup("/Object/book_fireball.png", 48, 48);
        this.description = "[" + this.name + "]\nUnderstand the pattern \nto learn Fireball magic.";
        this.stackable = true;
        this.setDialogue();
    }

    public void setDialogue() {
        this.dialogues[0][0] = "You learned the Fireball Magic! To cast it, \npress the 'F' key.";
    }
}
