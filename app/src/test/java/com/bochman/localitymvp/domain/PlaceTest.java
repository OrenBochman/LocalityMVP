package com.bochman.localitymvp.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;


@SuppressWarnings("RedundantThrows")
public class PlaceTest {

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
        tlv = new Place(id, name, address, lat, lng, icon);
    }

    @After
    public void tearDown() throws Exception {
        tlv = null;
    }

    //@AfterClass public void opstTearDown() throws Exception { }


    @Test
    public void toStringTest() {
        assertThat(tlv.toString(), notNullValue());
        assertThat(tlv.toString(), is(name));
    }

    @Test
    public void StreamBasedLoading_TLV() {
        InputStream stream = PlaceTest.class.getResourceAsStream("/places/TelAviv.txt");
        Place place = Place.fromStream(stream);
        assertThat(place, notNullValue());
        assertThat(id, is(tlv.getId()));
        assertThat(lat, is(tlv.getLat()));
        assertThat(lng, is(tlv.getLng()));
        assertThat(icon, is(tlv.getIcon()));
        assertThat(address, is(tlv.getAddress()));
        assertThat(name, is(tlv.getName()));
    }

    @Test
    public void StreamBasedLoading_SARONA() {
        InputStream stream = PlaceTest.class.getResourceAsStream("/places/SaronaMarket.txt");
        Place place = Place.fromStream(stream);
        assertNotNull(place);
        assertThat(place, notNullValue());
        assertThat(id, is(tlv.getId()));
        assertThat(place.getId(), is("ChIJu_9NFJ1LHRURqnCPWAarsj0"));
        assertThat(place.getLat(), is(32.0714008));
        assertThat(place.getLng(), is(34.7869421));
        assertThat(place.getIcon(),is("https://maps.gstatic.com/mapfiles/place_api/icons/shopping-71.png"));
        assertThat(place.getAddress(),is("Aluf Kalman Magen St 3, Tel Aviv-Yafo"));
        assertThat(place.getName(),is("Sarona Market"));
    }

    @Test
    public void DistanceBetween_JB_AND_SARONA(){

        InputStream stream1 = PlaceTest.class.getResourceAsStream("/places/SaronaMarket.txt");
        Place place1 = Place.fromStream(stream1);

        InputStream stream2 = PlaceTest.class.getResourceAsStream("/places/JohnBryce.txt");
        Place place2 = Place.fromStream(stream2);

        assertThat(place1.distanceFrom(place2.getLatLng()),is(820.0));

    }


}