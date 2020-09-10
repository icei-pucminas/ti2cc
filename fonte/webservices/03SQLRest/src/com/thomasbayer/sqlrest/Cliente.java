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

public class Cliente {
	private int id;
	private String nome, sobrenome;
	private String endereco, cidade;

	public Cliente(int id) {
		this.id = id;
	}

	public Cliente(int id, String nome, String sobrenome,
			String endereco, String cidade) {
		this(id);
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.endereco = endereco;
		this.cidade = cidade;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String toString() {
		return String.format(
			"Cliente (%s): %s %s \n %s, %s\n",
			id, nome, sobrenome, endereco, cidade);
	}
}