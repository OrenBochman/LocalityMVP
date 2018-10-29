package com.bochman.localitymvp.domain;

import android.support.annotation.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

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

    @NonNull
    @Override
    public String toString() {
        return name ;
    }
}
