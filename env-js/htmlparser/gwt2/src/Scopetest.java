import javax.script.ScriptContext;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import jdk.nashorn.api.scripting.NashornScriptEngine;

public class Scopetest {
	public static void main(String[] args) throws ScriptException {
		ScriptEngineManager sem = new ScriptEngineManager();
		//System.out.println(java.lang.System.getProperty("user.dir"));

		NashornScriptEngine se =  (NashornScriptEngine)sem.getEngineByName("js");
		ScriptContext ctx=new javax.script.SimpleScriptContext();
		Object func=se.eval("(function(o){print(o==this);this.load('nashorn:mozilla_compat.js');})",ctx);
		//se.eval("var a={}",ctx);
		//se.eval("print(a.__defineGetter__)",ctx);
		//se.eval("print(Object.__defineGetter__)");
		
		
		
		
		NashornScriptEngine se1 =  (NashornScriptEngine)sem.getEngineByName("js");
		se1.put("func",func);
		se1.eval("print(this);print(func)");
		se1.eval("func.call(this,this)");
		//se1.eval("func.call(this)");
		
		
		
		//se1.setContext(ctx);
		//se1.eval("load('nashorn:mozilla_compat.js');", ctx);
		
		
		
		se1.eval("print(Object.__defineGetter__)");
		
		//se.eval("print(Object.__defineGetter__)",ctx);
		
		
	}
}
