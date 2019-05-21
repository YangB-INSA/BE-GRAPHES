package org.insa.algo.utils;
import org.insa.graph.io.*;

import org.insa.graph.*;
import org.insa.algo.*;
import org.insa.algo.shortestpath.*;

import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.GraphReader;
import org.junit.BeforeClass;
import org.junit.Test;

public class ShortestPathTest {
    
	
	public static Graph insaGraph;
	
	public static Graph haitiGraph;
	
	public static Graph belgiumGraph;

	
	
	@BeforeClass
	public static void initAll() throws Exception {
		
		//cartes utilisées
		//String mapInsa = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/insa.mapgr";
		String mapInsa = "D:\\Projet INSA\\Maps\\insa.mapgr";
		//String mapHaiti = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/haiti-and-domrep.mapgr";
		String mapHaiti = "D:\\Projet INSA\\Maps\\haiti-and-domrep.mapgr";
		//String mapBelgium = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/belgium.mapgr";
		String mapBelgium = "D:\\Projet INSA\\Maps\\belgium.mapgr";
		
		
        GraphReader reader1 = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapInsa))));
        
        GraphReader reader2 = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapHaiti))));
        
        GraphReader reader3 = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapBelgium))));
        
        // Graph de test
		insaGraph = reader1.read();
		haitiGraph = reader2.read();
		belgiumGraph = reader3.read();
		
			
	}
	
	
	@Test
	public void testhaiti()
	{
		Node nodeimpo1 = haitiGraph.get(24585);
		Node nodeimpo2 = haitiGraph.get(65539);
		Node nodeimpo3 = haitiGraph.get(51928);
		Node nodeimpo4 = haitiGraph.get(77593);
		
		Node nodeorigin= haitiGraph.get(107873);
		Node nodedestination= haitiGraph.get(184722);
		
		ArcInspector arcinspector = ArcInspectorFactory.getAllFilters().get(0);
		
		ShortestPathData data = new ShortestPathData(haitiGraph,nodeimpo1,nodeimpo2,arcinspector);
		ShortestPathData data2 = new ShortestPathData(haitiGraph,nodeimpo3,nodeimpo4,arcinspector);
		ShortestPathData data3 = new ShortestPathData(haitiGraph,nodeorigin,nodedestination,arcinspector);
		
		DijkstraAlgorithm dij = new DijkstraAlgorithm(data);
		DijkstraAlgorithm dij2 = new DijkstraAlgorithm(data2);
		DijkstraAlgorithm dij3 = new DijkstraAlgorithm(data3);
		BellmanFordAlgorithm bellman = new BellmanFordAlgorithm(data3);

	
		
		// test impossible de ile vers continent
		assertEquals(false,dij.doRun().isFeasible());
		// test impossible de continent vers île
		assertEquals(false,dij2.doRun().isFeasible());
		//test normal
		assertEquals(bellman.doRun().getPath().getLength(),dij3.doRun().getPath().getLength(),0);
		
	}
	
	
}
