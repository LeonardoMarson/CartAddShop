package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class ItemPedidoDAO {

	public void incluir(List<ItemPedido> l) {

		try {
			Connect.getInstance();
			PreparedStatement st = Connect.getInstance().prepareStatement("INSERT INTO ItemPedido Values(null,?,?,?)");
			
			for (ItemPedido ip : l) {

				System.out.println(" dao " + ip.getProduto());

				st.setInt(1, ip.getPedido());
				st.setInt(2, ip.getProduto());
				st.setInt(3, ip.getQuantidade());
				st.executeUpdate();
			}

		}	
		catch (SQLException e) {
			System.out.println("Erro ao carregar: " + e.getMessage());
			Connect.closeCon();;
		}
	}

	public ItemPedido listarPorPedido(int pedido) {
		
		ItemPedido item = new ItemPedido();
		
		try {
			Connect.getInstance();
			PreparedStatement st = Connect.getInstance().prepareStatement("SELECT * From Item pedido WHERE pedido=?");
			st.setInt(1, pedido);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				ItemPedido c = new ItemPedido();
				c.setId(rs.getInt("id"));
				c.setPedido(rs.getInt("pedido"));
				c.setProduto(rs.getInt("produto"));
				c.setQuantidade(rs.getInt("Quantidade"));
			}
			
		}
		catch (SQLException e) {
			System.out.println("Erro ao carregar: " + e.getMessage());
			Connect.closeCon();;
		}

		return item;
	}

} // End of ItemPedidoDAO class
