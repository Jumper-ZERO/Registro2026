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
@Entity(name = "RegistroCompraEntity")
@Table(name = "registrocompra")
public class RegistroCompraEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "nrocom")
	private long codigo;
	
	@Column(name = "feccom", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechacompra;
	
	@Column(name = "estcom", nullable = false)
	private boolean estado;
	
	@ManyToOne
	@JoinColumn(name = "codprov", nullable = false)
	private ProveedorEntity proveedor;
	
	@ManyToOne
	@JoinColumn(name = "codemp", nullable = false)
	private EmpleadoEntity empleado;
}
