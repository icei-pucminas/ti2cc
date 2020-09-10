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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.thomasbayer.sqlrest.RestServiceClient.Resource;

public class ObjectFactory {
	private final static RestServiceClient client = new RestServiceClient();

	public static int[] getCustomerIds() {
		return getCollectionIds(Resource.CUSTOMER);
	}

	public static int[] getProductIds() {
		return getCollectionIds(Resource.PRODUCT);
	}

	public static int[] getItemIds() {
		return getCollectionIds(Resource.ITEM);
	}

	public static int[] getInvoiceIds() {
		return getCollectionIds(Resource.INVOICE);
	}

	public static Cliente createCustomer(int id) {
		Document document = client.getResourceItem(Resource.CUSTOMER, id);
		Cliente cliente = new Cliente(id);
		cliente.setNome(getEl(document, "FIRSTNAME"));
		cliente.setSobrenome(getEl(document, "LASTNAME"));
		cliente.setEndereco(getEl(document, "STREET"));
		cliente.setCidade(getEl(document, "CITY"));
		return cliente;
	}

	public static Produto createProduct(int id) {
		Document document = client.getResourceItem(Resource.PRODUCT, id);
		Produto produto = new Produto(id);
		produto.setName(getEl(document, "NAME"));
		produto.setPrice(Double.parseDouble(getEl(document, "PRICE")));
		return produto;
	}

	public static Pedido createInvoice(int id) {
		Document document = client.getResourceItem(Resource.INVOICE, id);
		Pedido pedido = new Pedido(id);
		pedido.setCliente(
				createCustomer(Integer.parseInt(getEl(document, "CUSTOMERID"))));
		pedido.setTotal(Double.parseDouble(getEl(document, "TOTAL")));
		pedido.setItems(createItems(id, pedido));
		return pedido;
	}

	public static List<Item> createItems(int id, Pedido pedido) {
		Item item;
		Produto produto = null;
		int quantity = 0;
		double cost = 0D;
		Document document = client.getResourceItem(Resource.ITEM, id);
		List<Item> items = new ArrayList<Item>();

		if (document.getChildNodes().getLength() == 0)
			return items;
		NodeList children = document.getChildNodes().item(0).getChildNodes();
		// Loop over the XML document
		for (int i = 0; i < children.getLength(); i++) {
			Node node = children.item(i);
			switch (node.getNodeName()) {
			case "PRODUCTID":
				produto = createProduct(Integer.parseInt(node.getTextContent()));
				break;
			case "QUANTITY":
				quantity = Integer.parseInt(node.getTextContent());
				break;
			case "COST":
				cost = Double.parseDouble(node.getTextContent());
				// This is the last line, commit our item to the list
				item = new Item(pedido, produto, quantity, cost);
				items.add(item);
				break;
			default:
				break;
			}
		}

		return items;
	}
	private static int[] getCollectionIds(Resource resource) {
		Document document = client.getResourceCollection(resource);
		NodeList elements = document.getElementsByTagName(resource.name());
		int[] ids = new int[elements.getLength()];
		for (int i = 0; i < ids.length; i++) {
		  ids[i] = Integer.parseInt(((Element) elements.item(i)).getTextContent());
		}
		return ids;
	}

	private static String getEl(Document document, String n) {
		return document.getElementsByTagName(n).item(0).getTextContent();
	}
}