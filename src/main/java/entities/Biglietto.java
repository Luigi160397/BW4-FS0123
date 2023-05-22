package entities;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Biglietto extends Ticket {

	public Biglietto(Tessera tessera, LocalDate dataEmissione, LocalDate dataScadenza, Distributore distributore,
			LocalDate dataTimbratura, Mezzo mezzoTimbratura) {
		super(tessera, dataEmissione, dataScadenza = dataEmissione.plusDays(1), distributore, dataTimbratura,
				mezzoTimbratura);

	}

	@Override
	public String toString() {
		return "Biglietto [ getId()=" + getId() + ", getTessera()=" + getTessera() + ", getDataEmissione()="
				+ getDataEmissione() + ", getDataScadenza()=" + getDataScadenza() + ", getDistributore()="
				+ getDistributore() + ", getDataTimbratura()=" + getDataTimbratura() + ", getMezzoTimbratura()="
				+ getMezzoTimbratura() + "]";
	}

}
