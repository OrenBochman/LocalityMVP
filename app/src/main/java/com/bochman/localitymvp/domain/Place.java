package com.bochman.localitymvp.domain;

import android.support.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import static org.apache.commons.lang3.StringUtils.substringBetween;

//import org.jetbrains.annotations.NotNull;

@Data
@Accessors(chain = true)
@AllArgsConstructor
public class Place {

    private String id;
    private String name;
    private String address;
    private Double lat;
    private Double lng;
    private String icon;

    //file storage
    private static final String ID_PREFIX   = "\"place_id\" : \"";
    private static final String ID_ADDRESS  = "\"vicinity\" : \"";
    private static final String ID_NAME     = "\"name\" : \"";
    private static final String ID_ICON     = "\"icon\" : \"";
    private static final String ID_LAT      = "\"lat\" : ";
    private static final String ID_LNG      = "\"lng\" : ";

    /**
     * for testing - load place from file..
     *
     * @param stream - stream to read the place from
     * @return the place
     */
    public static @NotNull
    Place fromStream(InputStream stream) {


        String id, name, address, icon;
        Double latitude, longitude;
        id = name = address = icon = null;
        latitude = longitude = 0.0;

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        try {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                if (line.startsWith(ID_PREFIX)) {
                    id = substringBetween(line, ID_PREFIX, "\",");
                }

                if (line.startsWith(ID_ADDRESS)) {
                    address = substringBetween(line, ID_ADDRESS, "\",");
                }

                if (line.startsWith(ID_NAME)) {
                    name = substringBetween(line, ID_NAME, "\",");
                }

                if (line.startsWith(ID_ICON)) {
                    icon = substringBetween(line, ID_ICON, "\",");
                }

                if (line.startsWith(ID_LAT)) {
                    latitude = Double.parseDouble(substringBetween(line, ID_LAT, ",").trim());
                }

                if (line.startsWith(ID_LNG)) {
                    longitude = Double.parseDouble(substringBetween(line, ID_LNG, ",").trim());
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return new Place(id, name, address, latitude, longitude, icon);
    }


    @NonNull
    @Override
    public String toString() {
        return name ;
    }
}
