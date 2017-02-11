package com.rv007602.sga;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;

public class Population {
	private Fitness fitnessFunction;
	private ArrayList<Individual> population;
	private int populationSize;

	public Population(Class<Individual> individualClass, int populationSize) throws Exception {
		this.population = new ArrayList<>();
		this.populationSize = populationSize;
		Constructor<Individual> constructor = individualClass.getConstructor();

		for (int i = 0; i < this.populationSize; i++) {

			Individual individual = constructor.newInstance();
			this.add(individual);
		}
	}

	public ArrayList<Individual> select(int selectionSize) {
		this.sort();
		return new ArrayList<>(this.population.subList(0, selectionSize));
	}

	public void add(Individual individual) {
		this.population.add(individual);
	}

	public void addAll(ArrayList<Individual> individuals) {
		this.population.addAll(individuals);
	}

	public void sort() {
		Collections.sort(this.population, (o1, o2) -> this.fitnessFunction.compare(o1, o2));
	}

	public void cull() {
		this.sort();
		while(this.population.size() > this.populationSize) {
			this.population.remove(this.population.size()-1);
		}
	}

	public String toString() {
		String str = "";
		String delimiter = "";

		for (Individual i : this.population) {
			str += delimiter + i;
			delimiter = ",";
		}

		return str;
	}

	public Individual getFittest() {
		this.sort();
		return this.population.get(0);
	}

	public void setFitnessComparator(Fitness f) {
		this.fitnessFunction = f;
	}
}
