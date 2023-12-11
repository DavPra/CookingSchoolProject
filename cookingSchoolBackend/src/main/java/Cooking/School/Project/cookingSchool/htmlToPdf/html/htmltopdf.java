package Cooking.School.Project.cookingSchool.htmlToPdf.html;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class htmltopdf {

    public static void htmltopdfconverter() throws IOException {
        // IO
        File htmlSource = new File("C:\\Users\\pm-user61\\OneDrive\\Desktop\\Rechnung.html");
        File pdfDest = new File("C:\\Users\\pm-user61\\OneDrive\\Desktop\\Rechnung.pdf");
        // pdfHTML specific code
        ConverterProperties converterProperties = new ConverterProperties();
        HtmlConverter.convertToPdf(new FileInputStream(htmlSource),
                new FileOutputStream(pdfDest), converterProperties);

    }

}
