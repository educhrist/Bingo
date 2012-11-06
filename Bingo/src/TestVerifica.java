import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;


public class TestVerifica {

	@Test
	public void testVerifica() {
		
		fail("Not yet implemented");
	}

	@Test
	public void testVerificaBingoTrue() {
		//List<Integer> listaSorteados = new ArrayList<Integer>();
		//Map<Double,Integer> sorteados = new TreeMap<Double, Integer>();
		Sorteio sorteio = new Sorteio();
		Ticket ticket = new Ticket();
		sorteio.setLista(ticket.getList());
//		sorteados.put(0.0, 10);
//		sorteados.put(0.1, 13);
//		sorteados.put(0.2, 98);
//		sorteados.put(0.3, 17);
//		sorteados.put(1.0, 6);
//		sorteados.put(2.1, 35);
//		sorteados.put(3.2, 84);
//		sorteados.put(4.3, 65);
//		listaSorteados.add(10);
//		listaSorteados.add(13);
//		listaSorteados.add(98);
//		listaSorteados.add(17);
//		listaSorteados.add(6);
//		listaSorteados.add(35);
//		listaSorteados.add(84);
//		listaSorteados.add(65);
		assertTrue(Verifica.verificaBingo(sorteio, ticket));
		
		//fail("Not yet implemented");
	}
	@Test
	public void testVerificaBingoFalse() {
		//List<Integer> listaSorteados = new ArrayList<Integer>();
		//Map<Double,Integer> sorteados = new TreeMap<Double, Integer>();
		Sorteio sorteio = new Sorteio();
		Ticket ticket = new Ticket();
		//sorteio.setLista(ticket.getList());
//		sorteados.put(0.0, 10);
//		sorteados.put(0.1, 13);
//		sorteados.put(0.2, 98);
//		sorteados.put(0.3, 17);
//		sorteados.put(1.0, 6);
//		sorteados.put(2.1, 35);
//		sorteados.put(3.2, 84);
//		sorteados.put(4.3, 65);
//		listaSorteados.add(10);
//		listaSorteados.add(13);
//		listaSorteados.add(98);
//		listaSorteados.add(17);
//		listaSorteados.add(6);
//		listaSorteados.add(35);
//		listaSorteados.add(84);
//		listaSorteados.add(65);
		assertFalse(Verifica.verificaBingo(sorteio, ticket));
		
		//fail("Not yet implemented");
	}
	
	@Test
	public void testVerificaColuna() {
		fail("Not yet implemented");
	}

}
