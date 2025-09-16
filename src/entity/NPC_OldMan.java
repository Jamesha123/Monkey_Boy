/*
 * Decompiled with CFR 0.152.
 */
package entity;

import data.Progress;
import entity.Entity;
import java.awt.Rectangle;
import main.GamePanel;
import objects.OBJ_Book_Fireball;

public class NPC_OldMan
extends Entity {
    public static String npcName = "Old Man";

    public NPC_OldMan(GamePanel gamePanel) {
        super(gamePanel);
        this.name = npcName;
        this.type = 1;
        this.speed = this.defaultSpeed = 1;
        this.solidArea = new Rectangle();
        this.solidArea.x = 8;
        this.solidArea.y = 16;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
        this.solidArea.width = 30;
        this.solidArea.height = 30;
        this.getNPCImage();
        this.setDialogue();
    }

    public void getNPCImage() {
        this.up1 = this.setup("/NPC/oldman_up_1", this.gp.tileSize, this.gp.tileSize);
        this.up2 = this.setup("/NPC/oldman_up_2", this.gp.tileSize, this.gp.tileSize);
        this.down1 = this.setup("/NPC/oldman_down_1", this.gp.tileSize, this.gp.tileSize);
        this.down2 = this.setup("/NPC/oldman_down_2", this.gp.tileSize, this.gp.tileSize);
        this.left1 = this.setup("/NPC/oldman_left_1", this.gp.tileSize, this.gp.tileSize);
        this.left2 = this.setup("/NPC/oldman_left_2", this.gp.tileSize, this.gp.tileSize);
        this.right1 = this.setup("/NPC/oldman_right_1", this.gp.tileSize, this.gp.tileSize);
        this.right2 = this.setup("/NPC/oldman_right_2", this.gp.tileSize, this.gp.tileSize);
    }

    public void setDialogue() {
        this.dialogues[0][0] = "OLD MAN: Greetings, young traveler!";
        this.dialogues[0][1] = "PLAYER: Hello there, sir.";
        this.dialogues[0][2] = "OLD MAN: How may I help you today?";
        this.dialogues[0][3] = "PLAYER: I'm looking for a shop that sells sugar.\nDo you know one?";
        this.dialogues[0][4] = "OLD MAN: Yes, actually. It is an odd purple looking \nbuilding, north of the village pond.";
        this.dialogues[1][0] = "OLD MAN: Ah, you're awake! Good to see you \nconscious.";
        this.dialogues[1][1] = "PLAYER: Where am I? What happened?";
        this.dialogues[1][2] = "OLD MAN: You're in my home. I found you unconscious \non the village path, badly injured.";
        this.dialogues[1][3] = "PLAYER: Injured? But I feel fine. I don't see any \nwounds...";
        this.dialogues[1][4] = "OLD MAN: *chuckles* That's because I healed you. \nI was once a wizard, you see.";
        this.dialogues[1][5] = "PLAYER: A wizard?! That's amazing! Thank you for \nsaving me!";
        this.dialogues[1][6] = "OLD MAN: You're welcome, but you should be careful \nout there. The forest is dangerous.";
        this.dialogues[1][7] = "PLAYER: I know. I was just trying to get sugar for \nmy mother, but then those orcs attacked \nme.";
        this.dialogues[1][8] = "OLD MAN: Green orcs? You mustn't face them alone! \nThey serve a dark master.";
        this.dialogues[1][9] = "PLAYER: I have to get that sugar back. It was the \nlast bag of the season... I can't \ndisappoint my family, especially my mother.";
        this.dialogues[1][10] = "OLD MAN: I know determination when I see it. Reminds \nme of my younger self. There's no way to \npursuade you otherwise.";
        this.dialogues[1][11] = "OLD MAN: *sighs* Very well. I'll help you out, but \nyou'll need more than determination.";
        this.dialogues[1][12] = "OLD MAN: Take this spellbook. It will teach you \nfireball magic - you'll need it against \nthose orcs.";
        this.dialogues[1][13] = "PLAYER: Thank you! This means everything to me.";
        this.dialogues[1][14] = "OLD MAN: The orcs likely retreated to the dungeons \nbelow. You'll need a golden key to enter.";
        this.dialogues[1][15] = "PLAYER: Where can I find such a key?";
        this.dialogues[1][16] = "OLD MAN: Adventurers passed through recently with \none. They headed west through the muddy \npath. You might catch them.";
        this.dialogues[1][17] = "OLD MAN: Also, take my old axe from the chest. \nIt's served me well over the years. \nMaybe it could serve you well.";
        this.dialogues[1][18] = "PLAYER: I won't let you down. Thank you for \neverything!";
        this.dialogues[1][19] = "OLD MAN: Good luck, young one. May your courage \nsee you through this trial.";
        this.dialogues[2][0] = "OLD MAN: Safe travels, lad!";
        this.dialogues[2][1] = "PLAYER: Thank you, old man.";
        this.dialogues[3][0] = "OLD MAN: AAHJOIHGHFHSHALHF! Oh, it's you. Don't sneak \nup on me like that.";
        this.dialogues[3][1] = "PLAYER: *chuckles* Sorry, I didn't mean to scare \nyou.";
        this.dialogues[3][2] = "PLAYER: I got the golden key, but I'm not sure where \nthe dungeon is.";
        this.dialogues[3][3] = "OLD MAN: Leave the village and head north. You can't \nmiss it. It's a giant cobblestone eyesore.";
        this.dialogues[3][4] = "PLAYER: Thank you, great wizard.";
        this.dialogues[3][5] = "OLD MAN: Now go, lad, and don't scare me like that \nagain.";
        this.dialogues[4][0] = "PLAYER: I got the sugar back! You would not believe \nthe things I had to fight.";
        this.dialogues[4][1] = "OLD MAN: *chuckles* I can imagine. I knew you could \ndo it. You're a brave lad.";
        this.dialogues[4][2] = "PLAYER: Thanks for everything, old man.";
        this.dialogues[4][3] = "OLD MAN: You're welcome, lad. You've earned that \nsugar. Hahhahaha.";
    }

    @Override
    public void setAction() {
        this.getRandomDirection(120);
    }

    @Override
    public void speak() {
        this.facePlayer();
        if (this.gp.questCompleted) {
            this.startDialogue(this, 4);
        } else if (Progress.slimeBossDefeated) {
            this.startDialogue(this, 3);
        } else if (this.gp.talkedToOldMan) {
            this.startDialogue(this, 2);
        } else if (this.gp.orcRobbedPlayer && !this.gp.talkedToOldMan) {
            this.startDialogue(this, 1);
            this.gp.player.inventory.add(new OBJ_Book_Fireball(this.gp));
            this.gp.talkedToOldMan = true;
        } else {
            this.startDialogue(this, 0);
        }
    }
}
