package com.example.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

  GreetingController() {
    System.out.printf("PAUL - my bean has been initialized\n");
  }

  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  @RequestMapping("/greeting")
  public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    return new Greeting(counter.incrementAndGet(),
        String.format(template, name));
  }

  // Worlds simplest
  @RequestMapping("/hi")
  public String hi() {
    return "hi";
  }

  // Jackson automatically converts to JSON with application/json
  @RequestMapping("/map")
  public Map getMap() {
    HashMap<String, String> map = new HashMap<String, String>();
    map.put("Paul", "Bartlett");
    map.put("Fred", "Bloggs");
    return map;
  }


}

