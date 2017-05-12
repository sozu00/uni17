#include "alg_grafoPMC.h"
#include "alg_grafoMA.h"
#include <iostream>
#include <climits>
//Ejercicio 2
template <typename tCoste>
tCoste DistMax(const matriz<tCoste>& m, typename GrafoP<tCoste>::vertice v){
  typedef typename GrafoP<tCoste>::vertice vertice;
  tCoste maximo, maximo2;
  maximo=0;
  for(int i=0; i<m.dimension(); i++){
    if(m[i][v]>maximo)
      maximo=m[i][v];
      
    else
      if(m[i][v] > maximo2)
        maximo2 = m[i][v];
  }
  return maximo+maximo2;
}

template <typename tCoste>
typename GrafoP<tCoste>::vertice Pseudocentro(const GrafoP<tCoste>& G, tCoste& t){
  typedef typename GrafoP<tCoste>::vertice vertice;
  matriz<tCoste> m = Floyd(G, new matriz<vertice>(G.numVert()));
  t = GrafoP<tCoste>::INFINITO;
  vertice Pseudoc;
  for(vertice i=0; i<G.numVert(); i++)
      if(DistMax(m, i) < t){
        t = DistMax(m,i);
        Pseudoc = i;
      }
  return Pseudoc;
}

template <typename tCoste>
tCoste diametro(const GrafoP<tCoste>& G){
  tCoste t;
  Pseudocentro(G, t);
  return t;
}


//Ejercicio 3 POR HACER
template <typename tCoste>
bool tieneCiclo(const GrafoP<tCoste>& G, typename GrafoP<tCoste>::vertice v){
  //POR HACER
}

template <typename tCoste>
bool esAciclico(const GrafoP<tCoste>& G){
  for(typename GrafoP<tCoste>::vertice i=0; i<G.numVert(); i++)
    if(tieneCiclo(G, i)) return false;
  return true;
}

//Ejercicio 4
template <typename tCoste>
matriz<tCoste> Zuelandia(const GrafoP<tCoste>& G, vector<typename GrafoP<tCoste>::vertice> CiudadesRebeldes, matriz<bool> Carreteras, typename GrafoP<tCoste>::vertice Capital){

  typedef typename GrafoP<tCoste>::vertice vertice;
  //Todas las conexiones con las Ciudades rebeldes pasan a ser infinito
  for(size_t i = 0; i<CiudadesRebeldes.size(); i++){
    for(size_t j = 0; j<G.numVert(); j++){
      G[CiudadesRebeldes[i]][j] = GrafoP<tCoste>::INFINITO;
      G[j][CiudadesRebeldes[i]] = GrafoP<tCoste>::INFINITO;
    }
  }
  //Todas las carreteras tomadas pasan a ser infinito
  for(size_t i = 0; i < G.numVert(); i++){
    for (size_t j = 0; j < G.numVert(); j++) {
      if(!Carreteras[i][j]) G[i][j] = GrafoP<tCoste>::INFINITO;
    }
  }
  //Calculo costes minimos desde todas las ciudades a la capital y desde la capital a todas las ciudades.
  vector<tCoste> DesdeCapital = Dijkstra(G, Capital, new vector<vertice>);
  vector<tCoste> HastaCapital = DijkstraInverso(G, Capital, new vector<vertice>);

  //Creo la matriz con los costes de i hasta Capital + Capital hasta j.
  matriz<tCoste> m;
  for (size_t i = 0; i < G.numVert(); i++) {
    for (size_t j = 0; j < G.numVert(); j++) {
      m[i][j] = DesdeCapital[i] + HastaCapital[j];
    }
  }
  return m;
}

//Ejercicio 5
//Devolveremos una matriz de enteros, que serán el número de saltos.

matriz<int> Dijkstra(
  const Grafo& G,
  typename Grafo::vertice origen,
  vector<typename Grafo::vertice>& P)
  {
    typedef typename Grafo::vertice vertice;
    vertice v, w;
    const size_t n = G.numVert();
    vector<bool> S(n, false);                  // Conjunto de vértices vacío.
    vector<int> D;                          // Costes mínimos desde origen.
    matriz<bool> m = Warshall(G);
    // Iniciar D y P con caminos directos desde el vértice origen.
    for (size_t i = 0; i < G.numVert(); i++) {
      if(G[origen][i]) D[i] = 1; //En caso de conectar pondremos el numero de saltos
      else D[i] = INT_MAX;
    }
    D[origen] = 0;                             // Coste origen-origen es 0.
    P = vector<vertice>(n, origen);

    // Calcular caminos de coste mínimo hasta cada vértice.
    S[origen] = true;                          // Incluir vértice origen en S.
    for (size_t i = 1; i <= n-2; i++) {
       // Seleccionar vértice w no incluido en S
       // con menor coste desde origen.
       int costeMin = INT_MAX;
       for (v = 0; v < n; v++)
          if (!S[v] && D[v] <= costeMin) {
             costeMin = D[v];
             w = v;
          }
       S[w] = true;                          // Incluir vértice w en S.
       // Recalcular coste hasta cada v no incluido en S a través de w.
       for (v = 0; v < n; v++)
          if (!S[v]) {
            int 0wv = (m[w][v]) ? D[w]+1 : INT_MAX;
            if (Owv < D[v]) {
              D[v] = Owv;
              P[v] = w;
            }
          }
    }
    return D;

}

int main(){
  std::cout << "HOLIS MUNDIS" << std::endl;
}
