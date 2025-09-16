/*
 * Decompiled with CFR 0.152.
 */
package spellBook;

import entity.Entity;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import main.GamePanel;
import main.KeyHandler;

public class snakeGame {
    GamePanel gp;
    KeyHandler keyH;
    private boolean gameActive = false;
    private boolean gameWon = false;
    private boolean gameLost = false;
    private boolean hasStarted = false;
    private ArrayList<Point> snake;
    private Point direction;
    private int snakeSize;
    private final int CELL_SIZE = 20;
    private final int GAME_WIDTH = 14;
    private final int GAME_HEIGHT = 18;
    private final int OFFSET_X = 330;
    private final int OFFSET_Y = 100;
    private long lastUpdate = 0L;
    private final long UPDATE_DELAY = 100L;
    private ArrayList<Point> patternDots;
    private int coveredDots = 0;
    private int totalDots;
    private String spellName;

    public snakeGame(GamePanel gamePanel, KeyHandler keyHandler, String string) {
        this.gp = gamePanel;
        this.keyH = keyHandler;
        this.spellName = string;
        this.initializeGame();
    }

    public void initializeGame() {
        this.initializePattern();
        this.snake = new ArrayList();
        int n = 7;
        int n2 = 9;
        this.snake.add(new Point(n, n2));
        this.direction = new Point(0, -1);
        this.gameActive = true;
        this.gameWon = false;
        this.gameLost = false;
        this.hasStarted = false;
        this.coveredDots = 0;
        this.lastUpdate = System.currentTimeMillis();
    }

    private void initializePattern() {
        this.patternDots = new ArrayList();
        if ("fireball".equalsIgnoreCase(this.spellName)) {
            this.initializeFireballPattern();
        }
    }

    private void initializeFireballPattern() {
        this.totalDots = 10;
        this.snakeSize = 13;
        this.patternDots.add(new Point(7, 8));
        this.patternDots.add(new Point(7, 10));
        this.patternDots.add(new Point(6, 10));
        this.patternDots.add(new Point(5, 10));
        this.patternDots.add(new Point(4, 10));
        this.patternDots.add(new Point(5, 9));
        this.patternDots.add(new Point(9, 9));
        this.patternDots.add(new Point(6, 8));
        this.patternDots.add(new Point(8, 8));
        this.patternDots.add(new Point(8, 10));
    }

    public void update() {
        if (!this.gameActive) {
            return;
        }
        if (!this.hasStarted) {
            return;
        }
        long l = System.currentTimeMillis();
        if (l - this.lastUpdate < 100L) {
            return;
        }
        this.lastUpdate = l;
        this.moveSnake();
        this.checkCollisions();
        this.checkWinCondition();
    }

    private void moveSnake() {
        Point point = this.snake.get(0);
        Point point2 = new Point(point.x + this.direction.x, point.y + this.direction.y);
        this.snake.add(0, point2);
        if (this.snake.size() > this.snakeSize) {
            this.snake.remove(this.snake.size() - 1);
        }
    }

    private void checkCollisions() {
        Point point = this.snake.get(0);
        if (point.x < 0 || point.x >= 14 || point.y < 0 || point.y >= 18) {
            this.gameLost = true;
            this.gameActive = false;
            return;
        }
        for (int i = 1; i < this.snake.size(); ++i) {
            if (!point.equals(this.snake.get(i))) continue;
            this.gameLost = true;
            this.gameActive = false;
            return;
        }
    }

    private void checkWinCondition() {
        this.coveredDots = 0;
        block0: for (Point point : this.patternDots) {
            for (Point point2 : this.snake) {
                if (!point.equals(point2)) continue;
                ++this.coveredDots;
                continue block0;
            }
        }
        if (this.coveredDots >= this.totalDots) {
            this.gameWon = true;
            this.gameActive = false;
        }
    }

    public void handleInput(int n) {
        if (!this.gameActive) {
            return;
        }
        if (this.gameWon) {
            if (n == 10) {
                this.gameActive = false;
            }
            return;
        }
        switch (n) {
            case 38: 
            case 87: {
                if (this.direction.y == 1) break;
                this.direction.setLocation(0, -1);
                this.hasStarted = true;
                break;
            }
            case 40: 
            case 83: {
                if (this.direction.y == -1) break;
                this.direction.setLocation(0, 1);
                this.hasStarted = true;
                break;
            }
            case 37: 
            case 65: {
                if (this.direction.x == 1) break;
                this.direction.setLocation(-1, 0);
                this.hasStarted = true;
                break;
            }
            case 39: 
            case 68: {
                if (this.direction.x == -1) break;
                this.direction.setLocation(1, 0);
                this.hasStarted = true;
                break;
            }
            case 27: {
                this.gameActive = false;
            }
        }
    }

    public void draw(Graphics2D graphics2D) {
        int n;
        graphics2D.setColor(new Color(170, 75, 20, 120));
        graphics2D.fillRoundRect(320, 90, 300, 380, 10, 10);
        graphics2D.setColor(new Color(255, 165, 0, 180));
        graphics2D.setStroke(new BasicStroke(2.0f));
        graphics2D.drawRoundRect(325, 95, 290, 370, 10, 10);
        graphics2D.setColor(Color.yellow);
        for (Point point : this.patternDots) {
            graphics2D.fillOval(330 + point.x * 20 + 4, 100 + point.y * 20 + 4, 12, 12);
        }
        graphics2D.setColor(Color.cyan);
        for (n = 0; n < this.coveredDots; ++n) {
            int n2 = 330 + n * 15;
            int n3 = 100;
            graphics2D.fillOval(n2 + 4, n3 + 4, 12, 12);
        }
        graphics2D.setColor(new Color(128, 0, 128));
        for (n = 0; n < this.snake.size(); ++n) {
            Point point = this.snake.get(n);
            graphics2D.fillOval(330 + point.x * 20 + 1, 100 + point.y * 20 + 1, 18, 18);
        }
        if (this.gameWon || this.gameLost) {
            this.drawGameOverScreen(graphics2D);
        }
    }

    private void drawGameOverScreen(Graphics2D graphics2D) {
        graphics2D.setColor(new Color(0, 0, 0, 180));
        graphics2D.fillRect(320, 90, 300, 380);
        graphics2D.setColor(Color.WHITE);
        graphics2D.setFont(graphics2D.getFont().deriveFont(1, 24.0f));
        if (this.gameWon) {
            switch (this.spellName) {
                case "fireball": {
                    int n = this.gp.player.searchItemInInventory("Book of Fireball Magic");
                    this.gp.player.startDialogue((Entity)this.gp.player.inventory.get(n), 0);
                    this.gp.playSE(2);
                    this.gp.player.maxMana += 5;
                    this.gp.player.mana = this.gp.player.maxMana;
                    this.gp.player.inventory.remove(n);
                    this.gp.snakeGame = null;
                }
            }
        } else {
            String string = "Failure!";
            int n = 470 - graphics2D.getFontMetrics().stringWidth(string) / 2;
            int n2 = 280;
            graphics2D.drawString(string, n, n2);
        }
    }

    public boolean isGameActive() {
        return this.gameActive;
    }

    public boolean isGameWon() {
        return this.gameWon;
    }

    public boolean isGameLost() {
        return this.gameLost;
    }

    public void resetGame() {
        this.initializeGame();
    }

    public void closeGame() {
        this.gameActive = false;
    }
}
