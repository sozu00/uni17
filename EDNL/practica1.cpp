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
int NodoMasnumhijos(Abin<T>& A, typename Abin<T<::nodo n){
  return (n == Abin::NODO_NULO) ? 0 : 1 + NodoMasnumhijos(A,A.hijoIzqdoB(n)) + NodoMasnumhijos(A, A.hijoDrchoB(n));
}

template <typename T>
int altura(Abin<T>& A){
  return (A.raizB() == Abin::NODO_NULO) ? 0 : 1 + max()
}


int main(){
  Abin<int> a;
  a.insertarRaizB(0);

  a.insertarHijoIzqdoB(a.raizB(), 1);
  a.insertarHijoDrchoB(a.raizB(), 2);
  a.insertarHijoIzqdoB(a.hijoIzqdoB(a.raizB()), 3);
  std::cout << numnodos(a);
}
