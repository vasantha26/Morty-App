package com.example.rickymortyapp.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickymortyapp.adapter.FavoriteAdapter
import com.example.rickymortyapp.model.FavoriteCharacter
import com.example.rickymortyapp.databinding.FavBinding


class FavoriteActivity : AppCompatActivity() , FavoriteAdapter.OnItemClickedListers {

    var id :Int ?= null
    var name :String ?= null
    var status :String ?= null
    private var gender :String ?= null
    private var type :String ?= null
    private var species :String ?= null
    private var image :String ?= null
    private var postions :Int ?= null
    private var remove :Boolean ?= null
    private lateinit var favoriteCharacter: FavoriteAdapter



    private lateinit var binding: FavBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FavBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val intent = intent
        id = intent.getIntExtra("id", 0)
        name = intent.getStringExtra("name")
        status =  intent.getStringExtra("status")
        gender = intent.getStringExtra("gender")
        type = intent.getStringExtra("type")
        species = intent.getStringExtra("species")
        image = intent.getStringExtra("image")
        postions = intent.getIntExtra("pos" ,0)
        remove = intent.getBooleanExtra("isRemoves" ,false)


        binding.title.setOnClickListener {
            finish()
        }


        val characters = listOf(
            FavoriteCharacter(id, name, image,status,species,type,gender),
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        favoriteCharacter = FavoriteAdapter(characters,this)
        binding.recyclerView.adapter = favoriteCharacter
        if (remove == true){
            favoriteCharacter.removeData(postions!!)
        }


    }

    override fun cliekcedItem(character: FavoriteCharacter, position: Int) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("id",character.id)
        intent.putExtra("name",character.name)
        intent.putExtra("status",character.status)
        intent.putExtra("gender",character.gender)
        intent.putExtra("type",character.type)
        intent.putExtra("species",character.species)
        intent.putExtra("image",character.image)
        intent.putExtra("remove" ,true)
        startActivity(intent)

    }

    override fun removedate(character: FavoriteCharacter) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("id",character.id)
        intent.putExtra("name",character.name)
        intent.putExtra("status",character.status)
        intent.putExtra("gender",character.gender)
        intent.putExtra("type",character.type)
        intent.putExtra("species",character.species)
        intent.putExtra("image",character.image)
        intent.putExtra("remove" ,false)
        startActivity(intent)
    }
}