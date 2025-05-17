package com.network.system.model;

import java.util.Objects;

public class Node {
  String name;

  public Node(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Node node = (Node) o;
    return Objects.equals(name, node.name);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(name);
  }
}
