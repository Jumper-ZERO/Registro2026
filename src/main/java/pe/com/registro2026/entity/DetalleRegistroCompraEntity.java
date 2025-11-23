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
@Entity(name = "DetalleRegistroCompraEntity")
@Table(name = "detalleregistrocompra")
public class DetalleRegistroCompraEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "nrodetcom")
	private long codigo;
	
	@ManyToOne
	@JoinColumn(name = "nrocom", nullable = false)
	private RegistroCompraEntity registrocompra;
	
	@ManyToOne
	@JoinColumn(name = "codpro", nullable = false)
	private ProductoEntity producto;
	
	@Column(name = "cancom", nullable = false)
	private long cantidad;

	@Column(name = "precom", nullable = false)
	private long precio;
}
