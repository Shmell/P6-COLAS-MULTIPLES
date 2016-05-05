



public class Automatico extends Thread
{
	static int limite=0;
	
	
	
	
	
	
	public void run()
	{int bandera=0;
        
		while(true)
            //while(bandera!=1)   
            {
                if(Ventana.entrar==true)
		{
			if(Ventana.contadorSRT==0 && Ventana.contadorRR==0 && Ventana.contadorFCFS==0)
	  	   	 {   
                          Ventana.entrar=false;
                          
                         
                    
                          Ventana.crea();
                          
                          Ventana.entrar=true; 
			  Ventana.Creador.start();
                          
                          
	  	   	 }
                        else
                         {
                          Ventana.entrar=false;  
                         
                          
                          Ventana.crea(); 
                          
                          Ventana.entrar=true; 
                        
                           
                         }
                            
                           
			try 
                        {
		         this.sleep(4000);
			} 
                        catch (InterruptedException e) 
                        {
                         e.printStackTrace();
			}
                        
		}
            
            
            
            
            
            
            
            //bandera++;
            }
            
            
		//while(limite++ < 10)
		
		
	 
		
		
		
		
	}
        


}