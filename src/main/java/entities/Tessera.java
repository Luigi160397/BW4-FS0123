package entities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tessere")
@Getter
@Setter
@NoArgsConstructor

public class Tessera {

	@Id
	@GeneratedValue
	private UUID numeroTessera;
	private String nome;
	private String cognome;
	private LocalDate dataNascita;
	private LocalDate dataRilascio;
	private LocalDate dataScadenza;
	@OneToMany(mappedBy = "tessere")
	private Ticket ticket;

	public Tessera(String nome, String cognome, LocalDate dataNascita, LocalDate dataRilascio, LocalDate dataScadenza) {
		super();

		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.dataRilascio = dataRilascio;
		this.dataScadenza = dataScadenza;
	}

}
