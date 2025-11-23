package pe.com.registro2026.entity;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

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
@Entity(name = "EmpleadoEntity")
@Table(name = "empleado")
public class EmpleadoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codemp")
	private long codigo;

	@Column(name = "nomemp", length = 30, nullable = false)
	private String nombre;

	@Column(name = "apepemp", length = 30, nullable = false)
	private String apellidopaterno;

	@Column(name = "apememp", length = 30, nullable = false)
	private String apellidomaterno;

	@Column(name = "fecemp", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechanacimiento;

	@Column(name = "docemp", length = 20, nullable = false, unique = true)
	private String numerodocumento;

	@Column(name = "diremp", length = 300, nullable = false)
	private String direccion;

	@Column(name = "telemp", length = 7)
	private String telefono;

	@Column(name = "celemp", length = 9, nullable = false)
	private String celular;

	@Column(name = "coremp", length = 30, nullable = false)
	private String correo;
	
	@Column(name = "usuemp", length = 40, nullable = false)
	private String usuario;
	
	@Column(name = "claemp", length = 60, nullable = false)
	private String clave;

	@Column(name = "estemp", nullable = false)
	private boolean estado;

	@ManyToOne
	@JoinColumn(name = "coddis", nullable = false)
	private DistritoEntity distrito;

	@ManyToOne
	@JoinColumn(name = "codsex", nullable = false)
	private SexoEntity sexo;

	@ManyToOne
	@JoinColumn(name = "codtipd", nullable = false)
	private TipoDocumentoEntity tipodocumento;

	@ManyToOne
	@JoinColumn(name = "codrol", nullable = false)
	private RolEntity rol;
}