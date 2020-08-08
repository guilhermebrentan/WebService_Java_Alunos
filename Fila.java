public class Fila<X>{
	private LinkedList<X> list = null;

	//Construtor
	public Fila(){list = new LinkedList<X>();}

	//Metodos de lista
	public X get() throws Exception{return list.getFirst();}
	public void next() throws Exception{list.removeFirst();}
	public void add(X data) throws Exception{list.addLast(data);}

	//Obrigatorios
	public String toString(){return list.toString();}
	public int hashCode(){return (56 * 3 + list.hashCode());}
	//Nao deixei sem nada só pra ficar no padrao do professor Andre
	public boolean equals(Object obj){
        	if(this == obj)
        	    return true;
	        if(obj == null)
	            return false;
	        if(this.getClass() != obj.getClass())
	            return false;

	        Fila<X> fila = (Fila<X>)obj;

		if(!fila.list.equals(this.list))
			return false;

	        return true;
	}
	public Fila(Fila model)throws Exception{this.list = (LinkedList<X>)model.list.clone();}
	public Object clone(){
		Fila<X> ret=null;

        	try{
        	    ret = new Fila(this);
        	}
        	catch (Exception erro)
        	{}

        	return ret;
	}
}
