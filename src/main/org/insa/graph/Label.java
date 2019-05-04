package org.insa.graph;

public class Label implements Comparable<Label>{
	
	//booléen, vrai lorsque le coût min de ce sommet est définitivement connu par l'algorithme.
	public boolean marque;
	
	public int sommet_courant;
	
	public int cout;
	
	public int pere;
	
	public Label(int init_id_sommet,boolean init_marque,int init_cout,int init_id_pere)
	{
		this.marque = init_marque;
		this.sommet_courant=init_id_sommet;
		this.cout=init_cout;
		this.pere=init_id_pere;
	}
	public int getCost()
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
}
