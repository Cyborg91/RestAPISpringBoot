package com.sayhi.user.model;

import java.io.Serializable;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDasar<T>  implements Serializable {
    private boolean success = true;
    private String message = "";
    private T data=null;
}
