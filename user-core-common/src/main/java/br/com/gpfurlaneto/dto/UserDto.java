package br.com.gpfurlaneto.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.gpfurlaneto.constants.UserCoreCommonConstants;

public class UserDto {

	private Long id;
	private String nome;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = UserCoreCommonConstants.DATE_PATTERN)
	private Date dataNascimento;
	private String email;
	private String login;
	private String senha;
	private String token;
	
	public UserDto() {
	}
	
	public UserDto(Long id, String nome, Date dataNascimento, String email, String login) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.login = login;
	}

	public UserDto(Long id, String nome, String email, String login) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.login = login;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;	
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
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
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
