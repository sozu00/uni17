#include "alg_grafoPMC.h"
#include "alg_grafoMA.h"
#include "grafoLA.h"
#include <iostream>
#include <climits>

using namespace std;
//Ejercicio 1
template <typename tCoste>
tCoste CosteMax(typename GrafoP<tCoste>::vertice &origen,
  typename GrafoP<tCoste>::vertice &destino,
  const GrafoP<tCoste>& G)
  {
    GrafoP<tCoste> Ginv;
    for(int i = 0; i < G.numVert(); i++){
      for(int j = 0; j < G.numVert(); j++){
        if(G[i][j] != GrafoP<tCoste>::INFINITO)
          G[i][j] = -Ginv[i][j];
        else G[i][j] = GrafoP<tCoste>::INFINITO;
      }
    }
    matriz<typename GrafoP<tCoste>::vertice> P;
    matriz<tCoste> Costes = Floyd(Ginv,P);
    tCoste min = 0;
    for(int i = 0; i < Ginv.numVert(); i++){
      for(int j = 0; j < Ginv.numVert(); j++){
        if(Costes[i][j] < min){
          origen = i;
          destino = j;
          min = Costes[i][j];
        }
      }
    }
    return -min;
  }

//Ejercicio 2
struct Pared{
  public:
    int c1, c2;
};

bool esAdyacente(int i, int j, vector<Pared> paredes, int N){
  for(Pared p : paredes){
    if(p.c1 == i and p.c2 == j) return false;
    if(p.c1 == j and p.c2 == i) return false;
  }
  if(j == i+1 || j == i-1 || j == i+N || j == i-N) return true;
  return false;
}

vector<Grafo::vertice> Laberinto(const int N,
  vector<Pared> paredes,
  int origen, int destino
  {
    std::vector<String> txtLab(N, "");
    for (size_t i = 0; i < N; i++) {
      txtLab[i] = txtLab[i] + toString(i)+ ":";
      for (size_t j = 0; j < N; j++) {
        if(esAdyacentes(i,j, paredes, N))
          txtLab[i] = txtLab[i] + " " + toString(j);
      }
    }
    String grafiti = "";
    for(String p : txtLab){
        grafiti = grafiti + p + "\n";
    }
    Grafo G(grafiti);
    vector<typename GrafoP<tCoste>::vertice> Rest;
    /*REHACER Dijkstra
    */
    vector<tCoste> costes = Dijkstra(G, origen, Rest);
    return Rest;

}

//Ejercicio 3
