package br.com.lucasbezerra.meuseguroauto

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.SeekBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import br.com.lucasbezerra.meuseguroauto.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpSeekBar()

        binding.btSend.setOnClickListener{
            send()
        }

        binding.btBack.setOnClickListener{
            back()
        }

    }

    private fun setUpSeekBar() {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        binding.sbYear.max = currentYear
        binding.sbYear.min = currentYear - 20
        binding.sbYear.progress = currentYear - 10
        binding.tvYear.text = getString(R.string.seekbar_vehicle_year_text,
            binding.sbYear.progress)
        binding.sbYear.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                // Atualiza um TextView com o valor do SeekBar
                binding.tvYear.text = getString(R.string.seekbar_vehicle_year_text,
                    p1)
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {
                // Pode ser usado para indicar que o usuário começou a interagir
            }
            override fun onStopTrackingTouch(p0: SeekBar?) {
                // Pode ser usado para salvar o valor quando o usuário soltar o
            }
        })
    }

    private fun send() {
        val name = binding.etName.text.toString()
        val uf = binding.spUf.selectedItem.toString()
        val cbAcceptTerms = if (binding.cbAcceptTerms.isChecked) "Aceito"
        else "Não aceito"
        val rgCoverage =
            findViewById<RadioButton>(binding.rgCoverage.checkedRadioButtonId)?.text.toString()
        val switchValue = if (binding.switchSeguro.isChecked) "Sim" else
            "Não"
        val anoSelecionado = binding.sbYear.progress
        binding.tvSummary.text =
            getString(R.string.summary_text, name, uf, cbAcceptTerms,
                rgCoverage, switchValue, anoSelecionado)
        binding.form.visibility = View.GONE
        binding.summary.visibility = View.VISIBLE
    }

    private fun back() {
        binding.form.visibility = View.VISIBLE
        binding.summary.visibility = View.GONE
    }

}