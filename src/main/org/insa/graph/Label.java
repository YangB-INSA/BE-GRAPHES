package org.insa.graph;
import java.util.Comparator;

public class Label implements Comparable<Label>{
	
	//booléen, vrai lorsque le coût min de ce sommet est définitivement connu par l'algorithme.
	public boolean marque;
	
	public int sommet_courant;
	
	public double cout;
	
	public int pere;
	
	public Label(int init_id_sommet,boolean init_marque,double init_cout,int init_id_pere)
	{
		this.marque = init_marque;
		this.sommet_courant=init_id_sommet;
		this.cout=init_cout;
		this.pere=init_id_pere;
	}
	public double getCost()
	{
		return this.cout;
	}
	public int compareTo(Label label)
	{
		int result =0;
		if (this.getCost()>label.getCost())
		{
			result = 1;
		}
		if (this.getCost()==label.getCost())
		{
			result = 0;
		}
		if (this.getCost()<label.getCost())
		{
			result = -1;
		}
		return result;
	}
	
	public int getSommet()
	{
		return this.sommet_courant;
	}
    public static Comparator<Label> ComparatorSommet = new Comparator<Label>() {
        
        @Override
        public int compare(Label l1, Label l2) {
            return (l1.getSommet()-l2.getSommet());
        }
    };
	
	
}
