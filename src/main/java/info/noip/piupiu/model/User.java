package info.noip.piupiu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@SequenceGenerator(name = "user_seq", sequenceName = "user_seq")
	private Long id;

	@Column(nullable = false, length = 45)
	@NotEmpty(message = "- O Nome é obrigatório.")
	@Length(min = 3, message = "- O Nome deve conter no mínimo 3 caracteres.")
	private String name;

	@Column(nullable = false, length = 45)
	@NotEmpty(message = "- A Senha é obrigatória.")
	private String password;

	@Transient
	@NotEmpty(message = "- A Confirmação de Senha é obrigatória.")
	private String passwordConfirmation;

	@Column(unique = true, nullable = false, length = 45)
	@Email(message = "- E-mail inválido.")
	@NotEmpty(message = "- O E-mail é obrigatório.")
	private String email;
	
	private String hashFoto;

	public String getHashFoto() {
		return hashFoto;
	}

	public void setHashFoto(String hashFoto) {
		this.hashFoto = hashFoto;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
