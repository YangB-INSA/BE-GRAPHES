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
        int nb_node_marque = 0;
        
        
        // phase d'initialisation
        for (Node node : nodes)
        {
        	boolean marque = false;
        	double cout = 1e10;
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
        
        Collections.sort(list_label, Label.ComparatorSommet);
        
        
        
        //algorithme
        while (nb_node_marque < size_graph)
        {
        	Label x = bin_heap.findMin();
        	Node node_x = graph.get(x.getSommet());
        	x.marque = true;
        	list_label.set(node_x.getId(), x);
        	
        	
        	double cost_x = x.getCost();
        	
        	List<Arc> successors = node_x.getSuccessors();
        	for (Arc arc_successor : successors)
        	{
        		Node node_y = arc_successor.getDestination();
        		Label label_y = list_label.get(node_y.getId());
        		if (label_y.marque == false)
        		{
        			double cost_y = label_y.getCost();
        			if (cost_y > (cost_x + arc_successor.getMinimumTravelTime()))
					{
        				double new_cost = cost_x + arc_successor.getMinimumTravelTime();
						Label new_label_y = new Label(node_y.getId(),false,new_cost,x.getSommet());
						
						
						list_label.set(node_y.getId(), new_label_y);
						bin_heap.insert(new_label_y);
					}	
        				
        		}
        		
        	}
        	
        }
        
        return solution;
    }

}
