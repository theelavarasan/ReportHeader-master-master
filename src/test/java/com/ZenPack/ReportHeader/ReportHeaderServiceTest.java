package com.ZenPack.ReportHeader;

import com.ZenPack.ReportHeader.dto.HeaderInfoDto;
import com.ZenPack.ReportHeader.model.ReportHeader;
import com.ZenPack.ReportHeader.repository.ReportHeaderRepository;
import com.ZenPack.ReportHeader.service.ReportHeaderService;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ReportHeaderServiceTest {
    @Mock
    private ReportHeaderRepository repository;

    @InjectMocks
    private ReportHeaderService service;

    private ReportHeader reportHeader;

    private ReportHeader reportHeader1;

    public HeaderInfoDto headerInfoDto;

    @BeforeEach
    void init(){
        ReportHeader reportHeader = new ReportHeader();
        reportHeader.setReportId(1L);
        reportHeader.setReportName("Zen1");


        ReportHeader reportHeader1 = new ReportHeader();
        reportHeader1.setReportId(2L);
        reportHeader1.setReportName("Zen2");
    }

    @Test
    @DisplayName("Junit Test for Save Reports")
    void saveList(){
        ReportHeader reportHeader = new ReportHeader();

        reportHeader.setReportId(2L);
        reportHeader.setReportName("zen");
        JSONObject actual = new JSONObject();
        actual.put("actualName","Zen_Pack");
        actual.put("hide",false);
        actual.put("displayName","ZenPack");
        actual.put("dataType","String");
        given(repository.findById(reportHeader.getReportId()))
                .willReturn(Optional.empty());

        given(repository.save(reportHeader)).willReturn(reportHeader);

        System.out.println(repository);
        System.out.println(service);

        ReportHeader savedReports = service.createReportHeader(reportHeader);

        System.out.println(savedReports);
        assertThat(savedReports).isNotNull();
    }

    @DisplayName("JUnit test for getAllReport")
    @Test
    void givenReport_whenGetAllReport_thenReturnReport(){
        ReportHeader reportHeader = ReportHeader.builder()
                .reportId(1L)
                .reportName("ZenPack")
                .build();
        HeaderInfoDto headerInfoDto = new HeaderInfoDto();
        headerInfoDto.setActualName("Zen_Pack");
        headerInfoDto.setHide(true);
        headerInfoDto.setDisplayName("ZenPack");
        headerInfoDto.setDataType("String");
        ReportHeader reportHeader1 = ReportHeader.builder()
                .reportId(2L)
                .reportName("ZenPack1")
                .build();
        HeaderInfoDto headerInfoDto1 = new HeaderInfoDto();
        headerInfoDto1.setActualName("created_Date");
        headerInfoDto1.setHide(true);
        headerInfoDto1.setDisplayName("Created Date");
        headerInfoDto1.setDataType("String");
        given(repository.findAll()).willReturn(Arrays.asList(reportHeader,reportHeader1));


        List<ReportHeader> list = service.getAllReportHeader();

        assertThat(list).isNotNull();
        assertThat(list.size()).isEqualTo(2);
    }


}
