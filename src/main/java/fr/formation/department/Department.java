package fr.formation.department;

import fr.formation.artistdetail.ArtistDetail;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Type of Department
 */
@Entity
@Table(name="department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    @NotNull
    private String name;

    @Column(name="code")
    @NotNull
    private String code;

    @ManyToOne
    @JoinColumn(name="id_artistdetail")
    private ArtistDetail artistDetail;

    public Department(){}

    public Department(@NotNull String name, @NotNull String code) {
        this.name = name;
        this.code = code;
    }

    public Department(@NotNull String name, @NotNull String code, ArtistDetail artistDetail) {
        this.name = name;
        this.code = code;
        this.artistDetail = artistDetail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArtistDetail getArtistDetail() {
        return artistDetail;
    }

    public void setArtistDetail(ArtistDetail artistDetail) {
        this.artistDetail = artistDetail;
    }
}
