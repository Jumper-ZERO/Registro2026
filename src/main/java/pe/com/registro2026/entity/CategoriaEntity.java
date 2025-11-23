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

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name="CategoriaEntity")
@Table(name="categoria")
public class CategoriaEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name = "codcat")
	private long codigo;
	
	@Column(name = "nomcat", length = 30, nullable = false)
	private String nombre;
	
	@Column(name= "estcat", nullable = false)
	private boolean estado;
}
