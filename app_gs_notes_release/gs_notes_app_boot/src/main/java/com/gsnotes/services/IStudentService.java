package com.gsnotes.services;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.gsnotes.web.models.ExcelDataModel;

public interface IStudentService {
	
	List<ExcelDataModel> importFromExcel(Workbook workbook) throws IOException;
	void updateStudentsExcel(List<ExcelDataModel> listModel) throws IOException;
}
