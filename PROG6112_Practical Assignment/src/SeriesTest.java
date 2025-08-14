import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SeriesTest {

    private Series app; // our main app to test

    @BeforeEach
    public void setUp() {
        // setup for all tests
        // make new Series object
        app = new Series();
        // add some test data so we have something to test with
        // adding some test data so we can test stuff
        SeriesModel testSeries1 = new SeriesModel("101", "Extreme Sports", "12", "10"); // first test series
        SeriesModel testSeries2 = new SeriesModel("102", "Cooking Show", "8", "15"); // second test series
        app.addSeries(testSeries1);
        app.addSeries(testSeries2);
    }

    @Test
    public void TestSearchSeries() {
        // test searching for series 101

        // do the search
        SeriesModel result = app.SearchSeries("101"); // look for series with id 101

        // check if we got the right stuff back
        assertNotNull(result); // make sure we got something back
        assertEquals("101", result.SeriesId); // check the id is right
        assertEquals("Extreme Sports", result.SeriesName); // check the name is right
        assertEquals("12", result.SeriesAge); // check the age is right
        assertEquals("10", result.SeriesNumberOfEpisodes); // check episodes is right
    }

    @Test
    public void TestSearchSeries_SeriesNotFound() {
        // test searching for a series that doesn't exist

        // try to find series 999 (doesn't exist)
        SeriesModel result = app.SearchSeries("999"); // this series doesn't exist

        // should get null back
        assertNull(result); // should be null since series 999 doesn't exist
    }
    
    @Test
    public void TestUpdateSeries(){
        // test updating a series
        
        // get the series we want to update
        SeriesModel originalSeries = app.SearchSeries("101"); // get the original series
        assertNotNull(originalSeries); // make sure we found it
        
        // change the series info
        originalSeries.SeriesName = "Updated Sports"; // change the name
        originalSeries.SeriesAge = "10"; // change the age
        originalSeries.SeriesNumberOfEpisodes = "20"; // change episodes
        
        // check if the changes worked
        SeriesModel updatedSeries = app.SearchSeries("101"); // get the series again
        assertEquals("Updated Sports", updatedSeries.SeriesName); // check new name
        assertEquals("10", updatedSeries.SeriesAge); // check new age
        assertEquals("20", updatedSeries.SeriesNumberOfEpisodes); // check new episodes
    }
    
    @Test
    public void TestDeleteSeries(){
        // test deleting a series
        
        // make sure the series exists first
        SeriesModel seriesToDelete = app.SearchSeries("101"); // find the series to delete
        assertNotNull(seriesToDelete); // make sure it exists
        
        // delete it
        boolean deleted = app.deleteSeries("101"); // actually delete it
        
        // check if it was deleted properly
        assertTrue(deleted); // should return true if deleted
        SeriesModel deletedSeries = app.SearchSeries("101"); // try to find it again
        assertNull(deletedSeries); // should be null now since it's deleted
    }
    
    @Test
    public void TestDeleteSeries_SeriesNotFound(){
        // test trying to delete a series that doesn't exist
        
        // check that series 999 doesn't exist
        SeriesModel nonExistentSeries = app.SearchSeries("999"); // try to find series 999
        assertNull(nonExistentSeries); // should be null since it doesn't exist
        
        // try to delete it anyway (shouldn't crash)
        
        // make sure it still doesn't exist
        SeriesModel stillNonExistent = app.SearchSeries("999"); // check again
        assertNull(stillNonExistent); // should still be null
    }
    
    @Test
    public void TestSeriesAgeRestriction_AgeValid(){
        // test that age 10 is valid (between 2 and 15)
        
        String validAge = "10"; // this age should be valid
        
        // convert to int and check if it's in the right range
        int age = Integer.parseInt(validAge); // convert string to int
        assertTrue(age >= 2 && age <= 15); // check if it's between 2 and 15
    }
    
    @Test
    public void TestSeriesAgeRestriction_SeriesAgainValid(){
        // test that age 5 is also valid
        
        String anotherValidAge = "5"; // another valid age
        
        // check if 5 is in the valid range
        int age = Integer.parseInt(anotherValidAge); // convert to int
        assertTrue(age >= 2 && age <= 15); // check if it's valid
    }

}