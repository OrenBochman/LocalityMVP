package com.bochman.localitymvp.domain;

import com.google.android.gms.maps.model.LatLng;

import org.parceler.Parcel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Parcel
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Search {

    int id;
    String keywords;
    long timestamp;
    LatLng latlng;

}
