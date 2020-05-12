package com.telstra.codechallenge.repositary;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpringBootRepositaryController {

  private SpringBootRepositaryService springBootRepositaryService;

  public SpringBootRepositaryController(
          SpringBootRepositaryService springBootRepositaryService) {
    this.springBootRepositaryService = springBootRepositaryService;
  }

  @RequestMapping("/api/repositories")
  public List<ViewRepository> repositories(@RequestParam(name = "q") String query) {
    return springBootRepositaryService.getRepositaries(query);
  }


}
