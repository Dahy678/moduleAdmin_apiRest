package com.fiuni.administrador.dto.colegio;



import com.fiuni.administrador.dto.base.BaseDto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "COLEGIO")
public class ColegioDto extends BaseDto {

    private String nombreColegio;

    private Boolean estadoColegio = false;



    public void setEstadoColegio(Boolean estadoColegio) {
        this.estadoColegio = estadoColegio;
    }

    @XmlElement
    public Boolean getEstadoColegio() {
        return estadoColegio;
    }

    @XmlElement
    public String getNombreColegio() {
        return nombreColegio;
    }

    public void setNombreColegio(String nombreColegio) {
        this.nombreColegio = nombreColegio;
    }


}