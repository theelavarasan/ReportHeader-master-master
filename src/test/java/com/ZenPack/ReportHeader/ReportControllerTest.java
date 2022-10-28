package com.ZenPack.ReportHeader;

import com.ZenPack.ReportHeader.Controller.ZenPackReportController;
import com.ZenPack.ReportHeader.dto.HeaderInfoDto;
import com.ZenPack.ReportHeader.model.ReportHeader;
import com.ZenPack.ReportHeader.repository.ReportHeaderRepository;
import com.ZenPack.ReportHeader.service.ReportHeaderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.AbstractBigDecimalAssert;
import org.hamcrest.CoreMatchers;

import org.hibernate.SessionFactory;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@WebMvcTest(ZenPackReportController.class)
@AutoConfigureMockMvc
public class ReportControllerTest {
    @MockBean
    private ReportHeaderRepository repository;

    @MockBean
    private ReportHeaderService service;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private ZenPackReportController zenPackReportController;

    @Autowired
    private ObjectMapper objectMapper;

    private ReportHeader reportHeader;
    private ReportHeader reportHeader1;

    public HeaderInfoDto headerInfoDto;


    @BeforeEach
    void init(){
        ReportHeader reportHeader = new ReportHeader();
        reportHeader.setReportId(1L);
        reportHeader.setReportName("Zen1");
        HeaderInfoDto headerInfoDto = new HeaderInfoDto();
        headerInfoDto.setActualName("Zen_Pack");
        headerInfoDto.setHide(true);
        headerInfoDto.setDisplayName("ZenPack");
        headerInfoDto.setDataType("String");

        List<HeaderInfoDto> list = new ArrayList<>();
        list.add(headerInfoDto);

        reportHeader.setHeaderInfo(list);

        ReportHeader reportHeader1 = new ReportHeader();
        reportHeader1.setReportId(2L);
        reportHeader1.setReportName("Zen2");
        HeaderInfoDto headerInfoDto1 = new HeaderInfoDto();
        headerInfoDto1.setActualName("Zen_Pack");
        headerInfoDto1.setHide(true);
        headerInfoDto1.setDisplayName("ZenPack");
        headerInfoDto1.setDataType("String");

        List<HeaderInfoDto> list1 = new ArrayList<>();
        list.add(headerInfoDto1);

        reportHeader1.setHeaderInfo(list1);


    }


    @Test
    void create() throws Exception {
        ReportHeader reportHeader = new ReportHeader();

        reportHeader.setReportId(2L);
        reportHeader.setReportName("zen");

//        HeaderInfoDto headerInfoDto = new HeaderInfoDto();
//        headerInfoDto.setActualName("Zen_Pack");
//        headerInfoDto.setHide(true);
//        headerInfoDto.setDisplayName("ZenPack");
//        headerInfoDto.setDataType("String");
//
//        List<HeaderInfoDto> list = new ArrayList<>();
//        list.add(headerInfoDto);
//
//        reportHeader.setHeaderInfo(list);


        when(service.createReportHeader(any(ReportHeader.class))).thenReturn(reportHeader);

        this.mockMvc.perform(post("/api/v1/reportHeader/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reportHeader)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.reportName", CoreMatchers.is(reportHeader.getReportName())));

        JSONObject actual = new JSONObject();
        actual.put("actualName","Zen_Pack");
        actual.put("hide",false);
        actual.put("displayName","ZenPack");
        actual.put("dataType","String");

        JSONAssert.assertEquals("{actualName:\"Zen_Pack\"}", actual, false);
    }

    @Test
    void shouldGetAllReports() throws Exception {
        List<ReportHeader> reportHeaderList = new ArrayList<>();
        reportHeaderList.add(reportHeader);
        reportHeaderList.add(reportHeader1);

        JSONObject actual = new JSONObject();
        actual.put("actualName","Zen_Pack");
        actual.put("hide",false);
        actual.put("displayName","ZenPack");
        actual.put("dataType","String");


        when(service.getAllReportHeader()).thenReturn(reportHeaderList);

        this.mockMvc.perform(get("/api/v1/reportHeader/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",CoreMatchers.is(reportHeaderList.size())));
        JSONAssert.assertEquals("{actualName:\"Zen_Pack\"}", actual, false);

    }



    @Test
    void shouldFetchOneReportById() throws Exception {
        ReportHeader reportHeader = new ReportHeader();
        reportHeader.setReportId(3L);
        reportHeader.setReportName("Zen");

        when(service.getReportHeaderById(anyLong())).thenReturn(reportHeader);

        this.mockMvc.perform(get("/api/v1/reportHeader/getReportHeaderById/{reportId}", 3L))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.reportId", CoreMatchers.is(reportHeader.getReportId())))
                .andExpect(jsonPath("$.reportName", CoreMatchers.is(reportHeader.getReportName())));
    }

    @Test
    void shouldFetchOneReportByName() throws Exception {
        ReportHeader reportHeader = new ReportHeader();
        reportHeader.setReportId(3L);
        reportHeader.setReportName("Zen");

        when(service.getReportHeaderByName("Zen")).thenReturn(reportHeader);

        this.mockMvc.perform(get("/api/v1/reportHeader/getReportHeaderById/{reportId}", 3L))
                .andExpect(status().isOk());
    }


    @Test
    public void deleteReport() throws Exception {
        mockMvc.perform(delete("/api/v1/reportHeader/delete/{reportId}", 1L)
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }

}
    
