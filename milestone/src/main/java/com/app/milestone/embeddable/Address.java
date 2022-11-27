package com.app.milestone.embeddable;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class Address {
    @NotNull
    private String schoolAddress;
    @NotNull
    private String schoolAddressDetail;
    @NotNull
    private String schoolZipcode;
}
