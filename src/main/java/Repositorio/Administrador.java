package Repositorio;

	import java.util.*;
	import java.text.*;
import java.time.LocalDateTime;

public class Administrador {
	
	   static Timer timerProceso1 = new Timer();
	   static Timer timerProceso2 = new Timer();
	   static Timer timerProceso3 = new Timer();
	 
	   private static class Proceso1 extends TimerTask {
	      public void run() {
	         System.out.println("Running Task");
	    
	         timerProceso1.cancel();
	      }
	   }
	 
	   private static class Proceso2 extends TimerTask {
		      public void run() {
		         System.out.println("Running Task");
		
		         timerProceso2.cancel();
		      }
		   }
		 
	   private static class Proceso3 extends TimerTask {
		      public void run() {
		         System.out.println("Running Task");
		         timerProceso3.cancel();
		      }
		   }
		 
	   public static void main(String[] args) throws ParseException {
	 
	      System.out.println("Current Time: " + df.format( new Date()));
	 
	      //Date and time at which you want to execute
	      LocalDateTime  horarioProceso1;
	      LocalDateTime  horarioProceso2;
	      LocalDateTime  horarioProceso3;
	 
	      timer.schedule(new MyTimeTask(), date);
	   }
	}
