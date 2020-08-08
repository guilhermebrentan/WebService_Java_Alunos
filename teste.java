public class teste{
	public static void main(String[] args){
		try{
			System.out.println("LinkedList:");
			LinkedList<Integer> testando = new LinkedList<Integer>();
			testando.addLast(1);
			testando.addLast(2);
			testando.addLast(3);
			testando.addLast(4);
		
			System.out.println("toString(): "+testando.toString());
			System.out.println("hashCode(): "+testando.hashCode());
			System.out.println("equals(clone): "+testando.equals(new LinkedList<Integer>(testando)));
			System.out.println("equals(new obj): "+testando.equals(new LinkedList<Integer>()));
			try{
                                for(;;){
                                        System.out.println(testando.getFirst()+"");
                                        testando.removeFirst();
                                }
                        }
                        catch(Exception e){}

			System.out.println("\nFila:");
			Fila<Integer> testa = new Fila<Integer>();
			testa.add(1);
			testa.add(2);
			testa.add(3);
			testa.add(4);
		
			System.out.println("toString(): "+testa.toString());
			System.out.println("hashCode(): "+testa.hashCode());
			System.out.println("equals(clone): "+testa.equals(new Fila<Integer>(testa)));
			System.out.println("equals(new obj): "+testando.equals(new Fila<Integer>()));
			try{
				for(;;){
					System.out.println(testa.get()+"");
					testa.next();
				}
			}
			catch(Exception e){}

			System.out.println("\nMatricula");
			Matricula ma = new Matricula((short)123, 123, (float)10, (float)1);
			ma.setRa((short)19187);
			ma.setCod(104);
			ma.setNota((float)9);
			ma.setFreq((float)0.8);
			System.out.println("Ra :"+ma.getRa());
			System.out.println("Cod: "+ma.getCod());
			System.out.println("Nota: "+ma.getNota());
			System.out.println("Freq: "+ma.getFreq());

			System.out.println("toString(): "+ma.toString());
                        System.out.println("hashCode(): "+ma.hashCode());
                        System.out.println("equals(clone): "+ma.equals(new Matricula(ma)));
                        System.out.println("equals(new obj): "+ma.equals(null));

		}
		catch(Exception e){}
	}
}
