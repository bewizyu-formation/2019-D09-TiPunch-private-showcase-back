package fr.formation.artistdetail;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.formation.artist.Artist;
import fr.formation.department.Department;

import javax.persistence.*;
import java.util.Set;

/**
 * The type Artist Detail.
 */
@Entity
@Table(name = "artist_detail")
public class ArtistDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="photo")
    private String photo;

    @Column(name="longDescription", length = 10000)
    private String longDescription;

    @Column(name="site")
    private String site;

    @Column(name="phoneNumber")
    private int phoneNumber;

    @Column(name="nbVotes")
    private int nbVotes;

    @Column(name="totalVotes")
    private int totalVotes;

    @OneToOne
    private Artist artist;


    @ManyToMany
    @JoinTable(name = "artistdetail_department",
        joinColumns = @JoinColumn(name="id_artistdetail", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "id_department", referencedColumnName = "id"))
    @JsonIgnore
    private Set<Department> departments;

    public ArtistDetail(){}


    @Override
    public String toString() {
        return "ArtistDetail{" +
                "artiste=" + artist +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }
}
