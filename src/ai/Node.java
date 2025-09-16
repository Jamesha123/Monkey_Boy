/*
 * Decompiled with CFR 0.152.
 */
package ai;

public class Node {
    Node parent;
    public int col;
    public int row;
    int gCost;
    int hCost;
    int fCost;
    boolean solid;
    boolean open;
    boolean checked;

    public Node(int n, int n2) {
        this.col = n;
        this.row = n2;
    }
}
