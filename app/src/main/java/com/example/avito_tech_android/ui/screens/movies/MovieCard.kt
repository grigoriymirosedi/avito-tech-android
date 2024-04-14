package com.example.avito_tech_android.ui.screens.movies

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.avito_tech_android.R
import com.example.avito_tech_android.domain.models.MovieModel
import com.example.avito_tech_android.domain.models.PosterModel

@Composable
fun MovieCard(modifier: Modifier = Modifier, movieModel: MovieModel) {

    Card(modifier = modifier) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth().aspectRatio(3f/2f),
            model = movieModel.poster.previewUrl,
            placeholder = painterResource(R.drawable.placeholder),
            error = painterResource(R.drawable.placeholder),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = modifier.padding(horizontal = 16.dp, vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                text = movieModel.name,
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.size(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    fontSize = 12.sp,
                    textAlign = TextAlign.Start,
                    text = "Год выпуска: " + movieModel.year.toString(),
                    color = Color.LightGray,
                )
                Text(
                    fontSize = 12.sp,
                    text = movieModel.ageRating.toString() + "+",
                    color = Color.LightGray,
                    textAlign = TextAlign.End
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieCardPreview(
    modifier: Modifier = Modifier,
    movieModel: MovieModel = MovieModel(
        id = 123,
        name = "Шрек",
        type = "Фильим",
        year = 2023,
        ageRating = 18,
        description = "Лучший фильм",
        countries = "Россия",
        poster = PosterModel(
            previewUrl = "https://upload.wikimedia.org/wikipedia/ru/3/39/Shrek.jpg",
            url = "https://upload.wikimedia.org/wikipedia/ru/3/39/Shrek.jpg"
        )
    ),
) {
    MovieCard(movieModel = movieModel)
}