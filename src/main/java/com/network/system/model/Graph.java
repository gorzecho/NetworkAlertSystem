package com.network.system.model;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.util.*;

public class Graph {
  private Map<Node, List<Node>> adjacentNodes = new HashMap<>();

  public Graph() {}

  public Graph(Map<Node, List<Node>> adjacentNodes) {
    this.adjacentNodes = adjacentNodes;
  }

  public void addNode(String name) {
    adjacentNodes.putIfAbsent(new Node(name), new ArrayList<>());
  }

  public void removeNode(String name) {
    Node node = new Node(name);
    adjacentNodes.values().forEach(e -> e.remove(node));
    adjacentNodes.remove(new Node(name));
  }

  public void addEdge(String name1, String name2) {
    Node node1 = new Node(name1);
    Node node2 = new Node(name2);
    var adjacentNodes1 = adjacentNodes.get(node1);
    //    var adjacentNodes2 = adjacentNodes.get(node2);
    validateNodeExists(name1, adjacentNodes1);
    //    validateNodeExists(name2, adjacentNodes2);
    adjacentNodes1.add(node2);
    //    adjacentNodes2.add(node1);
  }

  private static void validateNodeExists(String name, List<Node> adjacentNodes) {
    if (isNull(adjacentNodes)) {
      throw new IllegalArgumentException("Node %s does not exist".formatted(name));
    }
  }

  public void removeEdge(String name1, String name2) {
    Node node1 = new Node(name1);
    Node node2 = new Node(name2);
    List<Node> adjNodes1 = adjacentNodes.get(node1);
    List<Node> adjNodes2 = adjacentNodes.get(node2);
    if (nonNull(adjNodes1)) {
      adjNodes1.remove(node2);
    }
    if (nonNull(adjNodes2)) {
      adjNodes2.remove(node1);
    }
  }

  public List<Node> getAdjacentNodes(String name) {
    List<Node> adjNodes = adjacentNodes.get(new Node(name));
    return nonNull(adjNodes) ? adjNodes : Collections.emptyList();
  }

  public Map<Node, List<Node>> getAdjacentNodes() {
    return adjacentNodes;
  }

  public void setAdjacentNodes(Map<Node, List<Node>> adjacentNodes) {
    this.adjacentNodes = adjacentNodes;
  }
}
