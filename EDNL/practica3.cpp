#include "Agen.h"
#include "agen_E-S.h"

template <typename T>
int gradoNodo(Agen<T> &A, typename Agen<T>::nodo n){//Cuento el numero de hijos de 1 nodo
  int g = 0;
  for(typename Agen<T>::nodo her = A.hijoIzqdo(n); her != Agen<T>::NODO_NULO; her = A.hermDrcho(her))
    g++;
  return g;
}

template <typename T>
int gradoAgenRec(Agen<T> &A, typename Agen<T>::nodo n){
  int max = gradoNodo(A,n);
  for(typename Agen<T>::nodo her = A.hijoIzqdo(n); her != Agen<T>::NODO_NULO; her = A.hermDrcho(her)){
    max = max < gradoAgenRec(A, her) ? gradoAgenRec(A, her) : max;
  }
  return max;
}

template <typename T>
int gradoAgen(Agen<T> &A){
  return gradoAgenRec(A, A.raiz());
}

template <typename T>
int profundidad(Agen<T> &A, typename Agen<T>::nodo n){
  if(n != Agen<T>::NODO_NULO)
    return 1 + profundidad(A.padre(n));
  return -1;
}

template <typename T>
int altura(Agen<T> &A, typename Agen<T>::nodo n){
  if(n != Agen<T>::NODO_NULO){
    int max = 0;
    for(typename Agen<T>::nodo her = A.hijoIzqdo(n); her != Agen<T>::NODO_NULO; her = A.hermDrcho(her)){
      max = max < altura(A, her) ? altura(A, her) : max;
      //Busco la altura mayor -> Crea mayor numero de vueltas
    }
    return 1 + max;
    //Cada vez que llame a la altura de un hijo, será 1 mayor.
  }
  return -1;
}

template <typename T>
int desequilibrioNodo(Agen<T> &A, typename Agen<T>::nodo n){
  if(n != Agen<T>::NODO_NULO){
    int alturaMax = 0;
    int alturaMin = altura(A, n);
    int desMax= 0;
    int difAlt;
    for(typename Agen<T>::nodo her = A.hijoIzqdo(n); her != Agen<T>::NODO_NULO; her = A.hermDrcho(her)){
      int alt = altura(A, her);
      int des = desequilibrioNodo(A, her);
      desMax = desMax > des ? desMax : des;
      alturaMax = alturaMax < alt ? alt : alturaMax;
      alturaMin = alturaMin > alt ? alt : alturaMin;
    }
    difAlt = alturaMax - alturaMin;
    return desMax > difAlt ? desMax : difAlt;
  }
  return 0;
}

template <typename T>
int desequilibrio(Agen<T> &A){
  return desequilibrioNodo(A, A.raiz());
}

template <typename T>
typename Agen<T>::nodo busquedaNodo(Agen<T> &A, typename Agen<T>::nodo n, int x){
  typename Agen<T>::nodo rec = n;
  if(A.elemento(rec) == x) return rec;
  rec = busquedaNodo(A, A.hijoIzqdo(rec), x);
  if(A.elemento(rec) == x) return rec;
  rec = busquedaNodo(A, A.hermDrcho(rec), x);
  if(A.elemento(rec) == x) return rec;
  return Agen<T>::NODO_NULO;
}

template <typename T>
void podar(Agen<T> &A, typename Agen<T>::nodo n){
  if(n != Agen<T>::NODO_NULO){
    podar(A, A.hijoIzqdo(n));
    A.eliminarHijoIzqdo(n);
    podar(A, A.hermDrcho(n));
    A.eliminarHermDrcho(n);
  }
}

template <typename T>
void podaArbol(Agen<T> &A, int x){
  typename Agen<T>::nodo n = busquedaNodo(A,A.raiz(), x);
  podar(A, n);
}


/*
int main(){
  Agen<int> A;
  A.crearRaiz(0);
  A.insertarHijoIzqdo(A.raiz(), 1);
  Agen<int>::nodo n = A.hijoIzqdo(A.raiz());
  A.insertarHijoIzqdo(n, 3);
  A.insertarHermDrcho(A.hijoIzqdo(A.raiz()), 2);
  std::cout << altura(A, A.raiz()) << std::endl;
}
*/
