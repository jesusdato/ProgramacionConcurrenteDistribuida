package filosofos;

public class Filosofo implements Runnable{  
	  
	   private Thread hilo;  
	   protected Cena cena;  
	   protected int tizq, tder;  
	   protected int numero;  
	  	
		//Creamos un filosofo y lo lanzamos a ejecuci�n (hilo)
	 public Filosofo (int x, Cena cena){  
	     		this.numero= x;  
	     		this.cena= cena;  
	    		tizq= cena.getTenedorIzq(numero);  
	      		tder= cena.getTenedorDer(numero);  
	      		hilo = new Thread(this);  
	      		hilo.start();  
	   	}  
		
		//Ponemos el hilo asociado a un fil�sofo en el estado de espera (hilo principal dormido)
	public void pensar(){  
	     	try{  
	       		System.out.println ("Filosofo "+numero+" pensando");  
	        		int espera = (int)(Math.random()*1000);  
	        		hilo.sleep(espera);  
	        		System.out.println ("Filosofo "+numero+" tiene hambre");  
	     	}catch(InterruptedException e){  
	 		e.printStackTrace();  
	    	 }  
	}  

	//Ponemos el hilo asociado a un fil�sofo en el estado de ejecuci�n
	//Le asociamos dos tenedores (derecha e izquierda)
	public void tomarTenedores(){  
	     		System.out.println ("Tomando tenedores");  
	     		Tenedor t1= cena.getTenedor(tizq);  
	    	 	Tenedor t2= cena.getTenedor(tder);  
	     		t1.usar();  
	     		t2.usar();  
	} 






	//Hilo principal asociado a un fil�sofo pasa a estado de espera (comiendo)
	public void comer(){  
	     	try{  
	        		System.out.println("Filosofo "+numero+" esta comiendo");
			//Tiempo que el proceso principal va a estar en estado de espera        	
	//Mandamos a dormir el HILO PRINCIPAL (sleep no es lo mismo que wait)
	//Ambos duermen un hilo:
		//.sleep -> el hilo (proceso) principal
		//.wait -> cualquier proceso
	int espera = (int)(Math.random()*1000);
	       		hilo.sleep(espera);  
	        		System.out.println("Filosofo "+numero+" esta satisfecho");  
	     	}catch(InterruptedException e){  
	  		e.printStackTrace();  
	     		}  
	}

	//Filosofo ha terminado de comer y deja los tenedores adyacentes libres
	protected void dejarTenedores(){  
	     		System.out.println("Soltando los tenedores.");  
	     		Tenedor t1= cena.getTenedor(tizq);  //consulta tenedor izquierdo
	     		Tenedor t2= cena.getTenedor(tder);  //consulta tenedor derecho
	     		t1.dejar();  //t1 (izquierda) -> enUso = false
	    		t2.dejar();  //t2 (derecha) -> enUso = false
	}
	/*
	Todos los programadores est�n familiarizados con los programas secuenciales. Todos ellos tienen un principio, una secuencia de ejecuci�n y un final. Por ejemplo, una aplicaci�n comienza en la funci�n main, ejecuta las sentencias del cuerpo de dicha funci�n en orden consecutivo y el programa acaba cuando se llega al final de dicha funci�n.
	Un subproceso es similar a un programa secuencial, tiene un principio, una secuencia y un final. La diferencia fundamental estriba en que un subproceso no es un programa que pueda correr aislado, sino que se ejecuta dentro de un programa.
	El m�todo run es el coraz�n del subproceso, es donde tiene lugar la acci�n del subproceso. Hay dos modos de proporcionar el el m�todo run a un subproceso:
	Derivando una clase de Thread y redefiniendo el m�todo run
	Implementando el interface Runnable y definiendo la funci�n run de dicho interface.
	La raz�n de que existan estas dos posibilidades es que en Java no existe la herencia m�ltiple. Si una clase deriva de otra no podemos hacer que derive tambi�n de Thread. Por ejemplo, un applet deriva de la clase base Applet por tanto, ha de implementar el interface Runnable para que pueda definir el m�todo run. En el estudio de la animaci�n veremos esta aproximaci�n.
	*/

	public void run(){  
	     		while(true){  
	         			pensar();  
	         			tomarTenedores();  
	         			comer();  
	         			dejarTenedores();  
	     		 }  
	}  
	}

