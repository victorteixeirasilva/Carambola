package modelo;

import java.util.Objects;

public class MinhaMesa {

	private Long id;
	private boolean disponivel;

	public MinhaMesa() {

	}

	public MinhaMesa(Long id, boolean reserva) {
		super();
		this.id = id;
		this.disponivel = reserva;
	}

	public Long getId() {
		return id;
	}

	public boolean isReserva() {
		return disponivel;
	}

	public void setReserva(boolean reserva) {
		this.disponivel = reserva;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MinhaMesa other = (MinhaMesa) obj;
		return Objects.equals(id, other.id);
	}

}
