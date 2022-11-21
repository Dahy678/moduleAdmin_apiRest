package com.fiuni.administrador.dto.ciclo;


import com.fiuni.administrador.dto.base.BaseDto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CICLOS")
public class CicloDto extends BaseDto {

    private String descripcion;

    private Boolean estado = false;


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @XmlElement
    public Boolean getEstado() {
        return estado;
    }

    @XmlElement
    public String getDescripcion() {
        return descripcion;
    }

}

