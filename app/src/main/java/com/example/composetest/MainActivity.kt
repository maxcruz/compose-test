package com.example.composetest

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.graphics.asImageAsset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.composetest.ui.ComposeTestTheme
import java.util.*

class LayoutActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestTheme {
                Scaffold(topBar = { Header() }) {
                    LazyColumnFor(items = list) { pokemon ->
                        PokemonCard(pokemon = pokemon) {
                            ImageLoader(url = pokemon.imageUrl)?.asImageAsset() ?: imageResource(id = R.drawable.pokeball)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Header() {
    TopAppBar(
            title = {
                Text(text = "Dexter")
            },
            actions = {
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.Search)
                }
            }
    )
}

@Composable
fun PokemonCard(pokemon: Pokemon, image: @Composable () -> ImageAsset) {
    val rowModifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.surface)
            .clickable(onClick = { })


    Row (modifier = rowModifier, verticalAlignment = Alignment.CenterVertically) {
        val imageModifier = Modifier.preferredSize(100.dp)
                .padding(16.dp)
                .clip(shape = CircleShape)
                .background(MaterialTheme.colors.onSurface.copy(alpha = 0.2f))


        Image(image(), modifier = imageModifier, contentScale = ContentScale.Inside)
        Column {
            Text(text = pokemon.name.capitalize(Locale.getDefault()), style = MaterialTheme.typography.h5)
            Divider(color = Color.LightGray)
            Text(text = pokemon.type.capitalize(Locale.getDefault()), style = MaterialTheme.typography.body2)
        }
    }

}

@Preview
@Composable
fun PokemonCardPreview() {
    PokemonCard(Pokemon(0, "Pokemon", "Type", "")) {
        imageResource(id = R.drawable.pokeball)
    }
}

@Composable
fun ImageLoader(url: String): Bitmap? {
    var bitmap by remember { mutableStateOf<Bitmap?>(null)}
    Glide.with(ContextAmbient.current)
            .asBitmap()
            .load(url)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    bitmap = resource
                }
                override fun onLoadCleared(placeholder: Drawable?) {
                    bitmap = null
                }
            })
    return bitmap
}

data class Pokemon(val number: Int, val name: String, val type: String, val imageUrl: String)

val list = listOf(
        Pokemon(number = 1, name = "bulbasaur", type = "grass", imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"),
        Pokemon(number = 4, name = "charmander", type = "fire", imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/4.png"),
        Pokemon(number = 7, name = "squirtle", type = "water", imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/7.png"),
        Pokemon(number = 10, name = "caterpie", type = "bug", imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/10.png"),
        Pokemon(number = 16, name = "pidgey", type = "flyin", imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/16.png"),
        Pokemon(number = 25, name = "pikachu", type = "electric", imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png"),
        Pokemon(number = 35, name = "clefairy", type = "fairy", imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/35.png"),
        Pokemon(number = 37, name = "vulpix", type = "fire", imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/37.png"),
        Pokemon(number = 52, name = "meowth", type = "normal", imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/52.png"),
        Pokemon(number = 63, name = "abra", type = "psychic", imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/63.png"),
        Pokemon(number = 77, name = "ponyta", type = "fire", imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/77.png"),
)