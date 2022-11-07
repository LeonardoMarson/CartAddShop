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
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import bd.Produto;
import bd.ProdutoDAO;

public class Tela_Adm extends JFrame implements ActionListener, MouseListener, WindowListener {
	private JButton include = new JButton("Cadastrar");
	private JButton delete = new JButton("Excluir");
	private JButton edit = new JButton("Editar");
	private JLabel name = new JLabel("Produtos");
	private JPanel panel = new JPanel();
	private JButton logOff = new JButton("Sair");
    private JLabel branco = new JLabel("");
    private JLabel branco2 = new JLabel("");
    private JLabel branco3 = new JLabel("");
    
	private List_Products lp = new List_Products();
	private JTable table = new JTable(lp);

	private JScrollPane scroll = new JScrollPane(table);

	// VARIAVEL PARA SALVAR LINHA SELECIONADA NO JTable
	static int result;

	public Tela_Adm() {
		setLayout(new BorderLayout());
		setVisible(true);
		setSize(800, 600);
		setResizable(false);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/img/icon.png"));
		setIconImage(icon.getImage());

		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		name.setFont(new Font("SansSerif", Font.BOLD, 35));
		name.setHorizontalAlignment(JLabel.CENTER);

		getContentPane().add(name, BorderLayout.PAGE_START);
		getContentPane().add(scroll, BorderLayout.CENTER);
		getContentPane().add(panel, BorderLayout.PAGE_END);
	
		panel.setLayout(new GridLayout(1,7));
		panel.add(branco);
		panel.add(logOff);
		panel.add(branco2);
		panel.add(include);
		panel.add(delete);
		panel.add(edit);
		panel.add(branco3);
		result = 0;
		
		include.addActionListener(this);
		delete.addActionListener(this);
		edit.addActionListener(this);
		table.addMouseListener(this);
		logOff.addActionListener(this);
		addWindowListener(this);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	class List_Products extends AbstractTableModel {

		String[] column = { "Id", "Produto", "Valor", "Descricao", "Quantidade" };
		List<Produto> produtos = new ArrayList<Produto>();

		public List_Products() {
			ProdutoDAO dao = new ProdutoDAO();
			produtos.addAll(dao.listar());
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
	} // End of List_Products class

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == logOff)
		{
			new Tela_Login();
			dispose();
		}

		if (e.getSource() == include) {
			Tela_Cadastro.operacao = 1;
			new Tela_Cadastro();
		}

		if (e.getSource() == edit) {
			if(result != 0)
			{
				Tela_Cadastro.operacao = 2;
				new Tela_Cadastro();
			}
			else {
				JOptionPane.showMessageDialog(this, "Nenhum item selecionado", "Erro", JOptionPane.WARNING_MESSAGE);
			}
		}
		if(e.getSource() == delete)
		{
			if(result != 0)
			{
				ProdutoDAO dao = new ProdutoDAO();
				dao.excluir(result);
				JOptionPane.showMessageDialog(this, "Excluido com sucesso", "Parabens", JOptionPane.WARNING_MESSAGE);
				List_Products lpNew = new List_Products();
				table.setModel(lpNew);
			}
			else {
				JOptionPane.showMessageDialog(this, "Nenhum item selecionado", "Erro", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int linha = table.getSelectedRow();
		int j = Integer.parseInt(String.valueOf(table.getValueAt(linha, 0)));
		result = j;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		List_Products lpNew = new List_Products();
		table.setModel(lpNew);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

} // End of Tela_Adm class
