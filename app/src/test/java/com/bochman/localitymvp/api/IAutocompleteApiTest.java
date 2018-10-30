package com.bochman.localitymvp.api;

import com.bochman.localitymvp.api.NearBy.INearByApi;
import com.bochman.localitymvp.api.NearBy.pojos.PlaceSearchResults;
import com.bochman.localitymvp.domain.Place;
import com.bochman.localitymvp.domain.PlaceTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class IAutocompleteApiTest {

    Retrofit retrofit;
    INearByApi service;
    Place sm;
    String radius="10000";

    @Before
    public void setup() {
        InputStream stream = PlaceTest.class.getResourceAsStream("/places/SaronaMarket.txt");
        sm = Place.fromStream(stream);
    }

    @After
    public void cleanUp() {
        retrofit = null;
        service = null;
    }

    @Test
    public void getNearbyResults() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .build();

        retrofit = new Retrofit
                .Builder()
                .baseUrl(INearByApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        service = retrofit.create(INearByApi.class);

        final Call<PlaceSearchResults> details = service.
                getDetails(
                        sm.getLatLngString(), //"32.0714008,34.786942",
                        1000,
                        "",
                        "",
                        INearByApi.API_KEY);

        assertThat(sm.getLatLngString()).isEqualToIgnoringCase("32.0714008,34.7869421");

    }


    @Test
    public void Classic_RxTest(){

        List<String> letters = Arrays.asList("A", "B", "C", "D", "E");
        List<String> results = new ArrayList<>();

        Observable<String> observable = Observable
                .fromIterable(letters)
                .zipWith(
                        Observable.range(1, Integer.MAX_VALUE),
                        (string, index) -> index + "-" + string);

        observable.subscribe(results::add);

        assertThat(results).isNotNull();
        assertThat(results).hasSize(5);
        assertThat(results).contains("1-A", "2-B", "3-C", "4-D", "5-E");
    }
/*
    @Test
    public void TextSubscriber_RxTest(){

        List<String> letters = Arrays.asList("A", "B", "C", "D", "E");
        TestSubscriber<String> subscriber = new TestSubscriber<>();

        Observable<String> observable = Observable
                .fromIterable(letters)
                .zipWith(
                        Observable.range(1, Integer.MAX_VALUE),
                        ((string, index) -> index + "-" + string));

        observable.subscribe((Observer<? super String>) subscriber);

        subscriber.assertComplete();
        subscriber.assertNoErrors();
        subscriber.assertValueCount(5);
        assertThat(subscriber.getOnNextEvent(), hasItems("1-A", "2-B", "3-C", "4-D", "5-E"));

    }
*/
//    TestSubscriber<Integer> ts = Flowable.range(1, 5).test();
//    TestObserver<Integer> to = Observable.range(1, 5).test();
//    TestObserver<Integer> tso = Single.just(1).test();
//    TestObserver<Integer> tmo = Maybe.just(1).test();
 //   TestObserver<Integer> tco = Completable.complete().test();


   // Flowable can be tested with
   // io.reactivex.subscribers.TestSubscriber.
   // Non-backpressured Observable Single, Maybe and Completable can be tested with
   // io.reactivex.observers.TestObserver.
    @Test
    public void anObservableStreamOfEventsAndDataShouldEmitsEachItemInOrder() {

        Observable<String> pipelineOfData = Observable.just("Foo", "Bar");

        TestObserver<String> testObserver = pipelineOfData.test();

        pipelineOfData.subscribe(testObserver);

        List<String> dataEmitted = testObserver.values();
        assertThat(dataEmitted).hasSize(2);
        assertThat(dataEmitted).containsOnlyOnce("Foo");
        assertThat(dataEmitted).containsOnlyOnce("Bar");
    }
}
