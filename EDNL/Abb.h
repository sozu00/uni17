#ifndef ABB_H_
#define ABB_H_
#include <cassert>
#include "Abin.h"

template <typename T> 
class Abb {
    struct celda;   // declaración adelantada privada
    public:
        typedef celda* nodo;
        static const nodo NODO_NULO;
        explicit Abb(nodo n = NODO_NULO);      // constructor
        nodo buscar(const T& e) const;
        void insertar(const T& e);
        void eliminar(const T& e);
        bool vacio() const;
        Abb(const Abb<T>& a);                 // ctor. de copia
        Abb<T>& operator =(const Abb<T>& a);  // asig. árboles
        //Ejercicio 1
        operator Abin<T>();
        //Ejercicio 2
        void eliminarNodo(const T& e);
        ~Abb();   							//Destructor
    private:                            
        struct celda {
            T elto;
            nodo hizq, hder;
            celda(const T& e): elto(e), hizq(NODO_NULO), hder(NODO_NULO) {}
        };
        nodo r;   // nodo raíz del árbol
        T borrarMin(nodo& n);
        nodo copiar(nodo n);
        void copiarnodos(nodo, typename Abin<T>::nodo, Abin<T>&) const;
        void eliminarNodos(nodo n);
};
/* Definición del nodo nulo */
template <typename T>
const typename Abb<T>::nodo Abb<T>::NODO_NULO(0);

template <typename T>
inline Abb<T>::Abb(nodo n) : r(n) {}

template <typename T>
inline bool Abb<T>::vacio() const
{
    return (r == NODO_NULO);
}

template <typename T>
typename Abb<T>::nodo Abb<T>::buscar(const T& e) const
{
    nodo n;
    if (r == NODO_NULO)     // árbol vacío, e no encontrado
        n = NODO_NULO;
    else if (e == r->elto)  // encontrado e en la raíz
        n = r;
    else if (e < r->elto) { // buscar en subárbol izqdo.
        Abb<T> Ai(r->hizq);
        n = Ai.buscar(e);
        Ai.r = NODO_NULO;    // impide destruir el subárbol izqui
    }
    else {                  // buscar en subárbol drcho.
        Abb<T> Ad(r->hder);
        n = Ad.buscar(e);
        Ad.r = NODO_NULO;    // impide destruir el subárbol derecho
    }
    return n;
}

template <typename T>
void Abb<T>::insertar(const T& e)
{
    if (r == NODO_NULO)         // árbol vacío
        r = new celda(e);
    else if (!(e == r->elto)) { // e no está en la raíz.
        if (e < r->elto) {       // insertar en el subárbol izqdo.
            Abb<T> Ai(r->hizq);
            Ai.insertar(e);
            r->hizq = Ai.r;
            Ai.r = NODO_NULO;     // impide destruir el subárbol drcho.
        }
        else {  // insertar en el subárbol drcho.
            Abb<T> Ad(r->hder);
            Ad.insertar(e);
            r->hder = Ad.r;
            Ad.r = NODO_NULO;     // impide destruir el subárbol drcho.
        }
    }
}

template <typename T>
void Abb<T>::eliminar(const T& e)
{
    if (r != NODO_NULO) {  // árbol no vacío
        if (e == r->elto) { // quitar elemento de la raíz
            if (r->hizq == NODO_NULO && r->hder == NODO_NULO) {//1.Raíz es hoja
                delete(r);
                r = NODO_NULO;   // el árbol queda vacío
            }
            else if (r->hder == NODO_NULO) { // 2.Raíz sólo tiene hijo izqdo.
                nodo n = r->hizq;
                delete(r);
                r = n;                    // nueva raíz el antiguo hijo izqdo.
            }
            else if (r->hizq == NODO_NULO) { // 3.Raíz sólo tiene hijo drcho.
                nodo n = r->hder;
                delete(r);
                r = n;                    // nueva raíz el antiguo hijo drcho.
            }
            else // 4.La raíz tiene dos hijos.
                r->elto = borrarMin(r->hder); // sustituir el elemento de la 
// raíz por el mínimo del 
// subárbol derecho
        }
        else if (e < r->elto) {//quitar elemento del subárbol izqdo.
            Abb<T> Ai(r->hizq);
            Ai.eliminar(e);
            r->hizq = Ai.r;
            Ai.r = NODO_NULO; // impide destruir el subárbol izqdo.
        }
        else {               // quitar elemento del subárbol drcho.
            Abb<T> Ad(r->hder);
            Ad.eliminar(e);
            r->hder = Ad.r;
            Ad.r = NODO_NULO; // impide destruir el subárbol drcho.
        }
    }
}

// Método privado
template <typename T>
T Abb<T>::borrarMin(Abb<T>::nodo& n)
// Elimina el nodo que almacena el menor elemento
// del subárbol cuya raíz es n. Devuelve el elemento
// del nodo eliminado
{
    if (n->hizq == NODO_NULO) {
        T e = n->elto;
        nodo m = n->hder;
        delete(n);
        n = m;
        return e;
    }
    else
        return borrarMin(n->hizq);
}

template <typename T>
inline Abb<T>::Abb(const Abb<T>& a)
{
    r = copiar(a.r);
}

template <typename T>
Abb<T>& Abb<T>::operator =(const Abb<T>& a)
{
    if (this != &a) {   // evitar autoasignación
        this->~Abb();    // vaciar el árbol
        r = copiar(a.r);
    }
    return *this;
}

template <typename T>
inline Abb<T>::~Abb()
{
    if (r != NODO_NULO) {   // árbol no vacío
        Abb<T> Ai(r->hizq),
        Ad(r->hder);
        delete r;
    }
}
// Método privado
template <typename T>
typename Abb<T>::nodo Abb<T>::copiar(Abb<T>::nodo n)
// Devuelve una copia del nodo n y todos sus descendientes
{
    nodo m = NODO_NULO;
    if (n != NODO_NULO) {
        m = new celda(n->elto);    // copiar n
        m->hizq = copiar(n->hizq); // copiar subárbol izqdo.
        m->hder = copiar(n->hder); // copiar subárbol drcho.
    }
    return m;
}

//Ejercicio 1

template <typename T>
Abb<T>::operator Abin<T>() 
{
    Abin<T> arb;
    if(!vacio())
    {
        arb.crearRaizB(r->elto);
        copiarnodos(r, arb.raizB(), arb);
    }
    return arb;
}

template <typename T>
void Abb<T>::copiarnodos(nodo n, typename Abin<T>::nodo m, Abin<T>& arb) const
{
    if(n->hizq != NODO_NULO)
    {
        arb.insertarHijoIzqdoB(m, n->hizq->elto);
        copiarnodos(n->hizq,arb.hijoIzqdoB(m), arb);
    }
    if(n->hder != NODO_NULO)
    {
        arb.insertarHijoDrchoB(m, n->hder->elto);
        copiarnodos(n->hder, arb.hijoDrchoB(m), arb);
    }
}

//Ejercicio 2

template <typename T>
void Abb<T>::eliminarNodo (const T& e){
	eliminarNodos(buscar(e));
}

template <typename T>
void Abb<T>::eliminarNodos(Abb<T>::nodo n){
	if(n != Abb<T>::NODO_NULO){
		eliminarNodos(n->hizq);
		eliminarNodos(n->hder);
		eliminar(n->elto);
	}	
}

#endif // ABB_H
