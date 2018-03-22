package constantin.rosana.knowledge.base.parser;

import constantin.rosana.knowledge.base.models.facts.Men;
import constantin.rosana.knowledge.base.models.facts.Movie;
import constantin.rosana.knowledge.base.models.facts.Oscar;
import constantin.rosana.knowledge.base.models.facts.Women;
import constantin.rosana.knowledge.base.models.rules.Statement;
import constantin.rosana.knowledge.base.utils.Cache;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class XmlParser {

    private Document openFile(String fileName) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        File xmlFile = new File(classLoader.getResource(fileName).getFile());
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document document = documentBuilder.parse(xmlFile);
        document.getDocumentElement().normalize();
        return document;
    }

    public List<Movie> getMovies(String filename){
        Document document;
        try {
            document = openFile(filename);
        } catch (Exception e) {
            System.out.println("Problem at opening the file.");
            return new ArrayList<>();
        }

        NodeList nodeList = document.getElementsByTagName("movie");
        List<Movie> movies = new ArrayList<>();

        for (int index = 0; index < nodeList.getLength(); index++) {
            Node movie = nodeList.item(index);
            if (movie.getNodeType() == Node.ELEMENT_NODE) {
                NodeList movieInfo = movie.getChildNodes();
                Movie movieObj = new Movie();
                for (int i = 0; i < movieInfo.getLength(); i++) {
                    Node movInf = movieInfo.item(i);
                    if (movInf.getNodeType() == Node.ELEMENT_NODE) {
                        switch (movInf.getNodeName()) {
                            case "name" : {
                                movieObj.setName(movInf.getChildNodes().item(0).getNodeValue());
                                break;
                            }
                            case "director" : {
                                movieObj.setDirector(movInf.getChildNodes().item(0).getNodeValue());
                                break;
                            }
                            case "star" : {
                                movieObj.setStar(movInf.getChildNodes().item(0).getNodeValue());
                                break;
                            }
                        }
                    }
                }
                movies.add(movieObj);
                if (!Cache.movieList.contains(movieObj)) Cache.movieList.add(movieObj);
            }
        }
        return movies;
    }

    public List<Oscar> getOscars(String filename){
        Document document;
        try {
            document = openFile(filename);
        } catch (Exception e) {
            System.out.println("Problem at opening the file.");
            return new ArrayList<>();
        }

        NodeList nodeList = document.getElementsByTagName("oscar");
        List<Oscar> oscars = new ArrayList<>();

        for (int index = 0; index < nodeList.getLength(); index++) {
            Node movie = nodeList.item(index);
            if (movie.getNodeType() == Node.ELEMENT_NODE) {
                NodeList movieInfo = movie.getChildNodes();
                Oscar oscarObj = new Oscar();
                for (int i = 0; i < movieInfo.getLength(); i++) {
                    Node movInf = movieInfo.item(i);
                    if (movInf.getNodeType() == Node.ELEMENT_NODE) {
                        switch (movInf.getNodeName()) {
                            case "title" : {
                                oscarObj.setTitle(movInf.getChildNodes().item(0).getNodeValue());
                                break;
                            }
                            case "owner" : {
                                oscarObj.setOwner(movInf.getChildNodes().item(0).getNodeValue());
                                break;
                            }
                            case "name" : {
                                oscarObj.setMovie(movInf.getChildNodes().item(0).getNodeValue());
                                break;
                            }
                        }
                    }
                }
                oscars.add(oscarObj);
                if (!Cache.oscarList.contains(oscarObj)) Cache.oscarList.add(oscarObj);
            }
        }
        return oscars;
    }

    public List<Men> getMen(String filename) {
        Document document;
        try {
            document = openFile(filename);
        } catch (Exception e) {
            System.out.println("Problem at opening the file.");
            return new ArrayList<>();
        }

        NodeList nodeList = document.getElementsByTagName("men");
        List<Men> men = new ArrayList<>();

        for (int index = 0; index < nodeList.getLength(); index++) {
            Node man = nodeList.item(index);
            if (man.getNodeType() == Node.ELEMENT_NODE) {
                Men men1 = new Men();
                men1.setName(man.getChildNodes().item(0).getNodeValue());
                men.add(men1);
                if (!Cache.menList.contains(men1)) Cache.menList.add(men1);
            }
        }
        return men;
    }

    public List<Women> getWomen(String filename) {
        Document document;
        try {
            document = openFile(filename);
        } catch (Exception e) {
            System.out.println("Problem at opening the file.");
            return new ArrayList<>();
        }

        NodeList nodeList = document.getElementsByTagName("women");
        List<Women> women = new ArrayList<>();

        for (int index = 0; index < nodeList.getLength(); index++) {
            Node woman = nodeList.item(index);
            if (woman.getNodeType() == Node.ELEMENT_NODE) {
                Women women1 = new Women();
                women1.setName(woman.getChildNodes().item(0).getNodeValue());
                women.add(women1);
                if (!Cache.womenList.contains(women1)) Cache.womenList.add(women1);
            }
        }
        return women;
    }

    public List<String> getRules(String fileName) {
        Document document;
        try {
            document = openFile(fileName);
        } catch (Exception e) {
            System.out.println("Problem at opening the file.");
            return new ArrayList<>();
        }

        NodeList nodeList = document.getElementsByTagName("rule");
        List<String> rules = new ArrayList<>();

        for (int index = 0; index < nodeList.getLength(); index++) {
            Node rule = nodeList.item(index);
            if (rule.getNodeType() == Node.ELEMENT_NODE) {
                NodeList statements = rule.getChildNodes();
                Statement ifStatement = new Statement();
                Statement andStatement = new Statement();
                String result = "";
                for (int i = 0; i < statements.getLength(); i++) {
                    Node statement = statements.item(i);
                    if (statement.getNodeType() == Node.ELEMENT_NODE && (statement.getNodeName().equals("if") || statement.getNodeName().equals("and"))) {
                        NodeList ifChildren = statement.getChildNodes();
                        for (int j = 0; j < ifChildren.getLength(); j++) {
                            Node node = ifChildren.item(j);
                            if (node.getNodeType() == Node.ELEMENT_NODE) {
                                Element element = (Element) node;
                                switch (node.getNodeName()) {
                                    case "what": {
                                        if (statement.getNodeName().equals("if")) {
                                            ifStatement.setWhat(element.getChildNodes().item(0).getNodeValue());
                                        } else {
                                            andStatement.setWhat(element.getChildNodes().item(0).getNodeValue());
                                        }

                                        break;
                                    }
                                    case "relation": {
                                        if (statement.getNodeName().equals("if")) {
                                            ifStatement.setRelation(element.getChildNodes().item(0).getNodeValue());
                                        } else {
                                            andStatement.setRelation(element.getChildNodes().item(0).getNodeValue());
                                        }
                                        break;
                                    }
                                    case "to": {
                                        if (statement.getNodeName().equals("if")) {
                                            ifStatement.setTo(element.getChildNodes().item(0).getNodeValue());
                                        } else {
                                            andStatement.setTo(element.getChildNodes().item(0).getNodeValue());
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (statement.getNodeType() == Node.ELEMENT_NODE && statement.getNodeName().equals("then")) {
                        Element element = (Element) statement;
                        result = element.getChildNodes().item(0).getNodeValue();
                    }
                }

                if (andStatement.getRelation() != null) {
                    if (ifStatement.getTo().equals("*")) {
                        if (ifStatement.getWhat().contains("movie")) {
                            if (ifStatement.getWhat().contains("star")) {
                                if (andStatement.getWhat().contains("movie")) {
                                    if (andStatement.getWhat().contains("star")) {
                                        if (andStatement.getRelation().equals("=")) {
                                            if (andStatement.getTo().equals("women")) {
                                                for (Movie movie : Cache.movieList) {
                                                    String ruleString = "";
                                                    for (Women women : Cache.womenList) {
                                                        if (women.getName().equals(movie.getStar())) {
                                                            ruleString = "movie.name=" + movie.getName() + "&movie.director=" + movie.getDirector() +
                                                                    "&movie.star=" + movie.getStar() + "&women=" + women.getName() +
                                                                    "=>" + movie.getStar() + "." + result + ";";
                                                        }
                                                    }
                                                    if (!StringUtils.isEmpty(ruleString)) rules.add(ruleString);
                                                }
                                            } else if (andStatement.getTo().equals("men")) {
                                                for (Movie movie : Cache.movieList) {
                                                    String ruleString = "";
                                                    for (Men men : Cache.menList) {
                                                        if (men.getName().equals(movie.getStar())) {
                                                            ruleString = "movie.name=" + movie.getName() + "&movie.director=" + movie.getDirector() +
                                                                    "&movie.star=" + movie.getStar() + "&men=" + men.getName() +
                                                                    "=>" + movie.getStar() + "." + result + ";";
                                                        }
                                                    }
                                                    if (!StringUtils.isEmpty(ruleString)) rules.add(ruleString);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (ifStatement.getWhat().contains("oscar")) {
                        if (ifStatement.getWhat().contains("title")) {
                            if (ifStatement.getRelation().equals("=")) {
                                if (!ifStatement.getTo().contains("movie") && !ifStatement.getTo().contains("men") && !ifStatement.getTo().contains("women")
                                        && !ifStatement.getTo().contains("oscar")  && ifStatement.getTo().length() > 1) {
                                    if (andStatement.getWhat().contains("oscar")) {
                                        if (andStatement.getWhat().contains("owner")) {
                                            if (andStatement.getRelation().equals("!=")) {
                                                for (Oscar oscar : Cache.oscarList) {
                                                    String ruleString = "";
                                                    if (oscar.getTitle().equals(ifStatement.getTo()) && !oscar.getOwner().equals(andStatement.getTo())) {
                                                        ruleString = "oscar.title=" + oscar.getTitle() + "&oscar.movie=" + oscar.getMovie() + "&oscar.owner=" + oscar.getOwner() +
                                                                "&" + oscar.getOwner() + andStatement.getRelation() + andStatement.getTo() + "=>" + oscar.getOwner() + "." + result + ";";
                                                    }
                                                    if (!StringUtils.isEmpty(ruleString)) rules.add(ruleString);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (ifStatement.getWhat().contains("movie")) {
                        if (ifStatement.getWhat().contains("director")) {
                            if (ifStatement.getRelation().equals("=")) {
                                if (!ifStatement.getTo().contains("movie") && !ifStatement.getTo().contains("men") && !ifStatement.getTo().contains("women")
                                        && !ifStatement.getTo().contains("oscar")  && ifStatement.getTo().length() > 1) {
                                    for (Movie movie : Cache.movieList) {
                                        String ruleString = "";
                                        if (movie.getDirector().equals(ifStatement.getTo())) {
                                            ruleString = "movie.name=" + movie.getName() + "&movie.director=" + movie.getDirector() +
                                                    "&movie.star=" + movie.getStar() + "&director=" + movie.getDirector() + "=>" + movie.getName() + "." + result + ";";
                                        }
                                        if (!StringUtils.isEmpty(ruleString)) rules.add(ruleString);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return rules;
    }

}
