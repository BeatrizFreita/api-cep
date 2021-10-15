package com.example.retrofitviacep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.retrofitviacep.RetrofitFactory
import com.example.retrofitviacep.Cep
import com.example.retrofitviacep.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var buttonBuscar: Button
    lateinit var textViewEndereco: TextView
    lateinit var editTextCep: EditText

    lateinit var buttonCep: Button
    lateinit var nomeDaRua: TextView
    lateinit var nomeDaCidade: TextView
    lateinit var siglaDoEstado: TextView
    lateinit var textViewLis: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonBuscar = findViewById(R.id.buttonBuscar)
        textViewEndereco = findViewById(R.id.textViewEndereco)
        editTextCep = findViewById(R.id.editTextCep)
        buttonBuscar.setOnClickListener {

            val remote = RetrofitFactory().retrofitService()
            val call: Call<Cep> = remote.getCEP(editTextCep.text.toString())

            call.enqueue(object : Callback<Cep> {

                override fun onResponse(call: Call<Cep>, response: Response<Cep>) {
                    val cep = response.body()
                    textViewEndereco.text = cep.toString()
                }

                override fun onFailure(call: Call<Cep>, t: Throwable) {
                    Log.i("cep", t.message.toString())
                }
            })
        }

        nomeDaRua = findViewById(R.id.nameDaRua)
        nomeDaCidade = findViewById(R.id.nomeDaCidade)
        siglaDoEstado = findViewById(R.id.siglaEstado)
        buttonCep.setOnClickListener {
            val remote = RetrofitFactory().retrofitService()
            val call: Call<List<Cep>> = remote.getCEPByLogradouro(
                siglaDoEstado.text.toString(),
                nomeDaCidade.text.toString(),
                nomeDaRua.text.toString()
            )

            call.enqueue(object : Callback<List<Cep>> {
                override fun onResponse(call: Call<List<Cep>>, response: Response<List<Cep>>) {
                    val cep = response.body()
                    textViewLis.text = cep.toString()

                }

                override fun onFailure(call: Call<List<Cep>>, t: Throwable) {
                    Log.i("cep", t.message.toString())
                }

            })
        }
    }
}
