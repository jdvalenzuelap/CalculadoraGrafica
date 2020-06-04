

//Proyecto final EDD 
//Calculadora graficadora de funciones
//Juan Diego Valenzuela - A00825349

public class InfixEvaluation {

   public static Double evaluar(String ecuacion, int x) {
	   ecuacion = ecuacion.replaceAll("x", ""+x);
	   //Stack de operadores y operandos
       MyStack<Integer> op  = new MyStack<>();
       MyStack<Double> val  = new MyStack<>();
       //Stacks temporales apra funcionamiento del algoritmo
       MyStack<Integer> optmp  = new MyStack<>();
       MyStack<Double> valtmp  = new MyStack<>();
       ecuacion = "0" + ecuacion;
       ecuacion = ecuacion.replaceAll("-", "+-");
       
       String temp = "";
       for (int i = 0;i < ecuacion.length();i++){
           char ch = ecuacion.charAt(i);
           if (ch == '-')
               temp = "-" + temp;
           else if (ch != '+' &&  ch != '*' && ch != '/')
              temp = temp + ch;
           else
           {
               val.push(Double.parseDouble(temp));
               op.push((int)ch);
               temp = "";
           }
       }
       val.push(Double.parseDouble(temp));
       
       char operators[] = {'/','*','+'};
   
       for (int i = 0; i < 3; i++){
           boolean it = false;
           while (!op.isEmpty())
           {
               int optr = op.pop();
               double v1 = val.pop();
               double v2 = val.pop();
               if (optr == operators[i])
               {
                  
                   if (i == 0)
                   {
                       valtmp.push(v2 / v1);
                       it = true;
                       break;
                   }
                   else if (i == 1)
                   {
                       valtmp.push(v2 * v1);
                       it = true;
                       break;
                   }
                   else if (i == 2)
                   {
                       valtmp.push(v2 + v1);
                       it = true;
                       break;
                   }                                        
               }
               else
               {
                   valtmp.push(v1);
                   val.push(v2);
                   optmp.push(optr);
               }                
           }    
                    
           while (!valtmp.isEmpty())
               val.push(valtmp.pop());
           while (!optmp.isEmpty())
               op.push(optmp.pop());
          
           if (it)
               i--;                            
       }
       return val.pop();
   }
}