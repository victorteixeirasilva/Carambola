package br.fam.Carambola.Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import br.fam.Carambola.Formatador;
/**
 * @author Victor Teixeira Silva
 * 
 * @version 1.0
 * 
 * - Classe ConnectionDb está fora do Diagrama, Essa classe é responsavel por fazer a conexão com o banco de dados em H2 e fazer tantos os comandos DDL quanto os DML
 * 
 * 
 */
public class ConnectionDb {
/**
 * @param url - Atributo responsavel por receber a String de conexão do banco de dados.
 * @param usuario - Atributo responsavel por receber o usuário de conexão no banco de dados.
 * @param senha - Atributo responsavel por receber a senha do usuário passado no parametro usuario.
 * @param formatador - Objeto da classe formatador responsavel por formatar os inteiros em formato de moeda.
 * 
 */
	String url = "jdbc:h2:~/teste";
	String usuario = "sa"; //Usuario padrão
	String senha = "";// Senha padrao
	Formatador formatador = new Formatador();
	
	public int buscarIdUsuarioPorEmailESenha(String email, String senha) throws SQLException {
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
			System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(url, usuario, senha);
		
		//Cria um statement para receber comandos
		PreparedStatement ps = conexao.prepareStatement("SELECT (USU_IDUSU) FROM TB_USUARIOS WHERE USU_EMAIL = "+email+" AND senha = "+senha+";");
        ps.setString(1, email);
        ps.setString(2, senha);
	    
	    ResultSet rs = ps.executeQuery();
	    
	    int id = -1;
	    if (rs.next()) {
	        id = rs.getInt("id");
	    }
	    
	    rs.close();
	    ps.close();
	    return id;
	}
	
	public boolean verificarSeCliente(int idUsuario) {
		boolean resultado = false;
        String sql = "SELECT (USUCLI_IDUSU) FROM TB_USUARIOS_CLIENTE WHERE USUCLI_IDUSU = "+idUsuario+";";
        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                resultado = true; // Usuário e senha válidos
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage()); // Tratamento de exceções
        }
        return resultado;
	}
	
	public boolean verificarSeEstabelecimento(int idUsuario) {
		boolean resultado = false;
        String sql = "SELECT (USUEST_IDUSU ) FROM TB_USUARIOS_ESTABELECIMENTO WHERE USUEST_IDUSU = "+idUsuario+";";
        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                resultado = true; // Usuário e senha válidos
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage()); // Tratamento de exceções
        }
        return resultado;
	}
	
	public boolean verificarLoginBd(String email, String senha){
		boolean resultado = false;
        String sql = "SELECT * FROM TB_USUARIO WHERE USU_EMAIL = '"+email+"' AND USU_SENHA = '"+senha+"'";
        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                resultado = true; // Usuário e senha válidos
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage()); // Tratamento de exceções
        }
        return resultado;
	}
	/**
	 * 
	 * @param comandoSQL - String que vai receber o comando sql sendo eles, de inserção
	 * @throws SQLException
	 * 
	 * - Metodo responsavel por receber uma String como parametro que é um código em sql to tipo de inserção de dados, o metodo exucuta essa string no banco de dados
	 * 
	 * @return 
	 * Esse metodo possui 3 retornos possiveis. 
	 * 1.Esse metodo tem como retorno a mensagem "Conexão bem sucessida!" caso a cenexão com o banco esteja funcionando corretamente.
	 * 2.Esse metodo também tem como retorno a mensagem "Registo foi cadastrado corretamente!", caso o comando sql execute sem problema e faça a inserção no banco de dados
	 * 3.O terceiro retorno possivel é a mensagem "Ocorreu um erro ao conectar", que retorna sempre que o projeto tem dificuldade em se conectar com o banco
	 */
	public void insert(String comandoSQL) throws SQLException{ // metodo de inserir novo registo
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
			System.out.println("Conexão bem-sucedida!");
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
	/**
	 * 
	 * @param comandoSQL - String que vai receber o comando sql sendo eles, de inserção
	 * @throws SQLException
	 * 
	 * - Metodo responsavel por receber uma String como parametro que é um código em sql to tipo que faz atualização em dados, o metodo exucuta essa string no banco de dados
	 * 
	 * @return 
	 * Esse metodo possui 3 retornos possiveis. 
	 * 1.Esse metodo tem como retorno a mensagem "Conexão bem sucessida!" caso a cenexão com o banco esteja funcionando corretamente.
	 * 2.Esse metodo também tem como retorno a mensagem "Registo foi cadastrado corretamente!", caso o comando sql execute sem problema e faça a inserção no banco de dados
	 * 3.O terceiro retorno possivel é a mensagem "Ocorreu um erro ao conectar", que retorna sempre que o projeto tem dificuldade em se conectar com o banco
	 */ 
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
	/**
	 * 
	 * @param idCatalogo - Parametro responsavel po receber o ID do catalogo para que seja feito a consulta em base nele
	 * @throws SQLException
	 * 
	 * - Esse metodo é responsavel por fazer uma consulta dentro do banco de dados e mostrar todas as categorias cadastradas em um catalogo especifico, cujo id é passado como parametro
	 * 
	 * @return
	 *  Esse metodo possui 3 retornos possiveis. 
	 * 1.Esse metodo tem como retorno a mensagem "Conexão bem sucessida!" caso a cenexão com o banco esteja funcionando corretamente.
	 * 2.Esse metodo também tem como retorno a mensagem "Consulta feita corretamente no Banco de dados", caso o comando sql execute sem problema e faça consulta no banco de dados.
	 * 3.O terceiro retorno possivel é a mensagem "Ocorreu um erro ao conectar", que retorna sempre que o projeto tem dificuldade em se conectar com o banco
	 */
	public void queryVerTodasAsCategoriasDeUmCatalogo(int idCatalogo) throws SQLException {
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
		String sql = "SELECT * FROM TB_CATEGORIAS CATE JOIN TB_CATALOGO CAT ON CATE.CATE_IDCAT = CAT.CAT_IDCAT WHERE CAT.CAT_IDCAT = '"+idCatalogo+"';";
        ResultSet row = statement.executeQuery(sql);
        System.out.println(row + " Consulta feita corretamente no Banco de Dados!");
        
        //Mostra o resultado na tela]
        while(row.next()) {
        	int id = row.getInt("CATE_IDCATE");
        	int idCat = row.getInt("CATE_IDCAT");
        	String nomeCategoria = row.getString("CATE_DESCCATE");
        	JOptionPane.showMessageDialog(null,   "\nId da Categoria: " + id
        										+ "\nId do Catalogo: " + idCat
        										+ "\nNome da Categoria: " + nomeCategoria);
        	//System.out.println("Id da Categoria: " + id + ", Id do Catalogo: " + idCatalogo + ", Nome da Categoria: " + nomeCategoria);
        }
        
        //Fecha Statemente e Conexão
        conexao.close();
        statement.close();
	}
	/**
	 * 
	 * @param idCategoria - parametro responsavel por receber o ID da categoria para que seja usado na consulta.
	 * @throws SQLException
	 * 
	 * - Esse metodo é responsavel por fazer uma consulta dentro do banco de dados e mostrar todos os produtos presentes em uma categoria, de acordo com o ID da mesma passada como parametro.
	 * 
	 * @return
	 * Esse metodo possui 3 retornos possiveis. 
	 * 1.Esse metodo tem como retorno a mensagem "Conexão bem sucessida!" caso a cenexão com o banco esteja funcionando corretamente.
	 * 2.Esse metodo também tem como retorno a mensagem "Consulta feita corretamente no Banco de dados", caso o comando sql execute sem problema e faça consulta no banco de dados.
	 * 3.O terceiro retorno possivel é a mensagem "Ocorreu um erro ao conectar", que retorna sempre que o projeto tem dificuldade em se conectar com o banco
	 */
	public void queryVerProdutosDeUmaCategoria(int idCategoria) throws SQLException {
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
		String sql = "SELECT * FROM TB_PRODUTOS PRO JOIN TB_CATEGORIAS CATE ON PRO.PRO_IDCATE = CATE.CATE_IDCATE WHERE CATE.CATE_IDCATE = '"+idCategoria+"';";
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
	
	/**
	 * 
	 * @param id1
	 * @throws SQLException
	 * 
	 * - Metodo responsavel por fazer uma consulta no banco de dados e retornar uma categoria de acordo com o id
	 * 
	 * @return
	 * Esse metodo possui 3 retornos possiveis. 
	 * 1.Esse metodo tem como retorno a mensagem "Conexão bem sucessida!" caso a cenexão com o banco esteja funcionando corretamente.
	 * 2.Esse metodo também tem como retorno a mensagem "Consulta feita corretamente no Banco de dados", caso o comando sql execute sem problema e faça consulta no banco de dados.
	 * 3.O terceiro retorno possivel é a mensagem "Ocorreu um erro ao conectar", que retorna sempre que o projeto tem dificuldade em se conectar com o banco

	 */
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
	/**
	 * 
	 * @param idEstabelecimento - Parametro que devera receber o id do estabelecimento
	 * @throws SQLException
	 * 
	 * - Metodo responsavel por fazer uma consulta no banco de dados e retornar o id do catalogo de acordo com o id do estabelecimento informado como parametro
	 * 
	 * @return
	 * * Esse metodo possui 3 retornos possiveis. 
	 * 1.Esse metodo tem como retorno a mensagem "Conexão bem sucessida!" caso a cenexão com o banco esteja funcionando corretamente.
	 * 2.Esse metodo também tem como retorno a mensagem "Consulta feita corretamente no Banco de dados", caso o comando sql execute sem problema e faça consulta no banco de dados.
	 * 3.O terceiro retorno possivel é a mensagem "Ocorreu um erro ao conectar", que retorna sempre que o projeto tem dificuldade em se conectar com o banco
	 * 
	 */
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
	
	/**
	 * 
	 * @param idCategoria - Parametro responsavel por receber o id da categoria
	 * @throws SQLException
	 * 
	 * - Metodo responsavel por pegar o id do catalogo de acordo com o id de uma categoria informada no parametro do metodo.
	 * 
	 * @return
	 * * Esse metodo possui 3 retornos possiveis. 
	 * 1.Esse metodo tem como retorno a mensagem "Conexão bem sucessida!" caso a cenexão com o banco esteja funcionando corretamente.
	 * 2.Esse metodo também tem como retorno a mensagem "Consulta feita corretamente no Banco de dados", caso o comando sql execute sem problema e faça consulta no banco de dados.
	 * 3.O terceiro retorno possivel é a mensagem "Ocorreu um erro ao conectar", que retorna sempre que o projeto tem dificuldade em se conectar com o banco
	 * 
	 */
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
