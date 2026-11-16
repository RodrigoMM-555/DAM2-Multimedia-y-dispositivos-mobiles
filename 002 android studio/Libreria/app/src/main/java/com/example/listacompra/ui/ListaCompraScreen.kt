package com.example.listacompra.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import com.example.listacompra.R
import com.example.listacompra.data.Producto

@Composable
fun ListaCompraScreen(
    modifier: Modifier = Modifier
) {
    val productos = remember { mutableStateListOf<Producto>() }

    var nombreProducto by remember { mutableStateOf("") }

    // Identificador incremental para evitar duplicados.
    var siguienteId by remember { mutableStateOf(1) }

    // Calculamos cuántos productos quedan pendientes.
    val pendientes = productos.count { !it.comprado }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.screen_padding))
    ) {

        Text(
            text = stringResource(R.string.screen_title),
            style = MaterialTheme.typography.headlineMedium
        )

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
                            id = siguienteId,
                            nombre = nombreProducto.trim()
                        )

                        productos.add(nuevoProducto)
                        siguienteId++

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

        // Contador de productos pendientes.
        Text(
            text = pluralStringResource(
                R.plurals.pending_products,
                pendientes,
                pendientes
            ),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(vertical = dimensionResource(R.dimen.screen_padding) / 2)
        )

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

            LazyColumn {
                items(
                    items = productos,
                    key = { producto -> producto.id }
                ) { producto ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = dimensionResource(R.dimen.screen_padding) / 4)
                    ) {

                        Box(
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        end = dimensionResource(R.dimen.screen_padding) * 4,
                                        start = dimensionResource(R.dimen.screen_padding),
                                        top = dimensionResource(R.dimen.screen_padding),
                                        bottom = dimensionResource(R.dimen.screen_padding)
                                    ),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Text(
                                    text = producto.nombre,
                                    modifier = Modifier.weight(1f),
                                    style = MaterialTheme.typography.bodyLarge
                                )

                                // Botón para eliminar el producto.
                                IconButton(
                                    onClick = {
                                        productos.remove(producto)

                                        Log.d(
                                            "ListaCompra",
                                            "Producto eliminado: ${producto.nombre}"
                                        )
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = stringResource(
                                            R.string.delete_product_description
                                        ),
                                        tint = MaterialTheme.colorScheme.error
                                    )
                                }
                            }

                            // Icono de estado situado en la esquina superior derecha.
                            IconButton(
                                onClick = {
                                    val indice = productos.indexOf(producto)

                                    if (indice != -1) {
                                        productos[indice] = producto.copy(
                                            comprado = !producto.comprado
                                        )
                                    }
                                },
                                modifier = Modifier.align(Alignment.TopEnd)
                            ) {
                                Icon(
                                    imageVector = if (producto.comprado) {
                                        Icons.Default.CheckCircle
                                    } else {
                                        Icons.Default.RadioButtonUnchecked
                                    },
                                    contentDescription = stringResource(
                                        if (producto.comprado) {
                                            R.string.bought_description
                                        } else {
                                            R.string.not_bought_description
                                        }
                                    ),
                                    tint = if (producto.comprado) {
                                        MaterialTheme.colorScheme.primary
                                    } else {
                                        MaterialTheme.colorScheme.onSurfaceVariant
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}