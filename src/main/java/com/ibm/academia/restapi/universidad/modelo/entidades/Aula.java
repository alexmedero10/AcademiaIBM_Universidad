package com.ibm.academia.restapi.universidad.modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "aulas", schema = "universidad")
//@Table(name = "aulas")
public class Aula implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@PositiveOrZero
	@Column(name = "numero_aula", nullable = false, length = 5)
	private Integer numeroAula;
	
	@Column(name = "medidas")
	private String medidas;
	
	@PositiveOrZero
	@Column(name = "cantidad_pupitres")
	private Integer cantidadPupitres;
	
	@Column(name = "tipo_pizarron")
	@Enumerated(EnumType.STRING)
	private TipoPizarron tipoPizarron;
	
	@NotNull
	@NotEmpty
	@Column(name = "usuario_creacion", nullable = false)
	private String usuarioCreacion;
	
	@Column(name = "fecha_creacion", nullable = false)
	private Date fechaCreacion;
	
	@Column(name = "fecha_modificacion")
	private Date fechaModificacion;
	
	@ManyToOne(optional = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "pabellon_id", foreignKey = @ForeignKey(name = "FK_PABELLON_ID"))
	//@JoinColumn(name = "pabellon_id", foreignKey = @ForeignKey(name = "FK_01_PABELLON_ID"))
	@JsonIgnoreProperties({"aulas"})
	private Pabellon pabellon;

	
	public Aula(Long id, Integer numeroAula, String medidas, Integer cantidadPupitres, TipoPizarron tipoPizarron,
			String usuarioCreacion) {
		this.id = id;
		this.numeroAula = numeroAula;
		this.medidas = medidas;
		this.cantidadPupitres = cantidadPupitres;
		this.tipoPizarron = tipoPizarron;
		this.usuarioCreacion = usuarioCreacion;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Aula [id=");
		builder.append(id);
		builder.append(", numeroAula=");
		builder.append(numeroAula);
		builder.append(", medidas=");
		builder.append(medidas);
		builder.append(", cantidadPupitres=");
		builder.append(cantidadPupitres);
		builder.append(", tipoPizarron=");
		builder.append(tipoPizarron);
		builder.append(", usuarioCreacion=");
		builder.append(usuarioCreacion);
		builder.append(", fechaCreacion=");
		builder.append(fechaCreacion);
		builder.append(", fechaModificacion=");
		builder.append(fechaModificacion);
		builder.append("]");
		return builder.toString();
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, numeroAula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Aula))
			return false;
		Aula other = (Aula) obj;
		return Objects.equals(id, other.id) && Objects.equals(numeroAula, other.numeroAula);
	}

	@PrePersist
	private void antesPersistir()
	{
		this.fechaCreacion = new Date();
	}
	
	@PreUpdate
	private void antesActualizar()
	{
		this.fechaModificacion = new Date();
	}

	private static final long serialVersionUID = -4256732634639334114L;
}
