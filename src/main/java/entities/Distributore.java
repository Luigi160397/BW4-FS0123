package entities;

import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class Distributore {
	
	
	@Id
	@GeneratedValue
	private UUID id;
	private TipoDistributore tipoDistributore;
	private StatoDistributore statoAttivita;
	
	public Distributore(TipoDistributore tipoDistributore, StatoDistributore statoAttivita) {
		super();
		
		this.tipoDistributore = tipoDistributore;
		this.statoAttivita = statoAttivita;
	}
	
	public Distributore(TipoDistributore tipoDistributore) {
		super();
		
		this.tipoDistributore = tipoDistributore;
		this.statoAttivita = null;
		
	}
	
	
	
}
