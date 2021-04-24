fun main(args:Array<String>){
    println("Inicio del programa")
    val tIncial:Double = 0.0
    val tFinal:Double = 10.0
    val  h = 0.01
     val params:Array<Double> = arrayOf(1.5/*k*/, 0.5/*m*/, 0.3/*λ*/)
    val yInicial2:Array<Double> = arrayOf(0.7, 0.0)

    //eulerAdelante(tIncial, tFinal, h, yInicial)

    var miFuncion = fun (t:Double, y:Array<Double>, params:Array<Double>): Array<Double>{
        var f = Array(y.size) { 0.0 } //Inicializa un array con un tamaño específico y valores en ceros
        f[0] = y[1]
        f[1] = - (params[0] / params[1]) * y[0] - (params[2] / params[1]) * y[1]
        return f
    }

    //eulerAdelante(tIncial,tFinal,h,yInicial2,params,miFuncion)
    rungeKutta(tIncial,tFinal,h,yInicial2,params,miFuncion,"datos2")
    rungeKutta45(tIncial,tFinal,yInicial2,params,miFuncion,"datos3",0.001,1.0e-8,1.0e-4,0.0001,0.1)
}


