package com.fiuni.administrador.dto.rol;

import com.library.domainLibrary.domain.ciclo.CicloDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


import com.fiuni.administrador.dto.base.BaseDto;

import java.util.List;
@XmlRootElement(name = "ROLES")
public class RolDto extends BaseDto {

    private String nombre;
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

