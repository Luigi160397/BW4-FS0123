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
public class Biglietto extends Ticket {

	public Biglietto(Tessera tessera, LocalDate dataEmissione, Distributore distributore, LocalDate dataTimbratura,
			Mezzo mezzoTimbratura) {
		super(tessera, dataEmissione, distributore, dataTimbratura, mezzoTimbratura);
		setDataScadenza(dataTimbratura.plusDays(1));
	}

	@Override
	public String toString() {
		return "Biglietto [getId()=" + getId() + ", getDataEmissione()=" + getDataEmissione() + ", getDataScadenza()="
				+ getDataScadenza() + ", getDataTimbratura()=" + getDataTimbratura() + "]";
	}

}
