import SwiftUI
import ComposeApp
import CoreLocation

var lm :LocationManager2?


@main
struct iOSApp: App {
   
    
  
   
    init() {
        KoinKt.doInitKoin()

        LocateKt.requestLocation = {
            lm = LocationManager2()
        }

    }

 
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}






class LocationManager2: NSObject, ObservableObject, CLLocationManagerDelegate {
    private var locationManager = CLLocationManager()

    

    override init() {
        super.init()
        self.locationManager.startUpdatingLocation()
        self.locationManager.delegate = self
        self.locationManager.requestWhenInUseAuthorization()
        self.locationManager.startUpdatingLocation()
        print("init")
    }

    func locationManagerDidPauseLocationUpdates(_ manager: CLLocationManager) {
        
    }
    
    func locationManager(_ manager: CLLocationManager, didUpdateHeading newHeading: CLHeading) {
    
    }

    
    func locationManagerDidChangeAuthorization(_ manager: CLLocationManager) {
        self.locationManager.startUpdatingLocation()
        print("locationManagerDidChangeAuthorization")
    }
    
    func locationManager(_ manager: CLLocationManager, didChangeAuthorization status: CLAuthorizationStatus) {
        self.locationManager.startUpdatingLocation()
        print("didChangeAuthorization")
    }
    
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        print("didUpdateLocations")
        print(locations.first!.coordinate.latitude)

        if(!locations.isEmpty)
        {
            let location=locations.first!
            LocateKt.locate( lt: locations.first!.coordinate.latitude, lg: locations.first!.coordinate.longitude,pn:"")

            let geocoder = CLGeocoder()
            geocoder.reverseGeocodeLocation(location) { (placemarks, error) in
            
              // Check for errors
              if let error = error {
                print("Error in reverse geocoding: \(error.localizedDescription)")
                return
              }

              guard let placemarks = placemarks, let placemark = placemarks.first else {
                print("No placemark found for location.")
                return
              }

              // Get the place name from the placemark
              let placeName = placemark.locality ?? "" // Locality is city or town name

              // You can also access other details like region, country etc. using placemark properties
              print("Place Name: \(placeName)")
                LocateKt.locate( lt: locations.first!.coordinate.latitude, lg: locations.first!.coordinate.longitude,pn:placeName)
                lm=nil
            }
        }
    }

    func locationManager(_ manager: CLLocationManager, didFailWithError error: Error) {
        print("didFailWithError")
    }
}






