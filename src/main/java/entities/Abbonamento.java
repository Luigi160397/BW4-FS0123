package entities;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Abbonamento extends Ticket {

	private TipoAbbonamento tipoAbbonamento;

	public Abbonamento(Set<Tessera> tessere, LocalDate dataEmissione, LocalDate dataScadenza,
			Set<Distributore> distributori, LocalDate dataTimbratura, Set<Mezzo> mezziTimbratura,
			TipoAbbonamento tipoAbbonamento) {
		super(tessere, dataEmissione, dataScadenza, distributori, dataTimbratura, mezziTimbratura);
		this.tipoAbbonamento = tipoAbbonamento;
		if (tipoAbbonamento.equals(TipoAbbonamento.SETTIMANALE)) {
			dataScadenza = dataEmissione.plusWeeks(1);
		} else {
			dataScadenza = dataEmissione.plusMonths(1);
		}
	}

	@Override
	public String toString() {
		return "Abbonamento [tipoAbbonamento=" + tipoAbbonamento + ", getId()=" + getId() + ", getDataEmissione()="
				+ getDataEmissione() + ", getDataScadenza()=" + getDataScadenza() + ", getDataTimbratura()="
				+ getDataTimbratura() + "]";
	}

}
