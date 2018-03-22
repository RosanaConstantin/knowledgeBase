package constantin.rosana.knowledge.base.utils;

import constantin.rosana.knowledge.base.models.facts.Men;
import constantin.rosana.knowledge.base.models.facts.Movie;
import constantin.rosana.knowledge.base.models.facts.Oscar;
import constantin.rosana.knowledge.base.models.facts.Women;
import constantin.rosana.knowledge.base.parser.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class CacheInitializer {

    @Autowired
    private XmlParser parser;

    @PostConstruct
    public void init() {
        List<Movie> movies = this.parser.getMovies("base.xml");
        List<Oscar> oscars = this.parser.getOscars("base.xml");
        List<Women> women = this.parser.getWomen("base.xml");
        List<Men> men = this.parser.getMen("base.xml");
        List<String> rules = this.parser.getRules("base.xml");
        String knowledgeBase = "";
        for (Movie movie : movies) {
            knowledgeBase += movie.toString();
        }
        for (Oscar oscar : oscars) {
            knowledgeBase += oscar.toString();
        }
        for (Women women1 : women) {
            knowledgeBase += women1.toString();
        }
        for (Men men1 : men) {
            knowledgeBase += men1.toString();
        }
        for (String rule : rules) {
            knowledgeBase += rule.toString();
        }
        Cache.rules = rules;
        Cache.base = knowledgeBase;
    }
}
