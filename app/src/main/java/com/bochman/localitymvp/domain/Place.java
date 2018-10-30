package com.bochman.localitymvp.domain;

import android.content.ContentValues;
import android.provider.BaseColumns;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import static com.bochman.localitymvp.domain.Place.IConstants.COL_ADDRESS;
import static com.bochman.localitymvp.domain.Place.IConstants.COL_ICON;
import static com.bochman.localitymvp.domain.Place.IConstants.COL_ID;
import static com.bochman.localitymvp.domain.Place.IConstants.COL_LATITUDE;
import static com.bochman.localitymvp.domain.Place.IConstants.COL_LONGITUDE;
import static com.bochman.localitymvp.domain.Place.IConstants.COL_NAME;
import static org.apache.commons.lang3.StringUtils.substringBetween;

/**
 * Place is a room entity representing a place on a map.
 * It needs to be passed via intents hence parcelable.
 * It is shared via a content provider hence fromContentValues,
 * It can be read from a file hence from stream.
 * <p>
 * Android annotations have been used to reduce the boiler plate.
 * Using an annotation for generate the parcelable interface
 * http://github.com/androidannotations/androidannotations/wiki/ParcelerIntegration
 */
@Parcel
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Place implements IPlace {

    public interface IConstants {
        //// Table Name ////////////////////////////////////////////////////////////////////////////////
        /**
         * The name of the places Table.
         */
        String PLACES_TABLE = "places";

        /**
         * The name of the TERM column.
         */
        String COL_TERM = "term";

        /**
         * The name of the DATE column.
         */
        String COL_DATE = "date";

        /**
         * The name of the ID column.
         */
        String COL_ID = BaseColumns._ID;
        /**
         * The name of the name column.
         */
        String COL_NAME = "name";
        /**
         * The name of the address column.
         */
        String COL_ADDRESS = "address";
        /**
         * The name of the latitude column.
         */
        String COL_LATITUDE = "latitude";
        /**
         * The name of the longitude column.
         */
        String COL_LONGITUDE = "longitude";
        /**
         * The name of the icon column.
         */
        String COL_ICON = "icon";
        /**
         * The name of the isFavourite column.
         */
        String COL_FAV = "isFavourite";
    }

    /**
     * The unique ID of the place.
     */
    @ColumnInfo(index = true, name = COL_ID)
    @PrimaryKey
    @NonNull
    private String id;

    /**
     * The name of the place.
     */
    @ColumnInfo(name = COL_NAME)
    private String name;

    /**
     * The  address of the place.
     */
    @ColumnInfo(name = COL_ADDRESS)
    private String address;

    /**
     * The  latitude of the place.
     */
    @ColumnInfo(name = COL_LATITUDE)
    private Double lat;

    /**
     * The  longitude of the place.
     */
    @ColumnInfo(name = COL_LONGITUDE)
    private Double lng;

    /**
     * The icon of the place.
     */
    @ColumnInfo(name = COL_ICON)
    private String icon;

    /**
     * distance in meters from location
     *
     * @param location - another location
     * @return
     */
    public double distanceFrom(LatLng location) {

        return Math.round(
                SphericalUtil.computeDistanceBetween(this.getLatLng(), location));
    }

    /**
     * distance in meters from location
     *
     * @param place - another place
     * @return
     */
    public double distanceFrom(Place place) {

        return Math.round(
                SphericalUtil.computeDistanceBetween(this.getLatLng(), place.getLatLng()));
    }

    /**
     * @return
     */
    public LatLng getLatLng() {

        return new LatLng(getLat(), getLng());
    }

    /*
     * TODO : remove to string once ListAdapter does not rely on to String. -
     * @Data annotatinos provides full implementation.
     *
     */
    @NonNull
    @Override
    public String toString() {
        return name;
    }


    /**
     * Load place from file..
     * TODO: save place to file..
     *
     * @param stream - stream to read the place from
     * @return the place
     */
    public static @NotNull
    Place fromStream(InputStream stream) {

        //file storage constants
        String ID_PREFIX = "\"place_id\" : \"";
        String ID_ADDRESS = "\"vicinity\" : \"";
        String ID_NAME = "\"name\" : \"";
        String ID_ICON = "\"icon\" : \"";
        String ID_LAT = "\"lat\" : ";
        String ID_LNG = "\"lng\" : ";


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


    /**
     * Create a new {@link Place} from the specified {@link ContentValues}.
     *
     * @param values A {@link ContentValues} that at least contain {@link IConstants#COL_NAME}.
     * @return A newly created {@link Place} instance.
     */
    public static Place fromContentValues(ContentValues values) {

        if (null == values)
            throw new IllegalArgumentException("for values");

        return new Place()
                .setId(values.getAsString(COL_ID))
                .setName(values.getAsString(COL_NAME))
                .setAddress(values.getAsString(COL_ADDRESS))
                .setLat(values.getAsDouble(COL_LATITUDE))
                .setLng(values.getAsDouble(COL_LONGITUDE))
                .setIcon(values.getAsString(COL_ICON));
    }

/*

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.address);
        dest.writeValue(this.lat);
        dest.writeValue(this.lng);
        dest.writeString(this.icon);
    }

    protected Place(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.address = in.readString();
        this.lat = (Double) in.readValue(Double.class.getClassLoader());
        this.lng = (Double) in.readValue(Double.class.getClassLoader());
        this.icon = in.readString();
    }

    public static final Parcelable.Creator<Place> CREATOR = new Parcelable.Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel source) {
            return new Place(source);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };

    */
}
