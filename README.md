# LocalityMVP

## Requirements

1 Adaptive UI
   * Tablet support for Master/Detail
   * Handset support for seperate master detail activity
2 Connectivity 
   * access Google Places API
   * work offline.
3 ActionBar
4 Settings available in all activities.
5 Recycler
   1 place details.
      1 name.
      2 address.
      3 distance.
      4 image of the place.
   2 long click context menu
     * share
     * save to favourites (replaced by clickable star)
6 Search
    * using free text
    * using current location


 # Domain
 
 ## Entities
 
 Place
 Search
 Favourite
  
 ## UseCases:  UseCase<IRequest,IResponse>;
   
 1. AdjustSetting<IRequest<key,value>,IResponse<Observable<Settings>>> settings have defaults
     a. SettingsSetRadius<IRequest<int>,IResponse<Observable>>          - interactive 
     b. SettingsSetUnit<IRequest<boolean>,IResponse<Observable>>        - interactive
     c. SettingsSetLocation<IRequest<LatLng>,IResponse<Observable>>     - not interactive (all the rest)
     d. SettingLastSearch<IRequest<Search>,IResponse<Observable>>
     e. SettingBatteryState<IRequest<Search>,IResponse<Observable>>
     f. SettingConnectivityState<IRequest<Search>,IResponse<Observable>>
 2. FindPlacesInRadius<IRequest<Search>,IResponse<List<Place>>          - keywords are optional, repo flushed on new data. 
 3. PlaceMarkerOnMap<IRequest<Place>,IResponse<Observable<MapState>>
 4. SharePlaceWithContacts<IRequest<Contact,Place>,IResponse<Single<Boolean>>>
 5. AddPlaceToFavourites<IRequest<Place>,IResponse<Observable<Favourites>>>
 6. RemoveAllFavourites<<IRequest<>,IResponse<Observable<Favourites>>>
 7. CalculateDistance<IRequest<Location,Place>,IResponse<Observable<Places>>>

## Storage

 * Place has both junit and instrumented junit tests (parcelable etc).


* Settings - via SharePreferences
* Places - repo encapsulating DB & API
* favourites DB only 
* last search  - via SharePreferences
    
## Testing:
 
## Patterns

* MVP - planned!
* Builder used in Place for fluent creation 

## Special Features:

* TDD style development
