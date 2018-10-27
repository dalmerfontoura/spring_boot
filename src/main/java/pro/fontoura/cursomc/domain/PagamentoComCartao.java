package pro.fontoura.cursomc.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import pro.fontoura.cursomc.domain.enuns.EstadoPagamento;

@Entity
@DiscriminatorValue("CC")
public class PagamentoComCartao extends Pagamento {

	private static final long serialVersionUID = 1L;

	@Column(length = 3)
	private Integer numeroDeParcelas;

	public PagamentoComCartao() {

	}

	/**
	 * @param id
	 * @param estado
	 * @param pedido
	 * @param numeroDeParcelas
	 */
	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
		super(id, estado, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}

	/**
	 * @return the numeroDeParcelas
	 */
	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	/**
	 * @param numeroDeParcelas the numeroDeParcelas to set
	 */
	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}

}
