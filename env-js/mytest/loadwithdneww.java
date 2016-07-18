import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import jdk.nashorn.api.scripting.NashornScriptEngine;

public class loadwithdneww {
	public static void main(String[] args) throws ScriptException {
		
		ScriptEngineManager sem = new ScriptEngineManager();
		System.out.println(java.lang.System.getProperty("user.dir"));

		NashornScriptEngine se =  (NashornScriptEngine)sem.getEngineByName("js");
		se.eval("var ctx={}");
		se.eval("var obj=loadWithNewGlobal({name:'xxxxx',script:'var f=function(){print(11111);};f()'},ctx)");
		Object o=se.eval("ctx.f");
		se.eval("print(obj);print(f)");
		
	}
}
