package classes;

public class Mensagens 
{
	protected String Mensagem;
	
	public Mensagens(String msg)
	{
		this.setMensagem(msg);
	}
	public Mensagens() {}
	public Mensagens(Mensagens modelo) throws Exception
	{
		if(modelo == null)
			throw new Exception("Modelo nulo");
		
		this.Mensagem = modelo.Mensagem;
	}
	
	public String getMensagem()
	{
		return this.Mensagem;
	}
	
	public void setMensagem(String msg)
	{
		this.Mensagem = msg;
	}
	
}
