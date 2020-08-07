public class Fila<X>{
    private LinkedList<X> list = null;

    //Construtor
    public Fila(){list = new LinkedList<X>();}

    //Metodos de lista
    public X get() throws Exception{return list.getFirst();}
    public void next() throws Exception{list.removeFirst();}
    public void add(X data) throws Exception{list.addLast(data);}
}
