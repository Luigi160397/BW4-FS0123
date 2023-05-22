package entities;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "distributori")
@Getter
@Setter
@NoArgsConstructor

public class Distributore {

	@Id
	@GeneratedValue
	private UUID id;
	@Enumerated(EnumType.STRING)
	private TipoDistributore tipoDistributore;
	@Enumerated(EnumType.STRING)
	private StatoDistributore statoAttivita;
	@OneToMany(mappedBy = "distributore")
	private Set<Ticket> tickets;

	public Distributore(TipoDistributore tipoDistributore, StatoDistributore statoAttivita) {
		super();

		this.tipoDistributore = tipoDistributore;
		this.statoAttivita = statoAttivita;
	}

	public Distributore(TipoDistributore tipoDistributore) {
		super();

		this.tipoDistributore = tipoDistributore;
		this.statoAttivita = null;

	}

	@Override
	public String toString() {
		return "Distributore [id=" + id + ", tipoDistributore=" + tipoDistributore + ", statoAttivita=" + statoAttivita
				+ "]";
	}

}
