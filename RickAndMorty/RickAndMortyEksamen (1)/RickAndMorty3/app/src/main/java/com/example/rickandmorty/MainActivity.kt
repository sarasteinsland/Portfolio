package com.example.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rickandmorty.ui.theme.data.CharacterRepository
import com.example.rickandmorty.ui.theme.data.room.AppDatabase
import com.example.rickandmorty.ui.theme.screens.screens.favorite_characters.FavoriteCharactersScreen
import com.example.rickandmorty.ui.theme.screens.screens.character_details.CharacterDetailsScreen
import com.example.rickandmorty.ui.theme.screens.screens.character_details.CharacterDetailsViewModel
import com.example.rickandmorty.ui.theme.screens.screens.character_list.CharacterListScreen
import com.example.rickandmorty.ui.theme.screens.screens.character_list.CharacterListViewModel
import com.example.rickandmorty.ui.theme.screens.screens.madeCharactersScreen.CharacterCreationScreen
import com.example.rickandmorty.ui.theme.screens.screens.madeCharactersScreen.SavedCharactersScreen
import com.example.rickandmorty.ui.theme.screens.screens.madeCharactersScreen.SavedCharactersViewModel
import com.example.rickandmorty.ui.theme.theme.DarkBackground
import com.example.rickandmorty.ui.theme.theme.NeonGreen
import com.example.rickandmorty.ui.theme.theme.RickAndMortyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialiserer databasen som brukes i appen
        CharacterRepository.initializeDatabase(this)
        AppDatabase.getInstance(applicationContext)


        setContent {
            // Angir temaet for appen
            RickAndMortyTheme {
                // Oppretter en navigasjonskontroller
                val navController = rememberNavController()

                // Oppretter viewModels for karakterliste og lagrede karakterer
                val characterListViewModel = viewModel { CharacterListViewModel(CharacterRepository) }
                val savedCharactersViewModel = viewModel { SavedCharactersViewModel(CharacterRepository) }

                Scaffold(
                    // Angir bakgrunnsfarge og navigasjonslinjer på siden
                    backgroundColor = DarkBackground,
                    topBar = {
                        TopAppBar(
                            title = { Text("Rick and Morty", color = Color.Black) },
                            backgroundColor = NeonGreen,
                            actions = {
                                // Knapp for å navigere til skjermen for å lage karakterer
                                IconButton(onClick = { navController.navigate("character_creation") }) {
                                    Icon(
                                        painter = painterResource(id = android.R.drawable.ic_input_add),
                                        contentDescription = "Add Character",
                                        tint = Color.Black
                                    )
                                }
                            }
                        )
                    },
                    bottomBar = {
                        BottomAppBar(
                            backgroundColor = NeonGreen,
                            content = {
                                // Knapp for å navigere til skjerm med lagrede karakterer
                                IconButton(onClick = { navController.navigate("saved_characters") }) {
                                    Icon(
                                        painter = painterResource(id = android.R.drawable.ic_menu_myplaces),
                                        contentDescription = "Saved Characters",
                                        tint = Color.Black
                                    )
                                }
                                // Knapp for å navigere til favorittkarakterer
                                IconButton(onClick = { navController.navigate("favorite_characters") }) {
                                    Icon(
                                        painter = painterResource(id = android.R.drawable.star_on),
                                        contentDescription = "Favorite Characters",
                                        tint = Color.Black
                                    )
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    // Hovednavigasjon mellom forskjellige skjermer
                    NavHost(
                        navController = navController,
                        startDestination = "character_list", // Startskjermen
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        // Skjerm for karakterliste
                        composable("character_list") {
                            CharacterListScreen(
                                viewModel = characterListViewModel,
                                onCharacterClick = { characterId ->
                                    // Navigerer til detaljskjermen for en valgt karakter
                                    navController.navigate("character_details/$characterId")
                                }
                            )
                        }
                        // Skjerm for å lage nye karakterer
                        composable("character_creation") {
                            CharacterCreationScreen(
                                viewModel = savedCharactersViewModel,
                                onCharacterCreated = { navController.popBackStack() }, // Tilbake til forrige skjerm etter oppretting
                                navController = navController
                            )
                        }

                        // Skjerm for detaljer om en spesifikk karakter
                        composable("character_details/{characterId}") { backStackEntry ->
                            val characterId = backStackEntry.arguments?.getString("characterId")?.toIntOrNull() ?: return@composable
                            val characterDetailsViewModel = viewModel { CharacterDetailsViewModel(CharacterRepository) }

                            LaunchedEffect(characterId) {
                                // Laster inn karakterdetaljer basert på ID
                                characterDetailsViewModel.loadCharacter(characterId)
                            }

                            CharacterDetailsScreen(
                                onBackButtonClick = { navController.popBackStack() }, // Tilbakeknapp
                                viewModel = characterDetailsViewModel
                            )
                        }
                        // Skjerm for lagrede karakterer
                        composable("saved_characters") {
                            SavedCharactersScreen(navController = navController, viewModel = savedCharactersViewModel)
                        }
                        // Skjerm for favorittkarakterer
                        composable("favorite_characters") {
                            FavoriteCharactersScreen(navController = navController, characterRepository = CharacterRepository)
                        }
                    }
                }
            }
        }
    }
}
