package com.PPAIEnJava.NuevoPPAI.Repositories;

import com.PPAIEnJava.NuevoPPAI.Models.NoPersistent.VinoRemoto;
import com.PPAIEnJava.NuevoPPAI.Models.Persistent.Vino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VinoRemotoRepositroy extends JpaRepository<VinoRemoto, Long> {
    @Query(value = "SELECT V FROM VinoRemoto V where cast(V.BODEGA as string) like %:idBodega%")
    List<VinoRemoto> findVinoByBodega(@Param("idBodega") String idBodega);
}
