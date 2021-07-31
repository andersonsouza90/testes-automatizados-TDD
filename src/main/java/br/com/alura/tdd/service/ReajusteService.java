package br.com.alura.tdd.service;

import java.math.BigDecimal;

import br.com.alura.tdd.modelo.Desempenho;
import br.com.alura.tdd.modelo.Funcionario;

public class ReajusteService {
	
	public BigDecimal calcularReajuste(Funcionario funcionario, Desempenho desempenho) {
	/*if(desempenho == Desempenho.A_DESEJAR) {
		BigDecimal reajuste = funcionario.getSalario().multiply(new BigDecimal("0.03"));
		funcionario.reajustarSalario(reajuste);		
		return funcionario.getSalario();
	}else if(desempenho == Desempenho.BOM) {
		BigDecimal reajuste = funcionario.getSalario().multiply(new BigDecimal("0.1"));
		funcionario.reajustarSalario(reajuste);	
		return funcionario.getSalario();
	}else {
		BigDecimal reajuste = funcionario.getSalario().multiply(new BigDecimal("0.2"));
		funcionario.reajustarSalario(reajuste);	
		return funcionario.getSalario();
	}*/
	
	//Refactoring
	if (funcionario.getSalario().compareTo(new BigDecimal("1000")) > 0) {
		throw new IllegalArgumentException("Funcionário com salário maior que R$ 1.000,00");
	}
	BigDecimal percentual = desempenho.percentualReajuste();
	BigDecimal reajuste = funcionario.getSalario().multiply(percentual);
	funcionario.reajustarSalario(reajuste);
	return funcionario.getSalario();
	
	}

}
