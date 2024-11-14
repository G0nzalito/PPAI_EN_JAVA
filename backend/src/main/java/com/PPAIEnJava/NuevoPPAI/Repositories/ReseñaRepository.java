package com.PPAIEnJava.NuevoPPAI.Repositories;

import com.PPAIEnJava.NuevoPPAI.Models.Persistent.Reseña;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReseñaRepository extends JpaRepository<Reseña, Long> {
    @Query(value = "SELECT * FROM Reseñas", nativeQuery = true)
    List<Object[]> getReseñas();
}
