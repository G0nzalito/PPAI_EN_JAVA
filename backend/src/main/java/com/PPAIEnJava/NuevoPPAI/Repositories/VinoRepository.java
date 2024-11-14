package com.PPAIEnJava.NuevoPPAI.Repositories;

import com.PPAIEnJava.NuevoPPAI.Models.NoPersistent.VinoRemoto;
import com.PPAIEnJava.NuevoPPAI.Models.Persistent.Vino;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Objects;

public interface VinoRepository extends JpaRepository<Vino, Long> {
    @Query(value = "SELECT * FROM Vinos where cast(BODEGA as string) like :idBodega", nativeQuery = true)
    List<Object[]> findVinoByBodega(@Param("idBodega") String idBodega);

    @Query(value = "SELECT * FROM Vinos", nativeQuery = true)
    List<Object[]> getVinos();

}
