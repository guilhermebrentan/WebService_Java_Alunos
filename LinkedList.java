import java.lang.reflect.*;

public class LinkedList<X> {
    private class Node{
	private X data;
	private Node next;

	//Getters
	public X getData(){return this.data;}
	public Node getNext(){return this.next;}

	//Setters
	public void setData(X data){this.data = data;}
	public void setNext(Node next){this.next = next;}

	//Construtor
	public Node(X data, Node next){
	    this.data = data;
	    this.next = next;
	}
	public Node(X data){
	    this.data = data;
	    this.next = null;
	}
    }

    //Clona X ou retorna o mesmo valor, caso nao seja clonavel
    private X cloneX(X x){
	if(x instanceof Cloneable){
	    X ret=null;
	    try{
		Class<?> classe = x.getClass();
		Class<?>[] tiposDosParms = null;
		Method metodo = classe.getMethod("clone",tiposDosParms);
		Object[] parms = null;
		ret = (X)metodo.invoke(x,parms);
	    }
	    catch (Exception erro){}
	    
	    return ret;
	}
	
	return x;
    }

    private Node first, last;

    public LinkedList(){
	this.first = null;
	this.last =null;
    }

    //Obrigatorios para o trabalho
    public void addLast(X data) throws Exception{
	if(data == null)
	    throw new Exception("Valor a inserir nulo");

	X aux = cloneX(data);

	if(this.last == null){
	    this.last = new Node(aux);
	    this.first = this.last;
	}
	else{
	    this.last.setNext(new Node(aux));
	    this.last = this.last.getNext();
	}
    }
    public X getFirst() throws Exception{
	if(this.first == null)
	    throw new Exception("Sem dados guardados");
	
	return cloneX(this.first.getData());
    }
    public void removeFirst() throws Exception{
	if(this.first == null)
	    throw new Exception("Sem dados guardados");

	if(this.first == this.last){
	    this.first = null;
	    this.last = null;
	}
	else{
	    this.first = this.first.getNext();
	}
    }

    //Metodos obrigatorios
    public String toString(){
	String ret = "";
	try{
	    for(Node node = this.first; node != null; node = node.getNext()){
		ret += node.getData().toString()+". ";
	    }
	}
	catch(Exception e){}
	return ret;
    }
    public boolean equals (Object obj){
        if(this == obj)
            return true;
	if(obj == null)
	    return false;
	if(this.getClass() != obj.getClass())
            return false;
	
        LinkedList<X> list = (LinkedList<X>)obj;

        Node contThis = this.first;
        Node contList = list.first;

	for(; contThis != null && contList != null; contThis = contThis.getNext(), contList.getNext())
            if(!contThis.getData().equals(contList.getData()))
                return false;

        if (contThis!=null || contList != null)
            return false;

        return true;
    }
    public int hashCode (){   
        int ret = 55;

        for(Node cont = this.first; cont != null; cont = cont.getNext())
             ret = 17*ret + cont.getData().hashCode();

        if(ret<0) ret = -ret;

        return ret;
    }
    public LinkedList (LinkedList<X> model) throws Exception{
        if(model == null)
            throw new Exception ("Model nulo");

        if(model.first == null)
            return;

        this.first = new Node (model.first.getData());

	Node contThis = this.first;
	Node contModel = model.first.getNext();
	
        for(; contModel != null; contThis = contThis.getNext(), contModel = contModel.getNext()){
            contThis.setNext(new Node(contModel.getData()));
	}

        this.last = contThis;
    }
    public Object clone(){
        LinkedList<X> ret=null;

        try{
            ret = new LinkedList(this);
        }
        catch (Exception erro)
        {}

	return ret;
    }
}
