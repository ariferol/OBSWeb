/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.obs.web.mbeans;

/**
 *
 * @author arif.erol
 */
import com.obs.dao.GradeRepositoryLocal;
import com.obs.model.StudentGrade;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import lombok.Getter;
import lombok.Setter;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

@Named(value = "reportBean")
@RequestScoped
@Getter
@Setter
public class ReportBean {

    private byte[] reportContent;
    private JasperPrint jasperPrint;

    @EJB
    private GradeRepositoryLocal gradeRepository;

//    public void generateReport() {
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        ExternalContext externalContext = facesContext.getExternalContext();
//        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
//
//        try {
//            String reportFileName = "deneme.pdf";
//            List<StudentGrade> grades = gradeRepository.findGradesForSemester("2025-2026");
//
//            if (grades.isEmpty()) {
//                // Handle case where no data is found
//                return;
//            }
//
//            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(grades);
//            Map<String, Object> inputParameters = new HashMap<>();
//            inputParameters.put("Semester", "2025-2026");
////            ClassLoader classLoader = this.getClass().getClassLoader();
//            String reportFilePath = "/reports/grade_report.jrxml";
//            InputStream jrxmlStream = getClass().getResourceAsStream(reportFilePath);
//            if (jrxmlStream == null) {
//                throw new FileNotFoundException("Rapor dosyası bulunamadı!");
//            }
//            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlStream);
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, inputParameters, beanCollectionDataSource);
//            
//            response.reset(); // Optional but safe
//            response.setContentType("application/pdf");
//            response.setHeader("Content-disposition", "inline; filename=" + reportFileName);
//
//            ServletOutputStream outputStream = response.getOutputStream();
//            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
//
//            outputStream.flush();
//            outputStream.close();
//
//            // 4. JSF'e view rendering yapmaması gerektiğini söyle
//            facesContext.responseComplete();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
////            isLoading = false;
//        }
//    }

}
