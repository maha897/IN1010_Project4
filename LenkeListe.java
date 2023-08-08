import java.util.Iterator;

class LenkeListe<T> implements Liste<T> {
    protected Node start;
    protected Node slutt;
    protected int lengde;

    protected class LenkelisteIterator implements Iterator<T> {
        Node tmp = start;

        @Override
        public boolean hasNext() {
            return tmp != null;
        }

        @Override
        public T next() {
            T element = tmp.hentElement();
            tmp = tmp.hentNeste();
            return element;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LenkelisteIterator();
    }

    protected class Node {
        T element;
        Node forrige;
        Node neste;
        int index;

        public String toString() {
            return ("Index: " + index + "   Element:  " + element);
        }

        public Node(T element) {
            this.element = element;
            this.neste = null;
            this.forrige = null;
            index = 0;
        }

        public Node(T element, int index) {
            this.element = element;
            this.neste = null;
            this.forrige = null;
            this.index = index;
        }

        public T hentElement() {
            return element;
        }

        public void settInnEtter(Node settInnNode) {
            // Setter inn settInnNode etter denne noden og flytter alle de tidligere
            // referansene
            Node tempNeste = this.neste;

            this.neste = settInnNode;
            if (tempNeste != null) {
                tempNeste.forrige = settInnNode;
            }
            settInnNode.settForrige(this);
            settInnNode.settNeste(tempNeste);

        }

        public void settInnFor(Node settInnNode) {

            // Setter inn settInnNode for denne noden og flytter alle de tidligere
            // referansene
            settInnNode.settForrige(forrige);
            settInnNode.settNeste(this);

            if (forrige != null) {

                this.forrige.neste = settInnNode;
            }
            this.forrige = settInnNode;
        }

        public void settElement(T element) {
            this.element = element;
        }

        public Node hentNeste() {
            return neste;
        }

        public Node hentForrige() {
            return forrige;
        }

        public int hentIndex() {
            return index;
        }

        public void settIndex(int nyIndex) {
            this.index = nyIndex;
        }

        public void settNeste(Node neste) {

            this.neste = neste;
            return;

        }

        public void settForrige(Node forrige) {

            this.forrige = forrige;
            return;

        }
    }

    public LenkeListe() {
        start = null;
        slutt = null;

        lengde = 0;

    }

    public int stoerrelse() {
        return lengde;
    }

    public T fjern() {
        // Ingen elementer i lista enda kan ikke fjerne noe
        if (start == null) {
            throw new UgyldigListeindeks(0);
        }

        Node n = start.hentNeste();
        if (n != null) {
            n.settForrige(null);
        }

        T element = start.hentElement();
        start = n;
        lengde--;
        return element;
    }

    public T hent() {
        return hentDataElement(start);
    }

    protected T hentDataElement(Node n) {
        if (n == null) {
            return null;
        }
        return n.hentElement();
    }

    protected void startListe(T startdata) {
        Node startNode = new Node(startdata, 0);
        start = startNode;
        slutt = startNode;
        lengde++;
        return;

    }

    public void leggTil(T x) {
        if (stoerrelse() == 0) {
            startListe(x);
            return;
        }
        Node nyNode = new Node(x, 0);

        // Setter inn pa slutten
        slutt.settInnEtter(nyNode);
        slutt = nyNode;
        lengde++;

    }

}
