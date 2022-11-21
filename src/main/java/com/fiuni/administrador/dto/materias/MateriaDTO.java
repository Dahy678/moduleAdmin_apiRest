package com.fiuni.administrador.dto.materias;


import com.fiuni.administrador.dto.base.BaseDto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "MATERIAS")
public class MateriaDTO extends BaseDto {

    private String  nombre;

    private Boolean estado;


    @XmlElement
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlElement
    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }


}
