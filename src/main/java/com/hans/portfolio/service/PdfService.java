package com.hans.portfolio.service;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

@Service
public class PdfService {

    private final TemplateEngine templateEngine;

    public PdfService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public byte[] generatePdfFromHtml(String templateName, Map<String, Object> data) throws IOException {
        // 1. Thymeleaf를 이용해 HTML 렌더링
        Context context = new Context();
        context.setVariables(data);
        String htmlContent = templateEngine.process(templateName, context);

        // 2. PDF 생성
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            
            // 한글 폰트 설정
            String fontPath = "src/main/resources/static/fonts/NanumGothic.ttf";
            renderer.getFontResolver().addFont(fontPath, "Identity-H", true);
            
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(outputStream);
            
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("PDF 생성 중 오류 발생: " + e.getMessage(), e);
        }
    }
}
