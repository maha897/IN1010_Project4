class Koe<T> extends LenkeListe<T> {
    @Override
    public T fjern() {
        // Ingen elementer i lista enda kan ikke fjerne noe
        if (slutt == null) {
            throw new UgyldigListeindeks(0);
        }

        Node n = slutt.hentForrige();
        slutt.settNeste(null);

        T element = slutt.hentElement();
        slutt = n;
        lengde--;
        return element;
    }

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

    @Override
    public T hent() {
        return hentDataElement(slutt);
    }
}