package com.network.system.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class AlertNetworkServiceTest {

  static AlertNetworkService alertNetworkService = new AlertNetworkService();

  @BeforeAll
  public static void testSetup() {
    alertNetworkService.addService("A");
    alertNetworkService.addService("B");
    alertNetworkService.addService("C");
    alertNetworkService.addService("D");
    alertNetworkService.addDependency("A", "B");
    alertNetworkService.addDependency("B", "C");
    alertNetworkService.addDependency("A", "D");
    alertNetworkService.addDependency("D", "C");
  }

  @Test
  public void findAlertPropagationPathShouldReturnValidPath() {
    // given // when
    var alertPropagationPath = alertNetworkService.findAlertPropagationPath("A", "C");
    // then
    assertEquals(List.of("A", "B", "C"), alertPropagationPath);
  }

  @Test
  public void findAlertPropagationPathShouldReturnEmptyListWhenThereIsNoPathBetweenTwoSystems() {
    // given // when
    var alertPropagationPath = alertNetworkService.findAlertPropagationPath("B", "D");
    // then
    assertEquals(Collections.emptyList(), alertPropagationPath);
  }

  @Test
  public void getAffectedServicesShouldReturnAllAffectedServices() {
    // given // when
    var affectedServices = alertNetworkService.getAffectedServices("A");
    // then
    assertEquals(List.of("B", "C", "D"), affectedServices);
  }

  @Test
  public void getAffectedServicesShouldReturnEmptyListForServiceWithoutDependencies() {
    // given // when
    var affectedServices = alertNetworkService.getAffectedServices("C");
    // then
    assertEquals(List.of(), affectedServices);
  }

  @Test
  public void testSuggestContainmentEdges() {
    // given // when
    var containmentEdges = alertNetworkService.suggestContainmentEdges("A");
    // then
    assertEquals(List.of("B", "C"), containmentEdges);
  }
}
