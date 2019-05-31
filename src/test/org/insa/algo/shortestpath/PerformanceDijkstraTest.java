package org.insa.algo.shortestpath;

import org.insa.graph.*;
import org.insa.algo.*;

import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.GraphReader;
import org.junit.BeforeClass;
import org.junit.Test;

public class PerformanceDijkstraTest {
    
	public static Graph belgiumGraph;
	
	public static Graph garonneGraph;
	
	public static Graph carreGraph;
	
	public static ArcInspector arcinspectorlength;
	
	public static ArcInspector arcinspectortime;
	
	public static ShortestPathData data1,data2,data3,data4,data5,data6,carre1,carre2,garo1,garo2;

	public static long Time,Time1,Time2,Time3,Time4,Time5,Time6;

	@BeforeClass
	public static void initAll() throws Exception {
		
		//cartes utilisées
		String mapBelgium = "..\\Maps\\belgium.mapgr";
		String mapGaronne = "..\\Maps\\haute-garonne.mapgr";
		String mapcarre = "..\\Maps\\carre-dense.mapgr";
		
		
        GraphReader reader1 = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapBelgium))));
        
        GraphReader reader2 = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapGaronne))));

        GraphReader reader3 = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapcarre))));
        
        // Graph de test
        belgiumGraph = reader1.read();
		garonneGraph = reader2.read();
		carreGraph = reader3.read();
		
		//arc inspector
		arcinspectorlength = ArcInspectorFactory.getAllFilters().get(0);
		arcinspectortime = ArcInspectorFactory.getAllFilters().get(2);
		
		//data belgique
		//data distance
		data1 = new ShortestPathData(belgiumGraph,belgiumGraph.get(859265),belgiumGraph.get(1015144),arcinspectorlength);
		data2 = new ShortestPathData(belgiumGraph,belgiumGraph.get(779268),belgiumGraph.get(3596),arcinspectorlength);
		data3 = new ShortestPathData(belgiumGraph,belgiumGraph.get(531717),belgiumGraph.get(22677),arcinspectorlength);

		//data temps
		data4 = new ShortestPathData(belgiumGraph,belgiumGraph.get(859265),belgiumGraph.get(1015144),arcinspectortime);
		data5 = new ShortestPathData(belgiumGraph,belgiumGraph.get(779268),belgiumGraph.get(3596),arcinspectortime);
		data6 = new ShortestPathData(belgiumGraph,belgiumGraph.get(531717),belgiumGraph.get(22677),arcinspectortime);
		
		//data carre distance
		carre1 = new ShortestPathData(carreGraph,carreGraph.get(83468),carreGraph.get(25557),arcinspectorlength);
		
		//data carre temps
		carre2 = new ShortestPathData(carreGraph,carreGraph.get(83468),carreGraph.get(25557),arcinspectortime);
		
		//data garonne distance
		garo1 = new ShortestPathData(garonneGraph,garonneGraph.get(93785),garonneGraph.get(24621),arcinspectorlength);
		
		//data garonne temps
		garo2 = new ShortestPathData(garonneGraph,garonneGraph.get(93785),garonneGraph.get(24621),arcinspectortime);
		
	}

	
	@Test
	public void testDistance() 
	{
		DijkstraAlgorithm dij = new DijkstraAlgorithm(data1);
		AStarAlgorithm star = new AStarAlgorithm(data1);
		
		DijkstraAlgorithm dij2 = new DijkstraAlgorithm(data2);
		AStarAlgorithm star2 = new AStarAlgorithm(data2);
		
		DijkstraAlgorithm dij3 = new DijkstraAlgorithm(data3);
		AStarAlgorithm star3 = new AStarAlgorithm(data3);
		
		DijkstraAlgorithm dijg = new DijkstraAlgorithm(garo1);
		AStarAlgorithm starg = new AStarAlgorithm(garo1);
		
		DijkstraAlgorithm dijc= new DijkstraAlgorithm(carre1);
		AStarAlgorithm starc = new AStarAlgorithm(carre1);
		

		System.out.println("\n ------Test en Distance------ \n" );

		System.out.println("--Test sur la carte de Belgique--\n" );
		
		System.out.println("-Test 'grande échelle'- \n" );
		
		//on démare le timer 
		Time = System.currentTimeMillis();
		// on execute 10 fois l'algo
		for (int i = 0;i<10;i++) {
			dij.doRun();
		}
		//on calcule le temps écoulé divisé par 10
		Time1 = (System.currentTimeMillis() - Time)/10;
			
		Time = System.currentTimeMillis();
		for (int i = 0;i<10;i++) {
			star.doRun();
		}
		Time2 = (System.currentTimeMillis() - Time)/10;
		
		System.out.println("Nombre sommets visités Dijkstra : "+ dij.sommetvisite);
		System.out.println("Nombre sommets visités Astar : " + star.sommetvisite2);
		System.out.println("Dijkstra visite environs " + 
		dij.sommetvisite/star.sommetvisite2 +" fois plus de sommets");
		
		System.out.println("Temps d'execution Dijkstra :" + Time1 +" ms");
		System.out.println("Temps d'execution Astar :" + Time2 +" ms");
		System.out.println("Astar est environs " + (float)Time1/Time2 +" plus rapide");
		
		//test moyenne echelle
		
		System.out.println("\n-Test 'moyenne echelle'- \n" );
		Time = System.currentTimeMillis();
		for (int i = 0;i<10;i++) {
			dij2.doRun();
		}
		Time3 = (System.currentTimeMillis() - Time)/10;
		
		Time = System.currentTimeMillis();
		for (int i = 0;i<10;i++) {
			star2.doRun();
		}
		Time4 = (System.currentTimeMillis() - Time)/10;
		
		System.out.println("Nombre sommets visités Dijkstra : "+ dij2.sommetvisite);
		System.out.println("Nombre sommets visités Astar : " + star2.sommetvisite2);
		System.out.println("Dijkstra visite environs " + 
		dij2.sommetvisite/star2.sommetvisite2 + " fois plus de sommets");
		
		System.out.println("Temps d'execution Dijkstra :" + Time3 +" ms");
		System.out.println("Temps d'execution Astar :" + Time4 +" ms");
		System.out.println("Astar est environs " + (float)Time3/Time4 +" plus rapide");
		
		//test petite échelle
		
		System.out.println("\n-Test 'petite echelle'- \n" );
		
		Time = System.currentTimeMillis();
		for (int i = 0;i<10;i++) {
			dij3.doRun();
		}
		Time5 = (System.currentTimeMillis() - Time)/10;
		
		Time = System.currentTimeMillis();
		for (int i = 0;i<10;i++) {
			star3.doRun();
		}
		Time6 = (System.currentTimeMillis() - Time)/10;
	
		System.out.println("Nombre sommets visités Dijkstra : "+ dij3.sommetvisite);
		System.out.println("Nombre sommets visités Astar : " + star3.sommetvisite2);
		System.out.println("Dijkstra visite environs " + 
		dij3.sommetvisite/star3.sommetvisite2 +" fois plus de sommets");
		
		System.out.println("Temps d'execution Dijkstra :" + Time5 +" ms");
		System.out.println("Temps d'execution Astar :" + Time6 +" ms");
		System.out.println("Astar est environs " + (float)Time5/Time6 +" plus rapide");
		
		
		//test Haute Garonness
		
		System.out.println("\n-Test sur la 'Haute-Garonne'- \n" );
		
		Time = System.currentTimeMillis();
		for (int i = 0;i<10;i++) {
			dijg.doRun();
		}
		Time3 = (System.currentTimeMillis() - Time)/10;
		
		Time = System.currentTimeMillis();
		for (int i = 0;i<10;i++) {
			starg.doRun();
		}
		Time4 = (System.currentTimeMillis() - Time)/10;
		
		System.out.println("Nombre sommets visités Dijkstra : "+ dijg.sommetvisite);
		System.out.println("Nombre sommets visités Astar : " + starg.sommetvisite2);
		System.out.println("Dijkstra visite environs " + 
		dijg.sommetvisite/starg.sommetvisite2 +" fois plus de sommets");
		
		System.out.println("Temps d'execution Dijkstra :" + Time3 +" ms");
		System.out.println("Temps d'execution Astar :" + Time4 +" ms");
		System.out.println("Astar est environs " + (float)Time3/Time4 +" plus rapide");
		
		
		//test carré dense
		
		System.out.println("\n-Test 'carré dense'- \n" );
		
		Time = System.currentTimeMillis();
		for (int i = 0;i<10;i++) {
			dijc.doRun();
		}
		Time3 = (System.currentTimeMillis() - Time)/10;
		
		Time = System.currentTimeMillis();
		for (int i = 0;i<10;i++) {
			starc.doRun();
		}
		Time4 = (System.currentTimeMillis() - Time)/10;
		
		System.out.println("Nombre sommets visités Dijkstra : "+ dijc.sommetvisite);
		System.out.println("Nombre sommets visités Astar : " + starc.sommetvisite2);
		System.out.println("Dijkstra visite environs " + 
		dijc.sommetvisite/starc.sommetvisite2 +" fois plus de sommets");
		
		System.out.println("Temps d'execution Dijkstra :" + Time3 +" ms");
		System.out.println("Temps d'execution Astar :" + Time4 +" ms");
		System.out.println("Astar est environs " + (float)Time3/Time4 +" plus rapide");
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
		
		DijkstraAlgorithm dijg = new DijkstraAlgorithm(garo2);
		AStarAlgorithm starg = new AStarAlgorithm(garo2);
		
		DijkstraAlgorithm dijc= new DijkstraAlgorithm(carre2);
		AStarAlgorithm starc = new AStarAlgorithm(carre2);
		
		System.out.println("\n ------Test en Temps------ \n" );

		System.out.println("--Test sur la carte de Belgique--\n" );
		
		System.out.println("-Test 'grande échelle'- \n" );
		
		//on démare le timer 
		Time = System.currentTimeMillis();
		// on execute 10 fois l'algo
		for (int i = 0;i<10;i++) {
			dij.doRun();
		}
		//on calcule le temps écoulé divisé par 10
		Time1 = (System.currentTimeMillis() - Time)/10;
			
		Time = System.currentTimeMillis();
		for (int i = 0;i<10;i++) {
			star.doRun();
		}
		Time2 = (System.currentTimeMillis() - Time)/10;
		
		System.out.println("Nombre sommets visités Dijkstra : "+ dij.sommetvisite);
		System.out.println("Nombre sommets visités Astar : " + star.sommetvisite2);
		System.out.println("Dijkstra visite environs " + 
		dij.sommetvisite/star.sommetvisite2 +" fois plus de sommets");
		
		System.out.println("Temps d'execution Dijkstra :" + Time1 +" ms");
		System.out.println("Temps d'execution Astar :" + Time2 +" ms");
		System.out.println("Astar est environs " + (float)Time1/Time2 +" plus rapide");
		
		//test moyenne echelle
		
		System.out.println("\n-Test 'moyenne echelle'- \n" );
		Time = System.currentTimeMillis();
		for (int i = 0;i<10;i++) {
			dij2.doRun();
		}
		Time3 = (System.currentTimeMillis() - Time)/10;
		
		Time = System.currentTimeMillis();
		for (int i = 0;i<10;i++) {
			star2.doRun();
		}
		Time4 = (System.currentTimeMillis() - Time)/10;
		
		System.out.println("Nombre sommets visités Dijkstra : "+ dij2.sommetvisite);
		System.out.println("Nombre sommets visités Astar : " + star2.sommetvisite2);
		System.out.println("Dijkstra visite environs " + 
		dij2.sommetvisite/star2.sommetvisite2 + " fois plus de sommets");
		
		System.out.println("Temps d'execution Dijkstra :" + Time3 +" ms");
		System.out.println("Temps d'execution Astar :" + Time4 +" ms");
		System.out.println("Astar est environs " + (float)Time3/Time4 +" plus rapide");
		
		//test petite échelle
		
		System.out.println("\n-Test 'petite echelle'- \n" );
		
		Time = System.currentTimeMillis();
		for (int i = 0;i<10;i++) {
			dij3.doRun();
		}
		Time5 = (System.currentTimeMillis() - Time)/10;
		
		Time = System.currentTimeMillis();
		for (int i = 0;i<10;i++) {
			star3.doRun();
		}
		Time6 = (System.currentTimeMillis() - Time)/10;
	
		System.out.println("Nombre sommets visités Dijkstra : "+ dij3.sommetvisite);
		System.out.println("Nombre sommets visités Astar : " + star3.sommetvisite2);
		System.out.println("Dijkstra visite environs " + 
		dij3.sommetvisite/star3.sommetvisite2 +" fois plus de sommets");
		
		System.out.println("Temps d'execution Dijkstra :" + Time5 +" ms");
		System.out.println("Temps d'execution Astar :" + Time6 +" ms");
		System.out.println("Astar est environs " + (float)Time5/Time6 +" plus rapide");
		
		
		//test Haute Garonness
		
		System.out.println("\n-Test sur la 'Haute-Garonne'- \n" );
		
		Time = System.currentTimeMillis();
		for (int i = 0;i<10;i++) {
			dijg.doRun();
		}
		Time3 = (System.currentTimeMillis() - Time)/10;
		
		Time = System.currentTimeMillis();
		for (int i = 0;i<10;i++) {
			starg.doRun();
		}
		Time4 = (System.currentTimeMillis() - Time)/10;
		
		System.out.println("Nombre sommets visités Dijkstra : "+ dijg.sommetvisite);
		System.out.println("Nombre sommets visités Astar : " + starg.sommetvisite2);
		System.out.println("Dijkstra visite environs " + 
		dijg.sommetvisite/starg.sommetvisite2 +" fois plus de sommets");
		
		System.out.println("Temps d'execution Dijkstra :" + Time3 +" ms");
		System.out.println("Temps d'execution Astar :" + Time4 +" ms");
		System.out.println("Astar est environs " + (float)Time3/Time4 +" plus rapide");
		
		
		//test carré dense
		
		System.out.println("\n-Test 'carré dense'- \n" );
		
		Time = System.currentTimeMillis();
		for (int i = 0;i<10;i++) {
			dijc.doRun();
		}
		Time3 = (System.currentTimeMillis() - Time)/10;
		
		Time = System.currentTimeMillis();
		for (int i = 0;i<10;i++) {
			starc.doRun();
		}
		Time4 = (System.currentTimeMillis() - Time)/10;
		
		System.out.println("Nombre sommets visités Dijkstra : "+ dijc.sommetvisite);
		System.out.println("Nombre sommets visités Astar : " + starc.sommetvisite2);
		System.out.println("Dijkstra visite environs " + 
		dijc.sommetvisite/starc.sommetvisite2 +" fois plus de sommets");
		
		System.out.println("Temps d'execution Dijkstra :" + Time3 +" ms");
		System.out.println("Temps d'execution Astar :" + Time4 +" ms");
		System.out.println("Astar est environs " + (float)Time3/Time4 +" plus rapide");
	}
}
		
