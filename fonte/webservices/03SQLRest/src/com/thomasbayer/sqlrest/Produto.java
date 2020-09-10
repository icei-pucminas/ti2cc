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

public class Produto {
	private int id;
	private String nome;
	private double preco;

	public Produto(int id) {
		this.id = id;
	}

	public Produto(int id, String name, double price) {
		this(id);
		this.nome = name;
		this.preco = price;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return nome;
	}

	public void setName(String name) {
		this.nome = name;
	}

	public double getPrice() {
		return preco;
	}

	public void setPrice(double price) {
		this.preco = price;
	}

	public String toString() {
		return String.format(
			"Produto (%s): %s: R$ %s",
			id, nome, preco);
	}
}