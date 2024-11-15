package com.PPAIEnJava.NuevoPPAI.Repositories;

import com.PPAIEnJava.NuevoPPAI.Models.Persistent.Siguiendo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SiguiendoRepository extends JpaRepository<Siguiendo, Integer> {
    @Query(value = "SELECT * FROM Siguiendo", nativeQuery = true)
    List<Object[]> getSiguiendos();
}
