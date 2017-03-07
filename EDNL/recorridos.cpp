#include "abin.h"
#include <iostream>

using namespace std;

template <typename T>
void recorridoPreorden(Abin<T> &A){
  recorrerNodoP(A, A.raizB());
}

template <typename T>
void recorridoNodoP(Abin<T> &A, typename Abin<T>::nodo n){
  if(n != Abin<T>::NODO_NULO){
    procesar(A, n);
    recorridoNodoP(A, A.hijoIzqdoB(A, n));
    recorridoNodoP(A, A.hijoDrchoB(A, n));
  }
}

template <typename T>
void procesar(Abin<T> &A, typename Abin<T>::nodo n){
  cout << A.elemento(n) << endl;
}
