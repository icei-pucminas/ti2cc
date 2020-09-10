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

import java.util.ArrayList;
import java.util.List;

public class Pedido {
	private int id;
	private Cliente cliente;
	private double total;
	private final List<Item> itens;

	public Pedido(int id) {
		this.id = id;
		this.itens = new ArrayList<Item>();
	}

	public Pedido(int id, Cliente cliente, double total, List<Item> itens) {
		this(id);
		this.cliente = cliente;
		this.total = total;
		this.itens.addAll(itens);
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getId() {
		return id;
	}

	public void addItem(Item item) {
		itens.add(item);
	}

	public void removeItem(Item item) {
		itens.remove(item);
	}

	public List<Item> getItems() {
		return new ArrayList<Item>(itens);
	}

	public void setItems(List<Item> items) {
		this.itens.clear();
		this.itens.addAll(items);
	}

	public String toString() {
		return String.format(
			"Pedido (%s): total R$ %s" +
			"%n%s",
			id, total, cliente);
	}
}