package com.rv007602.sga;

import java.util.ArrayList;

public class Breeder {

	public static ArrayList<Individual> crossover(ArrayList<Individual> population, float crossoverRate) {
		ArrayList<Individual[]> parents = Breeder.getPairs(population);
		ArrayList<Individual> children = new ArrayList<>();

		String parent1;
		String parent2;
		String child1;
		String child2;

		for (Individual[] pair : parents) {
			parent1 = pair[0].getGenotype();
			parent2 = pair[1].getGenotype();
			child1 = parent1;
			child2 = parent2;

			for (int i = 0; i < child1.length(); i++) {
				boolean swap = (Math.random() < crossoverRate);
				if (swap) {
					char char1 = child1.charAt(i);
					char char2 = child2.charAt(i);

					StringBuilder child1sb = new StringBuilder(child1);
					child1sb.setCharAt(i, char2);
					child1 = child1sb.toString();

					StringBuilder child2sb = new StringBuilder(child2);
					child2sb.setCharAt(i, char1);
					child2 = child2sb.toString();
				}
			}

			Debug.crossover(parent1, parent2, child1, child2);

			children.add(new Individual(child1));
			children.add(new Individual(child2));
		}

		return children;
	}

	public static ArrayList<Individual> mutate(ArrayList<Individual> population, float mutationRate) {

		for (Individual individual : population) {
			boolean shouldMutate = Math.random() < mutationRate;
			if (shouldMutate) {
				individual.mutate();
			}
		}

		return population;
	}

	private static ArrayList<Individual[]> getPairs(ArrayList<Individual> population) {
		ArrayList<Individual[]> pairs = new ArrayList<>();

		ArrayList<Individual> pair = new ArrayList<>();

		Individual parent;

		while (population.size() > 0) {
			int index = (int) ((Math.random() * population.size()));
			parent = population.get(index);
			population.remove(index);
			pair.add(parent);

			if (pair.size() == 2) {
				Individual[] parents = new Individual[2];
				parents[0] = pair.get(0);
				parents[1] = pair.get(1);
				pairs.add(parents);
				pair.clear();
			}
		}

		return pairs;
	}
}
