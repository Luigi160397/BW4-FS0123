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
public class Biglietto extends Ticket {
	public Biglietto(Set<Tessera> tessere, LocalDate dataEmissione, LocalDate dataScadenza,
			Set<Distributore> distributori, LocalDate dataTimbratura, Set<Mezzo> mezziTimbratura) {
		super(tessere, dataEmissione, dataScadenza = dataEmissione.plusDays(1), distributori, dataTimbratura,
				mezziTimbratura);

	}

	@Override
	public String toString() {
		return "Biglietto [getId()=" + getId() + ", getDataEmissione()=" + getDataEmissione() + ", getDataScadenza()="
				+ getDataScadenza() + ", getDataTimbratura()=" + getDataTimbratura() + "]";
	}

}
