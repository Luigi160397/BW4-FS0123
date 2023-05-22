package entities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class Mezzo {
	@Id
	@GeneratedValue
	private UUID id;
	private int capienza;
	private StatoMezzo stato = StatoMezzo.IN_SERVIZIO;
	private LocalDate dataInizioServizio;
	private LocalDate dataFineServizio;
	private LocalDate dataInizioManutenzione;
	private LocalDate dataFineManutenzione;
	private int numeroBigliettiVidimati;
	private int tratta;

	public Mezzo(int capienza, StatoMezzo stato, LocalDate dataInizioServizio, LocalDate dataFineServizio,
			LocalDate dataInizioManutenzione, LocalDate dataFineManutenzione, int numeroBigliettiVidimati, int tratta) {
		this.capienza = capienza;
		this.stato = stato;
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
