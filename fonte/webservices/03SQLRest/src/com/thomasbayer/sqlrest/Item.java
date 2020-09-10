/* 
 * Exemplo Cap. 10
 * 
 * Beginning Java Programming: The Object-Oriented Approach
 * 
 * Bart Baesens, Aimee Backiel, Seppe vanden Broucke
 * ISBN: 978-1-118-73949-5
 * March 2015
 */
package com.thomasbayer.sqlrest;

public class Item {
	private Pedido pedido;
	private Produto produto;
	private int quantidade;
	private double custo;

	public Item(Pedido pedido, Produto produto) {
		this.pedido = pedido;
		this.produto = produto;
	}

	public Item(Pedido pedido, Produto produto, int quantidade, double custo) {
		this(pedido, produto);
		this.quantidade = quantidade;
		this.custo = custo;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getCusto() {
		return custo;
	}

	public void setCusto(double custo) {
		this.custo = custo;
	}

	public String toString() {
		return String.format(
			"Item: quantidade: %s, custo: R$ %s" +
			"%n%s" + "%n%s", 
			quantidade, custo, pedido, produto);
	}
}