#include "abin.h"
#include <iostream>

template <typename T>
int max(int a, b){
  return (a > b)? a : b;
}

template <typename T>
int numnodos(Abin<T>& a){
  return NodoMasnumhijos(a, a.raizB());
}

template <typename T>
int NodoMasnumhijos(Abin<T>& A, typename Abin<T>::nodo n){
  return (n == Abin::NODO_NULO) ? 0 : 1 + NodoMasnumhijos(A,A.hijoIzqdoB(n)) + NodoMasnumhijos(A, A.hijoDrchoB(n));
}

template <typename T>
int altura(Abin<T>& A){
  return alturaNodo(A, a.raizB());
}

template <typename T>
int alturaNodo(Abin<T>& A, typename Abin<T>::nodo n){
  return (n == NODO_NULO) ? -1 : 1 + max(alturaNodo(A, A.hijoIzqdoB(n)), alturaNodo(A, A.hijoDrchoB(n)));
}

template <typename T>
int profundidadNodo(Abin<T>& A, typename Abin<T>::nodo n){
  int prof = 0;
  for(typename Abin<T>::nodo aux = n; A.padreB(aux) != Abin::NODO_NULO;  aux = A.padreB(aux)){
    ++prof;
  }
  return prof;
}


int main(){
  Abin<int> a;
  a.insertarRaizB(0);

  a.insertarHijoIzqdoB(a.raizB(), 1);
  a.insertarHijoDrchoB(a.raizB(), 2);
  a.insertarHijoIzqdoB(a.hijoIzqdoB(a.raizB()), 3);
  std::cout << numnodos(a);
}
