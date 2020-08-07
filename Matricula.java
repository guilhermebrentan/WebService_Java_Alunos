public class Matricula{
    short ra;
    int cod;
    float nota, freq;

    //Construtor
    public Matricula(short ra, int cod, float nota, float freq) throws Exception{
	this.setRa(ra);
	this.setCod(cod);
	this.setNota(nota);
	this.setFreq(freq);
    }
    
    //Getters
    public short getRa(){return ra;}
    public int gerCod(){return cod;}
    public float getNota(){return nota;}
    public float getFreq(){return freq;}

    //Setters
    public void setRa(short ra) throws Exception{
	if(ra < 0)
	    throw new Exception("Ra negativo");
	this.ra = ra;
    }
    public void setCod(int cod) throws Exception{
	if(cod < 0)
	    throw new Exception("Cod negativo");
	this.cod = cod;
    }
    public void setFreq(float freq) throws Exception{
	if(freq > 1 || freq < 0)
	    throw new Exception("Freq invalida");
	this.freq = freq;
    }
    public void setNota(float nota) throws Exception{
	if(nota > 10 || nota < 0)
	    throw new Exception("Nota invalida");
	this.nota = nota;
    }

    //Metodos obrigatorios
    public String toString(){
	return "Ra: "+ra+", cod: "+cod+", nota: "+nota+", freq: "+freq;
    }
    public boolean equals (Object obj)
    {
        if (this==obj)
            return true;
        if (obj==null)
            return false;
        if (this.getClass()!=obj.getClass())
            return false;

	Matricula m = (Matricula) obj;
	
	if(this.ra != m.ra || this.cod != m.cod || this.nota != m.nota || this.freq != m.nota)
	    return false;

        return true;
    }
    public int hashCode ()
    {
        int ret=55;

        ret = ret*7 + new Short(ra).hashCode();
	ret = ret*7 + new Integer(cod).hashCode();
	ret = ret*7 + new Float(nota).hashCode();
	ret = ret*7 + new Float(freq).hashCode();

        if (ret<0) ret = -ret;

        return ret;
    }
    public Matricula (Matricula model) throws Exception{
        if(model == null)
            throw new Exception ("Model nulo");

        this.ra = model.ra;
	this.cod = model.cod;
	this.nota = model.nota;
	this.freq = model.freq;
    }
    public Object clone(){
        Matricula ret=null;

        try{
            ret = new Matricula(this);
        }
        catch (Exception erro){}

        return ret;
    }
}
