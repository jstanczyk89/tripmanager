package pl.edu.agh.mwo;
import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

public class TripManagerTest {

	TripManager tripManager;
	Trip trip, trip2;
	Photo photo1, photo2;
	
	@Before
	public void setUp() {
		tripManager = new TripManager();
		trip = new Trip("nazwa", "opis");
		trip2 = new Trip("Super wycieszka MWO", "Cwiczenia TRAVIS i MAVEN");
		trip2.setName("Super wycieszka MWO2");  // only for codecov badge
		trip2.setDescription("Cwiczenia2 TRAVIS i MAVEN"); // only for codecov badge
		photo1 = new Photo();
		photo1.setComment("TRAVIS jest super");
		photo2 = new Photo();
		photo2.setComment("MAVEN jest jeszcze lepszy");
		trip2.addPhoto(photo1);
		trip2.addPhoto(photo2);
	}
	
	@Test
	public void testAdd() throws TripAlreadyExistsException {
		assertEquals(0, tripManager.getTrips().size());
		tripManager.add(trip);
		assertEquals(1, tripManager.getTrips().size());
	}

	@Test(expected = TripAlreadyExistsException.class)
	public void testAddTripTwice() throws TripAlreadyExistsException {
		assertEquals(0, tripManager.getTrips().size());
		tripManager.add(trip);
		assertEquals(1, tripManager.getTrips().size());
		tripManager.add(trip);
	}

	@Test
	public void testRemoveTrip() throws Exception {
		tripManager.add(trip);
		assertEquals(1, tripManager.getTrips().size());
		tripManager.remove(trip.getName());
		assertEquals(0, tripManager.getTrips().size());
	//	fail("chcemy zespuc");
		}
	
	@Test
	public void testTripFinderEmptyString()throws Exception{
		tripManager.add(trip);
		tripManager.add(trip2);
		assertEquals(2, tripManager.tripFinder("").size());
		
	}
	
	@Test
	public void testTripFinderFindByName()throws Exception{
		tripManager.add(trip);
		tripManager.add(trip2);
		assertEquals(1, tripManager.tripFinder("SuPeR").size());
		assertEquals(1, tripManager.tripFinder("NaZwA").size());
		assertEquals(0, tripManager.tripFinder("brak").size());
		
	}
	
	@Test
	public void testTripFinderFindByDescription()throws Exception{
		tripManager.add(trip);
		tripManager.add(trip2);
		assertEquals(1, tripManager.tripFinder("travis").size());
		assertEquals(1, tripManager.tripFinder("maven").size());
		assertEquals(0, tripManager.tripFinder("brak").size());
		
	}
	
	@Test
	public void testTripFinderFindByPhotoComment()throws Exception{
		tripManager.add(trip);
		tripManager.add(trip2);
		assertEquals(1, tripManager.tripFinder("lePszy").size());
		assertEquals(1, tripManager.tripFinder("sUper").size());
		assertEquals(0, tripManager.tripFinder("brak").size());
		
	}
	
	
}
