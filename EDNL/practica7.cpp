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


//Ejercicio 5
template <typename tCoste>
vector<Grafo::vertice> infatigableViajero(
  typename tCoste presupuesto,
  int alergia,
  GrafoP<tCoste> Carretera,
  GrafoP<tCoste> Tren,
  GrafoP<tCoste> Avion,
  typename GrafoP<tCoste>::vertide &origen){

  GrafoP<tCoste>SuperGrafo = new GrafoP<>(Tren.numVert());

  for(size_t i=0; i < Tren.numVert();i++){
    for (size_t j = 0; j < Tren.numVert(); j++) {
      switch (alergia) {
        case 1: SuperGrafo[i][j] = std::min(Tren[i][j], Avion[i][j]);break;
        case 2: SuperGrafo[i][j] = std::min(Carretera[i][j], Avion[i][j]);break;
        case 3: SuperGrafo[i][j] = std::min(Tren[i][j], Carretera[i][j]);break;
        default: SuperGrafo[i][j] = 0;
    }
  }
  vector<tCoste> P = Dijkstra(SuperGrafo,origen, new vector<Grafo::vertice>());
  vector<Grafo::vertice> Ciudades;
  for(int i = 0; i < P.size(); i++)
    if(P[i] >= presupuesto) Ciudades.insert(i);
  return Ciudades;
}


//Ejercicio 6
template <typename tCoste>
GrafoP<tCoste> agenciaTaxis(GrafoP<tCoste> Tren, GrafoP<tCoste> Bus, typename GrafoP<tCoste>::vertice Conector){
  matriz<tCoste> TodoBus = Floyd(Bus, new matriz<typename GrafoP<tCoste>::vertice>);
  matriz<tCoste> TodoTren = Floyd(Tren, new matriz<typename GrafoP<tCoste>::vertice>);
  GrafoP<tCoste> Final = new GrafoP<tCoste>(Tren.numVert());

  for (size_t i = 0; i < Tren.numVert(); i++) {
    for (size_t j = 0; j < Tren.numVert(); j++) {
      tCoste BusTren = TodoBus[i][Conector] + TodoTren[Conector][j];
      tCoste TrenBus = TodoTren[i][Conector] + TodoBus[Conector][j];
      Final[i][j] = std::min(std::min(BusTren, TrenBus), std::min(TodoBus[i][j], TodoTren[i][j]));
    }
  }
  return Final;
}

//Ejercicio 7
template <typename tCoste>
vector<Grafo::vertice> dosCambios(
  Grafo<tCoste> Tren,
  GrafoP<tCoste> Bus,
typename GrafoP<tCoste>::vertice origen,
typename GrafoP<tCoste>::vertice destino
typename GrafoP<tCoste>::vertice cambio1,
typename GrafoP<tCoste>::vertice cambio2){
}

//Ejercicio 8
template <typename tCoste>
tCoste Trasbordo(
  Grafo<tCoste> Tren,
  GrafoP<tCoste> Bus,
  typename GrafoP<tCoste>::vertice origen,
  typename GrafoP<tCoste>::vertice destino){

  vector<tCoste> idaBus = Dijkstra(Bus,origen, new vector<Grafo::vertice>());
  vector<tCoste> idaTren = Dijkstra(Tren,origen, new vector<Grafo::vertice>());
  vector<tCoste> vueltaBus = DijkstraInverso(Bus,destino, new vector<Grafo::vertice>());
  vector<tCoste> vueltaTren = DijkstraInverso(Tren,destino, new vector<Grafo::vertice>());

  tCoste Final = min(idaBus[destino], idaTren[destino]);
  for (size_t Conector = 0; Conector < Tren.numVert(); Conector++) {
        tCoste BusTren = idaBus[Conector] + vueltaTren[Conector];
        tCoste TrenBus = idaTren[Conector] + vueltaBus[Conector];
        Final = min(Final, min(BusTren, TrenBus));
    }
  return Final;
}


//Ejercicio 9
template <typename tCoste>
typename GrafoP<tCoste>::tCamino CambioCoste(
  Grafo<tCoste> Tren,
  GrafoP<tCoste> Bus,
  typename GrafoP<tCoste>::vertice origen,
  typename GrafoP<tCoste>::vertice destino,
  tCoste trasbordo){

    GrafoP<tCoste> SuperGrafo(Tren.numVert()*2);
    for (size_t i = 0; i < Tren.numVert(); i++) {
      for (size_t j = 0; j < Tren.numVert(); j++) {
        SuperGrafo[i][j] = Bus[i][j];
        SuperGrafo[i+Tren.numVert()][j+Tren.numVert()] = Tren[i][j];
        SuperGrafo[i+Tren.numVert()][j] = GrafoP::INFINITO;
        SuperGrafo[i][j+Tren.numVert()] = GrafoP::INFINITO;
      }
      SuperGrafo[i+Tren.numVert()][i] = trasbordo;
      SuperGrafo[i][i+Tren.numVert()] = trasbordo;
    }

    vector<GrafoP::vertice> OrigenBus();
    vector<tCoste> OrBus = Dijkstra(SuperGrafo, origen, new vector<GrafoP::vertice>());

    vector<GrafoP::vertice> OrigenTren();
    vector<tCoste> OrTren = Dijkstra(SuperGrafo, origen+Bus.numVert(), new vector<GrafoP::vertice>());

    GrafoP<tCoste>::tCamino minimo = tCamino(origen, destino);
    if(OrBus[destino+Bus.numVert()] < OrBus[destino]) minimo = tCamino(origen, destino+Bus.numVert());
    if(OrTren[destino] < minimo) minimo = tCamino(origen+Bus.numVert(), destino);
    if(OrTren[destino+Bus.numVert()]) minimo = tCamino(origen+Bus.numVert(), destino+Bus.numVert())

    return minimo;
}
