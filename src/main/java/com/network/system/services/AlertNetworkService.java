package com.network.system.services;

import ch.qos.logback.core.joran.sanity.Pair;
import com.network.system.interfaces.AlertNetwork;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Log4j2
public class AlertNetworkService implements AlertNetwork {

//  private static final String CIK_SEPARATOR = ":";
//  private static final int CIK_LENGTH = 10;
  private static final Logger log = LogManager.getLogger(AlertNetworkService.class);

  public void addService(String service) {

  }

  public void addDependency(String fromService, String toService) {

  }

  public List<String> getDependencies(String service) {
    return List.of();
  }

  public List<String> findAlertPropagationPath(String source, String target) {
    return List.of();
  }

  public List<String> getAffectedServices(String source) {
    return List.of();
  }

  public List<Pair<String, String>> suggestContainmentEdges(String source) {
    return List.of();
  }

}
