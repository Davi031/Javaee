package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {
	
	/**  Módulo de conexão *. */
	// Parâmetros de conexão
	private String driver = "com.mysql.cj.jbdc.Driver";
	
	/** The url. */
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	
	/** The user. */
	private String user = "root";
	
	/** The password. */
	private String password = "pacoca123";
	
	/** The contato. */
	private JavaBeans contato;

	/**
	 * Con.
	 *
	 * @return the connection
	 */
	// Métodos de conexão
	@SuppressWarnings("unused")
	private Connection con() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 *  CRUD CREATE *.
	 *
	 * @param contato the contato
	 */
	public void inserirContato(JavaBeans contato) {
		String create = "insert into contatos (nome,fone,email) values (?,?,?)";
		try {
			// abrir a comexão
			Connection con = conectar();
			// Preparar a query para execução no banco de dados
			PreparedStatement pst = con.prepareCall(create);
			// Substituir os parâmetros (?) pelo conteúdo das variáveis JavaBeans
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			// Executar a query
			pst.executeUpdate();
			// Encerrar a conexão com o banco
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	private Connection conectar() {
		return null;
	}

	/**
	 * CRUD READ*.
	 *
	 * @return the array list
	 */
	
	public ArrayList<JavaBeans> listarContatos(){
		//Criando um objeto para acessar a classe JavaBeans
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		//
		String read = "selct * from contatos order by nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			// o laço abaixo será executado enquanto houver contatos
			while (rs.next()) {
				//variáveis de apoio que recebem os dados do banco
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				// populando o ArrayList
				contatos.add(new JavaBeans(idcon,nome,fone,email));
			}
			con.close();
			return contatos;
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
		
		/**
		 *  CRUD UPDATE *.
		 *
		 * @param contato the contato
		 */
		//selecionar o contato
		public void selecionarContato(JavaBeans contato) {
			String read2 = "select * from contato where idcon = ?";
			try {
				Connection con = conectar();
				PreparedStatement pst = con.prepareStatement(read2);
				pst.setString(1, contato.getIdcon());
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					contato.setIdcon(rs.getString(1));
					contato.setNome(rs.getString(2));
					contato.setFone(rs.getString(3));
					contato.setEmail(rs.getString(4));
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
				
			}
			
			/**
			 * Alterar contato.
			 *
			 * @param conato the conato
			 */
			// Editar o contato
			public void alterarContato(JavaBeans conato) {
				String create = "update conatos set nome=?,fone=?,email? where idcon?";
				try {
					Connection con = conectar();
					PreparedStatement pst = con.prepareStatement(create);
					
					pst.setString(1, contato.getNome());
					pst.setString(2, contato.getFone());
					pst.setString(3, contato.getEmail());
					pst.setString(4, contato.getIdcon());
					pst.executeUpdate();
					con.close();
					
				} catch (Exception e) {
					System.out.println(e);
				}
			
			
			}
	
	/**
	 * CRUD DELETE *.
	 *
	 * @param contato the contato
	 */
	public void deletarContato(JavaBeans contato) {
		String delete = "delete from conattos where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, contato.getIdcon());
			pst.executeUpdate();
			con.close();
			
		} catch (Exception e) {
			
		}
		
	}


}

