/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * 
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.casereport;

import static org.junit.Assert.assertEquals;
import static org.openmrs.module.casereport.DocumentUtil.convertToDecimal;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;
import org.openmrs.util.OpenmrsUtil;

public class DocumentUtilTest {
	
	@Test
	public void convertToDecimalString_shouldReturnTheStringifiedDecimalFormOfTheSpecifiedUuid() {
		assertEquals("165886298145228458464681453875973269261",
		    convertToDecimal(UUID.fromString("7ccc89f5-1904-4141-b5e3-bf0d8bb3270d")));
		
		//Should be the unsigned representation for negative numbers 
		String uuid = "e2687878-fb18-4dda-85c4-eb451bbb765e";
		assertEquals("300947969394920668599875792303032071774", convertToDecimal(UUID.fromString(uuid)));
	}
	
	@Test
	public void getCaseReportFile_shouldGetTheDocumentFileForTheSpecifiedCaseReport() throws Exception {
		
		CaseReport cr = new CaseReport();
		final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		final String year = "2016";
		final String month = "11";
		final String day = "2";
		Date resolutionDate = dateFormat.parse(year + "-" + month + "-" + day);
		cr.setResolutionDate(resolutionDate);
		String sep = SystemUtils.FILE_SEPARATOR;
		System.setProperty("OPENMRS_APPLICATION_DATA_DIRECTORY", SystemUtils.JAVA_IO_TMPDIR);
		File file = DocumentUtil.getCaseReportFile(cr);
		String expected = OpenmrsUtil.getApplicationDataDirectory() + CaseReportConstants.MODULE_ID + sep + year + sep
		        + month + sep + day + sep + cr.getUuid() + DocumentConstants.DOC_FILE_EXT;
		assertEquals(expected, file.getAbsolutePath());
	}
}
