package org.insa.algo.utils;
import org.insa.graph.io.*;
import org.insa.base.*;
import org.insa.graph.*;
import org.insa.algo.*;
import org.insa.algo.shortestpath.*;

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
	

	//chemin entre ile et continent
	private static Node[] nodesImpossible;
	
	
	@BeforeClass
	public static void initAll() throws Exception {
		
		
		String mapInsa = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/insa.mapgr";
		String mapHaiti = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/haiti-and-domrep.mapgr";
		String mapBelgium = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/belgium.mapgr";
		
		
		
        GraphReader reader1 = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapInsa))));
        
        GraphReader reader2 = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapHaiti))));
        
        GraphReader reader3 = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapBelgium))));
        
		insaGraph = reader1.read();
		haitiGraph = reader2.read();
		belgiumGraph = reader3.read();
			
	}
	
	
	@Test
	public void test1()
	{
		Node nodeimpo1 = haitiGraph.get(94803);
		Node nodeimpo2 = haitiGraph.get(46027);
		ArcInspector arcinspector = ArcInspectorFactory.getAllFilters().get(0);
		ShortestPathData data = new ShortestPathData(haitiGraph,nodeimpo1,nodeimpo2,arcinspector);
		BellmanFordAlgorithm bellman = new BellmanFordAlgorithm(data);
		DijkstraAlgorithm dij = new DijkstraAlgorithm(data);
		ShortestPathSolution solbell = bellman.doRun();
		ShortestPathSolution soldij = dij.doRun();
		
		
		// test impossible entre ile et continent 
		if (soldij.isFeasible()==false) {
			System.out.println("c'est bon");
		}
		else {
			System.out.println("c'est pas bon");
		}
		
		
		
		
		
	}
	
	
}
