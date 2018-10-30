package com.bochman.localitymvp.domain;

import android.content.ContentValues;
import android.os.Parcelable;

import com.bochman.localitymvp.domain.Place.IConstants;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.parceler.Parcels;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@SuppressWarnings("RedundantThrows")
public class PlaceTestIns {

    private Place tlv;
    private String id, name, address, icon;
    private Double lat, lng;

    // @BeforeClass public void preSetup() { }

    @Before
    public void setup() {

        //tlv place data
        id = "ChIJH3w7GaZMHRURkD-WwKJy-8E";
        name = "Tel Aviv-Yafo";
        address = "Tel Aviv-Yafo, Israel";
        icon = "https://maps.gstatic.com/mapfiles/place_api/icons/geocode-71.png";
        lat = 32.0852999d;
        lng = 34.7817676;
        // tlv = new Place(id, name, address, lat, lng, icon);
        tlv = new Place(id, name, address, lat, lng , icon);
    }

    @After
    public void tearDown() throws Exception {
        tlv=null;
    }

    @Test
    public void test_place_is_parcelable() {

        Parcelable parcel = Parcels.wrap(tlv);
        Place createdFromParcel = Parcels.unwrap(parcel);

        assertThat(createdFromParcel.getId(), is(id));
        assertThat(createdFromParcel.getName(), is(name));
        assertThat(createdFromParcel.getIcon(), is(icon));
        assertThat(createdFromParcel.getAddress(), is(address));
        assertThat(createdFromParcel.getLat(), is(lat));
        assertThat(createdFromParcel.getLng(), is(lng));
    }

    @Test
    public void test_place_creation_from_good_ContentValues() {

        ContentValues values = new ContentValues();

        values.put(IConstants.COL_ID,id);
        values.put(IConstants.COL_NAME,name);
        values.put(IConstants.COL_ADDRESS,address);
        values.put(IConstants.COL_ICON,icon);
        values.put(IConstants.COL_LATITUDE,lat);
        values.put(IConstants.COL_LONGITUDE,lng);

        Place place = Place.fromContentValues(values);

        assertThat(place,is(tlv));
    }

    @Test
    public void test_place_creation_from_bad_ContentValues() {

        ContentValues values = new ContentValues();

        values.put(IConstants.COL_ID,id);
        values.put(IConstants.COL_NAME,name);
        values.put(IConstants.COL_ADDRESS,address);
        values.put(IConstants.COL_ICON,icon);
        values.put(IConstants.COL_LATITUDE,lat);
        values.put(IConstants.COL_LONGITUDE,lng);

        Place place = Place.fromContentValues(values);

        assertThat(place,is(notNullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_place_creation_from_null_ContentValues() {

        ContentValues values = null;

        Place place = Place.fromContentValues(values);

        assertThat(place,is(notNullValue()));
    }

}