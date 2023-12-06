package Cooking.School.Project.cookingSchool.Services;

import Cooking.School.Project.cookingSchool.entities.Recipe;
import Cooking.School.Project.cookingSchool.repository.RecipeRepository;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DownloadService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public DownloadService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public void createPDF(Long id) throws IOException {
        Recipe recipe = recipeRepository.findById(id).orElse(null);

        if (recipe != null) {
            try (PDDocument document = new PDDocument()) {
                PDPage page = new PDPage();
                document.addPage(page);

                try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

                    float yPosition = 700; // Initial y-coordinate

                    // Title
                    contentStream.newLineAtOffset(10, 0);
                    contentStream.showText("Title: " + recipe.getTitle());
                    yPosition -= 15; // Adjust for the next line

                    // Description
                    contentStream.newLineAtOffset(10, 50);
                    contentStream.showText("Description: " + recipe.getDescription());
                    yPosition -= 20; // Adjust for the next line

                    // Preparation
                    contentStream.newLineAtOffset(10, 150);
                    contentStream.showText("Preparation: " + recipe.getPreparation());
                    yPosition -= 30; // Adjust for the next line

                    contentStream.endText();
                }

                // Save the document with a unique name, such as using the recipe title or ID
                String fileName = "recipe_" + id + ".pdf";
                document.save(fileName);
                System.out.println("PDF created successfully.");
            } catch (IOException e) {
                System.err.println("Error creating the PDF: " + e.getMessage());
            }
        }
    }


}
