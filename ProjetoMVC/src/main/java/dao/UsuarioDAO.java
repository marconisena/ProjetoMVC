package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;

import model.Usuario;

public class UsuarioDAO{

	private Connection connection;
	private String sql;

	public UsuarioDAO() throws SQLException {
		connection = DatabaseConnection.createConnection();
	}

	public void closeConnection() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createClient(Usuario usuario) {
		sql = "INSERT INTO usuario (nome,cpf,email,senha,telefone) VALUES (?,?,?,?,?)";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {

			stmt.setString(1,usuario .getNome());
			stmt.setString(2, usuario.getCpf());
			stmt.setString(3,usuario.getEmail ());
			stmt.setString(4, usuario.getSenha());
			stmt.setString(5, usuario.getTelefone());


			stmt.executeUpdate();

			System.out.println("Usuario criado com sucesso!");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Usuario findUsuario(int id) {
		Usuario usuario = null;
		sql = "SELECT * FROM usuario WHERE id=?";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {

			stmt.setInt(1, id);
			ResultSet r = stmt.executeQuery();

			if (r.next()) {
				Usuario  user = new Usuario();
				user.setId(r.getInt("id"));
				user.setNome(r.getString("nome"));
				user.setCpf(r.getString("cpf"));
				user.setEmail(r.getString("email"));
				user.setSenha(r.getString("senha"));
				user.setTelefone(r.getString("telefone"));
				
				return user;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return usuario;
	}
	
	
	
	
	public List<Usuario> findUsuario() {

		String sql = "SELECT * FROM usuario";

		List<Usuario> usuarios = new ArrayList<Usuario>();

		ResultSet r = null;

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			r = stmt.executeQuery();

			while (r.next()) {
				Usuario usuario = new Usuario();
				usuario = new Usuario();
				usuario.setId(r.getInt("id"));
				usuario.setNome(r.getString("nome"));
				usuario.setCpf(r.getString("cpf"));
				usuario.setEmail(r.getString("email"));
				usuario.setSenha(r.getString("senha"));
				usuario.setTelefone(r.getString("telefone"));

				usuarios.add(usuario);

				
			}
		} catch (Exception e) {

			e.printStackTrace();

		}
		return usuarios;

	}
	
	
	

	public void updateUsuario(Usuario usuario) {
		sql = "UPDATE usuario SET nome = ?, cpf = ?,email = ?, senha = ?,telefone = ? WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1,usuario.getNome());
			stmt.setString(2,usuario.getCpf());
			stmt.setString(3,usuario.getEmail());
			stmt.setString(4, usuario.getSenha());
			stmt.setString(5,usuario.getTelefone());
			
			stmt.setInt(6, usuario.getId());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deleteUsuario(int id) {
		sql = "DELETE FROM usuario WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();

			System.out.println("Usuario Deletado com sucesso!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
