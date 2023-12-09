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

                    // Title
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20); // Larger font for title
                    contentStream.newLineAtOffset(10, 700);
                    contentStream.showText("Title: " + recipe.getTitle());

                    // Description
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 15); // Smaller font for description
                    contentStream.newLineAtOffset(10, -100);
                    contentStream.showText("Description: " + recipe.getDescription());

                    // Preparation
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 15); // Smaller font for preparation
                    contentStream.newLineAtOffset(10, -100);
                    contentStream.showText("Preparation: " + recipe.getPreparation());

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
