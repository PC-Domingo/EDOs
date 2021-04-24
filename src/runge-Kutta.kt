import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

//Recibe el sistema de ecuaciones como parámetro con una función anónima
fun rungeKutta(tInicial:Double, tFinal:Double, h:Double, yInicial:Array<Double>, params:Array<Double>,
                  miFun: (t:Double, y:Array<Double>, params:Array<Double>)-> Array<Double>, nombreArchivo:String){

    // Definir ruta del archivo
    val nombreArchivo = "C:\\Users\\domin\\Documents\\${nombreArchivo}.csv"
    val file = File(nombreArchivo)
    if (!file.exists()) {
        file.createNewFile()
    }
    val fileWriter = FileWriter(file)
    val bufferedWriter = BufferedWriter(fileWriter)
    var t:Double = tInicial
    var y: Array<Double> = yInicial
    //var f: Array<Double> =  Array(yInicial.size) { 0.0 }
    var k1:Array<Double>
    var k2:Array<Double>
    var k3:Array<Double>
    var k4:Array<Double>
    var n:Int = 1
    while (t <= tFinal){
        k1 = (miFun(t, y, params)).perScalar(h)
        k2 = (miFun(t+h/2.0, y+k1.perScalar(0.5), params)).perScalar(h)
        k3 = (miFun(t+h/2.0, y+k2.perScalar(0.5), params)).perScalar(h)
        k4 = (miFun(t+h, y+k3, params)).perScalar(h)
        println("n2 = $n; t = $t; y = ${y[0]}")
        bufferedWriter.write("$t\t${y[0]}\t${y[1]}\n")// <-- Escribe en el archivo
        y += (k1 + k2.perScalar(2.0) + k3.perScalar(2.0) + k4).perScalar((1.0/6.0))
        t = n*h
        n++
    }
    bufferedWriter.close()
}
