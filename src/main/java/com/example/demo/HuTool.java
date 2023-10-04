package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.StringWriter;
import java.io.PrintWriter;


// import cn.hutool.json.JSON;
// import cn.hutool.json.JSONUtil;
// import cn.hutool.json.JSONObject;



@RestController
public class HuTool {

    
    public static void main(String[] args) {
	SpringApplication.run(HuTool.class, args);
    }

    @GetMapping("/jsonutil")
    public String jsonutil() {
	try {
	    String s = "{\"G\":00,[,,[0E5,6E9,6E5,6E9,6E5,6E9,6E5,6E9,6E9,6E5,true,6E5,6E9,6E5,6E9,6956,EE,5E9,6E5,RE,6E9,6E9,6E5,6E9,6E5,6E9,6E5,6E9,6E5,6E962756779,4141697],]}";
	    testHutoolJSON(s);
	   } catch (Exception e) {
	    StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    e.printStackTrace(pw);
	    return sw.toString();
        }
	//testGson(s);
	    
	return "OK";
    }

    // hutool-json测试
    public void testHutoolJSON (String str) {
	// JSON json = JSONUtil.parse(str);
    }

    /*
    // gson测试
    public void testGson (String str) {
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	JSONObject entries = gson.fromJson(str, JSONObject.class);
	System.out.println(entries.toStringPretty());
    }
    */

    @GetMapping("/jsonobject")
    public String jsonobject() {

	try {
	    String a="{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{'a':1}:1}:1}:1}:1}:1}:1}:1}:1}:1}:1}:1}:1}:1}:1}:1}:1}:1}:1}:1}:1}:1}:1}:1}:1}:1}:1}:1}:1}:1}";
	    // JSONObject jsonObject=new JSONObject(a);
	} catch (OutOfMemoryError e) {
	    return "OutOfMemoryError thrown";
        }
	return "OK";
    }
    
}

