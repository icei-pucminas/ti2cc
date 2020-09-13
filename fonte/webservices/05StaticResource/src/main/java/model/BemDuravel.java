package model;
import java.io.Serializable;
import java.time.LocalDateTime;

public class BemDuravel extends Produto implements Serializable {

	private static final long serialVersionUID = 1L;
	private int mesesGarantia;

	public int getMesesGarantia() {
		return mesesGarantia;
	}

	public void setMesesGarantia(int mesesGarantia) {
		if (mesesGarantia > 0)
			this.mesesGarantia = mesesGarantia;
	}

	public BemDuravel() {
		super();
		// o valor default é garantia de 6 meses.
		mesesGarantia = 6;
	}

	public BemDuravel(int id, String descricao, float preco, int quantidade, LocalDateTime fabricacao, int garantia) {
		super(id, descricao, preco, quantidade, fabricacao);
		setMesesGarantia(garantia);
	}
	
	/**
	 * Método sobreposto da classe Object.
	 * É executado quando um objeto precisa ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return super.toString() + "   Garantia: " + mesesGarantia;
	}

	@Override
	public boolean emValidade() {
		LocalDateTime vencimento = this.getDataFabricacao().plusMonths(mesesGarantia);
		return LocalDateTime.now().isBefore(vencimento);
	}


}
