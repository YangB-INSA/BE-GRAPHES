package org.insa.algo.shortestpath;

import org.insa.graph.*;
import org.insa.algo.*;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.GraphReader;
import org.junit.BeforeClass;
import org.junit.Test;

public class PerformanceDijkstraTest {
    
	public static Graph toulouseGraph;
	
	public static Graph haitiGraph;
	
	public static Graph garonneGraph;
	
	public static ArcInspector arcinspectorlength;
	
	public static ArcInspector arcinspectortime;
	
	public static ShortestPathData data1,data2,data3,data4,data5,data6;
	
	public static long Time1,Time2;

	@BeforeClass
	public static void initAll() throws Exception {
		
		//cartes utilisées
		//String mapToulouse = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/toulouse.mapgr";
		String mapToulouse = "D:\\Projet INSA\\Maps\\toulouse.mapgr";
		//String mapHaiti = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/haiti-and-domrep.mapgr";
		String mapHaiti = "D:\\Projet INSA\\Maps\\haiti-and-domrep.mapgr";
		//String mapBelgium = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/belgium.mapgr";
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
		
		//data length
		data1 = new ShortestPathData(toulouseGraph,toulouseGraph.get(17161),toulouseGraph.get(23752),arcinspectorlength);
		data2 = new ShortestPathData(garonneGraph,garonneGraph.get(26431),garonneGraph.get(60105),arcinspectorlength);
		//data time
		data3 = new ShortestPathData(toulouseGraph,toulouseGraph.get(17161),toulouseGraph.get(23752),arcinspectortime);
		data4 = new ShortestPathData(garonneGraph,garonneGraph.get(26431),garonneGraph.get(60105),arcinspectortime);
	}
	
	@Test
	public void testLongueur() 
	{
		DijkstraAlgorithm dij = new DijkstraAlgorithm(data1);
		AStarAlgorithm star = new AStarAlgorithm(data1);
		
		DijkstraAlgorithm dij2 = new DijkstraAlgorithm(data2);
		AStarAlgorithm star2 = new AStarAlgorithm(data2);
		
		//A tester sur de grandes cartes pour voir des résultats significatifs
		System.out.println("Test sur la carte de Toulouse \n" );
		
		Time1 = System.currentTimeMillis();
		dij.doRun();
		Time2 = System.currentTimeMillis() - Time1;
		System.out.println("Temps d'execution Dijkstra :" + Time2 );
		
		Time1 = System.currentTimeMillis();
		star.doRun();
		Time2 = System.currentTimeMillis() - Time1;
		System.out.println("Temps d'execution A*:" + Time2 );
		
		System.out.println("\nTest sur la carte de Haute-Garonne \n" );
		
		Time1 = System.currentTimeMillis();
		dij2.doRun();
		Time2 = System.currentTimeMillis() - Time1;
		System.out.println("Temps d'execution Dijkstra :" + Time2 );
		
		Time1 = System.currentTimeMillis();
		star2.doRun();
		Time2 = System.currentTimeMillis() - Time1;
		System.out.println("Temps d'execution A* :" + Time2 );
		
		
	}
	
	/*@Test 
	public void testTemps() 
	{
		
		DijkstraAlgorithm dij = new DijkstraAlgorithm(data3);
		AStarAlgorithm star = new AStarAlgorithm(data3);
		BellmanFordAlgorithm bell = new BellmanFordAlgorithm(data3);
		DijkstraAlgorithm dij2 = new DijkstraAlgorithm(data4);
		AStarAlgorithm star2 = new AStarAlgorithm(data4);
		BellmanFordAlgorithm bell2 = new BellmanFordAlgorithm(data4);
		
		//test dijkstra
		assertEquals(dij.doRun().getPath().getLength(),star.doRun().getPath().getMinimumTravelTime()(),0);
		assertEquals(dij2.doRun().getPath().getLength(),star2.doRun().getPath().getMinimumTravelTime()(),0);
			
	}
*/
}



