package entities;


import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor


public class Tratta {
	private UUID id;
	private Mezzo mezzo;
	private String stazionePartenza;
	private String capolinea;
	private double tempoEffettivoPercorrenza;
    private Tappa tappa;
    private double tempoMedioPercorrenza;
	public Tratta(Mezzo mezzo, String stazionePartenza, String capolinea, double tempoEffettivoPercorrenza, Tappa tappa,
			double tempoMedioPercorrenza) {
		super();
		this.mezzo = mezzo;
		this.stazionePartenza = stazionePartenza;
		this.capolinea = capolinea;
		this.tempoEffettivoPercorrenza = tempoEffettivoPercorrenza;
		this.tappa = tappa;
		this.tempoMedioPercorrenza = tempoMedioPercorrenza;
	}
	@Override
	public String toString() {
		return "Tratta [id=" + id + ", mezzo=" + mezzo + ", stazionePartenza=" + stazionePartenza + ", capolinea="
				+ capolinea + ", tempoEffettivoPercorrenza=" + tempoEffettivoPercorrenza + ", tappa=" + tappa
				+ ", tempoMedioPercorrenza=" + tempoMedioPercorrenza + "]";
	}

  
   

    
    
}

