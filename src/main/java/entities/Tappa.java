package entities;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tappe")
@Getter
@Setter
@NoArgsConstructor

public class Tappa {
	@Id
	@GeneratedValue
	private UUID id;
	private String zonaPartenza;
	private String zonaArrivo;
	private int numeroVoltePercorsa;
	@OneToMany(mappedBy = "tappa")
	private Set<Tratta> tratte;

	public Tappa(String zonaPartenza, String zonaArrivo, int numeroVoltePercorsa) {
		super();
		this.zonaPartenza = zonaPartenza;
		this.zonaArrivo = zonaArrivo;
		this.numeroVoltePercorsa = numeroVoltePercorsa;
	}

	@Override
	public String toString() {
		return "Tappa [id=" + id + ", zonaPartenza=" + zonaPartenza + ", zonaArrivo=" + zonaArrivo
				+ ", numeroVoltePercorsa=" + numeroVoltePercorsa + "]";
	}

}
