package br.fam.Carambola.Db;
/*
 * CLASSE ConnectionDb -> Está fora do Diagrama
 * 
 * By Victor Teixeira
 * 
 * Anotação:	Classe de conexão com o banco de dados H2, que será utilizada
 * 				para criação dos metodos de acesso ao Banco de dados
 * 
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.fam.Carambola.Formatador;



public class ConnectionDb {
	String url = "jdbc:h2:~/teste"; //Link local apra conexão do banco de dados
	String usuario = "sa"; //Usuario padrão
	String senha = "";// Senha padrao
	Formatador formatador = new Formatador();
	
	public void insert(String comandoSQL) throws SQLException{ // metodo de inserir novo registo
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
			//System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(url, usuario, senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa o comando de insert
		String sql = comandoSQL;
        int row = statement.executeUpdate(sql);
        System.out.println(row + " Registo foi cadastrado corretamente!");
        
        //Fecha Statemente e Conexão
        conexao.close();
        statement.close();
	}
	
	public void update(String comandoSQL) throws SQLException{ // metodo de atualizar um registo
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
			//System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(url, usuario, senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa o comando de insert
		String sql = comandoSQL;
        int row = statement.executeUpdate(sql);
        System.out.println(row + " Registo foi alterado corretamente!");
        
        //Fecha Statemente e Conexão
        conexao.close();
        statement.close();
	}
	
	public void queryVerTodasAsCategoriasDeUmCatalogo(int id1) throws SQLException {
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
			//System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(url, usuario, senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa o comando de insert
		String sql = "SELECT * FROM TB_CATEGORIAS CATE JOIN TB_CATALOGO CAT ON CATE.CATE_IDCAT = CAT.CAT_IDCAT WHERE CAT.CAT_IDCAT = '"+id1+"';";
        ResultSet row = statement.executeQuery(sql);
        System.out.println(row + " Consulta feita corretamente no Banco de Dados!");
        
        //Mostra o resultado na tela]
        while(row.next()) {
        	int id = row.getInt("CATE_IDCATE");
        	int idCatalogo = row.getInt("CATE_IDCAT");
        	String nomeCategoria = row.getString("CATE_DESCCATE");
        	JOptionPane.showMessageDialog(null,   "\nId da Categoria: " + id
        										+ "\nId do Catalogo: " + idCatalogo
        										+ "\nNome da Categoria: " + nomeCategoria);
        	//System.out.println("Id da Categoria: " + id + ", Id do Catalogo: " + idCatalogo + ", Nome da Categoria: " + nomeCategoria);
        }
        
        //Fecha Statemente e Conexão
        conexao.close();
        statement.close();
	}
	
	public void queryVerProdutosDeUmaCategoria(int id1) throws SQLException {
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
			//System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(url, usuario, senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa o comando de insert
		String sql = "SELECT * FROM TB_PRODUTOS PRO JOIN TB_CATEGORIAS CATE ON PRO.PRO_IDCATE = CATE.CATE_IDCATE WHERE CATE.CATE_IDCATE = '"+id1+"';";
        ResultSet row = statement.executeQuery(sql);
        System.out.println(row + " Consulta feita corretamente no Banco de Dados!");
        
        //Mostra o resultado na tela]
        while(row.next()) {
        	int id = row.getInt("PRO_IDPROD");
        	String nome = row.getString("PRO_DESC");
        	String categoria = row.getString("CATE_DESCCATE");
        	Double valor = row.getDouble("PRO_VALOR");
        	JOptionPane.showMessageDialog(null,   "\nID: " + id 
        										+ "\nNome: " + nome
        										+ "\nCategoria: " + categoria
        										+ "\nValor: " + Formatador.doubleToString(valor));
        	//System.out.println("ID: " + id + ", Nome: " + nome + ", Categoria: " + categoria + ", Valor: " + valor);
        }
        
        //Fecha Statemente e Conexão
        conexao.close();
        statement.close();
	}
	
	public String queryGetCategoriaBd(int id1) throws SQLException {
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
			//System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(url, usuario, senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa o comando de insert
		String sql = "SELECT CATE_DESCCATE FROM TB_CATEGORIAS WHERE CATE_IDCATE = '"+id1+"';";
        ResultSet row = statement.executeQuery(sql);
        //System.out.println(row + "Consulta feita corretamente no Banco de Dados!");
        
        String categoria = null;
        //Mostra o resultado na tela]
        while(row.next()) {
        	categoria = row.getString("CATE_DESCCATE");
        	//System.out.println("Categoria: " + categoria);
        }
        
        //Fecha Statemente e Conexão
        conexao.close();
        statement.close();
        return categoria;
	}
	
	public int queryGetIdCatalagoPorEstabelecimento(int idEstabelecimento) throws SQLException {
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
			//System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(url, usuario, senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa o comando de insert
		String sql = "SELECT CAT_IDCAT FROM TB_CATALOGO WHERE CAT_IDUSUEST = '"+idEstabelecimento+"';";
        ResultSet row = statement.executeQuery(sql);
        
        int idCatalogo = 0;
        //Mostra o resultado na tela]
        while(row.next()) {
        	idCatalogo = row.getInt("CAT_IDCAT");
        }
        
        //Fecha Statemente e Conexão
        conexao.close();
        statement.close();
        return idCatalogo;
	}
	
	public int queryIdCatalogoComIdCategoria(int idCategoria) throws SQLException {
		int idCatalogo = 0;
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
			//System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(url, usuario, senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa o comando de insert
		String sql = "SELECT CATE_IDCAT FROM TB_CATEGORIAS WHERE CATE_IDCATE = '"+idCategoria+"';";
        ResultSet row = statement.executeQuery(sql);
        
        
        //Mostra o resultado na tela]
        while(row.next()) {
        	int idCatalogo1 = row.getInt("CATE_IDCAT");
        	idCatalogo = idCatalogo1;
        }
        
        //Fecha Statemente e Conexão
        conexao.close();
        statement.close();
        return idCatalogo;
	}
	
	
}
