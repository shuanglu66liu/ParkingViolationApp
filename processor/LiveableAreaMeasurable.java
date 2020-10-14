package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.Property;

public class LiveableAreaMeasurable implements PropertyMeasurable{

	@Override
	public double getMeasures(Property p) {

		return p.getTotalLivableArea();
	}
	

}
