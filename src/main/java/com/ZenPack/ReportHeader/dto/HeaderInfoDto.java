package com.ZenPack.ReportHeader.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
//@Builder
public class HeaderInfoDto implements Serializable {

	private static final long serialVersionUID = 6067643839574646866L;
	
	private String actualName;
	private boolean hide;
	private String displayName;
	private String dataType;
	
}
