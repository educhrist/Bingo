package server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Eduardo Christ
 * @author Lucas Roncato
 */
public class Sorteio extends Thread {

	List<Integer> lista = new ArrayList<Integer>();
	private ServerDispatcher mServerDispatcher;

	public Sorteio(ServerDispatcher mServerDispatcher) {
		super();
		this.mServerDispatcher = mServerDispatcher;
	}

	public void setLista(List<Integer> lista) {
		this.lista = lista;
	}

	public int sorteia() {

		int test = -1;
		while (true) {
			if (lista.size() == 99) {
				System.out.println("Malditos noobas");
				break;
			}

			if (!(lista.contains((test = Ticket.randomNumber(1, 99))))) {
				lista.add(test);
				break;
			}

		}

		return test;

	}

	public List<Integer> getLista() {
		return lista;
	}

	public void run() {
		try {
			while (!isInterrupted()) {
				Thread.sleep(5000);
				String numero = String.valueOf(sorteia());
				if (numero.equals("-1")) {
					interrupt();
				} else {
					mServerDispatcher.dispatchMessage(numero);
					System.out.println(numero);
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
