package jjjjjjj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.script.CompiledScript;
import javax.script.ScriptContext;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;

import jdk.nashorn.api.scripting.NashornScriptEngine;

public class compile1 {
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
			CompiledScript c = se.compile("load('envjs/rhino.js')");
/*
			ScriptContext s = new SimpleScriptContext();
			s.setAttribute("__currentScriptEngine__", se, ScriptContext.ENGINE_SCOPE);
			c.eval(s);*/
			//se.eval("load('nashorn:mozilla_compat.js');", s);
			//se.eval("new f(this)", s);

			/*ScriptContext s1 = new SimpleScriptContext();
			s1.setAttribute("__currentScriptEngine__", se, ScriptContext.ENGINE_SCOPE);
			c.eval(s1);*/
			//se.eval("load('nashorn:mozilla_compat.js');", s1);
			//se.eval("new f(this)", s1);
			
			
			ScriptContext s2 = new SimpleScriptContext();
			s2.setAttribute("__currentScriptEngine__", se, ScriptContext.ENGINE_SCOPE);
			c.eval(s2);
			se.eval("new Window(this)",s2);
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						se.eval("Envjs.eventLoop();",s2);
					} catch (ScriptException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}).start();
			se.eval("location='https://www.baidu.com'",s2);
			
			BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
			
			while (true) {

				try {
					String str = strin.readLine();
					String ll = null;
					while ((ll = strin.readLine()) != null) {
						if ("e".equals(ll)) {
							try {
								Object o = se.eval(str,s2);
								System.out.println(o);
							} catch (Exception e) {
								e.printStackTrace();
							}

							break;
						}
						str += ll;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			

		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
