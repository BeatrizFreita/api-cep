package br.senai.sp.jandira.retrofitviacep
import com.example.retrofitviacep.Cep
import com.example.retrofitviacep.Cliente
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {
    //Método que será responsável por chamar a API
    // https://viacep.com.br/ws/06600025/json/
    @GET("{CEP}/json/")
    fun getCEP(@Path("CEP")cep: String) : Call<Cep>
    @GET("{uf}/{cidade}/{logradouro}/json")
    fun getCEPByLogradouro(
        @Path("uf") uf: String,
        @Path("cidade") cidade: String,
        @Path("logradouro") logradouro: String) : Call<List<Cep>>

    @DELETE("ceps/{id}")
    fun excluir(@Path("id") id: Int)

    @POST("clientes")
    fun gravarCliente(@Body cliente: Cliente) : Call<Cliente>
}