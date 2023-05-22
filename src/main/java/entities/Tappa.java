package entities;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class Tappa {
	private UUID id;
	
    private String zonaPartenza;
    private String zonaArrivo;
    private int numeroVoltePercorsa;
	public Tappa(String zonaPartenza, String zonaArrivo, int numeroVoltePercorsa) {
		super();
		this.zonaPartenza = zonaPartenza;
		this.zonaArrivo = zonaArrivo;
		this.numeroVoltePercorsa = numeroVoltePercorsa;
	}
	@Override
	public String toString() {
		return "Tappa [id=" + id + ", zonaPartenza=" + zonaPartenza + ", zonaArrivo=" + zonaArrivo
				+ ", numeroVoltePercorsa=" + numeroVoltePercorsa + "]";
	}
    

   

   
}
