package org.insa.graph;

public class LabelStar extends Label implements Comparable<Label>{
	
	// coût estimé à la destination
	public double cout_est;
	
	public LabelStar(int init_id_sommet,boolean init_marque,double init_cout,Arc init_pere,double cout_est)
	{
		super(init_id_sommet,init_marque,init_cout,init_pere);
		this.cout_est=cout_est;
	}
	
	@Override
	public double getTotalCost()
	{
		return this.cout + this.cout_est;
	}

	public int compareTo(LabelStar label)
	{
		int result =0;
		if (this.getTotalCost()>label.getTotalCost())
		{
			result = 1;
		}
		if (this.getTotalCost()==label.getTotalCost())
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
		if (this.getTotalCost()<label.getTotalCost())
		{
			result = -1;
		}
		return result;
	}
}