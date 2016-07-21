package jjjjjjj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.script.Bindings;
import javax.script.CompiledScript;
import javax.script.ScriptContext;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import javax.script.SimpleScriptContext;

import jdk.nashorn.api.scripting.NashornScriptEngine;

public class adf {
	public static void main(String[] args) throws FileNotFoundException {
		ScriptEngineManager sem = new ScriptEngineManager();
		System.out.println(java.lang.System.getProperty("user.dir"));
		String userDir = java.lang.System.getProperty("user.dir");
		NashornScriptEngine se = (NashornScriptEngine) sem.getEngineByName("js");

		NashornScriptEngine se1 = (NashornScriptEngine) sem.getEngineByName("js");
		try {
			se.put("se", se);
			se.eval("var f=function(e){print(e.name);}");
			se.eval("var a={name:'fujinlong'}");
			se.eval("eval('var fp=function(){f(this);};fp.call(a);')");
		
		
			//se.eval("se.eval('f(this)',a)");

		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
