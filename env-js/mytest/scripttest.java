import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.script.Bindings;
import javax.script.CompiledScript;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import javax.script.SimpleScriptContext;

import jdk.nashorn.api.scripting.NashornScriptEngine;
import jdk.nashorn.api.scripting.URLReader;

public class scripttest {
	public static void main(String[] args) throws ScriptException, MalformedURLException {
		ScriptEngineManager sem = new ScriptEngineManager();
		System.out.println(java.lang.System.getProperty("user.dir"));

		NashornScriptEngine se = (NashornScriptEngine) sem.getEngineByName("js");
		NashornScriptEngine se1 = (NashornScriptEngine) sem.getEngineByName("js");

		NashornScriptEngine se2 = (NashornScriptEngine) sem.getEngineByName("js");

		ScriptContext globalContext = new SimpleScriptContext();

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					globalContext.setAttribute("__currentScriptEngine__", se, ScriptContext.ENGINE_SCOPE);
					// se.put(key, value);

					se.setContext(globalContext);
					se.eval("load('envjs/rhino.js')", globalContext);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
				ScriptContext ctx = new SimpleScriptContext();
				Bindings bbb = globalContext.getBindings(ScriptContext.ENGINE_SCOPE);
				Bindings bbb1 = globalContext.getBindings(ScriptContext.GLOBAL_SCOPE);
				try {
					// ScriptContext ctx1=new SimpleScriptContext();
					se1.eval("load('nashorn:mozilla_compat.js');", ctx);
					se1.eval("print(Object.__defineSetter__)", ctx);
					se1.eval("print({}.__defineSetter__)", ctx);
				} catch (ScriptException e1) {
					e1.printStackTrace();
				}
				while (globalContext.getBindings(ScriptContext.ENGINE_SCOPE).get("Window") == null) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				ctx.getBindings(ScriptContext.ENGINE_SCOPE).put("Window",
						globalContext.getBindings(ScriptContext.ENGINE_SCOPE).get("Window"));
				//ctx.setBindings(globalContext.getBindings(ScriptContext.ENGINE_SCOPE), ScriptContext.ENGINE_SCOPE);
				// ctx.setAttribute("Window", "window",
				// ScriptContext.ENGINE_SCOPE);

				while (true) {

					try {
						String str = strin.readLine();
						String ll = null;
						while ((ll = strin.readLine()) != null) {
							if ("e".equals(ll)) {
								try {
									Object o = se1.eval(str, ctx);
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

			}
		}).start();

	}
}
