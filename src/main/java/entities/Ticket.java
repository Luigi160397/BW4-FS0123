package entities;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class Ticket {
	private UUID id;
	private Tessera tessera;
	private LocalDate DataEmissione;
	private LocalDate DataScadenza;
	private Distributore distributore;
	private LocalDate dataTimbratura;
	private Mezzo mezzoTimbratura;

	public Ticket(Tessera tessera, LocalDate dataEmissione, LocalDate dataScadenza, Distributore distributore,
			LocalDate dataTimbratura, Mezzo mezzoTimbratura) {
		super();
		this.tessera = tessera;
		DataEmissione = dataEmissione;
		DataScadenza = dataScadenza;
		this.distributore = distributore;
		this.dataTimbratura = dataTimbratura;
		this.mezzoTimbratura = mezzoTimbratura;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", tessera=" + tessera + ", DataEmissione=" + DataEmissione + ", DataScadenza="
				+ DataScadenza + ", distributore=" + distributore + ", dataTimbratura=" + dataTimbratura
				+ ", mezzoTimbratura=" + mezzoTimbratura + "]";
	}

}
