package com.ZenPack.ReportHeader.model;


import com.ZenPack.ReportHeader.dto.HeaderInfoDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "REPORT_HEADER", uniqueConstraints = {
		@UniqueConstraint(name = "UniqueReportName", columnNames = { "REPORT_NAME" }) })
@JsonInclude(JsonInclude.Include.NON_NULL)
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Builder
public class ReportHeader implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REPORT_ID")
	private Long reportId;

	@Column(name = "REPORT_NAME", unique = true)
	private String reportName;

	@Type(type = "jsonb") // See (2)
	@Column(name = "HEADERINFO", columnDefinition = "jsonb")
	private List<HeaderInfoDto> headerInfo;

	@Type(type = "jsonb") // See (2)
	@Column(name = "COLUMNORDER", columnDefinition = "jsonb")
	private List<String> columnOrder;

//	public ReportHeader(long l, String zen1) {
//	}
}
