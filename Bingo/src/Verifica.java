import java.util.Collections;

/*
 * @author Eduardo Christ
 */

public class Verifica {

	// public Verifica(String coluna, Sorteio sorteio,
	// Ticket sorteados) {
	//
	// }
	// public Verifica(Sorteio sorteio,
	// Ticket sorteados) {
	// verificaBingo(sorteio, sorteados);
	//
	// }

	public static boolean verificaBingo(Sorteio sorteio, Ticket sorteados) {

		if (sorteio.getLista().containsAll(sorteados.getList())) {
			// System.out.println("AHHHHHHHHHHHHHHHHHH");
			return true;
		}

		return false;
	}

	public static boolean verificaColuna(String coluna, Sorteio sorteio,
			Ticket sorteados) {

		int contem = 0;
		double coluna2 = 0.0;
		if (coluna != null) {
			if (coluna.endsWith("b"))
				coluna2 = 0.0;
			if (coluna.endsWith("i"))
				coluna2 = 1.0;
			if (coluna.endsWith("n"))
				coluna2 = 2.0;
			if (coluna.endsWith("g"))
				coluna2 = 3.0;
			if (coluna.endsWith("o"))
				coluna2 = 4.0;
		
		//System.out.println(Double.valueOf(coluna2));
		
		for (int i = 0; i < 4; i++) {
			coluna2 += i / 10;
//			String coluna3 = Double.toString(coluna2);
			if (sorteio.getLista().contains(sorteados.getMap().get(coluna2))) {
				contem++;
			}

		}
//		System.out.println(Collections.sort(sorteio.getLista()));
//		System.out.println(sorteados.getMap());
		if (contem == 4) {
			return true;
		}
		}
		return false;

	}
}
