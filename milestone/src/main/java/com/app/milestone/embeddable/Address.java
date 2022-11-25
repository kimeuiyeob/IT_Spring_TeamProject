package com.app.milestone.embeddable;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class Address {
    private String schoolAddress;
    private String schoolAddressDetail;
    private String schoolZipcode;
}
