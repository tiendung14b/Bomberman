package uet.oop.bomberman.entities.dynamic_entities.enemy.AStar;

public class Node {
    public int g; // chi phi hien tai
    public int h; // "heuristic" - chi phi hien tai -> dich
    public int f; // ham tinh chi phi tong, cang nho thi cang duoc uu tien chon
    public int i;
    public int j;
    public boolean hasBlock;
    public Node parent;

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public boolean isHasBlock() {
        return hasBlock;
    }

    public void setHasBlock(boolean hasBlock) {
        this.hasBlock = hasBlock;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node(int i, int j) {
        this.setI(i);
        this.setJ(j);
    }

    public void calcH(Node destination) {
        this.h = (int)Math.abs(Math.pow(destination.getI() - this.getI(), 2) + Math.pow(destination.getJ() - this.getJ(), 2));
    }

    public void calcF() {
        this.setF(this.getG() + this.getH());
    }

    public void setNode(Node parent) {
        this.setG(parent.getG());
        this.setParent(parent);
        calcF();
    }

    public boolean isGoodNode(Node parent) {
        if(parent.getG() > this.getG()) {
            return false;
        }
        this.setNode(parent);
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        Node otherNode = (Node) obj;
        return this.getI() == ((Node) obj).getI() && this.getJ() == ((Node) obj).getJ();
    }
}