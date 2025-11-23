package pe.com.registro2026.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor //metodo constructor con parametros
@NoArgsConstructor // metodo constructor sin parametros
@Data //GETTERS AND SETTERS
@Builder //clase Builder
@Entity(name="SexoEntity")
@Table(name = "sexo")
public class SexoEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id //representa la clave primaria
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name = "codsex")
	private long codigo;
	
	@Column(name = "nomsex", length = 20, nullable = false)
	private String nombre;
	
	@Column(name = "estsex", nullable = false)
	private boolean estado;
}
