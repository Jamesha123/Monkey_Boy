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

    public Lighting(GamePanel gamePanel) {
        this.gp = gamePanel;
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
        boolean bl;
        this.playerTileCol = this.gp.player.worldX / this.gp.tileSize;
        this.playerTileRow = this.gp.player.worldY / this.gp.tileSize;
        this.playerLightRadius = this.gp.player.currentLight != null ? this.gp.player.currentLight.lightRadius : -1;
        this.torchHash = this.calculateTorchLayoutHash();
        boolean bl2 = this.playerTileCol != this.lastPlayerTileCol || this.playerTileRow != this.lastPlayerTileRow;
        boolean bl3 = this.playerLightRadius != this.lastPlayerLightRadius;
        boolean bl4 = this.dayState != this.lastDayState;
        boolean bl5 = this.filterAlpha != this.lastFilterAlpha;
        boolean bl6 = bl = this.torchHash != this.lastTorchLayoutHash;
        if (bl2 || bl3 || bl4 || bl5 || bl || this.dirty) {
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
        long l = 1469598103934665603L;
        for (int i = 0; i < this.gp.iTile[1].length; ++i) {
            if (this.gp.iTile[this.gp.currentMap][i] == null || this.gp.iTile[this.gp.currentMap][i].name == null || !this.gp.iTile[this.gp.currentMap][i].name.equals("Torch")) continue;
            InteractiveTile interactiveTile = this.gp.iTile[this.gp.currentMap][i];
            long l2 = (long)interactiveTile.worldX * 31L + (long)interactiveTile.worldY * 131L + (long)interactiveTile.lightRadius * 997L;
            l ^= l2;
            l *= 1099511628211L;
        }
        return l;
    }

    public void removeLight(int n, int n2, int n3) {
        for (int i = 0; i < this.gp.iTile[1].length; ++i) {
            if (this.gp.iTile[this.gp.currentMap][i] == null || this.gp.iTile[this.gp.currentMap][i].getCol() != n2 || this.gp.iTile[this.gp.currentMap][i].getRow() != n3) continue;
            this.gp.iTile[this.gp.currentMap][i] = null;
        }
        this.dirty = true;
    }

    private boolean drawDebugDayStateIfEnabled(Graphics2D graphics2D) {
        if (!this.gp.debugManager.darknessFilterOff) {
            return false;
        }
        String string = "";
        switch (this.dayState) {
            case 0: {
                string = "Day";
                break;
            }
            case 1: {
                string = "Dusk";
                break;
            }
            case 2: {
                string = "Night";
                break;
            }
            case 3: {
                string = "Dawn";
            }
        }
        graphics2D.setFont(this.gp.ui.fontBold20);
        graphics2D.drawString(string, 920, 500);
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
        int n = this.gp.currentArea;
        Objects.requireNonNull(this.gp);
        if (n == 1) {
            graphics2D.setComposite(AlphaComposite.getInstance(3, this.filterAlpha));
        }
    }

    private void drawDarknessFilterWithPlayerOffset(Graphics2D graphics2D) {
        block3: {
            block2: {
                int n = this.gp.currentArea;
                Objects.requireNonNull(this.gp);
                if (n == 1) break block2;
                int n2 = this.gp.currentArea;
                Objects.requireNonNull(this.gp);
                if (n2 != 3) break block3;
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
        RadialGradientPaint radialGradientPaint = new RadialGradientPaint(this.centerX, (float)this.centerY, this.gp.player.currentLight.lightRadius / 2, this.gradientFractions, this.gradientColors);
        graphics2D.setPaint(radialGradientPaint);
        graphics2D.fillRect(0, 0, this.darknessFilter.getWidth(), this.darknessFilter.getHeight());
    }

    private void buildTorchLights(Graphics2D graphics2D) {
        for (int i = 0; i < this.gp.iTile[1].length; ++i) {
            if (this.gp.iTile[this.gp.currentMap][i] == null || this.gp.iTile[this.gp.currentMap][i].name == null || !this.gp.iTile[this.gp.currentMap][i].name.equals("Torch")) continue;
            InteractiveTile interactiveTile = this.gp.iTile[this.gp.currentMap][i];
            this.centerX = this.padding + interactiveTile.getCenterX() - this.gp.player.worldX + this.gp.player.screenX;
            this.centerY = this.padding + interactiveTile.getCenterY() - this.gp.player.worldY + this.gp.player.screenY;
            RadialGradientPaint radialGradientPaint = new RadialGradientPaint(this.centerX, (float)this.centerY, interactiveTile.lightRadius, this.gradientFractions, this.gradientColors);
            graphics2D.setPaint(radialGradientPaint);
            graphics2D.fillRect(0, 0, this.darknessFilter.getWidth(), this.darknessFilter.getHeight());
        }
    }

    /*
     * Unable to fully structure code
     */
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
        v0 = this.gp.currentArea;
        Objects.requireNonNull(this.gp);
        if (v0 != 3) {
            this.filterAlpha += 0.005f;
            if (this.filterAlpha > 1.0f) {
                this.filterAlpha = 1.0f;
                this.dayState = 2;
            }
        } else if (this.dayState == 1) {
            v1 = this.gp.currentArea;
            Objects.requireNonNull(this.gp);
            if (v1 == 3) {
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
        v2 = this.gp.currentArea;
        Objects.requireNonNull(this.gp);
        if (v2 != 3) {
            this.filterAlpha -= 0.005f;
            if (this.filterAlpha < 0.0f) {
                this.filterAlpha = 0.0f;
                this.dayState = 0;
            }
        } else if (this.dayState == 3) {
            v3 = this.gp.currentArea;
            Objects.requireNonNull(this.gp);
            if (v3 == 3) {
                ++this.dayCounter;
                if (this.dayCounter > 3600) {
                    this.dayState = 2;
                    this.dayCounter = 0;
                }
            }
        }
    }
}
