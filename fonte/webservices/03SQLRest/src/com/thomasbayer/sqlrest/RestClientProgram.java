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

public class RestClientProgram {
	public static void main(String[] args) {
		int[] customerIds = ObjectFactory.getCustomerIds();

		System.out.println("----------- Teste da Coleção -----------");
		System.out.println("Os primeiros três IDs de clientes: " +
				customerIds[0] + ", " +
				customerIds[1] + ", " +
				customerIds[2]);

		System.out.println("----------- Teste de Cliente -----------");
		Cliente cliente = ObjectFactory.createCustomer(customerIds[1]);
		System.out.println(cliente);

		System.out.println("----------- Teste de Produto -----------");
		Produto produto = ObjectFactory.createProduct(1);
		System.out.println(produto);

		System.out.println("----------- Teste de Pedido -----------");
		Pedido pedido = ObjectFactory.createInvoice(0);
		System.out.println(pedido);

		System.out.println("----------- Pedido items test -----------");
		System.out.println(pedido.getItems());
	}
}