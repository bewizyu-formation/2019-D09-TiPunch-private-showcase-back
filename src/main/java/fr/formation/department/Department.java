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
    private Long id;

    @Column(name="name")
    @NotNull
    private String name;

    @Column(name="code")
    @NotNull
    private String code;

    @Column(name="name_uppercase")
    private String name_uppercase;

    @Column(name="slug")
    private String slug;

    @Column(name="name_soundex")
    private String name_soundex;


    public Department(){}

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

    public String getName_uppercase() {
        return name_uppercase;
    }

    public void setName_uppercase(String name_uppercase) {
        this.name_uppercase = name_uppercase;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName_soundex() {
        return name_soundex;
    }

    public void setName_soundex(String name_soundex) {
        this.name_soundex = name_soundex;
    }

}
