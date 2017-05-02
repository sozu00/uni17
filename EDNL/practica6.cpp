#include "alg_grafoPMC.h"
#include <iostream>

//Ejercicio 2
template <typename tCoste>
tCoste DistMax(const matriz<tCoste>& m, typename GrafoP<tCoste>::vertice v){
  typedef typename GrafoP<tCoste>::vertice vertice;
  tCoste maximo, maximo2;
  maximo=0;
  for(int i=0; i<m.dimension(); i++)
    if(m[i][v]>maximo){
      maximo2=maximo;
      maximo=m[i][v];
    }
  return maximo+maximo2;
}

template <typename tCoste>
typename GrafoP<tCoste>::vertice Pseudocentro(const GrafoP<tCoste>& G){
  typedef typename GrafoP<tCoste>::vertice vertice;
  matriz<tCoste> m = Floyd(G, new matriz<vertice>(G.numVert()));
  tCoste minimo = GrafoP<tCoste>::INFINITO;
  vertice Pseudoc;
  for(vertice i=0; i<G.numVert(); i++)
      if(DistMax(m, i) < minimo){
        minimo = DistMax(m,i);
        Pseudoc = i;
      }
  return Pseudoc;
}

int main(){
  std::cout << "HOLIS MUNDIS" << std::endl;
}
