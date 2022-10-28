package com.ZenPack.ReportHeader;

import com.ZenPack.ReportHeader.dto.HeaderInfoDto;
import com.ZenPack.ReportHeader.model.ReportHeader;
import com.ZenPack.ReportHeader.repository.ReportHeaderRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.annotation.Rollback;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@AutoConfigureMockMvc
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReportHeaderRepoTest {
    @Autowired
    private ReportHeaderRepository repository;

    @Test
    @Order(1)
    @Rollback(value = false)
    void saveReportHeader(){
        ReportHeader reportHeader = ReportHeader.builder()
                .reportId(1L)
                .reportName("ZenPack11")
                .build();
        ReportHeader newList = repository.save(reportHeader);
        assertNotNull(newList);
        assertThat(newList.getReportId()).isNotEqualTo(null);

    }

    @Test
    @Order(2)
    @Rollback(value = false)
    void getAllReports(){
        List<ReportHeader> reportHeaderList = repository.findAll();

        assertNotNull(reportHeaderList);
        assertThat(reportHeaderList).isNotNull();
        assertNotEquals(7,reportHeaderList.size());
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    void deleteList() {

        ReportHeader reportHeader = ReportHeader.builder()
                .reportId(20L)
                .reportName("ZenPack9")
                .build();

        repository.save(reportHeader);
        Long id = reportHeader.getReportId();


        repository.delete(reportHeader);

        List<ReportHeader> list1 = repository.findAll();

        Optional<ReportHeader> existingReport = repository.findById(id);

        assertThat(existingReport).isEmpty();

    }

    @Test
    @Order(3)
    @Rollback(value = false)
    void getReportById(){
        ReportHeader reportHeader = ReportHeader.builder()
                .reportId(3L)
                .reportName("ZenPack1")
                .build();

        ReportHeader newList = repository.save(reportHeader);


        ReportHeader reportHeader1 = repository.findById(reportHeader.getReportId()).get();

        assertNotNull(reportHeader1);
    }
}
