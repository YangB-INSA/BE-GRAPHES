package org.insa.graph;


public class LabelStar implements Comparable<LabelStar>{
	
	//booléen, vrai lorsque le coût min de ce sommet est définitivement connu par l'algorithme.
	public boolean marque;
	
	public int sommet_courant;
	
	public double cout;
	
	public Arc pere;
	
	public double cout_est;
	
	public LabelStar(int init_id_sommet,boolean init_marque,double init_cout,Arc init_pere,double init_cout_est)
	{
		this.marque = init_marque;
		this.sommet_courant=init_id_sommet;
		this.cout=init_cout;
		this.pere=init_pere;
		this.cout_est = init_cout_est;
	}
	
	public double getCost()
	{
		return this.cout;
	}
	public int compareTo(LabelStar label)
	{
		int result =0;
		if (this.get_total_cost()>label.get_total_cost())
		{
			result = 1;
		}
		if (this.get_total_cost()==label.get_total_cost())
		{
			if (this.cout_est > label.cout_est)
			{
				return 1;
			}
			else
			{
				return -1;
			}
		}
		if (this.get_total_cost()<label.get_total_cost())
		{
			result = -1;
		}
		return result;
	}
	
	public double get_total_cost()
	{
		return this.cout + this.cout_est;
	}

	public int getSommet()
	{
		return this.sommet_courant;
	}
	public Arc getArcPere()
	{
		return this.pere;
    }
	
	
}