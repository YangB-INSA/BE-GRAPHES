package org.insa.algo.shortestpath;
import org.insa.graph.*;
import java.util.*;

import org.insa.algo.AbstractSolution.Status;
import org.insa.algo.utils.*;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    public ShortestPathSolution doRun() {
    	
        ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
       
        Graph graph = data.getGraph();
        int size_graph = graph.size();
        
        // tas de labels
        BinaryHeap<Label> bin_heap = new BinaryHeap<Label>();
        
        //Liste de labels
        List<Label> list_label = new ArrayList<Label>(size_graph);
        
        //on remplit la liste de labels nuls
        for(int i =0; i < size_graph; i++)
        {
        	list_label.add(null);
        }
        
        Node node_origin = data.getOrigin();
        Node node_destination = data.getDestination();
        
        //insertion du premier label
        Label label_origin = new Label(node_origin.getId(),true,0,null);
        list_label.set(node_origin.getId(), label_origin);
        bin_heap.insert(label_origin);
        notifyOriginProcessed(node_origin);
        boolean arrive = false;
        
        //Tant que le tas n'est pas vide ( 
        while (bin_heap.isEmpty()==false && arrive == false)
        {
        	//on trouve le plus petit element du tas
        	Label x = bin_heap.deleteMin();
        	Node node_x = graph.get(x.getSommet());	
        	
        	//cet element devient "marqu�"
        	x.marque = true;
        	list_label.set(node_x.getId(), x);
        	notifyNodeMarked(node_x);
        	
        	//Si on attend la destination, l'algorithme s'arr�te
        	if (node_x == node_destination)
        	{
        		arrive = true;
        	}
        	
        	//cout de cette element
        	double cost_x = x.getCost();
        	
        	List<Arc> successors = node_x.getSuccessors();
        	
        	// On parcourt tout ses successeurs
        	for (Arc arc_successor : successors)
        	{
        		Node node_y = arc_successor.getDestination();
        		Label label_y = list_label.get(node_y.getId());
        		
        		boolean exist_prev = true;
        	
        		//si il n'y avait pas de label associ� � ce noeud
        		if (label_y == null)
        		{
        			exist_prev = false;
        			notifyNodeReached(node_y);
        			// on cr�e le label et on le met dans la liste
        			Label init_label_y = new Label(node_y.getId(),false,1e10,null);
        			list_label.set(node_y.getId(), init_label_y);
        			label_y = init_label_y;	
        		}
        		
        		// si le successeur n'est pas marqu�
        		if (label_y.marque == false)
        		{
        			double cost_y = label_y.getCost();
        			if (cost_y > (cost_x + data.getCost(arc_successor)))
					{
        				double new_cost = cost_x + data.getCost(arc_successor);
						Label new_label_y = new Label(node_y.getId(),false,new_cost,arc_successor);
						// si le label etait dans le tas
						// on l'enl�ve du tas
						if (exist_prev == true)
						{
							bin_heap.remove(label_y);
						}
						// on ajoute le label � la liste et au tas
						list_label.set(node_y.getId(), new_label_y);
						bin_heap.insert(new_label_y);
					}
        		}
        	}
        }
        
        // Destination has no predecessor, the solution is infeasible...
        if (arrive == false) {
            solution = new ShortestPathSolution(data, Status.INFEASIBLE);
            
        }
        else {

            // The destination has been found, notify the observers.
            notifyDestinationReached(node_destination);

            // Create the path from the array of predecessors...
         
            ArrayList<Arc> arcs = new ArrayList<>();
            Arc arc = list_label.get(node_destination.getId()).getArcPere();
            Node node_pere = null;
            
            
            while (arc != null) {
                arcs.add(arc);
                node_pere = arc.getOrigin();
                arc = list_label.get(node_pere.getId()).getArcPere();
            }

            // Reverse the path...
            Collections.reverse(arcs);

            // Create the final solution.
            solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, arcs));
            
        }

        return solution;
    }
}
