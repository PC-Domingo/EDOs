import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

//Recibe el sistema de ecuaciones como parámetro con una función anónima
fun eulerAdelante(tInicial:Double, tFinal:Double, h:Double, yInicial:Array<Double>, params:Array<Double>,
                  miFun: (t:Double, y:Array<Double>, params:Array<Double>)-> Array<Double>){
    /*Manejo de archivos

     */

    // Definir ruta del archivo
    val nombreArchivo = "C:\\Users\\DELL\\Documents\\datos.csv"
    val file = File(nombreArchivo)
    if (!file.exists()) {
        file.createNewFile()
    }
    val fileWriter = FileWriter(file)
    val bufferedWriter = BufferedWriter(fileWriter)
    var t:Double = tInicial
    var y: Array<Double> = yInicial
    var yPrima: Array<Double> =  Array(yInicial.size) { 0.0 }
    var n:Int = 1
    while (t <= tFinal){
        yPrima = miFun(t, y, params)
        println("n2 = $n; t = $t; y = ${y[0]}")
        bufferedWriter.write("$t\t${y[0]}\t${y[1]}\t${yPrima[1]}\n")// <-- Escribe en el archivo
        y += yPrima.perScalar(h) //accumulator
        t = n*h
        n++
    }
    bufferedWriter.close()
}
