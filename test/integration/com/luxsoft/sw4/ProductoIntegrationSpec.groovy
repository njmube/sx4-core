package com.luxsoft.sw4

import grails.test.spock.IntegrationSpec
import grails.buildtestdata.mixin.Build

@Build([Producto,Linea])
class ProductoIntegrationSpec extends IntegrationSpec {

    def setup() {
    }

    def cleanup() {
    }

    void "Salvar un producto nuevo"() {
    	given:'Un producto nuevo'
    	//def producto=new Producto(clave:'POL74',descripcion:'POL74')
    	def producto=Producto.build(clave:'POL74')
    	when:'Salvamos el producto'
    	producto.save()
    	then:'El producto es persistido en la base de datos'
    	producto.errors.errorCount==0
    	producto.id
    	Producto.get(producto.id).clave=='POL74'
    	println 'Producto salvado: '+producto
    }

    void "Actualizar un producto"(){
    	given:"Un Producto existente"
    	def producto=Producto.build(clave:'POL74')

    	when:'Actualizamos la descripcion'
    	def found=Producto.get(producto.id)
    	assert found.id
    	found.descripcion='CUBIERTO POL74'
    	found.save flush:true

    	then: 'El cambio persiste en la base de datos'
    	Producto.get(found.id).descripcion=='CUBIERTO POL74'
    	println 'Producto actualizado: '+found

    }

    void "Verificar la presicion de los precios"(){
        given: 'Un producto'
        def producto=Producto.build(clave:'POL72',precioDeContado:20.0003,precioDeCredito:21.0023)

        when:'Leemos el producto desde la base '
        def found=Producto.get(producto.id)
        then:' Los precios mentiene la precision de 4 posiciones'
        found.precioDeCredito==21.0023
        found.precioDeContado==20.0003


    }
}
