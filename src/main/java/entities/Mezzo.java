package entities;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mezzi")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@NoArgsConstructor
@NamedQuery(name = "getStatoMezzo", query = "SELECT s.stato FROM Mezzo s WHERE s.id = :id")
@NamedQuery(name = "getBigliettiVidimatiMezzo", query = "SELECT s.numeroBigliettiVidimati FROM Mezzo s WHERE s.id = :id")

@NamedQuery(name = "getNumeroPassaggiTappa", query = "SELECT t.tratta.tappa.numeroVoltePercorsa FROM Mezzo t WHERE t.id = :id")
public abstract class Mezzo {
	@Id
	@GeneratedValue
	private UUID id;
	private int capienza;
	@Enumerated(EnumType.STRING)
	private StatoMezzo stato;
	private LocalDate dataInizioServizio;
	private LocalDate dataFineServizio;
	private LocalDate dataInizioManutenzione;
	private LocalDate dataFineManutenzione;
	private int numeroBigliettiVidimati;
	@OneToOne
	@JoinColumn(name = "tratta_id", referencedColumnName = "id")
	private Tratta tratta;
	@OneToMany(mappedBy = "mezzoTimbratura")
	private Set<Ticket> tickets;

	public Mezzo(int capienza, StatoMezzo stato, LocalDate dataInizioServizio, LocalDate dataFineServizio,
			LocalDate dataInizioManutenzione, LocalDate dataFineManutenzione, int numeroBigliettiVidimati,
			Tratta tratta) {
		this.capienza = capienza;
		this.stato = stato;
		this.dataInizioServizio = dataInizioServizio;
		this.dataFineServizio = dataFineServizio;
		this.dataInizioManutenzione = dataInizioManutenzione;
		this.dataFineManutenzione = dataFineManutenzione;
		this.numeroBigliettiVidimati = numeroBigliettiVidimati;
		this.tratta = tratta;
	}

	public Mezzo(int capienza, LocalDate dataInizioServizio, LocalDate dataFineServizio,
			LocalDate dataInizioManutenzione, LocalDate dataFineManutenzione, int numeroBigliettiVidimati,
			Tratta tratta) {
		this.capienza = capienza;
		this.stato = StatoMezzo.IN_SERVIZIO;
		this.dataInizioServizio = dataInizioServizio;
		this.dataFineServizio = dataFineServizio;
		this.dataInizioManutenzione = dataInizioManutenzione;
		this.dataFineManutenzione = dataFineManutenzione;
		this.numeroBigliettiVidimati = numeroBigliettiVidimati;
		this.tratta = tratta;
	}

	@Override
	public String toString() {
		return "Mezzo [id=" + id + ", capienza=" + capienza + ", stato=" + stato + ", dataInizioServizio="
				+ dataInizioServizio + ", dataFineServizio=" + dataFineServizio + ", dataInizioManutenzione="
				+ dataInizioManutenzione + ", dataFineManutenzione=" + dataFineManutenzione
				+ ", numeroBigliettiVidimati=" + numeroBigliettiVidimati + ", tratta=" + tratta + "]";
	}

}
