package com.network.system.service;

import ch.qos.logback.core.joran.sanity.Pair;
import com.network.system.interfaces.AlertNetwork;
import com.network.system.model.Graph;
import com.network.system.model.Node;
import java.util.*;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class AlertNetworkService implements AlertNetwork {

  private final Graph alertNetworkSystem = new Graph();

  public void addService(String service) {
    alertNetworkSystem.addNode(service);
  }

  public void addDependency(String fromService, String toService) {
    alertNetworkSystem.addEdge(fromService, toService);
  }

  public List<String> findAlertPropagationPath(String source, String target) {
    Queue<Node> queue = new ArrayDeque<>();
    queue.add(new Node(source));

    Node currentNode;
    while (!queue.isEmpty()) {
      currentNode = queue.remove();

      // TODO
    }

    return List.of();
  }

  public List<String> getAffectedServices(String source) {
    Set<String> affectedServices = new HashSet<>();
    List<String> dependencies = getDependencies(source);
    getAffectedChildrenServices(dependencies, affectedServices);
    return affectedServices.stream().toList();
  }

  public List<String> getDependencies(String service) {
    return alertNetworkSystem.getAdjacentNodes(service).stream().map(Node::getName).toList();
  }

  private void getAffectedChildrenServices(
      List<String> dependencies, Set<String> affectedServices) {
    affectedServices.addAll(dependencies);
    dependencies.forEach(
        dependency -> getAffectedChildrenServices(getDependencies(dependency), affectedServices));
  }

  public List<Pair<String, String>> suggestContainmentEdges(String source) {
    return List.of();
  }
}
