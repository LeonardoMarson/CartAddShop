package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

	public void alterar(Produto p) {

		try {
			
			Connect.getInstance();
			PreparedStatement st = Connect.getInstance().prepareStatement("UPDATE Produto SET nome=?, valor=?, quantidade=?, description=?  WHERE id=?");
			st.setString(1, p.getNome());
			st.setFloat(2, p.getValor());
			st.setInt(3, p.getQuantidade());
			st.setString(4, p.getDescription());
			st.setInt(5, p.getId());
				
			st.executeUpdate();
			
		}
		catch (SQLException e) {
			System.out.println("Erro ao carregar: " + e.getMessage());
			Connect.closeCon();
		}
	}

	public void incluir(Produto p) {

		try {
			Connect.getInstance();
			PreparedStatement st = Connect.getInstance().prepareStatement("INSERT INTO Produto Values(?,?,?,?,?,?)");
			st.setInt(1, p.getId());
			st.setString(2, p.getNome());
			st.setFloat(3, p.getValor());
			st.setInt(4, p.getQuantidade());
			st.setString(5, p.getDescription());
			st.setInt(6, 0);
			
			st.executeUpdate();
			
		}	
		catch (SQLException e) {
			System.out.println("Erro ao carregar: " + e.getMessage());
			Connect.closeCon();
		}
	}

	public void excluir(int id) {
		
		try {
			Connect.getInstance();
			PreparedStatement st = Connect.getInstance().prepareStatement("UPDATE Produto SET remover = ? WHERE id = ?");
			int remover = 1;
			st.setInt(1, remover);
			st.setInt(2, id);
			
			st.executeUpdate();
			
		}
		catch (SQLException e) {
			System.out.println("Erro ao carregar: " + e.getMessage());
			Connect.closeCon();
		}
	}
	
	public List<Produto> listar() {

		List<Produto> produtos = new ArrayList<Produto>();
		
		try {
			Connect.getInstance();
			PreparedStatement st = Connect.getInstance().prepareStatement("SELECT * From Produto WHERE remover = 0");
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Produto p = new Produto();
				p.setId(rs.getInt("id"));
				p.setNome(rs.getString("nome"));
				p.setValor(rs.getFloat("valor"));
				p.setQuantidade(rs.getInt("quantidade"));
				p.setDescription(rs.getString("description"));
				produtos.add(p);
				
			}

			
		}	
		catch (SQLException e) {
			System.out.println("Erro ao carregar: " + e.getMessage());
			Connect.closeCon();
		}

		return produtos;
	}
	
	public Produto listarPorId(int id) {

		Produto p = new Produto();

		try {
			Connect.getInstance();
			PreparedStatement st = Connect.getInstance().prepareStatement("SELECT * From Produto WHERE id=? AND remover = 0");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				p.setId(rs.getInt("id"));
				p.setNome(rs.getString("nome"));
				p.setValor(rs.getFloat("valor"));
				p.setQuantidade(rs.getInt("quantidade"));
				p.setDescription(rs.getString("description"));
			}
			
			
		}	
		catch (SQLException e) {
			System.out.println("Erro ao carregar: " + e.getMessage());
			Connect.closeCon();
		}
		
		return p;
	}

	public void alterarQuantCar(List<Produto> l) {

		try {
			
			Connect.getInstance();
			PreparedStatement st = Connect.getInstance().prepareStatement("UPDATE Produto SET quantidade=? WHERE id=?");
			for (Produto p : l) {

				st.setInt(1, p.getQuantidade());
				st.setInt(2, p.getId());
				st.executeUpdate();
			}
				
			st.executeUpdate();
			
		}
		catch (SQLException e) {
			System.out.println("Erro ao carregar: " + e.getClass().getName());
			Connect.closeCon();
		}
	}


} // End of ProdutoDAO class
