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

    public PathFinder(GamePanel gamePanel) {
        this.gp = gamePanel;
        this.instantiateNodes();
    }

    public void instantiateNodes() {
        this.node = new Node[this.gp.maxWorldCol][this.gp.maxWorldRow];
        int n = 0;
        int n2 = 0;
        while (n < this.gp.maxWorldCol && n2 < this.gp.maxWorldRow) {
            this.node[n][n2] = new Node(n, n2);
            if (++n != this.gp.maxWorldCol) continue;
            n = 0;
            ++n2;
        }
    }

    public void resetNodes() {
        int n = 0;
        int n2 = 0;
        while (n < this.gp.maxWorldCol && n2 < this.gp.maxWorldRow) {
            this.node[n][n2].open = false;
            this.node[n][n2].checked = false;
            this.node[n][n2].solid = false;
            if (++n != this.gp.maxWorldCol) continue;
            n = 0;
            ++n2;
        }
        this.openList.clear();
        this.pathList.clear();
        this.goalReached = false;
        this.step = 0;
    }

    public void setNodes(int n, int n2, int n3, int n4, Entity entity) {
        this.resetNodes();
        this.currentNode = this.startNode = this.node[n][n2];
        this.goalNode = this.node[n3][n4];
        this.openList.add(this.currentNode);
        int n5 = 0;
        int n6 = 0;
        while (n5 < this.gp.maxWorldCol && n6 < this.gp.maxWorldRow) {
            int n7 = this.gp.tileM.mapTileNum[this.gp.currentMap][n5][n6];
            if (this.gp.tileM.tile[n7].collision) {
                this.node[n5][n6].solid = true;
            }
            for (int i = 0; i < this.gp.iTile[1].length; ++i) {
                if (this.gp.iTile[this.gp.currentMap][i] == null || !this.gp.iTile[this.gp.currentMap][i].destructible) continue;
                int n8 = this.gp.iTile[this.gp.currentMap][i].worldX / this.gp.tileSize;
                int n9 = this.gp.iTile[this.gp.currentMap][i].worldY / this.gp.tileSize;
                this.node[n8][n9].solid = true;
            }
            this.getCost(this.node[n5][n6]);
            if (++n5 != this.gp.maxWorldCol) continue;
            n5 = 0;
            ++n6;
        }
    }

    public void getCost(Node node) {
        int n = Math.abs(node.col - this.startNode.col);
        int n2 = Math.abs(node.row - this.startNode.row);
        node.gCost = n + n2;
        n = Math.abs(node.col - this.goalNode.col);
        n2 = Math.abs(node.row - this.goalNode.row);
        node.hCost = n + n2;
        node.fCost = node.gCost + node.hCost;
    }

    public boolean search() {
        while (!this.goalReached && this.step < 500) {
            int n = this.currentNode.col;
            int n2 = this.currentNode.row;
            this.currentNode.checked = true;
            this.openList.remove(this.currentNode);
            if (n2 - 1 >= 0) {
                this.openNode(this.node[n][n2 - 1]);
            }
            if (n - 1 >= 0) {
                this.openNode(this.node[n - 1][n2]);
            }
            if (n2 + 1 < this.gp.maxWorldRow) {
                this.openNode(this.node[n][n2 + 1]);
            }
            if (n + 1 < this.gp.maxWorldCol) {
                this.openNode(this.node[n + 1][n2]);
            }
            int n3 = 0;
            int n4 = 999;
            for (int i = 0; i < this.openList.size(); ++i) {
                if (this.openList.get((int)i).fCost < n4) {
                    n3 = i;
                    n4 = this.openList.get((int)i).fCost;
                    continue;
                }
                if (this.openList.get((int)i).fCost != n4 || this.openList.get((int)i).gCost >= this.openList.get((int)n3).gCost) continue;
                n3 = i;
            }
            if (this.openList.size() == 0) break;
            this.currentNode = this.openList.get(n3);
            if (this.currentNode == this.goalNode) {
                this.goalReached = true;
                this.trackThePath();
            }
            ++this.step;
        }
        return this.goalReached;
    }

    public void openNode(Node node) {
        if (!(node.open || node.checked || node.solid)) {
            node.open = true;
            node.parent = this.currentNode;
            this.openList.add(node);
        }
    }

    public void trackThePath() {
        Node node = this.goalNode;
        while (node != this.startNode) {
            this.pathList.add(0, node);
            node = node.parent;
        }
    }
}
