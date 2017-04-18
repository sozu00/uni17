#include <math.h>
#include "abin.h"
#include "Apo.h"
#include "Agen.h"
#include "practica3.cpp"
#include <vector>

//Ejercicio 1
template <typename T>
void buscar(int e, Abin<int>& A, typename Abin<T>::nodo& r, typename Abin<T>::nodo n){
  if(A.elemento(n) == e) r = n ;
  else {
    buscar(e, A, r, A.hijoIzqdoB(n));
    buscar(e, A, r, A.hijoDrchoB(n));
  }
}

template <typename T>
void reordenar(typename Abin<T>::nodo n, Abin<T>& A){
  //Si tiene hijos, SUBO el menor
  if(A.hijoIzqdoB(n) != Abin<T>::NODO_NULO){
    if(A.hijoDrchoB(n)!= Abin<T>::NODO_NULO)
      if(A.elemento(A.hijoDrchoB(n)) < A.elemento(A.hijoIzqdoB(n))) reordenar(A.hijoDrchoB(n), A);
    else reordenar(A.hijoIzqdoB(n), A);
  }
  else
    if(A.hijoDrchoB(n)!= Abin<T>::NODO_NULO) reordenar(A.hijoDrchoB(n), A);
}

template <typename T>
void eliminarElto(int e, Abin<T>& A){
  typename Abin<T>::nodo n = Abin<T>::NODO_NULO;
  buscar(e, A, n, A.raizB());
  reordenar(n, A);
}


//Ejercicio 4
template <typename T>
bool ternarioRec(Agen<T>& A, typename Agen<T>::nodo n){
  if(gradoNodo(A, n) != 3 && gradoNodo(A, n) != 0) return false;
  for(typename Agen<T>::nodo her = A.hijoIzqdo(n); her = A.hermDrcho(n); her != Agen<T>::NODO_NULO)
    if (!ternarioRec(A, her)) return false;
  return true;
}

template <typename T>
bool ternario(Agen<T>& A){
  return ternarioRec(A, A.raiz());
}

//Ejercicio 5
//Asumo que la estructura es: char color, int[2] superiorIzq, int[2] inferiorDer
using namespace std;

enum Color{blanco, negro, sinColor};
struct ej5{
  Color color;
  vector<int> superiorIzq;
  vector<int> inferiorDer;
};


template <typename T>
void colorear(vector< vector<bool> >& matriz, Agen<ej5>& A, typename Agen<T>::nodo n){
  bool color = (A.elemento(n).color == blanco) ? true : false;
  for(int i = A.elemento(n).superiorIzq[0]; i < A.elemento(n).inferiorDer[0]; i++)
    for(int j = A.elemento(n).superiorIzq[1]; j < A.elemento(n).inferiorDer[1]; j++)
      matriz[i][j] = color;
}

template <typename T>
void dibujar(Agen<ej5>& A, typename Agen<T>::nodo n, vector< vector<bool> >& matriz){
  if(n != Agen<T>::NODO_NULO){
    if(A.elemento(n).color == sinColor)
      for(typename Agen<T>::nodo her = A.hijoIzqdo(n); her = A.hermDrcho(n); her != Agen<T>::NODO_NULO)
        dibujar(A, her, matriz);
    else colorear(A, n, matriz);

  }
}

template <typename T>
vector< vector<bool> > dibujar(Agen<T>& A){//true = blanco, false = negro
  int k = altura(A, A.raiz())-1;
  int tamMatriz = int(pow(2,k));
  vector< vector<bool> > matriz(pow(2,k), vector<bool>(pow(2,k),true));
  dibujarRec(A, A.raiz(), matriz);
}


int main(){

}
