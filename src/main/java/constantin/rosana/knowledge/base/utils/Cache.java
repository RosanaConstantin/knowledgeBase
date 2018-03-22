package constantin.rosana.knowledge.base.utils;

import constantin.rosana.knowledge.base.models.facts.Men;
import constantin.rosana.knowledge.base.models.facts.Movie;
import constantin.rosana.knowledge.base.models.facts.Oscar;
import constantin.rosana.knowledge.base.models.facts.Women;

import java.util.ArrayList;
import java.util.List;

public class Cache {

    public static List<Movie> movieList = new ArrayList<>();
    public static List<Oscar> oscarList = new ArrayList<>();
    public static List<Men> menList = new ArrayList<>();
    public static List<Women> womenList = new ArrayList<>();
    public static List<String> rules = new ArrayList<>();
    public static String base = "";
}
