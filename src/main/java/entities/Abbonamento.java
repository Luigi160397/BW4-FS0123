package entities;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Abbonamento extends Ticket {

	private TipoAbbonamento tipoAbbonamento;

	public Abbonamento(Tessera tessera, LocalDate dataEmissione, LocalDate dataScadenza, Distributore distributore,
			LocalDate dataTimbratura, Mezzo mezzoTimbratura, TipoAbbonamento tipoAbbonamento) {
		super(tessera, dataEmissione, dataScadenza, distributore, dataTimbratura, mezzoTimbratura);
		this.tipoAbbonamento = tipoAbbonamento;
		if (tipoAbbonamento.equals(TipoAbbonamento.SETTIMANALE)) {
			dataScadenza = dataEmissione.plusWeeks(1);
		} else {
			dataScadenza = dataEmissione.plusMonths(1);
		}
	}

}
