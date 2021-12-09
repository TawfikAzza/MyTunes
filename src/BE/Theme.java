package BE;

public class Theme {
    private String nameTheme;
    private String themePath;

    public Theme(String name, String themePath) {
        this.nameTheme=name;
        this.themePath=themePath;
    }

    public String getNameTheme() {
        return nameTheme;
    }

    public void setNameTheme(String nameTheme) {
        this.nameTheme = nameTheme;
    }

    public String getThemePath() {
        return themePath;
    }

    public void setThemePath(String themePath) {
        this.themePath = themePath;
    }

    @Override
    public String toString() {
        return String.format("%s",getNameTheme());
    }
}
