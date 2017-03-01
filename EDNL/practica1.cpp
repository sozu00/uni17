#include "abin.h"

int numnodos(Abin a){
  return NodoMasnumhijos(a, a.raizB());
}

int NodoMasnumhijos(Abin A, Abin::nodo n){
  return (n == Abin::NODO_NULO) ? 0 : 1 + NodoMasnumhijos(A,A.hijoIzqdoB(n)) + NodoMasnumhijos(A, A.hijoDrchoB(n));
}
