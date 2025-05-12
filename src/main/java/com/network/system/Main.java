package com.network.system;

import lombok.extern.log4j.Log4j2;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.DepthFirstIterator;

import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.rmi.server.ExportException;
import java.util.Iterator;
import java.util.LinkedHashMap;

@Log4j2
public class Main {

  public static void main(String[] args) throws URISyntaxException {

    Graph<String, DefaultEdge> stringGraph = createStringGraph();

    System.out.println(stringGraph);
    System.out.println();
    traverseStringGraph(stringGraph, "v1");
    System.out.println();
    traverseStringGraph(stringGraph, "v2");
    System.out.println();
    traverseStringGraph(stringGraph, "v3");
    System.out.println();
    traverseStringGraph(stringGraph, "v4");


//    URI start = hrefGraph
//            .vertexSet().stream().filter(uri -> uri.getHost().equals("www.jgrapht.org")).findAny()
//            .get();

  }

  private static Graph<String, DefaultEdge> createStringGraph()
  {
    Graph<String, DefaultEdge> g = new DefaultDirectedGraph<>(DefaultEdge.class);

    String v1 = "v1";
    String v2 = "v2";
    String v3 = "v3";
    String v4 = "v4";

    g.addVertex(v1);
    g.addVertex(v2);
    g.addVertex(v3);
    g.addVertex(v4);

    g.addEdge(v1, v2);
    g.addEdge(v2, v3);
    g.addEdge(v3, v4);

    return g;
  }

  private static void traverseStringGraph(Graph<String, DefaultEdge> stringGraph, String start)
  {
    Iterator<String> iterator = new DepthFirstIterator<>(stringGraph, start);
    while (iterator.hasNext()) {
      String vertex = iterator.next();
      System.out.println(vertex);
    }
  }

}
