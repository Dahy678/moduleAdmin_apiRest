package com.fiuni.administrador.dto.horaCatedra;


import com.fiuni.administrador.dto.base.BaseDto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalTime;

@XmlRootElement(name = "HORAS_CATEDRAS")
public class HoraCatedraDto extends BaseDto {

    private LocalTime inicio;

    private LocalTime fin;

    private Boolean estado;


    @XmlElement
    public LocalTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalTime inicio) {
        this.inicio = inicio;
    }

    @XmlElement
    public LocalTime getFin() {
        return fin;
    }

    public void setFin(LocalTime fin) {
        this.fin = fin;
    }

    @XmlElement
    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }


}