package constantin.rosana.knowledge.base.models.facts;

public class Oscar {
    private String title;
    private String movie;
    private String owner;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "oscar.title=" + title + ";oscar.movie=" + movie + ";oscar.owner=" + owner + ";";
    }

    @Override
    public boolean equals(Object oscar) {
        if (!(oscar instanceof Oscar)) return false;
        if (oscar == this) return true;
        if (this.owner.equals(((Oscar) oscar).owner) && this.title.equals(((Oscar) oscar).title) && this.movie.equals(((Oscar) oscar).movie)) return true;
        return false;
    }
}
