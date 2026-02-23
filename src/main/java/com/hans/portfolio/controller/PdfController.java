package com.hans.portfolio.controller;

import com.hans.portfolio.service.PdfService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PdfController {

    private final PdfService pdfService;

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @GetMapping("/download-pdf")
    public ResponseEntity<byte[]> downloadPdf() throws IOException {
        // PDF에 전달할 데이터 (필요한 경우)
        Map<String, Object> data = new HashMap<>();
        data.put("title", "My Portfolio Resume");
        data.put("isPdf", true);

        // PDF 생성 (templates/index.html 사용)
        byte[] pdfBytes = pdfService.generatePdfFromHtml("index", data);

        // 파일 다운로드 응답 설정
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=portfolio_resume.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}
