package Model;

public class Product {
    private Integer id;
    private int yearOfPremiere;
    private String title;
    private int time;
    private int season;
    private Double priceSuscription;
    private boolean haveOscar = false;

    public Product(int yearOfPremiere, String title, int time, int season, boolean haveOscar) {
        this(null,yearOfPremiere, title, time, season, haveOscar);
    }

    public Product(Integer id, int yearOfPremiere, String title, int time, int season, boolean haveOscar) {
        this.id = id;
        this.yearOfPremiere = yearOfPremiere;
        this.title = title;
        this.time = time;
        this.season = season;
        this.haveOscar = haveOscar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getYearOfPremiere() {
        return yearOfPremiere;
    }

    public void setYearOfPremiere(int yearOfPremiere) {
        this.yearOfPremiere = yearOfPremiere;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public boolean isHaveOscar() {
        return haveOscar;
    }

    public void setHaveOscar(boolean haveOscar) {
        this.haveOscar = haveOscar;
    }
}
