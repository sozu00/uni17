#include "Abb.h"
#include <vector>
#include <iostream>

//Ejercicio 3
template <typename T>
void equilibradoV(Abb<T>& A, std::vector<T>& e){
  if(!A.vacio()){
    equilibradoV(A.izqdo(), e);
    e.push_back(A.elemento());
    equilibradoV(A.drcho(), e);
    A.eliminar(A.elemento());
  }
}

template <typename T>
void insertar(Abb<T>& A, std::vector<T> e, size_t p){
    if(p < e.size() && p >= 0){
      A.insertar(e[p]);
      insertar(A.izqdo(), e, (p-1)/2);
      insertar(A,drcho(), e, p+(p-1)/2);
    }
}

template <typename T>
void equilibrar(Abb<T>& A){
  std::vector<T> e;
  equilibradoV(A, e);
  insertar(A, e, (e.size()-1)/2);
}


//Ejercicio 4
template <typename T>
Abb<T union(Abb<T>& A, Abb<T>& B){
  Abb<T> R(A);
  Abb<T> aux(B);
  while(!aux.vacio()){
    R.insertar(aux.elemento());
    aux.eliminar(aux.elemento());
  }
  equilibrar(R);
  return R;
}

//Ejercicio 5
template <typename T>
Abb<T interseccion(Abb<T>& A, Abb<T>& B){
  Abb<T> R(A);
  Abb<T> aux(B);
  while(!aux.vacio()){
    if(R.buscar(aux.elemento()).vacio())
      R.insertar(aux.elemento());
    aux.eliminar(aux.elemento());
  }
  equilibrar(R);
  return R;
}

//Ejercicio 6
template <typename T>
Abb<T rombo(Abb<T>& A, Abb<T>& B){
  Abb<T> U = union(A, B);
  Abb<T> I = interseccion(A, B);
  Abb<T> R;
  while(!U.vacio()){
    //Copio toda la union que NO esta en la interseccion
    if(I.buscar(U.elemento()).vacio())
      //Compruebo que no esta en I
      R.insertar(U.elemento());
    U.eliminar(U.elemento());
  }
  equilibrar(R);
  return R;
}


int main ()
{
  std::vector<int> myvector (3,100);
  std::vector<int>::iterator it;

  it = myvector.begin();
  it = myvector.insert ( it , 200 );

  myvector.insert (it,2,300);

  // "it" no longer valid, get a new one:
  it = myvector.begin();

  std::vector<int> anothervector (2,400);
  myvector.insert (it+2,anothervector.begin(),anothervector.end());

  int myarray [] = { 501,502,503 };
  myvector.insert (myvector.begin(), myarray, myarray+3);
  myvector.insert (myvector.begin(), 1);
  std::cout << "myvector contains:";
  for (it=myvector.begin(); it<myvector.end(); it++)
    std::cout << ' ' << *it;
  std::cout << '\n';

  return 0;
}
