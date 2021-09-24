package br.com.paulo.matematica;

public class MatematicaSimples {
	
	
	public Double soma (Double pNumeroUm, Double pNumeroDois) {
		return pNumeroUm + pNumeroDois;
	}
	public Double subtracao (Double pNumeroUm, Double pNumeroDois) {
		return pNumeroUm - pNumeroDois;
	}
	public Double divisao (Double pNumeroUm, Double pNumeroDois) {
		return pNumeroUm / pNumeroDois;
	}
	public Double multiplicacao (Double pNumeroUm, Double pNumeroDois) {
		return pNumeroUm * pNumeroDois;
	}
	public Double media (Double pNumeroUm, Double pNumeroDois) {
		return (pNumeroUm + pNumeroDois)/2;
	}
	public Double raizQuadrada (Double pNumeroUm) {
		return Math.sqrt(pNumeroUm) ;
	}
	
}
