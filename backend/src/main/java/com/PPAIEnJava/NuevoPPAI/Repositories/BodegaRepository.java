package com.PPAIEnJava.NuevoPPAI.Repositories;

import com.PPAIEnJava.NuevoPPAI.Models.Persistent.Bodega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BodegaRepository extends JpaRepository<Bodega, Long> {
    @Query(value = "SELECT B FROM Bodega B WHERE B.NOMBRE LIKE %:nombre%")
    long recoverIdByNombre(@Param("nombre") String nombre);
}
