package com.rv007602.sga;

public class Individual {
	public static int genotypeLength;
	private String genotype;

	public Individual() {
		String nextCharacter;

		this.genotype = "";

		for (int i = 0; i < Individual.genotypeLength; i++) {
			nextCharacter = (Math.random() < 0.5) ? "1" : "0";
			this.genotype += nextCharacter;
		}
	}

	public Individual(String genotype) {
		this.setGenotype(genotype);
	}

	int getFitness(Fitness f) {
		return f.evaluate(this);
	}

	public String toString() {
		return String.valueOf(this.getPhenotype());
	}

	public String getGenotype() {
		return this.genotype;
	}

	public void setGenotype(String genotype) {
		this.genotype = genotype;
	}

	public int getPhenotype() {
		return Integer.parseInt(this.getGenotype(), 2);
	}

	public char getGene(int index) {
		return this.getGenotype().charAt(index);
	}

	public void setGene(int index, char gene) {
		StringBuilder genotype = new StringBuilder(this.getGenotype());
		genotype.setCharAt(index, gene);
		this.setGenotype(genotype.toString());
	}

	public void mutate() {
		String oldGenotype = this.getGenotype();
		int oldPhenotype = this.getPhenotype();

		int index = (int) (Math.random() * ((this.getGenotype().length())));

		char oldGene = this.getGene(index);
		char newGene = oldGene == '0' ? '1' : '0';
		this.setGene(index, newGene);

		Debug.mutate(oldPhenotype, oldGenotype, this.getPhenotype(), this.getGenotype());
	}
}
