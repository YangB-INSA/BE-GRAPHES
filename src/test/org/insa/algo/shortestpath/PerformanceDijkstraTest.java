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
    
	public static Graph franceGraph;
	
	public static Graph belgiumGraph;
	
	public static Graph garonneGraph;
	
	public static ArcInspector arcinspectorlength;
	
	public static ArcInspector arcinspectortime;
	
	public static ShortestPathData data1,data2,data3,data4,data5,data6;
	
	public static long Time1,Time2,Time3;

	@BeforeClass
	public static void initAll() throws Exception {
		
		//cartes utilisées
		//String mapToulouse = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/toulouse.mapgr";
		String mapFrance = "D:\\Projet INSA\\Maps\\france.mapgr";
		//String mapHaiti = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/haiti-and-domrep.mapgr";
		String mapBelgium = "D:\\Projet INSA\\Maps\\belgium.mapgr";
		//String mapBelgium = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/belgium.mapgr";
		String mapGaronne = "D:\\Projet INSA\\Maps\\haute-garonne.mapgr";
		
        GraphReader reader1 = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapFrance))));
        
        GraphReader reader2 = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapBelgium))));
        
        GraphReader reader3 = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapGaronne))));
        
        // Graph de test
		franceGraph = reader1.read();
		belgiumGraph = reader2.read();
		garonneGraph = reader3.read();
		
		//arc inspector
		arcinspectorlength = ArcInspectorFactory.getAllFilters().get(0);
		arcinspectortime = ArcInspectorFactory.getAllFilters().get(2);
		
		//data length
		data1 = new ShortestPathData(belgiumGraph,belgiumGraph.get(350502),belgiumGraph.get(230969),arcinspectorlength);
		data2 = new ShortestPathData(garonneGraph,garonneGraph.get(26431),garonneGraph.get(60105),arcinspectorlength);
		//data time
		data3 = new ShortestPathData(belgiumGraph,belgiumGraph.get(350502),belgiumGraph.get(230969),arcinspectortime);
		data4 = new ShortestPathData(garonneGraph,garonneGraph.get(26431),garonneGraph.get(60105),arcinspectortime);
	}
	
	@Test
	public void testLongueur() 
	{
		DijkstraAlgorithm dij = new DijkstraAlgorithm(data1);
		AStarAlgorithm star = new AStarAlgorithm(data1);
		
		DijkstraAlgorithm dij2 = new DijkstraAlgorithm(data2);
		AStarAlgorithm star2 = new AStarAlgorithm(data2);
		
		System.out.println("\n ------Test ShortestPath------ \n" );
		//A tester sur de grandes cartes pour voir des résultats significatifs
		System.out.println("Test sur la carte de Belgique \n" );
		
		Time1 = System.currentTimeMillis();
		dij.doRun();
		Time2 = System.currentTimeMillis() - Time1;
		System.out.println("Temps d'execution Dijkstra :" + Time2 +" ms");
		
		Time1 = System.currentTimeMillis();
		star.doRun();
		Time3 = System.currentTimeMillis() - Time1;
		System.out.println("Temps d'execution A*:" + Time3 +" ms");
		System.out.println("A* est " + (float)Time2/Time3 + " plus rapide que Dijkstra" );
		
		
		System.out.println("\nTest sur la carte de Haute-Garonne \n" );
		
		Time1 = System.currentTimeMillis();
		dij2.doRun();
		Time2 = System.currentTimeMillis() - Time1;
		System.out.println("Temps d'execution Dijkstra :" + Time2 +" ms");
		
		Time1 = System.currentTimeMillis();
		star2.doRun();
		Time3 = System.currentTimeMillis() - Time1;
		System.out.println("Temps d'execution A* :" + Time3 +" ms");
		System.out.println("A* est " + (float)Time2/Time3 + " plus rapide que Dijkstra" );

	}
	
	@Test 
	public void testTemps() 
	{
		
		DijkstraAlgorithm dij = new DijkstraAlgorithm(data3);
		AStarAlgorithm star = new AStarAlgorithm(data3);
		
		DijkstraAlgorithm dij2 = new DijkstraAlgorithm(data4);
		AStarAlgorithm star2 = new AStarAlgorithm(data4);
		
		System.out.println("\n ------Test FastestPath------ \n" );
		//A tester sur de grandes cartes pour voir des résultats significatifs
		System.out.println("\nTest sur la carte de Belgique \n" );
		
		Time1 = System.currentTimeMillis();
		dij.doRun();
		Time2 = System.currentTimeMillis() - Time1;
		System.out.println("Temps d'execution Dijkstra :" + Time2 +" ms");
		
		Time1 = System.currentTimeMillis();
		star.doRun();
		Time3 = System.currentTimeMillis() - Time1;
		System.out.println("Temps d'execution A*:" + Time3 +" ms");
		System.out.println("A* est " + (float)Time2/Time3 + " plus rapide que Dijkstra" );
		
		
		System.out.println("\nTest sur la carte de Haute-Garonne \n" );
		
		Time1 = System.currentTimeMillis();
		dij2.doRun();
		Time2 = System.currentTimeMillis() - Time1;
		System.out.println("Temps d'execution Dijkstra :" + Time2 +" ms");
		
		Time1 = System.currentTimeMillis();
		star2.doRun();
		Time3 = System.currentTimeMillis() - Time1;
		System.out.println("Temps d'execution A* :" + Time3 +" ms");
		System.out.println("A* est " + (float)Time2/Time3 + " plus rapide que Dijkstra" );
	}

}



