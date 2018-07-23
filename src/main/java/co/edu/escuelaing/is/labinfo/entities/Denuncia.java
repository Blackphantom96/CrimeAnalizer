package co.edu.escuelaing.is.labinfo.entities;

import co.edu.escuelaing.is.labinfo.utiles.Utiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.*;

public class Denuncia implements Serializable {
    private int id,codigo_dane,edad;
    private Date fecha;
    private Double lat,lng;
    private String departamento,municipio,dia,hora,zona,arma_empleada,movil_victima,barrio,movil_agresor,sexo,estado_civil,escolaridad,clase_de_sitio,cantidad,profesion,pais_de_nacimiento,clase_de_empleado,clase,marca,linea,modelo,color,profesiones,delito,clase_empresa,descripcion_conducta,categoria;
    public Denuncia(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodigo_dane() {
        return codigo_dane;
    }

    public void setCodigo_dane(int codigo_dane) {
        this.codigo_dane = codigo_dane;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getArma_empleada() {
        return arma_empleada;
    }

    public void setArma_empleada(String arma_empleada) {
        this.arma_empleada = arma_empleada;
    }

    public String getMovil_victima() {
        return movil_victima;
    }

    public void setMovil_victima(String movil_victima) {
        this.movil_victima = movil_victima;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getMovil_agresor() {
        return movil_agresor;
    }

    public void setMovil_agresor(String movil_agresor) {
        this.movil_agresor = movil_agresor;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    public String getEscolaridad() {
        return escolaridad;
    }

    public void setEscolaridad(String escolaridad) {
        this.escolaridad = escolaridad;
    }

    public String getClase_de_sitio() {
        return clase_de_sitio;
    }

    public void setClase_de_sitio(String clase_de_sitio) {
        this.clase_de_sitio = clase_de_sitio;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getPais_de_nacimiento() {
        return pais_de_nacimiento;
    }

    public void setPais_de_nacimiento(String pais_de_nacimiento) {
        this.pais_de_nacimiento = pais_de_nacimiento;
    }

    public String getClase_de_empleado() {
        return clase_de_empleado;
    }

    public void setClase_de_empleado(String clase_de_empleado) {
        this.clase_de_empleado = clase_de_empleado;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getProfesiones() {
        return profesiones;
    }

    public void setProfesiones(String profesiones) {
        this.profesiones = profesiones;
    }

    public String getDelito() {
        return delito;
    }

    public void setDelito(String delito) {
        this.delito = delito;
    }

    public String getClase_empresa() {
        return clase_empresa;
    }

    public void setClase_empresa(String clase_empresa) {
        this.clase_empresa = clase_empresa;
    }

    public String getDescripcion_conducta() {
        return descripcion_conducta;
    }

    public void setDescripcion_conducta(String descripcion_conducta) {
        this.descripcion_conducta = descripcion_conducta;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}
