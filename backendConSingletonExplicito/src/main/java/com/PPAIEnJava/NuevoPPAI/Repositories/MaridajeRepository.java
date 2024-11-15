package com.PPAIEnJava.NuevoPPAI.Repositories;

import com.PPAIEnJava.NuevoPPAI.Models.Persistent.Maridaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MaridajeRepository extends JpaRepository<Maridaje, Long> {
    @Query(value = "SELECT * FROM Maridaje_de_vino", nativeQuery = true)
    List<Object[]> getMaridajesVino();
}
