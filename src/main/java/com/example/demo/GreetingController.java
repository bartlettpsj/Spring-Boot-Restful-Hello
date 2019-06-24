package com.example.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

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
  public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
    return new Greeting(counter.incrementAndGet(),
        String.format(template, name));
  }

  private String getMapDisplay(String title, Set set1) {
    String header = String.format("<h1>%s</h1>", title);
    Set<Map.Entry<String, String>> set = set1;

    return header  +
        set.stream()
            .map( entry -> "<b>" + entry.getKey() + "</b>: " + entry.getValue())
            .collect(Collectors.joining("<br>"));
  }

  // Return Environment Variables
  @RequestMapping("/env")
  public String env() {
    return getMapDisplay("Environment Variables", System.getenv().entrySet());
  }

  // Return System Properties
  @RequestMapping("/prop")
  public String prop() {
    return getMapDisplay("System Properties", System.getProperties().entrySet());
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

