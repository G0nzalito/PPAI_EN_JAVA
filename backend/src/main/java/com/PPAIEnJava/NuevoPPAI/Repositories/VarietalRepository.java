package com.PPAIEnJava.NuevoPPAI.Repositories;

import com.PPAIEnJava.NuevoPPAI.Models.Persistent.Varietal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VarietalRepository extends JpaRepository<Varietal, Long> {
    @Query(value = "SELECT * FROM Varietal", nativeQuery = true)
    List<Object[]> getVarietales();

    @Query(value = "SELECT * FROM Varietal_de_vino", nativeQuery = true)
    List<Object[]> getVarietalesVino();
}
