package org.insa.algo.shortestpath;

import org.insa.graph.*;
import org.insa.algo.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.GraphReader;
import org.junit.BeforeClass;
import org.junit.Test;

public class ShortestPathTest {
    
	public static Graph toulouseGraph;
	
	public static Graph haitiGraph;
	
	public static Graph garonneGraph;
	
	public static ArcInspector arcinspectorlength;
	
	public static ArcInspector arcinspectortime;
	
	public static ShortestPathData data,data0,data1,data2,data3,data4,data5,data6;

	@BeforeClass
	public static void initAll() throws Exception {
		
		//cartes utilisées
		String mapToulouse = "D:\\Projet INSA\\Maps\\toulouse.mapgr";
		String mapHaiti = "D:\\Projet INSA\\Maps\\haiti-and-domrep.mapgr";
		String mapGaronne = "D:\\Projet INSA\\Maps\\haute-garonne.mapgr";
		
        GraphReader reader1 = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapToulouse))));
        
        GraphReader reader2 = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapHaiti))));
        
        GraphReader reader3 = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapGaronne))));
        
        // Graph de test
		toulouseGraph = reader1.read();
		haitiGraph = reader2.read();
		garonneGraph = reader3.read();
		
		//arc inspector
		arcinspectorlength = ArcInspectorFactory.getAllFilters().get(0);
		arcinspectortime = ArcInspectorFactory.getAllFilters().get(2);
		
		// data chemin inexistant
		data = new ShortestPathData(haitiGraph,haitiGraph.get(24585),haitiGraph.get(65539),arcinspectortime);
		
		
		//data length
		data1 = new ShortestPathData(toulouseGraph,toulouseGraph.get(17161),toulouseGraph.get(23752),arcinspectorlength);
		data2 = new ShortestPathData(garonneGraph,garonneGraph.get(26431),garonneGraph.get(60105),arcinspectorlength);
		//data time
		data3 = new ShortestPathData(toulouseGraph,toulouseGraph.get(17161),toulouseGraph.get(23752),arcinspectortime);
		data4 = new ShortestPathData(garonneGraph,garonneGraph.get(26431),garonneGraph.get(60105),arcinspectortime);
	}
	
	@Test
	public void testInexistant()
	{
		
		Node nodeinexistant = new Node(haitiGraph.size()+15,null);
		Node nodeexistant = new Node(haitiGraph.size()-3,null);
		
		DijkstraAlgorithm dij = new DijkstraAlgorithm(data);
		
		// test chemin inexistant de ile vers continent haiti
		assertFalse(dij.doRun().isFeasible());
		
		// test pour nodes appartenant et n'appartenant pas aux graphes
		assertFalse(haitiGraph.getNodes().isEmpty());
		assertFalse(haitiGraph.getNodes().contains(nodeinexistant));
		assertTrue(haitiGraph.getNodes().contains(nodeexistant));
	}
	
	@Test
	public void testNulle()
	{
		//test pour chemin de longueur nulle et de temps de parcours nulles ( d'un noeud ï¿½ lui mï¿½me )
		Node node = toulouseGraph.get(24224);
		//origin = destination
		ShortestPathData data = new ShortestPathData(toulouseGraph,node,node,arcinspectortime);
		DijkstraAlgorithm dij = new DijkstraAlgorithm(data);
		
		assertEquals(0,dij.doRun().getPath().getLength(),0);
		assertEquals(0,dij.doRun().getPath().getMinimumTravelTime(),0);
		
	}
	
	@Test
	public void testShortest() 
	{
		DijkstraAlgorithm dij = new DijkstraAlgorithm(data1);
		AStarAlgorithm star = new AStarAlgorithm(data1);
		BellmanFordAlgorithm bell = new BellmanFordAlgorithm(data1);
		DijkstraAlgorithm dij2 = new DijkstraAlgorithm(data2);
		AStarAlgorithm star2 = new AStarAlgorithm(data2);
		BellmanFordAlgorithm bell2 = new BellmanFordAlgorithm(data2);
		
		//test dijkstra
		assertEquals(bell.doRun().getPath().getLength(),dij.doRun().getPath().getLength(),0);
		assertEquals(bell2.doRun().getPath().getLength(),dij2.doRun().getPath().getLength(),0);
		
		//test AStar
		assertEquals(bell.doRun().getPath().getLength(),star.doRun().getPath().getLength(),0);
		assertEquals(bell2.doRun().getPath().getLength(),star2.doRun().getPath().getLength(),0);
		
		//test chemin valide
		assertTrue(dij.doRun().getPath().isValid());
		assertTrue(dij2.doRun().getPath().isValid());
		assertTrue(star.doRun().getPath().isValid());
		assertTrue(star2.doRun().getPath().isValid());
	}
	
	@Test 
	public void testFastest() 
	{
		
		DijkstraAlgorithm dij = new DijkstraAlgorithm(data3);
		AStarAlgorithm star = new AStarAlgorithm(data3);
		BellmanFordAlgorithm bell = new BellmanFordAlgorithm(data3);
		DijkstraAlgorithm dij2 = new DijkstraAlgorithm(data4);
		AStarAlgorithm star2 = new AStarAlgorithm(data4);
		BellmanFordAlgorithm bell2 = new BellmanFordAlgorithm(data4);
		
		//test dijkstra
		assertEquals(bell.doRun().getPath().getMinimumTravelTime(),dij.doRun().getPath().getMinimumTravelTime(),0);
		assertEquals(bell2.doRun().getPath().getMinimumTravelTime(),dij2.doRun().getPath().getMinimumTravelTime(),0);
		
		//test AStar
		assertEquals(bell.doRun().getPath().getMinimumTravelTime(),star.doRun().getPath().getMinimumTravelTime(),0);
		assertEquals(bell2.doRun().getPath().getMinimumTravelTime(),star2.doRun().getPath().getMinimumTravelTime(),0);
		
		//test chemin valide
		assertTrue(dij.doRun().getPath().isValid());
		assertTrue(dij2.doRun().getPath().isValid());
		assertTrue(star.doRun().getPath().isValid());
		assertTrue(star2.doRun().getPath().isValid());
	}
}



