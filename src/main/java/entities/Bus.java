package entities;

import java.time.LocalDate;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Bus extends Mezzo {

	public Bus(int capienza, StatoMezzo stato, LocalDate dataInizioServizio, LocalDate dataFineServizio,
			LocalDate dataInizioManutenzione, LocalDate dataFineManutenzione, int numeroBigliettiVidimati, Tratta tratta) {
		super(capienza = 50, stato, dataInizioServizio, dataFineServizio, dataInizioManutenzione, dataFineManutenzione,
				numeroBigliettiVidimati, tratta);

	}

}
