package filosofos;

public class Filosofo implements Runnable{  
	  
	   private Thread hilo;  
	   protected Cena cena;  
	   protected int tizq, tder;  
	   protected int numero;  
	  	
		//Creamos un filosofo y lo lanzamos a ejecución (hilo)
	 public Filosofo (int x, Cena cena){  
	     		this.numero= x;  
	     		this.cena= cena;  
	    		tizq= cena.getTenedorIzq(numero);  
	      		tder= cena.getTenedorDer(numero);  
	      		hilo = new Thread(this);  
	      		hilo.start();  
	   	}  
		
		//Ponemos el hilo asociado a un filósofo en el estado de espera (hilo principal dormido)
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

	//Ponemos el hilo asociado a un filósofo en el estado de ejecución
	//Le asociamos dos tenedores (derecha e izquierda)
	public void tomarTenedores(){  
	     		System.out.println ("Tomando tenedores");  
	     		Tenedor t1= cena.getTenedor(tizq);  
	    	 	Tenedor t2= cena.getTenedor(tder);  
	     		t1.usar();  
	     		t2.usar();  
	} 






	//Hilo principal asociado a un filósofo pasa a estado de espera (comiendo)
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
	Todos los programadores están familiarizados con los programas secuenciales. Todos ellos tienen un principio, una secuencia de ejecución y un final. Por ejemplo, una aplicación comienza en la función main, ejecuta las sentencias del cuerpo de dicha función en orden consecutivo y el programa acaba cuando se llega al final de dicha función.
	Un subproceso es similar a un programa secuencial, tiene un principio, una secuencia y un final. La diferencia fundamental estriba en que un subproceso no es un programa que pueda correr aislado, sino que se ejecuta dentro de un programa.
	El método run es el corazón del subproceso, es donde tiene lugar la acción del subproceso. Hay dos modos de proporcionar el el método run a un subproceso:
	Derivando una clase de Thread y redefiniendo el método run
	Implementando el interface Runnable y definiendo la función run de dicho interface.
	La razón de que existan estas dos posibilidades es que en Java no existe la herencia múltiple. Si una clase deriva de otra no podemos hacer que derive también de Thread. Por ejemplo, un applet deriva de la clase base Applet por tanto, ha de implementar el interface Runnable para que pueda definir el método run. En el estudio de la animación veremos esta aproximación.
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

