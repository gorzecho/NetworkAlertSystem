package com.network.system.service;

import static com.network.system.exception.ErrorMessages.NOT_EXIST;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.network.system.exception.NotFoundException;
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
  public void findAlertPropagationPathShouldReturnValidPathBetweenTwoServices() {
    // given // when
    var alertPropagationPath = alertNetworkService.findAlertPropagationPath("A", "C");
    // then
    assertEquals(List.of("A", "D", "C"), alertPropagationPath);
  }

  @Test
  public void findAlertPropagationPathShouldReturnEmptyListWhenThereIsNoPathBetweenTwoServices() {
    // given // when
    var alertPropagationPath = alertNetworkService.findAlertPropagationPath("B", "D");
    // then
    assertEquals(Collections.emptyList(), alertPropagationPath);
  }

  @Test
  void findAlertPropagationPathShouldThrowNotFoundExceptionWhenInvalidService() {
    // given
    var invalidService = "X";
    String expectedMessage = NOT_EXIST.formatted(invalidService);
    // when // then
    Exception exception =
        assertThrows(
            NotFoundException.class,
            () -> {
              alertNetworkService.findAlertPropagationPath(invalidService, "C");
            });
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
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
  void getAffectedServicesShouldThrowNotFoundExceptionWhenInvalidService() {
    // given
    var invalidService = "X";
    String expectedMessage = NOT_EXIST.formatted(invalidService);
    // when // then
    Exception exception =
        assertThrows(
            NotFoundException.class,
            () -> {
              alertNetworkService.getAffectedServices(invalidService);
            });
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  public void testSuggestContainmentEdges() {
    // given // when
    var containmentEdges = alertNetworkService.suggestContainmentEdges("A");
    // then
    assertEquals(List.of("B", "C"), containmentEdges);
  }
}
