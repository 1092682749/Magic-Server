package com.example.demo;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;

public class ScriptTest {
    public static void main(String[] args) throws FileNotFoundException, ScriptException, NoSuchMethodException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        URL url = ScriptTest.class.getClassLoader().getResource("static/html2canvas.js");
        System.out.println(url.getPath());
        FileReader reader = new FileReader(url.getPath());
        engine.eval(reader);
        String s = "function base (){\n" +
                "        var div = document.createElement(\"div\");\n" +
                "        div.setAttribute(\"id\",\"capture\");\n" +
                "        div.innerText = \"divdivasdadadadasd\";\n" +
                "        html2canvas(div).then(function(canvas) {\n" +
                "            console.log(canvas);\n" +
                "            return canvas;\n" +
                "        });\n" +
                "    }";
        engine.eval(s);
        Invocable invocable = (Invocable) engine;
        Object res = invocable.invokeFunction("base");
        System.out.println(res);
    }
}
