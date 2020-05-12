package com.telstra.codechallenge.repositary;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class SpringBootRepositaryService {

  @Value("${repositaries.base.url}")
  private String quotesBaseUrl;

  private RestTemplate restTemplate;

  public SpringBootRepositaryService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  /**
   * Returns an array of spring boot quotes. Taken from https://spring.io/guides/gs/consuming-rest/.
   *
   * @return - a quote array
   */
  public List<ViewRepository> getRepositaries(@RequestParam(name = "q") String query) {
    ZonedDateTime now = ZonedDateTime.now();
    ZonedDateTime zonedDateTime = now.minusDays(7);
    String  abc =  zonedDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE);

    ResponseEntity<GitHubRepositoriesResponse> forEntity =
              restTemplate.getForEntity(String.format("https://api.github.com/search/repositories?q=created:>"+abc+"", query), GitHubRepositoriesResponse.class);
      List<Repository> lr= sortByStar(forEntity.getBody().getItems());

    List<ViewRepository> lvr = new ArrayList<>();
    for(Iterator<Repository> itr = lr.iterator(); itr.hasNext();) {
      Repository r = itr.next();
              ViewRepository vr=new ViewRepository();
              vr.setDescription(r.getDescription());
              vr.setHtml_url(r.getHtml_url());
              vr.setLanguage(r.getLanguage());
              vr.setName(r.getName());
              vr.setWatchers_count(r.getWatchers_count());
              lvr.add(vr);
    }

      int size = lvr.size();
      if(size > Integer.parseInt(query))
      return lvr.subList(0, Integer.parseInt(query));
      else
        return lvr;
  }

  public static List<Repository> sortByStar(List<Repository> offers){
    if(!offers.isEmpty()) {
      Collections.sort(offers, new Comparator<Repository>() {
        @Override
        public int compare(Repository o1, Repository o2) {
          if(o1 == null  || o2 == null ) {
            return 0;
          }
          return (o2.getStargazers_count()).compareTo((o1.getStargazers_count()));

        }
      });
    }
    return offers;
  }

  /**
   * Returns a random spring boot quote. Taken from https://spring.io/guides/gs/consuming-rest/.
   *
   * @return - a quote
   */

}
