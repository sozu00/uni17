#ifndef ATER_H
#define ATER_H
#include <cassert>

template <typename T> class Ater {
    struct celda; // declaración adelantada privada
public:
    typedef celda* nodo;
    static const nodo NODO_NULO;
    Ater(); // constructor
    void crearRaiz(const T& e);
    void insertarHijoIzqdo(nodo n, const T& e);
    void insertarHijoCentral(nodo n, const T& e);
    void insertarHijoDrcho(nodo n, const T& e);
    void eliminarHijoIzqdo(nodo n);
    void eliminarHijoCentral(nodo n);
    void eliminarHijoDrcho(nodo n);
    void eliminarRaiz();
    bool arbolVacio() const;
    T elemento(nodo n) const; // acceso a elto, lectura
    T& elemento(nodo n); // acceso a elto, lectura/escritura
    nodo raiz() const;
    nodo padre(nodo n) const;
    nodo hijoIzqdo(nodo n) const;
    nodo hijoCentral(nodo n) const;
    nodo hijoDrcho(nodo n) const;
    Ater(const Ater<T>& a); // ctor. de copia
    Ater<T>& operator =(const Ater<T>& a); // asignación de árboles
    ~Ater(); // destructor
private:
    struct celda {
        T elto;
        nodo padre, hizq, hcent, hder;
        celda(const T& e, nodo p = NODO_NULO): elto(e),
        padre(p), hizq(NODO_NULO), hcent(NODO_NULO), hder(NODO_NULO) {}
    };
    nodo r; // nodo raíz del árbol
    void destruirNodos(nodo& n);
    nodo copiar(nodo n);
};

/* Definición del nodo nulo */
template <typename T>
const typename Ater<T>::nodo Ater<T>::NODO_NULO(0);

template <typename T>
inline Ater<T>::Ater() : r(NODO_NULO) {}

template <typename T>
inline void Ater<T>::crearRaiz(const T& e)
{
    assert(r == NODO_NULO); // árbol vacío
    r = new celda(e);
}

template <typename T>
inline void Ater<T>::insertarHijoIzqdo(Ater<T>::nodo n, const T& e)
{
    assert(n != NODO_NULO);
    assert(n->hizq == NODO_NULO);// no existe hijo
    n->hizq = new celda(e, n);
}

template <typename T>
inline void Ater<T>::insertarHijoDrcho(Ater<T>::nodo n, const T& e)
{
    assert(n != NODO_NULO);
    assert(n->hder == NODO_NULO); // no existe hijo
    n->hder = new celda(e, n);
}

template <typename T>
inline void Ater<T>::insertarHijoCentral(Ater<T>::nodo n, const T& e)
{
    assert(n != NODO_NULO);
    assert(n->hcent == NODO_NULO); // no existe hijo
    n->hcent = new celda(e, n);
}

template <typename T>
inline void Ater<T>::eliminarHijoIzqdo(Ater<T>::nodo n)
{
    assert(n != NODO_NULO);
    assert(n->hizq != NODO_NULO); // existe hijo izqdo.
    assert(n->hizq->hizq == NODO_NULO && n->hizq->hder == NODO_NULO); // hijo izqdo. // es hoja
    delete(n->hizq);
    n->hizq = NODO_NULO;
}

template <typename T>
inline void Ater<T>::eliminarHijoDrcho(Ater<T>::nodo n)
{
    assert(n != NODO_NULO);
    assert(n->hder != NODO_NULO); // existe hijo drcho.
    assert(n->hder->hizq == NODO_NULO && // hijo drcho.
    n->hder->hder == NODO_NULO); // es hoja
    delete(n->hder);
    n->hder = NODO_NULO;
}

template <typename T>
inline void Ater<T>::eliminarHijoCentral(Ater<T>::nodo n)
{
    assert(n != NODO_NULO);
    assert(n->hcent != NODO_NULO); // existe hijo drcho.
    assert(n->hcent->hizq == NODO_NULO && // hijo drcho.
    n->hcent->hder == NODO_NULO); // es hoja
    delete(n->hcent);
    n->hcent = NODO_NULO;
}

template <typename T>
inline void Ater<T>::eliminarRaiz()
{
    assert(r != NODO_NULO); // árbol no vacío
    assert(r->hizq == NODO_NULO); // la raíz es hoja
    delete(r);
    r = NODO_NULO;
}

template <typename T>
inline bool Ater<T>::arbolVacio() const
{
    return (r == NODO_NULO);
}

template <typename T>
inline T Ater<T>::elemento(Ater<T>::nodo n) const
{
    assert(n != NODO_NULO);
    return n->elto;
}

template <typename T>
inline T& Ater<T>::elemento(Ater<T>::nodo n)
{
    assert(n != NODO_NULO);
    return n->elto;
}

template <typename T>
inline typename Ater<T>::nodo Ater<T>::raiz() const
{
    return r;
}

template <typename T>
inline typename Ater<T>::nodo Ater<T>::padre(Ater<T>::nodo n) const
{
    assert(n != NODO_NULO);
    return n->padre;
}

template <typename T>
inline typename Ater<T>::nodo Ater<T>::hijoIzqdo(Ater<T>::nodo n) const
{
    assert(n != NODO_NULO);
    return n->hizq;
}

template <typename T>
inline typename Ater<T>::nodo Ater<T>::hijoDrcho(Ater<T>::nodo n) const
{
    assert(n != NODO_NULO);
    return n->hder;
}

template <typename T>
inline typename Ater<T>::nodo Ater<T>::hijoCentral(Ater<T>::nodo n) const
{
    assert(n != NODO_NULO);
    return n->hcent;
}

template <typename T>
Ater<T>::Ater(const Ater<T>& a)
{
    r = copiar(a.r);
}

template <typename T>
Ater<T>& Ater<T>::operator =(const Ater<T>& a)
{
    if (this != &a) { // evitar autoasignación
        this->~Ater(); // vaciar el árbol
        r = copiar(a.r);
    }
    return *this;
}

template <typename T>
inline Ater<T>::~Ater()
{
    destruirNodos(r); // vacía el árbol
}

// Métodos privados
// Destruye un nodo y todos sus descendientes
template <typename T>
void Ater<T>::destruirNodos(Ater<T>::nodo& n)
{
    if (n != NODO_NULO) {
        destruirNodos(n->hizq);
        destruirNodos(n->hcent);
        destruirNodos(n->hder);
        delete n;
        n = NODO_NULO;
    }
}

// Devuelve una copia de un nodo y todos sus descendientes
template <typename T>
typename Ater<T>::nodo Ater<T>::copiar(Ater<T>::nodo n)
{
    nodo m = NODO_NULO;
    if (n != NODO_NULO) {
        m = new celda(n->elto); // copiar n
        m->hizq = copiar(n->hizq); // copiar subárbol izqdo.
        if (m->hizq != NODO_NULO)
                m->hizq->padre = m;
        m->hcent = copiar(n->hcent); // copiar subárbol central.
        if (m->hcent != NODO_NULO)
                m->hcent->padre = m;
        m->hder = copiar(n->hder); // copiar subárbol drcho.
        if (m->hder != NODO_NULO)
                m->hder->padre = m;
    }
    return m;
}

#endif // ATER_H
