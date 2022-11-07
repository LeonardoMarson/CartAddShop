package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PedidoDAO {
	
	public int incluir(Pedido p) {

		int novoId = 0;
		
		try {
			Connect.getInstance();
			PreparedStatement st = Connect.getInstance().prepareStatement("INSERT INTO Pedido Values(null,?,?)");
			st.setTimestamp(1, p.getData());
			st.setLong(2, p.getCliente());
			
			st.executeUpdate();
			
			st = Connect.getInstance().prepareStatement("SELECT MAX(id) FROM Pedido");
			ResultSet rs = st.executeQuery();
		
			 if (rs.next()) {
				 novoId = rs.getInt("MAX(id)");
			 }
			 
		}
		catch (SQLException e) {
			System.out.println("Erro ao carregar: " + e.getMessage());
			Connect.closeCon();
		}

		return novoId;
	}
	
	public Pedido listarPorIdCliente(int id, int cliente) {
		Pedido pedido = new Pedido();
		
		try {
			Connect.getInstance();
			PreparedStatement st = Connect.getInstance().prepareStatement("SELECT * From Pedido WHERE id=? and cliente=?");
			st.setLong(1, id);
			st.setLong(2, cliente);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Pedido p = new Pedido();
				p.setId(rs.getInt("id"));
				p.setData(rs.getTimestamp("data"));
				p.setCliente(rs.getInt("cliente"));
			}
			
		}
		catch (SQLException e) {
			System.out.println("Erro ao carregar: " + e.getMessage());
			Connect.closeCon();
		}

		return pedido;
	}

} // End of PedidoDAO class
