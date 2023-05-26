package app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.DistributoreDAO;
import dao.MezzoDAO;
import dao.TappaDAO;
import dao.TesseraDAO;
import dao.TicketDAO;
import dao.TrattaDAO;
import entities.Abbonamento;
import entities.Biglietto;
import entities.Bus;
import entities.Distributore;
import entities.Mezzo;
import entities.StatoDistributore;
import entities.StatoMezzo;
import entities.Tappa;
import entities.Tessera;
import entities.Ticket;
import entities.TipoAbbonamento;
import entities.TipoDistributore;
import entities.Tram;
import entities.Tratta;
import lombok.extern.slf4j.Slf4j;
import utils.JpaUtil;

@Slf4j
public class Application {

	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {

		EntityManager em = emf.createEntityManager();

		DistributoreDAO distDao = new DistributoreDAO(em);
		MezzoDAO mezzoDao = new MezzoDAO(em);
		TappaDAO tappaDao = new TappaDAO(em);
		TesseraDAO tessDao = new TesseraDAO(em);
		TicketDAO ticDao = new TicketDAO(em);
		TrattaDAO trattaDao = new TrattaDAO(em);

		Tessera tessera1 = new Tessera("Paolo", "Bitta", LocalDate.of(1975, 3, 23), LocalDate.of(2023, 3, 10));
		Tessera tessera2 = new Tessera("Aldo", "Baglio", LocalDate.of(1968, 7, 17), LocalDate.of(2023, 4, 12));
		Tessera tessera3 = new Tessera("Ajeje", "Brazorf", LocalDate.of(1978, 6, 17), LocalDate.of(2023, 4, 15));

		Distributore distributore1 = new Distributore(TipoDistributore.AUTOMATICO, StatoDistributore.ATTIVO);
		Distributore distributore2 = new Distributore(TipoDistributore.FISICO, null);

		Tappa tappa1 = new Tappa("Colosseo", "Fori Imperiali", 5);
		Tappa tappa2 = new Tappa("Colosseo", "Cavour", 7);
		Tappa tappa3 = new Tappa("Colosseo", "Stadio Olimpico", 10);

		Tratta tratta1 = new Tratta(null, "Colosseo", "Termini", 0.40, tappa1, 0.30);
		Tratta tratta2 = new Tratta(null, "Colosseo", "Termini", 0.40, tappa2, 0.30);
		Tratta tratta3 = new Tratta(null, "Colosseo", "Termini", 0.50, tappa3, 0.40);

		Bus bus1 = new Bus(50, StatoMezzo.IN_SERVIZIO, LocalDate.of(2023, 10, 14), LocalDate.of(2023, 10, 17), null,
				null, 15, tratta1);
		Bus bus2 = new Bus(50, StatoMezzo.IN_SERVIZIO, LocalDate.of(2023, 10, 14), LocalDate.of(2023, 10, 17), null,
				null, 35, tratta2);
		Tram tram1 = new Tram(80, StatoMezzo.IN_SERVIZIO, LocalDate.of(2023, 10, 14), LocalDate.of(2023, 10, 17), null,
				null, 27, tratta3);

		Biglietto biglietto1 = new Biglietto(tessera1, LocalDate.of(2023, 10, 15), distributore1,
				LocalDate.of(2023, 10, 16), bus1);
		Biglietto biglietto2 = new Biglietto(tessera3, LocalDate.of(2023, 10, 15), distributore2,
				LocalDate.of(2023, 10, 16), tram1);

		Abbonamento abbonamento1 = new Abbonamento(tessera2, LocalDate.of(2023, 7, 16), distributore1,
				LocalDate.of(2023, 7, 17), bus2, TipoAbbonamento.MENSILE);

//		tessDao.save(tessera1);
//		tessDao.save(tessera2);
//		tessDao.save(tessera3);
//		distDao.save(distributore1);
//		distDao.save(distributore1);
//		distDao.save(distributore2);
//		tappaDao.save(tappa1);
//		tappaDao.save(tappa2);
//		tappaDao.save(tappa3);
//		trattaDao.save(tratta1);
//		trattaDao.save(tratta2);
//		trattaDao.save(tratta3);
//		mezzoDao.save(bus1);
//		mezzoDao.save(bus2);
//		mezzoDao.save(tram1);
//		ticDao.save(biglietto1);
//		ticDao.save(abbonamento1);
//		ticDao.save(biglietto2);
//		tratta1.setMezzo(bus1);
//		tratta2.setMezzo(bus2);
//		tratta3.setMezzo(tram1);
//		trattaDao.update(tratta1);
//		trattaDao.update(tratta2);
//		trattaDao.update(tratta3);

		Scanner scanner = new Scanner(System.in);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		int scelta = -1;
		System.out.println();
		System.out.println(Colors.ANSI_CYAN
				+ "-------------------------- Gestionale azienda di Trasporti --------------------------");
		System.out.println();

		System.out.println(Colors.ANSI_GREEN + "Scegli cosa vuoi controllare: (premi 0 per uscire)");
		System.out.println();

		System.out.println(Colors.ANSI_GREEN + "1. Trova biglietti emessi in un range di tempo");
		System.out.println(
				Colors.ANSI_GREEN + "2. Trova biglietti emessi per un range di tempo e per punto di emissione");
		System.out.println(Colors.ANSI_GREEN + "3. Trova biglietti validi per numero di tessera");
		System.out.println(Colors.ANSI_GREEN + "4. Trova lo stato del mezzo tramite ID");
		System.out.println(Colors.ANSI_GREEN + "5. Trova numero di biglietti vidimati in un mezzo");
		System.out.println(Colors.ANSI_GREEN + "6. Trova biglietti vidimati in un mezzo in un periodo di tempo");
		System.out.println(Colors.ANSI_GREEN + "7. Trova il numero di volte che una tappa è stata percorsa");
		System.out.println(Colors.ANSI_GREEN + "8. Trova il tempo effettivo di percorrenza di una tappa");
		System.out.println(Colors.ANSI_GREEN + "9. Registra nuovo utente");
		System.out.println(Colors.ANSI_GREEN + "10. Emetti nuovo biglietto");
		System.out.println(Colors.ANSI_GREEN + "11. Emetti nuovo abbonamento");
		System.out.println(Colors.ANSI_GREEN + "12. Cambia lo stato di un mezzo");
		System.out.println(Colors.ANSI_GREEN + "13. Cerca quale mezzo percorre una tratta");
		System.out.println(Colors.ANSI_GREEN + "14. Cerca tutti i mezzi in servizio");
		System.out.println(Colors.ANSI_GREEN + "15. Cerca tutti i mezzi in manutenzione");
		while (scelta != 0) {

			try {
				scelta = scanner.nextInt();

				switch (scelta) {
				case 1:
					scanner.nextLine();
					System.out.println(Colors.ANSI_GREEN + "Scegli un periodo di tempo");

					System.out.println(Colors.ANSI_GREEN + "Inserisci la prima data (formato: yyyy-MM-dd):");
					String input = scanner.nextLine();
					LocalDate data1 = null;
					boolean isValidInput = false;

					while (!isValidInput) {
						try {
							data1 = LocalDate.parse(input, formatter);
							isValidInput = true;
						} catch (DateTimeParseException e) {
							System.out.println(Colors.ANSI_RED
									+ "Data inserita in formato non valido. Reinserisci la prima data (formato: yyyy-MM-dd):");
							input = scanner.nextLine();
						}
					}

					System.out.println();
					System.out.println(Colors.ANSI_GREEN + "Inserisci la seconda data (formato: yyyy-MM-dd):");
					String input2 = scanner.nextLine();
					LocalDate data2 = null;
					isValidInput = false;

					while (!isValidInput) {
						try {
							data2 = LocalDate.parse(input2, formatter);
							isValidInput = true;
						} catch (DateTimeParseException e) {
							System.out.println(Colors.ANSI_RED
									+ "Data inserita in formato non valido. Reinserisci la seconda data (formato: yyyy-MM-dd):");
							input2 = scanner.nextLine();
						}
					}

					System.out.println();

					List<Ticket> trovatiPerPeriodoInTot = ticDao.getTotalTicket(data1, data2);
					log.info(Colors.ANSI_PURPLE
							+ "--------------------- Ticket Trovati range di tempo ---------------------");
					if (trovatiPerPeriodoInTot.size() > 0) {
						trovatiPerPeriodoInTot.stream().forEach(t -> log.info(Colors.ANSI_PURPLE + t.toString()));
					} else {
						log.info(Colors.ANSI_RED + "Nessun Ticket trovato per l'intervallo di tempo inserito");
					}
					break;

				case 2:
					scanner.nextLine();
					System.out.println(Colors.ANSI_GREEN
							+ "Inserisci il range di tempo e il punto di emissione per trovare i biglietti emessi");
					System.out.println(Colors.ANSI_GREEN + "Inserisci la prima data (formato: yyyy-MM-dd):");
					String input3 = scanner.nextLine();
					LocalDate data3 = null;
					boolean isValidInput3 = false;

					while (!isValidInput3) {
						try {
							data3 = LocalDate.parse(input3, formatter);
							isValidInput3 = true;
						} catch (DateTimeParseException e) {
							System.out.println(Colors.ANSI_RED
									+ "Data inserita in formato non valido. Reinserisci la prima data (formato: yyyy-MM-dd):");
							input3 = scanner.nextLine();
						}
					}

					System.out.println();
					System.out.println(Colors.ANSI_GREEN + "Inserisci la seconda data (formato: yyyy-MM-dd):");
					String input4 = scanner.nextLine();
					LocalDate data4 = null;
					boolean isValidInput4 = false;

					while (!isValidInput4) {
						try {
							data4 = LocalDate.parse(input4, formatter);
							isValidInput4 = true;
						} catch (DateTimeParseException e) {
							System.out.println(Colors.ANSI_RED
									+ "Data inserita in formato non valido. Reinserisci la seconda data (formato: yyyy-MM-dd):");
							input4 = scanner.nextLine();
						}
					}

					System.out.println(Colors.ANSI_GREEN + "Inserisci il Tipo di Distributore (Automatico/Fisico):");
					String input5 = scanner.nextLine();

					while (!input5.equalsIgnoreCase("automatico") && !input5.equalsIgnoreCase("fisico")) {
						System.out.println(Colors.ANSI_RED
								+ "Tipo Distributore Inesistente! Reinserisci il Tipo di Distributore (Automatico/Fisico):");
						input5 = scanner.nextLine();
					}

					TipoDistributore tipo = TipoDistributore.valueOf(input5.toUpperCase());
					System.out.println();

					List<Ticket> trovatiPerPeriodoEPuntoEmissione = ticDao.getTotalTicketByEmissionPoint(data3, data4,
							tipo);
					log.info(Colors.ANSI_PURPLE
							+ "--------------------- Ticket Trovati range di tempo e Distributore ---------------------");
					if (trovatiPerPeriodoEPuntoEmissione.size() > 0) {
						trovatiPerPeriodoEPuntoEmissione.stream()
								.forEach(t -> log.info(Colors.ANSI_PURPLE + t.toString()));
					} else {
						log.info(Colors.ANSI_RED
								+ "Nessun Ticket trovato per l'intervallo di tempo e distributore inseriti");
					}
					break;

				case 3:
					scanner.nextLine();
					System.out.println(Colors.ANSI_GREEN
							+ "Inserisci il numero di tessera e la data corrente per trovare i biglietti validi");
					System.out.println(Colors.ANSI_GREEN + "Inserisci il numero di tessera utente:");
					String tessera = scanner.nextLine();
					System.out.println(Colors.ANSI_GREEN + "Inserisci la data corrente:");
					String input6 = scanner.nextLine();
					LocalDate data5 = null;
					boolean isValidInput6 = false;

					while (!isValidInput6) {
						try {
							data5 = LocalDate.parse(input6, formatter);
							isValidInput6 = true;
						} catch (DateTimeParseException e) {
							System.out.println(Colors.ANSI_RED
									+ "Data inserita in formato non valido. Reinserisci la data corrente (formato: yyyy-MM-dd):");
							input6 = scanner.nextLine();
						}
					}

					List<Ticket> trovatiAbbonamentiValidi = ticDao.getAbbonamentiValidiPerNumeroTessera(tessera, data5);
					log.info(Colors.ANSI_PURPLE
							+ "--------------------- Ticket Validi Trovati Per Numero Di Tessera ---------------------");
					if (trovatiAbbonamentiValidi.size() > 0) {
						trovatiAbbonamentiValidi.stream().forEach(t -> log.info(Colors.ANSI_PURPLE + t.toString()));
					} else {
						log.info(Colors.ANSI_RED + "Nessun Ticket valido trovato per il numero di tessera inserito");
					}

					break;

				case 4:
					scanner.nextLine();
					System.out
							.println(Colors.ANSI_GREEN + "Inserisci l'Id del mezzo per trovare il suo stato attività:");
					String mezzoId = scanner.nextLine();
					try {
						StatoMezzo trovatoStatoMezzoPerId = mezzoDao.getStatoMezzoById(mezzoId);
						log.info(Colors.ANSI_PURPLE + "" + trovatoStatoMezzoPerId);
					} catch (Exception e) {
						log.info(Colors.ANSI_RED + "Nessun stato mezzo trovato per l'Id inserito");
					}
					break;
				case 5:
					scanner.nextLine();
					System.out
							.println(Colors.ANSI_GREEN + "Inserisci l'Id del mezzo per trovare i biglietti vidimati:");
					String mezzoId2 = scanner.nextLine();
					try {
						int trovatiNumeroBigliettiVidimatiPerId = mezzoDao.getBigliettiVidimatiMezzoById(mezzoId2);
						log.info(Colors.ANSI_PURPLE + "" + trovatiNumeroBigliettiVidimatiPerId);

					} catch (Exception e) {
						log.info(Colors.ANSI_RED + "Nessun biglietto vidimato trovato per l'Id inserito");
					}
					break;
				case 6:
					scanner.nextLine();
					System.out.println(Colors.ANSI_GREEN
							+ "Inserisci l'intervallo di tempo entro cui cercare i Biglietti Vidimati");
					System.out.println(Colors.ANSI_GREEN + "Inserisci la prima data (formato: yyyy-MM-dd):");
					String input7 = scanner.nextLine();
					LocalDate data6 = null;
					boolean isValidInput7 = false;

					while (!isValidInput7) {
						try {
							data6 = LocalDate.parse(input7, formatter);
							isValidInput7 = true;
						} catch (DateTimeParseException e) {
							System.out.println(Colors.ANSI_RED
									+ "Data inserita in formato non valido. Reinserisci la prima data (formato: yyyy-MM-dd):");
							input7 = scanner.nextLine();
						}
					}

					System.out.println(Colors.ANSI_GREEN + "Inserisci la seconda data (formato: yyyy-MM-dd):");
					String input8 = scanner.nextLine();
					LocalDate data7 = null;
					boolean isValidInput8 = false;

					while (!isValidInput8) {
						try {
							data7 = LocalDate.parse(input8, formatter);
							isValidInput8 = true;
						} catch (DateTimeParseException e) {
							System.out.println(Colors.ANSI_RED
									+ "Data inserita in formato non valido. Reinserisci la seconda data (formato: yyyy-MM-dd):");
							input8 = scanner.nextLine();
						}
					}

					log.info(Colors.ANSI_PURPLE
							+ "--------------------- Bigletti Vidimati Trovati dato un periodo di tempo ---------------------");
					try {
						Long bigliettiTrovatiPerRangeTempo = ticDao.getNumeroBigliettiVidimatiInPeriodoTempo(data6,
								data7);
						log.info(Colors.ANSI_PURPLE + "" + bigliettiTrovatiPerRangeTempo);
					} catch (Exception e) {
						log.info(Colors.ANSI_RED + "Nessun biglietto vidimato trovato per il periodo inserito");
					}

					break;

				case 7:
					scanner.nextLine();
					System.out.println(Colors.ANSI_GREEN
							+ "Inserisci id del mezzo per vedere quante volte è stata percorsa la tappa a lui associata");
					String trattaId = scanner.nextLine();

					log.info(Colors.ANSI_PURPLE
							+ "--------------------- Numero di Volte Che è stata Percorsa la Tappa ---------------------");

					try {
						int numeroPassaggiPerTappa = mezzoDao.getNumeroVolteTappaPercorsa(trattaId);
						log.info(Colors.ANSI_PURPLE + "" + numeroPassaggiPerTappa);
					} catch (Exception e) {
						log.info(Colors.ANSI_RED + "Nessun passaggio trovato per questo ID");
					}

					break;
				case 8:
					scanner.nextLine();
					System.out.println(Colors.ANSI_GREEN
							+ "Inserisci l'Id della tratta per controllare il tempo effettivo di percorrenza della sua tappa:");
					String trattaId2 = scanner.nextLine();
					log.info(Colors.ANSI_PURPLE
							+ "--------------------- Tempo Effettivo Di Percorrenza Tappa ---------------------");

					try {
						double tempoPercorrenzaTappa = trattaDao.getTempoEffettivoPercorrenza(trattaId2);
						log.info(Colors.ANSI_PURPLE + "" + tempoPercorrenzaTappa + " ore");

					} catch (Exception e) {
						log.info(Colors.ANSI_RED + "Nessuna Tratta trovata con questo Id");
					}

					break;
				case 9:
					scanner.nextLine();
					System.out.println(Colors.ANSI_GREEN + "Inserisci il nome del nuovo utente:");
					String input10 = scanner.nextLine();
					System.out.println(Colors.ANSI_GREEN + "Inserisci il cognome del nuovo utente:");
					String input11 = scanner.nextLine();
					System.out.println(
							Colors.ANSI_GREEN + "Inserisci la data di nascita del nuovo utente (formato: yyyy-MM-dd):");
					String input12 = scanner.nextLine();
					LocalDate data8 = null;
					boolean isValidInput12 = false;

					while (!isValidInput12) {
						try {
							data8 = LocalDate.parse(input12, formatter);
							isValidInput12 = true;
						} catch (DateTimeParseException e) {
							System.out.println(Colors.ANSI_RED
									+ "Data di nascita inserita in formato non valido. Reinserisci la data di nascita (formato: yyyy-MM-dd):");
							input12 = scanner.nextLine();
						}
					}

					Tessera tessera4 = new Tessera(input10, input11, data8, LocalDate.now());
					tessDao.save(tessera4);
					break;

				case 10:
					scanner.nextLine();
					System.out.println(Colors.ANSI_GREEN + "Inserisci il numero di tessera dell'utente:");
					String input13 = scanner.nextLine();
					Tessera tessera5 = tessDao.getById(UUID.fromString(input13));
					Distributore distributore3 = distDao
							.getById(UUID.fromString("95d7377b-d0eb-4a52-8cb3-dcb2c0f0aebd"));
					Biglietto biglietto3 = new Biglietto(tessera5, LocalDate.now(), distributore3);
					ticDao.save(biglietto3);
					break;
				case 11:
					scanner.nextLine();
					System.out.println(Colors.ANSI_GREEN + "Inserisci il numero di tessera dell'utente:");
					String input14 = scanner.nextLine();
					Tessera tessera6 = tessDao.getById(UUID.fromString(input14));
					Distributore distributore4 = distDao
							.getById(UUID.fromString("95d7377b-d0eb-4a52-8cb3-dcb2c0f0aebd"));
					System.out.println(Colors.ANSI_GREEN + "Inserisci il tipo di abbonamento (Settimanale/Mensile):");
					String input15 = scanner.nextLine();

					while (!input15.equalsIgnoreCase("settimanale") && !input15.equalsIgnoreCase("mensile")) {
						System.out.println(Colors.ANSI_RED
								+ "Tipo abbonamento Inesistente! Reinserisci il Tipo di abbonamento (Settimanale/Mensile):");
						input15 = scanner.nextLine();
					}
					TipoAbbonamento tipo1 = TipoAbbonamento.valueOf(input15.toUpperCase());
					Abbonamento abbonamento = new Abbonamento(tessera6, LocalDate.now(), distributore4, tipo1);
					ticDao.save(abbonamento);
					break;

				case 12:
					scanner.nextLine();
					System.out.println(
							Colors.ANSI_GREEN + "Inserisci l'id del mezzo di cui si vuole aggiornare lo stato:");
					String input16 = scanner.nextLine();
					System.out.println(Colors.ANSI_GREEN
							+ "Inserisci lo stato del mezzo che si vuole settare (IN_MANUTENZIONE/IN_SERVIZIO):");
					String input17 = scanner.nextLine();

					while (!input17.equalsIgnoreCase("IN_MANUTENZIONE") && !input17.equalsIgnoreCase("IN_SERVIZIO")) {
						System.out.println(Colors.ANSI_RED
								+ "Stato mezzo Inesistente! Reinserisci lo stato del mezzo tra (IN_MANUTENZIONE/IN_SERVIZIO):");
						input17 = scanner.nextLine();
					}
					StatoMezzo statoMezzo = StatoMezzo.valueOf(input17.toUpperCase());

					Mezzo mezzoTrovato = mezzoDao.getById(UUID.fromString(input16));
					if (statoMezzo.equals(StatoMezzo.IN_MANUTENZIONE)) {
						mezzoTrovato.setStato(statoMezzo);
						mezzoTrovato.setDataInizioServizio(null);
						mezzoTrovato.setDataFineServizio(null);
						mezzoTrovato.setDataInizioManutenzione(LocalDate.now());
						mezzoTrovato.setDataFineManutenzione(mezzoTrovato.getDataInizioManutenzione().plusDays(5));
						mezzoDao.update(mezzoTrovato);
					} else {
						mezzoTrovato.setStato(statoMezzo);
						mezzoTrovato.setDataInizioServizio(LocalDate.now());
						mezzoTrovato.setDataFineServizio(mezzoTrovato.getDataInizioServizio().plusMonths(6));
						mezzoTrovato.setDataInizioManutenzione(null);
						mezzoTrovato.setDataFineManutenzione(null);
						mezzoDao.update(mezzoTrovato);
					}
					break;
				case 13:
					scanner.nextLine();
					System.out.println(
							Colors.ANSI_GREEN + "Inserisci l'id della tratta per controllare quale mezzo la percorre:");
					String input18 = scanner.nextLine();
					log.info(Colors.ANSI_PURPLE
							+ "--------------------- Mezzo Trovato per Tratta ---------------------");
					try {
						Mezzo mezzoTrovato1 = mezzoDao.getMezzoByIdTratta(input18);
						log.info(Colors.ANSI_PURPLE + "" + mezzoTrovato1);
					} catch (Exception e) {
						log.info(Colors.ANSI_RED + "Nessun mezzo trovato per questa Tratta");
					}
					break;
				case 14:
					scanner.nextLine();

					List<Mezzo> mezziInServizio = mezzoDao.getMezziInServizio();
					log.info(Colors.ANSI_PURPLE
							+ "--------------------- Mezzi in servizio trovati ---------------------");
					if (mezziInServizio.size() > 0) {
						mezziInServizio.stream().forEach(t -> log.info(Colors.ANSI_PURPLE + t.toString()));
						;
					} else {
						log.info(Colors.ANSI_RED + "Nessun mezzo in servizio trovato");
					}
					break;
				case 15:
					scanner.nextLine();

					List<Mezzo> mezziInManutenzione = mezzoDao.getMezziInManutenzione();
					log.info(Colors.ANSI_PURPLE
							+ "--------------------- Mezzi in manutenzione trovati ---------------------");
					if (mezziInManutenzione.size() > 0) {
						mezziInManutenzione.stream().forEach(t -> log.info(Colors.ANSI_PURPLE + t.toString()));
						;
					} else {
						log.info(Colors.ANSI_RED + "Nessun mezzo in manutenzione trovato");
					}
					break;
				case 0:
					System.out.println(
							Colors.ANSI_GREEN + "Arrivederci, grazie di aver utilizzato la nostra applicazione!");
					break;
				default:
					System.out.println(Colors.ANSI_RED + "Opzione non valida. Riprova.");
				}
			} catch (InputMismatchException e) {
				System.out.println(
						Colors.ANSI_RED + "Non sono ammessi valori testuali. Inserisci un numero compreso tra 1 e 8");
				scanner.nextLine();
			}
		}

		scanner.close();
		em.close();
		emf.close();
	}

}
