import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

//Recibe el sistema de ecuaciones como parámetro con una función anónima
fun rungeKutta45(tInicial:Double,
                 tFinal:Double,
                 yInicial:Array<Double>,
                 params:Array<Double>,
                 miFun: (t:Double, y:Array<Double>, params:Array<Double>)-> Array<Double>,
                 nombreArchivo:String,
                 h0:Double,
                 errMin:Double,
                 errMax:Double,
                 hMin:Double,
                 hMax:Double){

    // Definir ruta del archivo
    val nombreArchivo = "C:\\Users\\domin\\Documents\\${nombreArchivo}.csv"
    val file = File(nombreArchivo)
    if (!file.exists()) {
        file.createNewFile()
    }
    val fileWriter = FileWriter(file)
    val bufferedWriter = BufferedWriter(fileWriter)

    var t:Double = tInicial
    var h:Double = h0
    var y4: Array<Double> = yInicial // Var para calcular Runge-Kutta 4°
    var y5: Array<Double> = yInicial // Var para calcular Runge-Kutta 5°
    var y: Array<Double> = yInicial  //Recibe las condiciones iniciales

    //var f: Array<Double> =  Array(yInicial.size) { 0.0 }
    var k1:Array<Double>
    var k2:Array<Double>
    var k3:Array<Double>
    var k4:Array<Double>
    var k5:Array<Double>
    var k6:Array<Double>

    var n:Int = 1
    while (t <= tFinal){
        if (h < hMin){
            h = hMin
        }else if (h > hMax){
            h = hMax
        }

        k1 = (miFun(t, y, params)).perScalar(h)
        k2 = (miFun(t + h/4.0,y
                + k1.perScalar(1.0/4.0), params)).perScalar(h)

        k3 = (miFun(t + 3.0*h/8.0,y
                +k1.perScalar(3.0/32.0)
                +k2.perScalar(9.0/32.0), params)).perScalar(h)

        k4 = (miFun(t + 12.0*h/13.0, y
                    + k1.perScalar(1932.0/2197.0)
                    - k2.perScalar(7200.0/2197.0)
                    + k3.perScalar(7296.0/2197.0), params)).perScalar(h)

        k5 = (miFun(t + h, y
                + k1.perScalar(439.0/216.0)
                - k2.perScalar(8.0)
                + k3.perScalar(3680.0/513.0)
                - k4.perScalar(845.0/4104.0), params)).perScalar(h)

        k6 = (miFun(t + h/2.0, y
                - k1.perScalar(8.0/27.0)
                + k2.perScalar(2.0)
                - k3.perScalar(3544.0/2565.0)
                + k4.perScalar(1859.0/4104.0)
                - k5.perScalar(11.0/40.0), params)).perScalar(h)

        println("n3 = $n; t = $t; y = ${y[0]}")
        bufferedWriter.write("$t\t${y[0]}\t${y[1]}\n")// <-- Escribe en el archivo

        y4 += k1.perScalar(25.0/216.0) + //Calcula RK4
                k3.perScalar(1408.0/2565.0) +
                k4.perScalar(2197.0/4104.0) -
                k5.perScalar(1.0/5.0)

        y5 += k1.perScalar(16.0/135.0) + //Calcula RK5
                k3.perScalar(6656.0/12825.0) +
                k4.perScalar(28561.0/56430.0) -
                k5.perScalar(9.0/50.0) +
                k6.perScalar(2.0/55.0)

        val error:Array<Double> = y4 - y5
        if (error[0] > errMax && h > hMin){
            h /= 2.0
        }else{
            t += h
            n++
            y = y5
            if (error[0] < errMin) h *= 2.0
        }
    }
    bufferedWriter.close()
}
