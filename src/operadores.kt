//Sobrecargar el operador * "times" entre dos arrays
operator fun Array<Double>.times(array: Array<Double>):Array<Double>{
    val result : Array<Double> = Array(array.size) {0.0}
    for (i in array.indices){
        result[i] = this[i] * array[i]
    }
    return result
}

//Extensión de la clase Array con la función perScalar que multiplica un escalar con un array
fun Array<Double>.perScalar(scalar: Double):Array<Double>{
    val result : Array<Double> = Array(this.size) {0.0}
    for (i in this.indices){
        result[i] = scalar * this[i]
    }
    return result
}

operator fun Array<Double>.plus(array: Array<Double>):Array<Double>{
    val result : Array<Double> = Array(array.size) {0.0}
    for (i in array.indices){
        result[i] = this[i] + array[i]
    }
    return result
}

operator fun Array<Double>.minus(array: Array<Double>):Array<Double>{
    val result : Array<Double> = Array(array.size) {0.0}
    for (i in array.indices){
        result[i] = this[i] - array[i]
    }
    return result
}
