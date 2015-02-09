package com.luxsoft.sw4

class Linea {

	String nombre

    static constraints = {
    	nombre size:5..50,unique:true
    }

    String toString(){
    	return nombre
    }
}
