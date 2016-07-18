package jjjjjjj;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import javax.script.SimpleScriptContext;

import jdk.nashorn.api.scripting.NashornScriptEngine;

public class ScopeTest {
	public static void main(String[] args) {
		ScriptEngineManager sem = new ScriptEngineManager();
		System.out.println(java.lang.System.getProperty("user.dir"));

		NashornScriptEngine se =  (NashornScriptEngine)sem.getEngineByName("js");
		
		NashornScriptEngine se1 =  (NashornScriptEngine)sem.getEngineByName("js");
		try {
			//ScriptContext ctx=new SimpleScriptContext();
			Bindings bindings = new SimpleBindings();
			bindings.put("func", "function(){print('func');}");
			//ctx.setAttribute("uuu", "uuuu", ScriptContext.GLOBAL_SCOPE);
			se.setBindings(bindings, ScriptContext.GLOBAL_SCOPE);
			//se.setContext(ctx);
			se.getContext().setAttribute("uuuuuu", "uuuuuu", ScriptContext.GLOBAL_SCOPE);
			
			se.eval("print(func)");
			se.eval("print(uuuuuu)");
			se1.setBindings(se.getBindings(ScriptContext.GLOBAL_SCOPE), ScriptContext.ENGINE_SCOPE);
			se1.eval("print(func)");
			//se1.getContext().setAttribute("uuuuuu", "fujisadfsdfasdfafadsfasd", ScriptContext.ENGINE_SCOPE);
			se1.eval("print(uuuuuu)");
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
