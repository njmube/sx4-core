package com.luxsoft.sw4

import groovy.transform.ToString
import groovy.transform.EqualsAndHashCode
import org.grails.databinding.BindingFormat


@ToString(includeNames=true,includePackage=false)
@EqualsAndHashCode(includes='id,clave')
class Producto {

	String clave
	String descripcion

	//Clasificacion del producto
	Linea linea
	Marca marca
	Clase clase
	String unidad

	BigDecimal precioDeCredito
	BigDecimal precioDeContado

	Boolean inventariable=true
	Boolean activo=true
	String presentacion

	Date dateCreated
	Date lastUpdated

    static constraints = {
    	clave size:4..20,unique:true
    	descripcion(size:4..300)
    	linea()
    	clase()
    	marca()
    	unidad(size:2..20)
    	precioDeCredito(scale:4)
    	precioDeContado(scale:4)
    	inventariable()
    	activo()
    	presentacion inList:['BOBINA','CORTADO','EXTENDIDO','ND']

    }
}
