package tela;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import bd.ItemPedido;
import bd.ItemPedidoDAO;
import bd.Pedido;
import bd.PedidoDAO;
import bd.Produto;
import bd.ProdutoDAO;
import Jasper.Jasper;

public class Tela_Carrinho extends JFrame implements ActionListener, MouseListener, WindowListener {
	private JButton finalizar = new JButton("Finalizar");
	private JButton delete = new JButton("Excluir");
	private JButton edit = new JButton("Editar");
	private JButton add = new JButton("Adicionar ao carrinho");
	private JButton help = new JButton("Ajuda");
	private JButton logOff = new JButton("Sair");
	private JLabel name = new JLabel("Produtos");
	private JLabel name2 = new JLabel("Carrinho");
	private JPanel panel = new JPanel();
	private JPanel panel2 = new JPanel();
	private JPanel panelTitulo = new JPanel();
	private JPanel panelBotoes1 = new JPanel();
	private JPanel panelBotoes2 = new JPanel();
	private JPanel panelBotoes3 = new JPanel();
	private JPanel panelBotoes4 = new JPanel();
    private JTextField valorTotal = new JTextField(7);
	private JLabel valorLabel = new JLabel("Valor Total: ");
	private JLabel branco = new JLabel("");
    private JLabel branco2 = new JLabel("");
	
	public static List<Produto> produtoss = new ArrayList<Produto>();
	private List_Products lp = new List_Products();
	private JTable table = new JTable(lp);
	
	public static List<Produto> carrinhoss = new ArrayList<Produto>();
	private List_Carrinho lc = new List_Carrinho();
	private JTable table2 = new JTable(lc);

	private JScrollPane scroll = new JScrollPane(table);
	private JScrollPane scroll2 = new JScrollPane(table2);
	
	static int result;
	static int linhaCarrinho = -1;
	static int linhaProduto = -1;
	static long logCliente;
    
	public Tela_Carrinho() {
		ProdutoDAO dao = new ProdutoDAO();
		produtoss.addAll(dao.listar());

		setLayout(new BorderLayout());
		setVisible(true);
		setSize(800, 600);
		setResizable(false);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/img/icon.png"));
		setIconImage(icon.getImage());

		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		table2.getTableHeader().setReorderingAllowed(false);
		table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		name.setFont(new Font("SansSerif", Font.BOLD, 35));
		name.setHorizontalAlignment(JLabel.CENTER);
		name2.setFont(new Font("SansSerif", Font.BOLD, 35));
		name2.setHorizontalAlignment(JLabel.CENTER);


		getContentPane().add(panelTitulo, BorderLayout.PAGE_START);
		getContentPane().add(panel2, BorderLayout.CENTER);
		getContentPane().add(panel, BorderLayout.PAGE_END);
		
		panel.setLayout(new GridLayout(2,2));
		panel.add(panelBotoes4);
		panelBotoes4.setLayout(new GridLayout(1,2));
		panelBotoes4.add(help);
		panelBotoes4.add(add);

		
		panel.add(panelBotoes2);
		panelBotoes2.setLayout(new GridLayout(1,2));
		panelBotoes2.add(valorLabel);
		valorLabel.setHorizontalAlignment(JLabel.RIGHT);
		panelBotoes2.add(valorTotal);
		valorTotal.setEditable(false);
		
		panel.add(panelBotoes3);
		panelBotoes3.setLayout(new GridLayout(1,4));
		panelBotoes3.add(branco);
		panelBotoes3.add(logOff);
		panelBotoes3.add(branco2);

		panel.add(panelBotoes1);
		panelBotoes1.setLayout(new GridLayout(1,3));
		panelBotoes1.add(delete);
		panelBotoes1.add(edit);
		panelBotoes1.add(finalizar);

		
		panel2.setLayout(new GridLayout(1,2));
		panel2.add(scroll);
		panel2.add(scroll2);

		panelTitulo.setLayout(new GridLayout(1,2));
		panelTitulo.add(name);
		panelTitulo.add(name2);
		
		finalizar.addActionListener(this);
		delete.addActionListener(this);
		edit.addActionListener(this);
		table.addMouseListener(this);
		add.addActionListener(this);
		addWindowListener(this);
		logOff.addActionListener(this);
		help.addActionListener(this);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
	}
	
	class List_Products extends AbstractTableModel {

		String[] column = { "Id", "Produto", "Valor", "Descricao", "Quantidade" };
		List<Produto> produtos = new ArrayList<Produto>();

		public List_Products() {
			produtos.addAll(produtoss);
		}

		@Override
		public int getRowCount() {

			return produtos.size();
		}

		@Override
		public int getColumnCount() {

			return column.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch (columnIndex) {
			case 0: {
				return produtos.get(rowIndex).getId();
			}
			case 1: {
				return produtos.get(rowIndex).getNome();
			}
			case 2: {
				return produtos.get(rowIndex).getValor();
			}
			case 3: {
				return produtos.get(rowIndex).getDescription();
			}
			case 4: {
				return produtos.get(rowIndex).getQuantidade();
			}
			default:
				return "";
			}

		}

		@Override
		public String getColumnName(int col) {
			return column[col];
		}
	}

	private float somaCarrinho() {

		float total = 0;
		for (Produto po : carrinhoss) {
			float tt = (po.getValor() * po.getQuantidade());
	    	total += tt;
		}
		return total;
	}
	
	class List_Carrinho extends AbstractTableModel {

		String[] column = { "Id", "Produto", "Valor", "Descricao", "Quantidade" };
		List<Produto> produtoscar = new ArrayList<Produto>();

		public List_Carrinho() {
			produtoscar.addAll(carrinhoss);
		}
		
		@Override
		public int getRowCount() {

			return produtoscar.size();
		}

		@Override
		public int getColumnCount() {

			return column.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch (columnIndex) {
			case 0: {
				return produtoscar.get(rowIndex).getId();
			}
			case 1: {
				return produtoscar.get(rowIndex).getNome();
			}
			case 2: {
				return produtoscar.get(rowIndex).getValor();
			}
			case 3: {
				return produtoscar.get(rowIndex).getDescription();
			}
			case 4: {
				return produtoscar.get(rowIndex).getQuantidade();
			}
			default:
				return "";
			}
		}

		@Override
		public String getColumnName(int col) {
			return column[col];
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == help)
		{
			JOptionPane.showMessageDialog(this, "Para adicionar, selecione o produto e clique em adicionar com o botao do lado esquerdo.", "Ajuda!", JOptionPane.INFORMATION_MESSAGE);
			JOptionPane.showMessageDialog(this, "Apos adicionado voce pode tirar, editar quantidade e finalizar o pedido. Com os 3 botoes do lado direito.", "Ajuda!", JOptionPane.INFORMATION_MESSAGE);
		}
		
		if(e.getSource() == logOff)
		{
			dispose();
		}
		
		if(e.getSource() == add)
		{
			if(result != 0) {
				//operacao 3 abre tela de cadastro produto somente para alterar quantidade
				linhaProduto = table.getSelectedRow();

				Tela_Cadastro.operacao = 3;
				new Tela_Cadastro();
			}
			else {
				JOptionPane.showMessageDialog(this, "Nenhum item selecionado", "Erro", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		if(e.getSource() == edit) {
			linhaCarrinho = table2.getSelectedRow();
			
			if(linhaCarrinho != -1) {

				//operacao 4 abre tela de cadastro produto puxando item da arraylist carrinhoss somente para alterar quantidade
				Tela_Cadastro.operacao = 4;
				new Tela_Cadastro();
			}
			else {
				JOptionPane.showMessageDialog(this, "Nenhum item selecionado", "Erro", JOptionPane.WARNING_MESSAGE);
			}	
		}
		
		if(e.getSource() == delete) {
			linhaCarrinho = table2.getSelectedRow();
			
   			Produto p = new Produto();
   			
			p = carrinhoss.get(Tela_Carrinho.linhaCarrinho);
			int id = p.getId();
			int quanti = p.getQuantidade();
			if(linhaCarrinho != -1) {
				carrinhoss.remove(linhaCarrinho);
				List_Carrinho lcNew = new List_Carrinho();
				table2.setModel(lcNew);
				valorTotal.setText(String.valueOf(somaCarrinho()));

				for (Produto prod : Tela_Carrinho.produtoss) {
					if(id == prod.getId()){
						int q = prod.getQuantidade();
						q = q + quanti;
						prod.setQuantidade(q);
					}
				}

				List_Products plNew = new List_Products();
				table.setModel(plNew);
					
    			JOptionPane.showMessageDialog(this, "Removido com sucesso", "Parabens", JOptionPane.WARNING_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(this, "Nenhum item selecionado", "Erro", JOptionPane.WARNING_MESSAGE);
			}	
		}
		
		if(e.getSource() == finalizar) {
			
			if(!carrinhoss.isEmpty() ) {
		    	Date dataUtil = new java.util.Date();
				java.sql.Timestamp dataSql = new java.sql.Timestamp(dataUtil.getTime());
				PedidoDAO dao = new PedidoDAO();
		    	Pedido pe = new Pedido();
		    	pe.setData(dataSql);
		    	pe.setCliente(logCliente);
		    	int novoId = dao.incluir(pe);
		    	ItemPedidoDAO idao = new ItemPedidoDAO();
		    	
				ProdutoDAO produtoDao = new ProdutoDAO();

				List<ItemPedido> listaItemPedido = new ArrayList<ItemPedido>();
				List<Produto> listaProdutosM = new ArrayList<Produto>();

				for (Produto po : carrinhoss) {
					ItemPedido ip = new ItemPedido();

					ip.setPedido(novoId);
                	ip.setProduto(po.getId());
                	ip.setQuantidade(po.getQuantidade());
					listaItemPedido.add(ip);

					for(Produto prodElemento : produtoss)
					{	
						Produto pMod = new Produto();
						if(po.getId() == prodElemento.getId()){
                			pMod.setId(prodElemento.getId());
                			pMod.setQuantidade(prodElemento.getQuantidade());
							listaProdutosM.add(pMod);
						}
					}
				}

				produtoDao.alterarQuantCar(listaProdutosM);
				idao.incluir(listaItemPedido);
				
				JOptionPane.showMessageDialog(this, "Pedido Realizado com Sucesso", "Parabens", JOptionPane.WARNING_MESSAGE);
				
				Jasper.vtotal = somaCarrinho();
				System.out.println(" " + Jasper.vtotal);
				JOptionPane.showMessageDialog(this, "Relatorio gerado com Sucesso", "Parabens", JOptionPane.WARNING_MESSAGE);
				
				carrinhoss.clear();
				List_Carrinho lcNew = new List_Carrinho();
				table2.setModel(lcNew);
				
				valorTotal.setText("0.0");
				
				new Jasper();
			}
			else {
				JOptionPane.showMessageDialog(this, "Nenhum item selecionado", "Erro", JOptionPane.WARNING_MESSAGE);
			}	
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int linha = table.getSelectedRow();
		result = Integer.parseInt(String.valueOf(table.getValueAt(linha, 0)));
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {	
		carrinhoss.clear();
		produtoss.clear();
		new Tela_Login();
	}

	@Override
	public void windowIconified(WindowEvent e) {	
	}

	@Override
	public void windowDeiconified(WindowEvent e) {	
	}

	@Override
	public void windowActivated(WindowEvent e) {
		List_Carrinho lcNew = new List_Carrinho();
		table2.setModel(lcNew);
		valorTotal.setText(String.valueOf(somaCarrinho()));
		List_Products plNew = new List_Products();
		table.setModel(plNew);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

} // End of Tela_Carrinho class
