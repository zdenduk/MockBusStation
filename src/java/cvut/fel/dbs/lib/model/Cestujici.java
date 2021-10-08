package cvut.fel.dbs.lib.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Cestujici {

    @Id
    private String email;

    private String tel_cislo;
    private String jmeno;
    private String prijmeni;
    private boolean premium;

    @ManyToMany
    private List<Jizda> minuleJizdy;

    public List<Jizda> getMinuleJizdy() {
        return minuleJizdy;
    }

    public void setMinuleJizdy(List<Jizda> minuleJizdy) {
        this.minuleJizdy = minuleJizdy;
    }

    public void addJizda(Jizda j) {
        minuleJizdy.add(j);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel_cislo() {
        return tel_cislo;
    }

    public void setTel_cislo(String tel_cislo) {
        this.tel_cislo = tel_cislo;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }
}
