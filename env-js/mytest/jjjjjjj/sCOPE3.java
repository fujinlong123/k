package jjjjjjj;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import javax.script.SimpleScriptContext;

import jdk.nashorn.api.scripting.NashornScriptEngine;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

public class sCOPE3 {
	public static void main(String[] args) {
		ScriptEngineManager sem = new ScriptEngineManager();
		System.out.println(java.lang.System.getProperty("user.dir"));

		NashornScriptEngine se = (NashornScriptEngine) sem.getEngineByName("js");

		NashornScriptEngine se1 = (NashornScriptEngine) sem.getEngineByName("js");
		NashornScriptEngine se2 = (NashornScriptEngine) sem.getEngineByName("js");
		NashornScriptEngine se3 = (NashornScriptEngine) sem.getEngineByName("js");
		try {
			
		ScriptContext ct=new SimpleScriptContext();
		se.setContext(ct);
		se.eval("var a=function(){};");
		se.eval("print(new Object())");
		Bindings gb=	ct.getBindings(ScriptContext.GLOBAL_SCOPE);
		Bindings b=	ct.getBindings(ScriptContext.ENGINE_SCOPE);
		Object  ob=b.get("Object");
		
		Bindings gb1=se1.getBindings(ScriptContext.GLOBAL_SCOPE);
		Bindings b1=((ScriptObjectMirror)se1.getBindings(ScriptContext.ENGINE_SCOPE));
		se1.eval("print(new Object())");
		Object  ob1=b1.get("Object");
		
		ScriptContext ct2=new SimpleScriptContext();
		Bindings bbb=new SimpleBindings();
		ct2.setBindings(bbb, ScriptContext.ENGINE_SCOPE);
		Bindings gb2=	ct2.getBindings(ScriptContext.GLOBAL_SCOPE);
		Bindings b2=	ct2.getBindings(ScriptContext.ENGINE_SCOPE);
		System.out.println(bbb==b2);
		se2.setContext(ct2);
		se2.eval("var a=function(){}");
		se2.eval("new Object()");
		
		//ct.setBindings(bindings, scope);
		Object gb3=se3.getBindings(ScriptContext.GLOBAL_SCOPE);
		System.out.println(gb3==gb1);
	
		se.eval("");	
			
			
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
