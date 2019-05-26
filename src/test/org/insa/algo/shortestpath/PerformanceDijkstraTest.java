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
	
	public static ArcInspector arcinspectorlength;
	
	public static ArcInspector arcinspectortime;
	
	public static ShortestPathData data1,data2,data3,data4,data5,data6;
	
	public static long Time1,Time2,Time3;

	@BeforeClass
	public static void initAll() throws Exception {
		
		//cartes utilisées
		String mapFrance = "D:\\Projet INSA\\Maps\\france.mapgr";
		String mapBelgium = "D:\\Projet INSA\\Maps\\belgium.mapgr";
		
		
        GraphReader reader1 = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapFrance))));
        
        GraphReader reader2 = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapBelgium))));
        
        // Graph de test
		franceGraph = reader1.read();
		belgiumGraph = reader2.read();
		
		//arc inspector
		arcinspectorlength = ArcInspectorFactory.getAllFilters().get(0);
		arcinspectortime = ArcInspectorFactory.getAllFilters().get(2);
		
		//data length
		data1 = new ShortestPathData(belgiumGraph,belgiumGraph.get(859265),belgiumGraph.get(1015144),arcinspectorlength);
		data2 = new ShortestPathData(belgiumGraph,belgiumGraph.get(779268),belgiumGraph.get(3596),arcinspectorlength);
		data3 = new ShortestPathData(belgiumGraph,belgiumGraph.get(531717),belgiumGraph.get(22677),arcinspectorlength);
		/*
		data1 = new ShortestPathData(franceGraph,franceGraph.get(350502),franceGraph.get(230969),arcinspectorlength);
		data2 = new ShortestPathData(franceGraph,franceGraph.get(350502),franceGraph.get(230969),arcinspectorlength);
		data3 = new ShortestPathData(franceGraph,franceGraph.get(350502),franceGraph.get(230969),arcinspectorlength);
		*/
		//data time
		data4 = new ShortestPathData(belgiumGraph,belgiumGraph.get(859265),belgiumGraph.get(1015144),arcinspectortime);
		data5 = new ShortestPathData(belgiumGraph,belgiumGraph.get(779268),belgiumGraph.get(3596),arcinspectortime);
		data6 = new ShortestPathData(belgiumGraph,belgiumGraph.get(531717),belgiumGraph.get(22677),arcinspectortime);
		/*
		data1 = new ShortestPathData(franceGraph,franceGraph.get(350502),franceGraph.get(230969),arcinspectortime);
		data2 = new ShortestPathData(franceGraph,franceGraph.get(350502),franceGraph.get(230969),arcinspectortime);
		data3 = new ShortestPathData(franceGraph,franceGraph.get(350502),franceGraph.get(230969),arcinspectortime);
		*/
	}
	
	@Test
	public void testLongueur() 
	{
		DijkstraAlgorithm dij = new DijkstraAlgorithm(data1);
		AStarAlgorithm star = new AStarAlgorithm(data1);
		
		DijkstraAlgorithm dij2 = new DijkstraAlgorithm(data2);
		AStarAlgorithm star2 = new AStarAlgorithm(data2);
		
		DijkstraAlgorithm dij3 = new DijkstraAlgorithm(data3);
		AStarAlgorithm star3 = new AStarAlgorithm(data3);
		
		System.out.println("\n ------Test ShortestPath------ \n" );

		System.out.println("--Test sur la carte de Belgique--\n" );
		
		System.out.println("-Test 'grande échelle'- \n" );
		Time1 = System.currentTimeMillis();
		dij.doRun();
		Time2 = System.currentTimeMillis() - Time1;
		System.out.println("Temps d'execution Dijkstra :" + Time2 +" ms");
		
		Time1 = System.currentTimeMillis();
		star.doRun();
		Time3 = System.currentTimeMillis() - Time1;
		System.out.println("Temps d'execution A*:" + Time3 +" ms");
		System.out.println("A* est " + (float)Time2/Time3 + " plus rapide que Dijkstra" );
		
		System.out.println("\n-Test 'moyenne échelle'- \n" );
		Time1 = System.currentTimeMillis();
		dij2.doRun();
		Time2 = System.currentTimeMillis() - Time1;
		System.out.println("Temps d'execution Dijkstra :" + Time2 +" ms");
		
		Time1 = System.currentTimeMillis();
		star2.doRun();
		Time3 = System.currentTimeMillis() - Time1;
		System.out.println("Temps d'execution A*:" + Time3 +" ms");
		System.out.println("A* est " + (float)Time2/Time3 + " plus rapide que Dijkstra" );
		
		System.out.println("\n-Test 'petite échelle'- \n" );
		Time1 = System.currentTimeMillis();
		dij3.doRun();
		Time2 = System.currentTimeMillis() - Time1;
		System.out.println("Temps d'execution Dijkstra :" + Time2 +" ms");
		
		Time1 = System.currentTimeMillis();
		star3.doRun();
		Time3 = System.currentTimeMillis() - Time1;
		System.out.println("Temps d'execution A*:" + Time3 +" ms");
		System.out.println("A* est " + (float)Time2/Time3 + " plus rapide que Dijkstra" );

	}
	
	@Test 
	public void testTemps() 
	{
		
		DijkstraAlgorithm dij = new DijkstraAlgorithm(data4);
		AStarAlgorithm star = new AStarAlgorithm(data4);
		
		DijkstraAlgorithm dij2 = new DijkstraAlgorithm(data5);
		AStarAlgorithm star2 = new AStarAlgorithm(data5);
		
		DijkstraAlgorithm dij3 = new DijkstraAlgorithm(data6);
		AStarAlgorithm star3 = new AStarAlgorithm(data6);
		
		System.out.println("\n ------Test FastestPath------ \n" );
		//A tester sur de grandes cartes pour voir des résultats significatifs
		System.out.println("--Test sur la carte de Belgique--\n" );
		
		System.out.println("-Test 'grande échelle'- \n" );
		Time1 = System.currentTimeMillis();
		dij.doRun();
		Time2 = System.currentTimeMillis() - Time1;
		System.out.println("Temps d'execution Dijkstra :" + Time2 +" ms");
		
		Time1 = System.currentTimeMillis();
		star.doRun();
		Time3 = System.currentTimeMillis() - Time1;
		System.out.println("Temps d'execution A*:" + Time3 +" ms");
		System.out.println("A* est " + (float)Time2/Time3 + " plus rapide que Dijkstra" );
		
		System.out.println("\n-Test 'moyenne échelle'- \n" );
		Time1 = System.currentTimeMillis();
		dij2.doRun();
		Time2 = System.currentTimeMillis() - Time1;
		System.out.println("Temps d'execution Dijkstra :" + Time2 +" ms");
		
		Time1 = System.currentTimeMillis();
		star2.doRun();
		Time3 = System.currentTimeMillis() - Time1;
		System.out.println("Temps d'execution A*:" + Time3 +" ms");
		System.out.println("A* est " + (float)Time2/Time3 + " plus rapide que Dijkstra" );
		
		System.out.println("\n-Test 'petite échelle'- \n" );
		Time1 = System.currentTimeMillis();
		dij3.doRun();
		Time2 = System.currentTimeMillis() - Time1;
		System.out.println("Temps d'execution Dijkstra :" + Time2 +" ms");
		
		Time1 = System.currentTimeMillis();
		star3.doRun();
		Time3 = System.currentTimeMillis() - Time1;
		System.out.println("Temps d'execution A*:" + Time3 +" ms");
		System.out.println("A* est " + (float)Time2/Time3 + " plus rapide que Dijkstra" );
	}

}



