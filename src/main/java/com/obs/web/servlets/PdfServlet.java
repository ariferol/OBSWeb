/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.obs.web.servlets;

/**
 *
 * @author arif.erol
 */
import com.obs.dao.GradeRepositoryLocal;
import com.obs.model.StudentGrade;
import java.io.FileNotFoundException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletOutputStream;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@WebServlet("/pdfReport")
public class PdfServlet extends HttpServlet {

    private JasperPrint jasperPrint;

    @EJB
    private GradeRepositoryLocal gradeRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String reportFileName = "deneme.pdf";
            List<StudentGrade> grades = gradeRepository.findGradesForSemester("2025-2026");

            if (grades.isEmpty()) {
                // Handle case where no data is found
                return;
            }

            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(grades);
            Map<String, Object> inputParameters = new HashMap<>();
            inputParameters.put("Semester", "2025-2026");
            String reportFilePath = "/reports/grade_report.jrxml";
            InputStream jrxmlStream = getClass().getResourceAsStream(reportFilePath);
            if (jrxmlStream == null) {
                throw new FileNotFoundException("Rapor dosyası bulunamadı!");
            }
            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, inputParameters, beanCollectionDataSource);

            response.reset(); // Optional but safe
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "inline; filename=" + reportFileName);

            ServletOutputStream outputStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

            outputStream.flush();
            outputStream.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
