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
     
        if(!locations.isEmpty)
        {
            LocateKt.locate( lt: locations.first!.coordinate.latitude, lg: locations.first!.coordinate.longitude)
        }
    }

    func locationManager(_ manager: CLLocationManager, didFailWithError error: Error) {
        print("didFailWithError")
    }
}






