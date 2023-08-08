
class IndeksertListe<T> extends LenkeListe<T> {
    public void leggTil(int pos, T x) {
        if (stoerrelse() == 0) {
            Node startNode = new Node(x, pos);
            start = startNode;
            slutt = startNode;
            lengde++;
            return;
        }

        Node nyNode = new Node(x, pos);
        Node indexPos = hentNodePaIndex(pos);
        Boolean leggesBak = indexPos.hentIndex() < nyNode.hentIndex();

        if (indexPos == start && !leggesBak) {
            // Noden skal legges inn for den forste sa da blir den nye starten
            start = nyNode;
        }

        if (leggesBak) {
            // Noden er mindre en funnet
            indexPos.settInnEtter(nyNode);
            shiftIndexMedEn(nyNode.hentNeste());

        } else {
            // Storre eller lik legges for
            indexPos.settInnFor(nyNode);
            shiftIndexMedEn(indexPos);

        }
        lengde++;
    }

    private void shiftIndexMedEn(Node node) {
        while (node != null) {
            node.settIndex(node.hentIndex() + 1);
            node = node.hentNeste();
        }
    }

    Node hentNodePaIndex(int pos) {
        Node node = start;
        while (node.hentNeste() != null && node.hentIndex() != pos) {
            node = node.hentNeste();
        }
        return node;
    }

    Node finnGyldigNodePaIndex(int pos) {
        // Alle funksjoner som leter etter node skal bruke denne

        // Innvariant
        // Noden paa pos maa finnes
        // Index >= 0
        // Index <= lengde
        Node sjekkNode = findNode(pos);
        if (sjekkNode.hentIndex() != pos | pos > stoerrelse() | pos < 0) {
            throw new UgyldigListeindeks(pos);
        }
        return sjekkNode;
    }

    void printListe() {
        // Debugger som pringer hele lista
        Node node = start;
        while (node != null) {
            System.out.println("ut " + node);
            node = node.hentNeste();
        }

    }

    Node findNode(int pos) {
        Node head = start;
        while (head.hentIndex() != pos && head.hentNeste() != null) {
            head = head.hentNeste();
        }
        return head;
    }

    public T fjern(int pos) throws UgyldigListeindeks {

        Node fjern = finnGyldigNodePaIndex(pos);

        // Henter referansene til denne noden sin forrige og neste
        // Linker disse sammen da fjerner man "fjern" noden ifra lenklista
        Node tempForrige = fjern.hentForrige();
        Node tempNeste = fjern.hentNeste();
        if (fjern == start) {
            start = fjern.hentNeste();
        }

        if (tempForrige != null) {
            tempForrige.settNeste(tempNeste);
        }

        if (tempNeste != null) {
            tempNeste.settForrige(tempForrige);
        }

        lengde--;

        return fjern.hentElement();

    }

    public T hent(int pos) throws UgyldigListeindeks {

        Node nodeFunnet = finnGyldigNodePaIndex(pos);
        return nodeFunnet.hentElement();

    }

    public void sett(int pos, T x) throws UgyldigListeindeks {

        Node nodeFunnet = finnGyldigNodePaIndex(pos);
        nodeFunnet.settElement(x);
    }
}