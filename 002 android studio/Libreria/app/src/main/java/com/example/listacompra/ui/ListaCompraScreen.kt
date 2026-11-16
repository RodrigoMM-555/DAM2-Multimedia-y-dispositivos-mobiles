package com.example.listacompra.ui

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.listacompra.R
import com.example.listacompra.data.Producto

@Composable
fun ListaCompraScreen(
    modifier: Modifier = Modifier
) {
    // Lista observable de productos.
    val productos = remember { mutableStateListOf<Producto>() }

    // Estado del campo de texto.
    var nombreProducto by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.screen_padding))
    ) {

        // Título de la aplicación.
        Text(
            text = stringResource(R.string.screen_title),
            style = MaterialTheme.typography.headlineMedium
        )

        // Fila para introducir y añadir productos.
        Row {
            OutlinedTextField(
                value = nombreProducto,
                onValueChange = { nombreProducto = it },
                label = {
                    Text(stringResource(R.string.product_hint))
                },
                modifier = Modifier.weight(1f),
                singleLine = true
            )

            Button(
                onClick = {
                    if (nombreProducto.isNotBlank()) {

                        val nuevoProducto = Producto(
                            id = productos.size + 1,
                            nombre = nombreProducto.trim()
                        )

                        productos.add(nuevoProducto)

                        Log.d(
                            "ListaCompra",
                            "Producto añadido: ${nuevoProducto.nombre}"
                        )

                        nombreProducto = ""
                    }
                },
                enabled = nombreProducto.isNotBlank()
            ) {
                Text(stringResource(R.string.add_button))
            }
        }

        // Si no hay productos, mostramos un mensaje.
        if (productos.isEmpty()) {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.empty_list),
                    style = MaterialTheme.typography.bodyLarge
                )
            }

        } else {

            // Si hay productos, mostramos la lista.
            LazyColumn {
                items(
                    items = productos,
                    key = { producto -> producto.id }
                ) { producto ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                vertical = dimensionResource(R.dimen.screen_padding) / 4
                            )
                    ) {
                        Text(
                            text = producto.nombre,
                            modifier = Modifier.padding(
                                dimensionResource(R.dimen.screen_padding)
                            )
                        )
                    }
                }
            }
        }
    }
}
