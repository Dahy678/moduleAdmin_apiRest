package com.fiuni.administrador.dto.base;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;

public abstract class BaseDto implements Serializable{

    private static final long serialVersionUID = 1L;
    private Integer _id;

    @XmlElement
    public Integer getId() {
        return _id;
    }

    public void setId(Integer id) {
        _id = id;
    }

}
