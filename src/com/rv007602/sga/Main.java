package com.rv007602.sga;

import java.io.FileWriter;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws Exception {
		int generation = 0;
		int survivors = 14;
		int populationSize = 20;
		float mutationRate = 0.2f;
		float crossoverRate = 0.5f;

		Individual.genotypeLength = 8;

		Population population = new Population(Individual.class, populationSize);

		Fitness f = new Fitness() {
			public int evaluate(Individual individual) {
				int n = individual.getPhenotype();

				int expected = 40010;
				int actual = (n * n) + 10; // 200^2 + 10
				return Math.abs(expected - actual);
			}
		};

		population.setFitnessComparator(f);

		Individual fittest = population.getFittest();

		FileWriter output = new FileWriter("out.csv");

		String headings = "Generation,";

		for (int i = 0; i < populationSize; i++) {
			headings += "Fitness" + (i+1) + ",";
		}

		output.write(headings + "\n");

		while (population.getFittest().getFitness(f) > 0 && generation < 3000) {
			generation += 1;
			Debug.population(population, generation, f);

			ArrayList<Individual> parents = population.select(survivors);

			ArrayList<Individual> children = Breeder.crossover(parents, crossoverRate);
			ArrayList<Individual> mutated = Breeder.mutate(children, mutationRate);

			population.addAll(mutated);
			population.cull();

			fittest = population.getFittest();

			output.write(String.format("%d,%s\n", generation, population));
		}

		output.flush();
		System.out.format("%d :: Closest solution: %d (%s)", generation, fittest.getPhenotype(), fittest.getGenotype());

	}
}
