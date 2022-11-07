package Jasper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import bd.Connect;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class Jasper {
	
	// VARIAVEL PARA ARMAZENAR O VALOR TOTAL DOS PRODUTOS, QUE VEM DA CLASSE TELA_CARRINHO
	public static float vtotal;
	
	public Jasper()
	{
		try {
			Connect.getInstance();
			
			PreparedStatement ts = Connect.getInstance().prepareStatement("SELECT MAX(id) FROM Pedido");
			ResultSet rs = ts.executeQuery();
		
			long novoId = 0;
			
			if (rs.next()) {
				novoId = rs.getLong("MAX(id)");
			}
			
			PreparedStatement st = Connect.getInstance().prepareStatement
			(
				"SELECT Cliente.rg, Cliente.nome, Pedido.id, Pedido.data, ItemPedido.quantidade, Produto.nome, Produto.valor "
				+ "FROM Cliente JOIN Pedido ON (Cliente.rg = Pedido.cliente) JOIN ItemPedido ON (ItemPedido.pedido = Pedido.id) "
				+ "JOIN Produto ON (Produto.id = ItemPedido.produto) WHERE Pedido.id = ? "
			);	
		
			st.setLong(1, novoId);
			
			ResultSet sr = st.executeQuery();

			JRResultSetDataSource jrDS = new JRResultSetDataSource(sr);

			Map<String, Object> total = new HashMap<String, Object>();
			total.put("vtotal",String.valueOf(vtotal));
			
			// CAMINHOS PARA O JASPER
			JasperCompileManager.compileReportToFile("./src/Jasper/Blank_A4_2.jrxml");
			JasperPrint jp = JasperFillManager.fillReport("./src/Jasper/Blank_A4_2.jasper", total, jrDS );

			// VISUALIZACAO DO RELATORIO JASPER EM TELA, ATRAVES DO JasperViewer
			JasperViewer jrviewer = new JasperViewer( jp, false );
			jrviewer.setVisible(true);
			JasperExportManager.exportReportToPdfFile(jp,"./Nota" + novoId  +".pdf");

		} 
		catch (SQLException e) {
			System.out.println("Erro SQL: " + e.getMessage());
		}
		catch(JRException e) {
			System.out.println(e.getMessage());
		}
	}

} // End of Jasper class
