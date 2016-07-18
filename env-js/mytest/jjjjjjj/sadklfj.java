package jjjjjjj;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;

import jdk.nashorn.api.scripting.NashornScriptEngine;

public class sadklfj {
	public static void main(String[] args) {
		ScriptEngineManager sem = new ScriptEngineManager();
		System.out.println(java.lang.System.getProperty("user.dir"));

		NashornScriptEngine se = (NashornScriptEngine) sem.getEngineByName("js");

		NashornScriptEngine se1 = (NashornScriptEngine) sem.getEngineByName("js");
		try {

			 /* ScriptContext defCtx = engine.getContext();
			   defCtx.getBindings(ScriptContext.GLOBAL_SCOPE).put("foo", "hello");
			  
			   ScriptContext myCtx = new SimpleScriptContext(); 
			   myCtx.setBindings(defCtx.getBindings(ScriptContext.ENGINE_SCOPE), ScriptContext.ENGINE_SCOPE); 
			   Bindings b = new SimpleBindings(); b.put("foo", "world");
			   myCtx.setBindings(b, ScriptContext.GLOBAL_SCOPE);
			   
			   
			   
			   engine.eval("print(foo)"); // prints 'hello'
			   engine.eval("print(foo)", myCtx); // prints "world"
			   engine.eval("print(foo)", defCtx); // prints "hello"
*/			
		
			
			ScriptContext sc = new SimpleScriptContext();
			se.eval("load('nashorn:mozilla_compat.js');", sc);
			se.eval("var name='fujinlong'",sc);
			se.eval("var f1=function(){}",sc);
			se.eval("var w=function(s,f2){s.__defineGetter__('name',f2);};", sc);
			sc.setAttribute("w", se.eval("w",sc), ScriptContext.ENGINE_SCOPE);
			Object o=sc.getAttribute("Object", ScriptContext.ENGINE_SCOPE);
			se.getBindings(ScriptContext.GLOBAL_SCOPE).put("w", sc.getAttribute("w", ScriptContext.ENGINE_SCOPE));
			//se.eval("print(jdk.nashorn.internal.runtime.ScriptFunction)",sc);
			
			ScriptContext sc1 = new SimpleScriptContext();
			se1.eval("load('nashorn:mozilla_compat.js');", sc1);
			sc1.setAttribute("w", sc.getAttribute("w", ScriptContext.ENGINE_SCOPE), ScriptContext.ENGINE_SCOPE);
			se1.eval("print(w)",sc1);
			se1.eval("var name='fujinlong'",sc1);
			//se1.eval("print(jdk.nashorn.internal.runtime.ScriptFunction)",sc1);
			se1.eval("var f2=function(){}",sc1);
			se1.eval("new w({},f2)",sc1);
			
			
			
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
