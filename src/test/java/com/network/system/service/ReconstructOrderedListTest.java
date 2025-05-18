package com.network.system.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class ReconstructOrderedListTest {

  @Test
  public void shouldReconstructOrderedList() {
    // given
    List<List<String>> pairs =
        List.of(
            Arrays.asList("A", "B"),
            Arrays.asList("C", "D"),
            Arrays.asList("B", "C"),
            Arrays.asList("D", null));
    // when
    var reconstructedList = reconstructOrder(pairs);
    // then
    assertEquals(List.of("A", "B", "C", "D"), reconstructedList);
  }

  @Test
  public void shouldReconstructEmptyOrderedList() {
    // given
    List<List<String>> pairs = List.of();
    // when
    var reconstructedList = reconstructOrder(pairs);
    // then
    assertEquals(List.of(), reconstructedList);
  }

  private static List<String> reconstructOrder(List<List<String>> pairs) {
    if (pairs.isEmpty()) {
      return Collections.emptyList();
    }
    List<String> reconstructedList = new ArrayList<>();
    reconstructedList.add(pairs.get(0).get(0));
    String target = hasNextValue(pairs.get(0)) ? pairs.get(0).get(1) : null;
    while (target != null) {
      for (var pair : pairs) {
        if (pair.get(0).equals(target)) {
          reconstructedList.add(pair.get(0));
          target = hasNextValue(pair) ? pair.get(1) : null;
        }
      }
    }
    return reconstructedList;
  }

  private static boolean hasNextValue(List<String> pair) {
    return pair.size() == 2;
  }
}
