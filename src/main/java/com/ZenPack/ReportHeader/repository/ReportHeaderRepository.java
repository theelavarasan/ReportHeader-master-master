package com.ZenPack.ReportHeader.repository;

import com.ZenPack.ReportHeader.model.ReportHeader;
import com.ZenPack.ReportHeader.model.ZenPack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReportHeaderRepository extends JpaRepository<ReportHeader, Long>, JpaSpecificationExecutor<ReportHeader> {

	Optional<ReportHeader> findByReportName(String name);

//	List<ZenPack> findByName(String );

//	List<ReportHeader> findByName(String name);
}
