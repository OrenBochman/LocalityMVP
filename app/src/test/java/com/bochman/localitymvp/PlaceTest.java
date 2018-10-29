package com.bochman.localitymvp;

import com.bochman.localitymvp.domain.Place;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class PlaceTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }


    private Place tlv;
    private String id, name, address, icon;
    private Double lat, lng;

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

    @Test
    public void toStringTest() {
        assertThat(tlv.toString(),notNullValue());
        assertThat(tlv.toString(), is(name));
    }

    @Test
    public void StreamBasedLoading_TLV() {
        InputStream stream = PlaceTest.class.getResourceAsStream("/places/TelAviv.txt");
        Place place = Place.fromStream(stream);
        assertThat(place,notNullValue());
        assertThat(id, is(tlv.getId()));
        assertThat(lat, is(tlv.getLat()));
        assertThat(lng, is(tlv.getLng()));
        assertThat(icon, is(tlv.getIcon()));
        assertThat(address, is(tlv.getAddress()));
        assertThat(name, is(tlv.getName()));
    }


}