package com.ZenPack.ReportHeader.Controller;

import com.ZenPack.ReportHeader.excel.ZenPackExcelExporter;
import com.ZenPack.ReportHeader.model.ZenPack;
import com.ZenPack.ReportHeader.repository.ExcelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ZenPackController {
    @Autowired
    private ExcelRepository excelRepository;

    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headervalue = "attachment; filename=ZenPack_info.xlsx";

        response.setHeader(headerKey, headervalue);
        List<ZenPack> listStudent = excelRepository.findAll();
        ZenPackExcelExporter exp = new ZenPackExcelExporter(listStudent);
        exp.export(response);

    }
}
