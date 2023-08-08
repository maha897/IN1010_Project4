class Stabel<T> extends LenkeListe<T> {
    @Override
    public void leggTil(T x) {
        if (stoerrelse() == 0) {
            startListe(x);
            return;
        }

        Node nyNode = new Node(x);
        start.settInnFor(nyNode);
        start = nyNode;
        lengde++;
    }

}