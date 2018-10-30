package com.bochman.localitymvp.domain;

import org.parceler.Parcel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Parcel
@Data
@Accessors(chain = true)
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
public class Favourite extends Place {

    int isFavourite=1;
}
