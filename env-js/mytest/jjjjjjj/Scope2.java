package jjjjjjj;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import jdk.nashorn.api.scripting.NashornScriptEngine;

public class Scope2 {
	public static void main(String[] args) {
		ScriptEngineManager sem = new ScriptEngineManager();
		System.out.println(java.lang.System.getProperty("user.dir"));

		NashornScriptEngine se = (NashornScriptEngine) sem.getEngineByName("js");

		NashornScriptEngine se1 = (NashornScriptEngine) sem.getEngineByName("js");
		try {
			
			Bindings b=se.getBindings(ScriptContext.GLOBAL_SCOPE);
			se.eval("var func='fujinlong'", b);
			 System.out.println(b.get("func"));
			 System.out.println(se.get("func"));
			se.setBindings(b, ScriptContext.GLOBAL_SCOPE);
			se.eval("print(func)");
			se.eval("var func='fulang'");
			se.eval("print(func)");
			
			
			
			
			
			
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
