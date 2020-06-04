//Proyecto final EDD 
//Calculadora graficadora de funciones
//Juan Diego Valenzuela - A00825349

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Ecuacion {
	public static Number calcular(String ecuacion, int x) {
		ecuacion = ecuacion.replaceAll("x", ""+x);
		//System.out.println(ecuacion);
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine js = mgr.getEngineByName("JavaScript");
		
		Number y = null;
		try {
			y = (Number) js.eval(ecuacion);
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(y == null) {
			return 0;
		}else {
			return y;
		}
	}
}
