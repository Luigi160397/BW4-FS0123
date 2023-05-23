package entities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tickets")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NamedQuery(name = "findBigliettiEmessiDatoUnPeriodoInTotale", query = "SELECT t FROM Ticket t WHERE t.DataEmissione >= :start AND t.DataScadenza <= :end")
@NamedQuery(name = "findBigliettiEmessiDatoUnPeriodoPerPuntoEmissione", query = "SELECT t FROM Ticket t WHERE t.DataEmissione >= :start AND t.DataScadenza <= :end AND t.distributore.tipoDistributore= :distributore")
@NamedQuery(name = "findAbbonamentiValidiPerNumeroTessera", query = "SELECT t FROM Ticket t WHERE t.tessera.numeroTessera = :tessera AND t.DataScadenza > :data")
@Getter
@Setter
@NoArgsConstructor
public abstract class Ticket {
	@Id
	@GeneratedValue
	private UUID id;
	@ManyToOne
	private Tessera tessera;
	private LocalDate DataEmissione;
	private LocalDate DataScadenza;
	@ManyToOne
	private Distributore distributore;
	private LocalDate dataTimbratura;
	@ManyToOne
	private Mezzo mezzoTimbratura;

	public Ticket(Tessera tessera, LocalDate dataEmissione, Distributore distributore, LocalDate dataTimbratura,
			Mezzo mezzoTimbratura) {
		super();
		this.tessera = tessera;
		this.DataEmissione = dataEmissione;

		this.distributore = distributore;
		this.dataTimbratura = dataTimbratura;
		this.mezzoTimbratura = mezzoTimbratura;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", DataEmissione=" + DataEmissione + ", DataScadenza=" + DataScadenza
				+ ", dataTimbratura=" + dataTimbratura;

	}

}
