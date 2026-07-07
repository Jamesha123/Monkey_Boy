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
        int keyCode;
        block14: {
            block13: {
                keyCode = keyEvent.getKeyCode();
                if (keyCode < this.keyPressed.length) {
                    this.keyPressed[keyCode] = true;
                }
                if (keyCode == 16) {
                    this.shiftPressed = true;
                }
                int gameState = this.gp.gameState;
                Objects.requireNonNull(this.gp);
                if (gameState == 0) {
                    this.titleState(keyCode);
                    return;
                }
                gameState = this.gp.gameState;
                Objects.requireNonNull(this.gp);
                if (gameState == 1) {
                    this.playState(keyCode);
                    return;
                }
                gameState = this.gp.gameState;
                Objects.requireNonNull(this.gp);
                if (gameState == 2) break block13;
                gameState = this.gp.gameState;
                Objects.requireNonNull(this.gp);
                if (gameState != 10) break block14;
            }
            this.dialogueState(keyCode);
            return;
        }
        int gameState = this.gp.gameState;
        Objects.requireNonNull(this.gp);
        if (gameState == 3) {
            this.characterState(keyCode);
            return;
        }
        gameState = this.gp.gameState;
        Objects.requireNonNull(this.gp);
        if (gameState == 4) {
            this.optionsState(keyCode);
            return;
        }
        gameState = this.gp.gameState;
        Objects.requireNonNull(this.gp);
        if (gameState == 5) {
            this.gameOverState(keyCode);
            return;
        }
        gameState = this.gp.gameState;
        Objects.requireNonNull(this.gp);
        if (gameState == 7) {
            this.tradeState(keyCode);
            return;
        }
        gameState = this.gp.gameState;
        Objects.requireNonNull(this.gp);
        if (gameState == 13) {
            this.debugState(keyCode);
            return;
        }
        gameState = this.gp.gameState;
        Objects.requireNonNull(this.gp);
        if (gameState == 9) {
            this.mapState(keyCode);
            return;
        }
        gameState = this.gp.gameState;
        Objects.requireNonNull(this.gp);
        if (gameState != 12) return;
        this.snakeGameState(keyCode);
    }

    public void titleState(int keyCode) {
        if (keyCode == 87 || keyCode == 38) {
            --this.gp.ui.commandNum;
            if (this.gp.ui.commandNum < 0) {
                this.gp.ui.commandNum = 2;
            }
        } else if (keyCode == 83 || keyCode == 40) {
            ++this.gp.ui.commandNum;
            if (this.gp.ui.commandNum > 2) {
                this.gp.ui.commandNum = 0;
            }
        } else if (keyCode == 10) {
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

    public void playState(int keyCode) {
        if (keyCode == 87 || keyCode == 38) {
            this.upPressed = true;
        } else if (keyCode == 83 || keyCode == 40) {
            this.downPressed = true;
        } else if (keyCode == 65 || keyCode == 37) {
            this.leftPressed = true;
        } else if (keyCode == 68 || keyCode == 39) {
            this.rightPressed = true;
        } else if (keyCode == 10) {
            this.enterPressed = true;
        } else if (keyCode == 67) {
            Objects.requireNonNull(this.gp);
            this.gp.gameState = 3;
        } else if (keyCode == 70) {
            this.shotKeyPressed = true;
        } else if (keyCode == 27) {
            Objects.requireNonNull(this.gp);
            this.gp.gameState = 4;
        } else if (keyCode == 77) {
            Objects.requireNonNull(this.gp);
            this.gp.gameState = 9;
        } else if (keyCode == 88) {
            this.gp.map.miniMapOn = !this.gp.map.miniMapOn;
        } else if (keyCode == 32 && this.gp.player.currentShield != null) {
            this.spacePressed = true;
        }
    }

    public void dialogueState(int keyCode) {
        if (keyCode == 10) {
            this.enterPressed = true;
        }
    }

    public void characterState(int keyCode) {
        if (keyCode == 67) {
            Objects.requireNonNull(this.gp);
            this.gp.gameState = 1;
        } else if (keyCode == 10) {
            if (!this.gp.ui.inHotbarMode || this.gp.ui.hotbarSlot < 0) {
                this.gp.player.selectItem();
            }
        } else if (keyCode == 86) {
            this.gp.player.dropItem();
        } else if (keyCode == 27) {
            if (this.gp.ui.inHotbarAssignmentMode) {
                this.gp.ui.inHotbarAssignmentMode = false;
            } else {
                Objects.requireNonNull(this.gp);
                this.gp.gameState = 1;
            }
        }
        this.playerInventory(keyCode);
    }

    public void optionsState(int keyCode) {
        if (keyCode == 27) {
            if (this.gp.ui.subState == 0) {
                Objects.requireNonNull(this.gp);
                this.gp.gameState = 1;
            } else {
                this.gp.ui.subState = 0;
            }
        } else if (keyCode == 10) {
            this.enterPressed = true;
        }
        int maxCommandNum = 0;
        switch (this.gp.ui.subState) {
            case 0: {
                maxCommandNum = 6;
                break;
            }
            case 3: {
                maxCommandNum = 1;
                break;
            }
            case 4: {
                maxCommandNum = 1;
            }
        }
        if (keyCode == 87 || keyCode == 38) {
            --this.gp.ui.commandNum;
            this.gp.playSE(9);
            if (this.gp.ui.commandNum < 0) {
                this.gp.ui.commandNum = maxCommandNum;
            }
        } else if (keyCode == 83 || keyCode == 40) {
            ++this.gp.ui.commandNum;
            this.gp.playSE(9);
            if (this.gp.ui.commandNum > maxCommandNum) {
                this.gp.ui.commandNum = 0;
            }
        } else if (keyCode == 65 || keyCode == 37) {
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
        } else if (keyCode == 68 || keyCode == 39) {
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

    public void gameOverState(int keyCode) {
        if (keyCode == 87 || keyCode == 38) {
            --this.gp.ui.commandNum;
            this.gp.playSE(9);
            if (this.gp.ui.commandNum < 0) {
                this.gp.ui.commandNum = 1;
            }
        } else if (keyCode == 83 || keyCode == 40) {
            ++this.gp.ui.commandNum;
            this.gp.playSE(9);
            if (this.gp.ui.commandNum > 1) {
                this.gp.ui.commandNum = 0;
            }
        } else if (keyCode == 10) {
            if (this.gp.ui.commandNum == 0) {
                this.gp.startRetryWithLoading();
            } else if (this.gp.ui.commandNum == 1) {
                Objects.requireNonNull(this.gp);
                this.gp.gameState = 0;
                this.gp.restart();
            }
        }
    }

    public void tradeState(int keyCode) {
        if (keyCode == 10) {
            this.enterPressed = true;
        }
        if (this.gp.ui.subState == 0) {
            if (keyCode == 87 || keyCode == 38) {
                --this.gp.ui.commandNum;
                this.gp.playSE(9);
                if (this.gp.ui.commandNum < 0) {
                    this.gp.ui.commandNum = 2;
                }
            } else if (keyCode == 83 || keyCode == 40) {
                ++this.gp.ui.commandNum;
                this.gp.playSE(9);
                if (this.gp.ui.commandNum > 2) {
                    this.gp.ui.commandNum = 0;
                }
            }
        } else if (this.gp.ui.subState == 1) {
            this.npcInventory(keyCode);
            if (keyCode == 27) {
                this.gp.ui.subState = 0;
            }
        } else if (this.gp.ui.subState == 2) {
            this.playerInventory(keyCode);
            if (keyCode == 27) {
                this.gp.ui.subState = 0;
            }
        }
    }

    public void mapState(int keyCode) {
        if (keyCode == 77) {
            Objects.requireNonNull(this.gp);
            this.gp.gameState = 1;
        }
    }

    public void snakeGameState(int keyCode) {
        if (this.gp.snakeGame != null) {
            this.gp.snakeGame.handleInput(keyCode);
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

    public void playerInventory(int keyCode) {
        if (this.gp.ui.inHotbarMode) {
            if (keyCode == 65 || keyCode == 37) {
                if (this.gp.ui.hotbarSlot > 0) {
                    --this.gp.ui.hotbarSlot;
                    this.gp.playSE(9);
                }
            } else if (keyCode == 68 || keyCode == 39) {
                if (this.gp.ui.hotbarSlot < 4) {
                    ++this.gp.ui.hotbarSlot;
                    this.gp.playSE(9);
                }
            } else if (keyCode == 87 || keyCode == 38) {
                this.gp.ui.inHotbarMode = false;
                this.gp.ui.playerSlotRow = 3;
                this.gp.ui.playerSlotCol = this.gp.ui.hotbarSlot;
                this.gp.playSE(9);
            } else if (keyCode == 83 || keyCode == 40) {
                this.gp.ui.inHotbarMode = false;
                this.gp.ui.playerSlotRow = 0;
                this.gp.ui.playerSlotCol = this.gp.ui.hotbarSlot;
                this.gp.playSE(9);
            } else if (keyCode == 10) {
                this.gp.ui.inHotbarAssignmentMode = true;
                this.gp.ui.inHotbarMode = false;
                this.gp.ui.playerSlotCol = 0;
                this.gp.ui.playerSlotRow = 0;
                this.gp.ui.addMessage("Select an item to assign to hot bar slot " + (this.gp.ui.hotbarSlot + 1));
                this.gp.playSE(9);
            }
        } else if (keyCode == 87 || keyCode == 38) {
            if (this.gp.ui.playerSlotRow > 0) {
                --this.gp.ui.playerSlotRow;
                this.gp.playSE(9);
            } else {
                this.gp.ui.inHotbarMode = true;
                this.gp.ui.hotbarSlot = this.gp.ui.playerSlotCol;
                this.gp.playSE(9);
            }
        } else if (keyCode == 83 || keyCode == 40) {
            if (this.gp.ui.playerSlotRow < 3) {
                ++this.gp.ui.playerSlotRow;
                this.gp.playSE(9);
            } else {
                this.gp.ui.inHotbarMode = true;
                this.gp.ui.hotbarSlot = this.gp.ui.playerSlotCol;
                this.gp.playSE(9);
            }
        } else if (keyCode == 65 || keyCode == 37) {
            if (this.gp.ui.playerSlotCol > 0) {
                --this.gp.ui.playerSlotCol;
                this.gp.playSE(9);
            }
        } else if ((keyCode == 68 || keyCode == 39) && this.gp.ui.playerSlotCol < 4) {
            ++this.gp.ui.playerSlotCol;
            this.gp.playSE(9);
        }
    }

    public void npcInventory(int keyCode) {
        if (keyCode == 87 || keyCode == 38) {
            if (this.gp.ui.npcSlotRow != 0) {
                --this.gp.ui.npcSlotRow;
                this.gp.playSE(9);
            }
        } else if (keyCode == 83 || keyCode == 40) {
            if (this.gp.ui.npcSlotRow != 3) {
                ++this.gp.ui.npcSlotRow;
                this.gp.playSE(9);
            }
        } else if (keyCode == 65 || keyCode == 37) {
            if (this.gp.ui.npcSlotCol != 0) {
                --this.gp.ui.npcSlotCol;
                this.gp.playSE(9);
            }
        } else if ((keyCode == 68 || keyCode == 39) && this.gp.ui.npcSlotCol != 4) {
            ++this.gp.ui.npcSlotCol;
            this.gp.playSE(9);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode < this.keyPressed.length) {
            this.keyPressed[keyCode] = false;
        }
        if (keyCode == 16) {
            this.shiftPressed = false;
        }
        if (keyCode == 87 || keyCode == 38) {
            this.upPressed = false;
        } else if (keyCode == 83 || keyCode == 40) {
            this.downPressed = false;
        } else if (keyCode == 65 || keyCode == 37) {
            this.leftPressed = false;
        } else if (keyCode == 68 || keyCode == 39) {
            this.rightPressed = false;
        } else if (keyCode == 70) {
            this.shotKeyPressed = false;
        } else if (keyCode == 10) {
            this.enterPressed = false;
        } else if (keyCode == 32) {
            this.spacePressed = false;
        }
    }

    public void debugState(int keyCode) {
    }
}
