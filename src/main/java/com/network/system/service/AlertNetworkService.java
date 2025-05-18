package com.network.system.service;

import ch.qos.logback.core.joran.sanity.Pair;
import com.network.system.interfaces.AlertNetwork;
import com.network.system.model.Graph;
import com.network.system.model.Node;
import java.util.*;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class AlertNetworkService implements AlertNetwork {

  private final Graph alertNetworkSystem = new Graph();

  public void addService(String service) {
    alertNetworkSystem.addNode(service);
  }

  public void addDependency(String fromService, String toService) {
    alertNetworkSystem.validateNodeExists(fromService);
    alertNetworkSystem.validateNodeExists(toService);
    alertNetworkSystem.addEdge(fromService, toService);
  }

  public List<String> findAlertPropagationPath(String source, String target) {
    alertNetworkSystem.validateNodeExists(source);
    alertNetworkSystem.validateNodeExists(target);
    List<String> alertPropagationPath = new ArrayList<>();
    Stack<Node> stack = new Stack<>();
    stack.add(new Node(source));

    Node currentNode;
    while (!stack.isEmpty()) {
      currentNode = stack.pop();
      alertPropagationPath.add(currentNode.getName());
      if (currentNode.getName().equals(target)) {
        return alertPropagationPath;
      } else {
        List<String> dependencies = getDependencies(currentNode.getName());
        dependencies.removeIf(
            dependency ->
                !(dependency.equals(target) || getAffectedServices(dependency).contains(target)));
        stack.addAll(dependencies.stream().map(Node::new).toList());
      }
    }

    return List.of();
  }

  public List<String> getAffectedServices(String source) {
    alertNetworkSystem.validateNodeExists(source);
    Set<String> affectedServices = new HashSet<>();
    List<String> dependencies = getDependencies(source);
    getAffectedChildrenServices(dependencies, affectedServices);
    return affectedServices.stream().toList();
  }

  public List<String> getDependencies(String service) {
    alertNetworkSystem.validateNodeExists(service);
    return alertNetworkSystem.getAdjacentNodes(service).stream()
        .map(Node::getName)
        .collect(Collectors.toList());
  }

  private void getAffectedChildrenServices(
      List<String> dependencies, Set<String> affectedServices) {
    affectedServices.addAll(dependencies);
    dependencies.forEach(
        dependency -> getAffectedChildrenServices(getDependencies(dependency), affectedServices));
  }

  public List<Pair<String, String>> suggestContainmentEdges(String source) {
    alertNetworkSystem.validateNodeExists(source);

    return List.of();
  }
}
