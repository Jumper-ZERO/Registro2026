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
@Entity(name="TipoDocumentoEntity")
@Table(name = "tipodocumento")
public class TipoDocumentoEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "codtipd")
	private long codigo;
	
	@Column(name = "nomtipd",length = 20, nullable = false)
	private String nombre;
	
	@Column(name = "esttipd", nullable = false)
	private boolean estado;
}
