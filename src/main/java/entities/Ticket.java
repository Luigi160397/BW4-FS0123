package entities;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tickets")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@NoArgsConstructor

public abstract class Ticket {
	@Id
	@GeneratedValue
	private UUID id;
	@ManyToOne

	private Set<Tessera> tessere;
	private LocalDate DataEmissione;
	private LocalDate DataScadenza;
	@ManyToOne
	private Set<Distributore> distributori;
	private LocalDate dataTimbratura;
	@ManyToOne
	private Set<Mezzo> mezziTimbratura;

	public Ticket(Set<Tessera> tessere, LocalDate dataEmissione, LocalDate dataScadenza, Set<Distributore> distributori,
			LocalDate dataTimbratura, Set<Mezzo> mezziTimbratura) {
		super();
		this.tessere = tessere;
		DataEmissione = dataEmissione;
		DataScadenza = dataScadenza;
		this.distributori = distributori;
		this.dataTimbratura = dataTimbratura;
		this.mezziTimbratura = mezziTimbratura;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", DataEmissione=" + DataEmissione + ", DataScadenza=" + DataScadenza
				+ ", dataTimbratura=" + dataTimbratura;

	}

}
