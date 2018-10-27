package pro.fontoura.cursomc.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ItemPedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItemPedidoPK id = new ItemPedidoPK();

	private Integer quantidade;

	private Double preco;

	private Double desconto;

	public ItemPedido() {

	}

	/**
	 * @param produto Entidade {@link Produto}
	 * @param pedido Entidade {@link Pedido}
	 * @param quantidade
	 * @param preco
	 * @param desconto
	 */
	public ItemPedido(Produto produto, Pedido pedido, Integer quantidade, Double preco, Double desconto) {
		super();
		this.id.setPedido(pedido);
		this.id.setProduto(produto);
		this.quantidade = quantidade;
		this.preco = preco;
		this.desconto = desconto;
	}

	/**
	 * @return {@link Produto} Produto
	 */
	public Produto getProduto() {
		return this.id.getProduto();
	}

	/**
	 * @return
	 */
	public Pedido getPedido() {
		return this.id.getPedido();
	}

	/**
	 * @return the id
	 */
	public ItemPedidoPK getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(ItemPedidoPK id) {
		this.id = id;
	}

	/**
	 * @return the quantidade
	 */
	public Integer getQuantidade() {
		return quantidade;
	}

	/**
	 * @param quantidade the quantidade to set
	 */
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * @return the preco
	 */
	public Double getPreco() {
		return preco;
	}

	/**
	 * @param preco the preco to set
	 */
	public void setPreco(Double preco) {
		this.preco = preco;
	}

	/**
	 * @return the desconto
	 */
	public Double getDesconto() {
		return desconto;
	}

	/**
	 * @param desconto the desconto to set
	 */
	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

}
