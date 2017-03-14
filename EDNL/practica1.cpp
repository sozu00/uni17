#include "abin.h"
#include "abin_E-S.h"
#include <iostream>

int max(int a, int b){
  return (a > b)? a : b;
}

template <typename T>
int numnodos(Abin<T>& a){
  return NodoMasnumhijos(a, a.raizB());
}

template <typename T>
int NodoMasnumhijos(Abin<T>& A, typename Abin<T>::nodo n){
  return (n == Abin<T>::NODO_NULO) ? 0 : 1 + NodoMasnumhijos(A,A.hijoIzqdoB(n)) + NodoMasnumhijos(A, A.hijoDrchoB(n));
}

template <typename T>
int altura(Abin<T>& A){
  return alturaNodo(A, A.raizB());
}

template <typename T>
int alturaNodo(Abin<T>& A, typename Abin<T>::nodo n){
  return (n == Abin<T>::NODO_NULO) ? -1 : 1 + max(alturaNodo(A, A.hijoIzqdoB(n)), alturaNodo(A, A.hijoDrchoB(n)));
}

template <typename T>
int profundidadNodo(Abin<T>& A, typename Abin<T>::nodo n){
  int prof = 0;
  for(typename Abin<T>::nodo aux = n; A.padreB(aux) != Abin<T>::NODO_NULO;  aux = A.padreB(aux)) ++prof;
  return prof;
}

template <typename T>
int desequilibrioNodo(Abin<T>& A, typename Abin<T>::nodo n){
  //Maxima diferencia de alturas
  int difAlt = abs(alturaNodo(A, A.hijoIzqdoB(n) - alturaNodo(A, A.hijoDrchoB(n))));
  int difMax = max(difAlt, max(desequilibrio(A, A.hijoIzqdoB(n)), desequilibrio(A, A.hijoDrchoB(n))));
  return difMax;
}

template <typename T>
int desequilibrio(Abin<T>& A){
  return desequilibrioNodo(A, A.raizB());
}

template <typename T>
bool pseudoCompleto(Abin<T>& A){
  //Penultimo nivel lleno o vacío
  //Busco nodos con profundidad = alturaArbol - 1
  int penLvL = altura(A) - 1;
  return cumplePenultimo(A, A.raizB(), penLvL);
}

template <typename T>
bool cumplePenultimo(Abin<T>& A, typename Abin<T>::nodo n, int penLvL){
  if(n == Abin<T>::NODO_NULO) return true;
  if(profundidadNodo(A, n) == penLvL){
    if(A.hijoIzqdoB(n) == Abin<T>::NODO_NULO) return (A.hijoDrchoB(n) == Abin<T>::NODO_NULO);
    else return (A.hijoDrchoB(n) != Abin<T>::NODO_NULO);
  }
  else return (cumplePenultimo(A, A.hijoIzqdoB(n), penLvL) && cumplePenultimo(A, A.hijoDrchoB(n), penLvL));
}


int main(){
  Abin<int> a;
  rellenarAbin(a, 1);
  std::cout << pseudoCompleto(a) << std::endl;
}
