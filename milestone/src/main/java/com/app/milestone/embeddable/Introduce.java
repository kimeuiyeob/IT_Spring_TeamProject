package com.app.milestone.embeddable;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
@Getter
@Setter
public class Introduce {
   private String schoolTitle;
   @Size(max = 2000)
   private String schoolContent;
}
