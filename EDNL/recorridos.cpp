#include "abin.h"
#include <iostream>

using namespace std;

template <typename T>
void recorridoPreorden(Abin<T> &A){
  recorrerNodoP(A, A.raizB());
}

template <typename T>
void Preorden(Abin<T> &A, typename Abin<T>::nodo n){
  if(n != Abin<T>::NODO_NULO){
    procesar(A, n);
    Preorden(A, A.hijoIzqdoB(A, n));
    Preorden(A, A.hijoDrchoB(A, n));
  }
}

template <typename T>
void Inorden(Abin<T> &A, typename Abin<T>::nodo n){
  if(n != Abin<T>::NODO_NULO){
    Inorden(A, A.hijoIzqdoB(A, n));
    procesar(A, n);
    Inorden(A, A.hijoDrchoB(A, n));
  }
}

template <typename T>
void Postorden(Abin<T> &A, typename Abin<T>::nodo n){
  if(n != Abin<T>::NODO_NULO){
    Postorden(A, A.hijoIzqdoB(A, n));
    Postorden(A, A.hijoDrchoB(A, n));
    procesar(A, n);
  }
}

template <typename T>
void procesar(Abin<T> &A, typename Abin<T>::nodo n){
  cout << A.elemento(n) << endl;
}
