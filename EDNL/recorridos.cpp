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

/*

//ARBOLES GENERALES
template <typename T>
void Preorden(Agen<T> &A, typename Agen<T>::nodo n){
  if (n != Agen<T>::NODO_NULO){
    procesar(A,n);
    Preorden(A, A.hijoIzqdo(n));
    Preorden(A, A.hermanoDrcho(n));
  }
}

template <typename T>
void Inorden(Agen<T> &A, typename Agen<T>::nodo n){
  if (n != Agen<T>::NODO_NULO){
    Inorden(A, A.hijoIzqdo(n));
    procesar(A,n);
    if(A.hijoIzqdo(n) != Agen<T>::NODO_NULO){//Sin el
      for (Agen<T>::nodo her = A.hermanoDrcho(A.hijoIzqdo(n)); her != Agen<T>::NODO_NULO; her = A.hermanoDrcho(her)){
        Inorden(A, her);
      }
    }
  }
}

template <typename T>
void PostOrden(Agen<T> &A, typename Agen<T>::nodo n){
  if(n != Agen<T>::NODO_NULO){
    PostOrden(A, A.hijoIzqdo(n));
    if(A.hijoIzqdo(n) != Agen<T>::NODO_NULO){
      for (Agen<T>::nodo her = A.hermanoDrcho(A.hijoIzqdo(her)); her != Agen<T>::NODO_NULO; her = A.hermanoDrcho(her)){
        Postorden(A, her);
      }
    }
    procesar(A, n);
  }
}
*/
