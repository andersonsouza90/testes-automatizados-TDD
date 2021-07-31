package br.com.alura.tdd.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.tdd.modelo.Desempenho;
import br.com.alura.tdd.modelo.Funcionario;

class BonusServiceTest {
	
	private Funcionario funcionario;
	private ReajusteService service;
	
	@BeforeEach
	public void inicializar() {
		this.funcionario = new Funcionario("Anderson", LocalDate.now(), new BigDecimal(1000));
		this.service = new ReajusteService();
	}

	@Test
	public void reajusteDeveriaSerTresPorcentoQuandoDesempenhoForADesejar() {
		service.calcularReajuste(funcionario, Desempenho.A_DESEJAR);
		assertEquals(new BigDecimal("1030.00"), funcionario.getSalario());
	}
	
	@Test
	public void reajusteDeveriaSerDezPorcentoQuandoDesempenhoForBom() {
		service.calcularReajuste(funcionario, Desempenho.BOM);
		assertEquals(new BigDecimal("1100.00"), funcionario.getSalario());
	}
	
	@Test	
	public void reajusteDeveriaSerVintePorcentoQuandoDesempenhoForOtimo() {
		service.calcularReajuste(funcionario, Desempenho.OTIMO);
		assertEquals(new BigDecimal("1200.00"), funcionario.getSalario());
	}
	
	@Test	
	public void deveRetornarErroPorSalarioMaiorQue1000() {
		this.funcionario.setSalario(new BigDecimal("2000"));
		assertThrows(IllegalArgumentException.class, 
				() -> service.calcularReajuste(funcionario, Desempenho.OTIMO));
		
		//outra forma de testar throws
		
		try {
			service.calcularReajuste(funcionario, Desempenho.OTIMO);
			fail("Não deu a exception");
		} catch (Exception e) {
			assertEquals("Funcionário com salário maior que R$ 1.000,00", e.getMessage());
		}
	}

}
