package constantin.rosana.knowledge.base.controller;

import constantin.rosana.knowledge.base.models.facts.Movie;
import constantin.rosana.knowledge.base.parser.FC;
import constantin.rosana.knowledge.base.utils.Cache;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class DummyController {
    @RequestMapping(value = "/dummy", method = RequestMethod.GET)
    public String getFrontPage(Map<String, Object> model) {
        model.put("movies", Cache.movieList);
        model.put("oscars", Cache.oscarList);
        return "index";
    }

    @RequestMapping(value = "/type", method = RequestMethod.POST)
    public String getType(@RequestParam(name = "name") String name, @RequestParam(name = "type") String type, Map<String, Object> model) {
        FC fc = new FC(name + "." + type, Cache.base);
        model.put("result1", fc.execute());
        model.put("movies", Cache.movieList);
        model.put("oscars", Cache.oscarList);
        return "index";
    }

    @RequestMapping(value = "/bestmovies", method = RequestMethod.GET)
    public String getBestMovies(Map<String, Object> model) {
        List<String> movieTitles = new ArrayList<>();
        String result = "";
        for (String rule : Cache.rules) {
            if (rule.contains("bestmovieever")) {
                String movieTitle = rule.split("=>")[1];
                movieTitle = movieTitle.substring(0, movieTitle.length() - 15);
                movieTitles.add(movieTitle);
            }
        }
        for (Movie movie : Cache.movieList) {
            for (String movieTitle : movieTitles) {
                if (movie.getName().equals(movieTitle)) {
                    result += movie.getName() + ": " + movie.getStar() + ". ";
                }
            }
        }
        model.put("result2", result);
        model.put("movies", Cache.movieList);
        model.put("oscars", Cache.oscarList);
        return "index";
    }

    @RequestMapping(value = "/gooddirectors", method = RequestMethod.GET)
    public String getGoodDirectors(Map<String, Object> model) {
        String result = "";
        for (String rule : Cache.rules) {
            if (rule.contains("anothergooddirector")) {
                String directorName = rule.split("=>")[1];
                directorName = directorName.substring(0, directorName.length() - 21);
                result += directorName + ", ";
            }
        }

        model.put("result3", result);
        model.put("movies", Cache.movieList);
        model.put("oscars", Cache.oscarList);
        return "index";
    }

}
