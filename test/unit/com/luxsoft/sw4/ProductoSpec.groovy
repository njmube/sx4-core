package com.luxsoft.sw4

import grails.test.mixin.TestFor
import spock.lang.Specification

import grails.buildtestdata.mixin.Build

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
//@TestFor(Producto)
@Build([Producto,Linea])
@TestFor(Producto)
class ProductoSpec extends Specification {

	//def linea

    def setup() {
    	//linea=Linea.build(nombre:'CUBIERTOS')
    }

    def cleanup() {
    }

    void "Validaciones de un producto"() {
    	given:'Un  producto nuevo valido'
    	def producto=Producto.buildWithoutSave()
    	when:'Validamos'
    	producto.validate()
    	then:'La validacion es correcta'
    	!producto.hasErrors()

    	when:'La clave es incorrecta'
    	producto.clave='POL'
    	then:'La validacion no pasa'
    	!producto.validate()
    	producto.hasErrors()
    	producto.errors.errorCount==1
    	producto.errors['clave'].code=='size.toosmall'

    	when:'La descripcion es incorrecta'
        producto=Producto.buildWithoutSave(descripcion:'SS')
    	
        then:'La validacion no pasa'
    	producto.hasErrors()
    	producto.errors.errorCount==1
        producto.errors['descripcion']?.code=='size.toosmall'
    	//producto.errors['descripcion'].code=='size.toosmall'

    }

    void "Salvar un producto nuevo"(){

    	given:'Un producto nuevo'
    	//def producto=Producto.buildWithoutSave(clave:'POL74',linea:linea)
    	def producto=Producto.build(clave:'POL74')

        when:'Salvamos el producto'
        producto.save() 

        then:'El producto persiste correctamente en la base de datos'
        producto.errors.errorCount==0
        producto.id
        Producto.get(producto.id).clave==producto.clave
        println Producto.count()==1
        

    }

   
    void "Precision de precios en producto"(){
        given: 'Un producto'
        def producto=Producto.build(clave:'POL72',precioDeContado:20.0003,precioDeCredito:21.0023)

        when:'Leemos el producto desde la base '
        def found=Producto.get(producto.id)
        then:' Los precios mentiene la precision requerida'
        found.precioDeCredito==21.0023
        found.precioDeContado==20.0003


    }
    
    


}
