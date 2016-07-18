package jjjjjjj;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import jdk.nashorn.api.scripting.NashornScriptEngine;

public class Bindproperties {
	public static void main(String[] args) {
		ScriptEngineManager sem = new ScriptEngineManager();
		System.out.println(java.lang.System.getProperty("user.dir"));

		NashornScriptEngine se =  (NashornScriptEngine)sem.getEngineByName("js");
		
		
		try {
			se.eval("var name='fulang';");
			se.eval("var ctx={name:'fujinlong'};");
			se.eval("var func=function(){print(name)};");
			se.eval("Object.bindProperties(func,ctx)");
			se.eval("func();");
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	}
}
