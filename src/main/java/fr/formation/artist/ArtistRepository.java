package fr.formation.artist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    public Artist findByUsername(String username);

    public Artist findByName(String name);
}
