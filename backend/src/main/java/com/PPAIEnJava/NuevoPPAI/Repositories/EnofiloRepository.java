package com.PPAIEnJava.NuevoPPAI.Repositories;

import com.PPAIEnJava.NuevoPPAI.Models.Persistent.Enofilo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnofiloRepository extends JpaRepository<Enofilo, Long> {
    @Query(value = "SELECT * FROM Enofilo", nativeQuery = true)
    List<Object[]> getEnofilos();
}
