package pe.com.registro2026.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "ProveedorEntity")
@Table(name = "proveedor")
public class ProveedorEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "codprov")
	private long codigo;
	
	@Column(name = "nomprov", length = 40, nullable = false)
	private String nombre;
	
	@Column(name = "repprov", length = 80, nullable = false)
	private String representante;
	
	@Column(name = "rucprov", length = 11, nullable = false)
	private String ruc;
	
	@Column(name = "dirprov", length = 300, nullable = false)
	private String direccion;
	
	@Column(name = "telprov", length = 7)
	private String telefono;
	
	@Column(name = "celprov", length = 9)
	private String celular;
	
	@Column(name = "corprov", length = 40, nullable = false)
	private String correo;
	
	@Column(name = "estprov", nullable = false)
	private boolean estado;
	
	@ManyToOne
	@JoinColumn(name = "coddis", nullable = false)
	private DistritoEntity distrito;
}
