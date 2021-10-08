package cvut.fel.dbs.lib.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Jizda {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(mappedBy = "minuleJizdy")
    private List<Cestujici> cestujici;

    private String datum;

    @Temporal(TemporalType.TIMESTAMP)
    private Date cas;
    private String linka;
    private String spolecnost;
    private Long idRidice;

    public Long getIdRidice() {
        return idRidice;
    }

    public void setIdRidice(Long idRidice) {
        this.idRidice = idRidice;
    }

    public Long getId() {
        return id;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public Date getCas() {
        return cas;
    }

    public void setCas(Date cas) {
        this.cas = cas;
    }

    public String getLinka() {
        return linka;
    }

    public void setLinka(String linka) {
        this.linka = linka;
    }

    public String getSpolecnost() {
        return spolecnost;
    }

    public void setSpolecnost(String spolecnost) {
        this.spolecnost = spolecnost;
    }

    public List<Cestujici> getCestujici() {
        return cestujici;
    }
}
