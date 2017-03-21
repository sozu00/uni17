#include "abin.h"
/*
  rebelde = mayoría ancestros positivo, y él negativo
*/
template <typename T>
bool nodoRebelde(Abin<T> &A, typename Abin<T>::nodo n, int profundidad){
  bool actitudNodo = A.elemento(n);
  int numAntecesoresContrarios = 0;
  for(Abin<bool>::nodo p = A.padreB(n); p != Abin<T>::NODO_NULO; p = A.padreB(p)){
    if(actitudNodo != A.elemento(n)) ++numAntecesoresContrarios;
    if(numAntecesoresContrarios > (profundidad/2)) return true;
  }
  return false;
}

template <typename T>
int rebeldesRec(Abin<T> &A, typename Abin<T>::nodo n, int profundidad){
  if(n != Abin<T>::NODO_NULO){
    if(nodoRebelde(A,n,profundidad))
      return 1 + rebeldesRec(A, A.hijoIzqdoB(n), profundidad+1) + rebeldesRec(A, A.hijoIzqdoB(n), profundidad+1);
    else
      return 0 + rebeldesRec(A, A.hijoIzqdoB(n), profundidad+1) + rebeldesRec(A, A.hijoIzqdoB(n), profundidad+1);
  }
  else return 0;
}

template <typename T>
int rebeldes(Abin<T> &A){
  return rebeldesRec(A, A.raizB(), 0);
}

template <typename T>
bool hijosProsperos(Abin<T> &A, Abin<T>::nodo n, Abin<T>::nodo origen){
  if(n == Abin<T>::NODO_NULO) return true;
  return (A.elemento(n) > A.elemento(origen)) && hijosProsperos(A, A.hijoIzqdoB(n), origen) && hijosProsperos(A, A.hijoDrchoB(n), origen);
}

template <typename T>
bool padresProsperos(Abin<T> &A, Abin<T>::nodo n){
  Abin<T>::nodo ancestro = A.padreB(n);
  while(ancestro != Abin<T>::NODO_NULO){
    if(A.elemento(ancestro)>A.elemento(n)) return false;
    ancestro = A.padreB(ancestro);
  }
  return true;
}

template <typename T>
bool nodoProspero(Abin<T> &A, typename Abin<T>::nodo n){
  return padresProsperos(A,n) && hijosProsperos(A, n, n);
}

template <typename T>
int prosperosRec(Abin<T> &A, typename Abin<T>::nodo n){
  if(n != Abin<T>::NODO_NULO){
    if(nodoProspero(A,n))
      return 1 + prosperosRec(A, A.hijoIzqdoB(n)) + prosperosRec(A, A.hijoDrchoB(n));
    else
      return prosperosRec(A, A.hijoIzqdoB(n)) + prosperosRec(A, A.hijoDrchoB(n));
  }
  else return 0;
}

template <typename T>
int prosperos(Abin<T> &A){
  return prosperosRec(A, A.raizB());
}





int main(){

}
