

import java.util.*;
import java.util.List;
import java.io.*;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.*;
import static java.lang.Thread.sleep;

import javax.swing.text.BadLocationException;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SRT extends Thread{

    
    public SRT() 
    {

          CreadorSRT=new Thread(this);
  
         auto=new AutomaticoSRT();
         auto.start();  
        
    }

//////////////////////////////////////////////////////////////////

public void run()
{
 
 int aux_status;

 
 
 while(true)
 {
     
    if(Ventana.entrar==true)
      {   
       Ventana.entrar=false;
       
       
       Collections.sort(indice);
     
       for(int i=0;i<indice.size();i++)
       {
         aux_status=ListaProcesos.get(indice.get(i).numProceso-1).status;
         
        if(aux_status==1)
        {
         ListaProcesos.get(indice.get(i).numProceso-1).start();
         i=indice.size();
         
        
        }   
       }
            Ventana.entrar=true;
         
        //mayor=false;

        try
          {
            sleep(6000); 
          }
        catch (InterruptedException e)
          {
            
          }	
       }
  
 }
 

}

//////////////////////////////////////////////////////////////
public static void crea()
{
 ProcesoSRT aux; 
 int prio;

 Random tiempo_random=new Random();
 int tiempo=1 + tiempo_random.nextInt(15);
 
 if(mayor==true)
 {
  Random prio_random=new Random();
  prio=(ListaProcesos.get(0).prioridad) + prio_random.nextInt(15); 
 }
 else
 {
  Random prio_random=new Random();
  prio=1 + prio_random.nextInt(15); 
 }
 
  contador++;
  
  if(contador==1)
  {
      ListaProcesos.add((new ProcesoSRT(contador,3,1)));
      indice.add((new ReferenciaSRT(contador,1)));
  }
  else
  {
      ListaProcesos.add((new ProcesoSRT(contador,tiempo,prio)));
      indice.add((new ReferenciaSRT(contador,prio)));
  }
  
  
 ListaProcesos.get(contador-1).status=1;
  ListaProcesos.get(contador-1).imprimeListo();    
    
}

//////////////////////////////////////////////
static public void imprimeReanudado(int posicion) 
{
String pos=Integer.toString(posicion);
String vieja,nueva,aux,aux2;

if(posicion<=9)
	  pos="0"+Integer.toString(posicion);

vieja="Proceso "+pos+"\t"+"Listo";
nueva="Proceso "+pos+"\t"+"En ejecucion";


aux=Ventana.textoSRT.getText();
aux2=aux.replace(vieja, nueva);

if(aux.equals(aux2))
	{
	 vieja="Proceso "+pos+"\t"+"Bloqueado";
	 aux2=aux.replace(vieja, nueva);
	}
Ventana.textoSRT.setText(aux2);


ListaProcesos.get(posicion-1).status=2;
ListaProcesos.get(posicion-1).resume();


}

////////////////////////////////////////////////
static public void imprimeBloqueado(int posicion) 
{
String pos=Integer.toString(posicion);
String vieja,nueva,aux;;

if(posicion<=9)
	  pos="0"+Integer.toString(posicion);

vieja="Proceso "+pos+"\t"+"En ejecucion";
nueva="Proceso "+pos+"\t"+"Bloqueado";

aux=Ventana.textoSRT.getText().replace(vieja, nueva);

Ventana.textoSRT.setText(aux);

ListaProcesos.get(posicion-1).status=3;
ListaProcesos.get(posicion-1).suspend();




}

////////////////////////////////////////////////
static public void imprimeEliminado(int posicion) 
{
 
String pos=Integer.toString(posicion);
String vieja=" ",nueva,aux,aux2;

if(posicion<=9)
	  pos="0"+Integer.toString(posicion);

    System.out.println("status de proceso: "+ListaProcesos.get(posicion-1).status);
    
if(ListaProcesos.get(posicion-1).status==2)
    vieja="Proceso "+pos+"\t"+"En ejecucion";
else if(ListaProcesos.get(posicion-1).status==1)
         vieja="Proceso "+pos+"\t"+"Listo y en espera"+"\t"+(ListaProcesos.get(posicion-1).prioridad);
     else if(ListaProcesos.get(posicion-1).status==3)
           vieja="Proceso "+pos+"\t"+"Bloqueado"+"\t"+(ListaProcesos.get(posicion-1).prioridad);



nueva="Proceso "+pos+"\t"+"Eliminado";

aux=Ventana.textoSRT.getText();
aux2=aux.replaceFirst(vieja, nueva);

if(aux.equals(aux2))
	{
	 vieja="Proceso "+pos+"\t"+"Bloqueado";
	 aux2=aux.replaceFirst(vieja, nueva);
	}


Ventana.textoSRT.setText(aux2);

ListaProcesos.get(posicion-1).status=4;
ListaProcesos.get(posicion-1).stop();
}
                    
////////////////////////////////////
  static public void suspender()
    {
        
     int limite=contador;
        
        for(int i=0;i<limite;i++)
            {
                int aux =ListaProcesos.get(i).status;
                
                if(aux==2)
                    imprimeBloqueado(i+1);
                  
            }
        
        
    }
    
  static public void reanudar()
    {
        int limite=contador;
        
        for(int i=0;i<limite;i++)
            {
                
                 int aux =ListaProcesos.get(i).status;
                 
                if(aux==3)
                    imprimeReanudado(i+1);
                
                try {
                    sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SRT.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            }
        
         
    }



    
 static ArrayList<ProcesoSRT> ListaProcesos=new ArrayList<ProcesoSRT>();
 static ArrayList<ReferenciaSRT> indice=new ArrayList<ReferenciaSRT>();
 
 int contAleatorio=0;
 static Thread CreadorSRT;
 
 static Thread auto;
 
 static int contador=0;
 static int contador_pos=0;

 public static boolean mayor=false;
 public static boolean entrar=true;

}

 class AutomaticoSRT extends Thread
{
	static int limite=0;
	
	public void run()
	{
		
            while(true)   
            {
                if(Ventana.entrar==true)
		{
			if(SRT.contador==0)
                            {   
                             Ventana.entrar=false;


                             SRT.crea();
                             SRT.crea();

                             Ventana.entrar=true; 
                             SRT.CreadorSRT.start();


                            }
                        else
                            {
                             Ventana.entrar=false;  


                             SRT.crea(); 

                             Ventana.entrar=true; 


                            }
	
                        
		}
                try 
                        {
		         this.sleep(5000);
			} 
                        catch (InterruptedException e) 
                        {
                         e.printStackTrace();
			}
                
                
                
                
                
                
            
            }
		//while(limite++ < 10)

	}
}






/*
 STATUS

1 IISTO
2 EJECUTANDO
3 BLOQUEADO
4 ELIMINADO/TERMINADO

*/

 class ProcesoSRT extends Thread implements Comparable<ProcesoSRT>
{
//Thread hilo;
 
 private int numero;
 int duracion;
 
 int prioridad;
 
 int status;
	
 public ProcesoSRT(int numero,int tiempo,int prioridad)
 {
  this.numero=numero;
  duracion=tiempo;
  this.prioridad=prioridad;
  //Thread hilo=new Thread(this);

   status=1;
  
  
 }
 
 ///////////////////////////////////////////////////////////////
 
 public  void run() 
 {
  try
	 {
	  imprimeEjecucion();
	 }
  catch (InterruptedException e)
	 {
	  e.printStackTrace();
	 } 	

 }
 
 ///////////////////////////////////////////////////////////////
 
 public synchronized void imprimeEjecucion() throws InterruptedException 
 {
     
     
 String pos=Integer.toString(numero);
 String vieja,nueva,aux="",aux2="";
 int dura=duracion;

 if(numero<=9)
 	  pos="0"+Integer.toString(numero);

 
	  vieja="Proceso "+pos+"\t"+"Listo y en espera"+"\t"+prioridad;
	  nueva="Proceso "+pos+"\t"+"En ejecucion"+"\t"+dura+"\t"+prioridad;
          
          status=2;
	 
	  while(dura!=-1)
		 {
		 aux=Ventana.textoSRT.getText();
		 aux2=aux.replaceFirst(vieja, nueva);
		 
		 Ventana.textoSRT.setText(aux2);
		  
		  
		  vieja=nueva;
		  dura--;
		  nueva="Proceso "+pos+"\t"+"En ejecucion"+"\t"+dura+"\t"+prioridad;
		  sleep(1000);
		  
		  if(dura <= 2)
			  Ventana.mayor=true;
		 }
	 
	  if(dura==-1)
		 {
		  vieja="Proceso "+pos+"\t"+"En ejecucion"+"\t"+(dura+1)+"\t"+prioridad;
		  nueva="Proceso "+pos+"\t"+"      Finalizado"+"\t\t"+prioridad;
		  
		  aux=Ventana.textoSRT.getText();
		  aux2=aux.replaceFirst(vieja, nueva);
		  Ventana.textoSRT.setText(aux2);
                  
                  status=4;
		 }

 }
 
 /////////////////////////////////////////////////////////////////
 
 public  void imprimeListo()
 {
  String pos=Integer.toString(numero);
  String status="Listo y en espera";

  if(numero<=9)
 	 {
 	  pos="0"+Integer.toString(numero);
 	 }
  
  if(numero==1)
	  Ventana.textoSRT.append("Proceso "+pos+"\t"+status+"\t"+prioridad);
  else
	  Ventana.textoSRT.append("\n"+"Proceso "+pos+"\t"+status+"\t"+prioridad);
 	  
 }



public int compareTo(ProcesoSRT o) {
int a=this.prioridad;
int b=o.prioridad;
int respuesta=0;


if(a<b)
	respuesta= -1;
else if(a>b)
	respuesta= 1;
	  else if(a==b)
		  respuesta= -1;

return respuesta;
}
 
 /////////////////////////////////////////////////////////////////
 /*
 public void imprimeBloqueado()
 {
  String pos=Integer.toString(numero);
  String status="Bloqueado";

  if(numero<=9)
 	 {
 	  pos="0"+Integer.toString(numero);
 	 }
  
  if(numero==1)
	  Interfaz.texto.append("Proceso "+pos+"\t"+status);
  else
	  Interfaz.texto.append("\n"+"Proceso "+pos+"\t"+status);
 	  
 }
 */
}




class ReferenciaSRT implements Comparable<ReferenciaSRT>
{
  int numProceso;
  int numPrioridad;
   
  public ReferenciaSRT(int n,int p)
  {
     numProceso=n;
     numPrioridad=p;
  }

    
  public int compareTo(ReferenciaSRT o) 
  {
    int a=this.numPrioridad;
    int b=o.numPrioridad;
    int respuesta=0;


    if(a<b)
	respuesta= -1;
    else if(a>b)
            respuesta= 1;
	  else if(a==b)
		  respuesta= -1;

    return respuesta;
  }
    
}