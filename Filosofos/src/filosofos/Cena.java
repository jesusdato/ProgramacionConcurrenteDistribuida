package filosofos;

public class Cena{  
Tenedor tenedores[];  
 	int parametro = 5;  //número de filósofos y tenedores

public static void main(String args[]){  
 		System.out.println("Cena de los Filosofos");  
 		Cena cena = new Cena();  
 		for(int i=0; i<5; i++){
//Creamos una cena con 5 filósofos (hilos)
//Un filósofo siempre pertenece a una cena 
  			Filosofo pensante = new Filosofo(i, cena);  
 }  
}  
public Cena(){
//Creamos un array de tenedores  
 tenedores = new Tenedor[parametro];  
 		for(int i=0; i<5; i++){
		//Cada tenedor va a tener un estado (enUso= true o enUso = false)
  			tenedores[i] = new Tenedor(i);  
 }  
}  

public Tenedor getTenedor(int x){  
 return tenedores[x];  
 	} 
	//Consultar el tenedor de la derecha de un filósofo concreto
public int getTenedorDer(int x){  
 return (x+1)%parametro;  
 }
//Consultar el tenedor de la izquierda de un filósofo concreto
public int getTenedorIzq(int x){  
 return x;  
 }  
}

