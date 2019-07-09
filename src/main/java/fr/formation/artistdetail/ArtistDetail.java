package fr.formation.artistdetail;

import fr.formation.artist.Artist;
import fr.formation.department.Department;

import javax.persistence.*;
import java.util.Set;

/**
 * The type Artist Detail.
 */
@Entity
@Table(name = "artistdetail")
public class ArtistDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="photo")
    private Byte[] photo;

    @Column(name="longDescription")
    private String longDescription;

    @Column(name="site")
    private String site;

    @Column(name="nbVotes")
    private int nbVotes;

    @Column(name="totalVotes")
    private int totalVotes;

    @OneToOne
    private Artist artiste;

    @OneToMany
    private Set<Department> departments;

    public ArtistDetail(){}

    public ArtistDetail(Byte[] photo, String longDescription, String site, int nbVotes, int totalVotes, Artist artiste, Set<Department> departments) {
        this.photo = photo;
        this.longDescription = longDescription;
        this.site = site;
        this.nbVotes = nbVotes;
        this.totalVotes = totalVotes;
        this.artiste = artiste;
        this.departments = departments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(Byte[] photo) {
        this.photo = photo;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getNbVotes() {
        return nbVotes;
    }

    public void setNbVotes(int nbVotes) {
        this.nbVotes = nbVotes;
    }

    public int getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(int totalVotes) {
        this.totalVotes = totalVotes;
    }

    public Artist getArtiste() {
        return artiste;
    }

    public void setArtiste(Artist artiste) {
        this.artiste = artiste;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }
}
