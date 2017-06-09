#include "alg_grafoPMC.h"
#include "alg_grafoMA.h"
#include <iostream>
#include <climits>

using namespace std;

//Ejercicio 1

struct Ciudad{
  double x, y;
};

double distancia(Ciudad c1, Ciudad c2){
  return sqrt(pow(c1->x - c2->x,2)+pow(c1->y - c2->y,2));
}

void rellenarGrafo(Lista<GrafoP>& Islas, size_t indexIslas, Lista<Grafo::vertice> ciudadesOrdenadas, size_t inicio, size_t fin, Grafo& G, vector<Ciudad> ciudades){
  for(size_t i = inicio; i < fin; i++)
    for (size_t j = inicio; j < fin; j++) //El vertice[ciudadI, CiudadJ] del grafo[index]
      Islas[indexIslas][ciudadesOrdenadas[i]][ciudadesOrdenadas[[j]] =
        (G[ciudadesOrdenadas[i]][ciudadesOrdenadas[j]]) ? distancia(cuidades[i],ciudades[j]) : INT_MAX;
}

template <typename tCoste>
Lista<matriz<tCoste>> tombuctu(vector<Ciudad> ciudades, Grafo& G, Lista<GrafoP> &Islas){
    vector<bool> visitadas = new vector(ciudades.size(), false);
    Lista<Grafo::vertice> ciudadesOrdenadas = Profundidad(G, 0);
    size_t indexIslas=0;
    size_t inicio=0;
    for(size_t i=0; i < ciudadesOrdenadas.size(); i++){
      if(i == ciudadesOrdenadas.size()-1 || !G[islas[i]][i+1]) //Para que añada el ultimo elemento al ultimo grafo
        rellenarGrafo(Islas, indexIslas, ciudadesOrdenadas, inicio, i, G, ciudades);
        inicio = i+1;
        indexIslas++;
    }

    Lista<matriz<tCoste>> Costes(Islas.size());
    for (size_t i = 0; i < Islas.size(); i++) {
      Costes[i] = Floyd(Islas[i], new matriz<GrafoP<tCoste>::vertice>);
    }
    return Costes;
}


//Ejercicio 4
/*Planteamiento:
  Al grafo inicial le cambio los valores a negativo y así al hacer Prim/Kruskall creará el arbol
  generador con los valores máximos.
*/
template<typename tCoste>
GrafoP<tCoste> invertir(GrafoP<tCoste> G){
  for(size_t i=0; i < G.numVert; i++){
    for (size_t j = 0; j < G.numVert; j++) {
      G[i][j] = 0 - G[i][j];
      if(i == j) G[i][j] = 0;
    }
  }
  return G;
}

template <typename tCoste>
GrafoP<tCoste> emasajer(GrafoP<tCoste> Ciudades){
  GrafoP G = invertir(Ciudades);
  G = invertir(Prim(G));//Lo reinvierto para que queden los costes positivos
  return G;
}

/*
Ejercicio 5
Planteamiento: Uso Prim para crear un arbol generador del grafo y le calculo el coste, ese sera el tamaño total
*/
template <typename tCoste>
tCoste reteuni3(GrafoP<tCoste> Ciudades){
  GrafoP G = Prim(Ciudades);
  tCoste t = 0;
  for (size_t i = 0; i < G.numVert; i++) {
    for (size_t j = 0; j < G.numVert; j++) {
      if(G[i][j]!=GrafoP::INFINITO)
        t+=G[i][j];
    }
  }
  return t/2; //La matriz es simétrica y sólo necesito la parte superior.
}

/*
Ejercicio 7
in: vector<Ciudad> Ciudades, Primera mitad = Fobos, Segunda = Deimos
    vector<bool> Costeras
    int Origen, Destino

out: tCoste Origen-Destino

Planteamiento:
Creo un supergrafo con todas las posibles uniones, y le realizo Prim para reducir al minimo
numero de caminos posibles, asumire q el coste de construir un puente es el doble de una carretera.

Tras haber calculado el arbol generador, reduzco el coste del puente a la mitad (en viaje cuesta lo mismo q una
carretera, y acordamos que el coste inicial seria el doble), realizo Dijkstra y calculo el coste minimo.

*/


template <typename tCoste>
GrafoP<tCoste> construirCiudad(int NF, int ND, vector<Ciudad> Ciudades, vector<bool> Costeras){
    GrafoP<tCoste> G(NF+ND);
    //Relleno
    for (int i = 0; i < NF+ND; ++i) {
        for (int j = 0; j < NF+ND; ++j) {
            if (i < NF && j < NF)
                //Fobos - Fobos
                G[i][j] = distancia(Ciudades[i], Ciudades[j]);
            if (i < NF && j >= NF)
                //Fobos - Deimos (Costes de puente dobles)
                if (Costeras[i] && Costeras[j]) G[i][j] = distancia(Ciudades[i], Ciudades[j])*2;
            if (i >= NF && j < NF)
                //Deimos - Fobos (Costes de puente dobles)
                if (Costeras[i] && Costeras[j]) G[i][j] = distancia(Ciudades[i], Ciudades[j])*2;
            if (i >= NF && j >= NF)
                //Deimos - Deimos
                G[i][j] = distancia(Ciudades[i], Ciudades[j]);
        }
    }
}


template <typename tCoste>
tCoste Grecoland(int NF, int ND, vector<Ciudad> Fobos, vector<Ciudad> Deimos, vector<bool> CosterasFobos, vector<bool> CosterasDeimos, int Origen, int Destino){

    //Creo vector con todas las ciudades y otro con todas las costeras
    vector<Ciudad> Ciudades(NF+ND);
    Ciudades.insert(Ciudades.end(), Fobos.begin(), Fobos.end());
    Ciudades.insert(Ciudades.end(), Deimos.begin(), Deimos.end());

    vector<bool> Costeras(NF+ND);
    Costeras.insert(Costeras.end(), CosterasFobos.begin(), CosterasFobos.end());
    Costeras.insert(Costeras.end(), CosterasDeimos.begin(), CosterasDeimos.end());

    GrafoP<tCoste> Greco(NF+ND) = construirCiudad(NF, ND, Ciudades, Costeras);

    Greco = Prim(Greco);//Elimino los innecesarios con Prim

    //Cambio los costes de los puentes a la mitad (En viaje cambian)

    for(int i = NF; i < NF+ND; i++) {
        for(int j = 0; j < NF; j++) {
            Greco[i][j] = Greco[i][j]/2;
            Greco[j][i] = Greco[i][j];
        }
    }
    //Realizo Dijkstra para recalcular los costes de los viajes y que me encuentre el mas corto
    return Dijkstra(Greco, Origen, vector<typename GrafoP<tCoste>::vertice>)[Destino];
}
