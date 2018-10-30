package com.bochman.localitymvp.domain;

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
public class Favourite extends Place {

    int isFavourite=1;
}
