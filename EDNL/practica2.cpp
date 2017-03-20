#include "abin.h"
#include "abin_E-S.h"
#include <iostream>

template <typename T>
bool similares(Abin<T>& A, Abin<T>& B){
  subarbolSimilar(A,B,A.raizB(),B.raizB());
}

template <typename T>
bool subarbolSimilar(Abin<T>& A, Abin<T>& B, typename Abin<T>::nodo nA, typename Abin<T>::nodo nB){
  if(nA == Abin<T>::NODO_NULO)
    return (nB == Abin<T>::NODO_NULO) ? true : false; //Si los 2 son nulos true, si A si y B no, false

  if(nB == Abin<T>::NODO_NULO) //A NO es nulo ya
    return false;

  //Ni A ni B son nulos
  return subarbolSimilar(A, B, A.hijoIzqdoB(nA), B.hijoIzqdoB(nB)) && subarbolSimilar(A, B, A.hijoDrchoB(nA), B.hijoDrchoB(nB));
}


template <typename T>
Abin<T> reflejado(Abin<T>& A){
  Abin<T> B;
  if(A.raizB() != Abin<T>::NODO_NULO){
    B.insertarRaizB(A.elemento(A.raizB()));
    reflexion(A, B, A.raizB(), B.raizB());
  }
  return B;
}

template <typename T>
void reflexion(Abin<T>& A,Abin<T>& B, typename Abin<T>::nodo nA, typename Abin<T>::nodo nB){
  if(A.hijoDrchoB(nA) != Abin<T>::NODO_NULO){
    B.insertarHijoIzqdoB(nB, A.elemento(A.hijoDrchoB(nA)));
    reflexion(A,B,A.hijoDrchoB(nA), B.hijoIzqdoB(nB));
  }

  if(A.hijoIzqdoB(nA) != Abin<T>::NODO_NULO){
    B.insertarHijoDrchoB(nB, A.elemento(A.hijoIzqdoB(nA)));
    reflexion(A,B,A.hijoIzqdoB(nA), B.hijoDrchoB(nB));
  }
}

struct componente{
  double operando;
  char operador;
};

double aritmeticaRec(Abin<componente>& A, typename Abin<componente>::nodo n){
  if(A.hijoIzqdoB(n) != Abin<componente>::NODO_NULO){//Si el nodo tiene HijoIzq tiene q ser un OPERADOR (o tiene 2 o ninguno), sino, es un operando
      if(A.elemento(n).operador == '+') return aritmeticaRec(A, A.hijoIzqdoB(n)) + aritmeticaRec(A, A.hijoDrchoB(n));
      if(A.elemento(n).operador == '-') return aritmeticaRec(A, A.hijoIzqdoB(n)) - aritmeticaRec(A, A.hijoDrchoB(n));
      if(A.elemento(n).operador == '*') return aritmeticaRec(A, A.hijoIzqdoB(n)) * aritmeticaRec(A, A.hijoDrchoB(n));
      if(A.elemento(n).operador == '/') return aritmeticaRec(A, A.hijoIzqdoB(n)) / aritmeticaRec(A, A.hijoDrchoB(n));
  }
  return A.elemento(n).operando;
}

double aritmetica(Abin<componente>& A){
  return aritmeticaRec(A, A.raizB());
}

int main(){
  Abin<componente> a;
  componente c;
  c.operador = '+';
  a.insertarRaizB(c);
  c.operando = 1;
  c.operador = '*';
  a.insertarHijoIzqdoB(a.raizB(), c);
  a.insertarHijoDrchoB(a.raizB(), c);
  a.insertarHijoIzqdoB(a.hijoIzqdoB(a.raizB()), c);
  a.insertarHijoDrchoB(a.hijoIzqdoB(a.raizB()), c);
  std::cout << aritmetica(a) << std::endl;
  //imprimirAbin(reflejado(a));
}
