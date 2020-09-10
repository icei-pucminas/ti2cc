package model;
import java.io.Serializable;
import java.time.LocalDateTime;

import org.json.JSONObject;

/**
 * Classe Produto
 * 
 * @author Hugo de Paula
 *
 */
public abstract class Produto implements Serializable, JsonFormatter  {

	private static final long serialVersionUID = 1L;
	public static final String DESCRICAO_PADRAO = "Novo Produto";
	public static final int MAX_ESTOQUE = 1000;
	private int id;
	private String descricao;
	private float preco;
	private int quantidade;
	private LocalDateTime dataFabricacao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean emEstoque() {
		return (quantidade > 0);
	}

	public abstract boolean emValidade();
	
	public String getDescricao() {
		return descricao;
	}

	public float getPreco() {
		return preco;
	}

	public int getQuant() {
		return quantidade;
	}

	public LocalDateTime getDataFabricacao() {
		return dataFabricacao;
	}

	public void setDescricao(String descricao) {
		if (descricao.length() >= 3)
			this.descricao = descricao;
	}

	public void setPreco(float preco) {
		if (preco > 0)
			this.preco = preco;
	}

	public void setQuant(int quantidade) {
		if (quantidade >= 0 && quantidade <= MAX_ESTOQUE)
			this.quantidade = quantidade;
	}

	public void setDataFabricacao(LocalDateTime dataFabricacao) {
		// Pega a Data Atual
		LocalDateTime agora = LocalDateTime.now();
		// Garante que a data de fabricação não pode ser futura
		if (agora.compareTo(dataFabricacao) >= 0)
			this.dataFabricacao = dataFabricacao;
	}

	public Produto(int id, String descricao, float preco, int quantidade, LocalDateTime fabricacao) {
		setId(id);
		setDescricao(descricao);
		setPreco(preco);
		setQuant(quantidade);
		setDataFabricacao(fabricacao);
	}

	public Produto() {
		descricao = DESCRICAO_PADRAO;
		preco = 0.01F;
		quantidade = 0;
		dataFabricacao = LocalDateTime.now();
	}

	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "Produto: " + descricao + "   Preço: R$" + preco + "   Quant.: " + quantidade + "   Fabricação: "
				+ dataFabricacao;
	}

	@Override
	public boolean equals(Object obj) {
		return (this.getId() == ((Produto) obj).getId());
	}
	
	/**
	 * Converte o Produto para o formato Json
	 */
	@Override
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("id", this.getId());
		obj.put("descricao", this.getDescricao());
		obj.put("preco", this.getPreco());
		obj.put("quant", this.getQuant());
		obj.put("dataFabricacao", this.getDataFabricacao());
		return obj;
	}

}



