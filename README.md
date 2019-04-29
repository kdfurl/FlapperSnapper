# FlapperSnapper
A simple Android app for bird watchers to log their sightings.

The main function of the app is to take a photo of a bird and enter a few basic details about it: what species it is, whether it's male or female, young or old. The photos are displayed in a list view on the home screen along with the details entered about it. When the user selects an item, it launches the edit activity. The user can modify the details about the bird, but at this point in time they cannot modify the photo. There is an option to delete every bird entry in the menu but there is no option to delete individual items yet.

The data is stored in a Realm database on the device.<br><br>

<b>Core Improvements in Assignment 2:</b>
<ul>
  <li><b>Google Sign-In and Multi-User Support</b></li>
  <p>Users can sign in with their Google account. Entries to the database now include the user's Google ID so they only see their own personal content when they sign in. They can stay signed in after they close the app and it will automatically sign them in to the home screen when they reopen it. There is an option to sign out, which won't affect the user's data, and an option to disconnect their account, which will remove all data associated with that account from the database. The 'clear all' option in the menu will only delete data associated with the currently signed in user.</p>
  
  <li><b>Google Maps and Location Awareness</b></li>
  <p> When the user takes a photo their location data is used to place a marker on the map. When clicked on, the marker displays an info window with the details about the bird and the address of the location where the picture was taken. Users only see their own markers on the map. The map gets the user's location when opened and sets the map accordingly. The map is displayed using a fragment.</li>
  
  <li><b>Implementation of Navigation Drawer</b></li>
  <p>The app now has a navigatio drawer that contains options to log a sighting (take a photo), open the map view, or return to the home screen. The user's Google profile picture, display name and email address are displayed at the top of the navigation bar.</p>
  
  <li><b>Updated Data Model and Database Methods</b></li>
  <p>The bird data model was updated to include the user ID and location data. The database methods were updated to use the user ID where appropriate to filter results and operations in line with the multi-user support.</p>
  
  <li><b>Minor Bug Fixes and Code Improvements</b></li>
  </ul>
  
  <br><h4>Link to YouTube demo:</h4> https://youtu.be/3_eVzywYI2g<br>
  
  #### References
  <ul>
  <li>https://ddrohan.github.io/</li>
  <li>https://developers.google.com/identity/sign-in/android/start-integrating</li>
  <li>https://developer.android.com/training/camera/photobasics</li>
  <li>https://developer.android.com/guide/components/fragments</li>
  <li>https://developer.android.com/training/location/display-address</li>
  <li>https://androidclarified.com/google-signin-android-example/</li>
  <li>https://androidclarified.com/pick-image-gallery-camera-android/</li>
  <li>https://www.javatpoint.com/android-googlesignin-integrating</li>
  </ul>
