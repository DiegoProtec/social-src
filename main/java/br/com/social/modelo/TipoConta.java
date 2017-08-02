package br.com.social.modelo;

public enum TipoConta {

	ADMINISTRADOR(1), COMUM(2);

	private final int tipoConta;

	TipoConta(int tipoConta) {
		this.tipoConta = tipoConta;
	}

	public int getTipoConta() {
		return this.tipoConta;
	}

	public static TipoConta porTipo(int tipo) {
		for (TipoConta tipoConta : TipoConta.values()) {
			if (tipo == tipoConta.getTipoConta())
				return tipoConta;
		}
		throw new IllegalArgumentException("Tipo de conta inválida.");
	}
}
