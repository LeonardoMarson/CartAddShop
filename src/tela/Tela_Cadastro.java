package tela;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import bd.Produto;
import bd.ProdutoDAO;

public class Tela_Cadastro extends JFrame implements ActionListener, KeyListener {
	private JLabel ScreenTitle;
	private JLabel Product = new JLabel("Produto:");
	private JLabel Value = new JLabel("Valor:");
	private JLabel Description = new JLabel("Descricao:");
	private JLabel Quant = new JLabel("Quantidade:");

	private JTextField pField = new JTextField(40);
	private JTextField vField = new JTextField(40);
	private JTextArea dField = new JTextArea();
	private JTextField qField = new JTextField(40);

	private JButton Cancel = new JButton("Cancelar");
	private JButton Register;

	private JButton mais;
	private JButton menos;

	private JPanel painel = new JPanel();

	static String v = "";
	static int operacao;

	private Produto p = new Produto();
	private ProdutoDAO dao = new ProdutoDAO();

	private int id, quanti;

	public Tela_Cadastro() {
		setLayout(new BorderLayout());

		ImageIcon icon = new ImageIcon(getClass().getResource("/img/icon.png"));
		setIconImage(icon.getImage());

		if (operacao == 1) {
			ScreenTitle = new JLabel("Cadastro Produto");
			Register = new JButton("Cadastrar");
			qField.setBounds(220, 250, 300, 40);
		}

		if (operacao == 2) {
			ScreenTitle = new JLabel("Alterar Produto");
			Register = new JButton("Alterar");
			p = dao.listarPorId(Tela_Adm.result);
			pField.setText(p.getNome());
			vField.setText(String.valueOf(p.getValor()));

			qField.setText(String.valueOf(p.getQuantidade()));
			dField.setText(p.getDescription());
			qField.setBounds(220, 250, 300, 40);
		}

		if (operacao == 3) {
			ScreenTitle = new JLabel("Adicionar ao carrinho");
			Register = new JButton("Adicionar");
			p = Tela_Carrinho.produtoss.get(Tela_Carrinho.linhaProduto);
			pField.setText(p.getNome());
			vField.setText(String.valueOf(p.getValor()));
			qField.setText("1");
			dField.setText(p.getDescription());

			qField.setBounds(220, 250, 100, 40);
			pField.setEditable(false);
			vField.setEditable(false);
			dField.setEditable(false);

			mais = new JButton("+");
			menos = new JButton("-");

			menos.setBounds(350, 250, 50, 40);
			mais.setBounds(410, 250, 50, 40);
			painel.add(mais);
			painel.add(menos);
			mais.addActionListener(this);
			menos.addActionListener(this);
		}

		if (operacao == 4) {
			ScreenTitle = new JLabel("Editar Quantidade");
			Register = new JButton("Editar");
			p = Tela_Carrinho.carrinhoss.get(Tela_Carrinho.linhaCarrinho);
			id = p.getId();
			pField.setText(p.getNome());
			vField.setText(String.valueOf(p.getValor()));
			quanti = p.getQuantidade();
			qField.setText(String.valueOf(p.getQuantidade()));
			dField.setText(p.getDescription());

			qField.setBounds(220, 250, 100, 40);
			pField.setEditable(false);
			vField.setEditable(false);
			dField.setEditable(false);

			mais = new JButton("+");
			menos = new JButton("-");

			menos.setBounds(350, 250, 50, 40);
			mais.setBounds(410, 250, 50, 40);
			painel.add(mais);
			painel.add(menos);
			mais.addActionListener(this);
			menos.addActionListener(this);
		}

		// ADD ELEMENTS TO THE PANEL
		painel.setLayout(null);
		painel.add(ScreenTitle);

		painel.add(Product);
		painel.add(pField);

		painel.add(Value);
		painel.add(vField);

		painel.add(Description);
		painel.add(dField);

		painel.add(Quant);
		painel.add(qField);

		painel.add(Cancel);
		painel.add(Register);

		// BOUNDS SET
		ScreenTitle.setFont(new Font("Helveltica", Font.BOLD, 30));
		ScreenTitle.setBounds(160, 50, 300, 30);

		Product.setFont(new Font("Arial", Font.BOLD, 18));
		Product.setBounds(90, 150, 100, 40);
		pField.setBounds(220, 150, 300, 40);
		pField.setBorder(null);

		Value.setFont(new Font("Arial", Font.BOLD, 18));
		Value.setBounds(90, 200, 100, 40);
		vField.setBounds(220, 200, 300, 40);
		vField.setBorder(null);

		Quant.setFont(new Font("Arial", Font.BOLD, 18));
		Quant.setBounds(90, 250, 120, 40);
		qField.setBorder(null);

		Description.setFont(new Font("Arial", Font.BOLD, 18));
		Description.setBounds(90, 300, 100, 40);
		dField.setBounds(220, 300, 300, 100);
		dField.setLineWrap(true);

		Cancel.setBounds(160, 450, 140, 40);

		Register.setBounds(310, 450, 140, 40);

		Cancel.addActionListener(this);
		Register.addActionListener(this);
		qField.addKeyListener(this);

		getContentPane().add(painel, BorderLayout.CENTER);

		setSize(600, 600);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Cancel) {
			dispose();
		}

		if (e.getSource() == Register) {

			if (pField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Campo Produto nao pode estar em Branco", "erro",
						JOptionPane.WARNING_MESSAGE);
			}
			if (vField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Campo Valor nao pode estar em Branco", "erro",
						JOptionPane.WARNING_MESSAGE);
			}
			if (qField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Campo Quantidade nao pode estar em Branco", "erro",
						JOptionPane.WARNING_MESSAGE);
			}

			if (operacao == 4) {
				p.setQuantidade(Integer.parseInt(qField.getText()));
				Tela_Carrinho.carrinhoss.set(Tela_Carrinho.linhaCarrinho, p);

				Produto prodd = new Produto();
				prodd = Tela_Carrinho.carrinhoss.get(Tela_Carrinho.linhaCarrinho);
				int idd = prodd.getId();
				int q = 0;

				for (Produto prod : Tela_Carrinho.produtoss) {
					if(idd == prod.getId()){
						q = prod.getQuantidade();
					}
				}

				if ((q + quanti) >= p.getQuantidade()){
					if (quanti < p.getQuantidade()) {
						int result = p.getQuantidade() - quanti;
						q = q - result;
					} else if (quanti > p.getQuantidade()) {
						int result = quanti - p.getQuantidade();
						q = q + result;
					} else {
						JOptionPane.showMessageDialog(this, "Nao ocorreram alteracoes", "Parabens", JOptionPane.WARNING_MESSAGE);
						dispose();
					}

					for (Produto prod : Tela_Carrinho.produtoss) {
						if(idd == prod.getId()){
							prod.setQuantidade(q);
						}
					}

					p.setQuantidade(Integer.parseInt(qField.getText()));
					Tela_Carrinho.carrinhoss.set(Tela_Carrinho.linhaCarrinho, p);

					JOptionPane.showMessageDialog(this, "Alterado com sucesso", "Parabens", JOptionPane.WARNING_MESSAGE);
					dispose();
				}else if ((q + quanti) < p.getQuantidade()){
					JOptionPane.showMessageDialog(this, "Nao temos quantidade suficiente. Em estoque: " + (q + quanti) , "erro", JOptionPane.WARNING_MESSAGE);
					qField.setText(String.valueOf(q + quanti));
				}
				

			} else {

				Produto prod = new Produto();
				prod.setNome(pField.getText());
				prod.setValor(Float.parseFloat(vField.getText()));
				prod.setQuantidade(Integer.parseInt(qField.getText()));
				prod.setDescription(dField.getText());

				if (operacao == 1) {
					dao.incluir(prod);
					JOptionPane.showMessageDialog(this, "Registrado", "Parabens", JOptionPane.WARNING_MESSAGE);
					dispose();
				}

				if (operacao == 2) {
					prod.setId(Tela_Adm.result);
					dao.alterar(prod);
					JOptionPane.showMessageDialog(this, "Alterado com sucesso", "Parabens",
							JOptionPane.WARNING_MESSAGE);
					dispose();
				}

				if (operacao == 3) {
					int i = Integer.parseInt(qField.getText());

					Produto prodd = new Produto();
					prodd = Tela_Carrinho.produtoss.get(Tela_Carrinho.linhaProduto);
					int quant = prodd.getQuantidade();

					if (i <= 0) {
						JOptionPane.showMessageDialog(this, "Campo Quantidade nao pode ser menor ou igual a zero", "erro", JOptionPane.WARNING_MESSAGE);
					} else if (quant < i) {
						JOptionPane.showMessageDialog(this, "Nao temos quantidade suficiente. Em estoque: " + quant , "erro", JOptionPane.WARNING_MESSAGE);
						qField.setText(String.valueOf(quant));
					} else if (quant >= i) {
						quant = quant - i;
						prodd.setQuantidade(quant);
						
						prod.setId(Tela_Carrinho.result);
						Tela_Carrinho.produtoss.set(Tela_Carrinho.linhaProduto, prodd);

						Tela_Carrinho.carrinhoss.add(prod);
						JOptionPane.showMessageDialog(this, "Adicionado com sucesso", "Parabens", JOptionPane.WARNING_MESSAGE);
						dispose();
					}
				}

			}
		}

		if (e.getSource() == mais) {
			qField.setText(String.valueOf((Integer.parseInt(qField.getText())) + 1));

		}
		if (e.getSource() == menos) {
			qField.setText(String.valueOf((Integer.parseInt(qField.getText())) - 1));
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
			v = qField.getText();
		} else {
			qField.setText(v);
		}
	}

} // End of Tela_Cadastro class