/*
 * Decompiled with CFR 0.152.
 */
package environment;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import main.GamePanel;
import tile_interactive.InteractiveTile;

public class Lighting {
    GamePanel gp;
    BufferedImage darknessFilter;
    public int dayCounter;
    public float filterAlpha = 0.0f;
    private int lastPlayerTileCol = -1;
    private int lastPlayerTileRow = -1;
    private int lastPlayerLightRadius = -1;
    private int lastDayState = -1;
    private float lastFilterAlpha = -1.0f;
    private long lastTorchLayoutHash = 0L;
    private boolean dirty = true;
    private int lastPlayerWorldX = 0;
    private int lastPlayerWorldY = 0;
    public final int day = 0;
    public final int dusk = 1;
    public final int night = 2;
    public final int dawn = 3;
    public int dayState = 0;
    private RenderingHints renderingHintsBackup;
    private Map<RenderingHints.Key, Object> fastLightingHints = new HashMap<RenderingHints.Key, Object>();
    private int padding;
    private int offsetX;
    private int offsetY;
    private int playerTileCol;
    private int playerTileRow;
    private int playerLightRadius;
    private long torchHash;
    private Color[] gradientColors = new Color[4];
    private float[] gradientFractions = new float[4];
    private int centerX;
    private int centerY;

    public Lighting(GamePanel gp) {
        this.gp = gp;
        this.setLightSource();
    }

    public boolean isNightTime() {
        return this.dayState == 2 || this.dayState == 1;
    }

    public void draw(Graphics2D graphics2D) {
        if (this.drawDebugDayStateIfEnabled(graphics2D)) {
            return;
        }
        this.backupRenderingHints(graphics2D);
        this.applyFastLightingHints(graphics2D);
        this.setOutsideComposite(graphics2D);
        this.drawDarknessFilterWithPlayerOffset(graphics2D);
        this.restoreDefaultComposite(graphics2D);
        this.restoreRenderingHints(graphics2D);
    }

    public void setLightSource() {
        this.padding = this.gp.tileSize;
        this.darknessFilter = new BufferedImage(this.gp.screenWidth + this.padding * 2, this.gp.screenHeight + this.padding * 2, 2);
        Graphics2D graphics2D = (Graphics2D)this.darknessFilter.getGraphics();
        this.fillDarkness(graphics2D);
        this.useDestinationOutComposite(graphics2D);
        this.prepareGradientStops();
        this.buildPlayerLight(graphics2D);
        this.buildTorchLights(graphics2D);
        graphics2D.dispose();
    }

    public void resetDay() {
        this.dayState = 0;
        this.filterAlpha = 0.0f;
    }

    public void update() {
        this.playerTileCol = this.gp.player.worldX / this.gp.tileSize;
        this.playerTileRow = this.gp.player.worldY / this.gp.tileSize;
        this.playerLightRadius = this.gp.player.currentLight != null ? this.gp.player.currentLight.lightRadius : -1;
        this.torchHash = this.calculateTorchLayoutHash();
        boolean playerTileChanged = this.playerTileCol != this.lastPlayerTileCol || this.playerTileRow != this.lastPlayerTileRow;
        boolean lightRadiusChanged = this.playerLightRadius != this.lastPlayerLightRadius;
        boolean dayStateChanged = this.dayState != this.lastDayState;
        boolean filterAlphaChanged = this.filterAlpha != this.lastFilterAlpha;
        boolean torchLayoutChanged = this.torchHash != this.lastTorchLayoutHash;
        if (playerTileChanged || lightRadiusChanged || dayStateChanged || filterAlphaChanged || torchLayoutChanged || this.dirty) {
            this.setLightSource();
            this.dirty = false;
            this.lastPlayerTileCol = this.playerTileCol;
            this.lastPlayerTileRow = this.playerTileRow;
            this.lastPlayerLightRadius = this.playerLightRadius;
            this.lastDayState = this.dayState;
            this.lastFilterAlpha = this.filterAlpha;
            this.lastTorchLayoutHash = this.torchHash;
            this.lastPlayerWorldX = this.gp.player.worldX;
            this.lastPlayerWorldY = this.gp.player.worldY;
        }
        this.updateDayNightCycle();
    }

    private long calculateTorchLayoutHash() {
        long hash = 1469598103934665603L;
        for (int i = 0; i < this.gp.iTile[1].length; ++i) {
            if (this.gp.iTile[this.gp.currentMap][i] == null || this.gp.iTile[this.gp.currentMap][i].name == null || !this.gp.iTile[this.gp.currentMap][i].name.equals("Torch")) continue;
            InteractiveTile torchTile = this.gp.iTile[this.gp.currentMap][i];
            long torchContribution = (long)torchTile.worldX * 31L + (long)torchTile.worldY * 131L + (long)torchTile.lightRadius * 997L;
            hash ^= torchContribution;
            hash *= 1099511628211L;
        }
        return hash;
    }

    public void removeLight(int mapIndex, int col, int row) {
        for (int i = 0; i < this.gp.iTile[1].length; ++i) {
            if (this.gp.iTile[this.gp.currentMap][i] == null || this.gp.iTile[this.gp.currentMap][i].getCol() != col || this.gp.iTile[this.gp.currentMap][i].getRow() != row) continue;
            this.gp.iTile[this.gp.currentMap][i] = null;
        }
        this.dirty = true;
    }

    private boolean drawDebugDayStateIfEnabled(Graphics2D graphics2D) {
        if (!this.gp.debugManager.darknessFilterOff) {
            return false;
        }
        String dayStateLabel = "";
        switch (this.dayState) {
            case 0: {
                dayStateLabel = "Day";
                break;
            }
            case 1: {
                dayStateLabel = "Dusk";
                break;
            }
            case 2: {
                dayStateLabel = "Night";
                break;
            }
            case 3: {
                dayStateLabel = "Dawn";
            }
        }
        graphics2D.setFont(this.gp.ui.fontBold20);
        graphics2D.drawString(dayStateLabel, 920, 500);
        return true;
    }

    private void backupRenderingHints(Graphics2D graphics2D) {
        this.renderingHintsBackup = graphics2D.getRenderingHints();
    }

    private void applyFastLightingHints(Graphics2D graphics2D) {
        this.fastLightingHints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        this.fastLightingHints.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
        this.fastLightingHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
        graphics2D.addRenderingHints(this.fastLightingHints);
    }

    private void setOutsideComposite(Graphics2D graphics2D) {
        int currentArea = this.gp.currentArea;
        Objects.requireNonNull(this.gp);
        if (currentArea == 1) {
            graphics2D.setComposite(AlphaComposite.getInstance(3, this.filterAlpha));
        }
    }

    private void drawDarknessFilterWithPlayerOffset(Graphics2D graphics2D) {
        block3: {
            block2: {
                int currentArea = this.gp.currentArea;
                Objects.requireNonNull(this.gp);
                if (currentArea == 1) break block2;
                int areaCheck = this.gp.currentArea;
                Objects.requireNonNull(this.gp);
                if (areaCheck != 3) break block3;
            }
            this.padding = this.gp.tileSize;
            this.offsetX = (this.lastPlayerWorldX - this.gp.player.worldX) % this.gp.tileSize - this.padding;
            this.offsetY = (this.lastPlayerWorldY - this.gp.player.worldY) % this.gp.tileSize - this.padding;
            graphics2D.drawImage((Image)this.darknessFilter, this.offsetX, this.offsetY, null);
        }
    }

    private void restoreDefaultComposite(Graphics2D graphics2D) {
        graphics2D.setComposite(AlphaComposite.getInstance(3, 1.0f));
    }

    private void restoreRenderingHints(Graphics2D graphics2D) {
        graphics2D.setRenderingHints(this.renderingHintsBackup);
    }

    private void fillDarkness(Graphics2D graphics2D) {
        graphics2D.setColor(new Color(0.0f, 0.0f, 0.1f, 0.9f));
        graphics2D.fillRect(0, 0, this.darknessFilter.getWidth(), this.darknessFilter.getHeight());
    }

    private void useDestinationOutComposite(Graphics2D graphics2D) {
        graphics2D.setComposite(AlphaComposite.getInstance(8, 1.0f));
    }

    private void prepareGradientStops() {
        this.gradientFractions[0] = 0.0f;
        this.gradientFractions[1] = 0.6f;
        this.gradientFractions[2] = 0.85f;
        this.gradientFractions[3] = 1.0f;
        this.gradientColors[0] = new Color(0.0f, 0.0f, 0.0f, 1.0f);
        this.gradientColors[1] = new Color(0.0f, 0.0f, 0.0f, 0.7f);
        this.gradientColors[2] = new Color(0.0f, 0.0f, 0.0f, 0.25f);
        this.gradientColors[3] = new Color(0.0f, 0.0f, 0.0f, 0.0f);
    }

    private void buildPlayerLight(Graphics2D graphics2D) {
        if (this.gp.player.currentLight == null) {
            return;
        }
        this.centerX = this.padding + this.gp.player.screenX + this.gp.tileSize / 2;
        this.centerY = this.padding + this.gp.player.screenY + this.gp.tileSize / 2;
        RadialGradientPaint playerLightGradient = new RadialGradientPaint(this.centerX, (float)this.centerY, this.gp.player.currentLight.lightRadius / 2, this.gradientFractions, this.gradientColors);
        graphics2D.setPaint(playerLightGradient);
        graphics2D.fillRect(0, 0, this.darknessFilter.getWidth(), this.darknessFilter.getHeight());
    }

    private void buildTorchLights(Graphics2D graphics2D) {
        for (int i = 0; i < this.gp.iTile[1].length; ++i) {
            if (this.gp.iTile[this.gp.currentMap][i] == null || this.gp.iTile[this.gp.currentMap][i].name == null || !this.gp.iTile[this.gp.currentMap][i].name.equals("Torch")) continue;
            InteractiveTile torchTile = this.gp.iTile[this.gp.currentMap][i];
            this.centerX = this.padding + torchTile.getCenterX() - this.gp.player.worldX + this.gp.player.screenX;
            this.centerY = this.padding + torchTile.getCenterY() - this.gp.player.worldY + this.gp.player.screenY;
            RadialGradientPaint torchLightGradient = new RadialGradientPaint(this.centerX, (float)this.centerY, torchTile.lightRadius, this.gradientFractions, this.gradientColors);
            graphics2D.setPaint(torchLightGradient);
            graphics2D.fillRect(0, 0, this.darknessFilter.getWidth(), this.darknessFilter.getHeight());
        }
    }

    private void updateDayNightCycle() {
        if (this.dayState == 0) {
            this.filterAlpha = 0.0f;
            ++this.dayCounter;
            if (this.dayCounter > 18000) {
                this.dayState = 1;
                this.dayCounter = 0;
            }
        }
        if (this.dayState == 1) {
            if (this.gp.currentArea != this.gp.dungeon) {
                this.filterAlpha += 0.005f;
                if (this.filterAlpha > 1.0f) {
                    this.filterAlpha = 1.0f;
                    this.dayState = 2;
                }
            } else {
                ++this.dayCounter;
                if (this.dayCounter > 3600) {
                    this.dayState = 2;
                    this.dayCounter = 0;
                }
            }
        }
        if (this.dayState == 2) {
            this.filterAlpha = 1.0f;
            ++this.dayCounter;
            if (this.dayCounter > 10800) {
                this.dayState = 3;
                this.dayCounter = 0;
            }
        }
        if (this.dayState == 3) {
            if (this.gp.currentArea != this.gp.dungeon) {
                this.filterAlpha -= 0.005f;
                if (this.filterAlpha < 0.0f) {
                    this.filterAlpha = 0.0f;
                    this.dayState = 0;
                }
            } else {
                ++this.dayCounter;
                if (this.dayCounter > 3600) {
                    this.dayState = 2;
                    this.dayCounter = 0;
                }
            }
        }
    }
}
