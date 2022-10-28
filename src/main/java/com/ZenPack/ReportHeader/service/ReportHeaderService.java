package com.ZenPack.ReportHeader.service;

import com.ZenPack.ReportHeader.model.ReportHeader;
import com.ZenPack.ReportHeader.repository.ReportHeaderRepository;
import com.ZenPack.ReportHeader.repository.ZenPackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportHeaderService {
    @Autowired
    private ReportHeaderRepository repository;

    @Autowired
    private ZenPackRepository zenPackRepository;

    public boolean checkZenPackName(String name) {
        boolean exists = zenPackRepository.findByName(name).size() == 0 ? false : true;
        return exists;
    }
    public ReportHeader createReportHeader(ReportHeader reportHeader) {
        return this.repository.save(reportHeader);
    }

    public List<ReportHeader> getAllReportHeader() {
        return this.repository.findAll();
    }

    public String deleteReportHeaderById(Long reportId) {
        this.repository.deleteById(reportId);
        return "Deleted Successfully";
    }

    public ReportHeader getReportHeaderById(Long reportId) {
        Optional<ReportHeader> reportHeader = this.repository.findById(reportId);
        if (reportHeader != null && reportHeader.isPresent()) {
            return reportHeader.get();
        }
        return null;
    }

    public ReportHeader getReportHeaderByName(String reportName) {
        Optional<ReportHeader> reportHeader = this.repository.findByReportName(reportName);
        if (reportHeader != null && reportHeader.isPresent()) {
            return reportHeader.get();
        }
        return null;
    }
}
