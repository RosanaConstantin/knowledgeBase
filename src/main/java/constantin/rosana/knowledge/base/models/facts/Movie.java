package constantin.rosana.knowledge.base.models.facts;

public class Movie {
    private String name;
    private String director;
    private String star;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    @Override
    public String toString() {
        return "movie.name=" + name + ";movie.director=" + director + ";movie.star=" + star + ";";
    }

    @Override
    public boolean equals(Object movie) {
        if (!(movie instanceof Movie)) return false;
        if (this == movie) return true;
        if (this.director.equals(((Movie) movie).director) && this.star.equals(((Movie) movie).star) && this.name.equals(((Movie) movie).name)) return true;
        return false;
    }

}
