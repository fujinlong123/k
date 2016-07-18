package jjjjjjj;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;

import jdk.nashorn.api.scripting.NashornScriptEngine;

public class BB {
	public static void main(String[] args) {
		ScriptEngineManager sem = new ScriptEngineManager();
		System.out.println(java.lang.System.getProperty("user.dir"));

		NashornScriptEngine se = (NashornScriptEngine) sem.getEngineByName("js");

		NashornScriptEngine se1 = (NashornScriptEngine) sem.getEngineByName("js");
		try {

			/*
			 * ScriptContext defCtx = engine.getContext();
			 * defCtx.getBindings(ScriptContext.GLOBAL_SCOPE).put("foo",
			 * "hello");
			 * 
			 * ScriptContext myCtx = new SimpleScriptContext();
			 * myCtx.setBindings(defCtx.getBindings(ScriptContext.ENGINE_SCOPE),
			 * ScriptContext.ENGINE_SCOPE); Bindings b = new SimpleBindings();
			 * b.put("foo", "world"); myCtx.setBindings(b,
			 * ScriptContext.GLOBAL_SCOPE);
			 * 
			 * 
			 * 
			 * engine.eval("print(foo)"); // prints 'hello'
			 * engine.eval("print(foo)", myCtx); // prints "world"
			 * engine.eval("print(foo)", defCtx); // prints "hello"
			 */

			Bindings gb=se.getBindings(ScriptContext.ENGINE_SCOPE);
		
			se1.setBindings(gb, ScriptContext.ENGINE_SCOPE);
			
			se.eval("var a='a'");
			
			se1.eval("var b='b'");
			
			Bindings seb=se.getBindings(ScriptContext.ENGINE_SCOPE);
			Bindings se1b=se1.getBindings(ScriptContext.ENGINE_SCOPE);
			System.out.println(seb==se1b);
			
			//se.eval("load('nashorn:mozilla_compat.js');");
			se.eval("var name='fujinlong1'");
			se.eval("var f1=function(){}");
			se.eval("var w=function(s,f2){s.__defineGetter__('name',f2);};");
			se.getContext().setAttribute("w", se.eval("w"), ScriptContext.ENGINE_SCOPE);
			
			
			se1.eval("load('nashorn:mozilla_compat.js');");
			se1.getContext().setAttribute("w", se.getContext().getAttribute("w", ScriptContext.ENGINE_SCOPE), ScriptContext.ENGINE_SCOPE);
			se1.eval("print(w)");
			se1.eval("var name='fujin1long'");
			//se1.eval("print(jdk.nashorn.internal.runtime.ScriptFunction)",sc1);
			se1.eval("var f2=function(){print(name)}");
			se1.eval("var ww=new w({},f2)");
			se1.eval("print(ww.name='ddd')");
			se1.eval("print(ww.name)");
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
