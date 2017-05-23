#include "alg_grafoPMC.h"
#include "alg_grafoMA.h"
#include <iostream>
#include <climits>

using namespace std;

//Ejercicio 1

struct Ciudad{
  double x, y;
}

double distancia(Ciudad c1, Ciudad c2){
  return sqrt(pow(c1->x - c2->x,2)+pow(c1->y - c2->y,2));
}

void rellenarGrafo(Lista<GrafoP>& Islas, size_t indexIslas, Lista<Grafo::vertice> ciudadesOrdenadas, size_t inicio, size_t fin, Grafo& G, vector<Ciudad> ciudades){
  for(size_t i = inicio; i < fin; i++)
    for (size_t j = inicio; j < fin; j++) //El vertice[ciudadI, CiudadJ] del grafo[index]
      Islas[indexIslas][ciudadesOrdenadas[i]][ciudadesOrdenadas[[j] =
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
