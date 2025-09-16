/*
 * Decompiled with CFR 0.152.
 */
package main;

import data.Progress;
import entity.Entity;
import java.util.Objects;
import main.EventRect;
import main.GamePanel;

public class EventHandler {
    GamePanel gp;
    EventRect[][][] eventRect;
    Entity eventMaster;
    int previousEventX;
    int previousEventY;
    boolean canTouchEvent = true;
    int hasSugar = 999;
    int tempMap;
    int tempCol;
    int tempRow;
    int talkingToMom = 0;
    int blockedPath = 1;

    public EventHandler(GamePanel gamePanel) {
        this.gp = gamePanel;
        this.eventMaster = new Entity(gamePanel);
        this.eventRect = new EventRect[gamePanel.maxMap][gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        while (n < gamePanel.maxMap && n2 < gamePanel.maxWorldCol && n3 < gamePanel.maxWorldRow) {
            this.eventRect[n][n2][n3] = new EventRect();
            this.eventRect[n][n2][n3].x = 23;
            this.eventRect[n][n2][n3].y = 23;
            this.eventRect[n][n2][n3].width = 2;
            this.eventRect[n][n2][n3].height = 2;
            this.eventRect[n][n2][n3].eventRectDefaultX = this.eventRect[n][n2][n3].x;
            this.eventRect[n][n2][n3].eventRectDefaultY = this.eventRect[n][n2][n3].y;
            if (++n2 != gamePanel.maxWorldCol) continue;
            n2 = 0;
            if (++n3 != gamePanel.maxWorldRow) continue;
            n3 = 0;
            ++n;
        }
        this.setDialogue();
    }

    public void setDialogue() {
        this.eventMaster.dialogues[this.talkingToMom][0] = "Hey, kiddo, can you come here?";
        this.eventMaster.dialogues[this.blockedPath][0] = "The path is blocked! The rain has cause the area \nto be muddy.";
        this.eventMaster.dialogues[this.blockedPath][1] = "I need to find another way!";
    }

    public void checkEvent() {
        int n;
        int n2 = Math.abs(this.gp.player.worldX - this.previousEventX);
        int n3 = Math.max(n2, n = Math.abs(this.gp.player.worldY - this.previousEventY));
        if (n3 > this.gp.tileSize) {
            this.canTouchEvent = true;
        }
        if (this.canTouchEvent) {
            Objects.requireNonNull(this.gp);
            if (this.hit(0, 28, 23, "right")) {
                Objects.requireNonNull(this.gp);
                Objects.requireNonNull(this.gp);
                this.teleport(1, 29, 23, 2);
            } else {
                Objects.requireNonNull(this.gp);
                if (this.hit(0, 28, 24, "right")) {
                    Objects.requireNonNull(this.gp);
                    Objects.requireNonNull(this.gp);
                    this.teleport(1, 29, 23, 2);
                } else {
                    Objects.requireNonNull(this.gp);
                    if (this.hit(1, 28, 22, "left")) {
                        Objects.requireNonNull(this.gp);
                        Objects.requireNonNull(this.gp);
                        this.teleport(0, 28, 22, 2);
                    } else {
                        Objects.requireNonNull(this.gp);
                        if (this.hit(1, 28, 23, "left")) {
                            Objects.requireNonNull(this.gp);
                            Objects.requireNonNull(this.gp);
                            this.teleport(0, 28, 23, 2);
                        } else {
                            Objects.requireNonNull(this.gp);
                            if (this.hit(1, 27, 29, "down") && this.gp.talkedToMom) {
                                Objects.requireNonNull(this.gp);
                                Objects.requireNonNull(this.gp);
                                this.teleport(2, 19, 38, 1);
                            } else {
                                Objects.requireNonNull(this.gp);
                                if (this.hit(2, 19, 38, "up")) {
                                    Objects.requireNonNull(this.gp);
                                    Objects.requireNonNull(this.gp);
                                    this.teleport(1, 27, 28, 2);
                                } else {
                                    Objects.requireNonNull(this.gp);
                                    if (this.hit(2, 37, 22, "up")) {
                                        Objects.requireNonNull(this.gp);
                                        Objects.requireNonNull(this.gp);
                                        this.teleport(3, 25, 41, 1);
                                        this.respawnMonster();
                                    } else {
                                        Objects.requireNonNull(this.gp);
                                        if (this.hit(3, 25, 42, "down")) {
                                            Objects.requireNonNull(this.gp);
                                            Objects.requireNonNull(this.gp);
                                            this.teleport(2, 37, 23, 1);
                                        } else {
                                            Objects.requireNonNull(this.gp);
                                            if (this.hit(3, 39, 12, "right")) {
                                                Objects.requireNonNull(this.gp);
                                                Objects.requireNonNull(this.gp);
                                                this.teleport(4, 9, 12, 1);
                                                this.respawnMonster();
                                            } else {
                                                Objects.requireNonNull(this.gp);
                                                if (this.hit(4, 8, 12, "left")) {
                                                    Objects.requireNonNull(this.gp);
                                                    Objects.requireNonNull(this.gp);
                                                    this.teleport(3, 38, 12, 1);
                                                    this.respawnMonster();
                                                } else {
                                                    Objects.requireNonNull(this.gp);
                                                    if (this.hit(4, 38, 37, "right")) {
                                                        Objects.requireNonNull(this.gp);
                                                        Objects.requireNonNull(this.gp);
                                                        this.teleport(5, 9, 29, 1);
                                                    } else {
                                                        Objects.requireNonNull(this.gp);
                                                        if (this.hit(5, 8, 29, "left")) {
                                                            Objects.requireNonNull(this.gp);
                                                            Objects.requireNonNull(this.gp);
                                                            this.teleport(4, 37, 37, 1);
                                                            this.respawnMonster();
                                                        } else {
                                                            Objects.requireNonNull(this.gp);
                                                            if (this.hit(5, 34, 17, "up")) {
                                                                Objects.requireNonNull(this.gp);
                                                                Objects.requireNonNull(this.gp);
                                                                this.teleport(6, 12, 12, 2);
                                                            } else {
                                                                Objects.requireNonNull(this.gp);
                                                                if (this.hit(6, 12, 13, "down")) {
                                                                    Objects.requireNonNull(this.gp);
                                                                    Objects.requireNonNull(this.gp);
                                                                    this.teleport(5, 34, 18, 1);
                                                                } else {
                                                                    Objects.requireNonNull(this.gp);
                                                                    if (this.hit(5, 18, 8, "right")) {
                                                                        Objects.requireNonNull(this.gp);
                                                                        Objects.requireNonNull(this.gp);
                                                                        this.teleport(7, 12, 14, 2);
                                                                    } else {
                                                                        Objects.requireNonNull(this.gp);
                                                                        if (this.hit(7, 12, 15, "down")) {
                                                                            Objects.requireNonNull(this.gp);
                                                                            Objects.requireNonNull(this.gp);
                                                                            this.teleport(5, 17, 8, 1);
                                                                        } else {
                                                                            Objects.requireNonNull(this.gp);
                                                                            if (this.hit(4, 10, 37, "left") && this.gp.talkedToOldMan) {
                                                                                Objects.requireNonNull(this.gp);
                                                                                Objects.requireNonNull(this.gp);
                                                                                this.teleport(8, 41, 8, 1);
                                                                            } else {
                                                                                Objects.requireNonNull(this.gp);
                                                                                if (this.hit(8, 41, 8, "right")) {
                                                                                    Objects.requireNonNull(this.gp);
                                                                                    Objects.requireNonNull(this.gp);
                                                                                    this.teleport(4, 11, 37, 1);
                                                                                } else {
                                                                                    Objects.requireNonNull(this.gp);
                                                                                    if (this.hit(8, 21, 42, "down")) {
                                                                                        Objects.requireNonNull(this.gp);
                                                                                        Objects.requireNonNull(this.gp);
                                                                                        this.teleport(9, 24, 11, 1);
                                                                                    } else {
                                                                                        Objects.requireNonNull(this.gp);
                                                                                        if (this.hit(9, 24, 10, "up") && Progress.slimeBossDefeated) {
                                                                                            Objects.requireNonNull(this.gp);
                                                                                            Objects.requireNonNull(this.gp);
                                                                                            this.teleport(8, 21, 41, 1);
                                                                                        } else {
                                                                                            Objects.requireNonNull(this.gp);
                                                                                            if (this.hit(4, 35, 8, "up")) {
                                                                                                Objects.requireNonNull(this.gp);
                                                                                                Objects.requireNonNull(this.gp);
                                                                                                this.teleport(10, 22, 39, 1);
                                                                                            } else {
                                                                                                Objects.requireNonNull(this.gp);
                                                                                                if (this.hit(10, 22, 40, "down")) {
                                                                                                    Objects.requireNonNull(this.gp);
                                                                                                    Objects.requireNonNull(this.gp);
                                                                                                    this.teleport(4, 35, 9, 1);
                                                                                                } else {
                                                                                                    Objects.requireNonNull(this.gp);
                                                                                                    if (this.hit(10, 36, 8, "up")) {
                                                                                                        Objects.requireNonNull(this.gp);
                                                                                                        Objects.requireNonNull(this.gp);
                                                                                                        this.teleport(11, 9, 42, 3);
                                                                                                    } else {
                                                                                                        Objects.requireNonNull(this.gp);
                                                                                                        if (this.hit(11, 9, 43, "down")) {
                                                                                                            Objects.requireNonNull(this.gp);
                                                                                                            Objects.requireNonNull(this.gp);
                                                                                                            this.teleport(10, 36, 9, 1);
                                                                                                        } else {
                                                                                                            Objects.requireNonNull(this.gp);
                                                                                                            if (this.hit(11, 12, 7, "up")) {
                                                                                                                Objects.requireNonNull(this.gp);
                                                                                                                Objects.requireNonNull(this.gp);
                                                                                                                this.teleport(12, 28, 42, 3);
                                                                                                            } else {
                                                                                                                Objects.requireNonNull(this.gp);
                                                                                                                if (this.hit(12, 28, 43, "down")) {
                                                                                                                    Objects.requireNonNull(this.gp);
                                                                                                                    Objects.requireNonNull(this.gp);
                                                                                                                    this.teleport(11, 12, 8, 3);
                                                                                                                } else {
                                                                                                                    Objects.requireNonNull(this.gp);
                                                                                                                    if (this.hit(12, 38, 8, "any")) {
                                                                                                                        Objects.requireNonNull(this.gp);
                                                                                                                        Objects.requireNonNull(this.gp);
                                                                                                                        this.teleport(13, 25, 42, 3);
                                                                                                                    } else {
                                                                                                                        Objects.requireNonNull(this.gp);
                                                                                                                        if (this.hit(12, 39, 8, "any")) {
                                                                                                                            Objects.requireNonNull(this.gp);
                                                                                                                            Objects.requireNonNull(this.gp);
                                                                                                                            this.teleport(13, 26, 42, 3);
                                                                                                                        } else {
                                                                                                                            Objects.requireNonNull(this.gp);
                                                                                                                            if (this.hit(13, 25, 43, "any")) {
                                                                                                                                Objects.requireNonNull(this.gp);
                                                                                                                                Objects.requireNonNull(this.gp);
                                                                                                                                this.teleport(12, 38, 9, 3);
                                                                                                                            } else {
                                                                                                                                Objects.requireNonNull(this.gp);
                                                                                                                                if (this.hit(13, 26, 43, "any")) {
                                                                                                                                    Objects.requireNonNull(this.gp);
                                                                                                                                    Objects.requireNonNull(this.gp);
                                                                                                                                    this.teleport(12, 39, 9, 3);
                                                                                                                                } else {
                                                                                                                                    Objects.requireNonNull(this.gp);
                                                                                                                                    if (this.hit(1, 27, 28, "down") && !this.gp.talkedToMom) {
                                                                                                                                        this.dialogue(this.talkingToMom);
                                                                                                                                    } else {
                                                                                                                                        Objects.requireNonNull(this.gp);
                                                                                                                                        if (this.hit(3, 37, 37, "right")) {
                                                                                                                                            this.dialogue(this.blockedPath);
                                                                                                                                        } else {
                                                                                                                                            Objects.requireNonNull(this.gp);
                                                                                                                                            if (this.hit(1, 20, 21, "up")) {
                                                                                                                                                this.speak(this.gp.npc[1][1]);
                                                                                                                                            } else {
                                                                                                                                                Objects.requireNonNull(this.gp);
                                                                                                                                                if (this.hit(6, 12, 9, "up")) {
                                                                                                                                                    this.speak(this.gp.npc[6][0]);
                                                                                                                                                } else {
                                                                                                                                                    Objects.requireNonNull(this.gp);
                                                                                                                                                    if (this.hit(4, 30, 37, "left") && this.hasSugar != this.gp.player.searchItemInInventory("Sugar")) {
                                                                                                                                                        this.orcRobbery();
                                                                                                                                                    } else {
                                                                                                                                                        Objects.requireNonNull(this.gp);
                                                                                                                                                        if (this.hit(9, 24, 12, "any")) {
                                                                                                                                                            this.slimeBoss();
                                                                                                                                                        } else {
                                                                                                                                                            Objects.requireNonNull(this.gp);
                                                                                                                                                            if (this.hit(13, 25, 32, "any")) {
                                                                                                                                                                this.skeletonLord();
                                                                                                                                                            } else {
                                                                                                                                                                Objects.requireNonNull(this.gp);
                                                                                                                                                                if (this.hit(13, 26, 32, "any")) {
                                                                                                                                                                    this.skeletonLord();
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean hit(int n, int n2, int n3, String string) {
        boolean bl = false;
        if (n == this.gp.currentMap) {
            this.gp.player.solidArea.x = this.gp.player.worldX + this.gp.player.solidArea.x;
            this.gp.player.solidArea.y = this.gp.player.worldY + this.gp.player.solidArea.y;
            this.eventRect[n][n2][n3].x = n2 * this.gp.tileSize + this.eventRect[n][n2][n3].x;
            this.eventRect[n][n2][n3].y = n3 * this.gp.tileSize + this.eventRect[n][n2][n3].y;
            if (this.gp.player.solidArea.intersects(this.eventRect[n][n2][n3]) && !this.eventRect[n][n2][n3].eventDone && (this.gp.player.direction.contentEquals(string) || string.contentEquals("any"))) {
                bl = true;
                this.previousEventX = this.gp.player.worldX;
                this.previousEventY = this.gp.player.worldY;
            }
            this.gp.player.solidArea.x = this.gp.player.solidAreaDefaultX;
            this.gp.player.solidArea.y = this.gp.player.solidAreaDefaultY;
            this.eventRect[n][n2][n3].x = this.eventRect[n][n2][n3].eventRectDefaultX;
            this.eventRect[n][n2][n3].y = this.eventRect[n][n2][n3].eventRectDefaultY;
        }
        return bl;
    }

    public void teleport(int n, int n2, int n3, int n4) {
        Objects.requireNonNull(this.gp);
        this.gp.gameState = 6;
        this.gp.nextArea = n4;
        this.tempMap = n;
        this.tempCol = n2;
        this.tempRow = n3;
        this.canTouchEvent = false;
        this.gp.playSE(13);
    }

    public void damagePit(int n) {
        this.gp.gameState = n;
        this.gp.playSE(6);
        this.eventMaster.startDialogue(this.eventMaster, 0);
        --this.gp.player.life;
        this.canTouchEvent = false;
    }

    public void dialogue(int n) {
        this.eventMaster.startDialogue(this.eventMaster, n);
        this.canTouchEvent = false;
    }

    public void speak(Entity entity) {
        if (this.gp.keyH.enterPressed) {
            Objects.requireNonNull(this.gp);
            this.gp.gameState = 2;
            this.gp.player.attackCanceled = true;
            entity.speak();
        }
    }

    public void respawnMonster() {
        this.gp.aSetter.setMonster();
    }

    public void orcRobbery() {
        Objects.requireNonNull(this.gp);
        this.gp.gameState = 10;
        this.gp.csManager.sceneNum = this.gp.csManager.orcRobbery;
    }

    public void slimeBoss() {
        if (!this.gp.bossBattleOn && !Progress.slimeBossDefeated) {
            Objects.requireNonNull(this.gp);
            this.gp.gameState = 10;
            this.gp.csManager.sceneNum = this.gp.csManager.slimeBoss;
        }
    }

    public void skeletonLord() {
        if (!this.gp.bossBattleOn && !Progress.skeletonLordDefeated) {
            Objects.requireNonNull(this.gp);
            this.gp.gameState = 10;
            this.gp.csManager.sceneNum = this.gp.csManager.skeletonLord;
        }
    }
}
