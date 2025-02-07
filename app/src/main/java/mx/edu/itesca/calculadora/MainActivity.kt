package mx.edu.itesca.calculadora
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * MainActivity
 *
 * This class represents the main activity of a simple calculator application.
 * It allows users to input numbers and basic arithmetic operations (+, -, *, /)
 * and then calculate the result.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var tvResultado: TextView
    private lateinit var tvSinTexto: TextView
    private var operacion: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvSinTexto = findViewById(R.id.TVSinTexto)
        tvResultado = findViewById(R.id.TV)

        findViewById<Button>(R.id.cero).setOnClickListener { agregarNumero("0") }
        findViewById<Button>(R.id.uno).setOnClickListener { agregarNumero("1") }
        findViewById<Button>(R.id.dos).setOnClickListener { agregarNumero("2") }
        findViewById<Button>(R.id.tres).setOnClickListener { agregarNumero("3") }
        findViewById<Button>(R.id.cuatro).setOnClickListener { agregarNumero("4") }
        findViewById<Button>(R.id.cinco).setOnClickListener { agregarNumero("5") }
        findViewById<Button>(R.id.seis).setOnClickListener { agregarNumero("6") }
        findViewById<Button>(R.id.siete).setOnClickListener { agregarNumero("7") }
        findViewById<Button>(R.id.ocho).setOnClickListener { agregarNumero("8") }
        findViewById<Button>(R.id.nueve).setOnClickListener { agregarNumero("9") }

        findViewById<Button>(R.id.sumar).setOnClickListener { agregarNumero("+") }
        findViewById<Button>(R.id.restar).setOnClickListener { agregarNumero("-") }
        findViewById<Button>(R.id.multiplicar).setOnClickListener { agregarNumero("*") }
        findViewById<Button>(R.id.dividir).setOnClickListener { agregarNumero("/") }

        findViewById<Button>(R.id.result).setOnClickListener { calcularResultado() }

        findViewById<Button>(R.id.borrar).setOnClickListener { limpiar() }
    }

    private fun agregarNumero(valor: String) {
        operacion += valor
        tvResultado.text = operacion
    }

    private fun calcularResultado() {
        try {
            val resultado = eval(operacion)
            tvSinTexto.text = resultado.toString()
        } catch (e: Exception) {
            tvSinTexto.text = "Error"
        }
    }

    private fun limpiar() {
        operacion = ""
        tvResultado.text = ""
        tvSinTexto.text = ""
    }
    private fun eval(expresion: String): Double {
        return expresion.split("+", "-", "*", "/").map { it.toDouble() }
            .reduce { acc, num -> when {
                expresion.contains("+") -> acc + num
                expresion.contains("-") -> acc - num
                expresion.contains("*") -> acc * num
                expresion.contains("/") -> acc / num
                else -> acc
            }}
    }
}