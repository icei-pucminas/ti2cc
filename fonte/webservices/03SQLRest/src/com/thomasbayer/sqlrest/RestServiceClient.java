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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class RestServiceClient {
	// Endereço base para o acesso ao Web Service
	private final static String URL_API_ROOT = "http://www.thomas-bayer.com/sqlrest/";

	// Define o tipo enumerado com os quatro serviços disponibilizados
	public enum Resource {
		CUSTOMER, PRODUCT, INVOICE, ITEM
	};

	// Busca no servidor uma coleção de elementos de um serviço.
	// O comando getHttpUrl está recebendo o endereço montado de acesso ao web service.
	public Document getResourceCollection(Resource resource) {
		return stringToXMLDocument(getHttpUrl(URL_API_ROOT + resource.name()));
	}

	// Busca no servidor um único recurso de um serviço.
	// O comando getHttpUrl está recebendo o endereço montado de acesso ao web service.
	public Document getResourceItem(Resource resource, int itemId) {
		return stringToXMLDocument(
		  getHttpUrl(URL_API_ROOT + resource.name() + "/" + itemId));
	}

	public String getHttpUrl(String url) {
		HttpURLConnection connection = null;
		try {
			URL u = new URL(url);
			connection = (HttpURLConnection) u.openConnection();
			
			// Esse exemplo fará apenas requisições do tipo GET para o serviço
			connection.setRequestMethod("GET");
			connection.connect();
			int responseCode = connection.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_OK) {
				// Se não receber o código 200 (OK) então houve erro no servidor.
				System.err.println("Servidor retornou código de erro: " + responseCode);
				return null;
			}
			
			// Recupera a resposta do servidor.
			StringBuilder stringBuilder = new StringBuilder();
			
			// getInputStream representa dados retornando do servidor.
			// getOutputStream é utilizado para enviar dados ao servidor.
			try (BufferedReader reader = new BufferedReader(
					new InputStreamReader(connection.getInputStream(), "UTF-8"))) {
				String s;
				while ((s = reader.readLine()) != null) {
					stringBuilder.append(s + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			connection.disconnect();
			return stringBuilder.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				connection.disconnect();
		}
		return null;
	}

	static public Document stringToXMLDocument(String xmlText) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(
					new StringReader(xmlText)));
			return document;
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String args[]) throws TransformerException {
		
		// Testando o RestServiceClient
		RestServiceClient client = new RestServiceClient();

		// Transformer converte o documento SML em uma String
		Document d;
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		
		// Testa a URL de uma coleção de itens
		StringWriter writer = new StringWriter();
		d = client.getResourceCollection(Resource.CUSTOMER);
		transformer.transform(new DOMSource(d), new StreamResult(writer));
		System.out.println(writer);

		// Testa a URL de um recurso
		writer = new StringWriter();
		d = client.getResourceItem(Resource.PRODUCT, 4);
		transformer.transform(new DOMSource(d), new StreamResult(writer));
		System.out.println(writer);
	}
}