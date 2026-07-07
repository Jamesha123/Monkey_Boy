/*
 * Decompiled with CFR 0.152.
 */
package ai;

import ai.Node;
import entity.Entity;
import java.util.ArrayList;
import main.GamePanel;

public class PathFinder {
    GamePanel gp;
    Node[][] node;
    ArrayList<Node> openList = new ArrayList();
    public ArrayList<Node> pathList = new ArrayList();
    Node startNode;
    Node goalNode;
    Node currentNode;
    boolean goalReached = false;
    int step = 0;

    public PathFinder(GamePanel gp) {
        this.gp = gp;
        this.instantiateNodes();
    }

    public void instantiateNodes() {
        this.node = new Node[this.gp.maxWorldCol][this.gp.maxWorldRow];
        int col = 0;
        int row = 0;
        while (col < this.gp.maxWorldCol && row < this.gp.maxWorldRow) {
            this.node[col][row] = new Node(col, row);
            if (++col != this.gp.maxWorldCol) continue;
            col = 0;
            ++row;
        }
    }

    public void resetNodes() {
        int col = 0;
        int row = 0;
        while (col < this.gp.maxWorldCol && row < this.gp.maxWorldRow) {
            this.node[col][row].open = false;
            this.node[col][row].checked = false;
            this.node[col][row].solid = false;
            if (++col != this.gp.maxWorldCol) continue;
            col = 0;
            ++row;
        }
        this.openList.clear();
        this.pathList.clear();
        this.goalReached = false;
        this.step = 0;
    }

    public void setNodes(int startCol, int startRow, int goalCol, int goalRow, Entity targetEntity) {
        this.resetNodes();
        this.currentNode = this.startNode = this.node[startCol][startRow];
        this.goalNode = this.node[goalCol][goalRow];
        this.openList.add(this.currentNode);
        int col = 0;
        int row = 0;
        while (col < this.gp.maxWorldCol && row < this.gp.maxWorldRow) {
            int tileIndex = this.gp.tileM.mapTileNum[this.gp.currentMap][col][row];
            if (this.gp.tileM.tile[tileIndex].collision) {
                this.node[col][row].solid = true;
            }
            for (int i = 0; i < this.gp.iTile[1].length; ++i) {
                if (this.gp.iTile[this.gp.currentMap][i] == null || !this.gp.iTile[this.gp.currentMap][i].destructible) continue;
                int tileCol = this.gp.iTile[this.gp.currentMap][i].worldX / this.gp.tileSize;
                int tileRow = this.gp.iTile[this.gp.currentMap][i].worldY / this.gp.tileSize;
                this.node[tileCol][tileRow].solid = true;
            }
            this.getCost(this.node[col][row]);
            if (++col != this.gp.maxWorldCol) continue;
            col = 0;
            ++row;
        }
    }

    public void getCost(Node node) {
        int deltaCol = Math.abs(node.col - this.startNode.col);
        int deltaRow = Math.abs(node.row - this.startNode.row);
        node.gCost = deltaCol + deltaRow;
        deltaCol = Math.abs(node.col - this.goalNode.col);
        deltaRow = Math.abs(node.row - this.goalNode.row);
        node.hCost = deltaCol + deltaRow;
        node.fCost = node.gCost + node.hCost;
    }

    public boolean search() {
        while (!this.goalReached && this.step < 500) {
            int currentCol = this.currentNode.col;
            int currentRow = this.currentNode.row;
            this.currentNode.checked = true;
            this.openList.remove(this.currentNode);
            if (currentRow - 1 >= 0) {
                this.openNode(this.node[currentCol][currentRow - 1]);
            }
            if (currentCol - 1 >= 0) {
                this.openNode(this.node[currentCol - 1][currentRow]);
            }
            if (currentRow + 1 < this.gp.maxWorldRow) {
                this.openNode(this.node[currentCol][currentRow + 1]);
            }
            if (currentCol + 1 < this.gp.maxWorldCol) {
                this.openNode(this.node[currentCol + 1][currentRow]);
            }
            int bestNodeIndex = 0;
            int lowestFCost = 999;
            for (int i = 0; i < this.openList.size(); ++i) {
                if (this.openList.get((int)i).fCost < lowestFCost) {
                    bestNodeIndex = i;
                    lowestFCost = this.openList.get((int)i).fCost;
                    continue;
                }
                if (this.openList.get((int)i).fCost != lowestFCost || this.openList.get((int)i).gCost >= this.openList.get((int)bestNodeIndex).gCost) continue;
                bestNodeIndex = i;
            }
            if (this.openList.size() == 0) break;
            this.currentNode = this.openList.get(bestNodeIndex);
            if (this.currentNode == this.goalNode) {
                this.goalReached = true;
                this.trackThePath();
            }
            ++this.step;
        }
        return this.goalReached;
    }

    public void openNode(Node neighborNode) {
        if (!(neighborNode.open || neighborNode.checked || neighborNode.solid)) {
            neighborNode.open = true;
            neighborNode.parent = this.currentNode;
            this.openList.add(neighborNode);
        }
    }

    public void trackThePath() {
        Node pathNode = this.goalNode;
        while (pathNode != this.startNode) {
            this.pathList.add(0, pathNode);
            pathNode = pathNode.parent;
        }
    }
}
