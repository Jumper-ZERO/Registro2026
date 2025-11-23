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
@Entity(name = "ProductoEntity")
@Table(name = "producto")
public class ProductoEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "codpro")
	private long codigo;
	
	@Column(name = "nompro", length = 60, nullable = false)
	private String nombre;
	
	@Column(name = "despro", length = 300, nullable = false)
	private String descripcion;
	
	@Column(name = "prepro", nullable = false)
	private long precio;
	
	@Column(name = "canpro", nullable = false)
	private long cantidad;
	
	@Column(name = "fecing", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaingreso;
	
	@Column(name = "estpro", nullable = false)
	private boolean estado;
	
	@ManyToOne
	@JoinColumn(name = "codmar", nullable = false)
	private MarcaEntity marca;
	
	@ManyToOne
	@JoinColumn(name = "codcat", nullable = false)
	private CategoriaEntity categoria;
}
