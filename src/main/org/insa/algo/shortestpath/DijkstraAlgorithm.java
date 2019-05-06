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
        /*
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
        		list_label.add(label_origin);
        		
        	}
        	else
        	{
        		Label label = new Label(node.getId(),marque,cout,0);
        		list_label.add(label);
        	}
        }
        */
        
        //insertion du premier label
        Label label_origin = new Label(node_origin.getId(),true,0,0);
        list_label.set(node_origin.getId(), label_origin);
        bin_heap.insert(label_origin);
        
        //algorithme
        while (nb_node_marque < size_graph)
        {
        	//on trouve le plus petit element du tas
        	Label x = bin_heap.deleteMin();
        	Node node_x = graph.get(x.getSommet());
        	//cet element devient "marqué"
        	x.marque = true;
        	list_label.set(node_x.getId(), x);
        	
        	nb_node_marque ++;
        	
        	//cout de cette element
        	double cost_x = x.getCost();
        	
        	List<Arc> successors = node_x.getSuccessors();
        	// pour chacun de ses successeurs
        	for (Arc arc_successor : successors)
        	{
        		Node node_y = arc_successor.getDestination();
        		Label label_y = list_label.get(node_y.getId());
        		
        		boolean exist_prev = true;
        		
        		if (label_y == null)
        		{
        			exist_prev = false;
        			Label init_label_y = new Label(node_y.getId(),false,1e10,0);
        			list_label.set(node_y.getId(), init_label_y);
        		}
        		
        		
        		
        		//si un successeur n'est pas marqué
        		if (label_y.marque == false)
        		{
        			double cost_y = label_y.getCost();
        			if (cost_y > (cost_x + arc_successor.getMinimumTravelTime()))
					{
        				double new_cost = cost_x + arc_successor.getMinimumTravelTime();
						Label new_label_y = new Label(node_y.getId(),false,new_cost,x.getSommet());
						if (exist_prev == true)
						{
							bin_heap.remove(label_y);
						}
						
						list_label.set(node_y.getId(), new_label_y);
						bin_heap.insert(new_label_y);
					}
        				
        		}
        		
        	}
        	
        }
        
        return solution;
    }

}
