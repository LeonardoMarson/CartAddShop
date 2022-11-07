package bd;

import java.sql.Timestamp;
import java.util.Objects;

public class Pedido {

	private int id;
	private Timestamp data;
	private long cliente;
	
	// GETTER AND SETTER FOR ID
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	// GETTER AND SETTER FOR DATA
	public Timestamp getData() {
		return data;
	}
	public void setData(Timestamp data) {
		this.data = data;
	}

	// GETTER AND SETTER FOR CLIENTE
	public long getCliente() {
		return cliente;
	}
	public void setCliente(long cliente) {
		this.cliente = cliente;
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
		Pedido other = (Pedido) obj;
		return id == other.id;
	}

} // End of Pedido class
