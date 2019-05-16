package pl.edu.agh.mwo;

import java.util.*;

public class TripManager {
	private HashMap<String, Trip> tripList;

	public TripManager() {
		tripList = new HashMap<String, Trip>();
	}

	public void add(Trip trip) throws TripAlreadyExistsException {
		if (tripList.get(trip.getName()) != null) {
			throw new TripAlreadyExistsException();
		} else {
			tripList.put(trip.getName(), trip);
		}
	}

	public HashMap<String, Trip> tripFinder(String keywordForSearch) {

		HashMap<String, Trip> tripSearchResults = new HashMap();

		if (keywordForSearch.equals("")) {
			return tripList;
		}

		for (String name : tripList.keySet()) {

			if (name.toLowerCase().contains(keywordForSearch.toLowerCase())
					|| tripList.get(name).getDescription().toLowerCase().contains(keywordForSearch.toLowerCase())) {
				tripSearchResults.put(name, tripList.get(name));
				
			} else {
				
				List<Photo> photosFromTrip = tripList.get(name).getPhotos();
				for (Photo photo : photosFromTrip) {
					if (photo.getComment().toLowerCase().contains(keywordForSearch.toLowerCase())) {
						tripSearchResults.put(name, tripList.get(name));
					}
				}
			}

		}
		return tripSearchResults;

	}

	public HashMap<String, Trip> getTrips() {
		return tripList;
	}

	public void remove(String name) {
		tripList.remove(name);
	}

}
