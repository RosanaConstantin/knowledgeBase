package constantin.rosana.knowledge.base.models.facts;

public class Men {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "men=" + name + ";";
    }

    @Override
    public boolean equals(Object men) {
        if (!(men instanceof Men)) return false;
        if (men == this) return true;
        if (this.name.equals(((Men) men).name)) return true;
        return false;
    }
}
