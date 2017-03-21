#include "abin.h"


/*
  rebelde = mayoría ancestros positivo, y él negativo
*/
template <typename T>
int rebeldes(Abin<bool> &A){
  return rebeldesRec(A, A.raizB(), 0);
}

template <typename T>
int rebeldesRec(Abin<bool> &A, Abin<bool>::nodo n, int profundidad){
  if(n != Abin<T>::NODO_NULO){
    if(nodoRebelde(A,n,profundidad))
      return 1 + rebeldesRec(A, A.hijoIzqdoB(n), profundidad+1) + rebeldesRec(A, A.hijoIzqdoB(n), profundidad+1);
    else
      return 0 + rebeldesRec(A, A.hijoIzqdoB(n), profundidad+1) + rebeldesRec(A, A.hijoIzqdoB(n), profundidad+1);
  }
  else return 0;
}

template <typename T>
bool nodoRebelde(Abin<bool> &A, Abin<bool>::nodo n, int profundidad){
  bool actitudNodo = A.elemento(n);
  int numAntecesoresContrarios = 0;
  for(Abin<bool>::nodo p = A.padreB(n); p != Abin<T>::NODO_NULO; p = A.padreB(p)){
    if(actitudNodo != A.elemento(n)) ++numAntecesoresContrarios;
    if(numAntecesoresContrarios > (profundidad/2)) return true;
  }
  return false;
}
