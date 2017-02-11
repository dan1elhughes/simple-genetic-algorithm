package com.rv007602.sga;

public abstract class Fitness {
	public abstract int evaluate(Individual individual);

	int compare(Individual o1, Individual o2) {
		return evaluate(o1) - evaluate(o2);
	}
}
