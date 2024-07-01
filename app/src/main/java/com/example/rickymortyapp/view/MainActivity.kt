package com.example.rickymortyapp.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickymortyapp.model.Character
import com.example.rickymortyapp.adapter.CharacterAdapter
import com.example.rickymortyapp.R
import com.example.rickymortyapp.service.RetrofitInstance
import com.example.rickymortyapp.model.RickAndMortyResponse
import com.example.rickymortyapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() , CharacterAdapter.OnItemClickedListers {


    private lateinit var characterAdapter: CharacterAdapter




    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        characterAdapter = CharacterAdapter()
        binding.recyclerView.adapter = characterAdapter

        fetchCharacters()

        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.charactes -> {
                    true
                }
                R.id.locations -> {
                    true
                }
                R.id.episodes -> {
                    true
                }
                R.id.settings -> {
                    true
                }
                else -> false
            }
        }
    }

    private fun fetchCharacters() {

        RetrofitInstance.api.getCharacters().enqueue(object : Callback<RickAndMortyResponse> {
            override fun onResponse(call: Call<RickAndMortyResponse>, response: Response<RickAndMortyResponse>) {
                if (response.isSuccessful) {
                    val characters = response.body()?.results
                    characters?.forEach { _ ->
                        characterAdapter.setCharacters(characters ,this@MainActivity)
                    }
                }else {
                    showError("Failed to fetch data")
                }
            }


            override fun onFailure(call: Call<RickAndMortyResponse>, t: Throwable) {
                showError(t.message.toString())
            }
        })
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun cliekcedItem(character: Character, position: Int) {
        val intent = Intent(this, DetailsActivity::class.java)
         intent.putExtra("id",character.id)
         intent.putExtra("name",character.name)
         intent.putExtra("status",character.status)
         intent.putExtra("gender",character.gender)
         intent.putExtra("type",character.type)
         intent.putExtra("species",character.species)
         intent.putExtra("image",character.image)
         intent.putExtra("pos",position)
        startActivity(intent)
    }

}

