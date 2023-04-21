package br.fam.Carambola.Usuarios;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.fam.Carambola.Catalogo;
import br.fam.Carambola.Categoria;
import br.fam.Carambola.Endereco;
import br.fam.Carambola.Db.ConnectionDb;

public class UsuarioEstabelecimento  {
	private String nome, email, senha;
	private Endereco endereco;
	private float classificacao;
	private int id;
	private long telefone;
	private long cnpj;
	private Usuario usuario;
	private ConnectionDb conn = new ConnectionDb();
	
	public UsuarioEstabelecimento() {
		
	}
	
	public UsuarioEstabelecimento(Usuario usuario, String nomeEstabelecimento , long cnpj) throws SQLException {
		this.usuario = usuario;
		this.nome = nomeEstabelecimento;
		this.email = this.usuario.getEmail();
		this.senha = this.usuario.getSenha();
		this.cnpj = cnpj;
		this.telefone = this.usuario.getTelefone();
		this.id = usuario.getId();
		conn.insert("INSERT INTO TB_USUARIOS_ESTABELECIMENTO(USUEST_IDUSUEST, USUEST_IDUSU, USUEST_NOME, USU_CNPJ) VALUES ("+this.usuario.getId()+", "+this.usuario.getId()+", '"+this.nome+"', "+this.cnpj+");");
		
	}

	private void cadastrarNovaMesa(int idEstabelecimento) throws SQLException {
		int opcao = JOptionPane.showConfirmDialog(null, "Deseja criar uma nova mesa ou comanda?\n\n");
		if(opcao == JOptionPane.YES_OPTION) {
			String numeroMesaComandaString = JOptionPane.showInputDialog("Informe o número da mesa ou comanda que deseja cadastrar!\n\n" + "OBS: Lembrando que esse número é apenas para" + "\n melhorar a logistica em seu estabelecimento");
			int numeroMesaComanda = Integer.parseInt(numeroMesaComandaString);
			conn.insert("INSERT INTO TB_MESA_COMANDA(MES_IDMES,MES_IDUSUEST,MES_NUMERO,MES_DISPONIVEL) VALUES (NEXT VALUE FOR SQ_MES_IDMES, "+idEstabelecimento+", "+numeroMesaComanda+", TRUE);");
			JOptionPane.showMessageDialog(null, "Nova Mesa ou Comanda Cadastrada corretamente!");
		} else if (opcao == JOptionPane.NO_OPTION) {
			JOptionPane.showMessageDialog(null, "Nenhuma mesa ou comanda cadastrada no seu estabelecimento!");
			return;
		}
	}
	
	public void verificarMesas(int idEstabelecimento) throws SQLException {
		if(conn.verificarSeExisteMesas(idEstabelecimento)) {
			String nomeEstabelecimento = conn.getNomeBdEstabelecimento(idEstabelecimento);
			JOptionPane.showMessageDialog(null, "As mesas ou comandas presentes no seu estabelecimento são!" + "\nNome Estabelecimento: "+nomeEstabelecimento);
			conn.mostrarMesasDisponiveis(idEstabelecimento);
			int opcao1 = JOptionPane.showConfirmDialog(null, "Deseja Cadastrar uma nova mesa ou comanda?");
			if(opcao1 == JOptionPane.YES_OPTION) {
				cadastrarNovaMesa(idEstabelecimento);
			} else {
				return;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Não existem mesas nem comandas em seu estabelecimento!\n\n");
			cadastrarNovaMesa(idEstabelecimento);
			return;
		}
	}
	
	
	
	public void cadastrarProduto(int idEstabelecimento) throws SQLException {
		Catalogo catalogo = new Catalogo();
		catalogo.verCategorias(idEstabelecimento);
		String idCategoriaString = JOptionPane.showInputDialog("Informe o Id da categoria cujo qual deseja cadastrar seu produto:\n\n");
		int idCategoria = Integer.parseInt(idCategoriaString);
		if(idEstabelecimento == conn.queryIdCatalogoComIdCategoria(idCategoria)) {
			String nomeProduto = JOptionPane.showInputDialog("Informe o Nome do produto:");
			String precoProdutoString = JOptionPane.showInputDialog("Informe o valor unitario do produto:");
			Double precoProduto = Double.parseDouble(precoProdutoString);
			int opcao = JOptionPane.showConfirmDialog(null, "Esse produto tem estoque? e está disponivel para venda?");
			boolean disponivel;
			if(opcao == JOptionPane.YES_OPTION) {
				disponivel = true;
			} else if (opcao == JOptionPane.CANCEL_OPTION) {
				JOptionPane.showMessageDialog(null, "Produto não foi cadastrado!");
				return;
			} else {
				disponivel = false;				
			}
			conn.insert("INSERT INTO TB_PRODUTOS(PRO_IDPROD, PRO_IDCATE, PRO_DESC, PRO_VALOR, PRO_TEMESTOQUE) VALUES (NEXT VALUE FOR SQ_PRO_IDPROD, "+idCategoria+", '"+nomeProduto+"', "+precoProduto+", "+disponivel+");");
		} else {
			JOptionPane.showMessageDialog(null, "Você não possui categoria com esse id");
		}
	}
	
	public void editarProdutos() {
		
	}
	
	public void verProdutos(int idEstabelecimento) throws SQLException {
		JOptionPane.showMessageDialog(null, "As categorias desse estabelecimento são:  ");
		conn.queryVerTodasAsCategoriasDeUmCatalogo(idEstabelecimento);
		String idCategoriaString = JOptionPane.showInputDialog("Informe o Id da Categoria que deseja ver os produtos: ");
		int idCategoria = Integer.parseInt(idCategoriaString);
		conn.queryVerProdutosDeUmaCategoria(idCategoria);
	}
	
	public void excluirProdutos() {
		
	}
	
	public void cadastrarFuncionario() {
		
	}
	
	public void excluirFuncionarios() {
		
	}
	
	public void verFuncionarios() {
		
	}
	
	public void editarFuncionarios() {
		
	}
	
	public void simularCompra() {
		
	}
	
	public void impulsionarEstabelecimento() {
		
	}
	
	public void verificarFaturamento() {
		
	}
	
	public void atualizarEstoque(int idEstabelecimento) throws SQLException {
		JOptionPane.showMessageDialog(null, "Selecione um produto dentro de uma categoria pelo id");
		conn.queryVerTodasAsCategoriasDeUmCatalogo(idEstabelecimento);
		String idCategoriaString = JOptionPane.showInputDialog("Informe o nome da categoria que deseja ver os produtos:");
		int idCategoria = Integer.parseInt(idCategoriaString);
		conn.verProdutosDeUmaCategoriaTrueFalse(idCategoria);
		String idProdutoString = JOptionPane.showInputDialog("Informe o Id do produto que deseja atualizar o estoque:");
		int idProduto = Integer.parseInt(idProdutoString);
		conn.verProdutoDetalhadoTrueFalse(idProduto);
		int opcao = JOptionPane.showConfirmDialog(null, 
				  "Conforme o produto visto anteriormente!\n\n"
				+ "Este produto está com estoque e está disponível para venda?\n\n"
				+ "(YES), altera o status para True"
				+ "(NO), altera o status para False");
		if(opcao == JOptionPane.YES_OPTION) {
			conn.insert("");//comando para alterar para True
			JOptionPane.showMessageDialog(null, "Disponibilidade do produto alterada para disponível");
			conn.verProdutoDetalhadoTrueFalse(idProduto);
		} else if(opcao == JOptionPane.NO_OPTION) {
			conn.insert("");//comando para alterar false
			JOptionPane.showMessageDialog(null, "Disponibilidade do produto alterada para indisponível");
			conn.verProdutoDetalhadoTrueFalse(idProduto);
		} else {
			JOptionPane.showMessageDialog(null, "Nenhuma alteração foi feita!");
			return;
		}
	}
	
	public void verCatalogo(int idEstabelecimento) throws SQLException {
		Catalogo catalogo = new Catalogo();
		catalogo.verCategorias(idEstabelecimento);
		Categoria categoria = new Categoria();
		categoria.verProdutosDaCategoria();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public float getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(float classificacao) {
		this.classificacao = classificacao;
	}

	public long getCnpj() {
		return cnpj;
	}

	public void setCnpj(int cnpj) {
		this.cnpj = cnpj;
	}

	public long getTelefone() {
		return telefone;
	}
	
	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	
	
}
