package TFIDF;

public class tupla<S, T> {
    private S idf;
    @SuppressWarnings("CanBeFinal")
    public T docPeso;
    
    public void setIDF(S idf){this.idf = idf;}
    
    public S getIDF(){return idf;}
    public T docPeso(){return docPeso;}
    public tupla(S idf, T docPeso) {
        this.idf = idf;
        this.docPeso = docPeso;
    }
    
    public String toString(){
    	return idf + "@@"+ docPeso;
    }
}
