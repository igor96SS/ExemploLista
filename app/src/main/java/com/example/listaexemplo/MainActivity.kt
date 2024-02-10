package com.example.listaexemplo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    //declaraco global das variáveis
    private lateinit var nomeEditText: EditText
    private lateinit var idadeInputLayout: TextInputLayout
    private lateinit var idadeText: TextInputEditText
    private lateinit var guardarButton: Button
    private lateinit var listaRecyclerView: RecyclerView
    private var listaDados: MutableList<DadosLista> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // inicializcao das variáveis
        // associar as variáveis aos elementos do layout
        nomeEditText = findViewById(R.id.nomeEditText)
        idadeText = findViewById(R.id.idadeEditText)
        guardarButton = findViewById(R.id.guardarButton)
        idadeInputLayout = findViewById(R.id.idadeTextInputField)
        listaRecyclerView = findViewById(R.id.listaRecyclerView)


        // Fica à escuta ao TextInputEditText para detetar e reagir a mudanças em tempo real
        idadeText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Não é necessário implementar este método
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Limpa o erro quando o utilizador começa a escrever
                idadeInputLayout.error = null
            }

            override fun afterTextChanged(s: Editable?) {
                // Não é necessário implementar este método
            }
        })

        // clique no botão guardar
        guardarButton.setOnClickListener {
            // chamar a funcao que guarda os dados
            guardarDados()

            // exibe mensagem a dizer que os dados foram inseridos
            Toast.makeText(this, "Dados inseridos", Toast.LENGTH_LONG).show()
        }

        // configuraçao do RecyclerView
        val servicesAdapter = ItemAdapter(listaDados)
        // associa o RecyclerView no layout
        listaRecyclerView = findViewById(R.id.listaRecyclerView)
        // define o adaptador para o recyclerView
        listaRecyclerView.adapter = servicesAdapter
        // Por padrão é definido layout vertical
        listaRecyclerView.layoutManager = LinearLayoutManager(this)

    }

    private fun guardarDados(){
        // variaveis que assumem o valor introduzido pelo utilizador
        val nome = nomeEditText.text.toString()
        val idade = idadeText.text.toString()

        // se a funcao verificarDados retornar "true" os dados sao adicionados à lista
        // caso contrario, mostra os erros
        if (verificarDados(nome, idadeInputLayout.toString())){
            val dados = DadosLista(nome, idade.toInt())
            // Adiciona o objeto à lista
            listaDados.add(dados)
        }else{
            // mostra um toast com uma mensagem na parte inferior do ecrã
            // que desaparece de forma automática após um tempo definido
            Toast.makeText(this,"Obrigatorio preencher o nome!",Toast.LENGTH_LONG).show()

            // Define uma mensagem de erro no textInputLayout
            idadeInputLayout.error = "Obrigatorio preencher a idade"
        }

        //notificar o recycler view para atualizar os dados
        // o ponto de interrogação "?" em kotlin serve para lidar com objetos nulos
        // se for nulo devolve nulo
        //previne os nullpointer exception
        listaRecyclerView.adapter?.notifyDataSetChanged()

    }

    // função para verificar se os campos estao preenchidos
    // retorna um valor Boolean(Verdadeiro ou falso)
    private fun verificarDados(nome: String, idadeInput: String): Boolean{
        // Verdadeiro(true) se tanto o nome como o idadeInput nao estiverem vazios
        return nome.isNotEmpty() && idadeInput.isNotEmpty()
    }

}