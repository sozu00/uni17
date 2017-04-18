#ifndef AMinMax_H
#define AMinMax_H
#include <cassert>

template <typename T> class AMinMax {
public:
	explicit AMinMax(int maxNodos); // constructor
	void insertar(const T& e);
	void suprimir();
	T cima() const;
	bool vacio() const;
	AMinMax(const AMinMax<T>& a); // ctor. de copia
	AMinMax<T>& operator =(const AMinMax<T>& a); // asignaci�n de AMinMax
	~AMinMax(); // destructor
private:
	typedef int nodo; // �ndice del vector
	// entre 0 y maxNodos-1
	T* nodos; // vector de nodos
	int maxNodos; // tama�o del vector
	nodo ultimo; // �ltimo nodo del �rbol
};

template <typename T>
inline AMinMax<T>::AMinMax (int maxNodos) :
	nodos(new T[maxNodos]),
	maxNodos(maxNodos),
	ultimo(-1) // AMinMax vac�o
{}

template <typename T>
inline T AMinMax<T>::cima() const
{
	assert(ultimo > -1); // AMinMax no vac�o
	return nodos[0];
}

template <typename T>
inline bool AMinMax<T>::vacio() const
{
	return (ultimo > -1);
}
template <typename T>
void AMinMax<T>::insertar(const T& e){
	assert(ultimo < maxNodos-1);
	nodo p = ++ultimo;
  int pos = (maxNodos-1);
  int paridad = ((int)floor(log(maxNodos)))%2;
	nodos[pos] = e;

  if(paridad == 0 && e>nodos[pos/2]) {
    nodos[pos] = nodos[pos/2];
    pos = (pos-1)/2;
    paridad = 1;
  }
  if(paridad != 0 && e<nodos[pos/2]) {
    nodos[pos] = nodos[pos/2];
    pos = (pos-1)/2;
    paridad = 0;
  }

  if(paridad == 0){
    while(e<nodos[pos/4]) {
      nodos[pos] = nodos[(pos-1)/4];
      pos = (pos-1)/4;
    }
  }
  if(paridad != 0){
    while(e>nodos[pos/4]) {
			nodos[pos] = nodos[(pos-1)/4];
      pos = (pos-1)/4;
    }
  }
	nodos[pos] = e;
}

template <typename T>
void AMinMax<T>::suprimir()
{
	assert(ultimo > -1); // AMinMax no vac�o
	T e = nodos[ultimo];
	--ultimo;
	if (ultimo > -1) { // AMinMax no vac�o.
		nodo p = 0;
		if (ultimo > 0) { // quedan dos o m�s elementos. Reordenar
		bool fin = false;
		while (p <= (ultimo-1)/2 && !fin) { // hundir e
			nodo pMin; // hijo menor del nodo p
			if (2*p+1 == ultimo) pMin = 2*p+1;
			else if (nodos[2*p+1] < nodos[2*p+2]) pMin = 2*p+1;
			else pMin = 2*p+2;
			if (nodos[pMin] < e) { // subir el hijo menor
				nodos[p] = nodos[pMin];
				p = pMin;
			}
			else // e <= hijos
				fin = true;
		}
		}
	nodos[p] = e; // colocar e
	}
}

template <typename T>
inline AMinMax<T>::~AMinMax()
{
	delete[] nodos;
}

template <typename T>
AMinMax<T>::AMinMax(const AMinMax<T>& a) :
	nodos(new T[a.maxNodos]),
	maxNodos(a.maxNodos),
	ultimo(a.ultimo)
{
	// copiar el vector
	for (nodo n = 0; n <= ultimo; n++)
		nodos[n] = a.nodos[n];
}

template <typename T>
AMinMax<T>& AMinMax<T>::operator =(const AMinMax<T>& a)
{
	if (this != &a) { // evitar autoasignaci�n
	// Destruir el vector y crear uno nuevo si es necesario
	if (maxNodos != a.maxNodos) {
		delete[] nodos;
		maxNodos = a.maxNodos;
		nodos = new T[maxNodos];
	}
	ultimo = a.ultimo;
// Copiar el vector
	for (nodo n = 0; n <= ultimo; n++)
		nodos[n] = a.nodos[n];
}
	return *this;
}


#endif // AMinMax_H
