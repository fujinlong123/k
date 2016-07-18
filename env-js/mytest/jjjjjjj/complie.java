package jjjjjjj;

import javax.script.Bindings;
import javax.script.CompiledScript;
import javax.script.ScriptContext;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;

import jdk.nashorn.api.scripting.NashornScriptEngine;

public class complie {
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
			CompiledScript c = se.compile("var f=function(s){s.__defineGetter__('name',function(){});}");

			ScriptContext s = new SimpleScriptContext();

			c.eval(s);
			se.eval("load('nashorn:mozilla_compat.js');", s);
			se.eval("new f(this)", s);

			ScriptContext s1 = new SimpleScriptContext();

			c.eval(s1);
			se.eval("load('nashorn:mozilla_compat.js');", s1);
			se.eval("new f(this)", s1);

		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
