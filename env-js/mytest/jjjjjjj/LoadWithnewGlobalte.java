package jjjjjjj;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import jdk.nashorn.api.scripting.NashornScriptEngine;

public class LoadWithnewGlobalte {
	public static void main(String[] args) {
		ScriptEngineManager sem = new ScriptEngineManager();
		System.out.println(java.lang.System.getProperty("user.dir"));

		NashornScriptEngine se =  (NashornScriptEngine)sem.getEngineByName("js");
		
		
		try {
			Object innerGlobal=se.eval("loadWithNewGlobal({name:'test',script:'load(\"nashorn:mozilla_compat.js\");var func=function(){print(\"func\");};this;'})");
			se.put("innerGlobal", innerGlobal);
			se.eval("innerGlobal.func();");
			se.eval("print(innerGlobal.__defineGetter__);");
		
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
