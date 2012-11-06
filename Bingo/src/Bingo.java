import java.util.Scanner;




public class Bingo {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		
//		Ticket a = new Ticket();
//		
//		Sorteio b = new Sorteio();
//		
//		System.out.println(b.sorteia());
//		System.out.println(b.sorteia());
//		System.out.println(b.sorteia());
//		System.out.println(a.getMap());
//		System.out.println(a.getList());
//		System.out.println(b.getLista());
		
		Sorteio sorteio = new Sorteio();
		Ticket ticket = new Ticket();
		 Scanner scanner = new Scanner(System.in);
		 String in;
		while(true){
			System.out.println(ticket.getList());
			System.out.println(sorteio.sorteia());
			in = scanner.nextLine();
			System.out.println("IN foi:" + in);
			if(in.equals("bingo")){
				if(Verifica.verificaBingo(sorteio, ticket)){
					System.out.println("Parabens, mané.");
					break;
				}else{
					System.out.println("MENTIRA!");
				}
			}else if(in.contains("coluna")){
				if(Verifica.verificaColuna(in, sorteio, ticket)){
					System.out.println("Parabens, mané.");
					break;
				}else{
					System.out.println("MENTIRA!");
				}
			}
		}
	}
	
	
}
