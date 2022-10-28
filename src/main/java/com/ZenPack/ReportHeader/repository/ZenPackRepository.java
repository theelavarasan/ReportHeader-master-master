package com.ZenPack.ReportHeader.repository;

import com.ZenPack.ReportHeader.model.ZenPack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ZenPackRepository extends JpaRepository<ZenPack,Integer>, JpaSpecificationExecutor<ZenPack> {
    List<ZenPack> findByName(String name);

}
