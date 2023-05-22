package entities;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mezzi")
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@NoArgsConstructor
public abstract class Mezzo {
	@Id
	@GeneratedValue
	private UUID id;
	private int capienza;
	private StatoMezzo stato;
	private LocalDate dataInizioServizio;
	private LocalDate dataFineServizio;
	private LocalDate dataInizioManutenzione;
	private LocalDate dataFineManutenzione;
	private int numeroBigliettiVidimati;
	@OneToOne
	private Tratta tratta;
	@OneToMany(mappedBy = "mezzoTimbratura")
	private Set<Ticket> tickets;

	public Mezzo(int capienza, StatoMezzo stato, LocalDate dataInizioServizio, LocalDate dataFineServizio,
			LocalDate dataInizioManutenzione, LocalDate dataFineManutenzione, int numeroBigliettiVidimati, Tratta tratta) {
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
			LocalDate dataInizioManutenzione, LocalDate dataFineManutenzione, int numeroBigliettiVidimati, Tratta tratta) {
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
