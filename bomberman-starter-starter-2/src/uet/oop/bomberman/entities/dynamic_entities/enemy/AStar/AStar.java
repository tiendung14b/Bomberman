package uet.oop.bomberman.entities.dynamic_entities.enemy.AStar;

import java.util.*;

public class AStar {
    public Node[][] worldMap;
    public PriorityQueue<Node> open;
    public Set<Node> close;
    public Node start;
    public Node destination;

    public Node[][] getWorldMap() {
        return worldMap;
    }

    public void setWorldMap(Node[][] worldMap) {
        this.worldMap = worldMap;
    }

    public PriorityQueue<Node> getOpen() {
        return open;
    }

    public void setOpen(PriorityQueue<Node> open) {
        this.open = open;
    }

    public Set<Node> getClose() {
        return close;
    }

    public void setClose(Set<Node> close) {
        this.close = close;
    }

    public Node getStart() {
        return start;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public Node getDestination() {
        return destination;
    }

    public void setDestination(Node destination) {
        this.destination = destination;
    }

    public AStar() {
    }

    public void createWorldMap(boolean[][] mapping) {
        for (int i = 0; i < this.worldMap.length; i++) {
            for (int j = 0; j < this.worldMap[0].length; j++) {
                Node new_node = new Node(i, j); // set i, j coordinate of node
                new_node.calcH(this.destination);
                new_node.setHasBlock(mapping[i][j]);
                if(new_node.isHasBlock()) {
                    new_node.setG(9999);
                }
                this.worldMap[i][j] = new_node;
            }
        }
    }

    public AStar(boolean[][] mapping, int rows, int cols, Node start, Node destination) {
        this.setStart(start);
        this.setDestination(destination);
        this.setWorldMap(new Node[rows][cols]);
        this.setOpen(new PriorityQueue<>(Comparator.comparingInt(Node::getF)));
        this.createWorldMap(mapping);
        this.setClose(new HashSet<>());
    }

    public void addNode(Node node, int i, int j) {
        if (!this.worldMap[i][j].isHasBlock() && !this.close.contains(worldMap[i][j])) {
            if (!open.contains(this.worldMap[i][j])) {
                worldMap[i][j].setNode(node);
                open.add(worldMap[i][j]);
            } else {
                if (worldMap[i][j].isGoodNode(node)) {
                    open.remove(worldMap[i][j]);
                    open.add(worldMap[i][j]);
                }
            }
        }
    }

    public void exploreUpNodes(Node node) {
        if (node.getI() - 1 >= 0) {
            if (node.getJ() - 1 >= 0) {
                addNode(node, node.getI(), node.getJ() - 1);
            }
            if (node.getJ() + 1 < this.worldMap[0].length) {
                addNode(node, node.getI(), node.getJ() + 1);
            }
            addNode(node, node.getI() - 1, node.getJ());
        }
    }

    public void exploreSideNodes(Node node) {
        if (node.getJ() - 1 >= 0) {
            addNode(node, node.getI(), node.getJ() - 1);
        }
        if (node.getJ() + 1 < this.worldMap[0].length) {
            addNode(node, node.getI(), node.getJ() + 1);
        }
    }

    public void exploreDownNodes(Node node) {
        if (node.getI() + 1 < this.worldMap[0].length) {
            if (node.getJ() - 1 >= 0) {
                addNode(node, node.getI(), node.getJ() - 1);
            }
            if (node.getJ() + 1 < this.worldMap[0].length) {
                addNode(node, node.getI(), node.getJ() + 1);
            }
            addNode(node, node.getI() + 1, node.getJ());
        }
    }

    public void explore(Node node) {
        exploreUpNodes(node);
        exploreSideNodes(node);
        exploreDownNodes(node);
    }

    public List<Node> explorePath(Node node) {
        List<Node> nodeList = new ArrayList<Node>();
        nodeList.add(0, node);
        while (node.parent != null) {
            nodeList.add(node.parent);
            node = node.parent;
        }
        return nodeList;
    }

    public List<Node> getPath() {
        open.add(start);
        while (!open.isEmpty()) {
            Node node = this.open.poll();
            this.close.add(node);
            assert node != null;
            if (node.equals(destination)) {
                return explorePath(node);
            } else {
                explore(node);
            }
        }
        return new ArrayList<Node>();
    }

}