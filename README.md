# TicketmasterMVVM
Es una aplicación en la que uso por primera vez la arquitetura MVVM con la API de TicketMaster, la aplicación esta dividida en 2 branchs.

La rama main unicamente tiene hasta la busqueda sin paginación ni el drawer menu.
La rama pagination contiene la versión "final"

La lista de eventos de la sección "Eventos cerca de ti" esta configurada para que cargue los eventos en un radio de 50 millas considerando la ubicación del usuario, los botones de corazon y cargar en la pantala de eventos de detalle de momento no realizan ninguna función ya que era una implementación de Room que por cuestiones de tiempo se dejo incompleto.

Entre las librerias que se usaron estan:

Como pruebas para el nav menu del drawer (no tienen nada en su vista)
// Fragment
implementation "androidx.fragment:fragment-ktx:1.3.2"

// Activity
implementation "androidx.activity:activity-ktx:1.2.2"

Procesamiento de estados y manejo de información
// ViewModel
implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1"

// LiveData
implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.3.1"

//Corrutinas
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.6'
    
 Realizar llamadas a la API   
// Retrofit
    implementation "com.squareup.retrofit2:retrofit:2.9.0"
    implementation "com.squareup.retrofit2:converter-gson:2.9.0"  
    
//RecyclerView
    implementation 'androidx.recyclerview:recyclerview:1.2.0'    

Convertir los links en imagenes
//Picasso
    implementation "com.squareup.picasso:picasso:2.71828"
    
 Aunque la API tiene soporte para encontrar eventos por latitud y longitud, recomiendan usar geohash en lugar de latitud/logitud   
//Geohash
    implementation("ch.hsr:geohash:1.4.0")    
  
  Servicios de ubicación
 //Location
    implementation 'com.google.android.gms:play-services-location:18.0.0'   
    
Screenshots

![Screenshot_20220129_085310_com example ticketmastermvvm](https://user-images.githubusercontent.com/83688841/151666247-b1aca843-9435-4127-af9b-c54717f2160f.jpg)
![Screenshot_20220129_085314_com example ticketmastermvvm](https://user-images.githubusercontent.com/83688841/151666253-00395965-d9e8-46c5-aedd-5cda59f35fac.jpg)

Demo
https://youtu.be/lNFWB1_4cQQ
