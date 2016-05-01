package br.com.gpfurlaneto.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "TB_USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator ="SQ_USER")
	@SequenceGenerator(sequenceName="SQ_USER",  name="SQ_USER", initialValue=1, allocationSize=100)
	@Column(name="ID_USER")
	private Long id;

	@NotEmpty
	@Column(name="NM_USER")
	private String nome;

	@NotNull(message="{org.hibernate.validator.constraints.NotEmpty.message}")
	@Column(name="DT_NASCIMENTO")
	private Date dataNascimento;
	
	@NotEmpty
	@Email
	@Column(name="DS_EMAIL")
	private String email;
	
	@NotEmpty
	@Column(name="DS_LOGIN")
	private String login;
	
	@NotEmpty
	@Column(name="DS_SENHA")
	private String senha;

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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
