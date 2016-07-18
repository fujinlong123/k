package jjjjjjj;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;

import jdk.nashorn.api.scripting.NashornScriptEngine;
import jdk.nashorn.internal.runtime.linker.NashornGuards;

public class myggggg {
	public static void main(String[] args) {
		ScriptEngineManager sem = new ScriptEngineManager();
		System.out.println(java.lang.System.getProperty("user.dir"));

		NashornScriptEngine se = (NashornScriptEngine) sem.getEngineByName("js");

		NashornScriptEngine se1 = (NashornScriptEngine) sem.getEngineByName("js");
		try {

			// Bindings b= se.getBindings(ScriptContext.ENGINE_SCOPE);
			se.setContext(new SimpleScriptContext());
			
			// System.out.println(b);

			se.eval("load('nashorn:mozilla_compat.js');");
			System.out.println("");
			Object o = se.getContext().getAttribute("nashorn.global", ScriptContext.ENGINE_SCOPE);
			Bindings b= se.getBindings(ScriptContext.ENGINE_SCOPE);
			System.out.println(b);
		
			se1.setContext(new SimpleScriptContext());
			se1.put("nashorn.global", o);
			se1.eval("print(Object.__defineGetter__)");
			
			System.out.println();
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
