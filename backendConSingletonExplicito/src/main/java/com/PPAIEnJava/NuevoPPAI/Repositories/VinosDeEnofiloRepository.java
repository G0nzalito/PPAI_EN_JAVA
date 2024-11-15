package com.PPAIEnJava.NuevoPPAI.Repositories;

import com.PPAIEnJava.NuevoPPAI.Models.Persistent.Vino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VinosDeEnofiloRepository extends JpaRepository<Vino, Integer> {
    @Query(value = "SELECT * FROM Vinos_de_enofilo", nativeQuery = true)
    public List<Object[]> getVinosDeEnofilo();
}
