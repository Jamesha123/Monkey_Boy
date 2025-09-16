/*
 * Decompiled with CFR 0.152.
 */
package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;
import main.GamePanel;

public class KeyHandler
implements KeyListener {
    GamePanel gp;
    public boolean upPressed;
    public boolean downPressed;
    public boolean leftPressed;
    public boolean rightPressed;
    public boolean enterPressed;
    public boolean shotKeyPressed;
    public boolean spacePressed;
    public boolean showDebugText = false;
    public boolean godModeOn = false;
    public boolean shiftPressed = false;
    public boolean[] keyPressed = new boolean[256];

    public KeyHandler(GamePanel gamePanel) {
        this.gp = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int n;
        block14: {
            block13: {
                n = keyEvent.getKeyCode();
                if (n < this.keyPressed.length) {
                    this.keyPressed[n] = true;
                }
                if (n == 16) {
                    this.shiftPressed = true;
                }
                int n2 = this.gp.gameState;
                Objects.requireNonNull(this.gp);
                if (n2 == 0) {
                    this.titleState(n);
                    return;
                }
                int n3 = this.gp.gameState;
                Objects.requireNonNull(this.gp);
                if (n3 == 1) {
                    this.playState(n);
                    return;
                }
                int n4 = this.gp.gameState;
                Objects.requireNonNull(this.gp);
                if (n4 == 2) break block13;
                int n5 = this.gp.gameState;
                Objects.requireNonNull(this.gp);
                if (n5 != 10) break block14;
            }
            this.dialogueState(n);
            return;
        }
        int n6 = this.gp.gameState;
        Objects.requireNonNull(this.gp);
        if (n6 == 3) {
            this.characterState(n);
            return;
        }
        int n7 = this.gp.gameState;
        Objects.requireNonNull(this.gp);
        if (n7 == 4) {
            this.optionsState(n);
            return;
        }
        int n8 = this.gp.gameState;
        Objects.requireNonNull(this.gp);
        if (n8 == 5) {
            this.gameOverState(n);
            return;
        }
        int n9 = this.gp.gameState;
        Objects.requireNonNull(this.gp);
        if (n9 == 7) {
            this.tradeState(n);
            return;
        }
        int n10 = this.gp.gameState;
        Objects.requireNonNull(this.gp);
        if (n10 == 13) {
            this.debugState(n);
            return;
        }
        int n11 = this.gp.gameState;
        Objects.requireNonNull(this.gp);
        if (n11 == 9) {
            this.mapState(n);
            return;
        }
        int n12 = this.gp.gameState;
        Objects.requireNonNull(this.gp);
        if (n12 != 12) return;
        this.snakeGameState(n);
    }

    public void titleState(int n) {
        if (n == 87 || n == 38) {
            --this.gp.ui.commandNum;
            if (this.gp.ui.commandNum < 0) {
                this.gp.ui.commandNum = 2;
            }
        } else if (n == 83 || n == 40) {
            ++this.gp.ui.commandNum;
            if (this.gp.ui.commandNum > 2) {
                this.gp.ui.commandNum = 0;
            }
        } else if (n == 10) {
            if (this.gp.ui.commandNum == 0) {
                Objects.requireNonNull(this.gp);
                this.gp.gameState = 1;
                this.gp.stopMusic();
                this.gp.playMusic(18);
            } else if (this.gp.ui.commandNum == 1) {
                this.gp.saveLoad.load();
                Objects.requireNonNull(this.gp);
                this.gp.gameState = 1;
                this.gp.stopMusic();
                this.gp.playMusicForCurrentArea();
            } else if (this.gp.ui.commandNum == 2) {
                System.exit(0);
            }
        }
    }

    public void playState(int n) {
        if (n == 87 || n == 38) {
            this.upPressed = true;
        } else if (n == 83 || n == 40) {
            this.downPressed = true;
        } else if (n == 65 || n == 37) {
            this.leftPressed = true;
        } else if (n == 68 || n == 39) {
            this.rightPressed = true;
        } else if (n == 10) {
            this.enterPressed = true;
        } else if (n == 67) {
            Objects.requireNonNull(this.gp);
            this.gp.gameState = 3;
        } else if (n == 70) {
            this.shotKeyPressed = true;
        } else if (n == 27) {
            Objects.requireNonNull(this.gp);
            this.gp.gameState = 4;
        } else if (n == 77) {
            Objects.requireNonNull(this.gp);
            this.gp.gameState = 9;
        } else if (n == 88) {
            this.gp.map.miniMapOn = !this.gp.map.miniMapOn;
        } else if (n == 32 && this.gp.player.currentShield != null) {
            this.spacePressed = true;
        }
    }

    public void dialogueState(int n) {
        if (n == 10) {
            this.enterPressed = true;
        }
    }

    public void characterState(int n) {
        if (n == 67) {
            Objects.requireNonNull(this.gp);
            this.gp.gameState = 1;
        } else if (n == 10) {
            if (!this.gp.ui.inHotbarMode || this.gp.ui.hotbarSlot < 0) {
                this.gp.player.selectItem();
            }
        } else if (n == 86) {
            this.gp.player.dropItem();
        } else if (n == 27) {
            if (this.gp.ui.inHotbarAssignmentMode) {
                this.gp.ui.inHotbarAssignmentMode = false;
            } else {
                Objects.requireNonNull(this.gp);
                this.gp.gameState = 1;
            }
        }
        this.playerInventory(n);
    }

    public void optionsState(int n) {
        if (n == 27) {
            if (this.gp.ui.subState == 0) {
                Objects.requireNonNull(this.gp);
                this.gp.gameState = 1;
            } else {
                this.gp.ui.subState = 0;
            }
        } else if (n == 10) {
            this.enterPressed = true;
        }
        int n2 = 0;
        switch (this.gp.ui.subState) {
            case 0: {
                n2 = 6;
                break;
            }
            case 3: {
                n2 = 1;
                break;
            }
            case 4: {
                n2 = 1;
            }
        }
        if (n == 87 || n == 38) {
            --this.gp.ui.commandNum;
            this.gp.playSE(9);
            if (this.gp.ui.commandNum < 0) {
                this.gp.ui.commandNum = n2;
            }
        } else if (n == 83 || n == 40) {
            ++this.gp.ui.commandNum;
            this.gp.playSE(9);
            if (this.gp.ui.commandNum > n2) {
                this.gp.ui.commandNum = 0;
            }
        } else if (n == 65 || n == 37) {
            if (this.gp.ui.subState == 0) {
                if (this.gp.ui.commandNum == 1 && this.gp.music.volumeScale > 0) {
                    --this.gp.music.volumeScale;
                    this.gp.music.checkVolume();
                    this.gp.playSE(9);
                } else if (this.gp.ui.commandNum == 2 && this.gp.se.volumeScale > 0) {
                    --this.gp.se.volumeScale;
                    this.gp.playSE(9);
                }
            }
        } else if (n == 68 || n == 39) {
            if (this.gp.ui.commandNum == 1 && this.gp.music.volumeScale < 5) {
                ++this.gp.music.volumeScale;
                this.gp.music.checkVolume();
                this.gp.playSE(9);
            } else if (this.gp.ui.commandNum == 2 && this.gp.se.volumeScale < 5) {
                ++this.gp.se.volumeScale;
                this.gp.playSE(9);
            }
        }
    }

    public void gameOverState(int n) {
        if (n == 87 || n == 38) {
            --this.gp.ui.commandNum;
            this.gp.playSE(9);
            if (this.gp.ui.commandNum < 0) {
                this.gp.ui.commandNum = 1;
            }
        } else if (n == 83 || n == 40) {
            ++this.gp.ui.commandNum;
            this.gp.playSE(9);
            if (this.gp.ui.commandNum > 1) {
                this.gp.ui.commandNum = 0;
            }
        } else if (n == 10) {
            if (this.gp.ui.commandNum == 0) {
                this.gp.startRetryWithLoading();
            } else if (this.gp.ui.commandNum == 1) {
                Objects.requireNonNull(this.gp);
                this.gp.gameState = 0;
                this.gp.restart();
            }
        }
    }

    public void tradeState(int n) {
        if (n == 10) {
            this.enterPressed = true;
        }
        if (this.gp.ui.subState == 0) {
            if (n == 87 || n == 38) {
                --this.gp.ui.commandNum;
                this.gp.playSE(9);
                if (this.gp.ui.commandNum < 0) {
                    this.gp.ui.commandNum = 2;
                }
            } else if (n == 83 || n == 40) {
                ++this.gp.ui.commandNum;
                this.gp.playSE(9);
                if (this.gp.ui.commandNum > 2) {
                    this.gp.ui.commandNum = 0;
                }
            }
        } else if (this.gp.ui.subState == 1) {
            this.npcInventory(n);
            if (n == 27) {
                this.gp.ui.subState = 0;
            }
        } else if (this.gp.ui.subState == 2) {
            this.playerInventory(n);
            if (n == 27) {
                this.gp.ui.subState = 0;
            }
        }
    }

    public void mapState(int n) {
        if (n == 77) {
            Objects.requireNonNull(this.gp);
            this.gp.gameState = 1;
        }
    }

    public void snakeGameState(int n) {
        if (this.gp.snakeGame != null) {
            this.gp.snakeGame.handleInput(n);
            if (!this.gp.snakeGame.isGameActive()) {
                if (this.gp.snakeGame.isGameWon()) {
                    this.gp.player.mana = this.gp.player.maxMana = 5;
                    this.gp.playSE(2);
                }
                Objects.requireNonNull(this.gp);
                this.gp.gameState = 3;
                this.gp.snakeGame = null;
            }
        }
    }

    public void playerInventory(int n) {
        if (this.gp.ui.inHotbarMode) {
            if (n == 65 || n == 37) {
                if (this.gp.ui.hotbarSlot > 0) {
                    --this.gp.ui.hotbarSlot;
                    this.gp.playSE(9);
                }
            } else if (n == 68 || n == 39) {
                if (this.gp.ui.hotbarSlot < 4) {
                    ++this.gp.ui.hotbarSlot;
                    this.gp.playSE(9);
                }
            } else if (n == 87 || n == 38) {
                this.gp.ui.inHotbarMode = false;
                this.gp.ui.playerSlotRow = 3;
                this.gp.ui.playerSlotCol = this.gp.ui.hotbarSlot;
                this.gp.playSE(9);
            } else if (n == 83 || n == 40) {
                this.gp.ui.inHotbarMode = false;
                this.gp.ui.playerSlotRow = 0;
                this.gp.ui.playerSlotCol = this.gp.ui.hotbarSlot;
                this.gp.playSE(9);
            } else if (n == 10) {
                this.gp.ui.inHotbarAssignmentMode = true;
                this.gp.ui.inHotbarMode = false;
                this.gp.ui.playerSlotCol = 0;
                this.gp.ui.playerSlotRow = 0;
                this.gp.ui.addMessage("Select an item to assign to hot bar slot " + (this.gp.ui.hotbarSlot + 1));
                this.gp.playSE(9);
            }
        } else if (n == 87 || n == 38) {
            if (this.gp.ui.playerSlotRow > 0) {
                --this.gp.ui.playerSlotRow;
                this.gp.playSE(9);
            } else {
                this.gp.ui.inHotbarMode = true;
                this.gp.ui.hotbarSlot = this.gp.ui.playerSlotCol;
                this.gp.playSE(9);
            }
        } else if (n == 83 || n == 40) {
            if (this.gp.ui.playerSlotRow < 3) {
                ++this.gp.ui.playerSlotRow;
                this.gp.playSE(9);
            } else {
                this.gp.ui.inHotbarMode = true;
                this.gp.ui.hotbarSlot = this.gp.ui.playerSlotCol;
                this.gp.playSE(9);
            }
        } else if (n == 65 || n == 37) {
            if (this.gp.ui.playerSlotCol > 0) {
                --this.gp.ui.playerSlotCol;
                this.gp.playSE(9);
            }
        } else if ((n == 68 || n == 39) && this.gp.ui.playerSlotCol < 4) {
            ++this.gp.ui.playerSlotCol;
            this.gp.playSE(9);
        }
    }

    public void npcInventory(int n) {
        if (n == 87 || n == 38) {
            if (this.gp.ui.npcSlotRow != 0) {
                --this.gp.ui.npcSlotRow;
                this.gp.playSE(9);
            }
        } else if (n == 83 || n == 40) {
            if (this.gp.ui.npcSlotRow != 3) {
                ++this.gp.ui.npcSlotRow;
                this.gp.playSE(9);
            }
        } else if (n == 65 || n == 37) {
            if (this.gp.ui.npcSlotCol != 0) {
                --this.gp.ui.npcSlotCol;
                this.gp.playSE(9);
            }
        } else if ((n == 68 || n == 39) && this.gp.ui.npcSlotCol != 4) {
            ++this.gp.ui.npcSlotCol;
            this.gp.playSE(9);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int n = keyEvent.getKeyCode();
        if (n < this.keyPressed.length) {
            this.keyPressed[n] = false;
        }
        if (n == 16) {
            this.shiftPressed = false;
        }
        if (n == 87 || n == 38) {
            this.upPressed = false;
        } else if (n == 83 || n == 40) {
            this.downPressed = false;
        } else if (n == 65 || n == 37) {
            this.leftPressed = false;
        } else if (n == 68 || n == 39) {
            this.rightPressed = false;
        } else if (n == 70) {
            this.shotKeyPressed = false;
        } else if (n == 10) {
            this.enterPressed = false;
        } else if (n == 32) {
            this.spacePressed = false;
        }
    }

    public void debugState(int n) {
    }
}
