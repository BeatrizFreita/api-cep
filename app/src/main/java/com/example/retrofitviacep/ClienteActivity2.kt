package com.example.retrofitviacep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class ClienteActivity2 : AppCompatActivity() {

    lateinit var edNome: EditText
    lateinit var edEmail: EditText
    lateinit var edTelefone: EditText
    lateinit var edSalvar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cliente2)

        edNome = findViewById(R.id.et_nome)
        edEmail = findViewById(R.id.et_email)
        edTelefone = findViewById(R.id.et_telefone)
        edSalvar = findViewById(R.id.button)
        edSalvar.setOnClickListener {
            val cliente = Cliente(
                    0,
                    edNome.text.toString(),
                    edEmail.text.toString(),
                    edTelefone.text.toString()
            )

            val remote = RetrofitFactory().retrofitService()

            val call: Call<Cliente> = remote.gravarCliente(cliente)

            call.enqueue(object : Callback<Cliente> {
                override fun onResponse(call: Call<Cliente>, response: Response<Cliente>) {
                    Toast.makeText(applicationContext, "Sucesso!", Toast.LENGTH_SHORT).show()
                }
                override fun onFailure(call: Call<Cliente>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}