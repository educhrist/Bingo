import java.util.ArrayList;
import java.util.List;

public class Sorteio {

	List<Integer> lista = new ArrayList<Integer>();

	public void setLista(List<Integer> lista) {
		this.lista = lista;
	}

	public int sorteia() {

		int test = 0;
		while (true) {
			if(lista.size()==99){
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
}
