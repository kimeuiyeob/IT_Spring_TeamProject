package com.app.milestone.embeddable;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class Introduce {
   private String schoolTitle;
   private String schoolContent;
}
