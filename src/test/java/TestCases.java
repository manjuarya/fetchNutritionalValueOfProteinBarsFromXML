import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestCases {
    @Test
    public void testCase1() throws ParserConfigurationException, SAXException, ParseException, IOException {
        Products newProduct = new Products();
        ArrayList<ProteinBar> proteinBarsList = newProduct.accessFileToClass();

        int result = proteinBarsList.size();
        assertEquals(500, result);
    }

    @Test
    public void testCase3() throws ParserConfigurationException, SAXException, ParseException, IOException {
        Products newProduct = new Products();
        ArrayList<ProteinBar> proteinBarsList = newProduct.accessFileToClass();

        double minFiberContent = 30;

        Filter filter = new MinimumFiberContent(minFiberContent);
        ArrayList<ProteinBar> proteinBarListAfterFilter = newProduct.filter ( proteinBarsList, filter);

        int result = proteinBarListAfterFilter.size();
        assertEquals(192, result);
    }

    @Test
    public void testCase4() throws ParserConfigurationException, SAXException, ParseException, IOException {
        Products newProduct = new Products();
        ArrayList<ProteinBar> proteinBarsList = newProduct.accessFileToClass();

        double minProteinContent = 30;
        String reviewerId = "gha";

        // Filter the protein bar list according to minimum protein content
        Filter proteinFilter = new MinimumProteinContent(minProteinContent);
        ArrayList<ProteinBar> proteinBarListAfterFilter = newProduct.filter ( proteinBarsList, proteinFilter);

        // Filter the protein bars list as per the reviewer reviewerId
        ArrayList<ProteinBar> proteinBarListOfReviewer = new ArrayList<ProteinBar>();
        for(ProteinBar bar : proteinBarListAfterFilter) {
            ArrayList<Review> reviewList = new ArrayList<Review>();
            reviewList = bar.getReviews();
            for(Review review : reviewList) {
                if(review.getReviewerId().equals(reviewerId)){
                    proteinBarListOfReviewer.add(bar);
                }
            }
        }

        int result = proteinBarListOfReviewer.size();
        assertEquals(1, result);
    }
}
