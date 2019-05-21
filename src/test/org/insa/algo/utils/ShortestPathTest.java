package org.insa.algo.utils;
import org.insa.graph.io.*;

import org.insa.graph.*;
import org.insa.algo.*;
import org.insa.algo.shortestpath.*;

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
	
	public static Graph belgiumGraph;
	
	public static Graph zelandeGraph;
	
	public static ArcInspector arcinspector;

	
	
	@BeforeClass
	public static void initAll() throws Exception {
		
		//cartes utilisées
		//String mapInsa = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/insa.mapgr";
		String mapToulouse = "D:\\Projet INSA\\Maps\\toulouse.mapgr";
		//String mapHaiti = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/haiti-and-domrep.mapgr";
		String mapHaiti = "D:\\Projet INSA\\Maps\\haiti-and-domrep.mapgr";
		//String mapBelgium = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/belgium.mapgr";
		String mapBelgium = "D:\\Projet INSA\\Maps\\belgium.mapgr";
		String mapZelande = "D:\\Projet INSA\\Maps\\new-zealand.mapgr";
		
		
        GraphReader reader1 = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapToulouse))));
        
        GraphReader reader2 = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapHaiti))));
        
        GraphReader reader3 = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapBelgium))));
        
        GraphReader reader4 = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapZelande))));
        
        // Graph de test
		toulouseGraph = reader1.read();
		haitiGraph = reader2.read();
		belgiumGraph = reader3.read();
		zelandeGraph = reader4.read();
		
		arcinspector = ArcInspectorFactory.getAllFilters().get(0);
	}
	
	
	@Test
	public void testInexistant()
	{
		
		int outofbounds = haitiGraph.size()+15;
		
		Node nodeinexistant = new Node(outofbounds,null);
		Node nodeexistant = new Node(haitiGraph.size()-3,null);
		
		Node nodeimpo1 = haitiGraph.get(24585);
		Node nodeimpo2 = haitiGraph.get(65539);
		
		Node node1= zelandeGraph.get(54005);
		Node node2= zelandeGraph.get(59030);
		
		ShortestPathData data = new ShortestPathData(haitiGraph,nodeimpo1,nodeimpo2,arcinspector);
		ShortestPathData data2 = new ShortestPathData(haitiGraph,nodeinexistant,nodeexistant,arcinspector);
		ShortestPathData data3 = new ShortestPathData(zelandeGraph,node1,node2,arcinspector);
		
		DijkstraAlgorithm dij = new DijkstraAlgorithm(data);
		DijkstraAlgorithm dij2 = new DijkstraAlgorithm(data2);
		DijkstraAlgorithm dij3 = new DijkstraAlgorithm(data3);
		
		// test chemin inexistant de ile vers continent haiti
		assertEquals(false,dij.doRun().isFeasible());
		
		//test chemin inexistant nouvelle zélande
		assertEquals(false,dij3.doRun().isFeasible());
		
		// test pour nodes appartenant et n'appartenant pas aux graphes
		// inutile ??
		assertFalse(haitiGraph.getNodes().contains(nodeinexistant));
		assertTrue(haitiGraph.getNodes().contains(nodeexistant));
	}
	
	@Test
	public void testNulle()
	{
		//test pour chemin de longueur nulle et de temps de parcours nulles ( d'un noeud à lui même )
		Node node = toulouseGraph.get(24224);
		ShortestPathData data = new ShortestPathData(toulouseGraph,node,node,arcinspector);
		DijkstraAlgorithm dij = new DijkstraAlgorithm(data);
		
		assertEquals(0,dij.doRun().getPath().getLength(),0);
		assertEquals(0,dij.doRun().getPath().getMinimumTravelTime(),0);
		
	}
	@Test
	public void testLongueur() 
	{
		
		//test en longueur
		Node nodelen1 = toulouseGraph.get(17161);
		Node nodelen2 = toulouseGraph.get(23752);
		
		ShortestPathData data = new ShortestPathData(toulouseGraph,nodelen1,nodelen2,arcinspector);
		DijkstraAlgorithm dij = new DijkstraAlgorithm(data);
		BellmanFordAlgorithm bell = new BellmanFordAlgorithm(data);
		
		assertEquals(bell.doRun().getPath().getLength(),dij.doRun().getPath().getLength(),0);
		
	}
	
	@Test 
	public void testTemps() 
	{
		//test en temps
		Node nodetime1 = toulouseGraph.get(17161);
		Node nodetime2 = toulouseGraph.get(23752);
		
		ShortestPathData data = new ShortestPathData(toulouseGraph,nodetime1,nodetime2,arcinspector);
		DijkstraAlgorithm dij = new DijkstraAlgorithm(data);
		BellmanFordAlgorithm bell = new BellmanFordAlgorithm(data);
		
		assertEquals(bell.doRun().getPath().getMinimumTravelTime(),dij.doRun().getPath().getMinimumTravelTime(),0);
		
	}
}

// ya un pb, comment on fait pour faire tourner les algos en choissisant la longueur ou l'heure ??
// on modifie pour mettre ca en argument ?????????

