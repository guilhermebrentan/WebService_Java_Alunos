package classes;

import java.util.Scanner;


public class Solicitadora 
{
	private Scanner ler;
	private Fila<Matricula> lista;
	
	public void solicitaAcao()
	{
		ler = new Scanner(System.in);
		String opcao;
		
		for(;;)
		{
			System.out.println("-----------------------------------------------------------");
			System.out.println("Digite o número referente a ação que deverá ser executada: ");
			System.out.println("1 - Cadastrar resultados de um aluno;");
			System.out.println("2 - Finalizar programa;");
			
			opcao = ler.nextLine();
			
			switch(opcao)
			{
				default: System.out.println("Opção inválida, tente novamente."); break;
				case "1": 
				{
					this.solicitaDados();
				} break;
				case "2": 
				{
					ler.close();
					System.exit(0);
				}
			}
		}
	}
	
	private void solicitaDados() 
	{
		Matricula mat = new Matricula();
		lista = new Fila<Matricula>();
		for (;;) 
		{
			//solicita o ra até ele ser validado
			for (;;) 
			{
				try 
				{
					mat.setRa((short) this.solicitaRA());//tenta adicionar na matricula o valor do ra, se for invalido retorna null e se for null da exception

					break; //se der certo sai do for e vai pro proximo
				} 
				catch (Exception e) 
				{
					System.out.println(e.getMessage());
				}

			}
			for (;;) 
			{
				try 
				{
					mat.setCod(this.solicitaCodigoDisciplina());//tenta adicionar na matricula o valor do ra, se for invalido retorna null e se for null da exception
					break; //se der certo sai do for e vai pro proximo
				} 
				catch (Exception e) 
				{
					System.out.println(e.getMessage());
				}

			}
			for (;;) 
			{
				try 
				{
					mat.setNota(this.solicitaNota());//tenta adicionar na matricula o valor do ra, se for invalido retorna null e se for null da exception
					break; //se der certo sai do for e vai pro proximo
				} 
				catch (Exception e) 
				{
					System.out.println(e.getMessage());
				}

			}
			for (;;) 
			{
				try 
				{
					mat.setFreq(this.solicitaFrequencia());//tenta adicionar na matricula o valor do ra, se for invalido retorna null e se for null da exception
					break; //se der certo sai do for e vai pro proximo
				} 
				catch (Exception e) 
				{
					System.out.println(e.getMessage());
				}

			}
			
			try 
			{
				lista.add(mat);
			} 
			catch (Exception e) 
			{
				System.out.println("Erro ao adicionar matricula, tente novamente.");
				System.exit(0);
			}
			
			esse:for (;;) 
			{
				String quer;
				System.out.println("-----------------------------------------------------------");
				System.out.println("Deseja cadastra mais um resultado?");
				System.out.println("1 - Sim");
				System.out.println("2 - Nao");
				quer = ler.nextLine();
				switch (quer) 
				{
				default: System.out.println("Opção inválida, tente novamente.");break; 
				case "1": break esse;
				case "2": 
					{
						Matricula aux;
						Mensagens msg = new Mensagens();
						String mensagens = "";
						
						try //verifica se ainda tem coisas na fila
						{
							for(int i = 1;;i++)//loop infinito
							{
								aux = new Matricula(lista.get());
								
								try
								{
									msg = (Mensagens) ClienteWS.getObjeto(Mensagens.class, "http://localhost:3000/executar", String.valueOf(aux.getRa()), String.valueOf(aux.gerCod()), String.valueOf(aux.getNota()), String.valueOf(aux.getFreq()));
									mensagens += msg.getMensagem();
								}
								catch(Exception ex)
								{
									mensagens += "Erro ao adicionar o "+i+"º resultado cadastrado no banco de dados";
								}
								lista.next();
							}
						} 
						catch (Exception e)//quando acabar as matriclas, 
						{
							System.out.println(mensagens);
						}
						return;
					}
				}
			}			
		}
	}

	public int solicitaRA() throws Exception
	{
		String ra;
		int ret;
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("Digite o RA: ");
		
		ra = ler.nextLine();
		
		try 
		{
			if(ra.length() != 5)		//se tem 5 digitos
				throw new Exception();
			
			ret = Integer.parseInt(ra); //se deu certo (ou seja, se so tem numero)
		}
		catch(Exception e) //caso o RA for inválido
		{
			throw new Exception("RA inválido");
		}
		
		System.out.println(ra);
		System.out.println("Deu certo");
		return ret;
	}
	
	public int solicitaCodigoDisciplina() throws Exception
	{
		String cod;
		int ret;
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("Digite o Codigo da Disciplina: ");
		
		cod = ler.nextLine();
		
		try 
		{
			ret = Integer.parseInt(cod); //se deu certo (ou seja, se so tem numero)
		}
		catch(Exception e) //caso o RA for inválido
		{
			throw new Exception("Codigo da Disciplina inválido");
		}
		
		System.out.println(cod);
		System.out.println("Deu certo");
		return ret;
	}
	
	public float solicitaNota() throws Exception
	{
		String nota;
		float ret;
		
		System.out.println("-----------------------------------------------------------");
		System.out.println("Digite a Nota: ");
		
		nota = ler.nextLine();
		
		try 
		{
			ret = Float.parseFloat(nota); //se deu certo (ou seja, se so tem numero)
			if(ret<0||ret>10)
				throw new Exception();
		}
		catch(Exception e) //caso o RA for inválido
		{
			throw new Exception("Nota inválido");
		}
		
		System.out.println(nota);
		System.out.println("Deu certo");
		return ret;
	}
	
	public float solicitaFrequencia() throws Exception
	{
		String freq;
		float ret;
		System.out.println("-----------------------------------------------------------");
		System.out.println("Digite a Frequencia: ");
		
		freq = ler.nextLine();
		
		try 
		{
			ret = Float.parseFloat(freq); //se deu certo (ou seja, se so tem numero)
			if(ret<0 || ret>1)
				throw new Exception();
		}
		catch(Exception e) //caso o RA for inválido
		{
			throw new Exception("Frequencia inválido");
		}
		
		System.out.println(freq);
		System.out.println("Deu certo");
		return ret;
	}	
}
