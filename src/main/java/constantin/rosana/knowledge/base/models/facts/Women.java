package constantin.rosana.knowledge.base.models.facts;

public class Women {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "women=" + name + ";";
    }

    @Override
    public boolean equals(Object women) {
        if (!(women instanceof Women)) return false;
        if (women == this) return true;
        if (this.name.equals(((Women) women).name)) return true;
        return false;
    }
}
