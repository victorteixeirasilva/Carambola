package br.fam.Carambola;

import java.sql.SQLException;

import br.fam.Carambola.Db.ConnectionDb;

public class MesaComanda {
	private int numeroMesa;
	private boolean estaDisponivel;
	private ConnectionDb conn = new ConnectionDb();
	
	
	public MesaComanda(int numeroMesa, int idUsuarioEstabelecimento) throws SQLException {
		this.numeroMesa = numeroMesa;
		conn.insert("");
	}
	
	public int getNumeroMesa() {
		return numeroMesa;
	}
	public void setNumeroMesa(int numeroMesa) {
		this.numeroMesa = numeroMesa;
	}

	public boolean isEstaDisponivel() {
		return estaDisponivel;
	}

	public void setEstaDisponivel(boolean estaDisponivel) {
		this.estaDisponivel = estaDisponivel;
	}
	
	
	
}
