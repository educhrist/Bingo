import java.util.Collection;
import java.util.List;
import java.util.Map;

/*
 * @author Eduardo Christ
 */

public class Verifica {
	
	
	public Verifica(String coluna, List<Integer> sorteio, Map<Double,Integer> sorteados){
		
		if(coluna != null ){
			
		double coluna2 = 0;
		
		if(coluna.endsWith("b"))  coluna2 = 0.0;
		if(coluna.endsWith("i"))  coluna2 = 1.0;
		if(coluna.endsWith("n"))  coluna2 = 2.0;
		if(coluna.endsWith("g"))  coluna2 = 3.0;
		if(coluna.endsWith("o"))  coluna2 = 4.0;
			
		verificaColuna(coluna2,sorteio, sorteados);
		
		}else{
			
		verificaBingo(sorteio, sorteados);
			
		}
	
	}
	
	private boolean verificaBingo(List<Integer> sorteio, Map<Double,Integer> sorteados){
		
		if(sorteio.containsAll((Collection<?>) sorteados)){
			
			return true;
		}		
		
		return false;
	}
	
	private boolean verificaColuna(double coluna,List<Integer> sorteio, Map<Double,Integer> sorteados) {
		
		
		int contem=0;	
			
		for (int i = 0; i < 3; i++) {
			
			if(sorteio.contains(sorteados.get(String.valueOf(coluna + (i/10))))){
				contem++;
			}
	
		}
		
		if (contem==3){
		return true;
		}
		
		return false;
		
		
	}

}
