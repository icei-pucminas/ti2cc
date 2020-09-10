package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.json.JSONObject;

public class BemDeConsumo extends Produto implements Serializable {

	private static final long serialVersionUID = 1L;
	private LocalDate dataValidade;

	public LocalDate getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(LocalDate dataValidade) {
		// a data de fabricação deve ser anterior à data de validade.
		if (getDataFabricacao().isBefore(dataValidade.atStartOfDay()))
			this.dataValidade = dataValidade;
	}

	public BemDeConsumo() {
		super();
		// o default é uma validade de 6 meses.
		dataValidade = LocalDate.now().plusMonths(6);
	}

	public BemDeConsumo(int id, String descricao, float preco, int quantidade, LocalDateTime fabricacao, LocalDate v) {
		super(id, descricao, preco, quantidade, fabricacao);
		setDataValidade(v);
	}

	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa ser
	 * exibido na forma de String.
	 */
	@Override
	public String toString() {
		return super.toString() + "   Data de Validade: " + dataValidade;
	}

	@Override
	public boolean emValidade() {
		return LocalDateTime.now().isBefore(this.getDataValidade().atTime(23, 59));
	}

	@Override
	public JSONObject toJson() {
		return super.toJson().put("dataValidade", this.getDataValidade());
	}
}
