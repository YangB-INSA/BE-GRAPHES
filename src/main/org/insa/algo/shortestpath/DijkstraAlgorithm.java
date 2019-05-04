package org.insa.algo.shortestpath;
import org.insa.graph.*;
import java.util.*;
import org.insa.algo.utils.*;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
    	
    	
        ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        
        
        
        Graph graph = data.getGraph();
        int size_graph = graph.size();
        List<Node> nodes = graph.getNodes();
        
        BinaryHeap<Label> bin_heap = new BinaryHeap<Label>();
        List<Label> list_label = new ArrayList<Label>();
        
        Node node_origin = data.getOrigin();
        Node node_destination = data.getDestination();
        int nb_node_marque = 1;
        
        
        // phase d'initialisation
        for (Node node : nodes)
        {
        	boolean marque = false;
        	int cout = 10000;
        	if (node.getId()==node_origin.getId())
        	{
        		marque = true;
        		cout = 0;
        		Label label_origin = new Label(node.getId(),marque,cout,0);
        		bin_heap.insert(label_origin);
        		
        	}
        	Label label = new Label(node.getId(),marque,cout,0);
        	list_label.add(label);
        }
        
        
        
        //algorithme
        while (nb_node_marque < size_graph)
        {
        	Label x = bin_heap.findMin();
        	
        }
        
        return solution;
    }

}
