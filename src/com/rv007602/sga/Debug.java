package com.rv007602.sga;

public class Debug {
	public static void crossover(String oldGenotype1, String oldGenotype2, String newGenotype1, String newGenotype2) {
		int oldPhenotype1 = Integer.parseInt(oldGenotype1, 2);
		int oldPhenotype2 = Integer.parseInt(oldGenotype2, 2);
		int newPhenotype1 = Integer.parseInt(newGenotype1, 2);
		int newPhenotype2 = Integer.parseInt(newGenotype2, 2);
		System.out.format(
				"Crossover: %d (%s) and %d (%s) to %d (%s) and %d (%s)\n",
				oldPhenotype1,
				oldGenotype1,
				oldPhenotype2,
				oldGenotype2,
				newPhenotype1,
				newGenotype1,
				newPhenotype2,
				newGenotype2
		);
	}

	public static void mutate(int oldPhenotype, String oldGenotype, int phenotype, String genotype) {
		System.out.format("Mutated %d (%s) to %d (%s)\n", oldPhenotype, oldGenotype, phenotype, genotype);
	}

	public static void population(Population population, int generation, Fitness f) {
		Individual fittest = population.getFittest();
		System.out.println("\n" + population);
		System.out.format("%d :: Fittest: %d (%d away)\n", generation, fittest.getPhenotype(), fittest.getFitness(f));
	}
}
