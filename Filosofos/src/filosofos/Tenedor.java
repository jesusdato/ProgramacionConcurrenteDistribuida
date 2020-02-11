package filosofos;

/*
La palabra reservada synchronized se usa para indicar que ciertas partes del c�digo, (habitualmente, una funci�n miembro) est�n sincronizadas, es decir, que solamente un subproceso puede acceder a dicho m�todo a la vez. Cada m�todo sincronizado posee una especie de llave que puede cerrar o abrir la puerta de acceso
*/
public class Tenedor{  
  
int numero;  
boolean enUso;  
   
public Tenedor(int x){  
 		numero = x;  
 	}  
  
synchronized public void usar(){   
      		if(enUso){  
         			System.out.println("Tenedor "+numero+" esta en uso, espera");  
      		}else{  
         			enUso= true;  
         			System.out.println("Se esta usando el tenedor "+numero);  
      			}  
}  
  
synchronized public void dejar(){   
      		enUso = false;  
      		System.out.println("Tenedor "+numero+" esta ahora disponible");  
   	}  
}

